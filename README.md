# Auto-Hide Hotbar

[![Build](https://github.com/peterservices/AutoHideHotbar/actions/workflows/build.yml/badge.svg)](https://github.com/peterservices/AutoHideHotbar/actions/workflows/build.yml)
[![Modrinth](https://img.shields.io/badge/dynamic/json?labelColor=black&color=grey&label=&query=title&url=https://api.modrinth.com/v2/project/B49AL3Br&style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAMAAABEpIrGAAAAIGNIUk0AAHomAACAhAAA+gAAAIDoAAB1MAAA6mAAADqYAAAXcJy6UTwAAAJPUExURQAAABvZahWnUha1WAYzGQlHIxvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZahvZav///9ScwmYAAADDdFJOUwAAAAAAAA8zW3uOYwIBK3rB6Pn+ml18KiGL5HEDquOIH07R/UzKz2zu+uLHIibtafWkVCMNBRqg7/RQuT8EQbvT+5ETDBSU/NAgCSdZlcQKii7mtxJY5fF/7D1SRkB+EcWh4UilOOtPMdTCR1PqN969vmGDCw7G4DSSsAcGHrSPr3bds5CEwDKoXumcZdwcG4KmjayX32A79pjOqRjIFoXynVYVgPi6qxDnL78p2obVJFquNbGZCPdyvHPZ1yhuh8s+iRzcsrEAAAABYktHRMQUDBvhAAAAB3RJTUUH5wQXDwgZWDUtiQAAAqRJREFUOMttU/k7lFEU/k4L4hsxtNAnhRgiJZOiSfbI2oJpmRFCi2kV0aaNVLTIEpVISmnf97r/WO+534yZnqfzw73vOe+559xz7rmKIoVYvLx95vj6qUSqVBW3sGrwnxsQaAwSwcDz5i9g0wxPfmFIqJCyiEgLE4vDl7iD8PGlETotIqOQahlAdJTLA5sWY5JsbNzysHiihEBWViRqRDOlgyFmJVtMq5JWJ5vhvyZlLevrEmUILKl8PihtvYWcYt6QLmNwFjjEb4SSkZnlZLO5yJzcPBg35bODwZfPZxYwWbC5sKjYzwBUkssxSg1wKNsCtHUb89vTy3GwopBxDmcJ9YdDJYB1B9t2WmUtcbtkqt18Mxsp9irsezS2VEu+uka/mqUWSrFd2VsnhLEetoZ9TFv3HwCexdUdjBWi0aH4YTuEvtoPM3/EO1nnuTpEjD2qHIM54DhR0wmA5hZySzIntyknsbZyi8IB0tp07tRpBDKfgeWsdDjHDlFGRIjnl3O0t573gcMFUBeVS1gvlxB1dOZxv0pIu9IF01UDZRdjr1SuZQhxvbvnRrmsMePmrdsrGPRaKP8OLtmn9KM7A4N3xb8yNExUb5RlZt2btpZbXajzPoYkBaDKrtADp3HkYdloo56ndgx37UcHxSP0tIWBGH9cg1nx6TJFTNhS7eCfcA1PJ+GgDbLDs2GuX3V05Ohj0xYSBGumyq/yfApw4EWTRxPJqxQvIF6+ks9Gr0d4nN+8Nbhoi+NdJEwVSc6hVNvHOUvg+5qeDxoP3GQz6x8/qXLuZ6Op7SPy+gNTnydSELabk1Z8Mbs/hlr4dbobfUgxJMS3UdXza9H33jqnA3/OH41FCZ7/l7HW8vOXiWc9GvPf06D953/T2O/EP8HBNtcH0Zm/lqFNUgTAex4AAAAldEVYdGRhdGU6Y3JlYXRlADIwMjMtMDQtMjNUMTU6MDg6MjQrMDA6MDAE5dOaAAAAJXRFWHRkYXRlOm1vZGlmeQAyMDIzLTA0LTIzVDE1OjA4OjI0KzAwOjAwdbhrJgAAACh0RVh0ZGF0ZTp0aW1lc3RhbXAAMjAyMy0wNC0yM1QxNTowODoyNSswMDowMITaQU0AAAAASUVORK5CYII=)](https://modrinth.com/mod/autohidehotbar)
[![CurseForge](https://cf.way2muchnoise.eu/title/auto-hide-hotbar.svg)](https://www.curseforge.com/minecraft/mc-mods/auto-hide-hotbar)

Automatically dims or hides Minecraft's HUD when not in use to protect OLED displays from burn-in and provide a more minimal experience.

> [!NOTE]
> This project is a fork of Alphayt's mod [OLED Saver](https://modrinth.com/mod/oled-saver) with extra features, support for NeoForge, and support for Minecraft 26.1+.

### **Features**

* Supports both **Fabric** and **NeoForge**
* HUD elements are put in an inactive state when they haven't changed in a configurable amount of time
  * Inactive elements are automatically hidden, or optionally, can be dimmed to a configurable opacity instead
* Supports hiding/dimming the hotbar, health bar, hunger bar, armor bar, XP bar, and effect indicators
* Mount jump/dash bar only shows when powering up
* Hide held item tooltips (Off by default)
* Hotbar stats (health, hunger, etc.) always show while in the inventory
* Highly configurable
  * Configure if UI elements should stay visible, decrease in opacity, or hide completely when inactive
  * Configure if held item tooltips should be hidden
  * Configure if stats should show using a custom text overlay while in the inventory
  * Configure timings for how long UI elements are considered active
  * Configure the percentage of health or hunger that should activate their respective HUD elements
  * Configure if health or hunger should **only** show when below the specified percentage, and not when it changes
  * GUI configuration via MidnightLib (bundled with mod)

### **Download**
Available on [Modrinth](https://modrinth.com/project/autohidehotbar), [CurseForge](https://www.curseforge.com/minecraft/mc-mods/auto-hide-hotbar), and [GitHub Releases](https://github.com/peterservices/AutoHideHotbar/releases).

Or you can download the latest [development build](https://github.com/peterservices/AutoHideHotbar/actions/workflows/build.yml).

### **Fabric Dependencies**
* [Architectury API](https://modrinth.com/mod/architectury-api)
* [Fabric API](https://modrinth.com/mod/fabric-api)

### **NeoForge Dependencies**
* [Architectury API](https://modrinth.com/mod/architectury-api)

---

YouTube demo of the mod:

[![Video demonstration of the mod](https://img.youtube.com/vi/LG09eT5JVJQ/0.jpg)](https://www.youtube.com/watch?v=LG09eT5JVJQ)
