package com.example.androidmemorygame;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * A a class representing a player in the {@link Game game}.
 */
class Player {

    /**
     * The player's score
     */
    private int score;

    /**
     * The player's health. When this reaches 0, they are out of the current {@link Round}.
     */
    private int health;

    /**
     * His number - for easy cycling through players when advancing to the next turn.
     */
    private int number;

    /**
     * The box displaying the player's score.
     */
    private TextView scoreBox;

    /**
     * The box displaying the player's health
     */
    private TextView healthBox; //TODO Add health boxes to the activities.

    /**
     * The game the player is a part of.
     */
    private Game game;

    /**
     * Constructs a player.
     * @param name The player's name
     * @param number Its number indexing can be assigned randomly as ling as it starts at 0 and
     *               rises by 1.
     * @param game The game the player is a part of.
     * @param label The label that should display the player's name.
     * @param scoreBox The {@link TextView} that should display the player's score.
     */
    Player(String name, int number, Game game, TextView label, TextView scoreBox) {
        this.game = game;
        this.scoreBox = scoreBox;
        this.number = number;

        this.health = 20;
        this.score = 0;

        label.setText(name);
        scoreBox.setText(this.score);
    }


    /**
     * Win turn and change score accordingly.
     */
    void winTurn() {
        score += 1;
        update();
    }

    /**
     * Lose turn and change health accordingly.
     */
    void loseTurn() {
        health -= 1;
        game.loseTurnAnimation();

        if (!alive()) {
           game.currentRound.livingPlayers.remove(this);
        }

        update();
    }

    /**
     * Win round and change score & health accordingly.
     */
    void winRound() {
        score += 10;
        health += 10;
        update();
    }

    int getScore() {
        return score;
    }

    int getNumber() {
        return number;
    }

    /**
     * @return if the player is alive.
     */
    private boolean alive() {
        return health > 0;
    }

    /**
     * Updates the player's score and health boxes.
     */
    private void update() {
        scoreBox.setText(score);
        healthBox.setText(health);
    }




}
