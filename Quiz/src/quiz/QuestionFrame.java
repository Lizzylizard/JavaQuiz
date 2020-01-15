package quiz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Shows the question frame. Main method of the graphical interface application.
 * @author Elisabeth
 *
 */
public class QuestionFrame extends JFrame implements ActionListener {	
	private static int instanceCounter = 0;
	static MyFile file = new MyFile();
	static Quiz myQuiz = new Quiz();
	
	static String[][] questions;
	static int next;
	static int score = 0;
	
	JLabel qLabel, aLabel, bLabel, cLabel, dLabel;
	JButton btnA, btnB, btnC, btnD;	
	JFrame quesFrame = new JFrame();
	JPanel quesPanel, ansPanel, btnPanel;
	Container masterPanel;
	Font head;
	Font text;
	
	public QuestionFrame() { 
		//check whether there has already been a question frame before
		//if so: only count how many questions have been answered already
		//if not: 
			//read the questions from the txt file and store them in arrays
			//count how many questions have been answered already
		if(instanceCounter <= 0) {
	    	this.myQuiz.file.readQuestions();
			
			this.questions = this.myQuiz.file.getQuestions();
			this.instanceCounter++;
		} else {
			this.instanceCounter++;
		}
		
		//get next question
    	this.next = this.myQuiz.getNextQuestion();
    	
    	//make the strings short so they can be displayed properly
		String question = makeShortString(this.questions[this.next][0]);
		
		String ansA = makeShortString(this.questions[this.next][1]);			
		String ansB = makeShortString(this.questions[this.next][2]);
		String ansC = makeShortString(this.questions[this.next][3]);
		String ansD = makeShortString(this.questions[this.next][4]);		
		
		//set up the screen properly
		setUp(question, ansA, ansB, ansC, ansD);
		
	}
	
	/**
	 * Breaks a long string into two strings divided by a new line.
	 * @param str, String - that is going to be short
	 * @return String - shorter version with a new line
	 */
	public String makeShortString(String str) {
		String res = "";
		String copy1 = str;
		String copy = str;
		String beg = "";
		String end = "";		
		
		//if string is long
		if(str.length() > 32) {
			for(int i = 31; i < str.length(); i++) {
				if(str.charAt(i) == ' ') {
					//break string after a word on an empty character
					beg = str.substring(0, i);
					end = copy1.substring(i, copy1.length());
					break;
				}
			}
			//put a new line between the two strings
			res = "<html>" + beg + "<br>" + end + "</html>";
			//return it as a single string again
			if(res.length() == 17) {
				return str;
			} else {
				return res;
			}
		} else {
			return copy;
		}
	}
	
