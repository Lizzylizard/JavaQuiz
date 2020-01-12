package quiz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TutorialFrame implements ActionListener {
	
	JButton btnGo;	
	JFrame tutFrame = new JFrame();
	FlowLayout layout = new FlowLayout();
	
	public TutorialFrame() {
		tutFrame.setSize(400, 300);
    	tutFrame.setLayout(layout);    
    	tutFrame.setTitle("Tutorial");
				
    	btnGo = new JButton("Go");
    	btnGo.setBounds(20, 100, 50, 12);
    	btnGo.addActionListener(this);
		tutFrame.add(btnGo);
		
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
