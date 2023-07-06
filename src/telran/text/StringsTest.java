package telran.text;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class StringsTest {

	@Test
	void test() {
		//String regex = "gray|grey|griy";
		//String regex = "gr(a|e|i)y";
//		assertTrue("gray".matches(regex));
//		assertTrue("grey".matches(regex));
//		assertTrue("griy".matches(regex));
//		assertFalse("groy".matches(regex));
		
//		String regex = "a?1234";// a may be or not be
//		assertTrue("1234".matches(regex));
//		assertTrue("a1234".matches(regex));
//		assertFalse("ab1234".matches(regex));
		
//		String regex = "a*1234";// a may be or not be many
//		assertTrue("1234".matches(regex));
//		assertTrue("a1234".matches(regex));
//		assertFalse("ab1234".matches(regex));
//		assertTrue("aa1234".matches(regex));
		
//		String regex = "a+1234";// a may be or not be many
//		assertFalse("1234".matches(regex));
//		assertTrue("a1234".matches(regex));
//		assertFalse("b1234".matches(regex));
//		assertTrue("aa1234".matches(regex));
		
		String regex = "(a|b)+1234";// a may be or not be many
		assertFalse("1234".matches(regex));
		assertTrue("a1234".matches(regex));
		assertTrue("b1234".matches(regex));
		assertTrue("aa1234".matches(regex));		
	}
	
	@Test
	void javaVariableNameTest() {
		String regex = Strings.javaVariableName();
		assertTrue( "a".matches(regex));
		assertTrue( "$".matches(regex));
		assertTrue( "$$".matches(regex));
		assertTrue( "_$".matches(regex));
		assertTrue( "__".matches(regex));
		assertTrue( "abc1234567890".matches(regex));
		assertTrue( "A_B".matches(regex));
		
		assertFalse("1a".matches(regex));
		assertFalse("_".matches(regex));
		assertFalse("a#".matches(regex));
		assertFalse("a b".matches(regex));
		assertFalse("a-b".matches(regex));		
	}
	
	@Test
	void zero_300Test() {
		String regex = Strings.zero_300();
		assertTrue("0".matches(regex));
		assertTrue("9".matches(regex));
		assertTrue("299".matches(regex));
		assertTrue("300".matches(regex));
		assertTrue("99".matches(regex));
		assertTrue("25".matches(regex));
		
		assertFalse("00".matches(regex));
		assertFalse("01".matches(regex));
		assertFalse("400".matches(regex));
		assertFalse("301".matches(regex));
		assertFalse("1111".matches(regex));
		assertFalse("-1".matches(regex));
		assertFalse("3 ".matches(regex));		
	}
	
	@Test
	void ipV4Octet () {
		String regex = Strings.ipV4Octet();
		assertTrue("000".matches(regex));
		assertTrue("00".matches(regex));
		assertTrue("0".matches(regex));
		assertTrue("99".matches(regex));
		assertTrue("1".matches(regex));
		assertTrue("10".matches(regex));
		assertTrue("199".matches(regex));
		assertTrue("249".matches(regex));
		assertTrue("250".matches(regex));
		assertTrue("255".matches(regex));
		assertTrue("050".matches(regex));
		
		assertFalse("0000".matches(regex));
		assertFalse("0 0".matches(regex));
		assertFalse(".0".matches(regex));
		assertFalse("256".matches(regex));
		assertFalse("1000".matches(regex));
	}
	@Test
	void ipV4Test () {
		String regex = Strings.ipV4();
		assertTrue("0.0.0.0".matches(regex));
		assertTrue("1.1.1.1".matches(regex));		
		assertTrue("99.99.12.09".matches(regex));
		assertTrue("100.199.200.255".matches(regex));
		
		assertFalse(".1.2.3.4".matches(regex));
		assertFalse(".1.2.3.4.".matches(regex));
		assertFalse("1.2.3".matches(regex));
		assertFalse("1.2.3.4.5".matches(regex));
		assertFalse("1111.2222.3.4".matches(regex));
		assertFalse("11.00.00.22.3".matches(regex));
		assertFalse("256.256.256.256".matches(regex));		
	}
	
	@Test
	void arithmeticExpressionTest() {
		assertTrue(Strings.isArithmeticExpression(" 12 "));
		assertTrue(Strings.isArithmeticExpression(" 12/300 "));
		assertTrue(Strings.isArithmeticExpression("12/300"));
		assertTrue(Strings.isArithmeticExpression(" 12* 2/3 + 1000 "));
		assertTrue(Strings.isArithmeticExpression(" 120 / 50 + 100 - 2 * 3 / 500"));
		
		assertFalse(Strings.isArithmeticExpression(" 12 18 "));
		assertFalse(Strings.isArithmeticExpression(" 12 /3&4 "));
		assertFalse(Strings.isArithmeticExpression(" 12 +20- "));
		assertFalse(Strings.isArithmeticExpression(" 12 /18 + 100 10 "));
	}
	
	@Test 
	void operandTest () {
		String regex = Strings.operand();
		assertTrue("10".matches(regex));
		assertTrue("0.100".matches(regex));
		assertTrue("10.1".matches(regex));
		assertTrue("10.0".matches(regex));
		assertTrue(".1".matches(regex));
		assertTrue("0.1".matches(regex));
		assertTrue("10.0".matches(regex));
		assertTrue("10.12340000".matches(regex));
		assertTrue("10.".matches(regex));
		assertTrue( "_$".matches(regex));
		assertTrue( "__".matches(regex));
		assertTrue( "abc1234567890".matches(regex));
		
		assertFalse(".10.".matches(regex));
		assertFalse("12.10.15".matches(regex));
		assertFalse("10..".matches(regex));
		assertFalse("".matches(regex));		
	}
	
	@Test
	void operatorTest() {
		String regex = Strings.operator();
		assertTrue("+".matches(regex));
		assertTrue("-".matches(regex));
		assertTrue("/".matches(regex));
		assertTrue("*".matches(regex));
		
		assertFalse("&".matches(regex));
		assertFalse(")".matches(regex));
		assertFalse("1".matches(regex));	
		
	}
	
	@Test
	void computeExpressionDoubleTest () {
		double[] values = {10, 0.1, 0.5, 10.1, .10, 10.};
		String[] nameVar = {"v_10", "v_0dote1", "v_0dote5", "v_10dote1", "v_dote10", "v_10dote" };
		HashMap<String,Double> mapVariables = getMapVariables( nameVar, values );
		
		assertEquals(2, Strings.computeExpression("20 /5/ 2", mapVariables));
		assertEquals(1.0, Strings.computeExpression("v_10 * v_0dote1", mapVariables));
		assertEquals(9.9,Strings.computeExpression("v_10dote + 10- v_10dote1", mapVariables) );
		assertEquals(0.01,Strings.computeExpression("v_dote10 / 10", mapVariables) );
		
		assertEquals(1.0, Strings.computeExpression("001", mapVariables));
		
		assertThrowsExactly(NoSuchElementException.class,
				() -> Strings.computeExpression("a / 10", mapVariables));
		assertThrowsExactly(NoSuchElementException.class,
				() -> Strings.computeExpression("null / 10", mapVariables));
		assertThrowsExactly(IllegalArgumentException.class,
				() -> Strings.computeExpression("a $ 10 +", mapVariables));
		assertThrowsExactly(IllegalArgumentException.class,
				() -> Strings.computeExpression(" ", mapVariables));
		
		
	}

	private HashMap<String,Double> getMapVariables(String[] nameVar, double[] values) {
		HashMap<String,Double> mapVariables = new HashMap<>();
		for(int i = 0; i<nameVar.length; i++) {
			mapVariables.put(nameVar[i], values[i]);
		}
		
		return mapVariables;
	}
	

}
