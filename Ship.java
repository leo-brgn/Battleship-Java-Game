import java.util.Scanner;

public class Ship {

	/**
	 * ATTRIBUTES
	 */

	private	Scanner sc = new Scanner(System.in);
	
	//Attributes of a ship: length, orientation, position, name, immatriculation, state
	/**immatriculation is the name of the ship we want to place: 
	 * destroyer, submarine, cruiser, battleship, carrier */
	public String name;
	public int im; /**Immatriculation number: associate each ship name to an int*/
	public String reverse;
	/**size between 2 and 5 from the destroyer to the carrier*/
	public int length;
	/**position*/
	public int line ;
	public char column;
	/**Use the position as an anchor and then deploy the ship thanks to the orientation : 
	 * 8 up, 2 down, 6 right, 4 left
	 * we chose those numbers since on the number pad of a keyborad, it corresponds to each direction*/
	public int orientation;
	/**state account for the cells of the ship that have not been touched*/
	public int sinking;
	public boolean state;//false means up and true means ship sunk
	private boolean testColumn;
	
	
	/**Constructor*/
	public Ship (String name){
		this.name = name;
		this.length = whichLength(this.name);
		this.im = whichShip(this.name);
		//this.reverse = whichShipReverse(this.im);
		sinking = this.length;
		state = false;		
	}
	
	/**Methods*/
	public int whichLength(String name){
		int l = 0 ;
		switch (name){
			case "destroyer":
				l = 2;
				break;
			case "submarine":
				l = 3;
				break;
			case "cruiser" :
				l = 3;
				break;
			case "battleship":
				l = 4;
				break;
			case "carrier":
				l = 5;
				break;
			case " ":
				l = 0;
				break;
		}
		return l;
	} 
	
	public int whichShip(String name){
		int i = 0 ;
		switch (name){
			case "destroyer":
				i = 1;
				break;
			case "submarine":
				i = 2;
				break;
			case "cruiser" :
				i = 3;
				break;
			case "battleship":
				i = 4;
				break;
			case "carrier":
				i = 5;
				break;
		}
		return i;
	} 
	/*
	public String whichShipReverse(int immatriculation){
		String name = "boat" ;
		switch (immatriculation){
			case 1:
				name = "destroyer";
				break;
			case 2:
				name = "submarine";
				break;
			case 3:  
				name = "cruiser" ;
				break;
			case 4 :
				name = "battleship";
				break;
			case 5 :
				name = "carrier";
				break;
		}
		return name;
	} */
	
	public void wherePlaced (){
		
		System.out.println("We want to place the " + this.name + ". Its length is " + this.length);
		System.out.println("Where do you want to place it?");
		System.out.println();
		
		/**Ask for the positon and orientation*/
		whichColumn(); //char between A and J
		whichLine(); //int between 1 and 10
		whichOrientation(); //int 2(down), 4(left), 8(up), 6(right)
		
		/**Check if the position asked is available*/
		boolean isItPossible = wellPlaced();
		while (!isItPossible){
			System.out.println("This is not possible unfortunately");
			wherePlacedAgain();
			isItPossible = wellPlaced();
		}
	}
	
