package techtest.sevensGame.sevensGame;


import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class SevensGameMain 
{
	
	Deck deck = new Deck();
	Hand hand = new Hand();
	Renderer renderer = new Renderer();
	Comp1 comp1 = new Comp1();
	Comp2 comp2 = new Comp2();
	Player player = new Player();;
	
	private int playerTotal = 17;
	private int comp1Total = 17;
	private int comp2Total = 17;
	
	private int dealerValue;
	private int turnIndicator;
	
	private boolean gameOver = false;
	
	//Table
	private ArrayList<Card> tableHearts = new ArrayList<Card>();
	private ArrayList<Card> tableClubs = new ArrayList<Card>();
	private ArrayList<Card> tableDiamonds = new ArrayList<Card>();
	private ArrayList<Card> tableSpades = new ArrayList<Card>();
	
	//Creates window
	JFrame frame = new JFrame("Sevens");
	
	public SevensGameMain() throws IOException 
	{
			
		//Make the program shutdown on exit out.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set background colour
		frame.getContentPane().setBackground(new Color(50,123,100));
		//Stops layout manager interfering 
		frame.getContentPane().setLayout(null);
		//Full screen
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		//Create deck
		deck.createDeck();
		
		//Decide dealer
		dealerValue = ThreadLocalRandom.current().nextInt(1, 4);
		System.out.println("Dealer "+dealerValue);
		
		//Add extra card and assign turn
		if (dealerValue == 1) 
		{
			comp1Total+=1;
			turnIndicator = 2;
		}
		else if(dealerValue == 2) 
		{
			comp2Total+=1;
			turnIndicator = 3;
		}
		else if(dealerValue == 3) 
		{
			playerTotal+=1;
			turnIndicator = 1;
		}

		//Assign hands
		player.setPlayerHand(hand.createHand(deck.getDeck(), playerTotal));
		comp1.setComp1Hand(hand.createHand(deck.getDeck(), comp1Total));
		comp2.setComp2Hand(hand.createHand(deck.getDeck(), comp2Total));
		
		System.out.println("Deck "+deck.getDeck());

		//Order hands
		player.setPlayerHand(hand.orderHand(player.getPlayerHand()));
		comp1.setComp1Hand(hand.orderHand(comp1.getComp1Hand()));
		comp2.setComp2Hand(hand.orderHand(comp2.getComp2Hand()));
		
		System.out.println("Player Ordered: "+player.getPlayerHand());
		System.out.println("Comp1 Ordered: "+comp1.getComp1Hand());
		System.out.println("Comp2 Ordered: "+comp2.getComp2Hand());

		frame.setVisible(true);
		
		//Find and set the 7 of diamonds
		hand.findSevenOfDiamonds(player.getPlayerHand(), comp1.getComp1Hand(), comp2.getComp2Hand(), tableDiamonds);
		renderer.displayGame(player.getPlayerHand(), tableHearts, tableClubs, tableDiamonds, tableSpades, "Starting Game", frame, comp1.getComp1Hand().size(), comp2.getComp2Hand().size());
		
		//Game loop
		while(gameOver == false) 
		{
			//Player turn
			if(turnIndicator == 1) 
			{
				renderer.displayGame(player.getPlayerHand(), tableHearts,tableClubs,tableDiamonds,tableSpades, "Player's turn", frame, comp1.getComp1Hand().size(), comp2.getComp2Hand().size());
				player.takeTurn(player.getPlayerHand(), tableHearts,tableClubs,tableDiamonds,tableSpades, frame);
				renderer.displayGame(player.getPlayerHand(), tableHearts,tableClubs,tableDiamonds,tableSpades, "Player's turn", frame, comp1.getComp1Hand().size(), comp2.getComp2Hand().size());
				
				//Display winner, end game
				if(player.getPlayerHand().size() == 0) 
				{
					System.out.println("Player Wins!");
					renderer.displayWinner(player.getPlayerHand(), "Player", frame);
					gameOver = true;
					break;
				}
				turnIndicator = 2;
			}

			//Computer 1 turn
			if(turnIndicator == 2) 
			{
				renderer.displayGame(player.getPlayerHand(), tableHearts,tableClubs,tableDiamonds,tableSpades, "Computer 1's turn", frame, comp1.getComp1Hand().size(), comp2.getComp2Hand().size());
				
				//Wait, for realism
				try 
				{
					TimeUnit.SECONDS.sleep(3);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				
				comp1.takeTurn(tableHearts,tableClubs,tableDiamonds,tableSpades);
				renderer.displayGame(player.getPlayerHand(), tableHearts,tableClubs,tableDiamonds,tableSpades, "Computer 1's turn", frame, comp1.getComp1Hand().size(), comp2.getComp2Hand().size());
				
				//Display winner, end game
				if(comp1.getComp1Hand().size() == 0) 
				{
					System.out.println("Computer 1 Wins!");
					renderer.displayWinner(comp1.getComp1Hand(), "Computer 1", frame);
					gameOver = true;
					break;
				}
				turnIndicator = 3;
			}
			
			//Computer 2 turn
			if(turnIndicator == 3) 
			{
				renderer.displayGame(player.getPlayerHand(), tableHearts,tableClubs,tableDiamonds,tableSpades, "Computer 2's turn", frame, comp1.getComp1Hand().size(), comp2.getComp2Hand().size());
				
				//Wait, for realism
				try 
				{
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				
				comp2.takeTurn(tableHearts,tableClubs,tableDiamonds,tableSpades);
				renderer.displayGame(player.getPlayerHand(), tableHearts,tableClubs,tableDiamonds,tableSpades, "Computer 2's turn", frame, comp1.getComp1Hand().size(), comp2.getComp2Hand().size());
				
				//Display winner, end game
				if(comp2.getComp2Hand().size() == 0) 
				{
					System.out.println("Computer 2 Wins!");
					renderer.displayWinner(comp2.getComp2Hand(), "Computer 2", frame);
					gameOver = true;
					break;
				}
				turnIndicator = 1;
			}	
		}
	}

	
	public static void main(String[] args) throws IOException 
	{
		new SevensGameMain();
	}

}
