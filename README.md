<div align="center">

# Xyris Death Messages Plugin

![Plugin Version](https://img.shields.io/badge/version-v2.0.0-blue)
![Minecraft Versions](https://img.shields.io/badge/Minecraft-1.16%20to%201.20.4-brightgreen)
  <a href="link_to_license">
    <img src="https://img.shields.io/github/license/Darkxx14/XyrisDeathMessages?color=blue&label=License&style=flat-square" alt="License">
  </a>
</p>

</div>

## Overview

Xyris Death Messages is a customizable death messages plugin for Spigot servers. Easily manage and customize player death messages, toggle their visibility, and add unlimited custom death messages.

### Features

- **Multi-Version Support:** Compatible with Minecraft versions 1.16 to 1.20.4.
- **Customizable Messages:** Edit, add, and randomize death messages for a unique player experience.
- **Visibility Toggle:** Control the visibility of death messages with a simple command.
- **Customize Kill and Death Titles:** Personalize kill and death titles through the `config.yml`.
- **Customize Kill and Death Action Bars:** Tailor kill and death action bars via the `config.yml`.
- **Hex Support:** Utilize hexadecimal support.
- **Configurable:** Customize messages via the `config.yml` and `messsges.yml` file.

## Installation

1. **Download:** Obtain the plugin from the [Release page](https://github.com/Darkxx14/XyrisDeathMessages/releases/).
2. **Installation:** Place the `XyrisDeathMessages.jar` file into your server's `plugins` folder.
3. **Activation:** Restart your server to enable the plugin.

## Usage

### Commands:

- `/xyrisdm reload`: Reloads the configuration. Server restart required for changes to take effect.
- `/toggledeathmessage`: Toggles visibility of death messages for the user.

### Configuration:

The `config.yml` file allows you to customize death messages. Edit, add, or remove messages to suit your server's theme or style.

```yaml
#  (_____)                                         (_____)
#  |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   |
#  |   | ##     ## ##    ## ########  ####  ######  |   |
#  |   |  ##   ##   ##  ##  ##     ##  ##  ##    ## |   |
#  |   |   ## ##     ####   ##     ##  ##  ##       |   |
#  |   |    ###       ##    ########   ##   ######  |   |
#  |   |   ## ##      ##    ##   ##    ##        ## |   |
#  |   |  ##   ##     ##    ##    ##   ##  ##    ## |   |
#  |   | ##     ##    ##    ##     ## ####  ######  |   |
#  |   |    Xyris Death Messages - XyrisPlugins     |   |
#  |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___|
#  (_____)         https://xyris.fun/devs          (_____)

# Welcome to the configuration file for Xyris Death Messages. It's very easy to edit all the settings. If you encounter any issues while configuring or discover any bugs, please feel free to contact us at xyris.fun/devs.


# ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
# Titles
# ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

# Placeholders:
# %attacker% - Returns the attacker name.
# %victim% - Returns the victim name.

kill-titles:
  enabled: true
  kill-title: '&#00FF07&lʏᴏᴜ ᴡᴏɴ!'
  kill-subtitle: '&7You defeated %victim%!'

death-titles:
  enabled: true
  death-title: '&#FF0000&lʏᴏᴜ ʟᴏsᴛ!'
  death-subtitle: '&7You have been slain by %attacker%!'

# ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
# Action Bars
# ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

# Placeholders:
# %attacker% - Returns the attacker name.
# %victim% - Returns the victim name.
# %attacker_health% - Returns the attacker health.
# %victim_health% - Returns the victim health.

kill-action-bar:
  enabled: true
  message: '&7You defeated &#00FF07%victim% &7with &#00FF07❤ %attacker_health%&7!'

death-action-bar:
  enabled: true
  message: '&7You were defeated by &#FF0000%attacker% &7with &#FF0000❤ %attacker_health%&7!'

# ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
# Death Messages
# ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

# Placeholders:
# %attacker% - Returns the attacker name.
# %victim% - Returns the victim name.
# %attacker_health% - Returns the attacker health.

death_messages:
  custom:
    - '&#FF0000%victim%&7 has been killed by &#FF0000%attacker% &8(&7❤ %attacker_health%&8)'
    - '&#00FFFF%victim%&7 has been killed by &#00FFFF%attacker% &8(&7❤ %attacker_health%&8)'

# You can add more death messages here!
# Done configuring the plugin? Execute the command '/xyrisdm reload' in-game to apply the changes.
```

The `messages.yml` file allows you to customize the /toggledeathmessage command messages to suit your server's theme or style.

```yaml
# messages.yml
# Re-start the server to make this file take change!

deathMessagesEnabled: "&aDeath messages are now enabled!"
deathMessagesDisabled: "&cDeath messages are now disabled!"
```
