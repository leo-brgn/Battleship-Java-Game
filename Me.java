import java.util.Scanner;

public class Me {
	/**
	 * ATTRIBUTES
	 */
	private Scanner sc = new Scanner(System.in); 
	private int line; //Line to aim at
	private char column; //Column to aim at
	private boolean testColumn;

	/**
	 * CONSTRUCTOR
	 */
	public Me (){
		this.line = 0;
		this.column = 'A';
	}
	
	/**
	 * METHODS
	 */
	
	/**Method to ask which line to put the boat in */
	private void whichLineMe(){
		int tries=0;
		do{
			System.out.print ("line (from 1 to 10) :"); 
			this.line = sc.nextInt();
			if((this.line<=0)||(this.line>10) && (tries>1)){
				System.out.println("Sorry again, you may have enter a non-valid number...Please choose between:");
				for(int i=1; i<=10; i++){
					System.out.print(i + " ");
				}
				System.out.println();
				System.out.println("Let's do it one more time");
				tries++;				
			}else if((this.line<=0)||(this.line>10)){
				System.out.println("Sorry this is either too high or too low...Let's do it again! ");
				tries++;
			}
		} while((this.line<=0)||(this.line>10));
	}
	/**Method to ask which column to put the boat in */
	private void whichColumnMe(){
		int tries=0;
		do{	
			System.out.print("column (from A to J) :"); 
			this.column = sc.next().charAt(0); //Records the input of a character of the keyboard
			this.testColumn = (this.column!='A')&&(this.column!='B')&&(this.column!='C')&&(this.column!='D')&&(this.column!='E')&&(this.column!='F')&&(this.column!='G')&&(this.column!='H')&&(this.column!='I')&&(this.column!='J');

			if(testColumn && (tries>0)){
				System.out.println("Sorry again, this entry is still incorrect...\nWrite in capital letter either:\nA  B  C  D  E  F  G  H  I  J\nLet's do it again!");
				tries++;
			}else if(testColumn){
				System.out.println("Sorry this entry is incorrect...Let's do it again! ");
				tries++;
			}
		} while(testColumn);
		this.testColumn = true;
	}
	
	/**Method for the player to play the game */
	public void plays(StrategyGrid tacticalGrid, PrivateGrid privateArray){
		
		/**display the strategy grid at the beginning of the player's turn (to know what the player has already targeted*/
		tacticalGrid.displayStrategy();
		
		/**Ask for the line and column the player wants to target*/
		System.out.println("Which cell do you want to target?");
		whichColumnMe();
		whichLineMe();
		System.out.println(""); 
		
		/**Convert the (char) column into an int*/
		int transformation = this.column;
		transformation = transformation - 65;
		
		/**Apply this targeting point (see hit method in StrategyGrid)*/
		tacticalGrid.hit(this.line,transformation);
		
		/**Display the strategy grid at the end of the player's turn to let the player know if he has missed/touched/sunk */
		tacticalGrid.displayStrategy();
	}
}
