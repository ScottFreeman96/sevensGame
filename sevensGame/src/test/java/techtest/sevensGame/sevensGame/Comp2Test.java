package techtest.sevensGame.sevensGame;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class Comp2Test 
{
	
	@Test
	public void findSuitCardsTest() 
	{
		Comp2 comp2 = new Comp2();
		
		ArrayList<Card> hearts = new ArrayList<Card>();
		ArrayList<Card> clubs = new ArrayList<Card>();
		ArrayList<Card> diamonds = new ArrayList<Card>();
		ArrayList<Card> spades= new ArrayList<Card>();
		
		ArrayList<Card> comp2Hand = new ArrayList<Card>();

		comp2Hand.add(new Card(6, Suits.HEARTS));
		comp2Hand.add(new Card(3, Suits.HEARTS));
		comp2Hand.add(new Card(6, Suits.CLUBS));
		comp2Hand.add(new Card(6, Suits.DIAMONDS));
		comp2Hand.add(new Card(6, Suits.SPADES));
		
		hearts = comp2.findSuitCards(comp2Hand, Suits.HEARTS);
		clubs = comp2.findSuitCards(comp2Hand, Suits.CLUBS);
		diamonds = comp2.findSuitCards(comp2Hand, Suits.DIAMONDS);
		spades = comp2.findSuitCards(comp2Hand, Suits.SPADES);
		
		assertEquals(2, hearts.size());
		assertEquals(1, clubs.size());
		assertEquals(1, diamonds.size());
		assertEquals(1, spades.size());
				
	}
	
	@Test
	public void findWeightingTest() 
	{
		ArrayList<Card> hearts = new ArrayList<Card>();
		ArrayList<Card> clubs = new ArrayList<Card>();
		
		double heartsWeight;
		double clubsWeight;
		
		hearts.add(new Card(2,Suits.HEARTS));
		hearts.add(new Card(13,Suits.HEARTS));
		hearts.add(new Card(12,Suits.HEARTS));
		
		clubs.add(new Card(6,Suits.CLUBS));
		
	    heartsWeight = Comp2.findWeighting(hearts);
	    clubsWeight = Comp2.findWeighting(clubs);
	    
	    //Double has precision loss, so have to add a delta for precision loss
	    assertEquals(1.7, heartsWeight, 0.00001);
	    assertEquals(1.0, clubsWeight, 0.00001);
	}
	
	@Test
	public void playCardTest() 
	{
		Comp2 comp2 = new Comp2();
		
		ArrayList<Card> handDiamonds = new ArrayList<Card>();
		ArrayList<Card> diamonds = new ArrayList<Card>();
		
		handDiamonds.add(new Card(6, Suits.DIAMONDS));
		
		diamonds.add(new Card(7, Suits.DIAMONDS));
		
		boolean played = comp2.playCard(handDiamonds, diamonds);
		
		assertEquals(true, played);
		assertEquals(0, handDiamonds.size());
		assertEquals(2, diamonds.size());
	}

}