	public void whichLine(){
		int tries=0;
		do{
			System.out.print("On which line would you like to place your " + this.name + "? ");
			System.out.println ("(you must choose an number between 1 and 10)");
			System.out.print ("line :"); 
			this.line = sc.nextInt();
			if((this.line<=0)||(this.line>10) && (tries>0)){
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
	
	
	public void whichColumn(){
		int tries=0;
		do{	
			System.out.println("On which column would you like to place your " + this.name +" ? (you must write a CAPITAL letter from A to J)");
			System.out.print ("Column :"); 
			//Asks for the columns
			this.column = sc.next().charAt(0); 
			testColumn = (this.column!='A')&&(this.column!='B')&&(this.column!='C')&&(this.column!='D')&&(this.column!='E')&&(this.column!='F')&&(this.column!='G')&&(this.column!='H')&&(this.column!='I')&&(this.column!='J');
			//Tests if the entered char is ok
			if(testColumn && (tries>0)){
				System.out.println("Sorry again, this entry is still incorrect...\nWrite in capital letter either:\nA  B  C  D  E  F  G  H  I  J\nLet's do it again!");
				tries++;
			}else if(testColumn){
				System.out.println("Sorry this entry is incorrect...Let's do it again! ");
				tries++;
			}
		} while(testColumn);
	}
	
	public void whichOrientation(){
		int tries=0;
		do{
			System.out.print("Which orientation should take your" + this.name + " ?" );
			System.out.println(" (8 upward, 6 right-oriented, 2 downward, 4 left-oriented): ");
			this.orientation = sc.nextInt();
			//Tests if the orientation
			if((this.orientation%2!=0)||(this.orientation==0) && tries>0){
				System.out.println("Sorry again, this entry is still incorrect...");
				System.out.println("You must choose 2(down), 4(left), 8(up), 6(right)");
				System.out.println("Let's do it again"); 
				tries++;
			}else if ((this.orientation%2!=0)||(this.orientation==0)){
				System.out.println("Sorry this entry is incorrect...Let's do it again! ");
				tries++;
			}
		} while((this.orientation%2!=0)||(this.orientation==0));
		
	}
	
	/**The general idea of this method is to check that for each orientation the difference between the ship's position and length is not outside the grid:
	 * Example with the bettleship that is of length 4: we indicate the "anchor point" with a X and the orientation using < or ^ or >
	 * 		      |A|B|C|D|E|F|...|J|  		  |A|B|C|D|E|F|...|J|  			  |A|B|C|...|H|I|J|
	 * 			1 | | | | | | |...| |			1 | | | | | | |...| |			1 | | | |...| | | |
	 * 			2 | | | | | 					2 | |<|X| |						2 | | | |...|X|>| |  
	 * 			3 | |^| |						3 | | | |						3 | | | |...| | | |
	 * 			4 | |X|							4 | | |							4 | | 
	 * 			5 | |							5 | |							5 | |
	 * 		  ... | | 						  ... | | 						  ... | | 
	 * 		   10 | |						   10 | |						   10 | |
	 * 		We selected B4 upward:          We selected C2 to the left:       We selected H2 to the right:
	 * 	  We should have B4, B3, B2, B2	   We should have C2, B2, A2, .2 		 We should have H2, I2, J2, K2
	 *     									  but we miss the cell .2  		 but the cell K2 does not exist
	 *  Return true since possible		   Return false since impossible 	  Return false since impossible
	 *For each mistake, we will print what is wrong on the terminal so that in the method wherePlacedAgain, the player can choose to only change one the the parameter.*/
	
	public boolean wellPlaced (){
		int transformation = this.column;
		transformation = transformation - 64;
		boolean checking = true;
		
		switch (this.orientation){
			/**Case for the orientation going downward*/
			case 2 :
				if(this.line + this.length -1  > 10 ){
					checking = false;
					System.out.println("Orienting your ship downward is not possible, your ship is out of bounds");
					System.out.println("Here you can either change the orientation or the line");
				}
				break;
			/**Case for the orientation going to the left*/
			case 4 :
				if(transformation - this.length < 0 ){
					checking = false;
					System.out.println("Orienting your ship to the left is not possible, your ship is out of bounds");
					System.out.println("Here you can either change the orientation or the column");
				}
				break;
			/**Case for the orientation going to the right*/
			case 6 :
				if(transformation + this.length - 1 > 10 ){
					checking = false;
					System.out.println("Orienting your ship to the left is not possible, your ship is out of bounds");
					System.out.println("Here you can either change the orientation or the column");
				}
				break;
			/**Case for the orientation going upward*/
			case 8 :
				if(this.line - this.length  < 0 ){
					checking = false;
					System.out.println("Orienting your ship upward is not possible, your ship is out of bounds");
					System.out.println("Here you can either change the orientation or the line");
				}
				break;
		}
		/**If no mistake has been made, the ship is well placed hence we return true*/
		return checking; 
	}
	
	public void wherePlacedAgain (){
		System.out.println("Which parameter do you want to change?");
		System.out.println("0: change the line, 1: change the colunm, 2:change the orientation, 3:change all of them");
		int change = sc.nextInt();
		switch(change){
			case 0:
				whichLine();
				break;
			case 1:
				whichColumn();
				break;
			case 2:
				whichOrientation();
				break;
			case 3:
				whichLine();
				whichColumn();
				whichOrientation();
				break;
		}
	}
	
	public void randomLine(){
		int a = (int)(Math.random()*10)+1;
		this.line = a;
	}

	public void randomColumn(){
		char b = (char)((Math.random()*10)+65);
		this.column = b;
	}

	public void randomOrientation(){
		int c = ((int)((Math.random()*4+1)))*2;
		this.orientation = c;
	}

	public void randomWherePlaced(){
		randomLine();
		randomColumn();
		randomOrientation();
		
		boolean isItPossibleRandom = wellPlacedRandom();
		while (!isItPossibleRandom){
			randomLine();
			randomColumn();
			randomOrientation();
			isItPossibleRandom = wellPlacedRandom();
		}
	}
	
	public boolean wellPlacedRandom(){
		int transformation = this.column;
		transformation = transformation - 64;
		boolean checking = true;
		
		switch (this.orientation){
			/**Case for the orientation going downward*/
			case 2 :
				if(this.line + this.length -1  > 10 ){
					checking = false;
				}
				break;
			/**Case for the orientation going to the left*/
			case 4 :
				if(transformation - this.length < 0 ){
					checking = false;
				}
				break;
			/**Case for the orientation going to the right*/
			case 6 :
				if(transformation + this.length - 1 > 10 ){
					checking = false;
				}
				break;
			/**Case for the orientation going upward*/
			case 8 :
				if(this.line - this.length  < 0 ){
					checking = false;
				}
				break;
		}
		/**If no mistake has been made, the ship is well placed hence we return true*/
		return checking; 
	}
	
	/**Check if the ship is sunk or not*/
	public void sink() {
		if (this.sinking == 0){
			this.state = true;
		}
	}
	
}
