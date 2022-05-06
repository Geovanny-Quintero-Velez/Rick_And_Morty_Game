package model;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import comparator.*;

public class Game {
	private ArrayList<Player> top;
	private Board board;
	private LocalTime beginig;
	private LocalTime end;
	private Tag state;
	Player rick;
	Player morty;
	
	public Player binarySearch(int begin, int end, String toFind) {
		int mid =(begin+end)/2;
		if(!(begin <= end)) {
			return null; 
		}else if(top.size() == 0){
			return null;
		}else if(toFind.compareTo(top.get(mid).getName()) == 0) {
			return top.get(mid);
		}else if(mid == 0 || mid ==top.size()-1) {
			return null;
		}else if(toFind.compareTo(top.get(mid).getName())<0) {
			return binarySearch(begin, mid-1, toFind);
		}else if(toFind.compareTo(top.get(mid).getName())>0) {
			return binarySearch(mid+1, end, toFind);
		}
		return null;
	}
	
	public void serialize() {
		try {
			FileOutputStream fos = new FileOutputStream("src\\file\\top.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(top);
			oos.close();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deserialize() {
		try {
			FileInputStream fis = new FileInputStream("src\\file\\top.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			top = (ArrayList<Player>) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizeTop(Player winner) {
		deserialize();
		LocalTime end=LocalTime.now();
		int score = winner.getSeeds()*120-(beginig.getSecond()-end.getSecond());
		winner.setSeeds(0);
		if(top.size()==0) {
			top.add(winner);
		}else if(binarySearch(0, top.size(), winner.getName())!= null) {
			winner = binarySearch(0, top.size(), winner.getName());
			winner.setPoints(winner.getPoints()+score);
		}else {
			top.add(winner);
		}
		Collections.sort(top, new CompareToName());
		serialize();
	}
	
	public void actualizeTop(Player winner, int points) {
		deserialize();
		System.out.println(top.size());
		int score = points;
		rick.setSeeds(0);
		morty.setSeeds(0);
		if(binarySearch(0, top.size(), winner.getName())!= null) {
			winner = binarySearch(0, top.size(), winner.getName());
			winner.setPoints(winner.getPoints()+score);
		}else {
			top.add(winner);
		}
		Collections.sort(top, new CompareToName());
		serialize();
	}
	
	public String printTop() {
		String message ="";
		Collections.sort(top, new CompareToScore());
		Iterator<Player> iterator = top.iterator();
		int counter = 0;
		for(int i=0;i<top.size()&&i<5;i++) {
			message += top.get(i).getName() +" - "+top.get(i).getPoints() + "\n";
			counter++;
		}
		/*while(iterator.hasNext() && counter<5) {
			Player player = iterator.next();
			message += player.getName() +" - "+player.getPoints() + "\n";
			counter++;
		}*/
		for(int i = 0;i<5-counter;i++) {
			message += "N/A - N/A \n";
		}
		return message;
	}
	
	public void verifyEndedGame() {
		if(board.getNumSeeds() == rick.getSeeds() + morty.getSeeds()) {
			setState(Tag.FINISHED);
			end = LocalTime.now();
		}
	}
	
	public Game(int lenght,int width,int seeds,int portals, String rickPlayer,String mortyPlayer) {
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
		if(top==null) {
			top=new ArrayList<>();
			rick=new Player(rickPlayer);
			morty=new Player(mortyPlayer);
		}else { 
			if(binarySearch(0, top.size(), rickPlayer) != null) {
				rick = binarySearch(0, top.size(), rickPlayer);
			}else {
				rick=new Player(rickPlayer);
			}
			if(binarySearch(0, top.size(), mortyPlayer) != null) {
				morty = binarySearch(0, top.size(), mortyPlayer);
			}else {
				morty=new Player(mortyPlayer);
			}
			
		}
		beginig = LocalTime.now();
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
	
	public String getLinks() {
		return board.printP();
	}
	
	public String getStringBoard() {
		return board.print();
	}
	
}
