/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017 
// PROJECT:          P1
// FILE:             Score.java
//
// Author: Songnie Wu, Justin Kwik, Kenji Passini, Haotian Zhu, Weisheng Chen
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * 
 * This Score class is an instantiable class that will contain the name of the
 * assignment the points earned, and the total possible points that can be
 * scored on a particular assignment. It contains the appropriate constructor to
 * assign the mentioned variables values, as well as methods to return the
 * assignment name, the points earned, the maximum number of points, the
 * category of the assignment, and finally the percentage value that was scored
 * on this assignment.
 *
 * @author (Songnie Wu, Justin Kwik, Kenji Passini, Haotian Zhu, Weisheng Chen)
 */
public class Score {

	private String AssignmentName;// name of assignment
	private double PointEarned;// the points earned in this assignment
	private double MaxPossible;// the max point of the assignment

	/**
	 * This is the constructor of the Score class. The constructor handles
	 * everything that needs to be done before its other methods work. It
	 * essentially allows the user to create an object out of our class Score
	 * giving it value that the user desires.
	 * 
	 * PRECONDITIONS: assignmentName can't be null, pointsEarned and
	 * pointsPossible both have to be greater than 0. And pointsEarned can't be
	 * greater tha pointsPossible.
	 * 
	 * POSTCONDITIONS: This particular Score object will be assigned the given
	 * values passed in the parameter.
	 *
	 * @param (assignmentName)
	 *            (A String of the name of the assignment in which a score
	 *            pertains to).
	 * @param (pointsEarned)
	 *            (A double holding the amount of points that the student earned
	 *            on this particular assignment).
	 * @param (pointsPossible)
	 *            (The maximum number of points possible that the student can
	 *            earn on this assignment, in other words the amount of points
	 *            to earn 100%).
	 */
	Score(String AssignmentName, double PointEarned, double MaxPossible) {

		if (AssignmentName == null)// throw an exception when the name is null
			throw new IllegalArgumentException();
		else if (PointEarned < 0)// throw an exception when the points earned is
									// negative
			throw new IllegalArgumentException();
		else if (MaxPossible < 0)// throw an exception when the max points is
									// negative
			throw new IllegalArgumentException();
		else if (MaxPossible < PointEarned)// throw an exception when the points
											// earned exceed the max points
			throw new IllegalArgumentException();

		this.AssignmentName = AssignmentName;
		this.PointEarned = PointEarned;
		this.MaxPossible = MaxPossible;
	}

	/**
	 * Returns the name of assignment
	 * 
	 * @return the name of score
	 */
	public String getName() {
		return AssignmentName;
	}

	/**
	 * Returns the points earn in this assignment
	 * 
	 * @return the points earned
	 */
	public double getPoints() {
		return PointEarned;
	}

	/**
	 * Returns the max points one can earn in the assignment
	 * 
	 * @return the max points
	 */
	public double getMaxPossible() {
		return MaxPossible;
	}

	/**
	 * Returns the category of the assignment
	 * 
	 * @return the points first letter of assgnment's name
	 */
	public String getCategory() {
		return AssignmentName.substring(0, 1);// get the first letter
	}

	/**
	 * Returns the percentage of points earned the assignment
	 * 
	 * @return the percentage of points earned the assignment
	 */
	public double getPercent() {
		return PointEarned / MaxPossible * 100.0;
	}
}
