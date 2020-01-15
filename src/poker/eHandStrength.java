package poker;

public enum eHandStrength {

	NaturalRoyalFlush(120),
	FiveOfAKind(110),
	RoyalFlush(100),
	StraightFlush(90),
	FourOfAKind(80),
	FullHouse(70),
	Flush(60),
	Straight(50),
	ThreeOfAKind(40),
	TwoPair(30),
	Pair(20),
	HighCard(10);
	
	private eHandStrength(final int handstrength){
		this.iHandStrength = handstrength;
	}

	private int iHandStrength;
	
	public int getHandStrength(){
		return iHandStrength;
	}
	
}
