package com.test.game.snakeLadder.board;

import junit.framework.Assert;

import org.junit.Test;

import com.game.snakeLadder.board.Board;

public class BoardTest {

//ROw test
	@Test
	public void testRowOverFlow(){
		Board b = Board.createBoard(13, 10);
		Assert.assertNull("Row Overflow test fail", b);
	}
	@Test
	public void testRowUnderFlow(){
		Board b = Board.createBoard(9, 10);
		Assert.assertNull("Row Underflow test fail", b);
	}
	@Test
	public void testValidRow(){
		Board b = Board.createBoard(12, 10);
		Assert.assertNotNull("Valid row test fail", b);
	}
	
	//For column Testing
	@Test
	public void testColOverFlow(){
		Board b = Board.createBoard(10, 13);
		Assert.assertNull("Col Overfalow test Fail", b);
	}
	@Test
	public void testColUnderFlow(){
		Board b = Board.createBoard(10, 9);
		Assert.assertNull("Col Underflow test Fail", b);
	}
	@Test
	public void testValidCol(){
		Board b = Board.createBoard(10, 12);
		Assert.assertNotNull("Valid column test Fail", b);
	}
	@Test
	public void testValidBoard(){
		Board b = Board.createBoard(10, 12);
		Assert.assertNotNull("Valid board creation test fail",b);
	}
}
