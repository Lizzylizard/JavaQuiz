package quiz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Shows the user's score and grades him based on it.
 * Ends program if clicked on a button.
 * @author Elisabeth
 *
 */
public class Score implements ActionListener{

	JFrame frame = new JFrame();
	JLabel text, scoreTxt, advise;
	JButton btnEnd = new JButton("End");
	Font f1 = new Font("Arial", Font.PLAIN, 20);
	Font f2 = new Font("Arial", Font.PLAIN, 15);
	
	public Score (int score) {
		frame.setLayout(new BorderLayout());
		frame.setSize(400, 300);
		
		//grade the user based on his score 
		if (score < 5) {
			text = new JLabel("Oh no");
			scoreTxt = new JLabel("Your score is only " + score + ".");
			advise = new JLabel("You should practice your Java skills!");
		} else if (score >= 5 && score <= 10) {
			text = new JLabel("You are doing ok.");
			scoreTxt = new JLabel("Your score is " + score + ".");
			advise = new JLabel("But try to practice a bit more.");		
		} else if (score > 10 && score < 15) {
			text = new JLabel("Good :)");
			scoreTxt = new JLabel("Your score is " + score + ".");
			advise = new JLabel("");			
		} else if (score >= 15 && score < 20) {
			text = new JLabel("Very good :)");
			scoreTxt = new JLabel("Your score is " + score + ".");
			advise = new JLabel("");	
		} else {
			text = new JLabel("Congratulations! You're an expert of Java.");
			scoreTxt = new JLabel("Your score is " + score + ".");
			advise = new JLabel("");
		}
		
		text.setFont(f1);
		scoreTxt.setFont(f2);
		advise.setFont(f2);
		frame.add(text, BorderLayout.PAGE_START);
		frame.add(scoreTxt, BorderLayout.CENTER);
		frame.add(advise, BorderLayout.PAGE_END);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
	}
}
