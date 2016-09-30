import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JFileChooser;

public class SetFileListener implements ActionListener {
	//Menu setFile;
	//String filePath;
	final JFileChooser fileChooser;
	RunLoop programLoop;
	//Loader fileLoader;
	
	public SetFileListener(RunLoop programLoop){
		//this.setFile = setFile;
		System.out.println("setFileListener attached");
		fileChooser = new JFileChooser();
		this.programLoop = programLoop;
		//this.fileLoader = fileLoader;
		
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("clicked");
        fileChooser.setDialogTitle("Choose FlashCards study file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("Chooser: " + fileChooser.getSelectedFile().toString());
            //fileLoader.setPath(fileChooser.getSelectedFile().toString());
            programLoop.initialLoad(fileChooser.getSelectedFile().toString());
            programLoop.fileSet = true;
        } else {
            //System.out.println("no selection");
        }
	}

}	
