package com.example.androidmemorygame;

import android.view.View;

/**
 * Represents the current turn, and handles the player's actions in that context.
 */
class Turn {

    /**
     * The currently playing player.
     */
    private Player player;

    /**
     * The card first picked.
     */
    private Card firstCard;

    /**
     * The current round.
     */
    private Round round;

    /**
     * Whether or not a card was already turned up
     */
    private boolean firstSwitch;


    /**
     * Constructs a {@link Turn turn}.
     * @param player the player the turn is of.
     * @param round the round the turn is a part of.
     */
    Turn(Player player, Round round) {
        this.player = player;
        this.round = round;
        this.firstSwitch = true;
    }

    /**
     * The method executed when a card is clicked - turns it up and handles it in the context
     * of the current turn (check against first card if second, change player's health/score, ect.)
     * @param view the button clicked.
     */
    void turnCard(View view)  {
        String[] coordinates = view.getTag().toString().split(","); //Get the card coordinates from its tags.
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);

        Card card = round.getGame().cards[x][y];

        if (firstSwitch)
            firstSwitch(card);

        else
            secondSwitch(card);
    }


    /**
     * The switch to happen at the first card turn of the turn - simply show the number.
     * @param card the card to swutch up.
     */
    private void firstSwitch(Card card) {
        firstCard = card.switchUp();
    }

    /**
     * The switch to happen at the second turn up - checking it against the first card and
     * reacting accordingly, as well as advancing to the next turn.
     * @param card the card to turn up.
     */
    private void secondSwitch(Card card) {
        card.switchUp();

        if (card.isPairOf(firstCard)) {
            card.disable();
            firstCard.disable();
            round.getGame().winTurnAnimation();
            player.winTurn();
            round.solvedCards++;

            if (round.solvedCards == 10)
            {
                //TODO advance tound
            }
        }

        else {
            round.getGame().loseTurnAnimation();
            player.loseTurn();
        }

        round.advanceTurn();
    }

    /**
     * Constructs the next turn.
     */
    Turn nextTurn() {
        return new Turn(round.getGame().players[player.getNumber() % round.getGame().PLAYER_NUM], round);
        //Notice that there is no need to increment the player's number, since their indexing is 1-based.
    }

    Player getPlayer() {
        return player;
    }

}
