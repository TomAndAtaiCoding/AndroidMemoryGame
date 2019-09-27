package com.example.androidmemorygame;

import android.view.View;

import androidx.constraintlayout.widget.Placeholder;

import java.util.ArrayList;
import java.util.Collections;

class Round {

    /**
     * The game the rounnd is a part of
     */
    private Game game;

    Turn currentTurn;

    ArrayList<Player> livingPlayers = new ArrayList<>();

    /**
     * The number of pairs found so far in the round.
     */
    int solvedCards = 0;


    /**
     * Constructs a round
     * @param game the game the round is a part of
     * @param firstPlayer the player that the turn of should start the round
     */
    Round(Game game, Player firstPlayer) {
        this.game = game;
        this.currentTurn = new Turn(game.players[firstPlayer.getNumber() - 1], this);

        Collections.addAll(livingPlayers, game.players);
    }



    /**
     * Advances the {@link #currentTurn the current turn} to the next one.
     */
    void advanceTurn() {
        currentTurn = currentTurn.nextTurn();
    }

    /**
     * Ends the round, calcuklates winners, show popups and advances to the next one.
     */
    void end() {

        //TODO add end round popups, winning, ect.

        game.currentRound = nextRound();
        game.setBoard();
    }

    /**
     * <ul>
     * @return
     *  <ul>
     *      <li><b>True</b> if all pairs have been solved</li>
     *      <li><b>False</b> if there are still pairs to solve.</li>
     *  </ul>
     * </ul>
     */
    boolean finished() {
        return solvedCards >= 10; //All pairs solved;
    }

    /**
     * Constructs the next round.
     */
    Round nextRound() {
        return new Round(game, game.players[currentTurn.getPlayer().getNumber()]);
    }

    void switchCard(View view) {
        currentTurn.turnCard(view);
    }

    /**
     * Checks for the player or players with the highest score and returns them as a list.
     */
    ArrayList<Player> getHighestScoring() {

        ArrayList<Player> winners = new ArrayList<>();
        int highestScore = 0;

        for (Player player : game.players) {
            int playerScore = player.getScore();

            if (playerScore == highestScore) {
                winners.add(player);
            }

            else if (playerScore > highestScore) {
                winners.clear();
                winners.add(player);
                highestScore = playerScore;
            }
        }

        return winners;
    }

    Game getGame() {
        return game;
    }

    /**
     * Show end popup.
     * @param winners - the plauers with the highest score.
     */
    void endPopUp(Player... winners) {

        if (winners.length > 1) {
            //TODO tie popup
        }

        else {
            //TODO winner popup
        }
    }
}
