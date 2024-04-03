package com.sds;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private final List<Integer> rolls;

    BowlingGame() {
        rolls = new ArrayList<>();
    }

    public void roll(Integer pin) {
        if(pin < 0 || pin > 10)
            throw new IllegalArgumentException();

        rolls.add(pin);
    }

    public Integer score() {
        List<Frame> frames = new ArrayList<>();
        for(int i=0; i<rolls.size();i++) {
            if(rolls.get(i) == 10) {
                frames.add(new Frame(10, 0));
            } else {
                Frame frame = new Frame(rolls.get(i), rolls.get(i+1));
                frames.add(frame);
                i++;
            }
        }
        Integer baseScore = frames.stream().mapToInt(Frame::baseScore).sum();
        Integer bonusScore = 0;
        for(int i=0; i<frames.size(); i++){
            Frame frame = frames.get(i);
            if(frame.isSpare()) {
                bonusScore += frames.get(i+1).getRoll1();
            }
            if(frame.isStrike()) {
                bonusScore += frames.get(i+1).getRoll1();
                bonusScore += frames.get(i+1).getRoll2();
            }
        }

        return baseScore +bonusScore;
    }
}