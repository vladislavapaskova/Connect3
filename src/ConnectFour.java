import java.util.ArrayList;
import java.util.List;


public class ConnectFour {

	public Node game;
	public State state;
	int col; 
	
public ConnectFour(Node previous, int colToPut)
{
	this.state = new State(previous.state.move(colToPut)); 
	this.game = new Node(state);
	this.col=colToPut; 
	
}

public ConnectFour(Node previous, String AI)
{
	Node newGame = previous.maxDecision().get(0);
	if(newGame==null)
	{
		newGame=previous.expand().get(0);
	}
	this.game = newGame; 
	this.state = newGame.state; 
}

public ConnectFour(Node game)
{
	this.game=game;
	this.state=game.state;
	
}

/*
	public static void main(String[] args) {
		
		Node first=new Node(); 
		System.out.println("Values for minimaxValue and the state space without alpha beta pruning");
		System.out.println(first.minimaxValue());
		System.out.println(first.minimaxCache.size());
		
		System.out.println("\n"+"-----------------------------------");
		
		System.out.println("Values for minimaxValue and the state space with alpha beta pruning");
		Node second = new Node(); 
		System.out.println(second.minimaxValueAB(Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println(second.minimaxCacheAB.size());
		
		System.out.println("\n"+"-----------------------------------");
		

		Node third = new Node(); 
		ArrayList<Node> listN=third.maxDecision();
		
		for(int i=0; i<listN.size();i++)
		{
			System.out.println("The best states to choose for Player 1 on an empty board:");
			System.out.println(listN.get(i).state.toString()+"\n");
			System.out.println("Player 2's response to Player 1 game:(if no respose is outputted then the game was unable to find a path, in which Player 2 wins");
			ArrayList<Node> listR=listN.get(i).minDecision();
			for(int k=0; k<listR.size();k++)
			{
				System.out.println(listR.get(k).state.toString()+"\n");
			}
		}

		System.out.println("\n"+"-----------------------------------");
		
		int[][] board=new int[4][5];
		board[3][0]=1;
		board[3][1]=1;
		board[3][2]=2;
		board[2][1]=1;
		board[1][1]=2;
		//board[2][2]=2;
		int whoseturn=2;
		State newS = new State(board, whoseturn);
		Node fourth = new Node(newS);
		ArrayList<Node> listN3=fourth.minDecision();
		
		for(int i=0; i<listN3.size();i++)
		{
			System.out.println("Solution for 1d:");
			System.out.println(listN3.get(i).state.toString()+"\n");
		}
		
	}
	*/
}