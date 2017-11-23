import java.io.IOException;
import java.util.*;
import java.lang.*;

public class Interface {

	private static int eval;
	private static int algo;
	
	public static void main(String[] args) throws CloneNotSupportedException{
			
		char [][]board = new char[][]{
            {'X','X','0','X','X','X','0'},
            {'X',' ','0',' ',' ',' ',' '},
            {' ',' ','0',' ',' ',' ',' '},
            {' ',' ','0',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' '},
            {' ',' ',' ',' ',' ',' ',' '},    
        };
        
		//Scanner in = new Scanner(System.in);		
		long startTime=0000;
		//user_input();
		System.out.println("Score main: "+eval_function_three(board));
		
		/*System.out.println("\n1. MinMax Algorithm \t 2.Alpha-Beta Pruning \n");
		int algo = in.nextInt();
		System.out.println("\nPlease enter eval function no: (1/2): ");
		eval = in.nextInt();*/
		
		/*int nodes=0;
		
		try{
			if(algo==1){
				// startTime = System.currentTimeMillis();
				connect4AI_MinMax minMax = new connect4AI_MinMax();
				minMax.setEvaluationFunction(eval);
				minMax.minmaxAlgo();
				
				//nodes = minMax.getNodes();
				//System.out.println("\nNodes generated are:  \n"+nodes);
			}
			
			else if(algo==2){
				 startTime = System.currentTimeMillis();
				
				Connect4AI aB = new Connect4AI();
				aB.setEvaluationFunction(eval);
				aB.playAgainstAIConsole();		
				//nodes = aB.getNodes();
				
			}
			else{
				System.out.println("\nError! \n");
				//algo = in.nextInt();
			}
		}catch(Exception e){
			 e.printStackTrace();
		}*/
	/*	long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("\nExecution Time: "+totalTime);*/

	}
	
	
	public static void user_input(){
		//Scanner-> for accepting user input 
		Scanner in = new Scanner(System.in);	
		
		System.out.println("\nPlease choose the algorithm: \n");
		System.out.println("\n1. MinMax Algorithm \t 2.Alpha-Beta Pruning \n");
		algo = in.nextInt();
		
		while(algo!=1 && algo!=2){
			System.out.println("Please enter valid no..\n");
			System.out.println("\nPlease choose the algorithm:");
			System.out.println("\n1. MinMax Algorithm \t 2.Alpha-Beta Pruning \n");
			 algo = in.nextInt();
		}
		
		System.out.println("\nPlease enter eval function no: (1/2): \n");
		eval = in.nextInt();
		
		while(eval!=1 && eval!=2){
			System.out.println("Please enter valid no..\n");
			System.out.println("\nPlease enter eval function no: (1/2): ");
			eval = in.nextInt();
		}
	}
	
	public static void display_interface(){
		System.out.println("\n********************* WELCOME TO CONNECT-4 GAME **************************\n");
		System.out.println("\n.......Initialization............\n");
	}
	
	public static int eval_function_three(char[][] board){
		int valueX=1;
		int value0 = 1, value=0;
		int score=1,scoreX_H=0, score0_H=0;
		int m=5,c=10;
		
		
		//horizontal checking
		for(int i=0;i<6;i++){
			valueX=1; value0=1;
			for(int j=0;j<6;j++){
				
				if(board[i][j]=='X' && board[i][j]==board[i][j+1] && valueX<4){
					valueX++;
					scoreX_H += (int)Math.pow(m, valueX)+c;
					System.out.println("\nvalX: "+valueX+" score:"+scoreX_H);
					if(valueX==4){
						return scoreX_H;
					}
				}
				
				else if(board[i][j]=='0' && board[i][j]==board[i][j+1] && value0<4){
					value0++;
					score0_H += (int)Math.pow(m, value0)+c;
					score0_H*=-1;
					System.out.println("\nval0: "+value0+" score:"+score0_H);
					if(value0==4){
						return score0_H;
					}
				}
				
				else if(board[i][j+1]==' '){
					//score = 0;
					continue;
				}
				
				else if(board[i][j]!=board[i][j+1]){
					valueX=1;
					continue;
				}
				
			}
		}
		
		System.out.println("\nHori ScoreX: "+valueX);
		System.out.println("\nHori Score0: "+value0);
		
		//vertical checking
		/*valueX=1;
		value0 = 1;
		
				for(int j=0;j<7;j++){
					for(int i=0;i<5;i++){
						//if((board[i][j]=='X' && valueX==1) || board[i][j]!='X')
							//continue;
						
						if(board[i][j]=='X' && board[i][j]==board[i+1][j] && valueX<4){
							valueX++;
							score = (int)Math.pow(m, valueX)+c;
							System.out.println("\nvalX: "+valueX+" score:"+score);
							if(valueX==4){
								return score;
							}
						}
						
						else if(board[i][j]=='0' && board[i][j]==board[i+1][j] && value0<4){
							value0++;
							score = (int)Math.pow(m, value0)+c;
							score*=-1;
							System.out.println("\nval0: "+value0+" score:"+score);
							if(value0==4){
								return score;
							}
						}
						
						else if(board[i+1][j]==' '){
							//score = 0;
							continue;
						}
						
						else{
							score = 0;
							continue;
						}
						
						
					}
				}*/
		
		System.out.println("\nHori ScoreX: "+scoreX_H);
		System.out.println("\nHori Score0: "+score0_H);
		return score;
	}

}
