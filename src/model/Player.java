package model;

import java.io.Serializable;

public class Player implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private int seeds;
	private int points;
	
	public Player(String name, int points) {
		this.setName(name);
		this.setPoints(points);
		setSeeds(0);
	}
	
	public Player(String name) {
		this.setName(name);
		setSeeds(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getSeeds() {
		return seeds;
	}

	public void setSeeds(int seeds) {
		this.seeds = seeds;
	}
	
	
	
	
}
