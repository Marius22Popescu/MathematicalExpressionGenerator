import java.util.ArrayList;
import java.util.Stack;
//Author: Marius Popescu
//Date: 01.11.2019
//This object will get an ArrayList of expression and a target number and will return an ArrayList with the expressions
// that are evaluating to the target number and have no leading zero numbers
public class ExpressionChecker {
	int targetSum;
	ArrayList <String> initialExpressionns = new ArrayList<String>(); //The array of unchecked expression
	ArrayList <String> checkedExpressionns = new ArrayList<String>();	//The array of checked expression
	Boolean zeroLeadingNumbers; //Will be used to check if there are zero leading no like 000 or 002
	//The constructor
	ExpressionChecker (int nr, ArrayList<String> exp){
		targetSum = nr;
		initialExpressionns = exp;
	}
	//This method will compute the sum of the expression
	Integer getValueOfExpression(String expression) {
		zeroLeadingNumbers = true; // will keep track of zero leading expressions
		char[] ex = expression.toCharArray(); //Convert to a char array
        Stack<Integer> values = new Stack<Integer>(); // Stack for numbers
        Stack<Character> operators = new Stack<Character>(); // Stack for Operators  
        for (int i = 0; i < ex.length; i++) {              
            if (ex[i] == ' ') // if white space go over
                continue; 
            if (ex[i] >= '0' && ex[i] <= '9') { // if is number
                StringBuffer sb = new StringBuffer();  
                while (i < ex.length && ex[i] >= '0' && ex[i] <= '9') //if it is more like one token in a number
                		sb.append(ex[i++]); 
                String val = sb.toString();
                values.push(Integer.parseInt(val)); //push value
                //check for leading zero numbers
                if (((val.length()) > 1) && (val.charAt(0) == '0') && (val.charAt(1) >= '0' && val.charAt(1) <= '9')) 
            			zeroLeadingNumbers = false;
            }  
            else if (ex[i] == '+' || ex[i] == '-' || ex[i] == '*' || ex[i] == '/') { //if are operators
                if (!operators.empty() && hasPrecedence(ex[i], operators.peek())) //if the top of operators has greater or the same precedence like the current operation 
                  values.push(performOperation(operators.pop(), values.pop(), values.pop())); //perform the operation 
                operators.push(ex[i]);  //push current operation
            } 
        } 
        while (!operators.empty())   //while operations stack is not empty  
        		values.push(performOperation(operators.pop(), values.pop(), values.pop())); //perform the rest of operations        
        return values.pop(); //return the result
		}
	// This method will check if 'op2' has higher or same precedence as 'op1'
    public static boolean hasPrecedence(char op1, char op2) { 
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) 
            return false; 
        else
            return true; 
    } 
    //This method will perform the operation
    public static int performOperation(char c, int x, int y) {
		if (c == '+') return x + y;
		else if (c == '*') return x * y;
		else if (c == '-') return y - x;
    	return 0;    	
    }	
    //This method will return the expression that are evaluated to the target number N 
    // and have non zero leading numbers, for the output
    public ArrayList<String> getCheckedExpressions(){
    	int sum = 0;
    	for (String e: initialExpressionns) { //for each initial expression
    		sum = getValueOfExpression(e);	//evaluate the expression
    		//check if has zero leading numbers and the expression is evaluating to target sum
    		if(zeroLeadingNumbers && (sum == targetSum)) { 
    			boolean contains = false;
    			for (String x: checkedExpressionns) {    				
    				if(e.equals(x))		//check for duplicates expressions
    					contains = true;
    			}
    			if (!contains)
    				checkedExpressionns.add(e); //add expression to the checkedExpressionns arrayList
    		}
    	}
		return checkedExpressionns;
    }   
}
