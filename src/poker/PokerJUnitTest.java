package poker;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PokerJUnitTest {
	@Test
	public void testNaturalRoyalFlush() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.CLUBS, eRank.ACE));
		SH.add(new Card(eSuit.CLUBS, eRank.KING));
		SH.add(new Card(eSuit.CLUBS, eRank.QUEEN));
		SH.add(new Card(eSuit.CLUBS, eRank.JACK));
		SH.add(new Card(eSuit.CLUBS, eRank.TEN));

		Hand h = Hand.EvalHand(SH);
		assertEquals(
				"Expected NaturalRoyalFlush, but returned somethine else;",
				120, h.getHandStrength());
	}

	@Test
	public void testFiveOfaKind() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.JOKER, eRank.JOKER));
		SH.add(new Card(eSuit.CLUBS, eRank.ACE));
		SH.add(new Card(eSuit.HEARTS, eRank.ACE));
		SH.add(new Card(eSuit.SPADES, eRank.ACE));
		SH.add(new Card(eSuit.DIAMONDS, eRank.ACE));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected FiveOfAKind, but returned somethine else;", 110,
				h.getHandStrength());
	}

	@Test
	public void testRoyalFlush() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.JOKER, eRank.JOKER));
		SH.add(new Card(eSuit.CLUBS, eRank.KING));
		SH.add(new Card(eSuit.CLUBS, eRank.QUEEN));
		SH.add(new Card(eSuit.CLUBS, eRank.JACK));
		SH.add(new Card(eSuit.CLUBS, eRank.TEN));
		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected RoyalFlush, but returned somethine else;", 100,
				h.getHandStrength());
	}

	@Test
	public void testStraightFlush() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.CLUBS, eRank.THREE));
		SH.add(new Card(eSuit.CLUBS, eRank.FOUR));
		SH.add(new Card(eSuit.CLUBS, eRank.FIVE));
		SH.add(new Card(eSuit.CLUBS, eRank.SIX));
		SH.add(new Card(eSuit.CLUBS, eRank.SEVEN));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected StraightFlush, but returned somethine else;",
				90, h.getHandStrength());
	}
	
	@Test
	public void testStraightFlushJoker() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.JOKER, eRank.JOKER));
		SH.add(new Card(eSuit.CLUBS, eRank.FOUR));
		SH.add(new Card(eSuit.CLUBS, eRank.FIVE));
		SH.add(new Card(eSuit.CLUBS, eRank.SIX));
		SH.add(new Card(eSuit.CLUBS, eRank.SEVEN));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected StraightFlush, but returned somethine else;",
				90, h.getHandStrength());
	}


	@Test
	public void testFourOfaKind() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.CLUBS, eRank.ACE));
		SH.add(new Card(eSuit.HEARTS, eRank.ACE));
		SH.add(new Card(eSuit.DIAMONDS, eRank.ACE));
		SH.add(new Card(eSuit.SPADES, eRank.ACE));
		SH.add(new Card(eSuit.CLUBS, eRank.EIGHT));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected FourOfAKind, but returned somethine else;", 80,
				h.getHandStrength());
	}
	
	@Test
	public void testFullHouse() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.CLUBS, eRank.ACE));
		SH.add(new Card(eSuit.HEARTS, eRank.ACE));
		SH.add(new Card(eSuit.SPADES, eRank.ACE));
		SH.add(new Card(eSuit.CLUBS, eRank.EIGHT));
		SH.add(new Card(eSuit.HEARTS, eRank.EIGHT));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected FullHouse, but returned somethine else;", 70,
				h.getHandStrength());
	}
	@Test
	public void testFullHouseREVERSED() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.CLUBS, eRank.EIGHT));
		SH.add(new Card(eSuit.HEARTS, eRank.EIGHT));
		SH.add(new Card(eSuit.CLUBS, eRank.ACE));
		SH.add(new Card(eSuit.HEARTS, eRank.ACE));
		SH.add(new Card(eSuit.SPADES, eRank.ACE));
		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected FullHouse, but returned somethine else;", 70,
				h.getHandStrength());
	}
	
	@Test
	public void testFullHouseJOKER() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.CLUBS, eRank.EIGHT));
		SH.add(new Card(eSuit.HEARTS, eRank.EIGHT));
		SH.add(new Card(eSuit.JOKER, eRank.JOKER));
		SH.add(new Card(eSuit.HEARTS, eRank.ACE));
		SH.add(new Card(eSuit.SPADES, eRank.ACE));
		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected FullHouse, but returned somethine else;", 70,
				h.getHandStrength());
	}
	
	@Test
	public void testFlush() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.CLUBS, eRank.THREE));
		SH.add(new Card(eSuit.CLUBS, eRank.KING));
		SH.add(new Card(eSuit.CLUBS, eRank.ACE));
		SH.add(new Card(eSuit.CLUBS, eRank.TEN));
		SH.add(new Card(eSuit.CLUBS, eRank.EIGHT));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected Flush, but returned somethine else;", 60,
				h.getHandStrength());
	}
	@Test
	public void testFlushJOKER() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.JOKER, eRank.JOKER));
		SH.add(new Card(eSuit.CLUBS, eRank.KING));
		SH.add(new Card(eSuit.CLUBS, eRank.ACE));
		SH.add(new Card(eSuit.CLUBS, eRank.TEN));
		SH.add(new Card(eSuit.CLUBS, eRank.EIGHT));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected Flush, but returned somethine else;", 60,
				h.getHandStrength());
	}
	
	@Test
	public void testStraight() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.SPADES, eRank.SIX));
		SH.add(new Card(eSuit.SPADES, eRank.FIVE));
		SH.add(new Card(eSuit.CLUBS, eRank.FOUR));
		SH.add(new Card(eSuit.CLUBS, eRank.THREE));
		SH.add(new Card(eSuit.HEARTS, eRank.TWO));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected Straight, but returned somethine else;", 50,
				h.getHandStrength());
	}
	@Test
	public void testStraightJOKER() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.JOKER, eRank.JOKER));
		SH.add(new Card(eSuit.SPADES, eRank.FIVE));
		SH.add(new Card(eSuit.CLUBS, eRank.FOUR));
		SH.add(new Card(eSuit.CLUBS, eRank.THREE));
		SH.add(new Card(eSuit.HEARTS, eRank.TWO));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected Straight, but returned somethine else;", 50,
				h.getHandStrength());
	}

	@Test
	public void testThreeOfAKind() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.SPADES, eRank.SIX));
		SH.add(new Card(eSuit.SPADES, eRank.SIX));
		SH.add(new Card(eSuit.CLUBS, eRank.SIX));
		SH.add(new Card(eSuit.CLUBS, eRank.THREE));
		SH.add(new Card(eSuit.HEARTS, eRank.TWO));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected ThreeOfAKind, but returned somethine else;", 40,
				h.getHandStrength());
	}
	@Test
	public void testThreeOfAKindJOKER() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.JOKER, eRank.JOKER));
		SH.add(new Card(eSuit.SPADES, eRank.SIX));
		SH.add(new Card(eSuit.CLUBS, eRank.SIX));
		SH.add(new Card(eSuit.CLUBS, eRank.THREE));
		SH.add(new Card(eSuit.HEARTS, eRank.TWO));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected ThreeOfAKind, but returned somethine else;", 40,
				h.getHandStrength());
	}

	@Test
	public void testTwoPair() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.SPADES, eRank.SIX));
		SH.add(new Card(eSuit.SPADES, eRank.SIX));
		SH.add(new Card(eSuit.CLUBS, eRank.EIGHT));
		SH.add(new Card(eSuit.CLUBS, eRank.EIGHT));
		SH.add(new Card(eSuit.HEARTS, eRank.TWO));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected TwoPair, but returned somethine else;", 30,
				h.getHandStrength());
	}
	
	@Test
	public void testPair() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.SPADES, eRank.THREE));
		SH.add(new Card(eSuit.SPADES, eRank.SIX));
		SH.add(new Card(eSuit.CLUBS, eRank.EIGHT));
		SH.add(new Card(eSuit.DIAMONDS, eRank.EIGHT));
		SH.add(new Card(eSuit.HEARTS, eRank.TWO));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected TwoPair, but returned somethine else;", 20,
				h.getHandStrength());
	}
	@Test
	public void testPairJOKER() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.SPADES, eRank.THREE));
		SH.add(new Card(eSuit.SPADES, eRank.SIX));
		SH.add(new Card(eSuit.JOKER, eRank.JOKER));
		SH.add(new Card(eSuit.DIAMONDS, eRank.EIGHT));
		SH.add(new Card(eSuit.HEARTS, eRank.TWO));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected TwoPair, but returned somethine else;", 20,
				h.getHandStrength());
	}	
	@Test
	public void HIGHCARD() {
		ArrayList<Card> SH = new ArrayList<Card>();
		SH.add(new Card(eSuit.SPADES, eRank.THREE));
		SH.add(new Card(eSuit.SPADES, eRank.SIX));
		SH.add(new Card(eSuit.CLUBS, eRank.EIGHT));
		SH.add(new Card(eSuit.DIAMONDS, eRank.FOUR));
		SH.add(new Card(eSuit.HEARTS, eRank.TWO));

		Hand h = Hand.EvalHand(SH);
		assertEquals("Expected TwoPair, but returned somethine else;", 10,
				h.getHandStrength());
	}

}