package com.game;

import com.game.play.SnakeAndLadder;
import com.game.snakeLadder.playable.Playable;

public class Game {
	
	Playable currentGame = new SnakeAndLadder();
	
	public static void main(String[] args) {
		new Game().start();
		
	}
	public void start(){
		currentGame.setUpGame();
		if(currentGame.isPlayable()){
			currentGame.play();
			
		}
	}
	
}
