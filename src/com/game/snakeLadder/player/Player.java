package com.game.snakeLadder.player;

public class Player {

	private static int idCounter =1 ;
	private Integer currentPosition;
	private String name;
	private Integer id;
	public Player(String name) {
		super();
		this.name = name;
		this.currentPosition = 0;
		this.id = Player.idCounter++;
	}

	public Integer getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Integer currentPosition) {
		this.currentPosition = currentPosition;
	}

	public String getName() {
		return name;
	}
	
	public void printDetails(){
		System.out.println("--------------------------");
		System.out.println("Player Name : "+this.name);
		System.out.println("Player Id : "+this.id);
		System.out.println("Current Position is :"+this.currentPosition);
		System.out.println("--------------------------");
		System.out.println();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((currentPosition == null) ? 0 : currentPosition.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (currentPosition == null) {
			if (other.currentPosition != null)
				return false;
		} else if (!currentPosition.equals(other.currentPosition))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
