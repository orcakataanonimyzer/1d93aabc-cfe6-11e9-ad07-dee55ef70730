import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class NextButtonListener implements ActionListener {
	private RunLoop programLoop;
	
	public NextButtonListener(RunLoop programLoop){
		this.programLoop = programLoop;

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		programLoop.nextButton();
		
	}

}
