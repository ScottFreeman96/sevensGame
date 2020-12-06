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
		
		assertEquals(2, validCards.size());
	}
	
	@Test
	public void checkSelectedCardAndPlayTest()
	{	
		Player player = new Player();
		
		ArrayList<Card> playerHand = new ArrayList<Card>();
		ArrayList<Card> validCards = new ArrayList<Card>();
		
		playerHand.add(new Card(4, Suits.HEARTS));
		playerHand.add(new Card(4, Suits.DIAMONDS));
		playerHand.add(new Card(8, Suits.SPADES));
		
		player.setPlayerHand(playerHand);
		
		validCards.add(new Card(4, Suits.HEARTS));
		validCards.add(new Card(8, Suits.SPADES));
		
		player.checkSelectedCardAndPlay(100, 566);
		player.checkSelectedCardAndPlay(142, 556); //should not remove from hand
		player.checkSelectedCardAndPlay(224, 556);
		
		assertEquals(1, player.getPlayerHand().size());
		
	}
	
	@Test
	public void addToTableTest() {
		Player player = new Player();
		
		player.addToTable(new Card(3,Suits.HEARTS));
		assertEquals(true, player.isClicked());
	}

}
