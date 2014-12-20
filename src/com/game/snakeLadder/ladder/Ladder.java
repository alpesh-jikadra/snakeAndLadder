
package com.game.snakeLadder.ladder;

import java.util.Map;
import java.util.TreeMap;

public class Ladder {

	private static final int MIN_LADDERS = 5;
	private static final int MAX_LADDERS = 10;
	
	private Integer totalLadders=0;
	
	private Map<Integer, Integer> ladders = new TreeMap<Integer, Integer>();;

	public Ladder(){}
	
	
	public boolean setNoOfLaddersIfValid(int noOfLadder) {
		this.totalLadders = noOfLadder;
		if(!isValidLadderCount()){
			this.totalLadders = 0;
			return false;
		}
		return true;
	}
	public boolean addNewLadder(int bottom, int top) {
		if (isAllLadderComplete()) {
			System.out.println("There is no position remains for Ladders");
			return false;
		}
		if (isLadderExist(bottom)) {
			return false;
		}
		if (!isValid(bottom, top)) {
			System.out.println("Not A valid head and tail position");
			return false;
		}
		ladders.put(bottom, top);

		return true;
	}

	public boolean isLadderExist(int bottom){
		return ladders.containsKey(bottom);
	}
	public boolean isAllLadderComplete() {
		return totalLadders >= MIN_LADDERS && totalLadders<= MAX_LADDERS && totalLadders == ladders.size();
	}

	public boolean isValid(int bottom, int top) {
		if (bottom <= 0 || top <= 0) {
			return false;
		}
		if(bottom == top){
			return false;
		}
		if (bottom >= top) {
			return false;
		}
		return true;
	}
	
	public boolean isLadderBottom(Integer position){
		return ladders.containsKey(position);
	}
	public Integer getLadderTop(Integer position){
		return ladders.get(position);
	}

	public Map<Integer, Integer> getLadders(){
		return this.ladders;
	}

	public Integer getTotalLadders() {
		return totalLadders;
	}
	
	public boolean isValidLadderCount(){
		return this.totalLadders>=MIN_LADDERS && this.totalLadders<=MAX_LADDERS;
	}
	
	public static int getMinLadderCount(){
		return Ladder.MIN_LADDERS;
	}
	public static int getMaxLadderCount(){
		return Ladder.MAX_LADDERS;
	}
	public void showLadders(){
		System.err.print("\n Ladders : ");
		for(Map.Entry<Integer, Integer> ladder : ladders.entrySet()){
			System.err.print("["+ladder.getKey()+"->"+ladder.getValue()+"],");
		}
		System.err.println();
		System.out.println();
	}
}
