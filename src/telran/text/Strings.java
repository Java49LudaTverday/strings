package telran.text;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.function.BinaryOperator;

public class Strings {
	static HashMap<String, BinaryOperator<Double>> mapOperations;
	
	static {
		mapOperations = new HashMap<>();
		mapOperations.put("-", (a,b) -> a - b);
		mapOperations.put("+", (a,b) -> a + b);
		mapOperations.put("*", (a,b) -> a * b);
		mapOperations.put("/", (a,b) -> a / b);		
	}
	public static String javaVariableName() {
		
		return "([a-zA-Z$][\\w$]*|_[\\w$]+)";
	}
	
	public static String zero_300 () {
		//positive number from 0 to 255 and leading zeros are allowed
		return "[3-9]\\d?|[1-2]\\d\\d?|300|0";
	}
	
	public static String ipV4Octet () {
		//positive number from zero to 255;
		// and leading zeros are allowed;
		return "([01]?\\d\\d?|2([0-4]\\d|5[0-5]))";
	}
	public static String ipV4 () {
		
		return String.format("(%1$s\\.){3}%1$s", ipV4Octet());
	}
	public static String arithmeticExpression() {
		String operandRE = operand();
		String operatorRE = operator();
		return String.format("%1$s(%2$s%1$s)*",operandRE,operatorRE );		
	}

	public static String operator() {
		return "\\s*([-+/*])\\s*";
	}

	public static String operand() {
		String variable = javaVariableName();
		return String.format("((\\d+([.]\\d*)?)|(\\.\\d+)|%s)",variable);
	}
	
	public static boolean isArithmeticExpression(String expression) {
		expression = expression.trim();// removed first and last space;
		return expression.matches(arithmeticExpression());
	}
	
	public static double computeExpression(String expression, 
			HashMap<String, Double> mapVariables) {
		if(!isArithmeticExpression(expression)) {
			throw new IllegalArgumentException("Wrong arithmetic expression");
		}
		expression = expression.replaceAll("\\s+", "");
		String[] operands = expression.split(operator());
		String[] operators = expression.split(operand());
		
		Double res = getOperand(operands[0], mapVariables);				
		for(int i = 1; i < operands.length; i++) {
			Double operand = getOperand(operands[i], mapVariables);			
			res = mapOperations.get(operators[i]).apply(res, operand);
		}
		return res;
	}

	private static Double getOperand(String operand, HashMap<String, Double> mapVariables) {
		Double res = null;
		try {
			res = Double.parseDouble(operand);
		}
		catch (Exception e) {
			res = mapVariables.get(operand);
			if( res == null ) {
				throw new NoSuchElementException();
			}
		}
		return res;
	}

}

//Update whole code for any numbers (double)
//Update code taking consideration possible variable names


