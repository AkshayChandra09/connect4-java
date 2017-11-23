import java.awt.Point;
import java.util.ArrayList;

public class EvalFunctions {
	 
	 Board current_board;
	 int rows = 6;
		int cols = 7;
	 int nodes=0;
	 
	 public int evaluateBoard(char[][] b, int f_no){
		 int score=0;
		
		 //score = function1(b);
		 
		 if(f_no==1){		
			 
			 score = eval_function_one(b);
		 }
		 else{
			 score = eval_function_two(b);
		 }
		 
		 return score;
	 }
	 
	 int eval_function_one(char[][] b){
		 int aiScore=1;
	        int score=0;
	        int blanks = 0;
	        int k=0, moreMoves=0;
	        for(int i=5;i>=0;--i){
	            for(int j=0;j<=6;++j){
	                
	                if(b[i][j]==0 || b[i][j]==2) continue; 
	                
	                if(j<=3){ 
	                    for(k=1;k<4;++k){
	                        if(b[i][j+k]==1)aiScore++;
	                 
	                        else if(b[i][j+k]==2){aiScore=0;blanks = 0;break;}
	                        else blanks++;
	                    }
	                     
	                    moreMoves = 0; 
	                    if(blanks>0) 
	                        for(int c=1;c<4;++c){
	                            int column = j+c;
	                            for(int m=i; m<= 5;m++){
	                             if(b[m][column]==0)moreMoves++;
	                                else break;
	                            } 
	                        } 
	                    
	                    if(moreMoves!=0) score += calculateScore(aiScore, moreMoves);
	                    //if(moreMoves!=0) score += calculateScore2(aiScore, moreMoves);
	                    aiScore=1;   
	                    blanks = 0;
	                } 
	                
	                if(i>=3){
	                    for(k=1;k<4;++k){
	                        if(b[i-k][j]==1)aiScore++;
	                        else if(b[i-k][j]==2){aiScore=0;break;} 
	                    } 
	                    moreMoves = 0; 
	                    
	                    if(aiScore>0){
	                        int column = j;
	                        for(int m=i-k+1; m<=i-1;m++){
	                         if(b[m][column]==0)moreMoves++;
	                            else break;
	                        }  
	                    }
	                    if(moreMoves!=0) score += calculateScore(aiScore, moreMoves);
	                    //if(moreMoves!=0) score += calculateScore2(aiScore, moreMoves);
	                    aiScore=1;  
	                    blanks = 0;
	                }
	                 
	                if(j>=3){
	                    for(k=1;k<4;++k){
	                        if(b[i][j-k]==1)aiScore++;
	                        else if(b[i][j-k]==2){aiScore=0; blanks=0;break;}
	                        else blanks++;
	                    }
	                    moreMoves=0;
	                    if(blanks>0) 
	                        for(int c=1;c<4;++c){
	                            int column = j- c;
	                            for(int m=i; m<= 5;m++){
	                             if(b[m][column]==0)moreMoves++;
	                                else break;
	                            } 
	                        } 
	                    
	                    if(moreMoves!=0) score += calculateScore(aiScore, moreMoves);
	                    //if(moreMoves!=0) score += calculateScore2(aiScore, moreMoves);
	                    aiScore=1; 
	                    blanks = 0;
	                }
	                 
	                if(j<=3 && i>=3){
	                    for(k=1;k<4;++k){
	                        if(b[i-k][j+k]==1)aiScore++;
	                        else if(b[i-k][j+k]==2){aiScore=0;blanks=0;break;}
	                        else blanks++;                        
	                    }
	                    moreMoves=0;
	                    if(blanks>0){
	                        for(int c=1;c<4;++c){
	                            int column = j+c, row = i-c;
	                            for(int m=row;m<=5;++m){
	                                if(b[m][column]==0)moreMoves++;
	                                else if(b[m][column]==1);
	                                else break;
	                            }
	                        } 
	                        if(moreMoves!=0) score += calculateScore(aiScore, moreMoves);
	                        //if(moreMoves!=0) score += calculateScore2(aiScore, moreMoves);
	                        aiScore=1;
	                        blanks = 0;
	                    }
	                }
	                 
	                if(i>=3 && j>=3){
	                    for(k=1;k<4;++k){
	                        if(b[i-k][j-k]==1)aiScore++;
	                        else if(b[i-k][j-k]==2){aiScore=0;blanks=0;break;}
	                        else blanks++;                        
	                    }
	                    moreMoves=0;
	                    if(blanks>0){
	                        for(int c=1;c<4;++c){
	                            int column = j-c, row = i-c;
	                            for(int m=row;m<=5;++m){
	                                if(b[m][column]==0)moreMoves++;
	                                else if(b[m][column]==1);
	                                else break;
	                            }
	                        } 
	                        if(moreMoves!=0) score += calculateScore(aiScore, moreMoves);
	                        //if(moreMoves!=0) score += calculateScore2(aiScore, moreMoves);
	                        aiScore=1;
	                        blanks = 0;
	                    }
	                } 
	            }
	        }
	        return score;
	 }
	 
