package com.game.snakeLadder;

import com.game.play.SnakeAndLadder;
import com.game.snakeLadder.playable.Playable;

public class GameFactory {

	private GameFactory(){}
	
	public static Playable getGame(){
		
		Playable currentGame = new SnakeAndLadder();
		
		currentGame.setUpGame();
		
		if(currentGame.isPlayable()){
			currentGame.play();
		}else{
			throw new RuntimeException("Error while getting Game Factory");
		}
		return currentGame;
	}
	
}
