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
	 * Chooses the next question that the user will be asked randomly and returns it.
	 * @return String, question.
	 */
	private String getNextQuestion() {
		String question = "";
		int[] alreadyAsked = file.getAskedQuestions();
		String[][] questions = file.getQuestions();
		boolean isDouble = false;
		
		//do as long as the String is still empty
		while(question.equals("")) {
			//choose a random index between 0 and 49
			int next = (int) Math.floor(Math.random() * 49);
			//check whether this index exists in the questions-array
			if(questions[next][0] != null) {
				//if it does exist, check, whether this question has already been asked
				for(int i = 0; i < alreadyAsked.length; i++) {
					//if already asked, choose another random number
					if(next == alreadyAsked[i]) {
						System.out.println(next + " = doppelt");
						isDouble = true;
						break;
					} 
				}
				
				//if not, get the question and store the index in the askedQuestions-array
				if(!isDouble) {
					question = questions[next][0];
					file.setAskedQuestions(next);
				}
				isDouble = false;
			}
		}
			
		//return the chosen question
		return question;
	}
	
	/**
	 * Prints the next question on the console.
	 */
	public void showNextQuestion() {
		System.out.println(getNextQuestion() + "\n");
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
