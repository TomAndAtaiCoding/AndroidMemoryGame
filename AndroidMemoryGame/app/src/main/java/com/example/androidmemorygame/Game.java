package com.example.androidmemorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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
     * An array containing all the UI buttons which correspond to the game cards.
     *
     * <ul><i>NOTE: this array exists solely because it was easier to type instead of outright
     * creating {@link Card} objects. However there probably is a way to algoritmically iterate through
     * the buttons. Might require converting to {@link android.widget.TableLayout}</i></ul>
     */
    Button[][] cardButtons = //TODO - Change to table layout and create the cards with looping instead.
            {{findViewById(R.id.cardA0), findViewById(R.id.cardB0), findViewById(R.id.cardC0), findViewById(R.id.cardD0)},
             {findViewById(R.id.cardA1), findViewById(R.id.cardB1), findViewById(R.id.cardC1), findViewById(R.id.cardD1)},
             {findViewById(R.id.cardA2), findViewById(R.id.cardB2), findViewById(R.id.cardC2), findViewById(R.id.cardD2)},
             {findViewById(R.id.cardA3), findViewById(R.id.cardB3), findViewById(R.id.cardC3), findViewById(R.id.cardD3)},
             {findViewById(R.id.cardA4), findViewById(R.id.cardB4), findViewById(R.id.cardC4), findViewById(R.id.cardD4)}};

    /**
     * The array of all cards in the game. Two dimensional in order to represent the board.
     */
    Card[][] cards = new Card[5][4];



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
        setContentView(R.layout.activity_game);

        //Get names from previous screen
        String p1Name = getIntent().getStringExtra("p1Name");
        String p2Name = getIntent().getStringExtra("p2Name");

        //Get labels and scoreBoxes
        playerLabels[0] = findViewById(R.id.player1Label);
        playerLabels[1] = findViewById(R.id.player2Label);

        scoreBoxes[0] = findViewById(R.id.scoreBox1);
        scoreBoxes[1] = findViewById(R.id.scoreBox2);

        //Reset cards' values
        Card.reset();


        //Construct player.
        for (int i = 0; i < PLAYER_NUM; i++) {
                String name = getIntent().getStringExtra("p" + i + "Name");
                players[i] = new Player(name, i + 1, this, playerLabels[i], scoreBoxes[i]);
        }

        //Construct the card array
        for (int i = 0; i < cardButtons.length; i++) {
            for (int j = 0; j < cardButtons[0].length; j++) {
                cards[i][j] = new Card(cardButtons[i][j], this);
            }
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
        EASY(3500, 30), //3.5 seconds to see cards
        NORMAL(2000, 20), //2 s
        HARD(900, 10); //0.9 s

        long wait;
        int health;

        Difficulty(long wait, int health)
        {
            this.wait = wait;
            this.health = health;
        }
    }

    /**
     * @return The sum of all the players' scores.
     */
    int sumOfScores() {
        int sum = 0;

        for (Player player : players) {
            sum += player.getScore();
        }

        return sum;
    }
}