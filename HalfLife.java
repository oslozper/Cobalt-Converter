/*
Course:				CP1340 - Object Oriented Programming
Lab 5:				GUI and Swing
File:				HalfLife.java
Description:		HalfLife Class to set the initial, percentage and year values
					to calculate the amount left of an isotope after a period of time.
					Has two constructors and eight methods (set and get).
Date:				November 26, 2020
Name:				OSCAR LOZANO-PEREZ
Student Number:		20164974
*/

public class HalfLife{

	// Class variables
	protected double initial;
	protected double percentage;
	protected double years;

	// Constructor that sets the initial, percentage and year values as 0
	public HalfLife(){
		initial = 0;
		percentage = 0;
		years = 0;
	}

	// Constructor that sets the initial, percentage and year values
	public HalfLife(double initial, double percentage, double years){
		this.initial = initial;
		this.percentage = percentage;
		this.years = years;
	}

	// Changes values for the initial, percentage and year values
	public void setValues(double initial, double percentage, double years){
		this.initial = initial;
		this.percentage = percentage;
		this.years = years;
	}

	// Set method that sets a specific value for initial
	public void setInitial(double initial){
		this.initial = initial;
	}

	// Set method that sets a specific value for percentage
	public void setPercentage(double percentage){
		this.percentage = percentage;
	}

	// Set method that sets a specific value for years
	public void setYears(double years){
		this.years = years;
	}

	// Get method that returns the value of initial
	public double getInitial(){
		return initial;
	}

	// Get method that returns the value of percentage
	public double getPercentage(){
		return percentage;
	}

	// Get method that returns the value of years
	public double getYears(){
		return years;
	}

	// Get method that returns the amount after the calculations.
	public double getValue(){
		double result = initial;
		for(int i = 0; i < years; i++){
			result = result * (1-percentage);
		}
		return result;
	}
}