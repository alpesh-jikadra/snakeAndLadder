package com.game.test.testSuit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.test.game.snakeLadder.SnakeAndLadderTest;
import com.test.game.snakeLadder.board.BoardTest;
import com.test.game.snakeLadder.die.DieTest;
import com.test.game.snakeLadder.ladder.LadderTest;
import com.test.game.snakeLadder.player.PlayerListTest;
import com.test.game.snakeLadder.snake.SnakeTest;

@RunWith(Suite.class)
@SuiteClasses({
BoardTest.class,
DieTest.class,
LadderTest.class,
SnakeTest.class,
PlayerListTest.class,
SnakeAndLadderTest.class
})
public class SnakeAndLadderAllTestSuite {

}
