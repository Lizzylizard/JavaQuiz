package quiz;

public class Quiz {
	MyFile file = new MyFile();
	
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
	 * Prints the next question on the console.
	 */
	public void showNextQuestion() {
		int next = getNextQuestion();
		file.prepareAnswers();
		String[][] questions = file.getQuestions();
		System.out.println(questions[next][0]);
		System.out.println("\t" + questions[next][1]);
		System.out.println("\t" + questions[next][2]);
		System.out.println("\t" + questions[next][3]); 
		System.out.println("\t" + questions[next][4] + "\n");
	}

	public static void main(String[] args) {
		Quiz myQuiz = new Quiz();
		myQuiz.file.readQuestions();
		//myQuiz.showQuestions();
		for(int i = 0; i < 20; i++) {
			System.out.print((i + 1) + ")");
			myQuiz.showNextQuestion();
		}
	}

}
