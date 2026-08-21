Is a simple and flexible whitelist plugin that allows you to manage access using player nicknames instead of UUIDs.
## Installation
1. Disable the default Minecraft whitelist in `server.properties` by setting:
   white-list=false
2. Place the plugin `.jar` file into the `plugins` folder  
3. Start or restart your server  
## MySQL Setup (Optional)
If you want to use MySQL instead of YAML:
1. Set `storage-type` to `mysql` in the config  
2. Enter your database credentials (host, port, database, user, password)  
3. Restart your server  
## Commands
| Command | Description |
|----------|-------------|
| `/nbwl add <username>` | Add a player to the whitelist |
| `/nbwl remove <username>` | Remove a player from the whitelist |
| `/nbwl enable` | Enable Name-Based Whitelist |
| `/nbwl disable` | Disable Name-Based Whitelist |
| `/nbwl reload` | Reload configuration |
## Permissions
| Permission | Description |
|-------------|-------------|
| `namebasedwhitelist.*` | Access to all commands |
| `namebasedwhitelist.modify` | Permission to add/remove players |
| `namebasedwhitelist.manage` | Permission to reload and toggle whitelist |
