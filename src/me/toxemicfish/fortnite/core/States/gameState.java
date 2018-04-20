package me.toxemicfish.fortnite.core.States;

public enum gameState {

    WAITING,  // The game is waiting for players to join.
    STARTING, // The game is starting let people join but the game is about to start.
    RUNNING, // The game is running do not let players join.
    RESTRICTED; // The game cannot be started.

}
