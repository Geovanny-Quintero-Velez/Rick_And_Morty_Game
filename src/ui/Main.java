package ui;

import java.util.Scanner;

import model.Game;
import model.Tag;

public class Main {

	private static int dieSides = 6;
	private static boolean turnRick;
	private static Game game;
	public static Scanner lector = new Scanner(System.in);
	
	public static void main(String[] args) {
		while(game.getState() == Tag.INGOING) {
			menu();
			int option = lector.nextInt();
			switch(option) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
			}
			game.verifyEndedGame();
		}
	}
	
	public static void movePlayer() {
		int die = (int) (Math.random()*(dieSides-1))+1;
		System.out.println("Select the direction to move.");
		System.out.println("1. Ahead");
		System.out.println("2. Back");
		int option = lector.nextInt();
		if(option == 2) {
			die *= -1;
		}
		if(isTurnRick() == true) {
			game.getBoard().movePlayer(game.getBoard().getActualRick(), die, 1);
		}else{
			game.getBoard().movePlayer(game.getBoard().getActualMorty(), die, 2);
		}
	}
	
	public static void menu() {
		if(isTurnRick() == true) {
			System.out.println("Now it's Rick's turn. What do yo want to do?");
		}else {
			System.out.println("Now it's Morty's turn. What do yo want to do?");
		}
		System.out.println("1. Roll die.");
		System.out.println("2. View board.");
		System.out.println("3. View links.");
		System.out.println("4. Bookmark.");
	}
	
	public static void printBoard() {
		
	}
	
	public static void printLinks() {
		
	}
	
	public static void printScore() {
		
	}
	
	public static void printEndGame() {
		
	}

	public static boolean isTurnRick() {
		return turnRick;
	}

	public static void setTurnRick(boolean turnRick) {
		Main.turnRick = turnRick;
	}

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		Main.game = game;
	}
}
