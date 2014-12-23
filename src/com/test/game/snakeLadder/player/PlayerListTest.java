package com.test.game.snakeLadder.player;

import org.junit.Assert;
import org.junit.Test;

import com.game.snakeLadder.player.Player;
import com.game.snakeLadder.player.Players;


public class PlayerListTest {

	
	@Test
	public void testAddNewPlayer(){
		Players list =new Players();
		Player p1 = new Player("A");
		boolean isAdded = list.addPlayer(p1);
		Assert.assertTrue("Player is not added", isAdded);
	}
	@Test
	public void testAddNewPlayerWithNull(){
		Players list =new Players();
		boolean isAdded = list.addPlayer(null);
		Assert.assertFalse("Player is not added", isAdded);
	}
	
	@Test
	public void addMultiplePlayer(){
		Players list =new Players();
		Player p1 = new Player("A");
		boolean isFirstAdded = list.addPlayer(p1);
		Player p2 = new Player("B");
		boolean isSecondAdded = list.addPlayer(p2);
		Player p3 = new Player("C");
		boolean isThirdAdded = list.addPlayer(p3);
		
		Assert.assertTrue("Error while adding multiple players", (isFirstAdded && isSecondAdded && isThirdAdded));
	}
	
	@Test
	public void testTotalPlayerAreOver(){
		Players list =new Players();
		Player p1 = new Player("A");
		list.addPlayer(p1);
		Player p2 = new Player("B");
		list.addPlayer(p2);
		Player p3 = new Player("C");
		list.addPlayer(p3);
		boolean actual = list.isMaxPlayerCompleted();
		Assert.assertTrue("Total Players are over",actual);
	}
	
	@Test
	public void testTotalPlayerAreNotOver(){
		Players list =new Players();
		Player p1 = new Player("A");
		boolean isFirstAdded = list.addPlayer(p1);
		Player p2 = new Player("B");
		boolean isSecondAdded = list.addPlayer(p2);
		boolean actual = list.isMaxPlayerCompleted();
		Assert.assertFalse("Total Players are over",actual);
	}
	@Test
	public void testCurrentPlayer(){
		Players list =new Players();
		Player p1 = new Player("A");
		boolean isFirstAdded = list.addPlayer(p1);
		Player p2 = new Player("B");
		boolean isSecondAdded = list.addPlayer(p2);
		Player p3 = new Player("C");
		boolean isThirdAdded = list.addPlayer(p3);
		
		//Expected p1
		Player current = list.getNextPlayer();
		Assert.assertEquals("Error while retriving current Player ",p1, current);
		
		//expected p2
		current = list.getNextPlayer();
		Assert.assertEquals("Error while retriving current Player ",p2, current);

		//Expected p3
		current = list.getNextPlayer();
		Assert.assertEquals("Error while retriving current Player ",p3, current);
		//Again Expect p1 
		current = list.getNextPlayer();
		Assert.assertEquals("Error while retriving current Player ",p1, current);
	}
	

}
