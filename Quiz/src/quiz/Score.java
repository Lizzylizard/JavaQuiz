package quiz;

import java.awt.*;
import javax.swing.*;

public class Score {

	JFrame frame = new JFrame();
	JLabel text, scoreTxt, advise;
	
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
		
		frame.add(text, BorderLayout.PAGE_START);
		frame.add(scoreTxt, BorderLayout.CENTER);
		frame.add(advise, BorderLayout.PAGE_END);
		
		frame.setVisible(true);
	}
}
