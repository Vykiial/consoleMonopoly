/*
  David Nong-Ang, Sahib Johar, Karthik Kodeswaran
  17/01/2021
  Piece Class
  This is code for the Piece Class
*/

public class Piece{
	// Instance Variables
	public final int id;
	public int position;

	// Constructors
	public Piece(int id){
		this.id = id;
	}

	// Overridden Method used to return a String and display its properties.
	public String toString(){
		String str = "ID: " + this.id + ", Position: " + this.position + ".";
		return str;
	}
}