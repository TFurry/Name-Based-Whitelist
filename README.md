## Simple whitelist plugin for your offline-mode server.

###  How to start using plugin?

Disable Minecraft's whitelist in server.properties (white-list=false)

Put plugin in plugins folder

Start your server

###  Commands:

`/nbwl add <username>` - add player to whitelist

`/nbwl remove <username>` - remove player from whitelist

`/nbwl on/off` - turns on/off the name-based whitelist

###  Permissions:

`namewhitelist.add` - for add command

`namewhitelist.remove` - for remove command

`namewhitelist.toggle` - for on/off command


###  Config.yml:

```
Enabling or disabling Whitelist. (true or false)
enabled: true

Kick message text.
kick-message: "You are not on the whitelist."
```
