public abstract class AbstractCard implements Comparable<AbstractCard>{
  
	/* handy arrays for ranks and suits    */
	/* do NOT change these                 */
	public final static String[] RANKS = { "None", "None", 
			"2", "3", "4", "5", "6", "7", "8", "9", "10", 
			"Jack", "Queen", "King", "Ace", "Joker"};
	
	public final static String[] SUITS = { "Diamonds", "Clubs", "Hearts", "Spades", "None"};

	protected String suit;
	protected String rank;
	
	// Purpose: Get the current card’s rank as an integer
	// Output: the rank of the card
	// joker -> 1, 2 -> 2, 3 -> 3, ..., 10 -> 10
	// jack -> 11, queen -> 12, king -> 13, ace -> 14
	public abstract int getRank();

	// Purpose: Get the current card’s rank as a string
	// Returns the cards’s rank as one of the strings in Card.RANKS
	// (whichever corresponds to the card)
	public abstract String getRankString();
	
	// Purpose: Get the current card’s suit
	// Returns the card’s suit as one of the strings in Card.SUITS
	// (whichever corresponds to the card)
	public abstract String getSuit();  
  
	@Override
	public final String toString(){
	// outputs a string representation of a card object
		int r = getRank();
		if( r >= 2 && r <= 14 ){
			return r + getSuit().substring(0,1);
	}
		else if (r == 1){
			return "J";
	}
		return "invalid card";
	}
}

