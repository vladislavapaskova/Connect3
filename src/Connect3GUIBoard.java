
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
	
public class Connect3GUIBoard extends JComponent{

public ConnectFour game; 
		
public Connect3GUIBoard(ConnectFour passedGame)
{
	 this.game=passedGame; 
}
		
public void paint(Graphics g){
	System.out.println("paint method");
	paintComponent(g);}
		
//create a function that is going to paint the tetris pieces
public void paintComponent(Graphics g)
{
int[][] gameBoard= game.state.board; 
//call the boardHasBlock method to check which tiles of the board hold pieces
//create a for loop to go through the board
	for(int i=0; i<4; i++)
	{
		for(int j=0; j<5; j++)
			{
			//check if it has a block 
			if(gameBoard[i][j]==1)
			{
				//set the color of the inside of the piece
				g.setColor(Color.YELLOW); 
				//fill the tile
				g.fillRect(i*getWidth()/10, j*getHeight()/18, getWidth()/10, getHeight()/18); 
				//set the color of the border of the box
				g.setColor(Color.BLACK); 
				//draw it
				g.drawRect(i*getWidth()/10, j*getHeight()/18, getWidth()/10, getHeight()/18); 
			}
			else if (gameBoard[i][j]==2)
			{
				//set the color of the inside of the piece
				g.setColor(Color.RED); 
				//fill the tile
				g.fillRect(i*getWidth()/10, j*getHeight()/18, getWidth()/10, getHeight()/18); 
				//set the color of the border of the box
				g.setColor(Color.BLACK); 
				//draw it
				g.drawRect(i*getWidth()/10, j*getHeight()/18, getWidth()/10, getHeight()/18); 
			}
			else
			{
				//set the color of the inside of the empty tile
				g.setColor(Color.BLUE); 
				//fill the tile
				g.fillRect(0,  0, getWidth(), getHeight()); 
				//draw it
				g.drawRect(0,  0, getWidth(),getHeight()); 
			}
		}

	}
}
	
}
