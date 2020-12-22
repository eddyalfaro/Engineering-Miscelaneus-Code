package exceptions;

public class InvalidInputException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String ERROR1 = "Input unit does not conrrepond to... ";
	public static final String ERROR2 = "Different Units that cannot be compared";
	
	public InvalidInputException(String msg) {
		super(msg);
	}

}