	 int calculateScore(int aiScore, int moreMoves){   
	        int moveScore = 4 - moreMoves;
	        if(aiScore==0)return 0;
	        else if(aiScore==1)return 1*moveScore;
	        else if(aiScore==2)return 10*moveScore;
	        else if(aiScore==3)return 100*moveScore;
	        else return 1000;
	    }
	 
	 
	/* public static int function2(char[][] b1){
		 //Rows
		 int row=0;
		 int m=5;
		 int count = 1;
		 
		 for(int i=0;i<7;i++){		 
			 if(b1[row][i] == b1[row][i+1]){
				 count++;
			 }
		 }
		 
		 //Columns
		 
		 System.out.println("Count is: "+ count);
		 
		 //left diagonal
		 
		 
		 //Right diagonal
		 
		 return count;
	 }
	 */
	 /*From connect4AI_MinMax*/
	 
		public boolean isGoal(char agent, char[][]board){
			
			
			String find=""+agent+""+agent+""+agent+""+agent;
			
			//check rows
			for(int i=0; i<this.rows; i++)
				if(String.valueOf(board[i]).contains(find))
					return true;
			
			//check cols
			for(int j=0; j<this.cols; j++){
				String col="";
				for(int i=0; i<this.rows; i++)
					col+=board[i][j];
					
				if(col.contains(find))
					return true;
			}
			
			//check diags
			ArrayList<Point> pos_right=new ArrayList<Point>();
			ArrayList<Point> pos_left=new ArrayList<Point>();
			
			for(int j=0; j<this.cols-4+1; j++)
				pos_right.add(new Point(0,j));
			for(int j=4-1; j<this.cols; j++)
				pos_left.add(new Point(0,j));	
			for(int i=1; i<this.rows-4+1; i++){
				pos_right.add(new Point(i,0));
				pos_left.add(new Point(i,this.cols-1));
			}
		
			//check right diags
			for (Point p : pos_right) {
				String d="";
				int x=p.x, y=p.y;
				while(true){				
					if (x>=this.rows||y>=this.cols)
						break;
					d+=board[x][y];
					x+=1; y+=1;
				}
				if(d.contains(find))
					return true;
			}
			
			//check left diags
			for (Point p : pos_left) {
				String d="";
				int x=p.x, y=p.y;
				while(true){
					if(y<0||x>=this.rows||y>=this.cols)
						break;
					d+=board[x][y];
					x+=1; y-=1;
				}
				if(d.contains(find))
					return true;
			}
			
			return false;
			
		}
		
		

		
		public int eval_function_two(char[][] board){    //evaluationFunction
		 
			if (isGoal('X',board)){   //0
				return 1000;
			}
				
			if (isGoal('0',board)){
				return -1000;
		     }
				
			return 0;
		}
		
		/*Evaluation function extra*/
		
}
