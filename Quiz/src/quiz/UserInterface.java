package quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Shows the welcome screen.
 * @author Elisabeth
 *
 */
public class UserInterface implements ActionListener{
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JButton btnStart;
	
	FlowLayout layout = new FlowLayout();
	
	public void init() {
		frame.setSize(400, 300);
		frame.setLayout(layout);
    	frame.setTitle("Welcome");
		
		JLabel label = new JLabel();
		label.setText("Welcome to our Java Quiz :)");
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		frame.add(label);	
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(this);
		//btnStart.setAlignmentY(500);
		frame.add(btnStart);
		
		frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);		
		frame.setVisible(true);
	}
	
	public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == this.btnStart){
    		//System.out.println("Start clicked");
        	frame.dispose();
        	new PWFrame();
        }
    }

}
