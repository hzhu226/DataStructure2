import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/////////////////////////////////////////////////////////////////////////////
//Semester:         CS367 Spring 2016 
//PROJECT:          p1
//FILE:             GradeEstimator.java
//
//Author: Songnie Wu, Justin Kwik, Kenji Passini, Haotian Zhu, Weisheng Chen
////////////////////////////80 columns wide //////////////////////////////////

/**
 * This class is a instantiable class which contain the main method that can read
 * the file and catch varies exceptions. It also contains methods that read and
 * store the information from the given file, how to calculate a student percent
 * score and letter score and how to display the information on the output
 *
 * <p>
 * Bugs: no bugs
 *
 * @author Justin Kwik, Kenji Passini, Songnie Wu, Haotian Zhu, Weisheng Chen
 */
public class GradeEstimator {

	// The input of each file will always include a letter grade, the
	// thresholds, the category names,
	// and the category weights. So below we created an array for every
	// single one of those categories.

	// Because we are starting off the array with an initial size of 100, we
	// want to make variables
	// to keep track of how many non-null items are inside of our array. So we
	// also created a variable
	// for each corresponding array.
	private String[] letterGrades = new String[100];
	private int letterGradesCount = 0;

	private Integer[] thresholds = new Integer[100];
	private int thresholdsCount = 0;

	private String[] category = new String[100];
	private int categoryCount = 0;

	private Integer[] categoryWeights = new Integer[100];
	private int categoryWeightsCount = 0;

	// We also created an array for the weighted averages since we will need
	// to calculate the
	// weighted averages of the scores we calculate. As well as a corresponding
	// counter for it.
	private Double[] weightedAverages = new Double[100];
	int weightedAveragesCount = 0;

	// To store the individual scores that a particular student acheived in each
	// assignment,
	// we created a ScoreList variable.
	private ScoreList scoreList = new ScoreList();

	/**
	 * This main class is designed to read the file and catch the exceptions if
	 * the file is not found or in wrong format.
	 * 
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS: The file has been scanned and recorded
	 *
	 * @param fileContent
	 *            keep update and store the message in a giving file if the file
	 *            has next word
	 * @return the file
	 */
	public static void main(String[] args) {

		// First we checked if exactly 1 arguement was passed into the main
		// method, because if
		// there was not exactly 1 then we print out the usage message and call
		// the
		// createGradeEstimatorFromFile method passing the default input from
		// the Config.
		if (args.length != 1) {

			// Printing the USAGE_MESSAGE
			System.out.println(Config.USAGE_MESSAGE);

			try {

				// We called the createGradeEstimatorFromFile passing the
				// default input from the Config
				// file, surrounding it in a try catch statement that catches
				// both
				// FileNotFoundException and GradeFileFormatException.
				createGradeEstimatorFromFile(Config.GRADE_INFO_FILE_FORMAT_EXAMPLE);

			} catch (FileNotFoundException e) {

				// If a FileNotFoundException was caught, then we let the user
				// know that the error occured
				// and call return which exits the program.
				System.out.println("File Not Found");
				return;

			} catch (GradeFileFormatException e) {

				// If a GradeFileFormatException was caught, then we let the
				// user know that the error occured
				// and call return which exits the program.
				System.out.println(e.getMessage());
				return;

			}

		} else {

			try {

				// Otherwise, we want to create a new File object passing the
				// argument passed in the main
				// method.
				File file = new File(args[0]);
				// And open a FileInputStream passing the file into the input
				// stream.
				FileInputStream stream = new FileInputStream(file);

				// Then we want a scaner to read from the stream.
				Scanner scanner = new Scanner(stream);

				// And have a variable to store the content from the files.
				String fileContent = "";

				// We want this loop to continue looping as long as scanner has
				// a next word.
				while (scanner.hasNextLine()) {

					// And we add all of the words into the fileContent String
					// variable.
					fileContent = fileContent + scanner.nextLine() + "\n";

				}
				// Then we call the createGradeEstimatorFromFile passing the
				// contents of the file.
				GradeEstimator estimator = createGradeEstimatorFromFile(fileContent);
				System.out.println(estimator.getEstimateReport());

				// Making sure to close the scanner.
				scanner.close();

				try {
					// Then we attempt to close the file, surrounding it in a
					// try catch block.
					stream.close();
				} catch (IOException e) {
					// If we catch an error then we make sure to let the user
					// know.
					System.out.println("File Error!");
					// Then we exit out of the program by calling return.
					return;
				}

			} catch (FileNotFoundException e) {

				// If a FileNotFoundException was caught, then we let the user
				// know that the error occured
				// and call return which exits the program.
				System.out.println("File Not Found");
				return;

			} catch (GradeFileFormatException e) {

				// If a GradeFileFormatException was caught, then we let the
				// user know that the error occured
				// and call return which exits the program.
				System.out.println(e.getMessage());
				return;

			}

		}

	}

