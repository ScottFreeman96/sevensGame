package techtest.sevensGame.sevensGame;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DeckTest 
{
	
	@Test
	public void createDeckTest() 
	{
		Deck deck = new Deck();
		deck.createDeck();
		assertEquals("Size of deck should be 52", 52, deck.getDeck().size());
	}

}
