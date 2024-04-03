package com.sds;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    @Test
    @DisplayName("플레이어는 모든 프레임의 각 투구에서 0점을 기록한다.")
    void gutterGame() {
        //Given : 게임을 진행하는 동안
        BowlingGame game = new BowlingGame();

        //When : 모든 투구에서 핀을 하나도 쓰러뜨리지 못하면
        rollZero(game, 20);

        //Then : 점수는 0점이 된다.
        assertEquals(0, game.score());
    }

    @Test
    @DisplayName("플레이어는 첫 프레임에서의 첫 투구에서 1점을 기록하고 나머지는 모두 0점을 기록한다.")
    void onePin() {
        //Given: 게임을 진행하는 동안
        BowlingGame game = new BowlingGame();

        //When: 첫 투구에서 1점을 기록하고, 나머지는 모두 0점을 기록하면
        game.roll(1);
        rollZero(game, 19);

        //Then: 점수는 1점이 된다.
        assertEquals(1, game.score());
    }

    @Test
    @DisplayName("플레이어는 각 투구에서 최소 0개에서 최대 10개까지의 핀을 쓰러뜨릴 수 있다. (0보다 적을시)")
    void accurateBowlingUnder0() {
        //Given: 게임을 진행하는 동안
        BowlingGame game = new BowlingGame();

        //Then: 런타임(IllegalArgumentException)가 발생한다.
        assertThrows(IllegalArgumentException.class, () -> {
            //When: 투구시 0개보다 적은 핀을 쓰러뜨리면
            game.roll(-1);
        });
    }

    @Test
    @DisplayName("플레이어는 각 투구에서 최소 0개에서 최대 10개까지의 핀을 쓰러뜨릴 수 있다. (10보다 클시)")
    void accurateBowlingOver10() {
        //Given: 게임을 진행하는 동안
        BowlingGame game = new BowlingGame();

        //Then: 런타임(IllegalArgumentException)가 발생한다.
        assertThrows(IllegalArgumentException.class, () -> {
            //When: 투구시 0개보다 적은 핀을 쓰러뜨리면
            game.roll(11);
        });
    }

    @Test
    @DisplayName("플레이어는 첫 프레임에서 스페어가 발생한다.")
    void spare() {
        //Given: 게임을 진행하는 동안 첫번째 투구에서 7개의 핀을 쓰러트리고
        BowlingGame game = new BowlingGame();
        game.roll(7);

        //When: 두번째 투구에서 나머지 3개의 핀을 쓰러뜨려 스페어를 한다면,
        game.roll(3);

        //Then: 다음 투구에서 쓰러뜨린 핀의 갯수만큼 보너스 점수를 받는다.
        game.roll(1);
        rollZero(game, 17);
        assertEquals(12, game.score());
    }

    @Test
    @DisplayName("플레이어는 첫 프레임에서 스트라이크를 기록하고 다음과 같은 점수를 얻는다")
    void strike() {
        //Given: 게임을 진행하는 동안
        BowlingGame game = new BowlingGame();

        //When: 첫 프레임에서 스트라이크를 기록한다
        //두번째 프레임의 투구들은 각 1점을 기록한다.
        //나머지 프레임은 0점을 기록한다.
        game.roll(10);
        game.roll(1);
        game.roll(1);
        rollZero(game,16);

        //Then: 점수는 14점이 된다.
        assertEquals(14, game.score());
    }

    private static void rollZero(BowlingGame game, int count) {
        for(int i=0; i<count; ++i) {
            game.roll(0);
        }
    }
}