package poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PlayHand {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		System.out.print("How many games would you like to play? ");
		int gCountMax = input.nextInt(); 

		System.out.print("How many jokers would you like to play with? ");
		int NbrJokers = input.nextInt();
		
		for (int gCount = 0; gCount <= gCountMax; gCount++) {
			ArrayList<Hand> Hands = new ArrayList<Hand>();
			
			Deck d = new Deck(NbrJokers);
			for (int hCnt = 0; hCnt <= 1; hCnt++) {
				Hand h = new Hand(d);
				Hands.add(h.BestHandWithJoker());
			}
						
			Collections.sort(Hands, Hand.HandRank);
			System.out.print("Hand Strength: " + Hands.get(0).getHandStrength() + " |");
			System.out.print(" Hi Hand: " + Hands.get(0).getHighPairStrength() + " |");
			System.out.print(" Lo Hand: " + Hands.get(0).getLowPairStrength() + " |");
			System.out.print(" Kicker: " + Hands.get(0).getKicker());

			System.out.print(" beats ");

			System.out.print(" Hand Strength: " + Hands.get(1).getHandStrength() + " |");
			System.out.print(" Hi Hand: " + Hands.get(1).getHighPairStrength() + " |");
			System.out.print(" Lo Hand: " + Hands.get(1).getLowPairStrength() + " |");
			System.out.print(" Kicker: " + Hands.get(1).getKicker() + " |");

			System.out.print("\n");

		}
		input.close();
	}

}
