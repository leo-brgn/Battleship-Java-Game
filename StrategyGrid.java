public class StrategyGrid {

	/*
	* ATTRIBUTES
	*/
	public int[][] tacticalGrid; //Grid of the current player
	public int[][] privateGrid; //Grid of the opponent
	public PrivateGrid privateGridEnemy; 
	public int sunkBoats; //counts the number of sunk boats
	public int currentUser; //to know if it is the computer 2 or the human 1
	public boolean finishedGame; 
	public boolean touched;
	public boolean sunk;

	/*
	* CONSTRUCTOR
	*/
	public StrategyGrid(PrivateGrid opponentGrid) {
		this.finishedGame = false; //Starts the game not finished
		this.sunkBoats = 0; //
		this.tacticalGrid = new int[10][10]; //Creates the strategy grid
		this.privateGridEnemy = opponentGrid;
		this.privateGrid = opponentGrid.privateArray;
		this.touched = false;
		this.sunk = false;
	}

	/*
	* METHODS 
	*/

	/** The method returns if the targeted cell as been missed, touched or sunk: */
	public void hit (int line, int column){
		int i = this.privateGrid[line-1][column];
		if (i > 0){
			privateGridEnemy.fleet [i - 1].sinking--;
			privateGridEnemy.fleet [i - 1].sink();
			this.touched = true;
			if (privateGridEnemy.fleet [i - 1].state) {
				changeSunk(this.tacticalGrid, privateGridEnemy.fleet [i-1]); //to change all the part of the boat to sunk 
				numberSunk(privateGridEnemy.fleet [i - 1]); //to add to the number of sunk boats
				Printer.hitDisplay(currentUser, "sunk");
				this.sunk = true;
			}else{
				this.tacticalGrid[line-1][column]=1;
				Printer.hitDisplay(currentUser, "touched");
				this.sunk = false;
			}
		}else if (i == 0) {
			this.tacticalGrid[line-1][column]=3;
			Printer.hitDisplay(currentUser, "missed");
			this.touched = false;
			this.sunk = false;
		}			
	}


	//
	public void changeSunk (int[][] grid ,Ship s){
		int transformation = s.column;
		transformation = transformation - 65;
		
		switch(s.orientation){
			case 2:
				for (int i = 0; i < s.length; i++) {
					grid[s.line-1+i][transformation] = 2;
				}
				break;
			case 4:
				for (int i = 0; i < s.length; i++) {
					grid[s.line-1][transformation-i] = 2;
				}
				break;
			case 8:
				for (int i = 0; i < s.length; i++) {
					grid[s.line-1-i][transformation] = 2;
				}
				break;
			case 6:
				for (int i = 0; i < s.length; i++) {
					grid[s.line-1][transformation+i] = 2;
				}
		}
	}

	//Method to display the grid
	public void displayStrategy() {
		Printer.displayArray(this.tacticalGrid, "strategy");
	}

	//Method to test if the game is won by one
	public void gameEnds() {
		if (this.sunkBoats == 5) {
			this.finishedGame = true;
		}
	}

	//Method to count the number of sunk ships
	public void numberSunk(Ship ship) {
		if (ship.state = true) {
			this.sunkBoats++;
		}
	}
}