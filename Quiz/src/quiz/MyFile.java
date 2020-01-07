package quiz;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

/**
 * A class where all the methods are stored that are needed in order to read and write the .txt files.
 * @author Elisabeth
 *
 */
public class MyFile {
	//the matrix where every question with its' answers is stored
	String[][] questions = new String[50][5];
	//the array where the indices of the correct answers are stored
	int[] correctAnswer = new int[50];
	//the array where the indices of the questions that have already been asked are stored
	int[] askedQuestions = new int[50];
	
	/**
	 * Opens the questions.txt file and reads all questions with answers from it.
	 */
	public void readQuestions() {
		try {
			//wie nicht mit festem pfad??
			//open the correct file
		      File myTxtFile = new File("C:/Users/Elisabeth/git/JavaQuiz/Quiz/src/quiz/questions.txt");
		      Scanner sc = new Scanner(myTxtFile);
		      int i = 0;
		      //read every line (line = 1 question with its four answers)
		      //store this line as a row of the questions matrix
		      while (sc.hasNextLine()) {
		        questions[i][0] = sc.nextLine();
		        i++;
		        this.storeQuestionsAndAnswers();
		      }
				
		      //after file is read, store the correct answers separately in an array
		      this.storeCorrectAnswers();
		      
		      sc.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	}
	
	/**
	 * Stores the questions and answers at the right position of the questions matrix.
	 */
	private void storeQuestionsAndAnswers() {
		for(int i = 0; i < questions.length; i++) {
			//the current question
			String line = questions[i][0];
			if( line != null) {
				//splits the current question on the character 'ä' since this is a character, that will most likely not be used 
				//in English questions
				String[] strings = line.split("( ä )");
				for(int j = 0; j < strings.length; j++) {
					//stores the answers as columns of the question matrix
					questions[i][j] = strings[j];
				}
			}
		}
	}
	
	/**
	 * Stores the correct answer to a question in correctAnswer-array.
	 * For example: questions[1][4] holds the correct answer to question 2. Then the value in correctAnswer[1] will be = 4.
	 */
	private void storeCorrectAnswers() {
		for(int i = 0; i < this.questions.length; i++) {
			String currQues = this.questions[i][0];
			//check if there is a next question in the questions-array
			if (currQues != null) {
				for(int j = 0; j < this.questions[i].length; j++) {
					//String that holds the answer j to question i 
					String currAns = this.questions[i][j];
					//check if there is a next answer to the current question
					if(currAns != null) {
						//if the first letter of the answer is an X, it means it is the correct answer -> has to be put in correctAnswer
						if(currAns.charAt(0) == 'X') {
							this.correctAnswer[i] = j;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Returns the questions matrix.
	 * @return String[][] - the question matrix.
	 */
	public String[][] getQuestions() {
		return this.questions;
	}
	
	/**
	 * Returns the array with the indices of the correct answers.
	 * @return int[] - correct answer array.
	 */
	public int[] getAnswers() {
		return this.correctAnswer;
	}
	
	/**
	 * Returns the array with the indices of the questions that have already been asked.
	 * @return int[] - the asked questions array.
	 */
	public int[] getAskedQuestions() {
		return this.askedQuestions;
	}
	
	
}
