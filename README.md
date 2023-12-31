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
# Available Placeholders

# %victim%
# %attacker_health%
# %attacker%
death_messages:
  custom:
    - '&c%victim% was slain by %attacker% with %attacker_health% health remaining!'
    - '&6%attacker% mercilessly eliminated %victim% while having %attacker_health% health!'
    - '&e%victim% got toasted by %attacker%!'
    - '&b%attacker% sent %victim% to the nether!'
```

The `messages.yml` file allows you to customize the /toggledeathmessage command messages to suit your server's theme or style.

```yaml
# messages.yml
# Re-start the server to make this file take change!

deathMessagesEnabled: "&aDeath messages are now enabled!"
deathMessagesDisabled: "&cDeath messages are now disabled!"
```
