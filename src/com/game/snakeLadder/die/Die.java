package com.game.snakeLadder.die;

import java.util.Random;

public class Die {

	private static final int TOTAL_DIE_POINTS = 6;

	private Die() {
	}

	public static Die getDie() {
		return new Die();
	}

	public Integer throwDie() {
		Random r = new Random();
		return r.nextInt(TOTAL_DIE_POINTS) + 1;
	}
	public Integer getMaxMovePosition(){
		return TOTAL_DIE_POINTS;
	}
}
