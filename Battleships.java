import java.util.Scanner;

public class Battleships {

	/*
	* MAIN METHOD
	*/
	public static void main (String[]args){
		Scanner sc = new Scanner(System.in); //Open the scanner for the keyboard input
		int winner = 0; //The game is initially not winned by anyone (player 1 and computer 2)
		Printer rules = new Printer();//The game starts printing the rules and we construct the object printer

		AI computer = new AI (); //We construct the AI class
		Me player = new Me (); //We construct the player class


		PrivateGrid privateGridPlayer = new PrivateGrid(); //We construct the private grid of the player, this one will be used to place the boats. 
		PrivateGrid privateGridAI = new PrivateGrid(); //We construct the private grid of the AI 
		
		privateGridPlayer.createShipPlayer(); 

		rules.beginning();
		int level = sc.nextInt(); //Asks for the game's difficulty
			
		StrategyGrid strategyGridPlayer = new StrategyGrid(privateGridAI); //We construct the strategy grid of the player, it will be used to know the touched, sunk and even missed spots of the game
		strategyGridPlayer.currentUser = 1; //We set to 1 to display the good dialog later
		StrategyGrid strategyGridAI = new StrategyGrid(privateGridPlayer); 
		strategyGridAI.currentUser = 2; //We set to 2 to display the good dialog later too
		
		privateGridAI.createShipComputer(level); //The computer places its fleet according to the difficulty
		
		System.out.println();
		temporing(2500);
		
		/**Do..While loop of the general game */
		do{
		
/**Player's turn*/
			player.plays(strategyGridPlayer, privateGridAI);
			strategyGridPlayer.gameEnds(); //tests if the game is finished
			if(strategyGridPlayer.finishedGame){
				winner = 1; 
				break; //Get out of the do while without playing the AI's turn
			}
			
			Printer.targeted(); //Prints a message signifying that AI is now playing

/**AI's turn*/	
			computer.whichDifficulty(level, strategyGridAI, privateGridPlayer);
			strategyGridAI.gameEnds(); //tests if the game is finished
			if (strategyGridAI.finishedGame) {
				winner = 2; 
			}
			System.out.println();
		}while (!strategyGridPlayer.finishedGame && !strategyGridAI.finishedGame);
		
		Printer.endGame(winner); //Prints the final words according if you won or lost
		sc.close();
	}
	
	/**Method to create delay using the Thread class provided automatically by java*/
	private static void temporing(int ms){
		try{
			Thread.sleep(ms);
		}catch (InterruptedException e) {}
	}
}