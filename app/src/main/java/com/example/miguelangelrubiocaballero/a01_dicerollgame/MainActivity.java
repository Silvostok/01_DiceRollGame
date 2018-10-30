package com.example.miguelangelrubiocaballero.a01_dicerollgame;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    //find and automatically cast the corresponding view in the layout
    @BindView(R.id.score)
    TextView mScore;
    @BindView(R.id.highscore)
    TextView mHighScore;
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.higherImageView)
    ImageView mHigherGuess;
    @BindView(R.id.lowImageView)
    ImageView mLowerGuess;
    @BindView(R.id.diceImageView)
    ImageView mDice;
    @BindView(R.id.mainConstraintLayout)
    ConstraintLayout mView;

    // Array to store all images names from drawable
    int[] mImagesDices = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3
            , R.drawable.d4, R.drawable.d5, R.drawable.d6};

    // List to store wins scores
    List<Integer> winsArray = new ArrayList<>();

    // counter for win and high score labels
    int winCounter = 0;
    int highscoreCounter = 0;

    // List of dices
    private List<Dice> mDices;

    // Array of Adapter
    private ArrayAdapter mAdapter;

    // Random class
    private Random mRandom = new Random();
    int beginNumber;
    private int wins = 0;
    private int highscore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Binds all views on this activity
        ButterKnife.bind(this);
        mDices = new ArrayList<>();
        beginNumber = setRandom();

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDices);
        mListView.setAdapter(mAdapter);
    }

    // set dice image, throw random dice, if random < next, dis paly win and update win & high score counter.
    //otherway display lose and update win & high score counter
    @OnClick(R.id.lowImageView)
    public void roleDiceLower() {

        int N = mImagesDices.length;
        int next = mRandom.nextInt(N);
        mDice.setImageResource(mImagesDices[next]);

        int random = setRandom();
        Dice newThrow = new Dice(random);

        mDices.add(newThrow);
        mAdapter.notifyDataSetChanged();

        if (random < next) {
            Snackbar.make(mView, "Win", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            winCounter = ++wins;
            mScore.setText("Score: " + winCounter);
            highscoreCounter = ++highscore;

        } else {
            Snackbar.make(mView, "Lose", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            winCounter = 0;
            wins = 0;
            mScore.setText("Score: " + winCounter);
            mHighScore.setText("HighScore: " + highscoreCounter);
            winsArray.add(highscoreCounter);
            highscoreChecker();
            highscore = 0;
            highscoreCounter = 0;
        }

    }

    // set dice image, throw random dice, if random < next, dis paly win and update win & high score counter.
    //otherway display lose and update win & high score counter
    @OnClick(R.id.higherImageView)
    public void roleDiceHigher() {

        int N = mImagesDices.length;
        int next = mRandom.nextInt(N);
        mDice.setImageResource(mImagesDices[next]);
        int random = setRandom();
        Dice newThrow = new Dice(random);

        mDices.add(newThrow);
        mAdapter.notifyDataSetChanged();
        if (next > random) {
            //Show a message to the user if the textfield is empty
            Snackbar.make(mView, "Win", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            winCounter = ++wins;
            mScore.setText("Score:  " + winCounter);
            highscoreCounter = ++highscore;

        } else {
            Snackbar.make(mView, "Lose", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            wins = 0;
            winCounter = 0;
            mScore.setText("Score: " + winCounter);
            mHighScore.setText("HighScore: " + highscoreCounter);
            winsArray.add(highscoreCounter);
            highscoreChecker();
            highscore = 0;
            highscoreCounter = 0;
        }

    }

    //calculate Random number
    public int setRandom() {
        int N = mImagesDices.length;
        int next = mRandom.nextInt(N);
        mDice.setImageResource(mImagesDices[next]);
        return next;
    }

    // check high score
    private void highscoreChecker() {
        if (winsArray.size() == 1) {
            mHighScore.setText("HighScore: " + winsArray.get(0));

        } else {
            mHighScore.setText("HighScore: " + Collections.max(winsArray));
        }
    }

}
