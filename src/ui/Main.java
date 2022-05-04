package ui;

import java.util.Scanner;

import model.Game;
import model.Player;
import model.Tag;

public class Main {

	private static int dieSides = 6;
	private static boolean turnRick = true;
	private static Game game;
	public static Scanner lector = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Welcome to Rick and Morty: Dimensional recolectors game");
		System.out.println("Please enter the lenght of the board.");
		int lenght = lector.nextInt();
		System.out.println("Please enter the width of the board.");
		int width = lector.nextInt();
		System.out.println("Please enter amount of seeds.");
		int seeds = lector.nextInt();
		System.out.println("Please enter amount of portals.");
		int portals = lector.nextInt();
		lector.nextLine();
		System.out.println("Please enter the nickName of the Rick player.");
		String rickPlayer = lector.nextLine();
		System.out.println("Please enter the nickName of the Morty player.");
		String mortyPlayer = lector.nextLine();
		
		game = new Game(lenght, width, seeds, portals, rickPlayer, mortyPlayer);
		
		while(game.getState() == Tag.INGOING) {
			menu();
			int option = lector.nextInt();
			switch(option) {
				case 1:
					movePlayer();
					break;
				case 2: printBoard();
					break;
				case 3:	printLinks();
					break;
				case 4:	printScore();
					break;
			}
			game.verifyEndedGame();
		}
		Player winner = endGame();
		if(winner != null) {
			game.actualizeTop(winner);
			System.out.println(game.printTop());
		}
	}
	
	public static void movePlayer() {
		int die = (int) (Math.random()*(dieSides-1))+1;
		System.out.println("The die landed on number: "+die);
		System.out.println("Select the direction to move.");
		System.out.println("1. Ahead");
		System.out.println("2. Back");
		int option = lector.nextInt();
		if(option == 2) {
			die *= -1;
		}
		if(isTurnRick() == true) {
			game.getBoard().movePlayer(game.getBoard().getActualRick(), die, 1);
			setTurnRick(false);
		}else{
			game.getBoard().movePlayer(game.getBoard().getActualMorty(), die, 2);
			setTurnRick(true);
		}
	}
	
	public static void menu() {
		if(isTurnRick() == true) {
			System.out.println("Now it's Rick's turn. What do yo want to do?");
		}else {
			System.out.println("Now it's Morty's turn. What do yo want to do?");
		}
		System.out.println("1. Roll dice.");
		System.out.println("2. View board.");
		System.out.println("3. View links.");
		System.out.println("4. Bookmark.");
	}
	
	public static void printBoard() {
		System.out.println(game.getStringBoard());
	}
	
	public static void printLinks() {
		System.out.println(game.getLinks());
	}
	
	public static void printScore() {
		System.out.println("Rick: "+game.getRick().getSeeds()+" seeds");
		System.out.println("Morty: "+game.getMorty().getSeeds()+" seeds");
	}
	
	public static Player endGame() {
		if(game.getRick().getSeeds() > game.getMorty().getSeeds()) {
			System.out.println("CONGRATULATIONS RICK!!");
			System.out.println("Rick won with "+game.getRick().getSeeds()+" recolected.");
			return game.getRick();
		}else if(game.getRick().getSeeds() < game.getMorty().getSeeds()) {
			System.out.println("CONGRATULATIONS Morty!!");
			System.out.println("Morty won with "+game.getMorty().getSeeds()+" recolected.");
			return game.getMorty();
		}
		return null;
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
