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
	
	public void verifyEndedGame() {
		if(board.getNumSeeds() == rick.getSeeds() + morty.getSeeds()) {
			setState(Tag.FINISHED);
		}
	}
	
	public Game(int lenght,int width,String player1,String player2,int seeds,int portals) {
		state = Tag.INGOING;
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

	public ArrayList<Player> getTop() {
		return top;
	}

	public void setTop(ArrayList<Player> top) {
		this.top = top;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getRick() {
		return rick;
	}

	public void setRick(Player rick) {
		this.rick = rick;
	}

	public Player getMorty() {
		return morty;
	}

	public void setMorty(Player morty) {
		this.morty = morty;
	}
	
	
}
