package model;


public class Board{

	private int size;
	private Cell first;
	private Cell last;
	public int leght;
	public int wide;
	private int numSeeds;
	private Cell actualRick;
	private Cell actualMorty;
	private char portal;
	
	public Board(int leght,int wide,int seeds) {
		portal='A';
		size = 0;
		last = null;
		first = null;
		this.leght=leght;
		this.wide=wide;
		this.numSeeds=seeds;
	}
	
	
	
	public void movePlayer(Cell actualCell, int cellToMove, int player) {
		if(cellToMove==0) {
			if(player == 1) {
				actualCell.setRick(actualRick.getRick());
				actualRick.setRick(null);
				actualRick = actualCell;
				if(actualRick.getPortal() != null) {
					actualRick.getPortal().setRick(actualRick.getRick());
					actualRick.setRick(null);
					actualRick = actualRick.getPortal();
				}
			}else if(player == 2) {
				actualCell.setMorty(actualMorty.getMorty());
				actualMorty.setMorty(null);
				actualMorty.setMorty(null);
				actualMorty = actualCell;
				if(actualMorty.getPortal() != null) {
					actualMorty.getPortal().setMorty(actualMorty.getMorty());
					actualMorty.setMorty(null);
					actualMorty = actualMorty.getPortal();
				}
			}
		}else if(cellToMove<0) {
			movePlayer(actualCell.getPrevious(), cellToMove + 1, player);
		}else if(cellToMove>0) {
			movePlayer(actualCell.getNext(), cellToMove - 1, player);
		}
	}

	public void addCell() {
		if(size == 0) {
			first = new Cell(size+1,this);
			last = first;
			first.setNext(last);
			first.setPrevious(last);
			last.setPrevious(first);
			last.setNext(first);
			
		}
		else {
			last.addNext(size+1);
		}
		size++;
		last = last.getNext();
		last.setNext(first);
		first.setPrevious(last);
	}
	
	public void addSeed() {
		Cell temp= getCellWhitoutSeed();
		temp.setSeed(true);
	}
	
	public void createPortals() {
		Cell cell1 = null;
		Cell cell2 = null;
		do {
			cell1 =getWhitoutPortalCell();
			cell2 =getWhitoutPortalCell();
		}while(cell1.equals(cell2));
		cell1.setPortalS(portal);
		cell2.setPortalS(portal);
		System.out.println(portal);
		cell1.setPortal(cell2);
		cell2.setPortal(cell1);
		portal++;
	}
	
	public Cell getWhitoutPortalCell() {
		Cell out=searchCell((int)(Math.random()*size+1),first);
		if(out.getPortal()==null) {
			return out;
		}else return getWhitoutPortalCell();
	}
	
	public Cell getCellWhitoutSeed() {
		Cell out=searchCell((int)(Math.random()*size+1),first);
		if(!out.isSeed()) {
			return out;
		}else return getCellWhitoutSeed();
	}
	
	public Cell searchCell(int position, Cell cell) {
		if(size<0 && position <1 && position >size) {
			return null;
		}else if(cell.getPosition()==position) {
			return cell;
		}else {
			return searchCell(position, cell.getNext());
		}
	}
	
	public Cell getFirst() {
		return first;
	}
	
	public Cell getLast() {
		return last;
	}
	
	public int getSize() {
		return size;
	}
	
	public String toString() {
		String out="";
		
		return out;
	}
	
	public String stringPortals() {
		
		
		return "";
	}

	public int getNumSeeds() {
		return numSeeds;
	}



	public Cell getActualRick() {
		return actualRick;
	}



	public void setActualRick(Cell actualRick) {
		this.actualRick = actualRick;
	}



	public Cell getActualMorty() {
		return actualMorty;
	}



	public void setActualMorty(Cell actualMorty) {
		this.actualMorty = actualMorty;
	}
	
public String print() {
		
		String out=print(first,first.getCellAtTheEnd());
		return out;
	}	
	public String print(Cell n1,Cell n2) {
		String out="";
		out+=inOrder(n1,n2);
		if(n2.getNext()!=first) {
			out+="\n"+printInv(n2.getNext(),n2.getCellAtTheEnd());
		}
		return out;
	}
	
	public String printInv(Cell n1,Cell n2) {
		String out="";
		out+=inverso(n1,n2);
		if(n2.getNext()!=first) {
			out+="\n"+print(n2.getNext(),n2.getCellAtTheEnd());
		}
		return out;
	}
	
	public String inverso(Cell n1,Cell n2) {
		if(n1==n2) {
			return n1.toString();
		}else {
			return inverso(n1.getNext(),n2)+n1;
		}
	}
	public String inOrder(Cell n1,Cell n2) {
		if(n1==n2) {
			return n1.toString();
		}else {
			return n1+inOrder(n1.getNext(),n2);
		}
	}
	
	public String printP() {
		
		String out=printP(first,first.getCellAtTheEnd());
		return out;
	}	
	public String printP(Cell n1,Cell n2) {
		String out="";
		out+=inOrderP(n1,n2);
		if(n2.getNext()!=first) {
			out+="\n"+printInvP(n2.getNext(),n2.getCellAtTheEnd());
		}
		return out;
	}
	
	public String printInvP(Cell n1,Cell n2) {
		String out="";
		out+=inversoP(n1,n2);
		if(n2.getNext()!=first) {
			out+="\n"+printP(n2.getNext(),n2.getCellAtTheEnd());
		}
		return out;
	}
	
	public String inversoP(Cell n1,Cell n2) {
		if(n1==n2) {
			return n1.getPortalS()+"";
		}else {
			return inversoP(n1.getNext(),n2)+n1.getPortalS();
		}
	}
	public String inOrderP(Cell n1,Cell n2) {
		if(n1==n2) {
			return n1.getPortalS()+"";
		}else {
			return n1.getPortalS()+inOrderP(n1.getNext(),n2);
		}
	}


}
