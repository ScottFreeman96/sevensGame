package techtest.sevensGame.sevensGame;

import java.util.ArrayList;

public class Comp1  
{
	
	private ArrayList<Card> comp1Hand = new ArrayList<Card>();
	
	public void takeTurn(ArrayList<Card> hearts, ArrayList<Card> clubs, ArrayList<Card> diamonds, ArrayList<Card> spades) 
	{
		System.out.println("=== Computer 1's turn ===");
		System.out.println("Computer 1 hand: "+comp1Hand);
		boolean turnOver = false;
		
		//Attempt to play a heart
		if(turnOver == false) 
		{
			turnOver = playCard(Suits.HEARTS, hearts);
		}
		
		//Attempt to play a club
		if(turnOver == false) 
		{
			turnOver = playCard(Suits.CLUBS, clubs);
		}
		
		//Attempt to play a diamond
		if(turnOver == false) 
		{
			turnOver = playCard(Suits.DIAMONDS, diamonds);
		}
		
		//Attempt to play a spade
		if(turnOver == false) 
		{
			turnOver = playCard(Suits.SPADES, spades);
		}
		
		if(turnOver == false) 
		{
			System.out.println("No valid move");
		}
			
		System.out.println("Computer 1 hand at end of turn: "+comp1Hand);
	}
	
	public boolean playCard(Suits suit, ArrayList<Card> tableSuit) 
	{
		boolean turnOver = false;

		//Find and play 7	
		for(Card card : comp1Hand) 
		{
			if(card.getValue() ==7 && card.getSuit() == suit ) 
			{
				tableSuit.add(card);
				System.out.println("Played: " + card);
				comp1Hand.remove(card);
				turnOver = true;
				break;
			}
		}
		
		//Find and play valid card		
		if(turnOver == false) 
		{
			for(Card card : comp1Hand) 
			{
				if(tableSuit.size()>0 && card.getSuit() == suit) 
				{
					for(Card tableCard : tableSuit) 
					{
						if((tableCard.getValue() - card.getValue()) == 1 || (card.getValue() - tableCard.getValue()) == 1)
						{
							tableSuit.add(card);
							System.out.println("Played: " + card);
							comp1Hand.remove(card);
							turnOver = true;
							break;
						}
					}
				}
			
				if(turnOver == true) 
				{
					break;
				}
			}
		}
		
		return turnOver;
	}

	public ArrayList<Card> getComp1Hand() 
	{
		return comp1Hand;
	}

	public void setComp1Hand(ArrayList<Card> comp1Hand) 
	{
		this.comp1Hand = comp1Hand;
	}
	
	
	
}
