import java.util.Scanner;

class Nodes{
	int node=0;
	
	public void incr(){
		node+=1;
		System.out.println("Nodes incr = "+node);
	}
	
	public void decr(){
		node-=1;
	}
	
	public int getNode(){
		return node;
	}
}

public class Connect4AI { 
    private Board b;
    private Scanner scan;
    private int nextMoveLocation=-1;
    private int maxDepth = 3;   //change depth-> user input
    private int func_no;
    private int node=0;
    
    public Connect4AI(){
    	Board b = new Board();
        this.b = b;
        scan = new Scanner(System.in);
        node=1;
    }

    
    public int gameResult(Board b){
       int aiScore = 0, humanScore = 0;
        for(int i=5;i>=0;--i){
            for(int j=0;j<=6;++j){
                if(b.board[i][j]==' ') continue;
                
                //Checking cells to the right
                if(j<=3){
                    for(int k=0;k<4;++k){ 
                            if(b.board[i][j+k]=='X') aiScore++;
                            else if(b.board[i][j+k]=='0') humanScore++;
                            else break; 
                    }
                    if(aiScore==4)return 1; else if (humanScore==4)return 2;
                    aiScore = 0; humanScore = 0;
                } 
                
                //Checking cells up
                if(i>=3){
                    for(int k=0;k<4;++k){
                            if(b.board[i-k][j]=='X') aiScore++;
                            else if(b.board[i-k][j]=='0') humanScore++;
                            else break;
                    }
                    if(aiScore==4)return 1; else if (humanScore==4)return 2;
                    aiScore = 0; humanScore = 0;
                } 
                
                //Checking diagonal up-right
                if(j<=3 && i>= 3){
                    for(int k=0;k<4;++k){
                        if(b.board[i-k][j+k]=='X') aiScore++;
                        else if(b.board[i-k][j+k]=='0') humanScore++;
                        else break;
                    }
                    if(aiScore==4)return 1; else if (humanScore==4)return 2;
                    aiScore = 0; humanScore = 0;
                }
                
                //Checking diagonal up-left
                if(j>=3 && i>=3){
                    for(int k=0;k<4;++k){
                        if(b.board[i-k][j-k]=='X') aiScore++;
                        else if(b.board[i-k][j-k]=='0') humanScore++;
                        else break;
                    } 
                    if(aiScore==4)return 1; else if (humanScore==4)return 2;
                    aiScore = 0; humanScore = 0;
                }  
            }
        }
        
        for(int j=0;j<7;++j){
            //Game has not ended yet
            if(b.board[0][j]==' ')return -1;
        }
       
        return 0;
    }
    
    /*int calculateScore(int aiScore, int moreMoves){   
        int moveScore = 4 - moreMoves;
        if(aiScore==0)return 0;
        else if(aiScore==1)return 1*moveScore;
        else if(aiScore==2)return 10*moveScore;
        else if(aiScore==3)return 100*moveScore;
        else return 1000;
    }*/
    
    public void setEvaluationFunction(int f1){
    	func_no = f1;
    }
    
    
    public int evaluateBoard(Board b){
      
    	int score = 0;
    	
    	EvalFunctions e1 = new EvalFunctions();
    	
        score = e1.evaluateBoard(b.board, func_no);
    	
   
        return score;
    } 
    
    public int minimax(int depth, int turn, int alpha, int beta){
        
    	//Nodes node = new Nodes();
    	
        if(beta<=alpha){if(turn == 1) return Integer.MAX_VALUE; else return Integer.MIN_VALUE; }
        int gameResult = gameResult(b);
        
        if(gameResult==1)return Integer.MAX_VALUE/2;
        else if(gameResult==2)return Integer.MIN_VALUE/2;
        else if(gameResult==0)return 0; 
        
        if(depth==maxDepth)return evaluateBoard(b);
        
        int maxScore=Integer.MIN_VALUE, minScore = Integer.MAX_VALUE;
                
        for(int j=0;j<=6;++j){
            
            int currentScore = 0;
            
            if(!b.isLegalMove(j)) continue; 
            
            node++;
            
            if(turn==1){
                    b.placeMove(j, 'X');
                    currentScore = minimax(depth+1, 2, alpha, beta);
                    
                    if(depth==0){
                        System.out.println("Score for location "+j+" = "+currentScore);
                        if(currentScore > maxScore)nextMoveLocation = j; 
                        if(currentScore == Integer.MAX_VALUE/2){b.undoMove(j);break;}
                    }
                    
                    maxScore = Math.max(currentScore, maxScore);
                    
                    alpha = Math.max(currentScore, alpha);  
            } 
            else if(turn==2){
                    b.placeMove(j, '0');
                    currentScore = minimax(depth+1, 1, alpha, beta);
                    minScore = Math.min(currentScore, minScore);
                    
                    beta = Math.min(currentScore, beta); 
            }  
            b.undoMove(j);
            if(currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break; 
        }  
        return turn==1?maxScore:minScore;
    }
    
    public int getAIMove(){
        nextMoveLocation = -1;
       // node++;
        minimax(0, 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return nextMoveLocation;
    }
    
    
    
    public void playAgainstAIConsole(){
        
    	//Nodes node = new Nodes();
    	
        b.displayBoard();
        long startTime = System.currentTimeMillis();
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        b.placeMove(3, 'X');
        b.displayBoard();
        
        while(true){ 
        	b.placeMove(getAIMove(), '0'); 
            b.displayBoard();
            
            int gameResult = gameResult(b);
            if(gameResult==1){System.out.println("AI 1 Wins!");break;}
            else if(gameResult==2){System.out.println("AI 2 Win!");break;}
            else if(gameResult==0){System.out.println("Draw!");break;}
            
            b.placeMove(getAIMove(), 'X');
            b.displayBoard();
            gameResult = gameResult(b);
            if(gameResult==1){System.out.println("AI 1 Wins!");break;}
            else if(gameResult==2){System.out.println("AI 2 Win!");break;}
            else if(gameResult==0){System.out.println("Draw!");break;}
        }
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		long actualMemUsed=afterUsedMem-beforeUsedMem;
		System.out.println("\nMemory Usage = "+actualMemUsed);
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("\nExecution Time: "+totalTime);
		System.out.println("\nNodes Generated = "+node);
    }


	public int getNodes() {
		return 0;
	}
    
}