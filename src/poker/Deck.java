package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;

	public Deck() {

		//	Create an ArrayList of Cards, add each card
		ArrayList<Card> MakingDeck = new ArrayList<Card>();
		for (short i = 0; i <= 3; i++) {
			eSuit SuitValue = eSuit.values()[i];			
			for (short j = 0; j <= 12; j++) {
				eRank RankValue = eRank.values()[j];				
				Card NewCard = new Card(SuitValue,RankValue);
				MakingDeck.add(NewCard);
			}
		}
		
		//	Set the instance variable
		cards = MakingDeck;
		
		//	Shuffle the cards
		Collections.shuffle(cards);
		
	}
	
	public Deck(int nbrJokers) {
//		Create an ArrayList of Cards, add each card
			ArrayList<Card> MakingDeck = new ArrayList<Card>();
			for (short i = 0; i <= 3; i++) {
				eSuit SuitValue = eSuit.values()[i];			
				for (short j = 0; j <= 12; j++) {
					eRank RankValue = eRank.values()[j];				
					Card NewCard = new Card(SuitValue,RankValue);
					MakingDeck.add(NewCard);
				}
			}
			for (short j = 0; j < nbrJokers; j++) {
				eSuit SuitValue = eSuit.values()[4];
				eRank RankValue = eRank.values()[13];
				Card NewJoker = new Card(SuitValue, RankValue);
				MakingDeck.add(NewJoker);
			}
			
			//	Set the instance variable
			cards = MakingDeck;
			
			//	Shuffle the cards
			Collections.shuffle(cards);
	}

	public Card drawFromDeck() {
		// Removes the first card from the deck and return the card
		Card FirstCard = this.cards.get(0);
		this.cards.remove(0);
		return FirstCard;
	}

	public int getTotalCards() {
		// Returns the total number of cards still in the deck
		return cards.size();
	}
}