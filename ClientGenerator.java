import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
//Author: Marius Popescu
//Date: 01.11.2019
//This is a driver(client) for the program
public class ClientGenerator {
	
	public static void main(String[] args) {
		//declaring variables
		Scanner s = new Scanner(System.in);
		ArrayList<Integer> inputArray = new ArrayList<Integer>();
		//Prompt the user for the number and sequences
		System.out.println("Please enter a sum target number in the first line. \nAfter, please enter sequences of digits"
				+ " in the following linses, -1 to quit: ");
		int number = s.nextInt(); //Get the first number, the target sum
		int input = 0; //Use integer for the digit sequences in order to disallow expressions like: 2*-100*-10+0
		try { 
			while(input != -1) { //Get the sequences
				input = s.nextInt();			
				if (input != -1) inputArray.add(input);
			}
		}catch(InputMismatchException e) { //catch wrong inputs
			System.out.println("You did not entered a valid sequence of digits!");
		}
		
		for (int e: inputArray) { //for each sequence of the user input
			System.out.println(""); //skip one line between output sets
			ExpressionGenerator exGen = new ExpressionGenerator(number, Integer.toString(e));
			exGen.generateExpression(); //generate and output all the legal expressions
		}
		s.close(); //close the scanner
	}
}