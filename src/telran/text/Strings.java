package telran.text;

public class Strings {
	public static String javaVariableName() {
		
		return "[a-zA-Z$][\\w$]*|_[\\w$]+";
	}
	
	public static String zero_300 () {
		
		return "[3-9]\\d?|[1-2]\\d\\d?|300|0";
	}
	
	public static String ipV4Octet () {
		//positive number from zero to 255;
		// and leading zeros are allowed;
		return "[0-1]?\\d{1,2}|2[0-4]\\d|25[0-5]";
	}
	public static String ipV4 () {
		//four ipV4 octets separated by dot 123.123.255.01
		return "(("+ipV4Octet()+")\\.){3}("+ipV4Octet()+")";
	}

}
