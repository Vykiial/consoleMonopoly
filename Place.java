/*
  David Nong-Ang, Sahib Johar, Karthik Kodeswaran
  17/01/2021
  Place Class
  This is code for the Place Class
*/

import java.util.ArrayList;

public class Place extends Square{ //Subclass extending from Square Class

	// Instance Variables
	public final int price;
	public final int intialRent;
	public int rent;
	public double rentMultiplier;
	public final int mortgage;
	private boolean mortgaged = false;
	public final int length = 0;
	public final int width = 0;
	public Player owner = null;
	public ArrayList<House> houses = new ArrayList<House>();
	public Hotel hotel = null;
	public ArrayList<Place> set = new ArrayList<Place>(3);

	// Constructors
	public Place(String name, int price, int rent, int mortgage, int position){
		super(name, position);
		this.name = name;
		this.price = price;
		this.rent = rent;
		this.intialRent = rent;
		this.mortgage = mortgage;
		this.position = position;
	}
	// Methods

  //Function used to "buy" a place
	public void buy(Player buyer){
		if(this.owner == null){
			if(buyer.money >= this.price){
				if(buyer.position == this.position){
					this.owner = buyer;
					this.owner.money -= this.price;
					this.owner.addProperty(this);
				}
				else
					System.out.println("Sorry you must be on the property to purchase it.");
			}
			else
				System.out.println("Sorry you cannot afford this property, the price is $" + this.price + ".");
		}
		else
			System.out.println("This property is already owned by " + this.owner + ", you cannot purchase it. Please initiate a trade instead.");
	}

	// Used to add money to the owner as well as change the mortgaged variable to true
	public void mortgage(){
		if(this.mortgaged == false){
			this.owner.money += this.mortgage;
			this.mortgaged = true;
		}
		else
			System.out.println("Sorry this property has already been mortgaged.");
	}
 
 	// Used to add money to the owner as well as change the mortgaged variable to true
	public boolean mortgage(Player owner){ // Overloading method.
		if(this.owner == owner){
			if(this.mortgaged == false){
				this.owner.money += this.mortgage;
				this.mortgaged = true;
				return true;
			}
			else{
				System.out.println("Sorry this property has already been mortgaged.");
				return false;
			}
		}
		else{
			System.out.println("Sorry you don't own this property.");
			return false;
		}
	}

	// Used to remove money to the owner as well as change the mortgaged variable to false
	public boolean unMortgage(Player owner){
		if(this.owner == owner){
			if(this.mortgaged == true){
				this.owner.money -= this.mortgage;
				this.mortgaged = false;
				return true;
			}
			else{
				System.out.println("Sorry this property hasn't been mortgaged.");
				return false;
			}
		}
		else{
			System.out.println("Sorry you don't own this property.");
			return false;
		}
	}
	
	// Used to change the owner of the place object
	public void changeOwnership(Player newOwner){
		if(this.owner != null)
			this.owner = newOwner;
		else
			System.out.println("Please buy this property first.");
	}

	// Used to create and add a house object to the houses arraylist
	public boolean buildHouse(Player owner){
		if(owner == this.owner){
			if(this.owner.money >= (this.price / 2)){
				this.rent = this.intialRent;
				House house = new House(this, (this.price / 2), 1.5, .5, .5);
				this.houses.add(house);
				this.rentMultiplier += this.houses.get(0).rentMultiplier;
				this.owner.money -= this.houses.get(0).price;
				this.rent *= rentMultiplier;
				return true;
			}
			else{
				System.out.println("Sorry you don't have enough money to buy a house here, it costs $" + this.houses.get(0).price + " and you have $" + this.owner.money + " .");
				return false;
			}
		}
		else{
			System.out.println("Sorry you don't own this property.");
			return false;
		}
	}

	// Used to create and add a hotel object to the place as well as remove any house objects
	public boolean buildHotel(Player player){
		if(player == this.owner){
			if(this.houses.get(3) != null && this.owner.money >= this.houses.get(0).price){
				this.hotel = new Hotel(this, this.houses.get(0).price, 10.00, .5, .5, this.houses.get(0), this.houses.get(1), this.houses.get(2), this.houses.get(3));
				this.rentMultiplier = this.hotel.rentMultiplier;
				this.rent = this.intialRent;
				this.rent *= this.rentMultiplier;
				this.owner.money -= this.hotel.price;
				this.houses.clear();
				return true;
			}
			else{
				System.out.println("Sorry you don't meet the critea to build a hotel on this propety.");
				return false;
			}
		}
		else{
			System.out.println("Sorry you don't own this property.");
			return false;
		}
	}

	// Used to give money to the player object that is the owner of this place object
	public void payRent(Player renter){
		renter.money -= this.rent;
		this.owner.money += this.rent;
	}
	
  //Overridden Method used to return a String and displays its properties.
	public String toString(){
		String str = "Name: " + this.name + ", Price: $" + this.price + ", Rent: $" + this.rent + ", Owner: " + this.owner.name + "."; 
		return str;
	}
}