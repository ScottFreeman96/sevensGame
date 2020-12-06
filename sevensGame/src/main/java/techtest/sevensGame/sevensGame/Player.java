package techtest.sevensGame.sevensGame;



import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;



public class Player 
{
	MouseLogic mouseLogic = new MouseLogic(this);
	
	private ArrayList<Card> playerHand = new ArrayList<Card>();
	private ArrayList<Card> validCards = new ArrayList<Card>();
	
	private ArrayList<Card> tableHearts = new ArrayList<Card>();
	private ArrayList<Card> tableClubs = new ArrayList<Card>();
	private ArrayList<Card> tableDiamonds = new ArrayList<Card>();
	private ArrayList<Card> tableSpades = new ArrayList<Card>();
	
	private boolean validPlays;
	private boolean clicked = false;
	
	public void takeTurn(ArrayList<Card> hand, ArrayList<Card> hearts, ArrayList<Card> clubs, ArrayList<Card> diamonds, ArrayList<Card> spades,  JFrame frame) 
	{
		
		System.out.println("=== Player's turn ===");
		System.out.println("Player hand: "+hand);
		
		//Check each table suit for valid plays
		validCards.addAll(findValidCards(hand, hearts, Suits.HEARTS));
		validCards.addAll(findValidCards(hand, clubs, Suits.CLUBS));
		validCards.addAll(findValidCards(hand, diamonds, Suits.DIAMONDS));
		validCards.addAll(findValidCards(hand, spades, Suits.SPADES));
		
		//Set local table
		tableHearts=(hearts);
		tableClubs=(clubs);
		tableDiamonds=(diamonds);
		tableSpades=(spades);

		//Check if a play can be made
		if(validCards.size()>0) 
		{
			System.out.println("Valid plays: "+validCards);
			
			//Implement click functionality
			frame.addMouseListener(mouseLogic);
			validPlays= true;	
		}
		else 
		{
			System.out.println("No valid plays");
			validPlays = false;
			
			//Display to player there are no valid moves
			JLabel noMovesLabel = new JLabel("No valid moves for player");
			noMovesLabel.setBounds(50, 100, 200, 20);
			frame.add(noMovesLabel);
			frame.repaint();
			
			try 
			{
				TimeUnit.SECONDS.sleep(3);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
		
		//Create ArrayList to help while loop continue to check if clicked
		ArrayList<Integer> waitingList = new ArrayList<Integer>();
		System.out.println("Waiting for player");
		
		//wait for player move
		while(clicked==false && validPlays ==true) 
		{
			waitingList.add(1);
			waitingList.clear();
			
			clicked=isClicked();
		}
		
		//Remove click ability and reset valid cards 
		frame.removeMouseListener(mouseLogic);
        validCards.clear();
        clicked = false;
        
        System.out.println("Player hand at end of turn: "+hand);
	}

	
	public static ArrayList<Card> findValidCards(ArrayList<Card> hand, ArrayList<Card> tableSuit, Suits suit) 
	{
		ArrayList<Card> validCards = new ArrayList<Card>();
		
		//Find 7s
		for(Card card : hand) 
		{
			if(card.getValue() ==7 && card.getSuit() == suit) 
			{
				validCards.add(card);
				break;
			}
		}
		
		//Find valid cards
		for(Card card : hand) 
		{
			if(tableSuit.size()>0 && card.getSuit() == suit) 
			{
				for(Card tableCard : tableSuit) 
				{
					//Check for a difference of 1 between card on table and card in hand
					if((tableCard.getValue() - card.getValue()) == 1 || (card.getValue() - tableCard.getValue()) == 1)
					{
						validCards.add(card);
						break;
					}
				}
			}
		}
		return validCards;
	}


	public void checkSelectedCardAndPlay(int xPos, int yPos) 
	{
		boolean played = false;

		int cardX;
		int cardY;
		int cardHeight;
		
		for(int x=0;x<playerHand.size();x++) 
		{
			Card handCard = playerHand.get(x);
			cardX= 90 + (42*x);
			cardY= 546;
			cardHeight=117;
			
			//To allow selection on full area of final card in hand
			int selectAreaModifier;
			int areaSubtract;
			
			if(handCard == playerHand.get(playerHand.size()-1)) 
			{
				selectAreaModifier= 2;
				areaSubtract = 7;
			}
			else 
			{
				selectAreaModifier=1;
				areaSubtract=0;
			}
			
			//Check clicked in card area
			if(xPos > cardX && xPos< cardX+(50*selectAreaModifier)-areaSubtract && yPos > cardY && yPos < cardY+cardHeight) 
			{
				for(Card validCard : validCards) 
				{
					//Check clicked card is valid
					if(handCard == validCard) 
					{
						System.out.println("Selected: "+handCard);
						playerHand.remove(handCard);
						addToTable(handCard);
						played=true;
						break;
					}
				}
			}
			
			if(played == true) 
			{
				break;
			}
		}
	}
	
	public void addToTable(Card handCard) 
	{
		//Check suit and add to relevant table ArrayList
		if(handCard.getSuit() == Suits.HEARTS) 
		{
			tableHearts.add(handCard);
			setClicked(true);
		}
		else if(handCard.getSuit() == Suits.CLUBS) 
		{
			tableClubs.add(handCard);
			setClicked(true);
		}
		else if(handCard.getSuit() == Suits.DIAMONDS) 
		{
			tableDiamonds.add(handCard);
			setClicked(true);
		}
		else if(handCard.getSuit() == Suits.SPADES) 
		{
			tableSpades.add(handCard);
			setClicked(true);
		}
	}

	public boolean isClicked() 
	{
		return clicked;
	}

	public void setClicked(boolean clicked) 
	{
		this.clicked = clicked;
	}

	public ArrayList<Card> getPlayerHand() 
	{
		return playerHand;
	}

	public void setPlayerHand(ArrayList<Card> playerHand) 
	{
		this.playerHand = playerHand;
	}

}
