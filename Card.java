/*
  David Nong-Ang, Sahib Johar, Karthik Kodeswaran
  17/01/2021
  Card Class
  This is code for the Card Class
*/

public class Card{
	// Instance Variables
	public final int length;
	public final int width;
	public final int cardID;
	public final String cardType;

	// Constructors
	public Card(int length, int width, int cardID, String cardType){
		this.length = length;
		this.width = width;
		this.cardID = cardID;
		this.cardType = cardType;
	}

  //Methods

  //This function takes a Player object as its parameters and has no return values. It is used for when a player lands on a Chance card position on the baord.
  public void chance1(Player player){
    System.out.println("Take a Visit!\nTake a quick visit to the jail.");
    player.position = 9;
  }
  //This function takes a Player object as its parameters and has no return values. It is used for when a player lands on a Chance card position on the baord.
  public void chance2(Player player){
    System.out.println("Donation!\nDonate $50,000 to the local animal shelter. ");
    player.money -= 50000;
    Main.showMoney(player);
  }
  //This function takes a Player object as its parameters and has no return values. It is used for when a player lands on a Chance card position on the baord.
  public void chance3(Player player){
    System.out.println("You are Arrested!\nGo directly to jail.");
    player.position = 9;
    player.jail = true;
  }
  //This function takes a Player object as its parameters and has no return values. It is used for when a player lands on a Chance card position on the baord.
  public void chance4(Player player){
    System.out.println("Back to Basics!\nPlayer is moved back to first position.");
    player.position = 1;
  }
  //This function takes a Player object as its parameters and has no return values. It is used for when a player lands on a Chance card position on the baord.
  public void chance5(Player player){
    System.out.println("Uber!\nPlayer moves up 3 position.");
    player.position += 3;
  }
  //This function takes a Player object as its parameters and has no return values. It is used for when a player lands on a Millionaire Lifestyle card position on the baord.
  public void lifeStyle1(Player player){
    System.out.println("Crypto Genius!\nPlayer Gains 25% of their current money.");
    player.money *= 1.25;
    Main.showMoney(player);
  }
  //This function takes a Player object as its parameters and has no return values. It is used for when a player lands on a Millionaire Lifestyle card position on the baord.
  public void lifeStyle2(Player player){
    System.out.println("Stocks Crash!\nPlayer Loses 25% of their current money.");
    player.money *= 0.75; 
    Main.showMoney(player);
  }
  //This function takes a Player object as its parameters and has no return values. It is used for when a player lands on a Millionaire Lifestyle card position on the baord.
  public void lifeStyle3(Player player){
    System.out.println("Party Gone Wrong!\nPlayer hosts a celebrity party and loses $100,000 from property damage");
    player.money -= 100000;
    Main.showMoney(player);
  }
  //This function takes a Player object as its parameters and has no return values. It is used for when a player lands on a Millionaire Lifestyle card position on the baord.
  public void lifeStyle4(Player player){
    System.out.println("Buy low Sell High?\nPlayer makes a profit of $20,0000 from stocks");
    player.money += 200000;
    Main.showMoney(player);
  }
  //This function takes a Player object as its parameters and has no return values. It is used for when a player lands on a Millionaire Lifestyle card position on the baord.
  public void lifeStyle5(Player player){
    System.out.println("Business is Blooming!\nPlayer opens a small business and earns $250,000.");
    player.money += 250000;
    Main.showMoney(player);
  }
}