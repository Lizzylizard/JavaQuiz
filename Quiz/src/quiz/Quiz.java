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

	public static void main(String[] args) {
		Quiz myQuiz = new Quiz();
		myQuiz.file.readQuestions();
		myQuiz.showQuestions();
	}

}
