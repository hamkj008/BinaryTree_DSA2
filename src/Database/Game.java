package Database;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


/**
 * Class to represent a PlayStation game.
 * Created for Data Structures, SP2 2020
 * @author Kieran Hambledon hamkj008
 * @version 1.0
 */
public class Game {
	
	private String name;
	private Calendar released;
	private Game next;
	private int totalTrophies;

    public Game() {}

    public Game(String name, Calendar released, int totalTrophies) {
    	this.name = name;
    	this.released = released;
    	this.totalTrophies = totalTrophies;  	
    }

    
    public String toString() { 
    	int day = released.get(Calendar.DATE);
    	
		return "\"" + name + "\", " + "released on: " + released.getDisplayName(Calendar.MONTH, Calendar.SHORT, new Locale("English")) 
				+ " " + String.format("%02d", day) + ", " + released.get(Calendar.YEAR);
    }

    
    @Override
    public boolean equals(Object other) {
    	if(this == other) {
    		return true;
    	}
    	if(other == null) {
    		return false;
    	}
    	if (getClass() != other.getClass()) {
			return false;
		}
    	if(!name.equals(((Game) other).getName())) {
    		return false;
    	}
    	if(!released.equals(((Game) other).getReleased())) {
    		return false;
    	}
    	if(totalTrophies != ((Game) other).getTotalTrophies()) {
    		return false;
    	}
    	return true;
    }

    
	public Calendar getReleased() {
		return released;
	}
	
	public void setReleased(Calendar released) {
		this.released = released;
	}

	public int getTotalTrophies() {
		return totalTrophies;
	}
	
	public void setTotalTrophies(int totalTrophies) {
		this.totalTrophies = totalTrophies;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Game getNext() {
		return next;
	}
	
	public void setNext(Game g2) {
		this.next = g2;		
	}
	
	public static void main(String[] args) {
		Calendar c = new GregorianCalendar(2005, 02, 10);
		Game g = new Game("as", c, 10);
		System.out.println(g);
	}
}
