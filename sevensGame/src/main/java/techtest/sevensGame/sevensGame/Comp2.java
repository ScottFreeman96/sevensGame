package techtest.sevensGame.sevensGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Comp2 
{

	private ArrayList<Card> comp2Hand = new ArrayList<Card>();
	
	public void takeTurn(ArrayList<Card> tableHearts, ArrayList<Card> tableClubs, ArrayList<Card> tableDiamonds, ArrayList<Card> tableSpades) 
	{
		
		System.out.println("=== Computer 2's turn ===");
		System.out.println("Computer 2 hand: "+comp2Hand);
		
		boolean turnOver =false;
		
		//Hand split into suits
		ArrayList<Card> handHearts = findSuitCards(comp2Hand, Suits.HEARTS);
		ArrayList<Card> handClubs = findSuitCards(comp2Hand, Suits.CLUBS);
		ArrayList<Card> handDiamonds = findSuitCards(comp2Hand, Suits.DIAMONDS);
		ArrayList<Card> handSpades = findSuitCards(comp2Hand, Suits.SPADES);
		
		//Weighting due to high/low cards in suit
		double heartsWeight = findWeighting(handHearts);
		double clubsWeight = findWeighting(handClubs);
		double diamondsWeight = findWeighting(handDiamonds);
		double spadesWeight = findWeighting(handSpades);
		
		//Total total in each suit
		double totalHearts = handHearts.size();
		double totalClubs = handClubs.size();
		double totalDiamonds = handDiamonds.size();
		double totalSpades = handSpades.size();
		
		//List of suit totals/weights
		List<Double> totalsList = Arrays.asList(totalHearts*heartsWeight, totalClubs*clubsWeight, totalDiamonds*diamondsWeight, totalSpades*spadesWeight);
		System.out.println("Totals list: "+totalsList);
		ArrayList<Double> suitTotals = new ArrayList<Double>();
		suitTotals.addAll(totalsList);
		
		//Find suit with most cards/weight, assign that suit to be played
		for(int x=0;x<4;x++) 
		{
			Double max = Collections.max(totalsList);
			
			if(totalsList.get(0) == max && turnOver == false) 
			{
				turnOver = playCard(handHearts, tableHearts);
				if(turnOver == false) 
				{
					totalsList.set(0, 0.0);
				}
			}
			else if(totalsList.get(1) == max && turnOver == false) 
			{
				turnOver = playCard(handClubs, tableClubs);
				if(turnOver == false) 
				{
					totalsList.set(1, 0.0);
				}
			}
			else if(totalsList.get(2) == max && turnOver == false) 
			{
				turnOver = playCard(handDiamonds, tableDiamonds);
				if(turnOver == false) 
				{
					totalsList.set(2, 0.0);
				}
			}
			else if(totalsList.get(3) == max && turnOver == false)
			{
				turnOver = playCard(handSpades, tableSpades);
				if(turnOver == false) 
				{
					totalsList.set(3, 0.0);
				}
			}
		}
		
		if(turnOver == false) 
		{
			System.out.println("No valid move");
		}
		
		ArrayList<Card> newHand = new ArrayList<Card>();
		newHand.addAll(handHearts);
		newHand.addAll(handClubs);
		newHand.addAll(handDiamonds);
		newHand.addAll(handSpades);
		
		comp2Hand = newHand;
		
		System.out.println("Computer 2 hand at end of turn: "+comp2Hand);
	}
	
	public ArrayList<Card> findSuitCards(ArrayList<Card> hand, Suits suit)
	{
		ArrayList<Card> suitCards = new ArrayList<Card>();
		
		for (Card card : hand) 
		{
			if (card.getSuit()== suit)
			{
				suitCards.add(card);
			}
		}
		
		return suitCards;
	}
	
	public static double findWeighting(ArrayList<Card> handSuit) 
	{
		double handWeight = 1.0;

		for(Card card : handSuit) 
		{
			if(card.getValue()>12) 
			{
				handWeight += 0.3;
			}
			else if(card.getValue() == 2 || card.getValue() == 12) 
			{
				handWeight += 0.2;
			}
		}
		
		return handWeight;
	}
	
	
	
	public boolean playCard(ArrayList<Card> handSuit, ArrayList<Card> tableSuit)
	{
		boolean turnOver = false;
		
		for(Card card : handSuit) 
		{
			if(card.getValue() == 7) 
			{
				tableSuit.add(card);
				System.out.println("Played: " + card);
				handSuit.remove(card);
				turnOver = true;
				break;
			}
			else if(tableSuit.size()>0 && turnOver == false) 
			{
				for(Card tableCard : tableSuit) 
				{
					if((tableCard.getValue() - card.getValue()) == 1 || (card.getValue() - tableCard.getValue()) == 1) 
					{
						tableSuit.add(card);
						System.out.println("Played: " + card);
						handSuit.remove(card);
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
		
		return turnOver;
	}

	public ArrayList<Card> getComp2Hand() 
	{
		return comp2Hand;
	}

	public void setComp2Hand(ArrayList<Card> comp2Hand) 
	{
		this.comp2Hand = comp2Hand;
	}
	
	
	
}
