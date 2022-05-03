package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Game {
	private ArrayList<Player> top;
	private Board board;
	private LocalTime beginig;
	private LocalTime end;
	private Tag state;
	Player rick;
	Player morty;
	
	public void serialize() {
		
	}
	
	public void deserialize() {
		
	}
	
	public void actualizeTop(ArrayList<Player> top) {
		
	}
	
	public Game(int lenght,int width,String player1,String player2,int seeds,int portals) {
		board=new Board(lenght,width,seeds);
		for(int i=0;i<lenght*width;i++) {
			board.addCell();
		}
		for(int i=0;i<seeds;i++) {
			board.addSeed();
		}
		for(int i=0;i<portals;i++) {
			board.createPortals();
		}
		rick=new Player(player1);
		morty=new Player(player2);
		board.setActualRick(board.searchCell((int)(Math.random()*board.getSize()+1), board.getFirst()));
		board.getActualRick().setRick(rick);
		board.setActualMorty(board.searchCell((int)(Math.random()*board.getSize()+1), board.getFirst()));
		board.getActualMorty().setMorty(morty);
	}
	
	public LocalTime getBeginig() {
		return beginig;
	}
	public void setBeginig(LocalTime beginig) {
		this.beginig = beginig;
	}
	public LocalTime getEnd() {
		return end;
	}
	public void setEnd(LocalTime end) {
		this.end = end;
	}
	public Tag getState() {
		return state;
	}
	public void setState(Tag state) {
		this.state = state;
	}
	
	
}
