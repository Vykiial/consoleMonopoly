/*
  David Nong-Ang, Sahib Johar, Karthik Kodeswaran
  17/01/2021
 Hotel Class
  This is code for the Hotel Class
*/

public class Hotel extends House{ //Subclass extending from House Class
	// Constructors
	public Hotel(Place place, int price, double multiplier, House house1, House house2, House house3, House house4){
		super(place, price, multiplier);
	}
// Overloaded Constructor
	public Hotel(Place place, int price, double multiplier, double length, double width, House house1, House house2, House house3, House house4){
		super(place, price, multiplier, length, width);
	}
}

