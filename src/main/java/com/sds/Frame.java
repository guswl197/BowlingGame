package com.sds;

public class Frame {

    private final Integer roll1;
    private final Integer roll2;

    Frame(Integer roll1, Integer roll2){
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    public boolean isSpare() {
        return this.roll1 + this.roll2 == 10 && this.roll2 != 0;
    }

    public boolean isStrike() {
        return this.roll1 == 10 && this.roll2 == 0;
    }

    public Integer baseScore() {
        return this.roll1 + this.roll2;
    }

    public Integer getRoll1() {
        return roll1;
    }

    public Integer getRoll2() {
        return roll2;
    }

}