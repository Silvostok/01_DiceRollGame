package com.example.miguelangelrubiocaballero.a01_dicerollgame;

public class Dice {

    //number of dice throws
    private int mThrows;

    //constructor
    public Dice(int mThrows) {
        this.mThrows = mThrows;
    }

    //getter & setter
    public int getmThrows() {
        return mThrows;
    }

    public void setmThrows(int mThrows) {
        this.mThrows = mThrows;
    }

    @Override
    public String toString() {
        return "" + mThrows;
    }



}
