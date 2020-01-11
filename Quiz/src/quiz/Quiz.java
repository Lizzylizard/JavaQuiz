package quiz;

import java.util.Scanner;

public class Quiz {
	MyFile file = new MyFile();
	private int score = 0;
	
	/**
	 * Shows all questions stored in the questions matrix and their correct answers.
	 * Just for testing purposes.
	 */
	public void showQuestions() {
		String[][] questions = file.getQuestions();
		int[] answers = file.getAnswers();
		for(int i = 0; i < questions.length; i++) {
			for(int j = 0; j < questions[i].length; j++) {
				if(questions[i][j] != null) {
					System.out.println(questions[i][j]);
				}
			}
			if(answers[i] != 0) {
				System.out.println("Correct answer is: " + answers[i] + "\n");
			}
		}
	}
	
	/**
	 * Chooses the next question that the user will be asked randomly and returns its index.
	 * @return int, index of chosen question.
	 */
	private int getNextQuestion() {
		//will hold the index of the question that is chosen to be the next one
		int next = -1;
		int[] alreadyAsked = file.getAskedQuestions();
		//a flag, states whether the random chosen question was already asked or not
		//has to be true in the beginning so the while-loop will at least be entered once
		boolean isDouble = true;
		
		//as long as the chosen question has already been asked, there must be chosen another one -> loop has to do
		//another iteration
		while(isDouble) {
			//we do not know yet, whether the question has already been asked, so set to false
			isDouble = false;
			
			//choose a random number between 0 and 49, because we have 50 questions
			next = (int) Math.floor(Math.random() * 50);
			
			//check in the alreadyAsked-array, whether the question has already been asked or not
			for(int i = 0; i < alreadyAsked.length; i++) {
				if(alreadyAsked[i] == next) {
					//set flag to true, we need to choose another question -> next while-iteration
					isDouble = true;
					//System.out.println("Double!");
				}
			}
			
			//if question has NOT already been asked, we can choose the question with the randomly generated index
			if(!isDouble) {
				//put it NOW in the alreadyAsked-array, so it won't be asked twice
				file.setAskedQuestions(next);
				//set the flag to false, no need to look for another question -> no further while-iterations needed
				isDouble = false;
			} 
		}
			
		//return the index of the chosen question
		return next;
	}
	
	/**
	 * Compares the user's answer to the correct answer.
	 * @param myQuiz, an instance of this class that the main method is working with.
	 * @param next, the currently displayed question
	 * @param sc, the currently used scanner
	 * @return boolean, true if user was correct, false if user was wrong
	 */
	private boolean compareAnswer(Quiz myQuiz, int next, Scanner sc) {
		//return value
		boolean correct = false;
		//get the user's answer
		String ans = sc.nextLine();
		int indexOfAnswer = 0;
		
		//if user typed 'A' or 'a' -> index is 1
		//'B' or 'b' -> index 2
		//'C' or 'c' -> index 3
		//'D' or 'd' -> index 4
		switch(ans) {
			case "A": 
				indexOfAnswer = 1;
				break;
			case "a": 
				indexOfAnswer = 1;
				break;
			case "B": 
				indexOfAnswer = 2;
				break;
			case "b": 
				indexOfAnswer = 2;
				break;
			case "C": 
				indexOfAnswer = 3;
				break;
			case "c": 
				indexOfAnswer = 3;
				break;
			case "D": 
				indexOfAnswer = 4;
				break;
			case "d": 
				indexOfAnswer = 4;
				break;
		}
		
		//get the questions
		String[][] questions = myQuiz.file.getQuestions();
		//get the correct answers
		int[] corrAns = myQuiz.file.getAnswers();
		
		//compare user's answer to correct answer to the currently displayed question
		if(indexOfAnswer == corrAns[next]) {
			System.out.println("Correct! Your answer was: " + questions[next][indexOfAnswer] + "\n");
			correct = true;
		}else {
			System.out.println("Wrong! Correct answer was: " + questions[next][corrAns[next]] + "\n");
			correct = false;
		}
		
		//return whether user was correct or not
		return correct;
	}
	
	/**
	 * Prints the next question on the console.
	 * @param index, number questions that user has done
	 * @return next, int-index of the currently displayed question
	 */
	public int showNextQuestion(int index) {
		int next = getNextQuestion();
		file.prepareAnswers();
		String[][] questions = file.getQuestions();
		System.out.println((index + 1) + ")" + questions[next][0]);
		System.out.println("\t" + questions[next][1]);
		System.out.println("\t" + questions[next][2]);
		System.out.println("\t" + questions[next][3]); 
		System.out.println("\t" + questions[next][4] + "\n");
		return next;
	}
	
	/**
	 * Holds the main logic of the quiz. Just so the main-method stays organized.
	 * @param myQuiz, an instance of this class that the main method is working with.
	 */
	public void mainProcedure(Quiz myQuiz) {
		//Scanner to read the answers from the user
		Scanner sc = new Scanner(System.in);
		//user has to answer 20 questions
		for(int i = 0; i < 20; i++) {
			//show first question
			int next = myQuiz.showNextQuestion(i);
			//get the user's answer and compare it to the correct one
			boolean isCorrect = myQuiz.compareAnswer(myQuiz, next, sc);	
			//if user was correct, update his score
			if(isCorrect) {
				this.score++;
			}
		}
		
		if (this.score < 5) {
			System.out.println("Oh no :( Your score is only " + this.score + ".");
			System.out.println("You should practice your Java skills!");
		} else if (this.score >= 5 && this.score <= 10) {
			System.out.println("You are doing ok. Your score is " + this.score + ".");
			System.out.println("But try to practice a bit more.");			
		} else if (this.score > 10 && this.score < 15) {
			System.out.println("Good :) Your score is " + this.score + ".");		
		} else if (this.score >= 15 && this.score < 20) {
			System.out.println("Very good :) Your score is " + this.score + ".");		
		} else {
			System.out.println("Congratulations! You're an expert of Java.");
			System.out.println("Your score is " + this.score);
		}
		
		//Close the scanner at the end
		sc.close();
	}

	public static void main(String[] args) {
		Quiz myQuiz = new Quiz();
		myQuiz.file.readQuestions();		

		//greet the user
		//explain what the user has to do
		
		//main procedure
		myQuiz.mainProcedure(myQuiz);
	}

}
