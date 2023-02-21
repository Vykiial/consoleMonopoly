/*
  David Nong-Ang, Sahib Johar, Karthik Kodeswaran
  17/01/2021
  Dice Class
  This is code for the Dice Class
*/

import java.util.ArrayList;
import java.lang.Math;

public class Dice{
	// Instance Variables
	public int numOfSides = 2;
	public final int length;
	public final int width;
	private ArrayList<Integer> lastThreeRolls = new ArrayList<Integer>();

	// Constructors
	public Dice(int length, int width){
		this.length = length;
		this.width = width;
		this.lastThreeRolls.add(0, 0);
		this.lastThreeRolls.add(1, 0);
		this.lastThreeRolls.add(2, 0);
	}
  // Overloaded Constructor
	public Dice(int length, int width, int numOfSides){ 
		this.length = length;
		this.width = width;
		this.numOfSides = numOfSides;
	}

	// Methods

  //This funciton has no parameters and 1 return value. It is used to roll the Dice Objects.
	public int roll(){
		int num = (int)((Math.random() * numOfSides) + 1);
		this.lastThreeRolls.add(0, num);
		if(this.lastThreeRolls.size() > 3)
			this.lastThreeRolls.remove(this.lastThreeRolls.size() - 1);
		return num;
	}

  //This function has no paramters and 1 return value. It is used to get the previous roll of the player.
	public int lastRoll(){
		int num = this.lastThreeRolls.get(0);
		return num;
	}

  //This function has no parameters and 1 return value. It is used to get the last 3 rollls of a player in order to determine if they are sent to jail or not.
	public int[] getLastThreeRolls(){
		int num[] = new int[3];
		for(int i = 0; i < 3; i++)
			num[i] = this.lastThreeRolls.get(i);
		return num;
	}
}

