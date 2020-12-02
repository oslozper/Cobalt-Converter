/*
Course:				CP1340 - Object Oriented Programming
Lab 5:				GUI and Swing
File:				TestHalfLife.java
Description:		Driver file to test the HalfLife object class file
Date:				November 26, 2020
Name:				OSCAR LOZANO-PEREZ
Student Number:		20164974
*/

import java.util.Scanner;

public class TestHalfLife{
	public static void main(String[] args){
		int initial;
		double percentage;
		int years;
		Scanner s = new Scanner(System.in);

		System.out.print("Give Initial: ");
		initial = s.nextInt();

		System.out.print("Give percentage: ");
		percentage = s.nextDouble();

		System.out.print("Give years: ");
		years = s.nextInt();
		
		HalfLife cobalt = new HalfLife(initial, percentage, years);
		System.out.println(cobalt.getValue());
	}
}