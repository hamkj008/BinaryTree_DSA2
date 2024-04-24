package Database;


import java.util.Calendar;
import java.util.Locale;

/**
 * Class to represent a PlayStation game trophy. A trophy comes in
 * four ranks: bronze, silver, gold and platinum. The date the trophy was
 * earned and its respective game is also stored.
 * Created for Data Structures, SP2 2017
 * @author Kieran Hambledon hamkj008
 * @version 1.0
 */
public class Trophy {
    
	public enum Rank {
		BRONZE, GOLD, SILVER, PLATINUM
	}

	public enum Rarity {
		COMMON,  UNCOMMON, RARE, VERY_RARE, ULTRA_RARE
	}
	
	
	private String name;
	private Rank rank;
	private Rarity rarity;
	private Calendar obtained;
	private Game game;
	
	

	public Trophy() {}
	
	
    public Trophy(String name, Rank rank, Rarity rarity, Calendar obtained, Game game) {
    	this.name = name;
    	this.rank = rank;
    	this.rarity = rarity;
    	this.obtained = obtained;
    	this.game = game;   	
    }
   
    
    public String toString() {
    	int day = obtained.get(Calendar.DATE);
    	
		return "\"" + name + "\", " + "rank: " + rank + ", rarity: " + rarity + ", obtained on: " 
				+ obtained.getDisplayName(Calendar.MONTH, Calendar.SHORT, new Locale("English")) 
				+ " " +  String.format("%02d", day) + ", " + obtained.get(Calendar.YEAR);
    }
   
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Rank getRank() {
		return rank;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}
	
	public Calendar getObtained() {
		return obtained;
	}
	
	public void setObtained(Calendar obtained) {
		this.obtained = obtained;
	}

	public Game getGame() {
		return game;	
	}
	
	public void setGame(Game game) {
		this.game = game;	
	}
	
}
