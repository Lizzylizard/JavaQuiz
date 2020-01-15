package quiz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 * Shows the password screen.
 * @author Elisabeth
 *
 */
public class PWFrame implements ActionListener {
	
	//final private String pw = "62210597";
	final private String pw = "6";
	MyFile file = new MyFile();
	
	JButton btnLogIn;	
	JFrame pwFrame = new JFrame();
	FlowLayout layout = new FlowLayout();
	
	JLabel pwLabel;
	JPasswordField pwField;
	
	public PWFrame() {
    	pwFrame.setSize(400, 300);
    	pwFrame.setLayout(layout);
    	pwFrame.setTitle("Password");
    	
		pwLabel = new JLabel("Password: ");
		pwField = new JPasswordField(20);
		pwLabel.setLabelFor(pwField);
		pwFrame.add(pwLabel);
		pwFrame.add(pwField);            
		
		
		btnLogIn = new JButton("Log in");
		btnLogIn.setBounds(20, 100, 50, 12);
		btnLogIn.addActionListener(this);
		pwFrame.add(btnLogIn);
		
    	pwFrame.setVisible(true);
	}
	
	/**
	 * Checks whether password is correct.
	 * @return
	 */
	public boolean checkPW() {
		boolean correct = false;
		
		//get the user input
		char[] input = pwField.getPassword();
		
		//only if the input is as long as the password it has the chance to be correct
		if(input.length == pw.length()) {
			for(int i = 0; i < input.length; i++) {
				//compare every char 
				if(input[i] == pw.charAt(i)) {
					correct = true;				
				} else {
					correct = false;
					//as soon as one character is false, the whole password is false
					break;
				}
			} 
		} else {
			correct = false;
		}
		//return whether correct or not
		return correct;
	}
	
	public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == this.btnLogIn){
        	//if correct go on
        	//else end program
        	if(checkPW()) {
        		JOptionPane.showMessageDialog(pwFrame, "Password correct");
        		pwFrame.dispose();
        		new TutorialFrame();
        	} else {
        		JOptionPane.showMessageDialog(pwFrame, "Password INCORRECT");
        		System.exit(0);
        	}
        }
    }

}
