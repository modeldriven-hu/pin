# Pin plugin

This plugin provides support for pins in activity diagrams, it allows easier setting pin multiplicity
and cloning pin names and types as well.

# Screenshots

![image](https://user-images.githubusercontent.com/8182138/200180344-c5302658-eb1e-425b-9cae-9a724f428025.png)

# Functionality

# Requirements

- Cameo/MagicDraw latest
- Java JDK 11
- Maven

# Usage

## Check out the repository

`git clone https://github.com/modeldriven-hu/pin.git`

## Configure environment variable

Configure Cameo/MagicDraw root directory according to local setup, for example:

`set CAMEO_ROOT=C:/home/Tools/Cameo`

or when using PowerShell:

`$env:CAMEO_ROOT = "C:/Home/Tools/Cameo"`

## Build project

`mvn package`

## Extract into Cameo plugins folder

Extract `target/hu.modeldriven.cameo.pin.zip` into `CAMEO_ROOT/plugins`

## Restart

Restart CAMEO
