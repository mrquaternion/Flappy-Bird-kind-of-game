# Flappy Ghost inspiration

## Overview

This JavaFX RPG flips roles: you play as an enemy collecting coins and avoiding or defeating heroes.

![Game preview](https://i.ibb.co/3YVjftx/IMG-1317.jpg)

## Game Mechanics

- **Movement**: Automatic right movement; jump with spacebar.
- **Objective**: Collect coins, avoid heroes.
- **Difficulty**: Increases with each coin collected.

## Heroes

- **Melee**: Instant death on contact; 5 coins if defeated.
- **Stealth**: Lose 10 coins on contact; 8 coins if defeated.
- **Tank**: Halves health on contact; 7 coins if defeated.

## Coins

- Appear randomly, increase gold count when collected.

## Enemy

- Initial speed: 120 pixels/second, increases with coins.
- Gravity and jump mechanics affect vertical movement.
- Bounces off screen edges.

## Shooting

- Unlimited bullets, shoot with 'E'.
- 1-second cooldown between shots.
  
## Background and Window

- The game window is centered on the enemy, with a scrolling background.
- UI displays score and health percentage, with a pause button included.

