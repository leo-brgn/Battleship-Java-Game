# Battleship
A project to apply Object-oriented programming to a well-known game. For ease of making, the game will be made in the terminal and not an graphical interface.  

## Presentation of the game
The goal of the game is to sink all of the opponent’s ships. In order to do that, each player has to target one cell of the opponent’s grid ( One’s grid being hidden from the other player ).
When a player takes a shot, the game warns him of the result, whether he hit or missed.
When a ship’s cells have all been touched, it sinks.
Traditionally, a fleet is composed of 5 ships:
* A destroyer, length 2
* A submarine, length 3
* A cruiser, length 3
* A battleship, length 4
* A Carrier, length 5
The first player who sinks the opponent’s entire fleet is declared winner.

## Requisites

Install the Java JDK.

## Utilisation

To start the game, open a command line in the source file and type the following line:

```bash
java Battleships
```

## Versionning

### Version 1.0.0

The grids of the game are arrays. They consist in lines and column indexed with integer from 1 to 10.
The placement of the ships is done this way on the private grid;
* We choose the coordinates of the extremity of the ship
* We choose the orientation of the ship, four different option
The computer places its ships according to five scenarii prepared previously.
The player plays in the first place. His moves are recorded on the strategy grid.
We choose a cell to target. 
We have different results:
* missed 
* touched
* sunk
The computer targets one random cell.
At each turn, we have a test to see if the game is finished. We have two tests per round. If the test is validated for the player, the computer's turn is skipped
At the end of the game, we print a message whether the computer or the player as won the game.

### Version 1.0.1

The computer cannot target twice the same cell. 

### Version 1.1.0

The coordinates are of the form: letter for the columns and number for the lines.

### Version 2.0.0

We have the choice between two AIs: the same one as before or a new one.
The new starts by playing randomly.
Once a cell is touched but the ship is not sunk, the program tests the surrounding case to look for the orientation of the ship.
Then, the program follows the line to sunk the ship and, if that's necessary, goes the opposite way to finish sinking the ship.

### Version 2.1.0

The more intelligent AI places its ships randomly at the beginning. It follows the rules through if..else statements. 

## Possible improvements

The player does not forcibly plays first, but it is chosen randomly.

## Organization

The game was made with the use of the IDE [Visual Studio Code](https://code.visualstudio.com/) and its extension [Live Share](https://visualstudio.microsoft.com/fr/services/live-share/?rr=https%3A%2F%2Fwww.google.com%2F) to share the code in real time.
We used the productivity manager [Trello]() to keep track of the updates and what we had to do for next week.
We used the shared drive [Google Drive](https://drive.google.com/) to share the UML Diagram made with [Visual Paradigm Online](https://online.visual-paradigm.com/).
To keep track of the versions when we did not work simultaneously we used [Caseine](https://caseine.org/).
Finally, for the meetings, in order to have a better connexion than with [Discord](https://discord.com/), we used [Zoom](https://zoom.us/).

## Authors

* **Laurine TURPIN** - **Dorian SONDERER** - **Lisa COLOMBARINI** - **Leo BRIGNONE**

## License
[INSA Lyon](https://www.insa-lyon.fr/)


