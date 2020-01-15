package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hand {
	private ArrayList<Card> CardsInHand; //changed for the purposes of JUnitTest

	private int HandStrength;
	private int HiHand;
	private int LoHand;
	private int Kicker;
	@SuppressWarnings("unused")
	private boolean bScored = false;

	private boolean Flush;
	private boolean Straight;
	private boolean Ace;
	private boolean Joker; //Check for joker
	
	public Hand(Deck d) {
		ArrayList<Card> Import = new ArrayList<Card>();
		for (int x = 0; x < 5; x++) {
			Import.add(d.drawFromDeck());
		}
		CardsInHand = Import;
	}


	public Hand() {
		ArrayList<Card> EmptyCardsInHand= new ArrayList<Card>(); 
		for (int x = 0; x < 5; x++) {
			EmptyCardsInHand.add(null);
		}
		this.CardsInHand = EmptyCardsInHand;
	}



	public ArrayList<Card> getCards() {
		return CardsInHand;
	}

	public int getHandStrength() {
		return HandStrength;
	}

	public int getKicker() {
		return Kicker;
	}

	public int getHighPairStrength() {
		return HiHand;
	}

	public int getLowPairStrength() {
		return LoHand;
	}

	public boolean getAce() {
		return Ace;
	}

	public static Hand EvalHand(ArrayList<Card> SeededHand) {		
		Deck d = new Deck();
		Hand h = new Hand(d);
		h.CardsInHand = SeededHand;
		Hand b = new Hand(d);
		b = h.BestHandWithJoker();
		b.EvalHand();

		return b;
	}	
	
	//CONSTRUCRTION ZONE		
	public Hand BestHandWithJoker() {
		ArrayList<Hand> Explosion = new ArrayList<Hand>();
		Collections.sort(this.CardsInHand, Card.CardRank);
		Explosion.add(this);
		CheckJoker(Explosion);
		Collections.sort(CheckJoker(Explosion), Hand.HandRank);
		Hand BestHand = Explosion.get(0);
		return BestHand;
	}
	
	public static ArrayList<Hand> CheckJoker(ArrayList<Hand> hands) {
		for (Hand h: hands) {
			Collections.sort(h.CardsInHand, Card.CardRank);
			for (Card c: h.CardsInHand) {
				if (c.getRank() == eRank.JOKER) {
					return explodeHands(hands, h);
				}
			}
			h.EvalHand(); //sorts and evalutes hands strength attributes
		}
		return hands;
	}

	public static ArrayList<Hand> explodeHands(ArrayList<Hand> hands, Hand hand) {
		hands.remove(hand);
		for (int i = 0; i < 4; i++) { 
			eSuit Suit = eSuit.values()[i]; //loops through 4 suits
			for (int j = 0; j < 13; j++) { 
				eRank Value = eRank.values()[j]; // loops through 13 values
				
				Hand temp = new Hand(); // temporary hand that copies hand we passed in
				
				for (int k = 0; k < 5; k++) { //make a deep copy of the arraylist
					eSuit CloneSuit = hand.CardsInHand.get(k).getSuit();
					eRank CloneRank = hand.CardsInHand.get(k).getRank();
					Card clone = new Card(CloneSuit, CloneRank);
					temp.CardsInHand.set(k, clone);
				}
			
				Card c = new Card(Suit,Value); // creates a card of the indexed suit/value
				
				temp.CardsInHand.set(0, c); // replaces the first joker with c

				temp.Joker = true;
				
				hands.add(temp); // adds temp to arraylist hands
			
				CheckJoker(hands); // recurses the checkJoker in case there are multiple jokers
				}
			}
		return hands;
	}
	
	//END CONSTRUCTION ZONE
	
	public void EvalHand() {
		// Evaluates if the hand is a flush and/or straight then figures out
		// the hand's strength attributes


		// Sort the cards!
		Collections.sort(CardsInHand, Card.CardRank);

		// Ace Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == eRank.ACE) {
			Ace = true;
		}

		// Flush Evaluation
		if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getSuit()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getSuit() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getSuit()) {
			Flush = true;
		} else {
			Flush = false;
		}

		// Straight Evaluation
		if (Ace) {
			// Looks for Ace, King, Queen, Jack, 10
			if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.KING
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.QUEEN
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == eRank.JACK
					&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN) {
				Straight = true;
				// Looks for Ace, 2, 3, 4, 5
			} else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TWO
					&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == eRank.THREE
					&& CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == eRank.FOUR
					&& CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == eRank.FIVE) {
				Straight = true;
			} else {
				Straight = false;
			}
			// Looks for straight without Ace
		} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
				.getRank() + 1
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
						.getRank() + 2
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
						.getRank() + 3
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()
						.getRank() + 4) {
			Straight = true;
		} else {
			Straight = false;
		}

		// Evaluates the hand type
		if (Straight == true && Flush == true && CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN && Ace && Joker) {
			ScoreHand(eHandStrength.RoyalFlush, 0, 0, 0);
		}
		
		else if (Straight == true && Flush == true && CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == eRank.TEN && Ace) {
			ScoreHand(eHandStrength.NaturalRoyalFlush, 0, 0, 0);
		}

		// Straight Flush
		else if (Straight == true && Flush == true) {
			ScoreHand(eHandStrength.StraightFlush, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0, 0);
		}
		// Five of a Kind
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.FiveOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0, 0);
		}
		
		// Four of a Kind

		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.FourOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0, CardsInHand.get(eCardNo.FifthCard.getCardNo())
					.getRank().getRank());
		}

		else if (CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.FourOfAKind, CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank(), 0, CardsInHand.get(eCardNo.FirstCard.getCardNo())
					.getRank().getRank());
		}

		// Full House
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.FullHouse, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), CardsInHand.get(eCardNo.FourthCard.getCardNo())
					.getRank().getRank(), 0);
		}

		else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()
				&& CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.FullHouse, CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(), CardsInHand.get(eCardNo.FirstCard.getCardNo())
					.getRank().getRank(), 0);
		}

		// Flush
		else if (Flush) {
			ScoreHand(eHandStrength.Flush, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0, 0);
		}

		// Straight
		else if (Straight) {
			ScoreHand(eHandStrength.Straight, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0, 0);
		}

		// Three of a Kind
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0, CardsInHand.get(eCardNo.FourthCard.getCardNo())
					.getRank().getRank());
		}

		else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(), 0, CardsInHand.get(eCardNo.FifthCard.getCardNo())
					.getRank().getRank());
		} else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.ThreeOfAKind, CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(), 0, CardsInHand.get(eCardNo.FirstCard.getCardNo())
					.getRank().getRank());
		}

		// Two Pair
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank())) {
			ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), CardsInHand.get(eCardNo.ThirdCard.getCardNo())
					.getRank().getRank(), CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank().getRank());
		} else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank())) {
			ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), CardsInHand.get(eCardNo.FourthCard.getCardNo())
					.getRank().getRank(), CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank());
		} else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()
				&& (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank())) {
			ScoreHand(eHandStrength.TwoPair, CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(), CardsInHand.get(eCardNo.FourthCard.getCardNo())
					.getRank().getRank(), CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank());
		}

		// Pair
		else if (CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0, CardsInHand.get(eCardNo.ThirdCard.getCardNo())
					.getRank().getRank());
		} else if (CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.SecondCard.getCardNo()).getRank().getRank(), 0, CardsInHand.get(eCardNo.FirstCard.getCardNo())
					.getRank().getRank());
		} else if (CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.ThirdCard.getCardNo()).getRank().getRank(), 0, CardsInHand.get(eCardNo.FirstCard.getCardNo())
					.getRank().getRank());
		} else if (CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank() == CardsInHand.get(eCardNo.FifthCard.getCardNo()).getRank()) {
			ScoreHand(eHandStrength.Pair, CardsInHand.get(eCardNo.FourthCard.getCardNo()).getRank().getRank(), 0, CardsInHand.get(eCardNo.FirstCard.getCardNo())
					.getRank().getRank());
		}

		else {
			ScoreHand(eHandStrength.HighCard, CardsInHand.get(eCardNo.FirstCard.getCardNo()).getRank().getRank(), 0, CardsInHand.get(eCardNo.SecondCard.getCardNo())
					.getRank().getRank());
		}
	}

	private void ScoreHand(eHandStrength hST, int HiHand, int LoHand, int Kicker) {
		this.HandStrength = hST.getHandStrength();
		this.HiHand = HiHand;
		this.LoHand = LoHand;
		this.Kicker = Kicker;
		this.bScored = true;

	}

	/**
	 * Custom sort to figure the best hand in an array of hands
	 */
	public static Comparator<Hand> HandRank = new Comparator<Hand>() {

		public int compare(Hand h1, Hand h2) {

			int result = 0;

			result = h2.HandStrength - h1.HandStrength;

			if (result != 0) {
				return result;
			}
			
			result = h2.HiHand - h1.HiHand;
			if (result != 0) {
				return result;
			}
			
			result = h2.LoHand - h1.LoHand;
			if (result != 0) {
				return result;
			}

			result = h2.Kicker - h1.Kicker;
			if (result != 0) {
				return result;
			}
			
			return 0;
		}
	};
}
