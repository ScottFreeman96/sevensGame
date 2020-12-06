package techtest.sevensGame.sevensGame;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class DeckTest 
{
	
	@Test
	public void createDeckTest() 
	{
		Deck deck = new Deck();
		deck.createDeck();
		assertEquals(52, deck.getDeck().size());
	}

}
