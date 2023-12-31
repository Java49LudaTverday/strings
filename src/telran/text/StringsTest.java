package telran.text;

import static org.junit.jupiter.api.Assertions.*;

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
	

}