	/**
	 * This method read information about the grade and store them in the
	 * arraylist created above, then, it read the socre of student achieve in
	 * each assignment and store them to each corresponding category.
	 *
	 * PRECONDITIONS: The incoming file is not empty and in correct format
	 * 
	 * POSTCONDITIONS: The information in the file are been stored
	 *
	 * @param gradeInfo
	 *            The information in the given file
	 * @param count
	 *            If it is smaller than 4, it will lead us to the correspound
	 *            case and read and store everything in that catagory
	 * @param assignmentName
	 *            The assignment name as a string.
	 * @param pointsEarned
	 *            The points that the student earned as a double.
	 * @param pointsPossible
	 *            The maximum points that can be earned for each assignment as a
	 *            double.
	 * 
	 * @return no return
	 */
	public GradeEstimator(String gradeInfo) {

		// We need to be able to count how many lines we have iterated through
		// in the input. This
		// is because after the 4th iteration we start creating objects of
		// score, storing them
		// in scorelist.
		int count = 0;

		// Then we create a new scanner object passing the gradeInfo object so
		// that we can
		// read from it.
		Scanner scanner = new Scanner(gradeInfo);

		// So we want the while loop to run as long as the scanner has a next
		// line.
		while (scanner.hasNextLine()) {

			// We want to create String variable to hold each line that the
			// scanner reads.
			String line = scanner.nextLine();

			// As mentioned above we want to add stuff into the 4 lists created
			// above as long as
			// we still haven't iterated 4 times.
			if (count < 4) {

				// To achieve this we used a switch statement that keeps track
				// of count.
				switch (count) {

				// In the case that count is 0 we want to insert data into the
				// letterGrades array
				// since it is always the first line.
				case 0:

					// So we just enter a for loop looping through the line.
					for (int i = 0; i < line.length(); i++) {

						// If the the current letter that the for loop is on (by
						// taking the substring
						// from the index i to index i+1, is #, we take
						// everything to the right of it
						// as a comment so we just break out of the for loop).
						if (line.substring(i, i + 1).equals("#")) {
							break;
						}

						// So if the current letter that its on is not an empty
						// space, then we
						// add the current letter we are into the letterGrades
						// array at index letterGradesCount.
						if (!(line.substring(i, i + 1).equals(" "))) {

							letterGrades[letterGradesCount] = line.substring(i, i + 1);

							// And we also want to keep track of how many items
							// we've added into the array.
							// By incrementing letterGradesCount.
							letterGradesCount++;
						}

					}

					// And we can't forget to increment count before breaking.
					count++;
					break;

				// If count is 1, meaning we are reading from the second line of
				// the input,
				// then we want to insert the data we read into the thresholds
				// array.
				case 1:
					// Like before we create a new scanner.
					Scanner thresholdScanner = new Scanner(line);

					// Loop as long as the scanner has a next line.
					while (thresholdScanner.hasNext()) {

						// We then create a new String variable to hold the next
						// line.
						String thresholdString = thresholdScanner.next();

						// If the word is an hashtag again, then we want to
						// break out of the for loop.
						if (thresholdString.equals("#")) {

							break;

						} else {

							// If it is not then we want to add the word into
							// the thresholds array
							// at the index thresholdsCount.
							thresholds[thresholdsCount] = Integer.valueOf(thresholdString);

							// Then we increment thresholdsCount.
							thresholdsCount++;

						}

					}

					// Then we make sure to close the scanner. And increment
					// count.
					thresholdScanner.close();
					count++;
					break;

				// If count is 2 then we are reading the 3rd line of the input
				// which will be the
				// category names.
				case 2:

					// To avoid redundancy in the commenting, this case acts
					// exactly like case 1 but
					// inserting into the category array instead.
					Scanner categoryScanner = new Scanner(line);

					while (categoryScanner.hasNext()) {

						String categoryString = categoryScanner.next();

						if (categoryString.equals("#")) {

							break;

						} else {

							category[categoryCount] = categoryString;
							categoryCount++;

						}

					}

					categoryScanner.close();
					count++;
					break;

				// When count is 3, this means that we are reading the category
				// weights from the input.
				case 3:

					// Again, to avoid redundancy of commenting, this case acts
					// just like the previous
					// 2 cases but makes sure to insert it into the
					// categoryWeights array.
					Scanner categoryWeightsScanner = new Scanner(line);

					while (categoryWeightsScanner.hasNext()) {

						String categoryString = categoryWeightsScanner.next();

						if (categoryString.equals("#")) {

							break;

						} else {

							categoryWeights[categoryWeightsCount] = Integer.valueOf(categoryString);
							categoryWeightsCount++;

						}

					}
					count++;
					categoryWeightsScanner.close();

				}

				// We have to make sure to increment the count variable so that
				// the correct switch statements
				// get called.

				// So this else gets called when the count is greater 4 or
				// greater, meaning that we are starting
				// to read the scores the student achieved in each assignment.
			} else {

				// So we create a scanner that reads from the line.
				Scanner scoreScanner = new Scanner(line);

				// And we want to create a variable for each of the different
				// things that we read
				// from each line.

				// The assignment name as a string.
				String assignmentName = scoreScanner.next();
				// The points that the student earned as a double.
				double pointsEarned = scoreScanner.nextDouble();
				// The maximum points that can be earned for each assignment as
				// a double.
				double pointsPossible = scoreScanner.nextDouble();

				// Then we create a score object using these 3 variables.
				Score score = new Score(assignmentName, pointsEarned, pointsPossible);

				// And add the score into the scoreList variable.
				scoreList.add(score);

				// Then we make sure to close the scoreScanner.
				scoreScanner.close();

			}

		}

		System.out.println(getEstimateReport());

	}

