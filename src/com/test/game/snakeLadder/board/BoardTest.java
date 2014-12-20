package com.test.game.snakeLadder.board;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import com.game.snakeLadder.board.Board;

public class BoardTest {

//ROw test
	@Test
	public void testRowOverFlow(){
		Board b = new Board(13, 100);
		boolean actual = b.isValidRow();
		assertFalse("Row Overfalow Fail", actual);
	}
	@Test
	public void testRowUnderFlow(){
		Board b = new Board(5, 100);
		boolean actual = b.isValidRow();
		assertFalse("Row Underflow Fail",actual);
	}
	@Test
	public void testValidRow(){
		Board b = new Board(12, 100);
		boolean actual = b.isValidRow();
		assertTrue("Valid Row Test Fail",actual);
	}
	//For column Testing
	@Test
	public void testColOverFlow(){
		Board b = new Board(100, 13);
		boolean actual = b.isValidCol();
		assertFalse("Col Overfalow Fail", actual);
	}
	@Test
	public void testColUnderFlow(){
		Board b = new Board(50, 0);
		boolean actual = b.isValidCol();
		assertFalse("Col Underflow Fail",actual);
	}
	@Test
	public void testValidCol(){
		Board b = new Board(12, 10);
		boolean actual = b.isValidCol();
		assertTrue("Valid Col Test Fail",actual);
	}
	@Test
	public void testValidBoard(){
		Board b = new Board(10,10);
		boolean actual = b.isValidBoard();
		assertTrue("Board Validation Fail",actual);
	}
}
