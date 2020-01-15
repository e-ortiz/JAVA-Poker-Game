package poker;

import java.util.Comparator;

public final class Card {
	private eSuit Suit;
	private eRank Rank;
	
	/**
	 * Keep the no-arg constructor private.  I don't want 'Card' created without attributes.
	 */
	@SuppressWarnings("unused")
	private Card()
	{
	}
	
	/**
	 * Create a new card of a given rank and suit.
	 * @param suit
	 * @param rank
	 */
	public Card(eSuit suit, eRank rank) {
		Suit = suit; 
		Rank = rank; 
	}

	/**
	 * Getter for Rank
	 * @return
	 */
	public eRank getRank() {
		return this.Rank;
	}

	/**
	 * Getter for Suit
	 * @return
	 */
	public eSuit getSuit() {
		return this.Suit;
	}

	/**
	 * CardRank Comparator is used for sorting the collection by rank
	 */
	public static Comparator<Card> CardRank = new Comparator<Card>() {

		public int compare(Card c1, Card c2) {

		   int Cno1 = c1.getRank().getRank();
		   int Cno2 = c2.getRank().getRank();

		   /*For descending order*/
		   return Cno2 - Cno1;

	   }};

}