package techtest.sevensGame.sevensGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseLogic extends MouseAdapter
{
	Player player;
	
	public MouseLogic(Player player) 
	{
		this.player =player;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		//Coordinates of click
		int xPos = e.getX();
		int yPos = e.getY();
		
		player.checkSelectedCardAndPlay(xPos, yPos);
	}
	
	
}
