import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.Point;
import java.util.*;

class NodesMin{
	int node=0;
	
	public void incr(){
		node+=1;
	}
	
	public void decr(){
		node-=1;
	}
	
	public int getNode(){
		return node;
	}
}

class State implements Cloneable
{

	private char[][] board = null;
	private static int nodes=0;
	
	int rows, cols;
	
	public Board b1 = new Board();

	
	public State(int n_rows, int n_cols){
		this.rows=n_rows;
		this.cols=n_cols;
		board = b1.getBoard();
		//this.nodes = 0;
		
	}
	
	public boolean equals(Object obj){
	
		State other=(State)obj;
		return Arrays.deepEquals(this.board, other.board);
	
	}
	

	public Object clone() throws CloneNotSupportedException {
        State new_state=new State(this.rows, this.cols);
		for (int i=0; i<this.rows; i++)
			new_state.board[i] = (char[]) this.board[i].clone();
		return new_state;
	}
	

	public ArrayList<Integer> getLegalActions(){
		//Nodes node = new Nodes();
		
		ArrayList<Integer> actions=new ArrayList<Integer>();
		
		for(int j=0; j<this.cols; j++){
			if(board[0][j]==' '){
				actions.add(j);
				//node.incr();
			}
				
		}
			
	//	nodes+= actions.size();
	//	System.out.println("Nodes in generate func= "+nodes);
		return actions;
	}
	
	
	public State generateSuccessor(char agent, int action) throws CloneNotSupportedException{
		
		int row;
		for(row=0; row<this.rows && this.board[row][action]!='X' && this.board[row][action]!='O'; row++);
		State new_state=(State)this.clone();
		new_state.board[row-1][action]=agent;
		return new_state;
	}
	
	
	public void printBoard(){
		
		b1.displayBoard();
	}
	
	/*public static int getNodes(){
		return nodes;
	}*/
	
	public boolean isGoal(char agent){
	
		return b1.isGoal(agent);
	}
	
	public char[][] getBoard(){
		
		return this.board;
	}
	
}

class minimaxAgent{
	
	int depth;
	int x=0;
	int nodes=0;
	private int func_no;
	
	public minimaxAgent(int depth)
	{
		this.depth = depth;
	}	
	
	public int getAction(State st) throws CloneNotSupportedException
	{
		double val = max_value(st, depth);
		return x;
		
	}
	
	public double max_value(State st, int d) throws CloneNotSupportedException
	{
		EvalFunctions e1 = new EvalFunctions();

		connect4AI_MinMax c1 = new connect4AI_MinMax();
		func_no = c1.getFunctionNo();
		
		ArrayList<Integer> children = new ArrayList<Integer>();
		if(d ==0)
		return e1.evaluateBoard(st.getBoard(),func_no);	//pass func no
		
		else{
		children = st.getLegalActions();
		//nodes += st.getNodes();
		double v = -10000000;
		
		double z;
		
		//System.out.println("Child size: "+children.size());
		
		for(int i =0; i<children.size();i++){
			nodes++;
			z = min_value(st.generateSuccessor('O',children.get(i)),d);
			if(z >= v){
				v =z;
				this.x = i;
			}
		}
		return v;
		}
	}
	
	public double min_value(State st, int d) throws CloneNotSupportedException
	{
		EvalFunctions e1 = new EvalFunctions();
		ArrayList<Integer> children = new ArrayList<Integer>();
		if(d == 0)
		return e1.evaluateBoard(st.getBoard(),func_no);  //pass eval fun no
		else
		{
		children = st.getLegalActions();
		//nodes += st.getNodes();
		
		double v = 10000000;
		int x=0;
		double z;
		//nodes = nodes+ children.size();
		for(int i =0; i<children.size();i++){
			
			z= max_value(st.generateSuccessor('X',children.get(i)),d-1);
			nodes++;
			if(z <= v)
				v=z;
		}
		
		return v;
		}
	}
	public int getNodes(){
		return nodes;
	}
}


public class connect4AI_MinMax{
	
	private static int eval_function_no;
	private static int nodes=0;
	
	public void setEvaluationFunction(int f1){
	  	eval_function_no = f1;
	}
	
	public int getFunctionNo(){
		return eval_function_no;
	}

	public static void minmaxAlgo() throws CloneNotSupportedException{
		Board b = new Board();
		//Nodes node = new Nodes();
		
		System.out.println("Enter the depth:");
		Scanner in = new Scanner(System.in);
		int depth = in.nextInt();

		//----------------- start of prog---------------------
		long startTime = System.currentTimeMillis();
		long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		//----------------------------------------------------
		minimaxAgent mma = new minimaxAgent(depth);
		State s=new State(6,7);
		while(true){
			int action = mma.getAction(s);
			
			//System.out.println("\nNodes = "+nodes);
			s = s.generateSuccessor('O', action);
			//nodes+=mma.getNodes();
			s.printBoard();      
	
			if(s.isGoal('O')){
				System.out.println("\nCongratulations! Player O Won this Game!");
				break;
			}
			
		
			int enemy_move = mma.getAction(s);
			s = s.generateSuccessor('X', enemy_move);
			//nodes+=mma.getNodes();
			s.printBoard();
		
			if(s.isGoal('X'))
			{
				System.out.println("\nCongratulations! Player X Won this Game!");
				break;
			}
			
			if(b.isFull()){
				System.out.println("\nGame Draw!");
				break;
			}
			
		}
		
		//---------------- end of prog-----------------------
		System.out.println("\nNodes = "+mma.getNodes());
		long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		long actualMemUsed=afterUsedMem-beforeUsedMem;
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("\nExecution Time: "+totalTime);
		//System.out.println("\nMemory Usage = "+actualMemUsed);
		//-----------------------------------------------
	}
	
}