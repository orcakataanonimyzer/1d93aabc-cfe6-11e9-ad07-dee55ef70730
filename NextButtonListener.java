import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class NextButtonListener implements ActionListener {
	private RunLoop programLoop;
	//private boolean showAnswerBtn;
	//private JButton yesBtn;
	//private JButton noBtn;
	//private JButton nextButton;
	
	public NextButtonListener(RunLoop programLoop, JButton yesBtn, JButton noBtn, JButton nextBtn){
		this.programLoop = programLoop;
		//this.yesBtn = yesBtn;
		//this.noBtn = noBtn;
		//this.nextButton = nextBtn;
		//showAnswerBtn = false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//when the button shows next a click runs the next and resets the yes, no buttons, switches the Next button into a show button
		programLoop.nextButton();
		
	}

}
