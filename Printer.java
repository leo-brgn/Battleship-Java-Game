import java.util.Scanner;

public class Printer{

	/**
	 * ATTRIBUTES
	 */
  private Scanner sc = new Scanner(System.in);
	private String understoodStr; 
	private String [] dialogs = {
		"(press enter to start the game...)",
		"Oh ! Welcome to the battlefield, Captain! The weather seems to be with us today.",
		"You must remember that the protocol is very strict around here!\nDo you remember it clearly, Captain ?(type \"no\" if not)",
		"Very well... I'm going to the archives, I'll be right back\n",
		"... Ah! Here is the original protocol: Each player has a fleet of five ships to command.\n The first one who has sunk all his opponent's ships is the winner! \nYou will soon place your ships... Choose their position wisely: there is no turning back!",
		"Captain, remember that your ships can't touch each other! You would lose a precious time...",
		"Now Captain, it is time to place your fleet!",
		"(Press enter when you feel ready to start the battle.)",
		"Do you want to challenge Captain Dumbetokey (enter 1) or Captain Wisend'clir (enter 2) ?",
		"When you will attempt to target the opponent fleet : '-' means missed, 'O' means touched and 'X' means sunk... \nThe same code is used for when the opponent targets your own fleet !"
	};
	
	/**
	 * CONSTRUCTOR
	 */
	public Printer () {
		this.understoodStr = "";
		planning();
	}

	/**
	 * METHODS
	 */
	/**Method to print all the rules at the beginning */
	public void planning(){

		print(dialogs[0], 30);

		sc.nextLine();
		print(dialogs[1], 30);

		print(dialogs[2], 30);
		this.understoodStr = sc.nextLine();
		temporing(1000);

		if (understoodStr.equals ("No") || understoodStr.equals ("NO") || understoodStr.equals("no")){	
			print(dialogs[3], 30);
			temporing(1500);
			print(dialogs[4], 30);
			temporing(1500);
		}

		print(dialogs[5],30);

		System.out.println();
		
		print(dialogs[6],30);
		

	}

	public void beginning(){

		System.out.println();
		print(dialogs[7], 30);
		sc.nextLine();
		System.out.println();

		print(dialogs[9],30);

		print(dialogs[8], 30);
	}
	
	/**Method to create delay using the Thread class provided automatically by java*/
	public static void temporing(int ms){
		try{
			Thread.sleep(ms);
		}catch(InterruptedException e){}
  }
	
	/**Method to print a message like a typemachine */
	public static void print (String msg, int mpc) {
		for (int i = 0; i<msg.length(); i++){
				System.out.print(msg.charAt(i));
				temporing(mpc);
		}
		System.out.println();
	}

	/**Method to display the arrays */
	public static void displayArray (int [][] g, String gridType) {
		int K = 1;
		if(gridType == "private"){
			System.out.println("           P R I V A T E  G R I D           ");
		}else{
			System.out.println("          T A C T I C A L  G R I D          ");
		}
		System.out.println("                    ****                    ");
		System.out.println("   | A | B | C | D | E | F | G | H | I | J |");
		for(int i=0; i<g.length; i++){
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			if(K==10){
				System.out.print(K + " " );
			}else{
				System.out.print(" " + K + " ");
			}
				K++;
			for(int j=0; j<g[0].length; j++){
				if (gridType == "private") {
					System.out.print("|");
					if(g[i][j]==0){
						System.out.print("   ");
					}else if(g[i][j]==-1){
						System.out.print(" O ");
					}else if(g[i][j]==-2){
						System.out.print(" X ");
					}else if(g[i][j]==-3){
						System.out.print(" - ");
					}else{
						System.out.print(" # ");
					}
				} else {
					System.out.print("|");
					if(g[i][j]==3){
						System.out.print(" - ");
					}else if(g[i][j]==1){
						System.out.print(" O ");
					}else if(g[i][j]==2){
						System.out.print(" X "); //only one cell is converted
					}else{
						System.out.print("   ");
					}
				}
				
			}
			System.out.println("|");
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

	/**Method to display the dialog at the end of each turn */
	public static void hitDisplay (int c, String s) {
		if(c == 1) {
			switch(s){
				case "sunk": 
					print("You sunk the opponent ship! Well done!", 1);
					break;
				case "touched": 
					print("You have touched a ship. Congrats! ", 1);
					break;
				case "missed": 
					print("You have missed. Sorry...", 1);
					break;
			}
		} else if (c==2) {
			switch(s){
				case "sunk": 
					print("One ship of your fleet has been sunk. You need to hurry!", 1);
					break;
				case "touched": 
					print("One of your ships has been touched...", 1);
					break;
				case "missed": 
					print("Missed... It was a close call.", 1);
					break;
			}
		}
	}

	/**Method to display the dialog at the end of the game */
	public static void endGame (int ended){
		if(ended==1){
			print("You won! Congrats! Well played!",30);
		}else if(ended==2){
			print("You lost! That's too bad!",30);
		}
		temporing(1000);
		print("Thanks for playing",30);
	}

	/**Method to display when the computer is playing */
	public static void targeted () {
		temporing(1500);
		print("The computer is targetting you!",30);
		System.out.println();
	}
}