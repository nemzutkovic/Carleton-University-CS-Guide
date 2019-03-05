import java.util.Arrays;

public class Card extends AbstractCard{

	public Card(String rank, String suit){
		this.rank = rank;
		this.suit = suit;
	// purpose: creates a card with given rank and suit
	// preconditions: suit must be a string found in Cards.SUITS
	// rank must be a string found in Cards.RANKS
	// Note: If the rank is AbstractCard.RANKS[15] then any
	// valid suit from AbstractCard.SUITS can be given
	// but the card's suit will be set to AbstractCard.SUITS[5]
	}

	public Card(int rank, String suit){
		this.rank = Integer.toString(rank);
		this.suit = suit;
	// purpose: creates a card with the given rank and suit
	// preconditions: suit must be a string found in Cards.SUITS
	// rank is an integer satisfying 1 <= rank <= 14, where
	// 1 for joker, 2 for 2, 3 for 3, .., 10 for 10
	// 11 for jack, 12 for queen, 13 for king, 14 for ace
	// Note: as with the other constructor, if a joker is created, any valid suit can be passed
	// but the card's suit will be set to AbstractCard.SUITS[5]
	}

	@Override
	public int compareTo(AbstractCard other){
		if ((java.util.Arrays.asList(SUITS).indexOf(this.suit)) > java.util.Arrays.asList(SUITS).indexOf(other.suit)) {
			return 1;	
		}
		if ((java.util.Arrays.asList(SUITS).indexOf(this.suit)) < java.util.Arrays.asList(SUITS).indexOf(other.suit)) {
			return -1;	
		}
		else{
			if(this.getRank() > other.getRank()){
				return 1;
		}
			else if(this.getRank() < other.getRank()){
				return -1;
		}
			else{
				return 0;
		}			
		}
	}

	@Override
	public int getRank() {
		if(rank.equals("1")){
			return 1;
		}
		if(rank.equals("2")){ 
			return 2;
		}
		if(rank.equals("3")){
			return 3;
		}
		if(rank.equals("4")){
			return 4;
		}
		if(rank.equals("5")){
			return 5;
		}
		if(rank.equals("6")){
			return 6;
		}
		if(rank.equals("7")){
			return 7;
		}
		if(rank.equals("8")){
			return 8;
		}
		if(rank.equals("9")){
			return 9;
		}
		if(rank.equals("10")){
			return 10;
		}
		if(rank.equals("Jack")){
			return 11;
		}
		if(rank.equals("Queen")){
			return 12;
		}
		if(rank.equals("King")){
			return 13;
		}
		if(rank.equals("Ace")){
			return 14;
		}
		else{
			return 0;
		}
	}

	@Override
	public String getRankString() {
		if(this.getRank() == 1){
			return "Joker";
		}
		else{
			return RANKS[getRank()];			
		}
	}

	@Override
	public String getSuit() {
		if(suit.equals("Joker")){
			return "None";
		}
		return suit;
	}

	/*public static void main(String[] args){
		Card c = new Card("Queen", "Diamonds");
		System.out.println(c.getRank());
		System.out.println(c.getRankString());
		System.out.println(c.getSuit());
		System.out.println(c);
		System.out.println();
		Card d = new Card("4", "Spades");
		System.out.println(c.compareTo(d));
		System.out.println(d.compareTo(c));
		System.out.println();
		Card e = new Card("Jack", "Spades");
		System.out.println(d.compareTo(e));
		System.out.println(e.compareTo(e));
		System.out.println(e.getRank());
		System.out.println(e.getSuit());
		System.out.println();
		Card j = new Card(1, "None");
		System.out.println(j);
		System.out.println(j.getRankString());
		System.out.println(j.getRank());
		System.out.println(j.getSuit());
		System.out.println(e.compareTo(j));
		System.out.println();
	}*/
}