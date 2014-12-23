package com.game.snakeLadder.player;

public class Players {

	private static final int TOTAL_MAX_PLAYERS = 3; 
	Node head = null;
	Node tail = null;

	public boolean addPlayer(Player player) {
		if(player == null){
			return false;
		}
		if(isMaxPlayerCompleted()){
			System.out.println("Total Players are over");
			return false;
		}
		Node n = new Node(player);
		if (head == null && tail == null) {
			head = n;
			tail = n;
		} else {
			while (tail.getNext() != null) {
				tail = tail.getNext();
			}
			tail.setNext(n);
			tail = tail.getNext();
		}
		return true;
	}
	public boolean isMaxPlayerCompleted(){
		Node temp = head;
		int count = 0;
		while(temp != null){
			count++;
			temp = temp.getNext();
		}
		return count == TOTAL_MAX_PLAYERS;
	}
	public Player getNextPlayer() {
		Player current = null;
		
		tail = tail.getNext();
		if (tail == null) {
			tail = head;
		}
		current  = tail.getPlayer();
		return current;
	}
}

class Node {

	private Player player;
	private Node next;

	public Node(Player player) {
		super();
		this.player = player;
		this.next = null;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getNext() {
		return next;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}