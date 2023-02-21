/*
  David Nong-Ang, Sahib Johar, Karthik Kodeswaran
  17/01/2021
  Action Sqaure Class
  This is code for the Action Sqaure Class.
*/

//This is a subclass that is extending from the Square class. 
public class ActionSquare extends Square{ 
	// Instance Variables
	public final int length;
	public final int width;
	public final int ID;

	// Constructors
	public ActionSquare(int length, int width, String name, int position, int ID){
		super(name, position);
		this.length = length;
		this.width = width;
		this.name = name;
		this.position = position;
		this.ID = ID;
	}
}