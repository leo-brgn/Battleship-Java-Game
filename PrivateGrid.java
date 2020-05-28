import java.util.Scanner;

public class PrivateGrid {
	/** Attributes */
	private Scanner sc = new Scanner(System.in);
	// Atributes: we creat the boats of the player, the computer and place them on
	// their "secret grid"
	/** We create a 2D array for the secret grid */
	public int[][] privateArray;
	/** For practical reasons we create 1D arrays containing the five ships */
	public int[] shipsIm;
	public Ship[] fleet ;
	public Ship[] supposedFleet;

	/** Constructor */
	public PrivateGrid() {
		this.privateArray = new int[10][10];
		this.fleet  = new Ship[5];
		this.shipsIm = new int[5];
		this.supposedFleet = new Ship[5];
	}

	/** Methods */
	/** The following method will ask the player to place its five boats */
	public void createShipPlayer() {
		
		/**Initialization of the supposed fleet */
		Ship s1 = new Ship("destroyer");
		Ship s2 = new Ship("submarine");
		Ship s3 = new Ship("cruiser");
		Ship s4 = new Ship("battleship");
		Ship s5 = new Ship("carrier");
		Ship s6 = new Ship(" ");

		this.supposedFleet[0] = s1;
		this.supposedFleet[1] = s2;
		this.supposedFleet[2] = s3;
		this.supposedFleet[3] = s4;
		this.supposedFleet[4] = s5;


		/**
		 * The do-while loop keeps on going as long as all the ships have not been
		 * placed
		 */
		do {
			System.out.print("Which boat do you want to place? You can choose among: ");
			for(int i = 0; i<this.supposedFleet.length; i++ ){
				System.out.print(supposedFleet[i].name + "  ");
			}	
			/*
			System.out.println();

			System.out.print("You have already placed :");
			for (int i = 0; i < fleet .length; i++) {
				if (shipsIm[i] != 0) {
					System.out.print(fleet [i].reverse + "  ");
				}
			}*/
			System.out.println();
			System.out.print("Name of the chosen boat: ");
			String a = sc.nextLine();
			displayPrivate();
			
			Ship ship = new Ship(a);

			while (ship.im == 0) {
				System.out.println("This not a correct name");
				a = sc.nextLine();
				ship = new Ship(a);
			}

			/** Check that the ship has not been placed */
			if (shipsIm[ship.im - 1] == 0) {
				/** If not placed, ship is created in the 1D array of Ships */
				fleet [ship.im - 1] = ship;
				supposedFleet[ship.im-1]= s6;
				/** The array counting for the ships created in incremented by 1 */
				shipsIm[ship.im - 1]++;

				/** Ask the player the position where he wants to place the boat */
				fleet [ship.im - 1].wherePlaced();
				/** use the method check to avoid overlaping */
				check(privateArray, fleet [ship.im - 1]);

				/**
				 * If a mistake has been made in the positionning of the ship, the while loop
				 * enforces the player to select a new position
				 */
				while (!check(privateArray, fleet [ship.im - 1])) {
					System.out.println("Oops, there was a mistake: you have to do it again!");
					fleet [ship.im - 1].wherePlaced();
					check(privateArray, fleet [ship.im - 1]);
				}

				/** Use the placement method to implement privateArray */
				placement(fleet [ship.im - 1]);
				displayPrivate();
				System.out.println();
			}
		} while (!allPlaced(shipsIm));
	}

	public boolean allPlaced(int[] ships) {
		boolean allplaced = true;
		for (int i = 0; i < ships.length; i++) {
			if (ships[i] == 0) {
				allplaced = false;
				break;
			}
		}
		return allplaced;
	}

	public void createShipComputer(int a){
		if (a==1){
			createShipComputerAlpha();
		}else{
			createShipComputerBeta();
		}
	}

