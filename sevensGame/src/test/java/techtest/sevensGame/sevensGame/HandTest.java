package techtest.sevensGame.sevensGame;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class HandTest 
{
	
	@Test
	public void createHandTest() 
	{
		Deck deck = new Deck();
		Hand hand = new Hand();
		int handTotal = 17;
		
		deck.createDeck();
		ArrayList<Card> testHand = hand.createHand(deck.getDeck(), handTotal);
		assertEquals("Size of hand shoukd match handTotal", handTotal, testHand.size());
		assertEquals("deck size should have reduced by handTotal value", 52 - handTotal, deck.getDeck().size());
	}
	
	@Test
	public void orderHandTest() 
	{
		Deck deck = new Deck();
		Hand hand = new Hand();
		int handTotal1 = 17;
		
		deck.createDeck();
		
		ArrayList<Card> testHand1 = hand.createHand(deck.getDeck(), handTotal1);
		testHand1 = hand.orderHand(testHand1);
		System.out.println(testHand1);
		
		//Check first cards are less than second cards 
		if(testHand1.get(0).getSuit() == Suits.HEARTS && testHand1.get(1).getSuit() == Suits.HEARTS) {
			boolean lessThan = testHand1.get(0).getValue() < testHand1.get(1).getValue();
			assertEquals("First heart should have lower value than second",true, lessThan);
		}
		if(testHand1.get(0).getSuit() == Suits.CLUBS && testHand1.get(1).getSuit() == Suits.CLUBS) {
			boolean lessThan = testHand1.get(0).getValue() < testHand1.get(1).getValue();
			assertEquals("First club should have lower value than second", true, lessThan);
		}
		if(testHand1.get(0).getSuit() == Suits.DIAMONDS && testHand1.get(1).getSuit() == Suits.DIAMONDS) {
			boolean lessThan = testHand1.get(0).getValue() < testHand1.get(1).getValue();
			assertEquals("First diamond should have lower value than second", true, lessThan);
		}
		if(testHand1.get(0).getSuit() == Suits.SPADES && testHand1.get(1).getSuit() == Suits.SPADES) {
			boolean lessThan = testHand1.get(0).getValue() < testHand1.get(1).getValue();
			assertEquals("First spade should have lower value than second", true, lessThan);
		}
	}
	
	@Test
	public void findSevenOfDiamondsTest() 
	{
		Deck deck = new Deck();
		Hand hand = new Hand();
		int handTotal1 = 17;
		int handTotal2 = 17;
		int handTotal3 = 18;
		
		deck.createDeck();
		ArrayList<Card> testHand1 = hand.createHand(deck.getDeck(), handTotal1);
		ArrayList<Card> testHand2 = hand.createHand(deck.getDeck(), handTotal2);
		ArrayList<Card> testHand3 = hand.createHand(deck.getDeck(), handTotal3);
		
		assertEquals("testHand1 size should match handTotal1", handTotal1, testHand1.size());
		assertEquals("testHand2 size should match handTotal2", handTotal2, testHand2.size());
		assertEquals("testHand3 size should match handTotal3", handTotal3, testHand3.size());
		assertEquals("deck should be empty", 0, deck.getDeck().size());
	}

}
