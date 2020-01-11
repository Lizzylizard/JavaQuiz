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
	int[] askedQuestions = new int[20];
	
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
		      
		      //and fill the askedQuestions array with only zeros to start with
		      this.initializeAskedQuestions();
		      
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
	 * Stores the index to the correct answer to a question in correctAnswer-array.
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
	 * Fills the askedQuestions-array with only zeros at the beginning.
	 */
	private void initializeAskedQuestions() {
		for(int i = 0; i < this.askedQuestions.length; i++) {
			this.askedQuestions[i] = -1;
		}
	}
	
	/**
	 * Replaces the Xs that mark the correct answer by their original answer letter.
	 * IMPORTANT: this HAS TO BE called AFTER storing the answers and correct answers, otherwise the 'X' marking the
	 * correct answer will have disappeared!
	 */
	public void prepareAnswers() {
		//go through all the questions
		for(int i = 0; i < questions.length; i++) {
			//skip the actual questions, just interested in the answers, so start at index 1
			for(int j = 1; j < questions[i].length; j++) {
				//get the current answer
				String currAns = this.questions[i][j];
				if(currAns != null) {
					String ans = "X";
					//if current answer start with 'X', replace it by the character that would normally be in its place.
					if(currAns.charAt(0) == 'X') {
						//if index of answer can be divided by 4 without a remainder, it should be answer 4 -> D
						//1 -> A
						//2 -> B
						//3 -> C
						int mod = j % 4;
						switch(mod) {
							case 0: 
								ans = "D";
								break;
							case 1: 
								ans = "A";
								break;
							case 2:
								ans = "B";
								break;
							case 3:
								ans = "C";
								break;
							default: System.out.println("Error");						
						}
						//split answer on ')'
						String[] answers = currAns.split("([)])");
						//replace the character before ')' by the actual character
						answers[0] = ans;
						//replace it in the questions matrix, so it will be displayed on screen without the 'X'
						String complete = "";
						for(int h = 0; h < answers.length; h++) {
							if(h > 0) {
								complete += ")" + answers[h];
							} else {
								complete += answers[h];								
							}
						}
						this.questions[i][j] = complete;
						//System.out.println(this.questions[i][j]);
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
	
	/**
	 * Stores the index of an already asked question in the askedQuestions-array.
	 * @param index, int - the index of the already asked question from the questions-array.
	 */
	public void setAskedQuestions(int index) {
		for(int i = 0; i < this.askedQuestions.length; i++) {
			//if the next number in the askedQuestions-array is -1 it means, the index has to be stored here,
			//because there can not be an index -1 in the questions-array and therefore -1 can not be an index
			//for a question that has already been asked
			
			if(this.askedQuestions[i] == -1) {
				this.askedQuestions[i] = index;
				//stop after the first -1 (because otherwise the whole array will be filled only with the first index)
				break;
			}
		}
	}
	
	
}
