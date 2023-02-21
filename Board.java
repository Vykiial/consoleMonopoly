/*
  David Nong-Ang, Sahib Johar, Karthik Kodeswaran
  17/01/2021
  Board Class
  This is code for the Board Class
*/

import java.util.ArrayList;

public class Board{
	// Instance variables
	public final int length;
	public final int width;
	private ArrayList<Place> places;
	private ArrayList<ActionSquare> cornerPieces = new ArrayList<ActionSquare>();
	private ArrayList<ActionSquare> specialTiles = new ArrayList<ActionSquare>();

	// Constructors 
	public Board(int length, int width){ 
		this.length = length;
		this.width = width;
	}

  // Overridden Constructors
	Board(int length, int width, ArrayList<Place> places, ArrayList<ActionSquare> cornerPieces, ArrayList<ActionSquare> specialTiles){
		this.length = length;
		this.width = width;
		this.places = places;
		this.cornerPieces = cornerPieces;
		this.specialTiles = specialTiles;
	}
}