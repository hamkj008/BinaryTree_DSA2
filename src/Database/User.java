package Database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class to represent a PlayStation user.
 * Created for Data Structures, SP2 2017
 * @author Kieran Hambledon hamkj008
 * @version 1.0
 */
public class User {
    private String username;
	private int level;
	ArrayList<Trophy> trophies;
	private GameList games;
	private Calendar dob;
	private User left;
	private User right;
	
	private int balance = 0;

	
	public User() {}
	
	public User(String username, Calendar dob, int level) {
		this.username = username;
		this.dob = dob;
		this.level = level;		
    }

	
    /**
     * DO NOT MODIFY THIS METHOD
     * This method uses the username and level to create a unique key.
     * As we don't want the username's hash to increase the level, it's first converted
     * to a floating point, then added to the level.
     * @return the unique key
     */
    public double calculateKey() {
        int hash = Math.abs(username.hashCode());
        // Calculate number of zeros we need
        int length = (int)(Math.log10(hash) + 1);
        // Make a divisor 10^x
        double divisor = Math.pow(10, length);
        // Return level.hash
        return level + hash / divisor;
    }

    
    public String toString() {
    	int day = dob.get(Calendar.DATE);
    	
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("User: " + username);
    	sb.append("\n\nTrophies: ");
    	
    	for(int i = 0; i < trophies.size(); i++) {
    		sb.append("\n" + trophies.get(i));
    	}
    	sb.append("\n\nGames: \n" + games.toString());
    	sb.append("\n\nBirth Date: " + dob.getDisplayName(Calendar.MONTH, Calendar.SHORT, new Locale("English")) 
		+ " " + String.format("%02d", day) + ", " + dob.get(Calendar.YEAR));

		return sb.toString();
	}

    
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public Calendar getDob() {
		return dob;
	}
	
	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;		
	}

	public double getKey() {
		return calculateKey();
	}
	
	public User getLeft() {
		return left;
	}
	
	public User getRight() {
		return right;
	}
	
	public void setLeft(User user) {
		this.left = user;
	}
	
	public void setRight(User user) {
		this.right = user;
	}
	
	public GameList getGames() {
		return games;		
	}
	
	public void setGames(GameList games) {
		this.games = games;		
	}

	public ArrayList<Trophy> getTrophies() {
		return trophies;	
	}
	
	public void setTrophies(ArrayList<Trophy> trophies) {
		this.trophies = trophies;	
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
