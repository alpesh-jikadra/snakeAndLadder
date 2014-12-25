package com.game.snakeLadder.snake;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Snake {
	
	private static final int MIN_SNAKES = 5;
	private static final int MAX_SNAKES = 10;
	
	private Integer totalSnakes = 0;
	
	private Map<Integer, Integer> snakes = new TreeMap<Integer, Integer>();
	
	public Snake(){}
	
	public boolean setNoOfSnakesIfValid(int noOfSnakes){
		if(this.totalSnakes==0){
			this.totalSnakes = noOfSnakes;
			if (!isValidNoOfSnakes()) {
				this.totalSnakes = 0;
				return false;
			}
		}
		return true;
	}
	public Snake(int noOfSnakes){
		
	}
	public boolean addNewSnake(int head, int tail){
		if(isAllSnakesComplete()){
			System.out.println("There is no position left for snakes");
			return false;
		}
		if(isSnakeExist(head)){
			return false;
		}
		if(!isValid(head, tail)){
			System.out.println("Not A valid head and tail position");
			return false;
		}
		snakes.put(head, tail);
		
		return true;
	}
	public boolean isSnakeExist(int head){
		return snakes.containsKey(head);
	}
	public boolean isAllSnakesComplete(){
		return totalSnakes >= MIN_SNAKES && totalSnakes <= MAX_SNAKES && totalSnakes == snakes.size();
	}
	public boolean isValid(int head, int tail){
		if(head <= 0 || tail<= 0){
			return false;
		}
		if(head == tail){
			return false;
		}
		if(tail >= head){
			return false;
		}
		return true;
	}
	public boolean isSnakeHead(Integer position){
		return snakes.containsKey(position);
	}
	public Integer getSnakeTail(Integer position){
		return snakes.get(position);
	}
	public Map<Integer,Integer> getSnakes(){
		return Collections.unmodifiableMap(this.snakes);
	}
	
	public boolean isValidNoOfSnakes(){
		return this.totalSnakes>= MIN_SNAKES && this.totalSnakes<=MAX_SNAKES;
	}
	
	public static int getMinSnakeCount(){
		return MIN_SNAKES;
	}
	public static int getMaxSnakeCount(){
		return MAX_SNAKES;
	}
	
	public void showSnakes(){
		System.out.print("\n Snakes : ");
		for(Map.Entry<Integer, Integer> snake : snakes.entrySet()){
			System.out.print("["+snake.getKey()+"->"+snake.getValue()+"],");
		}
		System.out.println();
		System.out.println();		
	}
}
