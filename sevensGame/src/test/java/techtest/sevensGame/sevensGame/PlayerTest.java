package techtest.sevensGame.sevensGame;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class PlayerTest {
	
	@Test
	public void findValidCardsTest() 
	{
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> tableHearts = new ArrayList<Card>();
		
		tableHearts.add(new Card(4, Suits.HEARTS));
		
		playerHand.add(new Card(3, Suits.HEARTS));
		playerHand.add(new Card(5, Suits.HEARTS));
		
		ArrayList<Card> validCards = Player.findValidCards(playerHand, tableHearts, Suits.HEARTS);
		
		assertEquals("validCards size should equal 2", 2, validCards.size());
	}
	
	@Test
	public void addToTableTest() 
	{
		Player player = new Player();
		
		player.addToTable(new Card(3,Suits.HEARTS));
		assertEquals("isClicked should return true", true, player.isClicked());
	}

}
