/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Spring 2016 
//PROJECT:          p1
//FILE:             GradeFileFormatException.java
//
//Author: Songnie Wu, Justin Kwik, Kenji Passini, Haotian Zhu, Weisheng Chen
////////////////////////////80 columns wide //////////////////////////////////

/**
 * This class is a instantiable class that we will be making instances of in
 * case the input of the grade file they inputted is in the wrong format.
 * <p>
 * Bugs: no bugs
 *
 * @author Justin Kwik, Kenji Passini, Songnie Wu, Haotian Zhu, Weisheng Chen
 */
public class GradeFileFormatException extends Exception {

	/**
	 * This is the constructor for the GradeFileFormatException where it calls the super constructor
	 * of the Exception class and passes the name of the exception for the error message.
	 *
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS: N/A
	 */
	public GradeFileFormatException() {
		super("GradeFileFormatException");
	}
	
	/**
	 * This is the constructor for the GradeFileFormatException that is called if the user wants to
	 * specify the message that the exception will return when the error occurs. It calls the super
	 * constructor of the Exception class and passes the message taken from the parameter.
	 *
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS: N/A
	 */
	public GradeFileFormatException(String message) {
		super(message);
	}

}
