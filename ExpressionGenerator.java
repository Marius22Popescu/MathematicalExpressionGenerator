import java.util.ArrayList;
//Author: Marius Popescu
//Date: 01.11.2019
//This class will accept an integer (N) and a sequence of digits. It will fill in 0 or more operands (+, -, *) and will 
//return all possible mathematical expressions that will generate the integer (N). It will use an OperationPermutator object
// and an ExpressionChecker object 
public class ExpressionGenerator {
	int targetNumber; 
	String sequence = "";
	ArrayList<String> permutedOperators = new ArrayList<String>(); //Array that will keep all possible permutation of operators
	ArrayList<String> initialExpresions = new ArrayList<String>(); //Array that will keep the new generated expressions 
	ArrayList<String> validExpresions = new ArrayList<String>(); //Array that will keep the expressions that evaluate to N
	OperationsPermutator op;
	ExpressionChecker ec;
	//The constructor
	ExpressionGenerator(int nr, String st) {
		targetNumber = nr;
		sequence = st;
		op = new OperationsPermutator(sequence.length()-1);
		ec = new ExpressionChecker(targetNumber, initialExpresions);
	}
	//This method will generate the expression
	void generateExpression(){  
		permutedOperators = op.getPermutedOperators();	//Create the permuted operators array list
		//Create the expression
		for (String o: permutedOperators) { 	// insert each possible permuted string of operators
			String expression = "";
			for (int i = 0; i < sequence.length()-1; i++) {				
				expression += sequence.charAt(i) + getOperator(o.charAt(i)); //build the expression
			}
			expression += sequence.charAt(sequence.length()-1); //insert last character from the string of digits
			initialExpresions.add(expression);
		}
		//check expressions in order to find if it sum to the N and get rid of leading zeros
		validExpresions = ec.getCheckedExpressions();
		if(validExpresions.isEmpty())  // if no expression found print "impossible"
			System.out.println("impossible");
		for (String fe: validExpresions) {	//print the final expressions
			System.out.println(fe); 
		}	
	}	
	
	//This method will return the operator from it's corresponding number. 
	//It returns nothing for "1" in order to perform concatenation
	private String getOperator(char c) {
		String stringOp = "";
		int op = Character.getNumericValue(c);
		if (op == 2) stringOp = " + ";
		else if (op == 3) stringOp = " - ";
		else if (op == 4) stringOp = " * ";
		return stringOp;
	}	
}
