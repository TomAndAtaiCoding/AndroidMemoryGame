package com.example.androidmemorygame;

import android.widget.Button;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A class for the game cards, containing the values that need to be paired.
 */
class Card {
    /**
     * The card's value to be paired.
     */
    private int value;

    /**
     * The card's corresponding button in the {@link Game Game activity}.
     */
    private Button button;

    /**
     * The game the card is in.
     */
    private Game game;

    /**
     * A list of all the value pairs, used to construct and reconstruct {@link #valuesToSet}.
     */
    //TODO Make the pairs more dynamic to allow difficulty adjustment.
    static private final List<Integer> possibleValues = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    /**
     * The list of all values that still don't have a matching card. Initialized as
     * the list of all values in the game - combination of twice the
     * {@link #possibleValues list of possible values}.
     */
    static private final ArrayList<Integer> valuesToSet =
            (ArrayList<Integer>)Stream.concat(possibleValues.stream(), possibleValues.stream()).
                    collect(Collectors.toList());

    /**
     * The list of all cards in the game. Every card constructed will be added here.
     */
    static private ArrayList<Card> deck = new ArrayList<>();

    /**
     * A Random Number Generator, to randomly assign the values.
     */
    static private final Random rng = new Random();

    /**
     * Constructs a card
     * @param button the button that corresponds to the card
     * @param game the game the card is a part of
     */
    Card(Button button, Game game)
    {
        this.button = button; //Set button by constructor.
        this.game = game; //Set the game the card is a part of

        assignVal(); //Assign it the value for the first round

        deck.add(this); //Add it to the card list

        // Sets the method executed when the card's button is clicked: turn the card and do the
        // calculations in the context of the gam'es current round and turn.
        button.setOnClickListener(view -> game.currentRound.currentTurn.turnCard(view));
    }

    /**
     * "turns" the card up and reveals its value.
     * "turns" it  back down (concealing it) after a wait according to the game's mode.
     * @return the card switched (this object)
     */
    Card switchUp()
    {
        button.setText(value);


        /*The sleep method is used to allow the player to see
        The card for a few seconds before it disappears. However, it can throw the checked
        InterruptedException which must be handled. This should only happen shall the thread
        run for too long and in parallel to other processes - as such, it should not be thrown
        here. However, for good practice and to allow usage in overridden methods,
        The code is still able to catch the exception and record it.*/
        try {
            Thread.sleep(game.getDifficulty().wait);
        }

        catch (InterruptedException e) {
            System.out.println("ERROR: view-time interrupted!");
        }

        button.setText("");
        return this;
    }

    /**
     * Reassigns the values of all cards in the game.
     */
    static void shuffleDeck()
    {
        reset();

        for (Card card : deck) {
            card.assignVal();
        }

    }

    /**
     * Resets the values of all cards in the game to 0 and their text to an empty string,
     * as well as resetting {@link #valuesToSet}.
     */
    static private void reset() {
        valuesToSet.clear();
        valuesToSet.addAll(possibleValues);
        valuesToSet.addAll(possibleValues);

        for (int i = 0; i < deck.size(); i++) {
            deck.get(i).value = 0;
            deck.get(i).button.setText("");
        }
    }

    /**
     * Assigns a value to the card according to {@link #valuesToSet the list of values to set}.
     */
    private void assignVal() {
        int valPosition = rng.nextInt(valuesToSet.size()); //Assign a random value from value list
        valuesToSet.remove(valPosition);
        //And remove that value such that we don't have too many cards using it.
        this.value = valuesToSet.get(valPosition);
    }

    /**
     * Disables the card's button.
     */
    void disable() {
        button.setEnabled(false);
    }

    /**
     * Checks if the card matches an input card.
     * @param card the card to check this card against.
     * @return
     * <i>True</i> if the cards share the same value<br/>
     * <i>False</i> otherwise.<br/>
     */
    boolean isPairOf(Card card) {
        return this.value == card.value;
    }
}
