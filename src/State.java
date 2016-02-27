class State {	
	int[][] board; //the main game board
	int whoseturn; //the player whose turn it is. you can use {1 and 2} or {1 and -1}
	
	public State() {
		//Basic constructor
		board = new int[4][5];
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<5; j++)
			{
				board[i][j]=0;
			}
		}
		/*
		board[3][4]=1; 
		board[3][3]=2; 
		board[3][2]=1; 
		board[3][1]=2;
		board[3][0]=1;
		
		
		board[2][4]=1; 
		board[2][3]=2; 
		board[2][2]=1; 
		board[2][1]=2;
		board[2][0]=1;
		*/
		
		whoseturn = 1;
	}
	
	public State(int[][] board, int whoseturn) {
		//Normal constructor
		this.board=board; 
		this.whoseturn=whoseturn; 
	}
	
	public State(State tocopy) {
		//_DEEP_ COPY CONSTRUCTOR

		//if deep copy vs shallow copy doesn't mean anything to you, come talk to me.
		int[][] newboard=new int[4][5];
		int newwhoseturn=tocopy.whoseturn;
		
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<5; j++)
			{
				newboard[i][j]=tocopy.board[i][j];
			}
		}
		
		this.board=newboard; 
		this.whoseturn=newwhoseturn; 
	}
	
	public String toString() {
		//construct a string representation of the state
		//obviously the board should be included, but it will also be helpful to say
		//whose turn it is
		String stateString="Turn: "+whoseturn+"\n";
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<5; j++)
			{
				stateString+=board[i][j];
			}
			stateString+="\n";
		}
		return stateString; 
	}
	
	public State move(int col) {
		//return the state resulting from having the current player drop a piece in column col
		//I recommend implementing this as a nondestructive function.
		State newState=new State(this);
		
		if(whoseturn%2==0)
		{
			newState.whoseturn=1; 
		}
		else
			newState.whoseturn=2;
		
		for(int i=3; i>=0; i--)
		{
			if(newState.board[i][col]==0)
			{
				newState.board[i][col]=this.whoseturn; 
				break;
			}
		}

		
		//System.out.println(newState.toString());
		return newState;
	}
	
	public boolean isTerminal() {
		//return true if the game is over

		//note this can be combined with the Utility function below by returning null
		//as the utility if the game is not over
		if((checkVertical()!=0)||(checkHorizontal()!=0)||(checkDiag()!=0)||(checkBoardFull()))
		{
			return true;

		}
		else 
		{
			//System.out.println("we are at a non-terminal node");
			return false; 
		}
	}

	public Integer utility() {
		//compute the utility for player 1, which should be 1 if player 1 wins, -1 if player 1 loses, 0 if it is a draw
		if(checkBoardFull()&&(checkVertical()==0)&&(checkHorizontal()==0)&&(checkDiag()==0))
			return 0; 
		else if((checkVertical()==1)||(checkHorizontal()==1)||(checkDiag()==1))
		{
			return 1; 
		}
		else
			return -1; 
	}
	
	/*
	 * Check if somebody won 
	 */
	public boolean checkBoardFull()
	{
		int count =0; 

		//System.out.println(toString());
		for(int i=0; i<4; i++)
		{
			for(int j=0; j<5; j++)
			{
				if(board[i][j]==0)
					{
						count++;  
					}
			}
		}
		if(count==0)
		{
			//System.out.println("the board is full");
			return true; 
		}
		else
			return false; 
	}
	public int checkVertical()
	{
		for (int row = 0; row < 4; row++) 
		{
            for (int col = 2; col < 5; col++) 
            {
                if (board[row][col-2] ==1 && board[row][col-1]==board[row][col-2]&& board[row][col-2]==board[row][col])
                {
                	//System.out.println("Player 1 wins vertical");
                	return 1; 
                }
                else if(board[row][col-2] ==2 && board[row][col-1]==board[row][col-2]&& board[row][col-2]==board[row][col])
                {
                	//System.out.println("Player 2 wins vertical");
                	return 2;
                }
            }
        }
		return 0; 
	}
	public int checkHorizontal()
	{
		for (int col = 0; col < 5; col++) 
		{
            for (int row = 2; row < 4; row++) 
            {
                if (board[row-2][col] ==1 && board[row-1][col]==board[row-2][col]&& board[row-2][col]==board[row][col])
                {
                	//System.out.println("Player 1 wins horizontal");
                	return 1;
                }
                else if((board[row-2][col] ==1 && board[row-1][col]==board[row-2][col]&& board[row-2][col]==board[row][col]))
                {
                	//System.out.println("Player 2 wins horizontal");
                	return 2; 
                }
            }
        }
		return 0; 
	}
	public int checkDiag()
	{
		for (int row = 0; row < 4; row++) {
            for (int col = 2; col< 5; col++) {
                if (col+row>=4)
                {
                	break;
                }
                if (board[row+col][col]==1 && board[row+col-1][col-1] == board[row+col][col] && board[row+col-1][col-1] == board[row+col-2][col-2])
                {
                	//System.out.println("Player 1 wins diag");
                	return 1; 
                }
                else if (board[row+col][col]==2 && board[row+col-1][col-1] == board[row+col][col] && board[row+col-1][col-1] == board[row+col-2][col-2])
                {
                	//System.out.println("Player 2 wins diag");
                	return 2;
                }

            }
        }
		
		for (int col = 0; col < 5; col++) 
		{
            for (int row = 2; row < 4; row++) 
            {
            	if (col+row>=5)
                {
                	break;
                }
                if (board[row][col+row]==1 && board[row-1][col+row-1]==board[row][col+row]&& board[row-1][col+row-1]==board[row-2][col+row-2])
                {
                	//System.out.println("Player 1 wins diag");
                	return 1;
                }
                else if (board[row][col+row]==2 && board[row-1][col+row-1]==board[row][col+row]&& board[row-1][col+row-1]==board[row-2][col+row-2])
                {
                	//System.out.println("Player 2 wins diag");
                	return 2;
                }
            }
        }
		
        for (int col = 0; col < 5; col++) 
        {
            for (int row = 2; row < 4; row++)
            {
                if (col-row < 0) break;
                if (board[row][col-row]==1 && board[row-1][col-row+1]==board[row][col-row]&& board[row-1][col-row+1]==board[row-2][col-row+2])
                {
                	//System.out.println("Player 1 wins diag");
                	return 1;
                }
                else if (board[row][col-row]==2 && board[row-1][col-row+1]==board[row][col-row]&& board[row-1][col-row+1]==board[row-2][col-row+2])
                {
                	//System.out.println("Player 2 wins diag");
                	return 2;
                }
            }
        }
		
        for (int row = 0; row < 4; row++) 
        {
            for (int col=4; col >= 2; col--) {
                if (col-row <0||col-row>1) break;
                if (board[col-row][col] == 1 && board[col-row+1][col-1]==board[col-row][col]&& board[col-row+1][col-1]==board[col-row+2][col-2])
                {
                	//System.out.println("Player 1 wins diag");
                	return 1;
                }
                else if (board[col-row][col] == 2 && board[col-row+1][col-1]==board[col-row][col]&& board[col-row+1][col-1]==board[col-row+2][col-2])
                {
                	//System.out.println("Player 2 wins diag");
                	return 2;
                }
            }
        }
		
		return 0; 
	}
	public boolean checkifFullCol(int col)
	{
		for(int i=3; i>=0; i--)
		{
			if(board[i][col]==0)
			{
				return true; 
			}
		}
		return false; 
	}
}

