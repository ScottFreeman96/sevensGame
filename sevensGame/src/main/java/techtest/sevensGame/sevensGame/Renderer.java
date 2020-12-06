package techtest.sevensGame.sevensGame;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;



public class Renderer 
{
	
	public void displayGame(ArrayList<Card> hand, ArrayList<Card> tableHearts, ArrayList<Card> tableClubs, ArrayList<Card> tableDiamonds, ArrayList<Card> tableSpades, String turn, JFrame frame, int comp1HandSize, int comp2HandSize)  
	{
		//Reset frame
		frame.getContentPane().removeAll();
		
		//Add turn label
		JLabel turnLabel = new JLabel(turn);
		turnLabel.setBounds(50, 50, 120, 20);
		frame.add(turnLabel);
				
		//Display computer hand sizes
		showComputerHand("Computer 1", comp1HandSize, 30, frame);
		showComputerHand("Computer 2", comp2HandSize, 200, frame);
		
		//Display the table cards
		displayTableSuit(tableHearts, 0, frame);
		displayTableSuit(tableClubs, 1, frame);
		displayTableSuit(tableDiamonds, 2, frame);
		displayTableSuit(tableSpades, 3, frame);
		
		//Display the player hand
		displayHand(hand, frame);
		
		frame.repaint();
		frame.setVisible(true);
	}
	
	public void displayHand(ArrayList<Card> hand, JFrame frame) 
	{
		//Gives each card in hand an image label and adds to frame
		for(int x=(hand.size()-1); x>=0;x--) 
		{
			Card card = hand.get(x);
			ImageIcon cardImage = handGraphics(card);
			JLabel cardLabel = new JLabel(cardImage);
			cardLabel.setBounds(90 +(42*x), 546, 81,117);
			frame.add(cardLabel);
		}
	}
	
	public void displayTableSuit(ArrayList<Card> tableSuit, int suitIdentifier, JFrame frame)
	{ 
		ArrayList<Card> newSuit = new ArrayList<Card>();
		Hand hand = new Hand();
		newSuit = hand.orderHand(tableSuit);
		
		//Create image label for each card on table and adds to frame
		for(Card card :newSuit) 
			{
				ImageIcon tempImage = handGraphics(card);
				JLabel cardLabel = new JLabel(tempImage);
				cardLabel.setBounds(300 +(200*suitIdentifier), 246-(30*(card.getValue() - 7)), 81,117);
				frame.add(cardLabel);
			}
	}
	
	public ImageIcon handGraphics(Card card)
	{
			int value = 4;
			
			//Decide row in image to take subimage from, based on suit
			if(card.getSuit() == Suits.HEARTS) 
			{
				value = 0;
			}
			else if(card.getSuit() == Suits.DIAMONDS) 
			{
				value = 1;
			}
			else if(card.getSuit() == Suits.CLUBS) 
			{
				value = 2;
			}
			else if(card.getSuit() == Suits.SPADES) 
			{
				value = 3;
			}
			else if(value == 4) {
				System.out.println("Error");
			}
			
			//assign correct image to card
			ImageIcon cardImage = new ImageIcon(assignCardImage(card.getValue() - 2, value));
			
			return cardImage;		
	}
	
	
	
	public BufferedImage assignCardImage(int cardNumber, int cardSuit) 
	{
		int width = 81;
		int height = 117;
		
		//Load image of deck, extra steps so can be read in jar file
		ImageIcon tempCardDeck = new ImageIcon(getClass().getClassLoader().getResource("cardDeck.png"));
		
		BufferedImage cardDeck = new BufferedImage(tempCardDeck.getIconWidth(), tempCardDeck.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		
		Graphics g = cardDeck.createGraphics();
		//Paint the ImageIcon to the BufferedImage
		tempCardDeck.paintIcon(null, g, 0,0);
		g.dispose();
		
		//Create sub image for card
		BufferedImage cardImage = cardDeck.getSubimage(cardNumber*width, cardSuit*height, width, height);
		
		return cardImage;
	}
	
	public void showComputerHand(String comp, int compHandSize, int yPosition, JFrame frame) 
	{
		//Add hand image
		ImageIcon compHand = new ImageIcon(assignCardImage(0, 4));
		JLabel compHandLabel = new JLabel(compHand);
		compHandLabel.setBounds(1070, yPosition, 81, 117);
		frame.add(compHandLabel);
		
		//Add hand text
		JLabel compHandLabelText = new JLabel(comp+" hand, cards left: "+compHandSize);
		compHandLabelText.setBounds(1070,yPosition+117, 300, 30);
		frame.add(compHandLabelText);
	}
	
	public void displayWinner(ArrayList<Card> hand, String winner, JFrame frame) 
	{
		//Remove everything and display winner message
		frame.getContentPane().removeAll();
		String winnerDeclaration = winner+ " is the winner!";
		JLabel winningPlayer = new JLabel(winnerDeclaration, SwingConstants.CENTER);
		winningPlayer.setBounds(550, 250, 150, 30);
		frame.add(winningPlayer);
		
		frame.repaint();
		frame.setVisible(true);
	}

}
