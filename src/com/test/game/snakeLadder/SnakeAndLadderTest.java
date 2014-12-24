package com.test.game.snakeLadder;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Scanner;

import junit.framework.Assert;

import org.junit.Test;

import com.game.play.SnakeAndLadder;

public class SnakeAndLadderTest {

	@Test
	public void testMovePostion() throws Exception {
		SnakeAndLadder instance = getInstance();
		Class[] types = new Class[] { Integer.class };
		boolean isMoved = changePlayerPosition(instance, 1);
		Assert.assertTrue("Change Player position fail ", isMoved);

		Method method = getMethod("move", types);
		isMoved = (Boolean) method.invoke(instance, 6);
		Assert.assertTrue("Move valid postion fail", isMoved);
	}

	@Test
	public void testWinGame() throws Exception {
		SnakeAndLadder instance = getInstance();
		Class[] types = new Class[] { Integer.class };

		Method method = getMethod("play", types);
		Boolean retVal = (Boolean) method.invoke(instance, new Object[] { 1 }); // Throw
																				// Die

		boolean isGameWon = false;

		while (!isGameWon) {
			isGameWon = (Boolean) method.invoke(instance, new Object[] { 1 });
		}
		Assert.assertTrue("Throw die to win game fail", isGameWon);
	}

	@Test
	public void testSnakeMove() throws Exception{
		SnakeAndLadder instance = getInstance();
		
		Map<Integer, Integer> snakes = instance.getSnakes().getSnakes();
		
		Integer snakeHead = snakes.keySet().iterator().next();
		
		Class[] types = new Class[] { };
		
		boolean isMoved = changePlayerPosition(instance, snakeHead);
		Assert.assertTrue("Change Player position at snake head fail", isMoved);

		Method method = getMethod("checkSnakeMove", types);
		isMoved = (Boolean) method.invoke(instance, null);
		
		Assert.assertTrue("Snake head to snake tail position move fail", isMoved);
	}
	
	@Test
	public void testLadderMove() throws Exception{
		SnakeAndLadder instance = getInstance();
		
		Map<Integer, Integer> ladders = instance.getLadders().getLadders();
		
		Integer snakeHead = ladders.keySet().iterator().next();
		
		Class[] types = new Class[] { };
		
		boolean isMoved = changePlayerPosition(instance, snakeHead);
		Assert.assertTrue("Change Player position at snake bottom fail", isMoved);

		Method method = getMethod("checkLadderMove", types);
		isMoved = (Boolean) method.invoke(instance, null);
		
		Assert.assertTrue("Snake head to snake top position move fail", isMoved);
	}
	
	@Test
	public void testIsWinner() throws Exception {
		SnakeAndLadder instance = getInstance();
		
		Class[] types = new Class[] {};
		
		boolean isMoved = changePlayerPosition(instance, instance.getBoard().getDestinationNumber());
		
		Assert.assertTrue("Test case fail for moving to winner position",isMoved);
		
		Method method = getMethod("isWinner", types);
		isMoved = (Boolean) method.invoke(instance, null);
		
		Assert.assertTrue("Test case fail for checking winner position",isMoved);
		
	}
	@Test
	public void testValidMove() throws Exception {
		SnakeAndLadder instance = getInstance();
		
		Class[] types = new Class[] {Integer.class};
		Method method = getMethod("isValidMove", types);
		Boolean isMoved = (Boolean) method.invoke(instance, 6);
		
		Assert.assertTrue("Valid position move fail",isMoved);
		
		isMoved = (Boolean) method.invoke(instance, 7);
		
		Assert.assertFalse("InValid position test fail",isMoved);
	}

	
	private boolean changePlayerPosition(SnakeAndLadder s, int move)
			throws Exception {
		Class[] types = new Class[] { Integer.class };
		Method method = getMethod("changePlayerPosition", types);
		Boolean isMoved = (Boolean) method.invoke(s, move);
		return isMoved;
	}

	private SnakeAndLadder getInstance() throws Exception {
		SnakeAndLadder instance = new SnakeAndLadder();
		setPlayerInput(instance, "n");
		instance.setUpGame();

		Method changePlayer = getMethod("changePlayer", null);
		changePlayer.setAccessible(true);
		changePlayer.invoke(instance, null);
		return instance;
	}

	private Method getMethod(String methodName, Class[] types) throws Exception {
		Method method = SnakeAndLadder.class.getDeclaredMethod(methodName,
				types);
		method.setAccessible(true);
		return method;
	}

	private void setPlayerInput(SnakeAndLadder instance, String input)
			throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		Field privateField = SnakeAndLadder.class.getDeclaredField("in");
		privateField.setAccessible(true);
		Scanner str = (Scanner) privateField.get(instance);
		str = new Scanner(in);
		privateField.set(instance, str);
	}
}
