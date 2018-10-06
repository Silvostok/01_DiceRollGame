package com.example.miguelangelrubiocaballero.a01_dicerollgame;

public class Dice {


    private int mThrows;

    public Dice(int mThrows) {
        this.mThrows = mThrows;
    }

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
