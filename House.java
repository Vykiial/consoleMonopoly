/*
  David Nong-Ang, Sahib Johar, Karthik Kodeswaran
  17/01/2021
  House Class
  This is code for the House Class
*/

public class House{
	// Instance Variables
	public double length = 0;
	public double width = 0;
	public final String colour = "green";
	private final Place place;
	public final int price;
	public final double rentMultiplier;

	// Constructors
	public House(Place place, int price, double multiplier){
		this.place = place;
		this.price = price;
		this.rentMultiplier = multiplier;
	}

  // Overridden Constructor
	public House(Place place, int price, double multiplier, double length, double width){
		this.place = place;
		this.price = price;
		this.rentMultiplier = multiplier;
		this.length = length;
		this.width = width;
	}

	// Methods

  //Access Method to get name of place.
	public String getPlace(){ 
		return this.place.name;
	}

  // Overridden Method used to return a String and displays it. 
	public String toString(){ 
		String str = "Property: " + this.place.name + ", Price: $" + this.price + ", Rent Multiplier: " + this.rentMultiplier + ".";
		return str;
	}
}