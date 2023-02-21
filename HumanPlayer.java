/*
  David Nong-Ang, Sahib Johar, Karthik Kodeswaran
  17/01/2021
  Human Player Class
  This is code for the Human Player Class
*/

import java.util.ArrayList;

public class HumanPlayer extends Player{ //Subclass extending from Player Class.
	// Constructors
	public HumanPlayer(){
		this.name = "Player One";
		this.piece = new Piece(1);
	}
  //Overloaded Constructors.
	public HumanPlayer(String name, Piece piece, int money){ 
		this.name = name;
		this.piece = piece;
		this.money = money;
	}

}