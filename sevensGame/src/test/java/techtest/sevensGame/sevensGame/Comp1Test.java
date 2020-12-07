package techtest.sevensGame.sevensGame;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class Comp1Test 
{
	
	@Test
	public void playCardTest() 
	{
		Comp1 comp1 =new Comp1();

		ArrayList<Card> comp1Hand =new ArrayList<Card>();
		ArrayList<Card> diamonds = new ArrayList<Card>();
		
		comp1Hand.add(new Card(6, Suits.DIAMONDS));
		comp1.setComp1Hand(comp1Hand);
		
		diamonds.add(new Card(7, Suits.DIAMONDS));
		
		boolean played = comp1.playCard(Suits.DIAMONDS, diamonds);

		assertEquals("playCard should return true", true, played);
		assertEquals("comp1Hand should be empty", 0, comp1.getComp1Hand().size());
		assertEquals("Size of diamonds should be 2",2, diamonds.size());
	}
	
}
