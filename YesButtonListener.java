import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class YesButtonListener implements ActionListener {
	private RunLoop programLoop;
	private JButton yesBtn;
	private JButton noBtn;
	
	public YesButtonListener(RunLoop programLoop, JButton yesBtn, JButton noBtn){
		this.programLoop = programLoop;
		this.yesBtn= yesBtn;
		this.noBtn = noBtn;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//yes button was pressed
		//show answer / question
		//mark a point for the question asked
		programLoop.setButtonPressed(true);
		noBtn.setEnabled(false);
		yesBtn.setEnabled(false);
		
	}

}
