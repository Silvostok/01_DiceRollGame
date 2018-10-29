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



    int[] mDrawableDices = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3
            , R.drawable.d4, R.drawable.d5, R.drawable.d6};
    List<Integer> winsArray = new ArrayList<>();
    int winCounter = 0;
    int higscoreCounter = 0;

    private List<Dice> mDices;
    private ArrayAdapter mAdapter;
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


        ButterKnife.bind(this);

        mDices = new ArrayList<>();
        beginNumber = setRandom();

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDices);

        mListView.setAdapter(mAdapter);

    }


    @OnClick(R.id.lowImageView)
    public void roleDiceLower() {

        int N = mDrawableDices.length;
        int next = mRandom.nextInt(N);
        mDice.setImageResource(mDrawableDices[next]);
        //Get the user text from the textfield
        int random = setRandom();
        Dice newThrow = new Dice(random);
        mDices.add(newThrow);
        mAdapter.notifyDataSetChanged();

        if (random < next) {
            Snackbar.make(mView, "Win", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            winCounter = ++wins;
            mScore.setText("Score:  " + winCounter);
            higscoreCounter = ++highscore;

        } else {
            Snackbar.make(mView, "Lose", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            winCounter = 0;
            wins = 0;
            mScore.setText("Score: " + winCounter);
            mHighScore.setText("HighScore: " + higscoreCounter);
            winsArray.add(higscoreCounter);
            highscoreChecker();
            highscore = 0;
            higscoreCounter = 0;
        }



    }
    @OnClick(R.id.higherImageView)
    public void roleDiceHigher() {


        int N = mDrawableDices.length;
        int next = mRandom.nextInt(N);
        mDice.setImageResource(mDrawableDices[next]);
        int random = setRandom();
        Dice newThrow = new Dice(random);


        mDices.add(newThrow);
        mAdapter.notifyDataSetChanged();
        if (next > random) {
            //Show a message to the user if the textfield is empty
            Snackbar.make(mView, "Win", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            winCounter = ++wins;

            mScore.setText("Score:  " + winCounter);
            higscoreCounter = ++highscore;
        } else {
            Snackbar.make(mView, "Lose", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            wins = 0;
            winCounter = 0;
            mScore.setText("Score: " + winCounter);
            mHighScore.setText("HighScore: " + higscoreCounter);
            winsArray.add(higscoreCounter);
            highscoreChecker();
            highscore = 0;
            higscoreCounter = 0;
        }




    }

    public int setRandom() {
        int N = mDrawableDices.length;
        int next = mRandom.nextInt(N);
        mDice.setImageResource(mDrawableDices[next]);
        return next;
    }



    private void highscoreChecker() {
        if (winsArray.size() == 1) {
            mHighScore.setText("HighScore: " + winsArray.get(0));

        } else {
            mHighScore.setText("HighScore: " + Collections.max(winsArray));
        }
    }

//-------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
