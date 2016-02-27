import javax.swing.JFrame;

public class Connect3GUIApplication 
{
	/**
	 * Start the game!
	 * @param args
	 */
	public static void main( String[] args )
	{
		
		// create a new JFrame to hold a new controller instance
		JFrame connectFrame = new JFrame("Connect 3");
		
		// set size
		connectFrame.setSize( 400, 800 );

		// make a new controller instance and add it
		connectFrame.add( new Connect3GUIController( ) );

		// exit normally on closing the window
		connectFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		connectFrame.setVisible( true );		
	}
	
}