	/**
	 * Sets up the screen for the user.
	 * @param question, String - the question
	 * @param ansA, String - answer A
	 * @param ansB, String - answer B
	 * @param ansC, String - answer C
	 * @param ansD, String - answer D
	 */
	public void setUp(String question, String ansA, String ansB, String ansC, String ansD) {
		//set the whole window
		quesFrame = new JFrame();
		quesFrame.setSize(1500, 900);
		
		//the highest panel, all components will be added to it.
		masterPanel = quesFrame.getContentPane();
		
		//two different fonts
		head = new Font("Arial", Font.PLAIN, 20);
		text = new Font("Arial", Font.PLAIN, 18);
		
		//the question panel
		quesPanel = new JPanel();
		//FlowLayout = simply add components to the end of the layout
		quesPanel.setLayout(new FlowLayout());
		qLabel = new JLabel(question);
		qLabel.setFont(head);
		quesPanel.add(qLabel);
		
		//the button panel for the answers
		btnPanel = new JPanel();
		//grid layout consisting of as many rows as needed and 4 columns
		btnPanel.setLayout(new GridLayout(0, 4));
		
		//leave the first row empty
		JLabel placeholder10 = new JLabel();
		btnPanel.add(placeholder10);
		JLabel placeholder11 = new JLabel();
		btnPanel.add(placeholder11);
		JLabel placeholder12 = new JLabel();
		btnPanel.add(placeholder12);
		JLabel placeholder13 = new JLabel("Score: " + score);
		btnPanel.add(placeholder13);
		
		//leave row 2, col 1 empty
		JLabel placeholder1 = new JLabel();
		btnPanel.add(placeholder1);
		
		//add two buttons (answer A and B to row 2, column 2 and 3
		btnA = new JButton(ansA);
		btnA.setFont(text);
    	btnA.addActionListener(this);
		btnPanel.add(btnA);
		
    	btnB = new JButton(ansB);
		btnB.setFont(text);
    	btnB.addActionListener(this);
    	btnPanel.add(btnB);    	

    	//leave row 2, col 4 and row 3, col 1 empty
		JLabel placeholder2 = new JLabel();
		btnPanel.add(placeholder2);    	
		JLabel placeholder3 = new JLabel();
		btnPanel.add(placeholder3);
    		
		//add two buttons (answer C and D to row 3, col 2 and 3
		btnC = new JButton(ansC);
		btnC.setFont(text);
    	btnC.addActionListener(this);
    	btnPanel.add(btnC);
		
		btnD = new JButton(ansD);
		btnD.setFont(text);
    	btnD.addActionListener(this);
    	btnPanel.add(btnD);		
    	
    	//leave row 3, col 4 empty
    	JLabel placeholder5 = new JLabel();
		btnPanel.add(placeholder5);
    	
		//leave last row empty
		JLabel placeholder6 = new JLabel();
		btnPanel.add(placeholder6);
		JLabel placeholder7 = new JLabel();
		btnPanel.add(placeholder7);
		JLabel placeholder8 = new JLabel();
		btnPanel.add(placeholder8);
		JLabel placeholder9 = new JLabel();
		btnPanel.add(placeholder9);
		
		//add question to the top of the master panel
		masterPanel.add(quesPanel, BorderLayout.PAGE_START);
		//add button (answer) panel underneath it
		masterPanel.add(btnPanel, BorderLayout.CENTER);
		
		//display everything
		//quesFrame.pack();
		quesFrame.setVisible(true);
	}
	
	/**
	 * Performs the main routine.
	 * Compares user's answer to correct answer.
	 * Updates score.
	 * Shows next question as long as the user has not answered 20 questions yet.
	 * Opens score screen if user finished 20 questions.
	 */
	public void actionPerformed (ActionEvent ae){
		int usersAnswer;
		//get the correct answer
		int[] correctAnswers = myQuiz.file.getAnswers();
		int correctAnswer = correctAnswers[this.next];
		boolean correct = false;
		//check whether user was correct
        if(ae.getSource() == this.btnA){ 
        	//if user clicked button A, answer A is meant, that is index 1
        	correct = myQuiz.compareAnswer(myQuiz, this.next, 1);
        	//set user's answer to index 1
        	usersAnswer = 1;
        } else if(ae.getSource() == this.btnB) {
        	//if user clicked button B, answer B is meant, that is index 2
        	correct = myQuiz.compareAnswer(myQuiz, this.next, 2); 
        	//set user's answer to index 2
        	usersAnswer = 2;       	
        } else if(ae.getSource() == this.btnC) {
        	//if user clicked button C, answer C is meant, that is index 3
        	correct = myQuiz.compareAnswer(myQuiz, this.next, 3);
        	//set user's answer to index 3
        	usersAnswer = 3;        	
        } else {
        	//if user clicked button D, answer D is meant, that is index 4
        	correct = myQuiz.compareAnswer(myQuiz, this.next, 4); 
        	//set user's answer to index 4
        	usersAnswer = 4;       	
        }
        
        //Tell user whether he was correct or not
        //show him the correct answer (again)
        if(correct) {
        	JOptionPane.showMessageDialog(quesFrame, "Correct\nYour answer was: " + questions[this.next][usersAnswer]);
        	this.score++;
        } else {
        	JOptionPane.showMessageDialog(quesFrame, "Incorrect\nCorrect answer should have been: " 
        			+ questions[this.next][correctAnswer],"Incorrect", JOptionPane.WARNING_MESSAGE);
        }
        
        //check if user already answered 20 questions
        //if so, dispose frame and go to score screen
        //if not, show another question
        if(this.instanceCounter < 20) {
        	quesFrame.dispose();
        	new QuestionFrame();
        } else {
        	//ending screen
        	quesFrame.dispose();
        	new Score(score);
        }
    }

}