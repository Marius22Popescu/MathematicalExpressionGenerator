import java.util.ArrayList;
//Author: Marius Popescu
//Date: 01.11.2019
// This class will return an Array list of expressions representing  all the possible permutation 
// of '+', '-', '*' and concatenation for a given size of expression
public class OperationsPermutator {
	int nrOfOperations;
	ArrayList<String> operatorsExpressions = new ArrayList<String>();
	OperationsPermutator (int nr) {
		nrOfOperations = nr;
	}
	private void permuteOperations(int j) {
		//Create the max number until the loop will go
		String st = "";		
		for(int i=1; i <= j; i++) {
			st += "4";
		}
		long max = Long.parseLong(st); //convert from string to long
		//Create the min number from where the loop will start
		String st2 = "";
		for(int i=1; i <= j; i++) {
			st2 += "1";
		}
		long min = Long.parseLong(st2); //convert from string to long
		//Create all the possible combinations of operations
		String nr = "";
		for(long x = min; x <= max; x++) {
			boolean flag = true;  // flag will keep track of numbers greater than 4
			nr = String.valueOf(x);
			for(int y= 0; y < nr.length(); y++) {
				int c = Character.getNumericValue(nr.charAt(y)); // convert char to integer
				if((c == 5)||(c == 6)||(c == 7)||(c == 8)||(c == 9)||(c == 0)) { //exclude all digits greater than 4
					flag = false;
					break;
		     	}
			}
			if(flag) {  //if not greater than 4 add to array list
				operatorsExpressions.add(nr);
			}
		}
	}
	//This method will return the arrayList of all the possible permutation
	public ArrayList<String> getPermutedOperators() {
		permuteOperations(nrOfOperations);
		return operatorsExpressions;
	}
}
