package techtest.sevensGame.sevensGame;

public class Card 
{

	private int value;
	private Suits suit;
	
	public Card(int value, Suits suit) 
	{
		this.value = value;
		this.suit =suit;
	}

	
	public int getValue() 
	{
		return value;
	}

	public void setValue(int value) 
	{
		this.value = value;
	}	
	

	public Suits getSuit() 
	{
		return suit;
	}


	public void setSuit(Suits suit) 
	{
		this.suit = suit;
	}

	@Override
	public String toString() 
	{
		return "Card [value=" + value + ", suit=" + suit + "]";
	}
	
	

}
