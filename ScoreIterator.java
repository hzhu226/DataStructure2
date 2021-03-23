import java.util.NoSuchElementException;
/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Spring 2017 
//PROJECT:          P1
//FILE:             ScoreIterator.java
//
//Author: Songnie Wu, Justin Kwik, Kenji Passini, Haotian Zhu, Weisheng Chen
////////////////////////////80 columns wide //////////////////////////////////

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
public class ScoreIterator implements ScoreIteratorADT {
	
	//For the score iterator class, because it iterates through the scores of a specified category,
	//we need a ScoreList variable.
	private ScoreList scoreList;
	//We also need currpos to keep track of the current position of the iteration.
	private int currPos;
	//And the scoreCategory for the score category that is specified and passed in the constructor.
	private String scoreCategory;

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
	public ScoreIterator(ScoreList scoreList, String scoreCategory) {

		//So we set the scoreList variable to a new ScoreList, calling the constructor.
		this.scoreList = new ScoreList();
		//And set the scoreCategory to the scoreCategory.
		this.scoreCategory = scoreCategory;
		//And set the current position to 0.
		currPos = 0;

		//Then we enter a for loop looping through the passed scoreList in the parameter.
		for (int i = 0; i < scoreList.size(); i++) {

			//And if the scoreList at index i's category matches the passed specified category:
			if (scoreList.get(i).getCategory().equals(scoreCategory)) {
				//Then we add the Score in scoreList at index i into the scoreList.
				this.scoreList.add(scoreList.get(i));
			}
		}

	}

	/**
	 * This method checks whether the current position is smaller than the size
	 * of scoreList
	 *
	 *
	 * @return true if the currPos is smaller than the size of scoreList
	 */
	public boolean hasNext() {
		//So this will return true if currPost is smaller than the size of scoreList.
		return currPos < scoreList.size();
	}

	/**
	 * This method throws NoSuchELementException. If there is no more scores can
	 * be read, this exception will be throw, if not, the outcome will display
	 * the information of current score, and then go to next score.
	 *
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS: N/A
	 *
	 * @param outcome
	 *            the information of a certain score
	 * @return outcome the information of a certain score
	 */
	public Score next() throws NoSuchElementException {

		//So we want to create a new empty Score object.
		Score outcome;
		//And if hasNext returns false, meaning there isn't another object in the ScoreList, then we just
		//throw a NoSuchElementException.
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		//Otherwise if hasNext returns true, then we set the Score outcome variable to the scoreList at
		//the current position.
		outcome = scoreList.get(currPos);
		//Then we increment currPos.
		currPos++;
		//And return the Score object.
		return outcome;
	}

}
