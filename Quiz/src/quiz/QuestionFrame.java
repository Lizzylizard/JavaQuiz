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

public class QuestionFrame extends JFrame implements ActionListener {	
	private static int instanceCounter = 0;
	static MyFile file = new MyFile();
	static Quiz myQuiz = new Quiz();
	
	static String[][] questions;
	static int next;
	
	JLabel qLabel, aLabel, bLabel, cLabel, dLabel;
	JButton btnA, btnB, btnC, btnD;	
	JFrame quesFrame = new JFrame();
	JPanel quesPanel, ansPanel, btnPanel;
	Container masterPanel;
	Font head;
	Font text;
	
	public QuestionFrame() {    
		if(instanceCounter <= 0) {
			//System.out.println("First instance! Counter = " + this.instanceCounter);
	    	this.myQuiz.file.readQuestions();
			
			this.questions = this.myQuiz.file.getQuestions();
			this.instanceCounter++;
		} else {
			//System.out.println("There's already an instance! Counter = " + this.instanceCounter);
			this.instanceCounter++;
		}
		
    	this.next = this.myQuiz.getNextQuestion();
    	
		String question = this.questions[this.next][0];
		
		String ansA = this.questions[this.next][1];	
		String ansB = this.questions[this.next][2];
		String ansC = this.questions[this.next][3];
		String ansD = this.questions[this.next][4];		
		
		quesFrame = new JFrame();
		quesFrame.setSize(1500, 900);
		
		masterPanel = quesFrame.getContentPane();
				
		head = new Font("Arial", Font.PLAIN, 20);
		text = new Font("Arial", Font.PLAIN, 15);
		quesPanel = new JPanel();
		quesPanel.setLayout(new FlowLayout());
		qLabel = new JLabel(question);
		qLabel.setFont(head);
		quesPanel.add(qLabel);
		
		btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(0, 4));
		
		JLabel placeholder10 = new JLabel();
		btnPanel.add(placeholder10);
		JLabel placeholder11 = new JLabel();
		btnPanel.add(placeholder11);
		JLabel placeholder12 = new JLabel();
		btnPanel.add(placeholder12);
		JLabel placeholder13 = new JLabel();
		btnPanel.add(placeholder13);
		
		JLabel placeholder1 = new JLabel();
		btnPanel.add(placeholder1);
		
		btnA = new JButton(ansA);
		btnA.setFont(text);
    	btnA.addActionListener(this);
		btnPanel.add(btnA);
		
    	btnB = new JButton(ansB);
		btnB.setFont(text);
    	btnB.addActionListener(this);
    	btnPanel.add(btnB);    	

		JLabel placeholder2 = new JLabel();
		btnPanel.add(placeholder2);    	
		JLabel placeholder3 = new JLabel();
		btnPanel.add(placeholder3);
    			
		btnC = new JButton(ansC);
		btnC.setFont(text);
    	btnC.addActionListener(this);
    	btnPanel.add(btnC);
		
		btnD = new JButton(ansD);
		btnD.setFont(text);
    	btnD.addActionListener(this);
    	btnPanel.add(btnD);		
    	
    	JLabel placeholder5 = new JLabel();
		btnPanel.add(placeholder5);
    	
		JLabel placeholder6 = new JLabel();
		btnPanel.add(placeholder6);
		JLabel placeholder7 = new JLabel();
		btnPanel.add(placeholder7);
		JLabel placeholder8 = new JLabel();
		btnPanel.add(placeholder8);
		JLabel placeholder9 = new JLabel();
		btnPanel.add(placeholder9);
		
		masterPanel.add(quesPanel, BorderLayout.PAGE_START);
		masterPanel.add(btnPanel, BorderLayout.CENTER);
		
		quesFrame.setVisible(true);
	}
	
	public void actionPerformed (ActionEvent ae){
		int usersAnswer;
		int[] correctAnswers = file.getAnswers();
		int correctAnswer = correctAnswers[next];
		boolean correct = false;
        if(ae.getSource() == this.btnA){ 
        	correct = myQuiz.compareAnswer(myQuiz, next, 1);
        	usersAnswer = 1;
        } else if(ae.getSource() == this.btnB) {
        	correct = myQuiz.compareAnswer(myQuiz, next, 2); 
        	usersAnswer = 2;       	
        } else if(ae.getSource() == this.btnC) {
        	correct = myQuiz.compareAnswer(myQuiz, next, 3);
        	usersAnswer = 3;        	
        } else {
        	correct = myQuiz.compareAnswer(myQuiz, next, 4); 
        	usersAnswer = 4;       	
        }
        
        if(correct) {
        	//JOptionPane.showMessageDialog(quesFrame, "Correct");
        	switch(usersAnswer) {
	        	case 1: 
	            	btnA.setBackground(Color.GREEN);
	            	break;
	        	case 2: 
	            	btnB.setBackground(Color.GREEN);
	            	break;	        	
	            case 3: 
		            btnC.setBackground(Color.GREEN);
		            break;	        	
		        case 4: 
		            btnD.setBackground(Color.GREEN);
		            break;
        	}
        } else {
        	//JOptionPane.showMessageDialog(quesFrame, "Incorrect");
        	switch(usersAnswer) {
	        	case 1: 
	            	btnA.setBackground(Color.RED);
	            	break;
	        	case 2: 
	            	btnB.setBackground(Color.RED);
	            	break;	        	
	            case 3: 
		            btnC.setBackground(Color.RED);
		            break;	        	
		        case 4: 
		            btnD.setBackground(Color.RED);
		            break;
        	}
        	switch(correctAnswer) {
	        	case 1: 
	            	btnA.setBackground(Color.GREEN);
	            	break;
	        	case 2: 
	            	btnB.setBackground(Color.GREEN);
	            	break;	        	
	            case 3: 
		            btnC.setBackground(Color.GREEN);
		            break;	        	
		        case 4: 
		            btnD.setBackground(Color.GREEN);
		            break;
        	}
        }
        
        try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(this.instanceCounter < 5) {
        	quesFrame.dispose();
        	new QuestionFrame();
        } else {
        	//ending screen
        	System.exit(0);
        }
    }

}