	/**
	 * This method is used for passing grade information from the contents of
	 * the input
	 *
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS: the gradeInfo been stored in the estimator object
	 *
	 * @param gradeInfo
	 *            the grade information from input
	 * @return the estimator object
	 */
	public static GradeEstimator createGradeEstimatorFromFile(String gradeInfo)
			throws FileNotFoundException, GradeFileFormatException {

		// We want to create a new instance of the GradeEstimator passing
		// gradeInfo
		// which contains the contents of the input.
		GradeEstimator estimator = new GradeEstimator(gradeInfo);

		// Then we return the estimator object.
		return estimator;
	}

	/**
	 * This method contains all math calculation required for getting a
	 * student's letter grade and displays the final imformation of student's
	 * grade estimation, include absulote grade from each assigment, the
	 * estimation based on how many scores, the grade from each assigment after
	 * converting to 100% scale, the final percet grade and letter grade
	 *
	 *
	 * PRECONDITIONS: N/A
	 * 
	 * POSTCONDITIONS: N/A
	 *
	 * @param output
	 *            all the information need to be dispalyed in the output.
	 * @param average
	 *            the average score of each assigment
	 * @param scorecount
	 *            the number of scores which will be used to calculate the
	 *            average
	 * @param weightedPercent
	 *            the total weightPercent a student achieves
	 * @return output all the information need to be dispalyed in the output.
	 */
	public String getEstimateReport() {

		// For this method it ultimately is going to return a String that
		// contains everything that
		// needs to be in the output.
		String output = "";

		// So for this we want to loop through the category array because we
		// want to match
		// the different weights of the different cateogories with the scores
		// that they achieved.
		for (int i = 0; i < categoryCount; i++) {

			// The score iterator for this project actually created a score list
			// containing all the scores
			// from a scorelist that we pass that has the category that we
			// specify in the second parameter.
			ScoreIterator iterator = new ScoreIterator(scoreList, String.valueOf(category[i].charAt(0)));

			// So basically we want to calculate the average that they scored
			// for each unique assignment.
			// So we have to add all of the scores and divide by the amount of
			// scores we added.

			// So we create a double that will initially hold the total sum of
			// the scores.
			double average = 0.0;
			// And everytime we add into the average variable we increment
			// scoreCount so we know
			// what to divide by.
			int scoreCount = 0;

			// We want the while loop to continue looping as long as the
			// iterator has a next word.
			while (iterator.hasNext()) {

				// If it does, we want to create a new Score object by getting
				// whats next in the iterator.
				Score score = iterator.next();

				// Then we add it into the average variable by calling
				// .getPercent on the score.
				average = average + score.getPercent();

				// And each entry for the score starts with 3 spaces.
				output = output + score.getName() + "   ";

				// Then we concatenate the percent formated to 2 decimal places
				// with a new line at the end.
				output = output + String.format("%5.2f", score.getPercent()) + "\n";

				// And we make sure to increment score count.
				scoreCount++;

			}

			// Then finally we set average to be the score that it contained
			// divided by the
			// the scoreCount.
			average = ((average / scoreCount));

			// Then we add the average that we just calculated into the
			// weightedAverages array at index weightedAveragesCount and
			// increment weightedAveragesCount.
			weightedAverages[weightedAveragesCount] = average;
			weightedAveragesCount++;

		}

		// Then we want to let the user know what the grade estimte was based
		// on, so we concetanate that
		// onto the output variable.
		output = output + "Grade estimate is based on " + scoreList.size() + " scores\n";
		// Then we want to calculate the total weightedpercent that they
		// achieved so we create a variable
		// for this.
		double weightedPercent = 0.0;

		// Then we enter a for loop looping through the weightedAveragesCount.
		for (int i = 0; i < weightedAveragesCount; i++) {

			// Then we follow the output format calculating each weighted
			// average by multiplying the
			// percentage that they got in the weightedAverage by the
			// categoryWeights of each assignment
			// then divide by 100.
			output = output + "  " + String.format("%7.2f", weightedAverages[i] * categoryWeights[i] / 100) + "% ="
					+ String.format("%7.2f", weightedAverages[i]) + "% of " + categoryWeights[i] + "% for "
					+ category[i] + "\n";

			// And the calculation that was explained in the previous comment we
			// add into the weightedPercent
			// variable.
			weightedPercent = weightedPercent + weightedAverages[i] * categoryWeights[i] / 100;
		}

		// Then again, following the output format, we let them know the total
		// percentage that they scored.
		output = output + "--------------------------------\n" + String.format("%7.2f", weightedPercent)
				+ "% weighted percent\n" + "Letter Grade Estimate: ";

		// Finally we want to loop through the thresholdsCount to come up
		// with a letter grade they achieved.
		for (int i = 0; i < thresholdsCount; i++) {

			// So we check if the total weightedPercent that they achieved is
			// greater than a certain
			// threshold in the thresholds array.
			if (weightedPercent > thresholds[i]) {
				// Then we set the output to concatenate the letterGrades at the
				// index i.
				output = output + letterGrades[i];
				// And break out of the for loop.
				break;
			}
			
			//This if statement is going to b run if the end of the for loop was reached and there was
			//no matching threshold that matches what they achieved.
			if(i == (thresholdsCount - 1)) {
				
				//If this if statement is run, then we concatenate the following phrase below.
				output = output + "unable to estimate letter grade for " + weightedPercent;
				
			}

		}

		// Then we return the output.
		return output;

	}

}
