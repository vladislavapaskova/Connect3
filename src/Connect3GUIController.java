import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.Keymap;

public class Connect3GUIController extends JPanel implements KeyListener{
	
	/*
	 * Instance Fields
	 */
	//create an instance of a Timer
	private Timer timer; 
	
	//create an instance of the timer box
	private JTextField timerTextField;
	//create a field for thescore
	private JTextField scoreTextField; 
	//create a field for the tetrises
	private JTextField tetrisesTextField; 
	
	//create an instance field of the game
	ConnectFour game;
	//create an instance field of the view
	Connect3GUIBoard view; 
	//create an instance var of the small panel that is going to contain the timer score and tetrises
	private JPanel upPanel; 
	
	/*
	 * Constructor
	 */
	public Connect3GUIController()
	{
		Node node = new Node(); 
		
		// create a tetris game
		game= new ConnectFour(node); 
		// create its view
		view= new Connect3GUIBoard(game);
		//call the function that makes the main panel
		makeMainPanel();
		
		this.setFocusable(true);
		this.addKeyListener(this);
	}
	//create a function to show the whole panel
	public void makeMainPanel()
	{
		//set the layout as border layout
		this.setLayout(new BorderLayout());
		//add theup panel
		this.add(makeUpPanel(), BorderLayout.NORTH); 
		//add the board to the center
		this.add(view, BorderLayout.CENTER); 
		 
	}
	
	//create a function that is going to return the upPanel
	public JPanel makeUpPanel()
	{
		//create a new instance of the panel
		upPanel = new JPanel();
		//set the layout as grid
		upPanel.setLayout(new GridLayout(3,1));
		//add timer
		timerTextField= new JTextField("0000");
		timerTextField.setEditable(false);
		//set up tumer
		setUpTimer(); 
		//add the timer
		upPanel.add(timerTextField); 
		
		//return panel
		return upPanel; 
	}
	
	//create a function that starts the timer	
	public void setUpTimer()
	{
			
	//delay in milliseconds
	int delay = 1000;
	
	//create an action Listener
	ActionListener taskPerformed = new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//call addSeconds
			addSeconds(); 

		}
	};

	//add the new timer instance
	timer= new Timer(delay, taskPerformed);
	//start the zero 
	timer.start(); 
	}
		
		//create a function that is going to add seconds 
		public void addSeconds()
		{
			//create a string variable to store the number of seconds
			String numSeconds; 
			//take the number of seconds from the textfield
			numSeconds=timerTextField.getText(); 
			//create an integer variable to add the new second to it 
			int intNumSeconds = Integer.parseInt(numSeconds); 
			intNumSeconds= intNumSeconds + 1;
			//add the second to the textfield
			timerTextField.setText(Integer.toString(intNumSeconds)); 
			//setScore(); 
			
		}
		//create a function that ends the timer
		public void endTimer()
		{
			//stop the timer
			timer.stop(); 
		}
	
	
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
        ConnectFour newG;
        Connect3GUIBoard newV; 
		// TODO Auto-generated method stub
		// different behavior depending on which key was released
        switch( e.getKeyCode() )
        {
 
	        // if it's the right key
	        case KeyEvent.VK_1:

	        newG= new ConnectFour(game.node, 0);
	        game=newG; 	        
	        view.game=newG; 
	        view.repaint();
		    //refresh
		    refreshDisplay();
	        break;
	        
	        // if it's the left key
	        case KeyEvent.VK_2:

		    newG= new ConnectFour(game.node, 1);
		    game=newG; 	        
		    view.game=newG; 
		    view.repaint();
		    //refresh
		    refreshDisplay();; 
	        break;
	        	
	        case KeyEvent.VK_3:

			newG= new ConnectFour(game.node, 1);
			game=newG; 	        
			view.game=newG; 
			view.repaint();
		    //refresh
		    refreshDisplay();
	        break;
	        
	        case KeyEvent.VK_4:

			newG= new ConnectFour(game.node, 1);
			game=newG; 	        
			view.game=newG; 
			view.repaint();
		    //refresh
		    refreshDisplay();
		    break;
		    
	        case KeyEvent.VK_5:

			newG= new ConnectFour(game.node, 1);
			game=newG; 	        
			view.game=newG; 
			view.repaint();
		    //refresh
		    refreshDisplay();
		    break;		    
	        
	        default:
                System.out.println("KEY PRESSED: " + e.getKeyCode() );
        }
	}
	//refresh the display
	private void refreshDisplay() 
	{
		//this is going to be the AI's response
		ConnectFour newG = new ConnectFour(game.node, "AI");
	    view.game=newG;
		//call repaint
	    view.repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
