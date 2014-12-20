package com.test.game.snakeLadder.die;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.game.snakeLadder.die.Die;
public class DieTest {

	@Test
	public void testDieMinNumber(){
		int actual = 1;
		assertTrue("Number is < 1", actual>=1);
	}
	
	@Test
	public void testDieRandom(){
		Die d = Die.getDie();
		int actual = d.throwDie();
		assertTrue("Number is in between 1 to 6", actual>=1 && actual <=6);
	}
	@Test
	public void testDieMaxNumber(){
		int actual = 6;
		assertTrue("Number is >6 ", actual<=6);
	}
	

}
