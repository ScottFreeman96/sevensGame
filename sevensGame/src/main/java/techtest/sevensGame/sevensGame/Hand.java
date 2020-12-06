package techtest.sevensGame.sevensGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Hand 
{
	
	Deck deck = new Deck();
	
	public ArrayList<Card> createHand(ArrayList<Card> deck, int handTotal) 
	{
		ArrayList<Card> hand = new ArrayList<Card>();

		//For size of hand assigned
		for(int x=0;x<handTotal;x++) 
		{
			//random number within deck limits
			int locator = ThreadLocalRandom.current().nextInt(0, deck.size()); 
			Card card = deck.get(locator);
			hand.add(card);
			deck.remove(locator);
		}
		
		return hand;
	}
	
	public ArrayList<Card> orderHand(ArrayList<Card> hand) 
	{
		ArrayList<Card> orderedHand = new ArrayList<Card>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		
		//Hearts 
		for(Card card : hand) 
		{
			if(card.getSuit() == Suits.HEARTS) 
			{
				temp.add(card.getValue());
			}
		}
		Collections.sort(temp);
		
		for(int value : temp) 
		{
			orderedHand.add(new Card(value, Suits.HEARTS));
		}
		temp.clear();
		
		//Clubs	
		for(Card card : hand) 
		{
			if(card.getSuit() == Suits.CLUBS) 
			{
				temp.add(card.getValue());
			}
		}
		Collections.sort(temp);
		
		for(int value : temp) 
		{
			orderedHand.add(new Card(value, Suits.CLUBS));
		}
		temp.clear();
		
		//Diamonds		
		for(Card card : hand) 
		{
			if(card.getSuit() == Suits.DIAMONDS) 
			{
				temp.add(card.getValue());
			}
		}
		Collections.sort(temp);
		
		for(int value : temp) 
		{
			orderedHand.add(new Card(value, Suits.DIAMONDS));
		}
		temp.clear();
		
		//Spades
		for(Card card : hand) 
		{
			if(card.getSuit() == Suits.SPADES) 
			{
				temp.add(card.getValue());
			}
		}
		Collections.sort(temp);
		
		for(int value : temp) 
		{
			orderedHand.add(new Card(value, Suits.SPADES));
		}
		temp.clear();
		
		return orderedHand;
	}
	
	public void findSevenOfDiamonds(ArrayList<Card> playerHand, ArrayList<Card> comp1Hand, ArrayList<Card> comp2Hand,ArrayList<Card> tableDiamonds) 
	{

		//Check player hand
		for(Card card : playerHand) 
		{
			//Play 7 of diamonds
			if (card.getValue() == 7 && card.getSuit() == Suits.DIAMONDS)
			{
				playerHand.remove(card);
				tableDiamonds.add(card);
				System.out.println("Found in player hand");
				break;
			}
		}
		
		//Check comp1 hand
		for(Card card : comp1Hand) 
		{
			//Play 7 of diamonds
			if (card.getValue() == 7 && card.getSuit() == Suits.DIAMONDS)
			{
				comp1Hand.remove(card);
				tableDiamonds.add(card);
				System.out.println("Found in computer 1 hand");
				break;
			}
		}
		
		//Check comp2 hand
		for(Card card : comp2Hand) 
		{
			//Play 7 of diamonds
			if (card.getValue() == 7 && card.getSuit() == Suits.DIAMONDS)
			{
				comp2Hand.remove(card);
				tableDiamonds.add(card);
				System.out.println("Found in computer 2 hand");
				break;
			}
		}
	}
}
