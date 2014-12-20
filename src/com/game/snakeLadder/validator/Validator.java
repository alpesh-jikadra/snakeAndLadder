package com.game.snakeLadder.validator;

import java.util.Map;

import com.game.snakeLadder.board.Board;
import com.game.snakeLadder.board.FilledBoard;
import com.game.snakeLadder.ladder.Ladder;
import com.game.snakeLadder.snake.Snake;

public class Validator {

	public static boolean isValidateSnakeEntry(int snakeHead, int snakeTail,Ladder ladder) {
		boolean isValid = true;
		if (ladder != null && ladder.getLadders().size() > 0) {
			for (Map.Entry<Integer, Integer> mp : ladder.getLadders()
					.entrySet()) {
				if (isLoopEntry(snakeHead, snakeTail, mp.getKey(),mp.getValue())) {
					System.out.println("Snake head, Ladder Top, Snake tail and ladder bottom must not same");
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}

	public static boolean isValidateLadderEntry(int ladderBottom,int ladderTop, Snake snake, Board board) {
		boolean isValid = true;
		if(ladderTop >= board.getDestinationNumber()){
			System.out.println("Ladder top can not be >="+board.getDestinationNumber());
			isValid = false;
		}else if(snake !=null && snake.getSnakes().size()>0){
			for (Map.Entry<Integer, Integer> mp : snake.getSnakes().entrySet()) {
				if (isLoopEntry(mp.getKey(),mp.getValue(), ladderBottom, ladderTop)){
					System.out.println("Snake head, Ladder Top, Snake tail and ladder bottom must not same");
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}

	private static boolean isLoopEntry(int snakeHead, int snakeTail,
			int ladderBottom, int ladderTop) {
		return (snakeHead == ladderTop && snakeTail == ladderBottom);
	}
	public static boolean isValidBoardFilled(FilledBoard board){
		boolean isValid = true;
		if(!board.getLadders().isAllLadderComplete()){
			isValid = false;
			System.out.println("You have entered only "+board.getLadders().getLadders().size() +" Ladder(s), you must enter total "+board.getLadders().getTotalLadders());
		}
		return isValid;
	}
}
