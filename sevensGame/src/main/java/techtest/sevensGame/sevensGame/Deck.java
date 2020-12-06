package techtest.sevensGame.sevensGame;

import java.util.ArrayList;

public class Deck 
{

	private ArrayList<Card> deck = new ArrayList<Card>();
	private Integer[] values = {2,3,4,5,6,7,8,9,10,11,12,13,14};
	
	public void createDeck() 
	{
		for (Suits suit : Suits.values()) 
		{
			for (int value : values) 
			{
				deck.add(new Card(value,suit));
			}
		}
		
		System.out.println("Deck: "+ deck.toString());
	}
		

	public ArrayList<Card> getDeck() 
	{
		return deck;
	}


	public void setDeck(ArrayList<Card> deck) 
	{
		this.deck = deck;
	}


	@Override
	public String toString() 
	{
		return "Deck [deck=" + deck + "]";
	}
	
	
}
