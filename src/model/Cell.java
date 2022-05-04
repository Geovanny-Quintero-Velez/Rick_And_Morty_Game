package model;

public class Cell {

	private Player rick, morty = null;
	private boolean isSeed;
	private int position;
	private Board board;
	private Cell next,previous,portal = null;
	private char portalS;
	
	
	public Cell(int position,Board board) {
		this.setPosition(position);
		isSeed = false;
		
		
	}
	
	
	public void addNext(int position) {
		next = new Cell(position,board);
		next.setPrevious(this);
	}

	public Player getRick() {
		return rick;
	}

	public void setRick(Player rick) {
		this.rick = rick;
		if(isSeed()==true) {
			rick.setSeeds(rick.getSeeds()+1);
			setSeed(false);
		}
	}

	public void setMorty(Player morty) {
		this.morty = morty;
		if(isSeed()==true) {
			morty.setSeeds(morty.getSeeds()+1);
			setSeed(false);
		}
	}
	
	public Player getMorty() {
		return morty;
	}
	
	public boolean isSeed() {
		return isSeed;
	}

	public void setSeed(boolean isSeed) {
		this.isSeed = isSeed;
	}

	public Cell getNext() {
		return next;
	}

	public void setNext(Cell next) {
		this.next = next;
	}

	public Cell getPrevious() {
		return previous;
	}

	public void setPrevious(Cell previous) {
		this.previous = previous;
	}

	public Cell getPortal() {
		return portal;
	}

	public void setPortal(Cell portal) {
		this.portal = portal;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public String toString() {
		String out="";
		
		return out;
	}
	
	public Cell getCellAtTheEnd() {
		if(next!=board.getFirst()) {
			if(next.position%board.widht==0) {
				return next;
			}
			else {
				return next.getCellAtTheEnd();
			}
		}
		return null;
	}
	public String getPortalS() {
		return "["+portalS+"]";
	}
	public void setPortalS(char portalS) {
		this.portalS = portalS;
	}
	
}
