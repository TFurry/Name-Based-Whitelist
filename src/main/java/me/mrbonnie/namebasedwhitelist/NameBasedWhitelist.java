package me.mrbonnie.namebasedwhitelist;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

    public class NameBasedWhitelist extends JavaPlugin implements Listener {

        private FileConfiguration config;
        private FileConfiguration whitelist;

        @Override
        public void onEnable() {
            // Create plugin directory
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }

            // Create config.yml
            File configFile = new File(getDataFolder(), "config.yml");
            if (!configFile.exists()) {
                saveResource("config.yml", false);
            }
            config = YamlConfiguration.loadConfiguration(configFile);

            // Create whitelist.yml
            File whitelistFile = new File(getDataFolder(), "whitelist.yml");
            if (!whitelistFile.exists()) {
                try {
                    whitelistFile.createNewFile();
                } catch (IOException e) {
                    getLogger().severe("Failed to create whitelist.yml");
                }
            }
            whitelist = YamlConfiguration.loadConfiguration(whitelistFile);

            // Register events
            getServer().getPluginManager().registerEvents(this, this);
        }

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (command.getName().equalsIgnoreCase("wladd")) {
                if (!sender.hasPermission("namewhitelist.add")) {
                    sender.sendMessage("You don't have permission to use this command.");
                    return true;
                }
                if (args.length != 1) {
                    sender.sendMessage("Usage: /wladd <username>");
                    return true;
                }
                String username = args[0];
                List<String> whitelistedPlayers = whitelist.getStringList("players");
                if (whitelistedPlayers.contains(username)) {
                    sender.sendMessage("Player already on the whitelist.");
                } else {
                    whitelistedPlayers.add(username);
                    whitelist.set("players", whitelistedPlayers);
                    saveWhitelist();
                    sender.sendMessage("Player added to the whitelist.");
                }
                return true;
            } else if (command.getName().equalsIgnoreCase("wlremove")) {
                if (!sender.hasPermission("namewhitelist.remove")) {
                    sender.sendMessage("You don't have permission to use this command.");
                    return true;
                }
                if (args.length != 1) {
                    sender.sendMessage("Usage: /wlremove <username>");
                    return true;
                }
                String username = args[0];
                List<String> whitelistedPlayers = whitelist.getStringList("players");
                if (!whitelistedPlayers.contains(username)) {
                    sender.sendMessage("Player not found on the whitelist.");
                } else {
                    whitelistedPlayers.remove(username);
                    whitelist.set("players", whitelistedPlayers);
                    saveWhitelist();
                    sender.sendMessage("Player removed from the whitelist.");
                }
                return true;
            }
            return false;
        }

        @EventHandler
        public void onPlayerJoin(PlayerJoinEvent event) {
            if (!config.getBoolean("enabled")) {
                return;
            }
            String username = event.getPlayer().getName();
            if (!whitelist.getStringList("players").contains(username)) {
                event.getPlayer().kickPlayer("You are not on the whitelist.");
            }
        }

        private void saveWhitelist() {
            try {
                whitelist.save(new File(getDataFolder(), "whitelist.yml"));
            } catch (IOException e) {
                getLogger().severe("Failed to save whitelist.yml");
            }
        }
    }
