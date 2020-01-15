package quiz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Shows the tutorial frame.
 * @author Elisabeth
 *
 */
public class TutorialFrame implements ActionListener {
	
	JButton btnGo;	
	JFrame tutFrame = new JFrame();
	BorderLayout layout = new BorderLayout();
	JLabel title = new JLabel();
	JLabel descr = new JLabel();
	
	public TutorialFrame() {
		tutFrame.setSize(400, 300);
    	tutFrame.setLayout(layout);    
    	tutFrame.setTitle("Tutorial");
    	
    	title.setText("Tutorial");
    	title.setFont(new Font("Arial", Font.PLAIN, 20));
    	tutFrame.add(title, BorderLayout.PAGE_START);
    	
    	String text = "<html>1. Read the question <br>2. Click on the correct answer "
    			+ "<br>3. Repeat 20 times <br>4. See your result <br><br>Good Luck!</html>";    	
    	descr.setText(text);
    	descr.setFont(new Font("Arial", Font.PLAIN, 15));
    	tutFrame.add(descr, BorderLayout.LINE_START);
				
    	btnGo = new JButton("Go");
    	btnGo.setBounds(20, 100, 50, 12);
    	btnGo.addActionListener(this);
		tutFrame.add(btnGo, BorderLayout.PAGE_END);
		
    	tutFrame.setVisible(true);
	}
	
	public void actionPerformed (ActionEvent ae){
        if(ae.getSource() == this.btnGo){
        	//do something
    		tutFrame.dispose();
    		new QuestionFrame();
        }
    }

}
