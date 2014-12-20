package com.test.game.snakeLadder.snake;

import org.junit.Assert;
import org.junit.Test;

import com.game.snakeLadder.snake.Snake;

public class SnakeTest {

	@Test
	public void testValidNoOfSnakes(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(7);
		boolean actual = snake.isValidNoOfSnakes();
		Assert.assertTrue("Number of Snake is not valid", actual);
	}
	@Test
	public void testNotValidSnakeCount(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(1);
		boolean actual = snake.isValidNoOfSnakes();
		Assert.assertFalse("No of Snakes are not valid", actual);
	}
	
	@Test
	public void testValidHeadPosition(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(7);
		boolean actual = snake.isValid(80, 10);
		Assert.assertTrue("Snake Head position is not valid",actual);
	}
	
	@Test
	public void testInValidHeadPosition(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(7);
		boolean actual = snake.isValid(1, 10);
		Assert.assertFalse("Snake Head position is not valid",actual);
	}
	
	@Test
	public void testValidTailPosition(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(7);
		boolean actual = snake.isValid(15, 10);
		Assert.assertTrue("Snake Tail position is not valid",actual);
	}
	
	@Test
	public void testInValidTailPosition(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(7);
		boolean actual = snake.isValid(1, 10);
		Assert.assertFalse("Snake Head position is not valid",actual);
	}
	
	@Test
	public void testValidSnakeCordinates(){
		Snake snake = new Snake();
		boolean actual = snake.isValid(50, 10);
		Assert.assertTrue("Snake Cordinates are not valid ",actual);
	}
	
	@Test
	public void testAddNewValidSnakeEntry(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(5);
		boolean isAdded = snake.addNewSnake(50, 30);
		Assert.assertTrue("Error while adding new Snake entry",isAdded);
	}
	
	@Test
	public void testAddNewInValidSnakeEntry(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(5);
		boolean isAdded = snake.addNewSnake(10, 30);
		Assert.assertFalse("Error while adding new Snake entry",isAdded);
	}
	
	@Test
	public void testIsAllSnakesCompleted(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(5);
		snake.addNewSnake(97, 60);
		snake.addNewSnake(50, 40);
		snake.addNewSnake(42, 20);
		snake.addNewSnake(18, 15);
		snake.addNewSnake(10, 5);
		boolean actual = snake.isAllSnakesComplete();
		Assert.assertTrue("All Snakes entry are not completed",actual);
	}

	@Test
	public void testIsAllSnakesNotCompleted(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(5);
		snake.addNewSnake(97, 60);
		snake.addNewSnake(42, 20);
		snake.addNewSnake(18, 15);
		snake.addNewSnake(10, 5);
		boolean actual = snake.isAllSnakesComplete();
		Assert.assertFalse("All Snakes entry are not completed",actual);
	}
	
	
	@Test
	public void testDuplicateEntry(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(5);
		snake.addNewSnake(20, 10);
		boolean actual = snake.isSnakeExist(20);
		Assert.assertTrue("Ther is duplicate entry in Snake",actual);
	}
	
	
	@Test
	public void testUniqueEntry(){
		Snake snake = new Snake();
		snake.setNoOfSnakesIfValid(5);
		snake.addNewSnake(20, 10);
		boolean actual = snake.isSnakeExist(40);
		Assert.assertFalse("Ther is duplicate entry in Snake",actual);
	}
}
