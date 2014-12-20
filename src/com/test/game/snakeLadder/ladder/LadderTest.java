package com.test.game.snakeLadder.ladder;

import org.junit.Assert;
import org.junit.Test;

import com.game.snakeLadder.ladder.Ladder;

public class LadderTest {

	@Test
	public void testValidNoOfLadder(){
		Ladder ladder = new Ladder();
		ladder.setNoOfLaddersIfValid(7);
		boolean actual = ladder.isValidLadderCount();
		Assert.assertTrue("Number of Ladder is not valid", actual);
	}
	@Test
	public void testNotValidLadderCount(){
		Ladder ladder = new Ladder();
		ladder.setNoOfLaddersIfValid(1);
		boolean actual = ladder.isValidLadderCount();
		Assert.assertFalse("Minimum Ladders not provided", actual);
	}
	@Test
	public void testBottom(){
		Ladder ladder = new Ladder();
		ladder.setNoOfLaddersIfValid(10);
		boolean actual = ladder.isValid(0, 10);
		Assert.assertFalse("Ladder Bottom Can not be start from 0",actual);
	}
	@Test
	public void testTop(){
		Ladder ladder = new Ladder();
		boolean actual = ladder.isValid(0, 101);
		Assert.assertFalse("Ladder Top position can not be greater than total Board number",actual);
	}
	@Test
	public void testValidLadderCordinates(){
		Ladder ladder = new Ladder();
		boolean actual = ladder.isValid(2, 10);
		Assert.assertTrue("Ladder Bottom top cordinates are not valid",actual);
	}
	@Test
	public void testNotValidLadderCordinates(){
		Ladder ladder = new Ladder();
		boolean actual = ladder.isValid(10, 2);
		Assert.assertFalse("Ladder Bottom top cordinates are not valid",actual);
	}
	
	@Test
	public void isAllLadderAreSet(){
		Ladder ladder = new Ladder();
		ladder.setNoOfLaddersIfValid(5);
		ladder.addNewLadder(2, 7);
		ladder.addNewLadder(20, 27);
		ladder.addNewLadder(35, 45);
		ladder.addNewLadder(60, 70);
		ladder.addNewLadder(84, 98);
		boolean actual = ladder.isAllLadderComplete();
		Assert.assertTrue("All laders are not completed",actual);
	}

	@Test
	public void isAllLadderAreNotSet(){
		Ladder ladder = new Ladder();
		ladder.setNoOfLaddersIfValid(5);
		ladder.addNewLadder(2, 7);
		ladder.addNewLadder(20, 27);
		ladder.addNewLadder(35, 45);
		ladder.addNewLadder(60, 70);
		boolean actual = ladder.isAllLadderComplete();
		Assert.assertFalse("All laders are not completed",actual);
	}
	@Test
	public void testDuplicateEntry(){
		Ladder ladder = new Ladder();
		ladder.setNoOfLaddersIfValid(5);
		ladder.addNewLadder(2, 10);
		boolean actual = ladder.isLadderExist(2);
		Assert.assertTrue("Ther is duplicate entry in Ladder",actual);
	}
	
	@Test
	public void addNewValidLadderEntry(){
		Ladder ladder = new Ladder();
		ladder.setNoOfLaddersIfValid(5);
		boolean isAdded = ladder.addNewLadder(5, 10);
		Assert.assertTrue("Error while adding new Ladder entry",isAdded);
	}
	
	@Test
	public void addNewInValidLadderEntry(){
		Ladder ladder = new Ladder();
		ladder.setNoOfLaddersIfValid(5);
		boolean isAdded = ladder.addNewLadder(50, 10);
		Assert.assertFalse("Error while adding new Ladder entry",isAdded);
	}
}