	/**
	 * The following method in alpha-version will provide a library of scenarii
	 * (maybe 5) for the computer
	 */
	public void createShipComputerAlpha() {
		int a = (int)((Math.random()*5)+1);
		
		Ship s1 = new Ship("destroyer");
		Ship s2 = new Ship("submarine");
		Ship s3 = new Ship("cruiser");
		Ship s4 = new Ship("battleship");
		Ship s5 = new Ship("carrier");

		switch(a){
			// il y a plusieurs s1... en duplicata ?
			case 1:
				s1.line = 1;
				s1.column = 'B';
				s1.orientation = 2;
				s2.line = 8;
				s2.column = 'A';
				s2.orientation = 6;
				s3.line = 7;
				s3.column = 'H';
				s3.orientation = 2;
				s4.line = 2;
				s4.column = 'I';
				s4.orientation = 2;
				s5.line = 4;
				s5.column = 'B';
				s5.orientation = 6;
			break;

			case 2:
				s1.line = 3;
				s1.column = 'B';
				s1.orientation = 6;
				s2.line = 3;
				s2.column = 'F';
				s2.orientation = 2;
				s3.line = 7;
				s3.column = 'I';
				s3.orientation = 2;
				s4.line = 1;
				s4.column = 'D';
				s4.orientation = 6;
				s5.line = 7;
				s5.column = 'A';
				s5.orientation = 6;
				break;
			
			case 3:
				s1.line = 2;
				s1.column = 'B';
				s1.orientation = 6;
				s2.line = 1;
				s2.column = 'J';
				s2.orientation = 2;
				s3.line = 8;
				s3.column = 'C';
				s3.orientation = 2;
				s4.line = 9;
				s4.column = 'F';
				s4.orientation = 6;
				s5.line = 1;
				s5.column = 'H';
				s5.orientation = 2;
				break;
			
			case 4:
				s1.line = 9;
				s1.column = 'E';
				s1.orientation = 2;
				s2.line = 10;
				s2.column = 'A';
				s2.orientation = 6;
				s3.line = 1;
				s3.column = 'F';
				s3.orientation = 2;
				s4.line = 6;
				s4.column = 'A';
				s4.orientation = 6;
				s5.line = 1;
				s5.column = 'I';
				s5.orientation = 2;
			break;
			
			case 5:
				s1.line = 5;
				s1.column = 'I';
				s1.orientation = 6;
				s2.line = 10;
				s2.column = 'A';
				s2.orientation = 6;
				s3.line = 1;
				s3.column = 'C';
				s3.orientation = 2;
				s4.line = 1;
				s4.column = 'H';
				s4.orientation = 2;
				s5.line = 8;
				s5.column = 'F';
				s5.orientation = 6;
			break;
		}
		placement(s1);
		placement(s2);
		placement(s3);
		placement(s4);
		placement(s5);
		this.fleet [0] = s1;
		this.fleet [1] = s2;
		this.fleet [2] = s3;
		this.fleet [3] = s4;
		this.fleet [4] = s5;
	}

	/**
	 * For this method in beta-version, the computer will place its ships randomly
	 * without lollowing a preexisting scenario -> create more possibilities since
	 * the opponants ships will always be placed differently
	 */
	public void createShipComputerBeta() {

		Ship s1 = new Ship("destroyer");
		Ship s2 = new Ship("submarine");
		Ship s3 = new Ship("cruiser");
		Ship s4 = new Ship("battleship");
		Ship s5 = new Ship("carrier");

		this.fleet [0] = s1;
		this.fleet [1] = s2;
		this.fleet [2] = s3;
		this.fleet [3] = s4;
		this.fleet [4] = s5;

		for(int i = 0; i < 5; i++){
			do{
				this.fleet [i].randomWherePlaced();
			}while(!check(privateArray, this.fleet [i]));
			placement(this.fleet [i]);
		}
	}

	/** This method check that ships don't overlap */
	public boolean check(int[][] grid, Ship s) {
		int transformation = s.column;
		transformation = transformation - 65;
		boolean checking = true;

		//Case when not on the edges or corners
		
		for (int i = -1; i <= s.length; i++) {
			for(int j = -1 ; j < 2 ; j++ ){
				try{
					if (s.orientation == 2 && privateArray[s.line + i - 1 ][transformation + j] != 0) {
						checking = false;
						break;
					} else if (s.orientation == 4 && privateArray[s.line - 1 + j][transformation - i ] != 0) {
						checking = false;
						break;
					} else if (s.orientation == 8 && privateArray[s.line - i - 1 ][transformation + j] != 0) {
						checking = false;
						break;
					} else if (s.orientation == 6 && privateArray[s.line - 1 + j][transformation + i ] != 0) {
						checking = false;
						break;
					}
				} catch(Exception e){}
			}
		}
		return checking;
	}

	/**
	 * This method assigns the immatriculation number of the placed boat to the
	 * cells it occupies
	 */
	public void placement(Ship s) {
		int transformation = s.column;
		transformation -= 65;

		for (int i = 0; i < s.length; i++) {
			if (s.orientation == 2) {
				privateArray[s.line + i - 1][transformation] = s.im;
			} else if (s.orientation == 4) {
				privateArray[s.line - 1][transformation - i] = s.im;
			} else if (s.orientation == 8) {
				privateArray[s.line - i - 1][transformation] = s.im;
			} else {
				privateArray[s.line - 1][transformation + i] = s.im;
			}
		}
	}
	
	public void updatePrivate (int line, int column){
		int boat = this.privateArray[line-1][column] ; 

		if(boat == 0){
			this.privateArray[line-1][column] = -3;
		}else{
			if (this.fleet[boat - 1].state) {
				changeSunkPrivate(this.privateArray, this.fleet[boat-1]);
			}else{
				this.privateArray[line-1][column] = -1;
			}
		}
		
		
	}
	
	public void changeSunkPrivate (int [][] grid, Ship s){
		int transformation = s.column;
		transformation = transformation - 65;
		
		switch(s.orientation){
			case 2:
				for (int i = 0; i < s.length; i++) {
					grid[s.line-1+i][transformation] = -2;
				}
				break;
			case 4:
				for (int i = 0; i < s.length; i++) {
					grid[s.line-1][transformation-i] = -2;
				}
				break;
			case 8:
				for (int i = 0; i < s.length; i++) {
					grid[s.line-1-i][transformation] = -2;
				}
				break;
			case 6:
				for (int i = 0; i < s.length; i++) {
					grid[s.line-1][transformation+i] = -2;
				}
		}
	}


	public void displayPrivate() {
		Printer.displayArray(privateArray, "private");
	}
}
