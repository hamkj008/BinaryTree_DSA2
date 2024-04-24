package Database;

import java.util.GregorianCalendar;


/**
 * Class to represent a single linked-list of Database.Game objects.
 * Created for Data Structures, SP2 2017
 * @author Kieran Hambledon hamkj008
 * @version 1.0
 */
public class GameList {

    public Game head;


    public GameList() {}
    
    
	public GameList(Game head) {
		this.head = head;
	
    }
		
	
	public void addGame(Game game) {
		// Throw exception if the specified object is null.
		if(game == null) {
			throw new IllegalArgumentException();
		}
		
		Game gameRef = head;
		// If the list is empty, add game object to list.
		if(head == null) {
			head = game;
		}
		// Game will not be added if it is already in the list
		else if(gameRef.equals(game)) {
			System.out.println("Error - game already in list.");
		}
		else {
			// if head is full add game object at end of list
			while(gameRef.getNext() != null) {				
				gameRef = gameRef.getNext();
				// Check to see if game is already in the list. Will not be added if so
				if(gameRef.equals(game)) {
					System.out.println("Error - game already in list");
				}
			}
			// End of the list is found and game can be added.
			gameRef.setNext(game);			
		}
	}
	

    public String toString() {
    	
    	StringBuilder sb = new StringBuilder();
    	Game gameRef = head;
    	
    	// If the list is empty
    	if(gameRef == null) {
    		sb.append("Empty game list");
    		return sb.toString();
    	}
    	// Traverse the list and append all games to the toString
    	while(gameRef.getNext() != null) {
    		sb.append(gameRef.toString() + "\n");
    		gameRef = gameRef.getNext();
    	}
    	sb.append(gameRef.toString());
    	
		return sb.toString();
    }

    
	public Game getGame(String name) {
		if(name == null) {
			throw new IllegalArgumentException();
		}
		// An iterator Game object to iterate over the list.
		Game gameRef = head;
		
		// Search through list to find the specified Game.
		while(gameRef != null) {
			// The game has been found.
			if(gameRef.getName().equals(name)) {
				return gameRef;
			}
			gameRef = gameRef.getNext();
		}
		// The game has not been found.
		return null;
	}
	
	public void removeGame(String name) {		
		// Throw exception if the specified object is null.
		if(name == null) {
			throw new IllegalArgumentException();
		}
		// An iterator game object to traverse the list.
		Game gameRef = head;
		// Another game object to follow the gameRef, keeping track of gameRefs predecessor.
		Game prev = null;
		// Boolean to check if the game exists in the list.
		boolean found = false;
			
		// First traversal to find the index of the item.
		// For loop to find the index of the item.
		while(gameRef != null && found == false) {
			// The game has been found
			if(gameRef.getName().equals(name)) {				
				found = true;
			}
			// Keep searching untill found or error
			else {
				prev = gameRef;
				gameRef = gameRef.getNext();
			}				
		}
		// The game has not been found so does not exist in the list.								
		if(found == false) {
			System.out.println("Error - the game does not exist in the list");
		}
		// If the game has been found
		if(found == true) {
			// If the game is in the head, link the list to remove the game and set a new head of the list.
			if(name.equals(head.getName())) {
				head = head.getNext();
				gameRef.setNext(null);
			}
			// The game is elsewhere in the list. Link the predecessors next to gameRefs next in the list
			// thereby removing the game, or predecessors next set to null if there aren't any more games in the list.
			else {
				prev.setNext(gameRef.getNext()); 	
				gameRef.setNext(null);
			}
		}
	}
	
	public void removeGame(Game game) {
		// Throw exception if the specified object is null.
		if(game == null) {
			throw new IllegalArgumentException();
		}
		// An iterator game object to traverse the list.
		Game gameRef = head;
		// Another game object to follow the gameRef, keeping track of gameRefs predecessor.
		Game prev = null;
		// Boolean to check if the game exists in the list.
		boolean found = false;
			
		// First traversal to find the index of the item.
		// For loop to find the index of the item.
		while(gameRef != null && found == false) {
			// The game has been found.
			if(gameRef.equals(game)) {				
				found = true;
			}
			// Keep searching untill found or error
			else {
				prev = gameRef;
				gameRef = gameRef.getNext();
			}				
		}
		// The game has not been found so does not exist in the list.								
		if(found == false) {
			System.out.println("Error - the game does not exist in the list");
		}
		// If the game has been found
		if(found == true) {
			// If the game is in the head, link the list to remove the game and set a new head of the list.
			if(game.equals(head)) {
				head = head.getNext();
				gameRef.setNext(null);
			}
			// The game is elsewhere in the list. Link the predecessors next to gameRefs next in the list
			// thereby removing the game, or predecessors next set to null if there aren't any more games in the list.
			else {
				prev.setNext(gameRef.getNext()); 	
				gameRef.setNext(null);
			}
		}	
	}
	
	
	public static void main(String[] args) {
		GameList glist1 = new GameList();
		System.out.println(glist1);
		Game g1 = new Game("Assassin's Creed IV: Black Flag", new GregorianCalendar(2013, 10, 29), 10);
		Game g2 = new Game("Child of Light", new GregorianCalendar(2014, 4, 1), 24);
        Game g3 = new Game("Dragon Age: Inquisition", new GregorianCalendar(2014, 11, 18), 53);
        Game g4= new Game("7 Days to Die", new GregorianCalendar(2012, 8, 10), 15);
		Game g5= new Game("GTAIV", new GregorianCalendar(2011, 7, 12), 16);
        Game g6 = new Game("The Witcher: Wild Hunt", new GregorianCalendar(2015, 7, 22), 40);
		GameList glist = new GameList(g1);
		glist.addGame(g2);
		glist.addGame(g3);
		glist.removeGame("Child of Light");
		glist.addGame(g4);		
		glist.addGame(g5);
		glist.removeGame("GTAIV");
		glist.removeGame("Assassin's Creed IV: Black Flag");
		glist.addGame(g2);
		glist.addGame(g1);
		glist.addGame(g3);
		glist.removeGame(g4);
		glist.removeGame(g6);
		glist.addGame(g4);
		glist.addGame(g6);
		glist.removeGame(g1);
		glist.removeGame(g5);
		glist.removeGame("The Witcher: Wild Hunt");
		glist.removeGame("Assassin's Creed IV: Black Flag");
		glist.toString();
	}
	
}
