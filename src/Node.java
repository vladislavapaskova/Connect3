import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Node {
	Node parent; //a pointer to the parent node of this one
				 //you may also like to keep a cache of all the successors of this node
	State state; //the state represented at this node
	int totaldepth;   //the depth from the root node
	Integer best; 
	int count =0;
	
	//if you do the dynamic programming thing, which I recommend, you'll want something like this:
	//I'm happy to help you write this part in office hours
	static Map<String, Integer> minimaxCache = new LinkedHashMap<String, Integer>();
	static ArrayList <Integer> list = new ArrayList<Integer>();
	
	static Map<String, Integer> minimaxCacheAB = new LinkedHashMap<String, Integer>();
	static ArrayList <Integer> listAB = new ArrayList<Integer>();
	
	public Node() {
		//Basic constructor
		this.parent = null;
		this.state = new State();
		//this.depth = 0;
	}
	
	public Node(State state) {
		//Basic constructor for a root node
		this.state=state;
	}
	
	public Node(Node parent, State state) {
		//Basic constructor for an interior node
		this.parent=parent;
		this.state=state;
	}
	
	public String toString() {
		//basically print out the depth of the node, and then print out the state
		String print=""; 
		
		return print;
	}
	
	public List<Node> expand() {
		//generate a list of all the successor nodes from this node
		//by applying every possible legal move to the state
		List<Node> list = new ArrayList(); 
		for(int i=0; i<5; i++)
		{
			if(state.checkifFullCol(i))
			{
				Node newnode=new Node(this,state.move(i));
				//System.out.println(n.state.move(i));
				list.add(newnode);
			}
		}		
		return list; 
	}
	
	/*
	public int getBest()
	{
		return best; 
	}
	public void setBest(int i)
	{
		best=i; 
	}
	*/
	public int minimaxValue() {
		//the meat of the assignment
		
		//I _strongly_ recommend implementing this recursively
		//to compute the minimax value of a node, you:
		//    just return the utility if the state at this node is terminal
		//    return the min or max of the minimaxValue for each of this node's successors, if there are any
		int current=0;

		if(count==0)
		{
			//System.out.println("count is zero");
			if(this.state.whoseturn==1)
			{
				best=Integer.MIN_VALUE; 
				
			}
			else
			{
				best=Integer.MAX_VALUE;
			}
			count++;
		}
		//System.out.println("________________________");
		//System.out.println(this.state.toString());
		
		//System.out.println(minimaxCache.size());
		
		/*
		 * here you check for the cache and if it is inside then just return this value
		 */
		if(minimaxCache.containsKey(this.state.toString()))
		{
			//System.out.println("we are in minimax cache");
			return minimaxCache.get(this.state.toString()); 
		}
		else if(this.state.isTerminal())
		{
			//System.out.println("we are in terminal");
			int ut=this.state.utility();
			minimaxCache.put(this.state.toString(),ut);
			list.add(depth(this));
			return ut; 
		}
		else
		{
			//System.out.println("we are in internal");
			List<Node> l= expand();
			//System.out.println("Size of the expansion: "+l.size());
			for(int i=0; i<l.size(); i++)
			{
				//System.out.println(l.get(i).state.toString());
				totaldepth+=depth(l.get(i)); 
				current=l.get(i).minimaxValue();
				//System.out.println("current:"+current);
				//System.out.println("best before"+best);
				
				if(this.state.whoseturn==1)
				{
					
					if(current>best)
					{
						
						best=current;
						//System.out.println("best:"+best);
						count++; 
						
					}
				}
				else
				{
					if(current<best)
					{
						//System.out.println("current inside 2:"+current);
						best=current;
						//System.out.println("best:"+best);
						count++; 
					}
				}
				minimaxCache.put(this.state.toString(), best);
				//list.add(depth(this));
			}
			return best;
		}
		
	}
	
	public ArrayList<Node> maxDecision()
	{
		List<Node> list = expand();
		ArrayList<Node> listN=new ArrayList<Node>();

		int current; 
		for(int i=0; i<list.size();i++)
		{
			
			current=list.get(i).minimaxValue();
			if(current==1)
			{
				listN.add(list.get(i)); 
			}	
		}
		return listN; 
	}
	
	public ArrayList<Node> minDecision()
	{
		List<Node> list = expand();

		ArrayList<Node> listN=new ArrayList<Node>();

		int current; 
		for(int i=0; i<list.size();i++)
		{
			
			current=list.get(i).minimaxValue(); 
			if(current==-1)
			{
				//System.out.println(list.get(i).state.toString());
				listN.add(list.get(i));
			}
		}
		return listN; 
	}
	
	public int minimaxValueAB(int alpha, int beta) {
		//the meat of the assignment
		
		//I _strongly_ recommend implementing this recursively
		//to compute the minimax value of a node, you:
		//    just return the utility if the state at this node is terminal
		//    return the min or max of the minimaxValue for each of this node's successors, if there are any
		int current=0;

		if(count==0)
		{
			//System.out.println("count is zero");
			if(this.state.whoseturn==1)
			{
				best=Integer.MIN_VALUE; 
				
			}
			else
			{
				best=Integer.MAX_VALUE;
			}
			count++;
		}
		//System.out.println("________________________");
		//System.out.println(this.state.toString());
		
		//System.out.println(minimaxCache.size());
		
		/*
		 * here you check for the cache and if it is inside then just return this value
		 */
		if(minimaxCacheAB.containsKey(this.state.toString()))
		{
			//System.out.println("we are in minimax cache");
			return minimaxCacheAB.get(this.state.toString()); 
		}
		else if(this.state.isTerminal())
		{
			//System.out.println("we are in terminal");
			int ut=this.state.utility();
			minimaxCacheAB.put(this.state.toString(),ut);
			listAB.add(depth(this));
			return ut; 
		}
		else
		{
			//System.out.println("we are in internal");
			List<Node> l= expand();
			//System.out.println("Size of the expansion: "+l.size());
			for(int i=0; i<l.size(); i++)
			{
				//System.out.println(l.get(i).state.toString());
				totaldepth+=depth(l.get(i)); 
				current=l.get(i).minimaxValueAB(alpha, beta);
				//System.out.println("current:"+current);
				//System.out.println("best before"+best);
				
				if(this.state.whoseturn==1)
				{
					
					if(current>best)
					{
						
						best=current;
						//System.out.println("best:"+best);
						count++; 
						
					}
					
					//alpha
					if(best>=beta)
					{
						return best; 
					}
					
					alpha=Math.max(alpha,current);
				}
				else
				{
					if(current<best)
					{
						best=current;
						//System.out.println("best:"+best);
						count++; 
					}
					
					if(best<=beta)
					{
						return best; 
					}
					
					alpha=Math.min(alpha,current);
				}
				minimaxCacheAB.put(this.state.toString(), best);
				//list.add(depth(this));
			}
			
			
			return best;
		}
		
	}
	
	
	public int depth(Node n) 
	{
		int count = 0;
		while (n.parent != null) {
			n = n.parent;
			count++;
		}
		return count;
	}
	
	public int TotalDepth()
	{
		for(int i=0; i<list.size(); i++)
		{
			totaldepth+=list.get(i);
		}
		return totaldepth; 
	}
}