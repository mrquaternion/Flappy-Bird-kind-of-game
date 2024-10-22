# UNO Reverse Flappy Enemy VS Hero with GUNS POWPOWPOW

## Overview

This project is a role-playing game (RPG) developed using JavaFX. Inspired by Flappy Ghost, it reverses traditional roles: the player controls an enemy character who must collect coins and avoid or defeat heroes.

## Game Mechanics

- **Player Control**: The enemy character moves automatically to the right. The player can make the character "jump" by pressing the spacebar.
- **Objective**: Collect as many coins as possible while avoiding or defeating heroes.
- **Difficulty Increase**: For each coin collected, the enemy's speed and gravity increase.

## Heroes

- **Melee Hero**: Contact results in instant death. Defeating this hero gives 5 gold coins.
- **Stealth Hero**: Contact results in a loss of 10 gold coins. Defeating this hero gives 8 gold coins. Moves vertically in a sinusoidal pattern.
- **Tank Hero**: Contact halves the enemy's health. Defeating this hero gives 7 gold coins. Teleports randomly every 0.5 seconds.

New heroes appear every 3 seconds outside the game window.

## Coins

- Coins appear randomly from the right edge and move with the background.
- Collecting a coin increases the displayed gold count.

## Enemy

- Initial speed: 120 pixels/second.
- Speed increases by 10 pixels/second per coin collected.
- Gravity starts at 500 pixels/second² and increases by 15 pixels/second² per coin.
- Jumping changes vertical speed to 300 pixels/second upwards.
- Vertical speed is capped at 300 pixels/second in either direction.
- The enemy bounces off screen edges.

## Shooting Mechanic

- The enemy has unlimited bullets and can shoot by pressing 'E'.
- Bullets kill heroes if they intersect with the enemy's vertical position.
- Shooting has a cooldown of 1 second.

## Background and Window

- The game window is centered on the enemy, with a scrolling background.
- Window dimensions: 640x440 pixels (640x400 for gameplay, 40 for UI).
- UI displays score and health percentage, with a pause button included.

![Game preview](https://ibb.co/BKvQ48h)
