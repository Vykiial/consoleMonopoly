/*
  David Nong-Ang, Sahib Johar, Karthik Kodeswaran
  17/01/2021
  Final Summative Project
  This is code for the Java Main.
*/
// import Monopoly;
import java.io.*; 
import java.util.*; 
import java.io.*;  

class Main{	

  // Scanner Object
  public static Scanner input = new Scanner(System.in);

  ///////-------------------------------------Public Variables-------------------------------------\\\\
  // Variables 
  public static String userName = null;
  public static String userInput = null;
  public static int menuSelect = 0;
	public static int gameLoop = 0;
  public static boolean menuError = false;
	public static String currentTile = null;
	public static int currentTileInt = 0;
	public static int doublesCount = 0;
	public static String mortgageProperty = null;
	public static boolean gameOver = false;

  // Color Variables
  //https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/ 
  public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
  public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
  public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
  public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
  public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
  public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
  public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
  public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RESET = "\u001B[0m";

  public static void main(String[] args) {
    
    ///////-------------------------------------CREATING ALL OBJECT VARIABLES FOR THE GAME-------------------------------------\\\\
		// Dice Objects
		Dice dice1 = new Dice(100, 100, 6);
		Dice dice2 = new Dice(100, 100, 6);
		Dice rng = new Dice(100, 100, 10); 
		Dice cardRng = new Dice(100, 100, 5); //used for picking chance and lifestyle cards.

		// Username
    userName = getName();

		// Player Objects
		Player player1 = new HumanPlayer(userName, new Piece(0), 372000);
		Player cpu1 = new CPU(new Piece(1), 372000, "David");
		Player cpu2 = new CPU(new Piece(2), 372000, "Karthik");
		Player cpu3 = new CPU(new Piece(3), 372000, "Sahib");

		// Place Objects
		// Create all the "Properties" and add them to an arraylist
		ArrayList<Place> places = new ArrayList<Place>(22);
		places.add(new Place("Motor Drive", 5000, 7000, 5000, 2));
		places.add(new Place("Gadget Wharf", 5000, 7000, 5000, 4));
		places.add(new Place("Surfer's Cove", 15000, 15000, 10000, 5));
		places.add(new Place("Aqua Park Resort", 15000, 15000, 10000, 7));
		places.add(new Place("Lakeside Marina", 20000, 15000, 10000, 8));
		places.add(new Place("Castle View", 35000, 90000, 20000, 10));
		places.add(new Place("Dream Avenue", 35000, 20000, 20000, 11));
		places.add(new Place("Palace Gardens", 40000, 20000, 20000, 12));
		places.add(new Place("Adventrure Park", 55000, 25000, 25000, 13));
		places.add(new Place("Themepark City", 55000, 25000, 25000, 15));
		places.add(new Place("Movie District", 60000, 25000, 25000, 16));
		places.add(new Place("Style Square", 80000, 35000, 35000, 18));
		places.add(new Place("Party Plaza", 80000, 35000, 35000, 20));
		places.add(new Place("Showtime Boulevard", 90000, 35000, 35000, 21));
		places.add(new Place("Sunshine Bay", 115000, 40000, 40000, 22));
		places.add(new Place("Bling Beach", 115000, 40000, 40000, 23));
		places.add(new Place("Yacht Harbor", 120000, 40000, 40000, 24));
		places.add(new Place("Treetop Retreat", 145000, 50000, 50000, 26));
		places.add(new Place("Ski Mountain", 145000, 50000, 50000, 27));
		places.add(new Place("Diamond Hills", 150000, 50000, 50000, 29));
		places.add(new Place("Fortune Valley", 170000, 65000, 65000, 31));
		places.add(new Place("Paradise Island", 200000, 65000, 65000, 32));

		// Corner Tile Objects
		// Create all the Corner Tiles and add them to an arraylist
		ArrayList<ActionSquare> cornerPieces = new ArrayList<ActionSquare>(4);
		cornerPieces.add(new ActionSquare(2, 2, "Go", 1, 1));
		cornerPieces.add(new ActionSquare(2, 2, "Jail", 9, 2));
		cornerPieces.add(new ActionSquare(2, 2, "Free Parking", 17, 3));
		cornerPieces.add(new ActionSquare(2, 2, "Go To Jail", 25, 4));

		// Special Tile Objects
		// Create all the Special Tiles and add them to an arraylist
		ArrayList<ActionSquare> specialTiles = new ArrayList<ActionSquare>(6);
		specialTiles.add(new ActionSquare(1, 1, "Millionaire Lifestyle", 3, 5));
		specialTiles.add(new ActionSquare(1, 1, "Chance", 6, 6));
		specialTiles.add(new ActionSquare(1, 1, "Millionaire Lifestyle", 14, 5));
		specialTiles.add(new ActionSquare(1, 1, "Chance", 19, 6));
		specialTiles.add(new ActionSquare(1, 1, "Millionaire Lifestyle", 28, 5));
		specialTiles.add(new ActionSquare(1, 1, "Chance", 30, 6));

		// All Tiles on the Board
    //This array list uses polymorphism where the type is a Sqaure and it stores Places and ActionSqaures objects, which are the child of Square.
		ArrayList<Square> tiles = new ArrayList<Square>(32);
		for(int i = 0; i < 22; i++)
			tiles.add(places.get(i));
		for(int i = 0; i < 4; i++)
			tiles.add(cornerPieces.get(i));
		for(int i = 0; i < 6; i++)
			tiles.add(specialTiles.get(i));

		// Board Object
		Board board = new Board(22, 22, places, cornerPieces, specialTiles);

    // Card Object
    Card chanceCards = new Card(100, 100, 1, "chance");
    Card lifeStyleCards = new Card(100,100, 2, "lifestyle");

    // Display Game Title
    printSprite();

    // Main Menu
    printMenuScreen();

    // Menu option 2 - Display Game Info
    while(menuSelect == 2){
      printInfoScreen();
    	menuSelect = 0;
    	printMenuScreen();
    }
      
    // Menu option 3 - Exit 
    if(menuSelect == 3)
      System.out.println("Thanks for playing!");
		
		// Menu Option 1 - Play
		if(menuSelect == 1){

    ///////-------------------------------------GAME STARTS-------------------------------------\\\\
		// Game Start Message
		System.out.println("Welcome to Monopoly, you start off with $372,000 and the goal is to achieve $1,000,000 before the three other opponents: Sahib, Karthik and David.");

		// Main Game Loop
		gameLoop = menuSelect;
			while(gameLoop == 1 && gameOver == false){
				// Player 1 turn
				if(player1.jail == true){ ///////-------------------------------------When Player is in Jail-------------------------------------\\\\
					menuSelect = 0;
					printJailMenu(); //Display options when user is in jail.
					if(menuSelect == 1){ // Pay to Bail
						System.out.println("You decided to pay for bail, you lose $50,000.");
						player1.jail = false;
					}
					if(menuSelect == 2){ //Roll for Doubles
						System.out.println("You have chosen to roll for doubles. \nPress enter to roll:");
						dice1.roll();
						dice2.roll();
						//display first and second dice
						printDice(dice1);
						printDice(dice2);
						if(dice1.lastRoll() == dice2.lastRoll()){ // If both dice roll the same thing
							System.out.println("Congratulations, you rolled doubles! You are now out of jail.");
							player1.jail = false;
						}
						else
							System.out.println("You did not roll doubles. Better luck next time.");
					}
				}
        ///////-------------------------------------Check if the user achieves a million dollars or goes bankrupt.-------------------------------------\\\\
        if(player1.money>=1000000)
          victoryScreen();
        if(player1.money<=0)
          gameOverScreen();
				if(player1.jail != true){ ///////-------------------------------------When Player is NOT in Jail-------------------------------------\\\\
					System.out.println("It is now your turn. Press enter to roll: ");
					input.nextLine();
					player1.move(dice1.roll() + dice2.roll());
					printBoard();
					if(player1.position > 32){ // Check if they pass the Start Position.
						player1.position -= 32;
						player1.money += 200000;
						System.out.println("Congratulations! You have passed 'Go' and gained $200,000!");
						showMoney(player1);
				  }// End If Loop
					//display first and second dice
					printDice(dice1);
					printDice(dice2);
					System.out.println("You rolled: " + (dice1.lastRoll() + dice2.lastRoll()) + ".");
					for(int i = 0; i < 32; i++){
						if(player1.position == tiles.get(i).position)
							currentTile = tiles.get(i).name;
					}
					System.out.println("You are now at position " + player1.position + " and landed on " + currentTile + ".");
					for(int i = 0; i < 32; i++){
						if(player1.position == tiles.get(i).position){
							if(tiles.get(i) instanceof Place){ ///////-------------------------------------When Player lands on a Property-------------------------------------\\\\
								if(((Place)tiles.get(i)).owner != null && ((Place)tiles.get(i)).owner != player1){ // When player lands on another player's property
									System.out.println("You landed on a property that someone else owns, you must pay rent.");
									((Place)tiles.get(i)).payRent(player1);
									showMoney(player1);
									break;
                }
								if(((Place)tiles.get(i)).owner == player1){ // When player lands on their own property.
									System.out.println("You landed on your own property, nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((Place)tiles.get(i)).owner == null){ // When player lands on an unowned property. 
									System.out.println(tiles.get(i).name + " is unowned. Would you like to buy it?");
									menuSelect = 0;
									printBuyMenu(); // Display menu for purchasing properties.
									if(menuSelect == 1){ // When the player decides to buy the property.
										((Place)tiles.get(i)).buy(player1); 
										System.out.println("You have purchased " + ((Place)tiles.get(i)).name + "!");
										showMoney(player1);
										System.out.println("Press enter to continue: ");
										input.nextLine();
										break;
									}
									if(menuSelect == 2){ // When player decides not to buy the property.
										System.out.println("You decided to not buy the property.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
								}
							}
              ///////-------------------------------------When Player lands on a Action Property like Chance or Millionaire Lifestyle.-------------------------------------\\\\
							if(tiles.get(i) instanceof ActionSquare){ 
								if(((ActionSquare)tiles.get(i)).name == "Chance"){ // If player lands on a Chance Card Position.
									cardRng.roll(); // Picks random number between 1 and 5 to choose a card.
									if(cardRng.lastRoll() == 1)
										chanceCards.chance1(player1);
									if(cardRng.lastRoll() == 2)
										chanceCards.chance2(player1);
									if(cardRng.lastRoll() == 3)
										chanceCards.chance3(player1);
									if(cardRng.lastRoll() == 4)
										chanceCards.chance4(player1);
									if(cardRng.lastRoll() == 5)
										chanceCards.chance5(player1);
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Millionaire Lifestyle"){ // If player lands on a Millionaire Lifestyle Card Position.
									cardRng.roll(); // Picks random number between 1 and 5 to choose a card.
									if(cardRng.lastRoll() == 1)
										lifeStyleCards.lifeStyle1(player1);
									if(cardRng.lastRoll() == 2)
										lifeStyleCards.lifeStyle2(player1);
									if(cardRng.lastRoll() == 3)
										lifeStyleCards.lifeStyle3(player1);
									if(cardRng.lastRoll() == 4)
										lifeStyleCards.lifeStyle4(player1);
									if(cardRng.lastRoll() == 5)
										lifeStyleCards.lifeStyle5(player1);
									break;
								}
                ///////-------------------------------------When Player lands on the Corner Tiles of the Board.-------------------------------------\\\\
								if(((ActionSquare)tiles.get(i)).name == "Go"){ // When player lands on First Positon.
									System.out.println("You are on 'Go', nothing happnes.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Go To Jail"){ // When player lands on "Go to Jail" Position, they are moved to Jail Position.
									player1.position = 9;
									player1.jail = true;
									System.out.println("You are now in jail.\nPress enter to continue: ");
									input.nextLine();  
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Free Parking"){ // When player lands on "Free Parking" Position.
									System.out.println("You are on 'Free Parking', nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Jail"){ // When player lands on the Jail Position.
									System.out.println("You are on 'Just Visiting', nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
							}
						}
					}
					///////-------------------------------------Roll Doubles-------------------------------------\\\\
					while(dice1.lastRoll() == dice2.lastRoll()){ // Checks if the dices are the same value.
						doublesCount++; // Keep track of doubles rolled.
						if(doublesCount == 3){ // When 3 doubles in row, player is taken to jail.
							System.out.println("Sorry you rolled doubles three times in a row. You will proceed directly to jail.");
							player1.position = 9;
							player1.jail = true;
							break;
						}
						System.out.println("Congratulations! You rolled doubles, you get to roll again. Press enter to roll: ");
						input.nextLine();
						player1.move(dice1.roll() + dice2.roll());
						printBoard();
						if(player1.position > 32){ // When player finishes a lap around the map.
							player1.position -= 32;
							player1.money += 200000;
							System.out.println("Congratulations! You have passed go and gained $200,000!");
						}
            // Display first and second dice.
            printDice(dice1);
            printDice(dice2);
						System.out.println("You rolled: " + (dice1.lastRoll() + dice2.lastRoll()) + "."); // Display dice rolls.
						for(int i = 0; i < 32; i++)
							if(player1.position == tiles.get(i).position)
								currentTile = tiles.get(i).name;
						System.out.println("You are now at position " + player1.position + " and landed on " + currentTile + ".");
						for(int i = 0; i < 32; i++){
							if(player1.position == tiles.get(i).position){
								if(tiles.get(i) instanceof Place){ ///////-------------------------------------When Player lands on a Property-------------------------------------\\\\
									if(((Place)tiles.get(i)).owner != null){ // If Player lands on another player's property
										System.out.println("You landed on a property someone else owns, you must pay rent.");
										((Place)tiles.get(i)).payRent(player1);
										showMoney(player1);
										break;
									}
									if(((Place)tiles.get(i)).owner == player1){ // If Player lands on their own property.
										System.out.println("You landed on your own property, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((Place)tiles.get(i)).owner == null){ // If Player lands on an unowned property.
										System.out.println(tiles.get(i).name + " is unowned. Would you like to buy it?");
										menuSelect = 0;
										printBuyMenu(); // Display Buy Menu
										if(menuSelect == 1){ // If player wants to purchase property.
											((Place)tiles.get(i)).buy(player1); 
											System.out.println("You have purchased " + ((Place)tiles.get(i)).name + "!");
											showMoney(player1);
											System.out.println("Press enter to continue: ");
											input.nextLine();
											break;
										}
										if(menuSelect == 2){ // If player does not want to purchase property.
											System.out.println("You decided to not buy the property.\n Press enter to continue: ");
											input.nextLine();
											break;
										}
									}
								}
								if(tiles.get(i) instanceof ActionSquare){ ///////-------------------------------------When Player lands on a Action Property like Chance or Millionaire Lifestyle.-------------------------------------\\\\
									if(((ActionSquare)tiles.get(i)).name == "Chance"){ //When player lands on a chance card position.
										cardRng.roll();
										if(cardRng.lastRoll() == 1)
											chanceCards.chance1(player1);
										if(cardRng.lastRoll() == 2)
											chanceCards.chance2(player1);
										if(cardRng.lastRoll() == 3)
											chanceCards.chance3(player1);
										if(cardRng.lastRoll() == 4)
											chanceCards.chance4(player1);
										if(cardRng.lastRoll() == 5)
											chanceCards.chance5(player1);
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Millionaire Lifestyle"){ // When player lands on a lifestyle card position.
										cardRng.roll();
										if(cardRng.lastRoll() == 1)
											lifeStyleCards.lifeStyle1(player1);
										if(cardRng.lastRoll() == 2)
											lifeStyleCards.lifeStyle2(player1);
										if(cardRng.lastRoll() == 3)
											lifeStyleCards.lifeStyle3(player1);
										if(cardRng.lastRoll() == 4)
											lifeStyleCards.lifeStyle4(player1);
										if(cardRng.lastRoll() == 5)
											lifeStyleCards.lifeStyle5(player1);
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Go"){ //When player lands on the Start Position.
										System.out.println("You are on 'Go', nothing happnes.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Go To Jail"){ // When player lands on the "Go to Jail", they are moved to jail.
										player1.position = 9;
										player1.jail = true;
										System.out.println("You are now in jail.\nPress enter to continue: ");
										input.nextLine();  
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Free Parking"){ // When player lands on "Free Parking" Position.
										System.out.println("You are on Free Parking, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Jail"){ // When player lands on jail position.
										System.out.println("You are on Just Visiting, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
								}
							}
						}
					}
					doublesCount = 0; 
        }
				menuSelect = 0;
				printEndTurnMenu();
				while(menuSelect != 5){
					if(menuSelect != 4)
						printEndTurnMenu();
					while(menuSelect == 1){ ///////-------------------------------------When Player chooses option to mortgage property.-------------------------------------\\\\
						System.out.println("Please choose a property you own to mortgage. You can also type 'exit' to to back.");
						mortgageProperty = input.nextLine().toLowerCase();
						if(mortgageProperty.equals("exit")){ // allows player to exit menu
							menuSelect = 0;
							break;
						}
						for(int i = 0; i < 22; i++){
							if(i == 22){
								System.out.println("Sorry that property could not be found.");
								break;
							}
							if(mortgageProperty.equals(places.get(i).name.toLowerCase())){
								if(places.get(i).mortgage(player1) == false){
									menuSelect = 0;
									break;
								}
								else{
									System.out.println(places.get(i).name + " has been mortgaged.\nYou gained $" + places.get(i).mortgage + " and you now have $" + player1.money + ".");
									menuSelect = 0;
									break;
								}
							}
						}
					}
					while(menuSelect == 2){///////-------------------------------------When Player chooses option to unmortgage property.-------------------------------------\\\\
						System.out.println("Please choose a property you own to unmortgage. You can also type 'exit' to to back.");
						mortgageProperty = input.nextLine().toLowerCase();
						if(mortgageProperty.equals("exit")){// allows player to exit menu
							menuSelect = 0;
							break;
						}
						for(int i = 0; i < 22; i++){
							if(i == 22){
								System.out.println("Sorry that property could not be found.");
								break;
							}
							if(mortgageProperty.equals(places.get(i).name.toLowerCase())){
								if(places.get(i).unMortgage(player1) == false){
									menuSelect = 0;
									break;
								}
								else{
									System.out.println(places.get(i).name + " has been unmortgaged.\nYou paid $" + places.get(i).mortgage + " and you now have $" + player1.money + ".");
									menuSelect = 0;
									break;
								}
							}
						}
					}
					while(menuSelect == 3){ ///////-------------------------------------When Player chooses to build a house on their property.-------------------------------------\\\\
						System.out.println("Please enter a property to build a house/hotel on (enter property name). You can also type 'exit' to back.");
						mortgageProperty = input.nextLine().toLowerCase();
						if(mortgageProperty.equals("exit")){// allows player to exit menu
							menuSelect = 0;
							break;
						}
						for(int i = 0; i < 22; i++){
							if(i == 22){
								System.out.println("Sorry that property could not be found.");
								break;
							}
							if(mortgageProperty.equals(places.get(i).name.toLowerCase())){
								if(places.get(i).hotel != null){
									System.out.println("You already have a hotel here, you cannot build anything else.");
									menuSelect = 0;
									break;
								}
								if(places.get(i).houses.size() != 4){
									if(places.get(i).buildHouse(player1) == false){
										menuSelect = 0;
										break;
									}
									System.out.println("You built a house on " + places.get(i).name + " for $" + places.get(i).houses.get(0).price + ".\nYou have " + places.get(i).houses.size() + " house(s).\nThe new rent is $" + places.get(i).rent + ".");
									showMoney(player1);
									menuSelect = 0;
									break;
								}
								if(places.get(i).houses.size() == 4){
									if(places.get(i).buildHotel(player1) == false){
										menuSelect = 0;
										break;
									}
									System.out.println("You built a hotel on " + places.get(i).name + " for $" + places.get(i).hotel.price + ".\nThe new rent is $" + places.get(i).rent + ".");
									showMoney(player1);
									menuSelect = 0;
									break;
								}
							}
						}
					}
					if(menuSelect == 4){
						System.out.println("You own " + player1.placeList() + ".\nPress enter to continue.");
						input.nextLine();
						menuSelect = 0;
					}
					if(menuSelect == 5){
						menuSelect = 0;
						break;
					}
				}

				///////-----------------------------------------------CPU 1's Turn -----------------------------------------------\\\\\\\
				if(cpu1.money <= 0){
					System.out.println(cpu1.name + " has been eliminated.\nPress enter to continue.");
					input.nextLine();
				}
				if(cpu1.jail == true && !(cpu1.money <= 0)){ ///////-------------------------------------When Player is in Jail-------------------------------------\\\\
					menuSelect = 0;
					if(rng.roll() > 7)
						menuSelect = 2;
					else
						menuSelect = 1; 
					if(menuSelect == 1){ // Pay to Bail
						System.out.println(cpu1.name + " decided to pay for bail, " + cpu1.name + " loses $50,000.");
						cpu1.jail = false;
					}
					if(menuSelect == 2){ //Roll for Doubles
						System.out.println(cpu1.name + " have chosen to roll for doubles. \nPress enter to roll:");
						dice1.roll();
						dice2.roll();
						//display first and second dice
						printDice(dice1);
						printDice(dice2);
						if(dice1.lastRoll() == dice2.lastRoll()){
							System.out.println("Congratulations, "+ cpu1.name + " rolled doubles! " + cpu1.name + " is now out of jail.");
							cpu1.jail = false;
						}
						else
							System.out.println(cpu1.name + " did not roll doubles. Better luck next time.");
					}
				}
        ///////-------------------------------------Check if the bots achieves a million dollars or goes bankrupt.-------------------------------------\\\\
        if(cpu1.money>=1000000){
          gameOver = true;
          System.out.println("David has achieved one million dollars!");
          if(gameOver==true)
            gameOverScreen();
        }
        //check if David wins the game
        if(cpu2.money>=1000000){
          gameOver = true;
          System.out.println("Karthik has achieved one million dollars!");
          if(gameOver==true)
            gameOverScreen();
        }
        //check if Karthik wins the game
        if(cpu3.money>=1000000){
          gameOver = true;
          System.out.println("Sahib has achieved one million dollars!");
          if(gameOver==true)
            gameOverScreen();
        }
        //check if Sahib wins the game              
				if(cpu1.jail != true && !(cpu1.money <= 0)){ ///////-------------------------------------When Player is NOT in Jail-------------------------------------\\\\
					System.out.println("It is now " + cpu1.name + "'s turn. Press enter to roll: ");
					input.nextLine();
					cpu1.move(dice1.roll() + dice2.roll());
					printBoard();
					if(cpu1.position > 32){ // Check if they pass the Start Position.
						cpu1.position -= 32;
						cpu1.money += 200000;
						System.out.println("Congratulations! " + cpu1.name + " have passed 'Go' and gained $200,000!");
						showMoney(cpu1);
				  }// End If Loop
				//display first and second dice
        printDice(dice1);
        printDice(dice2);
				System.out.println(cpu1.name + " rolled: " + (dice1.lastRoll() + dice2.lastRoll()) + ".");
				for(int i = 0; i < 32; i++){
					if(cpu1.position == tiles.get(i).position)
						currentTile = tiles.get(i).name;
				}
					System.out.println(cpu1.name + " are now at position " + cpu1.position + " and landed on " + currentTile + ".");
					for(int i = 0; i < 32; i++){
						if(cpu1.position == tiles.get(i).position){
							if(tiles.get(i) instanceof Place){ ///////-------------------------------------When Player lands on a Property-------------------------------------\\\\
								if(((Place)tiles.get(i)).owner != null && ((Place)tiles.get(i)).owner != cpu1){ // When player lands on another player's property
									System.out.println(cpu1.name + " landed on a property that someone else owns, " + cpu1.name + " must pay rent.");
									((Place)tiles.get(i)).payRent(cpu1);
									showMoney(cpu1);
									break;
                }
								if(((Place)tiles.get(i)).owner == cpu1){ // When player lands on their own property.
									System.out.println(cpu1.name + "landed on their own property, nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((Place)tiles.get(i)).owner == null){ // When player lands on an unowned property. 
									System.out.println(tiles.get(i).name + " is unowned. Would you like to buy it?");
									menuSelect = 0;
									if(rng.roll() > 7)
										menuSelect = 2;
									else
										menuSelect = 1;
									if(menuSelect == 1){ // When the player decides to buy the property.
										((Place)tiles.get(i)).buy(cpu1); 
										System.out.println(cpu1.name + " has purchased " + ((Place)tiles.get(i)).name + "!");
										showMoney(cpu1);
										System.out.println("Press enter to continue: ");
										input.nextLine();
										break;
									}
									if(menuSelect == 2){ // When player decides not to buy the property.
										System.out.println(cpu1.name + " decided to not buy the property.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
								}
							}
              ///////-------------------------------------When Player lands on a Action Property like Chance or Millionaire Lifestyle.-------------------------------------\\\\
							if(tiles.get(i) instanceof ActionSquare){ 
								if(((ActionSquare)tiles.get(i)).name == "Chance"){ // If player lands on a Chance Card Position.
									cardRng.roll(); // Picks random number between 1 and 5 to choose a card.
									if(cardRng.lastRoll() == 1)
										chanceCards.chance1(cpu1);
									if(cardRng.lastRoll() == 2)
										chanceCards.chance2(cpu1);
									if(cardRng.lastRoll() == 3)
										chanceCards.chance3(cpu1);
									if(cardRng.lastRoll() == 4)
										chanceCards.chance4(cpu1);
									if(cardRng.lastRoll() == 5)
										chanceCards.chance5(cpu1);
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Millionaire Lifestyle"){ // If player lands on a Millionaire Lifestyle Card Position.
									cardRng.roll(); // Picks random number between 1 and 5 to choose a card.
									if(cardRng.lastRoll() == 1)
										lifeStyleCards.lifeStyle1(cpu1);
									if(cardRng.lastRoll() == 2)
										lifeStyleCards.lifeStyle2(cpu1);
									if(cardRng.lastRoll() == 3)
										lifeStyleCards.lifeStyle3(cpu1);
									if(cardRng.lastRoll() == 4)
										lifeStyleCards.lifeStyle4(cpu1);
									if(cardRng.lastRoll() == 5)
										lifeStyleCards.lifeStyle5(cpu1);
									break;
								}
                ///////-------------------------------------When Player lands on the Corner Tiles of the Board.-------------------------------------\\\\
								if(((ActionSquare)tiles.get(i)).name == "Go"){ // When player lands on First Positon.
									System.out.println(cpu1.name + " is on 'Go', nothing happnes.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Go To Jail"){ // When player lands on "Go to Jail" Position, they are moved to Jail Position.
									cpu1.position = 9;
									cpu1.jail = true;
									System.out.println(cpu1.name + " is now in jail.\nPress enter to continue: ");
									input.nextLine();  
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Free Parking"){ // When player lands on "Free Parking" Position.
									System.out.println(cpu1.name + " is on 'Free Parking', nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Jail"){ // When player lands on the Jail Position.
									System.out.println(cpu1.name + " is on 'Just Visiting', nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
							}
						}
					}
					///////-------------------------------------Roll Doubles-------------------------------------\\\\
					while(dice1.lastRoll() == dice2.lastRoll()){ // Checks if the dices are the same value.
						doublesCount++; // Keep track of doubles rolled.
						if(doublesCount == 3){ // When 3 doubles in row, player is taken to jail.
							System.out.println("Sorry" + cpu1.name + "rolled doubles three times in a row. " + cpu1.name + " will proceed directly to jail.");
							cpu1.position = 9;
							cpu1.jail = true;
							break;
						}
						System.out.println("Congratulations! " + cpu1.name + " rolled doubles, " + cpu1.name + " gets to roll again. Press enter to roll: ");
						input.nextLine();
						cpu1.move(dice1.roll() + dice2.roll());
						printBoard();
						if(cpu1.position > 32){ // When player finishes a lap around the map.
							cpu1.position -= 32;
							cpu1.money += 200000;
							System.out.println("Congratulations! " + cpu1.name + " have passed go and gained $200,000!");
						}
            // Display first and second dice.
            printDice(dice1);
            printDice(dice2);
						System.out.println(cpu1.name + " rolled: " + (dice1.lastRoll() + dice2.lastRoll()) + "."); // Display dice rolls.
						for(int i = 0; i < 32; i++)
							if(cpu1.position == tiles.get(i).position)
								currentTile = tiles.get(i).name;
						System.out.println(cpu1.name + " is now at position " + cpu1.position + " and landed on " + currentTile + ".");
						for(int i = 0; i < 32; i++){
							if(cpu1.position == tiles.get(i).position){
								if(tiles.get(i) instanceof Place){ ///////-------------------------------------When Player lands on a Property-------------------------------------\\\\
									if(((Place)tiles.get(i)).owner != null){ // If Player lands on another player's property
										System.out.println(cpu1.name + " landed on a property someone else owns, you must pay rent.");
										((Place)tiles.get(i)).payRent(cpu1);
										showMoney(cpu1);
										break;
									}
									if(((Place)tiles.get(i)).owner == cpu1){ // If Player lands on their own property.
										System.out.println(cpu1.name + " landed on their own property, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((Place)tiles.get(i)).owner == null){ // If Player lands on an unowned property.
										System.out.println(tiles.get(i).name + " is unowned. Would you like to buy it?");
										menuSelect = 0;
										if(rng.roll() > 7)
											menuSelect = 2;
										else
											menuSelect = 1;
										if(menuSelect == 1){ // If player wants to purchase property.
											((Place)tiles.get(i)).buy(cpu1); 
											System.out.println(cpu1.name + " have purchased " + ((Place)tiles.get(i)).name + "!");
											showMoney(cpu1);
											System.out.println("Press enter to continue: ");
											input.nextLine();
											break;
										}
										if(menuSelect == 2){ // If player does not want to purchase property.
											System.out.println(cpu1.name + " decided to not buy the property.\n Press enter to continue: ");
											input.nextLine();
											break;
										}
									}
								}
								if(tiles.get(i) instanceof ActionSquare){ ///////-------------------------------------When Player lands on a Action Property like Chance or Millionaire Lifestyle.-------------------------------------\\\\
									if(((ActionSquare)tiles.get(i)).name == "Chance"){ //When player lands on a chance card position.
										cardRng.roll();
										if(cardRng.lastRoll() == 1)
											chanceCards.chance1(cpu1);
										if(cardRng.lastRoll() == 2)
											chanceCards.chance2(cpu1);
										if(cardRng.lastRoll() == 3)
											chanceCards.chance3(cpu1);
										if(cardRng.lastRoll() == 4)
											chanceCards.chance4(cpu1);
										if(cardRng.lastRoll() == 5)
											chanceCards.chance5(cpu1);
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Millionaire Lifestyle"){ // When player lands on a lifestyle card position.
										cardRng.roll();
										if(cardRng.lastRoll() == 1)
											lifeStyleCards.lifeStyle1(cpu1);
										if(cardRng.lastRoll() == 2)
											lifeStyleCards.lifeStyle2(cpu1);
										if(cardRng.lastRoll() == 3)
											lifeStyleCards.lifeStyle3(cpu1);
										if(cardRng.lastRoll() == 4)
											lifeStyleCards.lifeStyle4(cpu1);
										if(cardRng.lastRoll() == 5)
											lifeStyleCards.lifeStyle5(cpu1);
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Go"){ //When player lands on the Start Position.
										System.out.println(cpu1.name + " are on 'Go', nothing happnes.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Go To Jail"){ // When player lands on the "Go to Jail", they are moved to jail.
										cpu1.position = 9;
										cpu1.jail = true;
										System.out.println(cpu1.name + " is now in jail.\nPress enter to continue: ");
										input.nextLine();  
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Free Parking"){ // When player lands on "Free Parking" Position.
										System.out.println(cpu1.name + " are on Free Parking, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Jail"){ // When player lands on jail position.
										System.out.println(cpu1.name + " are on Just Visiting, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
								}
							}
						}
					}
					doublesCount = 0; 
        }

				///////-----------------------------------------------CPU 2's Turn -----------------------------------------------\\\\\\\
				if(cpu2.money <= 0){
					System.out.println(cpu2.name + " has been eliminated.\nPress enter to continue.");
					input.nextLine();
				}
				if(cpu2.jail == true && !(cpu2.money <= 0)){ ///////-------------------------------------When Player is in Jail-------------------------------------\\\\
					menuSelect = 0;
					if(rng.roll() > 7)
						menuSelect = 2;
					else
						menuSelect = 1; 
					if(menuSelect == 1){ // Pay to Bail
						System.out.println(cpu2.name + " decided to pay for bail, " + cpu2.name + " loses $50,000.");
						cpu2.jail = false;
					}
					if(menuSelect == 2){ //Roll for Doubles
						System.out.println(cpu2.name + " have chosen to roll for doubles. \nPress enter to roll:");
						dice1.roll();
						dice2.roll();
						//display first and second dice
						printDice(dice1);
						printDice(dice2);
						if(dice1.lastRoll() == dice2.lastRoll()){
							System.out.println("Congratulations, "+ cpu2.name + " rolled doubles! " + cpu2.name + " is now out of jail.");
							cpu2.jail = false;
						}
						else
							System.out.println(cpu2.name + " did not roll doubles. Better luck next time.");
					}
				}
        ///////-------------------------------------Check if the bots achieves a million dollars or goes bankrupt.-------------------------------------\\\\
        if(cpu2.money>=1000000){
          gameOver = true;
          System.out.println("David has achieved one million dollars!");
          if(gameOver==true)
            gameOverScreen();
        }
        //check if David wins the game
        if(cpu2.money>=1000000){
          gameOver = true;
          System.out.println("Karthik has achieved one million dollars!");
          if(gameOver==true)
            gameOverScreen();
        }
        //check if Karthik wins the game
        if(cpu3.money>=1000000){
          gameOver = true;
          System.out.println("Sahib has achieved one million dollars!");
          if(gameOver==true)
            gameOverScreen();
        }
        //check if Sahib wins the game              
				if(cpu2.jail != true && !(cpu2.money <= 0)){ ///////-------------------------------------When Player is NOT in Jail-------------------------------------\\\\
					System.out.println("It is now " + cpu2.name + "'s turn. Press enter to roll: ");
					input.nextLine();
					cpu2.move(dice1.roll() + dice2.roll());
					printBoard();
					if(cpu2.position > 32){ // Check if they pass the Start Position.
						cpu2.position -= 32;
						cpu2.money += 200000;
						System.out.println("Congratulations! " + cpu2.name + " have passed 'Go' and gained $200,000!");
						showMoney(cpu2);
				  }// End If Loop
				//display first and second dice
        printDice(dice1);
        printDice(dice2);
				System.out.println(cpu2.name + " rolled: " + (dice1.lastRoll() + dice2.lastRoll()) + ".");
				for(int i = 0; i < 32; i++){
					if(cpu2.position == tiles.get(i).position)
						currentTile = tiles.get(i).name;
				}
					System.out.println(cpu2.name + " are now at position " + cpu2.position + " and landed on " + currentTile + ".");
					for(int i = 0; i < 32; i++){
						if(cpu2.position == tiles.get(i).position){
							if(tiles.get(i) instanceof Place){ ///////-------------------------------------When Player lands on a Property-------------------------------------\\\\
								if(((Place)tiles.get(i)).owner != null && ((Place)tiles.get(i)).owner != cpu2){ // When player lands on another player's property
									System.out.println(cpu2.name + " landed on a property that someone else owns, " + cpu2.name + " must pay rent.");
									((Place)tiles.get(i)).payRent(cpu2);
									showMoney(cpu2);
									break;
                }
								if(((Place)tiles.get(i)).owner == cpu2){ // When player lands on their own property.
									System.out.println(cpu2.name + "landed on their own property, nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((Place)tiles.get(i)).owner == null){ // When player lands on an unowned property. 
									System.out.println(tiles.get(i).name + " is unowned. Would you like to buy it?");
									menuSelect = 0;
									if(rng.roll() > 7)
										menuSelect = 2;
									else
										menuSelect = 1;
									if(menuSelect == 1){ // When the player decides to buy the property.
										((Place)tiles.get(i)).buy(cpu2); 
										System.out.println(cpu2.name + " has purchased " + ((Place)tiles.get(i)).name + "!");
										showMoney(cpu2);
										System.out.println("Press enter to continue: ");
										input.nextLine();
										break;
									}
									if(menuSelect == 2){ // When player decides not to buy the property.
										System.out.println(cpu2.name + " decided to not buy the property.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
								}
							}
              ///////-------------------------------------When Player lands on a Action Property like Chance or Millionaire Lifestyle.-------------------------------------\\\\
							if(tiles.get(i) instanceof ActionSquare){ 
								if(((ActionSquare)tiles.get(i)).name == "Chance"){ // If player lands on a Chance Card Position.
									cardRng.roll(); // Picks random number between 1 and 5 to choose a card.
									if(cardRng.lastRoll() == 1)
										chanceCards.chance1(cpu2);
									if(cardRng.lastRoll() == 2)
										chanceCards.chance2(cpu2);
									if(cardRng.lastRoll() == 3)
										chanceCards.chance3(cpu2);
									if(cardRng.lastRoll() == 4)
										chanceCards.chance4(cpu2);
									if(cardRng.lastRoll() == 5)
										chanceCards.chance5(cpu2);
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Millionaire Lifestyle"){ // If player lands on a Millionaire Lifestyle Card Position.
									cardRng.roll(); // Picks random number between 1 and 5 to choose a card.
									if(cardRng.lastRoll() == 1)
										lifeStyleCards.lifeStyle1(cpu2);
									if(cardRng.lastRoll() == 2)
										lifeStyleCards.lifeStyle2(cpu2);
									if(cardRng.lastRoll() == 3)
										lifeStyleCards.lifeStyle3(cpu2);
									if(cardRng.lastRoll() == 4)
										lifeStyleCards.lifeStyle4(cpu2);
									if(cardRng.lastRoll() == 5)
										lifeStyleCards.lifeStyle5(cpu2);
									break;
								}
                ///////-------------------------------------When Player lands on the Corner Tiles of the Board.-------------------------------------\\\\
								if(((ActionSquare)tiles.get(i)).name == "Go"){ // When player lands on First Positon.
									System.out.println(cpu2.name + " is on 'Go', nothing happnes.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Go To Jail"){ // When player lands on "Go to Jail" Position, they are moved to Jail Position.
									cpu2.position = 9;
									cpu2.jail = true;
									System.out.println(cpu2.name + " is now in jail.\nPress enter to continue: ");
									input.nextLine();  
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Free Parking"){ // When player lands on "Free Parking" Position.
									System.out.println(cpu2.name + " is on 'Free Parking', nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Jail"){ // When player lands on the Jail Position.
									System.out.println(cpu2.name + " is on 'Just Visiting', nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
							}
						}
					}
					///////-------------------------------------Roll Doubles-------------------------------------\\\\
					while(dice1.lastRoll() == dice2.lastRoll()){ // Checks if the dices are the same value.
						doublesCount++; // Keep track of doubles rolled.
						if(doublesCount == 3){ // When 3 doubles in row, player is taken to jail.
							System.out.println("Sorry" + cpu2.name + "rolled doubles three times in a row. " + cpu2.name + " will proceed directly to jail.");
							cpu2.position = 9;
							cpu2.jail = true;
							break;
						}
						System.out.println("Congratulations! " + cpu2.name + " rolled doubles, " + cpu2.name + " gets to roll again. Press enter to roll: ");
						input.nextLine();
						cpu2.move(dice1.roll() + dice2.roll());
						printBoard();
						if(cpu2.position > 32){ // When player finishes a lap around the map.
							cpu2.position -= 32;
							cpu2.money += 200000;
							System.out.println("Congratulations! " + cpu2.name + " have passed go and gained $200,000!");
						}
            // Display first and second dice.
            printDice(dice1);
            printDice(dice2);
						System.out.println(cpu2.name + " rolled: " + (dice1.lastRoll() + dice2.lastRoll()) + "."); // Display dice rolls.
						for(int i = 0; i < 32; i++)
							if(cpu2.position == tiles.get(i).position)
								currentTile = tiles.get(i).name;
						System.out.println(cpu2.name + " is now at position " + cpu2.position + " and landed on " + currentTile + ".");
						for(int i = 0; i < 32; i++){
							if(cpu2.position == tiles.get(i).position){
								if(tiles.get(i) instanceof Place){ ///////-------------------------------------When Player lands on a Property-------------------------------------\\\\
									if(((Place)tiles.get(i)).owner != null){ // If Player lands on another player's property
										System.out.println(cpu2.name + " landed on a property someone else owns, you must pay rent.");
										((Place)tiles.get(i)).payRent(cpu2);
										showMoney(cpu2);
										break;
									}
									if(((Place)tiles.get(i)).owner == cpu2){ // If Player lands on their own property.
										System.out.println(cpu2.name + " landed on their own property, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((Place)tiles.get(i)).owner == null){ // If Player lands on an unowned property.
										System.out.println(tiles.get(i).name + " is unowned. Would you like to buy it?");
										menuSelect = 0;
										if(rng.roll() > 7)
											menuSelect = 2;
										else
											menuSelect = 1;
										if(menuSelect == 1){ // If player wants to purchase property.
											((Place)tiles.get(i)).buy(cpu2); 
											System.out.println(cpu2.name + " have purchased " + ((Place)tiles.get(i)).name + "!");
											showMoney(cpu2);
											System.out.println("Press enter to continue: ");
											input.nextLine();
											break;
										}
										if(menuSelect == 2){ // If player does not want to purchase property.
											System.out.println(cpu2.name + " decided to not buy the property.\n Press enter to continue: ");
											input.nextLine();
											break;
										}
									}
								}
								if(tiles.get(i) instanceof ActionSquare){ ///////-------------------------------------When Player lands on a Action Property like Chance or Millionaire Lifestyle.-------------------------------------\\\\
									if(((ActionSquare)tiles.get(i)).name == "Chance"){ //When player lands on a chance card position.
										cardRng.roll();
										if(cardRng.lastRoll() == 1)
											chanceCards.chance1(cpu2);
										if(cardRng.lastRoll() == 2)
											chanceCards.chance2(cpu2);
										if(cardRng.lastRoll() == 3)
											chanceCards.chance3(cpu2);
										if(cardRng.lastRoll() == 4)
											chanceCards.chance4(cpu2);
										if(cardRng.lastRoll() == 5)
											chanceCards.chance5(cpu2);
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Millionaire Lifestyle"){ // When player lands on a lifestyle card position.
										cardRng.roll();
										if(cardRng.lastRoll() == 1)
											lifeStyleCards.lifeStyle1(cpu2);
										if(cardRng.lastRoll() == 2)
											lifeStyleCards.lifeStyle2(cpu2);
										if(cardRng.lastRoll() == 3)
											lifeStyleCards.lifeStyle3(cpu2);
										if(cardRng.lastRoll() == 4)
											lifeStyleCards.lifeStyle4(cpu2);
										if(cardRng.lastRoll() == 5)
											lifeStyleCards.lifeStyle5(cpu2);
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Go"){ //When player lands on the Start Position.
										System.out.println(cpu2.name + " are on 'Go', nothing happnes.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Go To Jail"){ // When player lands on the "Go to Jail", they are moved to jail.
										cpu2.position = 9;
										cpu2.jail = true;
										System.out.println(cpu2.name + " is now in jail.\nPress enter to continue: ");
										input.nextLine();  
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Free Parking"){ // When player lands on "Free Parking" Position.
										System.out.println(cpu2.name + " are on Free Parking, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Jail"){ // When player lands on jail position.
										System.out.println(cpu2.name + " are on Just Visiting, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
								}
							}
						}
					}
					doublesCount = 0; 
        }
				
				///////-----------------------------------------------CPU 3's Turn -----------------------------------------------\\\\\\\
				if(cpu3.money <= 0){
					System.out.println(cpu2.name + " has been eliminated.\nPress enter to continue.");
					input.nextLine();
				}
				if(cpu3.jail == true && !(cpu3.money <= 0)){ ///////-------------------------------------When Player is in Jail-------------------------------------\\\\
					menuSelect = 0;
					if(rng.roll() > 7)
						menuSelect = 2;
					else
						menuSelect = 1; 
					if(menuSelect == 1){ // Pay to Bail
						System.out.println(cpu3.name + " decided to pay for bail, " + cpu3.name + " loses $50,000.");
						cpu3.jail = false;
					}
					if(menuSelect == 2){ //Roll for Doubles
						System.out.println(cpu3.name + " have chosen to roll for doubles. \nPress enter to roll:");
						dice1.roll();
						dice2.roll();
						//display first and second dice
						printDice(dice1);
						printDice(dice2);
						if(dice1.lastRoll() == dice2.lastRoll()){
							System.out.println("Congratulations, "+ cpu3.name + " rolled doubles! " + cpu3.name + " is now out of jail.");
							cpu3.jail = false;
						}
						else
							System.out.println(cpu3.name + " did not roll doubles. Better luck next time.");
					}
				}
        ///////-------------------------------------Check if the bots achieves a million dollars or goes bankrupt.-------------------------------------\\\\
        if(cpu3.money>=1000000){
          gameOver = true;
          System.out.println("David has achieved one million dollars!");
          if(gameOver==true)
            gameOverScreen();
        }
        //check if David wins the game
        if(cpu2.money>=1000000){
          gameOver = true;
          System.out.println("Karthik has achieved one million dollars!");
          if(gameOver==true)
            gameOverScreen();
        }
        //check if Karthik wins the game
        if(cpu3.money>=1000000){
          gameOver = true;
          System.out.println("Sahib has achieved one million dollars!");
          if(gameOver==true)
            gameOverScreen();
        }
        //check if Sahib wins the game              
				if(cpu3.jail != true && !(cpu3.money <= 0)){ ///////-------------------------------------When Player is NOT in Jail-------------------------------------\\\\
					System.out.println("It is now " + cpu3.name + "'s turn. Press enter to roll: ");
					input.nextLine();
					cpu3.move(dice1.roll() + dice2.roll());
					printBoard();
					if(cpu3.position > 32){ // Check if they pass the Start Position.
						cpu3.position -= 32;
						cpu3.money += 200000;
						System.out.println("Congratulations! " + cpu3.name + " have passed 'Go' and gained $200,000!");
						showMoney(cpu3);
				  }// End If Loop
				//display first and second dice
        printDice(dice1);
        printDice(dice2);
				System.out.println(cpu3.name + " rolled: " + (dice1.lastRoll() + dice2.lastRoll()) + ".");
				for(int i = 0; i < 32; i++){
					if(cpu3.position == tiles.get(i).position)
						currentTile = tiles.get(i).name;
				}
					System.out.println(cpu3.name + " are now at position " + cpu3.position + " and landed on " + currentTile + ".");
					for(int i = 0; i < 32; i++){
						if(cpu3.position == tiles.get(i).position){
							if(tiles.get(i) instanceof Place){ ///////-------------------------------------When Player lands on a Property-------------------------------------\\\\
								if(((Place)tiles.get(i)).owner != null && ((Place)tiles.get(i)).owner != cpu3){ // When player lands on another player's property
									System.out.println(cpu3.name + " landed on a property that someone else owns, " + cpu3.name + " must pay rent.");
									((Place)tiles.get(i)).payRent(cpu3);
									showMoney(cpu3);
									break;
                }
								if(((Place)tiles.get(i)).owner == cpu3){ // When player lands on their own property.
									System.out.println(cpu3.name + "landed on their own property, nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((Place)tiles.get(i)).owner == null){ // When player lands on an unowned property. 
									System.out.println(tiles.get(i).name + " is unowned. Would you like to buy it?");
									menuSelect = 0;
									if(rng.roll() > 7)
										menuSelect = 2;
									else
										menuSelect = 1;
									if(menuSelect == 1){ // When the player decides to buy the property.
										((Place)tiles.get(i)).buy(cpu3); 
										System.out.println(cpu3.name + " has purchased " + ((Place)tiles.get(i)).name + "!");
										showMoney(cpu3);
										System.out.println("Press enter to continue: ");
										input.nextLine();
										break;
									}
									if(menuSelect == 2){ // When player decides not to buy the property.
										System.out.println(cpu3.name + " decided to not buy the property.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
								}
							}
              ///////-------------------------------------When Player lands on a Action Property like Chance or Millionaire Lifestyle.-------------------------------------\\\\
							if(tiles.get(i) instanceof ActionSquare){ 
								if(((ActionSquare)tiles.get(i)).name == "Chance"){ // If player lands on a Chance Card Position.
									cardRng.roll(); // Picks random number between 1 and 5 to choose a card.
									if(cardRng.lastRoll() == 1)
										chanceCards.chance1(cpu3);
									if(cardRng.lastRoll() == 2)
										chanceCards.chance2(cpu3);
									if(cardRng.lastRoll() == 3)
										chanceCards.chance3(cpu3);
									if(cardRng.lastRoll() == 4)
										chanceCards.chance4(cpu3);
									if(cardRng.lastRoll() == 5)
										chanceCards.chance5(cpu3);
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Millionaire Lifestyle"){ // If player lands on a Millionaire Lifestyle Card Position.
									cardRng.roll(); // Picks random number between 1 and 5 to choose a card.
									if(cardRng.lastRoll() == 1)
										lifeStyleCards.lifeStyle1(cpu3);
									if(cardRng.lastRoll() == 2)
										lifeStyleCards.lifeStyle2(cpu3);
									if(cardRng.lastRoll() == 3)
										lifeStyleCards.lifeStyle3(cpu3);
									if(cardRng.lastRoll() == 4)
										lifeStyleCards.lifeStyle4(cpu3);
									if(cardRng.lastRoll() == 5)
										lifeStyleCards.lifeStyle5(cpu3);
									break;
								}
                ///////-------------------------------------When Player lands on the Corner Tiles of the Board.-------------------------------------\\\\
								if(((ActionSquare)tiles.get(i)).name == "Go"){ // When player lands on First Positon.
									System.out.println(cpu3.name + " is on 'Go', nothing happnes.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Go To Jail"){ // When player lands on "Go to Jail" Position, they are moved to Jail Position.
									cpu3.position = 9;
									cpu3.jail = true;
									System.out.println(cpu3.name + " is now in jail.\nPress enter to continue: ");
									input.nextLine();  
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Free Parking"){ // When player lands on "Free Parking" Position.
									System.out.println(cpu3.name + " is on 'Free Parking', nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
								if(((ActionSquare)tiles.get(i)).name == "Jail"){ // When player lands on the Jail Position.
									System.out.println(cpu3.name + " is on 'Just Visiting', nothing happens.\nPress enter to continue: ");
									input.nextLine();
									break;
								}
							}
						}
					}
					///////-------------------------------------Roll Doubles-------------------------------------\\\\
					while(dice1.lastRoll() == dice2.lastRoll()){ // Checks if the dices are the same value.
						doublesCount++; // Keep track of doubles rolled.
						if(doublesCount == 3){ // When 3 doubles in row, player is taken to jail.
							System.out.println("Sorry" + cpu3.name + "rolled doubles three times in a row. " + cpu3.name + " will proceed directly to jail.");
							cpu3.position = 9;
							cpu3.jail = true;
							break;
						}
						System.out.println("Congratulations! " + cpu3.name + " rolled doubles, " + cpu3.name + " gets to roll again. Press enter to roll: ");
						input.nextLine();
						cpu3.move(dice1.roll() + dice2.roll());
						printBoard();
						if(cpu3.position > 32){ // When player finishes a lap around the map.
							cpu3.position -= 32;
							cpu3.money += 200000;
							System.out.println("Congratulations! " + cpu3.name + " have passed go and gained $200,000!");
						}
            // Display first and second dice.
            printDice(dice1);
            printDice(dice2);
						System.out.println(cpu3.name + " rolled: " + (dice1.lastRoll() + dice2.lastRoll()) + "."); // Display dice rolls.
						for(int i = 0; i < 32; i++)
							if(cpu3.position == tiles.get(i).position)
								currentTile = tiles.get(i).name;
						System.out.println(cpu3.name + " is now at position " + cpu3.position + " and landed on " + currentTile + ".");
						for(int i = 0; i < 32; i++){
							if(cpu3.position == tiles.get(i).position){
								if(tiles.get(i) instanceof Place){ ///////-------------------------------------When Player lands on a Property-------------------------------------\\\\
									if(((Place)tiles.get(i)).owner != null){ // If Player lands on another player's property
										System.out.println(cpu3.name + " landed on a property someone else owns, you must pay rent.");
										((Place)tiles.get(i)).payRent(cpu3);
										showMoney(cpu3);
										break;
									}
									if(((Place)tiles.get(i)).owner == cpu3){ // If Player lands on their own property.
										System.out.println(cpu3.name + " landed on their own property, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((Place)tiles.get(i)).owner == null){ // If Player lands on an unowned property.
										System.out.println(tiles.get(i).name + " is unowned. Would you like to buy it?");
										menuSelect = 0;
										if(rng.roll() > 7)
											menuSelect = 2;
										else
											menuSelect = 1;
										if(menuSelect == 1){ // If player wants to purchase property.
											((Place)tiles.get(i)).buy(cpu3); 
											System.out.println(cpu3.name + " have purchased " + ((Place)tiles.get(i)).name + "!");
											showMoney(cpu3);
											System.out.println("Press enter to continue: ");
											input.nextLine();
											break;
										}
										if(menuSelect == 2){ // If player does not want to purchase property.
											System.out.println(cpu3.name + " decided to not buy the property.\n Press enter to continue: ");
											input.nextLine();
											break;
										}
									}
								}
								if(tiles.get(i) instanceof ActionSquare){ ///////-------------------------------------When Player lands on a Action Property like Chance or Millionaire Lifestyle.-------------------------------------\\\\
									if(((ActionSquare)tiles.get(i)).name == "Chance"){ //When player lands on a chance card position.
										cardRng.roll();
										if(cardRng.lastRoll() == 1)
											chanceCards.chance1(cpu3);
										if(cardRng.lastRoll() == 2)
											chanceCards.chance2(cpu3);
										if(cardRng.lastRoll() == 3)
											chanceCards.chance3(cpu3);
										if(cardRng.lastRoll() == 4)
											chanceCards.chance4(cpu3);
										if(cardRng.lastRoll() == 5)
											chanceCards.chance5(cpu3);
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Millionaire Lifestyle"){ // When player lands on a lifestyle card position.
										cardRng.roll();
										if(cardRng.lastRoll() == 1)
											lifeStyleCards.lifeStyle1(cpu3);
										if(cardRng.lastRoll() == 2)
											lifeStyleCards.lifeStyle2(cpu3);
										if(cardRng.lastRoll() == 3)
											lifeStyleCards.lifeStyle3(cpu3);
										if(cardRng.lastRoll() == 4)
											lifeStyleCards.lifeStyle4(cpu3);
										if(cardRng.lastRoll() == 5)
											lifeStyleCards.lifeStyle5(cpu3);
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Go"){ //When player lands on the Start Position.
										System.out.println(cpu3.name + " are on 'Go', nothing happnes.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Go To Jail"){ // When player lands on the "Go to Jail", they are moved to jail.
										cpu3.position = 9;
										cpu3.jail = true;
										System.out.println(cpu3.name + " is now in jail.\nPress enter to continue: ");
										input.nextLine();  
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Free Parking"){ // When player lands on "Free Parking" Position.
										System.out.println(cpu3.name + " are on Free Parking, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
									if(((ActionSquare)tiles.get(i)).name == "Jail"){ // When player lands on jail position.
										System.out.println(cpu3.name + " are on Just Visiting, nothing happens.\nPress enter to continue: ");
										input.nextLine();
										break;
									}
								}
							}
						}
					}
					doublesCount = 0; 
        }
			} // End While Loop 
		} //End If Statement 
  }// End Main


  ///////-------------------------------------FUNCTIONS.-------------------------------------\\\\
  
	//The following function takes no parameters and returns no values and just prints out the Game Title.
  public static void printSprite(){   
    System.out.println();
    System.out.println("███╗░░░███╗░█████╗░███╗░░██╗░█████╗░██████╗░░█████╗░██╗░░░░░██╗░░░██╗");
    System.out.println("████╗░████║██╔══██╗████╗░██║██╔══██╗██╔══██╗██╔══██╗██║░░░░░╚██╗░██╔╝");
    System.out.println("██╔████╔██║██║░░██║██╔██╗██║██║░░██║██████╔╝██║░░██║██║░░░░░░╚████╔╝░");
    System.out.println("██║╚██╔╝██║██║░░██║██║╚████║██║░░██║██╔═══╝░██║░░██║██║░░░░░░░╚██╔╝░░");
    System.out.println("██║░╚═╝░██║╚█████╔╝██║░╚███║╚█████╔╝██║░░░░░╚█████╔╝███████╗░░░██║░░░");
    System.out.println("╚═╝░░░░░╚═╝░╚════╝░╚═╝░░╚══╝░╚════╝░╚═╝░░░░░░╚════╝░╚══════╝░░░╚═╝░░░");
    System.out.println();
    System.out.println("███╗░░░███╗██╗██╗░░░░░██╗░░░░░██╗░█████╗░███╗░░██╗░█████╗░██╗██████╗░███████╗");
    System.out.println("████╗░████║██║██║░░░░░██║░░░░░██║██╔══██╗████╗░██║██╔══██╗██║██╔══██╗██╔════╝");
    System.out.println("██╔████╔██║██║██║░░░░░██║░░░░░██║██║░░██║██╔██╗██║███████║██║██████╔╝█████╗░░");
    System.out.println("██║╚██╔╝██║██║██║░░░░░██║░░░░░██║██║░░██║██║╚████║██╔══██║██║██╔══██╗██╔══╝░░");
    System.out.println("██║░╚═╝░██║██║███████╗███████╗██║╚█████╔╝██║░╚███║██║░░██║██║██║░░██║███████╗");
    System.out.println("╚═╝░░░░░╚═╝╚═╝╚══════╝╚══════╝╚═╝░╚════╝░╚═╝░░╚══╝╚═╝░░╚═╝╚═╝╚═╝░░╚═╝╚══════╝");
  } // End of printSprite

  //This function takes no paramaters and returns no values and displays the Main Menu
  public static void printMenuScreen(){
    System.out.println();
		System.out.println("Welcome to Monopoly Millionaire (enter number): ");
		System.out.println();
		System.out.println("1. Play");
		System.out.println("2. Information");
		System.out.println("3. Exit");
		System.out.println();
		while(menuSelect < 1 || menuSelect > 3){
			try{
				if(menuError == true){
					System.out.println("Sorry, please enter a valid input (enter number): ");
					System.out.println();
					System.out.println("1. Play");
					System.out.println("2. Information");
					System.out.println("3. Exit");
					System.out.println();
				}
				menuError = true;
				userInput = input.nextLine();
				menuSelect = Integer.parseInt(userInput);
				System.out.println();
			}catch(NumberFormatException er){
				menuSelect = 0;
			}
		}
		menuError = false;
	} // End of menuScreen

  //The following function has no parameters and no return values and displays the menu for when player is in jail.
	public static void printJailMenu(){
		System.out.println("Sorry you are in jail. You may either roll for doubles or you may pay the bail of $50,000"); 
		System.out.println("Please choose an option (enter number): ");
		System.out.println();
		System.out.println("1. Pay Bail");
		System.out.println("2. Roll for Doubles");
		System.out.println();
		while(menuSelect < 1 || menuSelect > 2){
			try{
				if(menuError == true){
					System.out.println("Sorry, please enter a valid input (enter number): ");
					System.out.println();
					System.out.println("1. Pay Bail");
					System.out.println("2. Roll for Doubles");
					System.out.println();
				}
				menuError = true;
				userInput = input.nextLine();
				menuSelect = Integer.parseInt(userInput);
				System.out.println();
			}catch(NumberFormatException er){
				menuSelect = 0;
			}
		}
		menuError = false;
	} // End of menuScreen

	public static void printEndTurnMenu(){
    System.out.println();
		System.out.println("Your turn is over, please choose an option: (Enter number)");
		System.out.println();
		System.out.println("1. Mortgage Property");
		System.out.println("2. Unmortgage Property");
		System.out.println("3. Build Houses/Hotels");
		System.out.println("4. List Owned Places");
		System.out.println("5. End Turn");
		System.out.println();
		while(menuSelect < 1 || menuSelect > 5){
			try{
				if(menuError == true){
					System.out.println("Your turn is over, please choose an option: (Enter number)");
					System.out.println();
					System.out.println("1. Mortgage Property");
					System.out.println("2. Unmortgage Property");
					System.out.println("3. Build Houses/Hotels");
					System.out.println("4. List Owned Places");
					System.out.println("5. End Turn");
					System.out.println();
				}
				menuError = true;
				userInput = input.nextLine();
				menuSelect = Integer.parseInt(userInput);
				System.out.println();
			}catch(NumberFormatException er){
				menuSelect = 0;
			}
		}
		menuError = false;
	} // End of menuScreen

  //The following function has no parameters and no return values and displays the Buy Menu.
	public static void printBuyMenu(){
		System.out.println("Please choose an option (enter number): ");
		System.out.println();
		System.out.println("1. Buy");
		System.out.println("2. Don't Buy");
		System.out.println();
		while(menuSelect < 1 || menuSelect > 2){
			try{
				if(menuError == true){
					System.out.println("Sorry, please enter a valid input (enter number): ");
					System.out.println();
					System.out.println("1. Buy");
					System.out.println("2. Dont' Buy");
					System.out.println();
				}
				menuError = true;
				userInput = input.nextLine();
				menuSelect = Integer.parseInt(userInput);
				System.out.println();
			}catch(NumberFormatException er){
				menuSelect = 0;
			}
		}
		menuError = false;
	} // End of menuScreen
  
  //This funciton has no parameters and no return values and displays the game information.
  public static void printInfoScreen(){
    System.out.println("GAME INFORMATION:");
    System.out.println("All players in the game will start with a balance of $372k and the goal is to reach $1M first.\nThe user will always start first, followed by the other 3 bots.\nThe player will roll the dice to determine any action taken.\nUsers can purchase properties, claim cards, mortgage properties, build houses/hotels, etc.\nThe first player to reach the goal of one million dollars is victorious!");
  } // End of printInfoScreen

  //This function has no parameters and one return value and asks the user for their name.
  public static String getName(){
    System.out.println("Please enter your name:");
    String userName = input.nextLine();
    System.out.println("Press enter to start!");  
    String enterKey = input.nextLine();
    System.out.println();
    return userName;
  } // End of getName

  //This function has no parameters and no return values and displays the 
	public static void printTurnScreen(){
    System.out.println();
		System.out.println("Please choose an option (enter integer): ");
		System.out.println();
		System.out.println("1. Roll");
		System.out.println("2. Information");
		System.out.println("3. Exit");
		System.out.println();
		while(menuSelect < 1 || menuSelect > 3){
			try{
				if(menuError == true){
					System.out.println("Sorry, please enter a valid input (enter integer): ");
					System.out.println();
					System.out.println("1. Play");
					System.out.println("2. Information");
					System.out.println("3. Exit");
					System.out.println();
				}
				menuError = true;
				userInput = input.nextLine();
				menuSelect = Integer.parseInt(userInput);
				System.out.println();
			}catch(NumberFormatException er){
				menuSelect = 0;
			}
		}
		menuError = false;
	} // End of printTurnScreen

	public static void player1Turn(Player player1, Dice dice1, Dice dice2){
		System.out.println(player1.name + " , press enter to roll: ");
		input.nextLine();
		player1.move(dice1.roll() + dice2.roll());
		// for(int i = 0; i < )
		System.out.println("You moved " + (dice1.lastRoll() + dice2.lastRoll()) + " spaces and are now on ");
	} // End of player1Turn

  public static void printDice(Dice dice){
		if(dice.lastRoll()==1)
			printDice1();
		if(dice.lastRoll()==2)
			printDice2();
		if(dice.lastRoll()==3)
			printDice3();
		if(dice.lastRoll()==4)
			printDice4();
		if(dice.lastRoll()==5)
			printDice5();
		if(dice.lastRoll()==6)
			printDice6();
	} //End of printDice
	
	public static void showMoney(Player player){
		if(player instanceof HumanPlayer)
			System.out.println("You have $" + player.money + ".");
		else
			System.out.println(player.name + " have $" + player.money + ".");
	}

  //Functions to print face of the dice
  public static void printDice1(){
    System.out.println(" ----- ");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+"     "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+"  "+ANSI_BLACK+"o  "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+"     "+ANSI_RESET+"|");
    System.out.println(" ----- ");
  } // End of printDice1
  public static void printDice2(){
    System.out.println(" ----- ");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+" "+ANSI_BLACK+"o   "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+"     "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+"   "+ANSI_BLACK+"o "+ANSI_RESET+"|");
    System.out.println(" ----- ");
  } // End of printDice2
  public static void printDice3(){
    System.out.println(" ----- ");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+" "+ANSI_BLACK+"o   "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+"  "+ANSI_BLACK+"o  "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+"   "+ANSI_BLACK+"o "+ANSI_RESET+"|");
    System.out.println(" ----- ");
  } // End of printDice3
  public static void printDice4(){
    System.out.println(" ----- ");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+" "+ANSI_BLACK+"o o "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+"     "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+" "+ANSI_BLACK+"o o "+ANSI_RESET+"|");
    System.out.println(" ----- ");
  } // End of printDice4
  public static void printDice5(){
    System.out.println(" ----- ");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+" "+ANSI_BLACK+"o o "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+"  "+ANSI_BLACK+"o  "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+" "+ANSI_BLACK+"o o "+ANSI_RESET+"|");
    System.out.println(" ----- ");
  } // End of printDice5
  public static void printDice6(){
    System.out.println(" ----- ");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+" "+ANSI_BLACK+"o o "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+" "+ANSI_BLACK+"o o "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_WHITE_BACKGROUND+" "+ANSI_BLACK+"o o "+ANSI_RESET+"|");
    System.out.println(" ----- ");
  } // End of printDice6

  public static void printBoard(){
    System.out.println("+--------------+--------------+--------------+-------------+-------------+-------------+-------------+-------------+--------------+");
    System.out.println("|  Free        |"+ANSI_RED_BACKGROUND+"  Style       "+ANSI_RESET+"|    Chance    |"+ANSI_RED_BACKGROUND+" Party       "+ANSI_RESET+"|"+ANSI_RED_BACKGROUND+" Showtime    "+ANSI_RESET+"|"+ANSI_YELLOW_BACKGROUND+" Sunshine    "+ANSI_RESET+"|"+ANSI_YELLOW_BACKGROUND+" Bling       "+ANSI_RESET+"|"+ANSI_YELLOW_BACKGROUND+" Yacht       "+ANSI_RESET+"|  Go-To-Jail  |");
    System.out.println("|    Parking   |"+ANSI_RED_BACKGROUND+"    Square    "+ANSI_RESET+"|              |"+ANSI_RED_BACKGROUND+"    Plaza    "+ANSI_RESET+"|"+ANSI_RED_BACKGROUND+"  Boulevard  "+ANSI_RESET+"|"+ANSI_YELLOW_BACKGROUND+"     Bay     "+ANSI_RESET+"|"+ANSI_YELLOW_BACKGROUND+"    Beach    "+ANSI_RESET+"|"+ANSI_YELLOW_BACKGROUND+"    Harbor   "+ANSI_RESET+"|              |");
    System.out.println("|              |"+ANSI_RED_BACKGROUND+"              "+ANSI_RESET+"|              |"+ANSI_RED_BACKGROUND+"             "+ANSI_RESET+"|"+ANSI_RED_BACKGROUND+"             "+ANSI_RESET+"|"+ANSI_YELLOW_BACKGROUND+"             "+ANSI_RESET+"|"+ANSI_YELLOW_BACKGROUND+"             "+ANSI_RESET+"|"+ANSI_YELLOW_BACKGROUND+"             "+ANSI_RESET+"|              |");
    System.out.println("+--------------+--------------+--------------+-------------+-------------+-------------+-------------+-------------+--------------+");
    System.out.println("|"+ANSI_BLUE_BACKGROUND+"  Movie       "+ANSI_RESET+"|                                                                                                   |"+ANSI_GREEN_BACKGROUND+"  Treetop     "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_BLUE_BACKGROUND+"   District   "+ANSI_RESET+"|                                                                                                   |"+ANSI_GREEN_BACKGROUND+"    Retreat   "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_BLUE_BACKGROUND+"              "+ANSI_RESET+"|                                                                                                   |"+ANSI_GREEN_BACKGROUND+"              "+ANSI_RESET+"|");
    System.out.println("+--------------+                                                                                                   +--------------+");
    System.out.println("|"+ANSI_BLUE_BACKGROUND+"  Themepark   "+ANSI_RESET+"|                                                                                                   |"+ANSI_GREEN_BACKGROUND+"  Ski         "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_BLUE_BACKGROUND+"     City     "+ANSI_RESET+"|                         ╭━╮╭━┳━━━┳━╮╱╭┳━━━┳━━━┳━━━┳╮╱╭╮╱╱╭╮                                       |"+ANSI_GREEN_BACKGROUND+"   Mountain   "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_BLUE_BACKGROUND+"              "+ANSI_RESET+"|                         ┃┃╰╯┃┃╭━╮┃┃╰╮┃┃╭━╮┃╭━╮┃╭━╮┃┃╱┃╰╮╭╯┃                                       |"+ANSI_GREEN_BACKGROUND+"              "+ANSI_RESET+"|");
    System.out.println("+--------------+                         ┃╭╮╭╮┃┃╱┃┃╭╮╰╯┃┃╱┃┃╰━╯┃┃╱┃┃┃╱╰╮╰╯╭╯                                       +--------------+");
    System.out.println("|  Millionaire |                         ┃┃┃┃┃┃┃╱┃┃┃╰╮┃┃┃╱┃┃╭━━┫┃╱┃┃┃╱╭╋╮╭╯                                        | Millionaire  |");
    System.out.println("|   Lifestyle  |                         ┃┃┃┃┃┃╰━╯┃┃╱┃┃┃╰━╯┃┃╱╱┃╰━╯┃╰━╯┃┃┃                                         |   Lifestyle  |");
    System.out.println("|              |                         ╰╯╰╯╰┻━━━┻╯╱╰━┻━━━┻╯╱╱╰━━━┻━━━╯╰╯                                         |              |");
    System.out.println("+--------------+                         ╭━╮╭━┳━━┳╮╱╱╭╮╱╱╭━━┳━━━┳━╮╱╭┳━━━┳━━┳━━━┳━━━╮                              +--------------+");
    System.out.println("|"+ANSI_BLUE_BACKGROUND+"  Adventure   "+ANSI_RESET+"|                         ┃┃╰╯┃┣┫┣┫┃╱╱┃┃╱╱╰┫┣┫╭━╮┃┃╰╮┃┃╭━╮┣┫┣┫╭━╮┃╭━━╯                              |"+ANSI_GREEN_BACKGROUND+"  Diamond     "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_BLUE_BACKGROUND+"     Park     "+ANSI_RESET+"|                         ┃╭╮╭╮┃┃┃┃┃╱╱┃┃╱╱╱┃┃┃┃╱┃┃╭╮╰╯┃┃╱┃┃┃┃┃╰━╯┃╰━━╮                              |"+ANSI_GREEN_BACKGROUND+"     Hills    "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_BLUE_BACKGROUND+"              "+ANSI_RESET+"|                         ┃┃┃┃┃┃┃┃┃┃╱╭┫┃╱╭╮┃┃┃┃╱┃┃┃╰╮┃┃╰━╯┃┃┃┃╭╮╭┫╭━━╯                              |"+ANSI_GREEN_BACKGROUND+"              "+ANSI_RESET+"|");
    System.out.println("+--------------+                         ┃┃┃┃┃┣┫┣┫╰━╯┃╰━╯┣┫┣┫╰━╯┃┃╱┃┃┃╭━╮┣┫┣┫┃┃╰┫╰━━╮                              +--------------+");
    System.out.println("|"+ANSI_PURPLE_BACKGROUND+"  Palace      "+ANSI_RESET+"|                         ╰╯╰╯╰┻━━┻━━━┻━━━┻━━┻━━━┻╯╱╰━┻╯╱╰┻━━┻╯╰━┻━━━╯                              |   Chance     |");
    System.out.println("|"+ANSI_PURPLE_BACKGROUND+"    Gardens   "+ANSI_RESET+"|                                   Created by: Sahib, David, Karthik                               |              |");
    System.out.println("|"+ANSI_PURPLE_BACKGROUND+"              "+ANSI_RESET+"|                                                                                                   |              |");
    System.out.println("+--------------+                                                                                                   +--------------+");
    System.out.println("|"+ANSI_PURPLE_BACKGROUND+"  Dream       "+ANSI_RESET+"|                                                                                                   |"+ANSI_WHITE_BACKGROUND+"  "+ANSI_BLACK+"Fortune     "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_PURPLE_BACKGROUND+"    Avenue    "+ANSI_RESET+"|                                                                                                   |"+ANSI_WHITE_BACKGROUND+"    "+ANSI_BLACK+"Valley    "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_PURPLE_BACKGROUND+"              "+ANSI_RESET+"|                                                                                                   |"+ANSI_WHITE_BACKGROUND+"       "+ANSI_BLACK+"       "+ANSI_RESET+"|");
    System.out.println("+--------------+                                                                                                   +--------------+");
    System.out.println("|"+ANSI_PURPLE_BACKGROUND+" Castle       "+ANSI_RESET+"|                                                                                                   |"+ANSI_WHITE_BACKGROUND+"  "+ANSI_BLACK+"Paradise    "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_PURPLE_BACKGROUND+"     View     "+ANSI_RESET+"|                                                                                                   |"+ANSI_WHITE_BACKGROUND+"    "+ANSI_BLACK+"Island    "+ANSI_RESET+"|");
    System.out.println("|"+ANSI_PURPLE_BACKGROUND+"              "+ANSI_RESET+"|                                                                                                   |"+ANSI_WHITE_BACKGROUND+"       "+ANSI_BLACK+"       "+ANSI_RESET+"|");
    System.out.println("+--------------+--------------+--------------+-------------+-------------+-------------+-------------+-------------+--------------+");
    System.out.println("|  Jail/Just - |"+ANSI_CYAN_BACKGROUND+"  Lakeside    "+ANSI_RESET+"|"+ANSI_CYAN_BACKGROUND+"  Aqua Park   "+ANSI_RESET+"|    Chance   |"+ANSI_CYAN_BACKGROUND+" Surfer's    "+ANSI_RESET+"|"+ANSI_BLACK_BACKGROUND+" Gadget      "+ANSI_RESET+"| Millionaire |"+ANSI_BLACK_BACKGROUND+" Motor       "+ANSI_RESET+"|      GO!     |");
    System.out.println("|   Visiting   |"+ANSI_CYAN_BACKGROUND+"    Marina    "+ANSI_RESET+"|"+ANSI_CYAN_BACKGROUND+"    Resort    "+ANSI_RESET+"|             |"+ANSI_CYAN_BACKGROUND+"     Cove    "+ANSI_RESET+"|"+ANSI_BLACK_BACKGROUND+"    Wharf    "+ANSI_RESET+"|  Lifestyle  |"+ANSI_BLACK_BACKGROUND+"    Drive    "+ANSI_RESET+"|              |");
    System.out.println("|              |"+ANSI_CYAN_BACKGROUND+"              "+ANSI_RESET+"|"+ANSI_CYAN_BACKGROUND+"              "+ANSI_RESET+"|             |"+ANSI_CYAN_BACKGROUND+"             "+ANSI_RESET+"|"+ANSI_BLACK_BACKGROUND+"             "+ANSI_RESET+"|             |"+ANSI_BLACK_BACKGROUND+"             "+ANSI_RESET+"|              |");
    System.out.println("+--------------+--------------+--------------+-------------+-------------+-------------+-------------+-------------+--------------+");
    System.out.println();
  } //End of printBoard

  public static void victoryScreen(){
    System.out.println("██╗░░░██╗░█████╗░██╗░░░██╗  ░██╗░░░░░░░██╗░█████╗░███╗░░██╗██╗");
    System.out.println("╚██╗░██╔╝██╔══██╗██║░░░██║  ░██║░░██╗░░██║██╔══██╗████╗░██║██║");
    System.out.println("░╚████╔╝░██║░░██║██║░░░██║  ░╚██╗████╗██╔╝██║░░██║██╔██╗██║██║");
    System.out.println("░░╚██╔╝░░██║░░██║██║░░░██║  ░░████╔═████║░██║░░██║██║╚████║╚═╝");
    System.out.println("░░░██║░░░╚█████╔╝╚██████╔╝  ░░╚██╔╝░╚██╔╝░╚█████╔╝██║░╚███║██╗");
    System.out.println("░░░╚═╝░░░░╚════╝░░╚═════╝░  ░░░╚═╝░░░╚═╝░░░╚════╝░╚═╝░░╚══╝╚═╝");
    System.out.println("Congratulations on your victory! Thanks for playing!");
    System.out.println("The program will now end, press 'run' if you wish to play again.");
    System.out.println("Thanks for playing!");
  }

  public static void gameOverScreen(){
    System.out.println("███╗░░██╗██╗░█████╗░███████╗  ████████╗██████╗░██╗░░░██╗██╗");
    System.out.println("████╗░██║██║██╔══██╗██╔════╝  ╚══██╔══╝██╔══██╗╚██╗░██╔╝██║");
    System.out.println("██╔██╗██║██║██║░░╚═╝█████╗░░  ░░░██║░░░██████╔╝░╚████╔╝░██║");
    System.out.println("██║╚████║██║██║░░██╗██╔══╝░░  ░░░██║░░░██╔══██╗░░╚██╔╝░░╚═╝");
    System.out.println("██║░╚███║██║╚█████╔╝███████╗  ░░░██║░░░██║░░██║░░░██║░░░██╗");
    System.out.println("╚═╝░░╚══╝╚═╝░╚════╝░╚══════╝  ░░░╚═╝░░░╚═╝░░╚═╝░░░╚═╝░░░╚═╝");
    System.out.println("Nice try! Thanks for playing!");
    System.out.println("The program will now end, press 'run' if you wish to play again.");
    System.out.println("Thanks for playing!");
  }
}