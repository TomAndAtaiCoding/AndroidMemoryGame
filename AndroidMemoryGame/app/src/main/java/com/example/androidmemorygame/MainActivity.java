package com.example.androidmemorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


/**
 * The welcome activity which opens on the app's launch, and is used for thew user to set the game's
 * parammeterds (player's names, round no., difficulty.
 */
public class MainActivity extends AppCompatActivity {
    EditText p1NameBox;
    EditText p2NameBox;
    //TODO add difficulty selector

    /**
     * The activity's initializer, getting the name boxes from the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p1NameBox = (EditText)findViewById(R.id.p1NameBox);
        p2NameBox = (EditText)findViewById(R.id.p2NameBox);
        setContentView(R.layout.activity_main);
    }


    /**
     * A method to start the {@link Game Game Activity} by the push of a button.
     * Passes the player's names to the game through an {@link Intent} and starts
     * the game's activity.
     * @param view - the button pressed.
     * */
    public void startGame(View view)
    {
        Intent startGame = new Intent(this, Game.class);
        startGame.putExtra("p1Name", p1NameBox.getText().toString());
        startGame.putExtra("p2Name", p2NameBox.getText().toString());
        startActivity(startGame);
    }
}
