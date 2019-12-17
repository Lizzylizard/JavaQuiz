package quiz;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class MyFile {
	Scanner sc = new Scanner(System.in);
	String[][] questions = new String[50][5];
	int[] correctAnswer = new int[50];
	int[] askedQuestions = new int[50];
	
	public void readQuestions() {
		try {
			//wie nicht mit festem pfad??
		      File myObj = new File("C:/Users/Elisabeth/git/JavaQuiz/Quiz/src/quiz/questions.txt");
		      Scanner myReader = new Scanner(myObj);
		      int i = 0;
		      while (myReader.hasNextLine()) {
		        questions[i][0] = myReader.nextLine();
		        System.out.println(questions[i][0]);
		        i++;
		        this.storeQuestionsAndAnswers();
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	private void storeQuestionsAndAnswers() {
		for(int i = 0; i < questions.length; i++) {
			String line = questions[i][0];
			String[] strings = line.split("( ä )");
			for(int j = 0; j < strings.length; j++) {
				questions[i][j] = strings[j];
				System.out.println(questions[i][j]);
			}
		}
	}
	
	public String[][] getQuestions() {
		return this.questions;
	}
	
	public int[] getAnswers() {
		return this.correctAnswer;
	}
	
	public int[] getAskesQuestions() {
		return this.askedQuestions;
	}
	
	
}
