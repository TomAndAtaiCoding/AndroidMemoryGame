package com.example.androidmemorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A game activity, visually composed of score boxes, labels and cards (buttons). <p><p>
 *
 * The activity represents a <b><i>Memory Game</i></b>: <p>
 * The board has 20 cards, each containing a
 * value. at each of their turns, the players pick two cards. A correct pair awards a point.
 * An incorrect pair costs health. When health reaches 0, the player is out of the round.
 * When one player remains or when all pairs have been found, the round ends, and the player who
 * got more pairs (or the only one who remained alive) receives an additional 10 points and 10 health.
 */
public class Game extends AppCompatActivity {
    //TODO add health UI

    /**
     * The number of players in the game. <p>
     *
     *  <ul><i>NOTE: Currently constant but most of the code <i>is</i> written to be generic,
     *  such that changing backend to three players would require mostly just changing this
     *  variable.
     */
    int PLAYER_NUM = 2; //In fact: TODO - make number of players dynamic.

    //----------UI---------
    /**
     * The labels on the player's scores, displaying their name.
     */
    TextView[] playerLabels = new TextView[PLAYER_NUM];

    /**
     * The Text Boxes containing the players' score.
     */
    TextView[] scoreBoxes = new TextView[PLAYER_NUM];


    /**
     * The list of all cards in the game.
     */
    ArrayList<Card> deck = new ArrayList<>();




    //---------Game Variables---------

    /**
     * The game's {@link Difficulty} (see below)
     */
    Difficulty difficulty;

    /**
     * An array of the game's {@link Player players}
     */
    Player[] players = new Player[PLAYER_NUM];

    /**
     * The current {@link Round} all button clicks are passed to it, and it handles them with
     * accordance to the players, the scores, and the rest of the game.
     */
    Round currentRound;


    /**
     * The activities' initialization function. Sets the game variables according to the previous
     * screen and the nature of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Start Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_game_table);
        //Get names from previous screen
        String p1Name = getIntent().getStringExtra("p1Name");
        String p2Name = getIntent().getStringExtra("p2Name");

        //Get labels and scoreBoxes
        playerLabels[0] = findViewById(R.id.);
//        playerLabels[1] = findViewById(R.id.player2Label);
//
//        scoreBoxes[0] = findViewById(R.id.scoreBox1);
//        scoreBoxes[1] = findViewById(R.id.scoreBox2);

        //Reset cards' values
        Card.reset();


        TableLayout layout = (TableLayout)R.id.
        for (int i = 0; i < difficulty.size; i++) {
            TableRow newRow = new TableRow(this);
            newRow.setLayoutParams(
                    new TableLayout.LayoutParams(
                            TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT));

            for (int j = 0; j < difficulty.size; j++) {
                findViewById(R.id.scoreSummaryBox1);
            }
        }

        //Construct player.
        for (int i = 0; i < PLAYER_NUM; i++) {
            String name = getIntent().getStringExtra("p" + i + "Name");
            players[i] = new Player(name, i + 1, this, playerLabels[i], scoreBoxes[i]);
        }

        //Set current turn
        currentRound = new Round(this, players[0]);
    }

    Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the board - enables all buttons and shuffles the pairs.
     */
    void setBoard() {
        Card.shuffleDeck();
        //TODO Add pop-ups and animations.
    }

    /**
     * End game pop-ups and calculations, and return to welcome screen.
     */
    void end() {
        //TODO game end logistics
    }

    /**
     * Animation to run whenever a correct pair is picked.
     */
    void winTurnAnimation() {
        //TODO winTurnAnimation()
    }

    /**
     * Animation to run whenever an incorrect pair is picked.
     */
    void loseTurnAnimation() {
        //TODO loseTurnAnimation()
    }

    /**
     * Animation to run for when a round ends.
     */
    void endRoundAnimation() {
        //TODO endRoundAnimation()
    }

    /**
     * The game difficulty, changing determining and time to see the cards.
     */
    enum Difficulty
    {
        EASY(3500, 30, 3),
        NORMAL(2000, 20, 4),
        HARD(900, 10, 5);

        /**
         * The time, in milliseconds, hat the player can see the card's values.
         */
        long wait;

        /**
         * The amount of health each player starts with.
         */
        int health;

        /**
         * The size of the board - size x size cards.
         */
        int size;


        Difficulty(long wait, int health, int size)
        {
            this.wait = wait;
            this.health = health;
            this.size = size;
        }
    }
}