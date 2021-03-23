/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          p1
// FILE:             ScoreList.java
//
// Author: Songnie Wu, Justin Kwik, Kenji Passini, Haotian Zhu, Weisheng Chen
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * This class contains method that store scores to a object under certain
 * category
 * 
 *
 * <p>
 * Bugs: no bugs
 *
 * @author Justin Kwik, Kenji Passini, Songnie Wu, Haotian Zhu, Weisheng Chen
 */
public class ScoreList implements ScoreListADT {

	private Score[] Scores;// an array that contains objects of Scores
	private int numItems;// number of items in the ScoreList

	/**
	 * This method is designed to add scores to its correspond score category
	 * and store the score in the scoreList
	 *
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS:N/A
	 *
	 * @param scoreList
	 *            The object that store scores
	 * @param scoreCategory
	 *            the object that assign each score to a correct categort
	 * @return no return
	 */
	ScoreList() {
		numItems = 0;
		Scores = new Score[100];
	}

	/**
	 * Returns the number of Scores in the list or zero
	 * 
	 * @return the number of scores in this list
	 */
	public int size() {
		return numItems;
	}

	/**
	 * Adds the score to the end of this list.
	 * 
	 * @param s
	 *            a non-null Score to place as the last item in the list.
	 * @throws IllegalArgumentException
	 */
	public void add(Score s) throws IllegalArgumentException {
		Score[] Temp; // the temporary array to store double sized array
		if (s == null)// to make sure s is a non-null value
			throw new IllegalArgumentException();

		if (numItems >= Scores.length) {
			Temp = new Score[Scores.length * 2];// create a new array with 2
												// times
			// length
			for (int i = 0; i < numItems; i++) {
				Temp[i] = Scores[i];// copy every element to the new array
			}
			Scores = Temp;// expand the current array
		}
		Scores[numItems] = s;// add the object and the end of the list

		numItems++;// increase the number of items by 1

	}

	/**
	 * Removes and returns the item at index position i. If i is less than zero
	 * or greater than size()-1, will throw an IndexOutOfBoundsException.
	 * 
	 * @param i
	 *            must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	public Score remove(int i) throws IndexOutOfBoundsException {
		Score temp;// the temporary Score object to store the Score
		if (i >= 0 && i < size()) { // make sure if the index is valid
			temp = Scores[i];// get the object that will be removed

			if (size() > 1)
				for (int k = i + 1; k < size(); k++)
					Scores[k - 1] = Scores[k];// shift the elements after index
												// to the right

			numItems--;// minus the number of elements by one
		} else
			throw new IndexOutOfBoundsException();// throw exception if the
													// index is not valid

		return temp;
	}

	/**
	 * Returns (without removing) the item at index position i. If i is less
	 * than zero or greater than size()-1, will throw an
	 * IndexOutOfBoundsException.
	 * 
	 * @param i
	 *            must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	public Score get(int i) throws IndexOutOfBoundsException {
		if (i >= 0 && i < size())// Decide if the i is a valid index.
			return Scores[i];// return the object at index i
		else
			throw new IndexOutOfBoundsException();// throw exception if the
		// index is not valid
	}
}
