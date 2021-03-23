import java.util.NoSuchElementException;
/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Spring 2017 
//PROJECT:          P1
//FILE:             ScoreIterator.java
//
//Author: Songnie Wu, Justin Kwik, Kenji Passini, Haotian Zhu, Weisheng Chen
////////////////////////////80 columns wide //////////////////////////////////

/**
 * This is the interface ADT that will be implemented by our ScoreIterator, because it is an interface
 * all of the methods are abstract and will need to be implemented (meaning they have no initial definition).
 *
 * <p>
 * Bugs: no bugs
 *
 * @author Justin Kwik, Kenji Passini, Songnie Wu, Haotian Zhu, Weisheng Chen
 */
public interface ScoreIteratorADT {
	
	/**
	 * Indicates if there is another score 
	 * @return True if there is another score, false otherwise 
	 */
	boolean hasNext();
	
	
	/** 
	 * returns the next Score and increments the positions in the score list
	 * @return the next score 
	 * @throws NoSuchElementException
	 */
	Score next() throws NoSuchElementException;

}
