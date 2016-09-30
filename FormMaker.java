import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FormMaker implements Runnable {
	private JFrame frame;
	String path;
	JLabel scoreLabel;
	JLabel filePath;
	RunLoop programLoop;
	int yNudge = 0;
	int plusWidth = 120;
	JButton yesButton;
	JButton noButton;
	JButton nextButton;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		frame = new JFrame("FlashCards");
		initFrameParts();
		
		System.out.println("scoreLabel created");
		programLoop = new RunLoop(path, scoreLabel, filePath, yesButton, noButton, nextButton);

		frame.setPreferredSize(new Dimension(350+plusWidth,420+yNudge));
		
		createComponents(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);

	}
	
	private void initFrameParts(){
		scoreLabel = new JLabel("0");
		filePath = new JLabel("path");
		yesButton = new JButton("Know it");
		noButton = new JButton("Don't know");
		nextButton = new JButton("next");
	}
	
	public void setFilePath(String path){
		this.path = path;
	}
	
	private void createComponents(Container container){
		//GridLayout layout = new GridLayout(3,1);
		container.setLayout(null);
		
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("File");
		MenuItem setFile = new MenuItem("Set File");
		
		file.add(setFile);
		
		setFile.addActionListener(new SetFileListener(programLoop));
		
		menuBar.add(file);
		
		
		JTextArea jTextQuestion = new JTextArea();
		//jTextQuestion.setPreferredSize(new Dimension(300,300));
		JLabel questionLabel = new JLabel("Question");
		questionLabel.setBounds(10,-40+yNudge,100,100);
		
		//scoreLabel = new JLabel("0");
		scoreLabel.setBounds(415, -40+yNudge, 100,100);
		
		JLabel scoreTag = new JLabel("Rating:");
		scoreTag.setBounds(350, -40+yNudge, 100,100);
		
		jTextQuestion.setBounds(10,20+yNudge, 310+plusWidth,100);
		jTextQuestion.setLineWrap(true);
		jTextQuestion.setWrapStyleWord(true);
		jTextQuestion.setEditable(false);
		jTextQuestion.setFont(new Font("Ariel", Font.PLAIN, 18));
		
		JLabel answerLabel = new JLabel("Answer");
		answerLabel.setBounds(10,80+yNudge,100,100);
		
		
		JTextArea jTextAnswer = new JTextArea();
		jTextAnswer.setBounds(10,140+yNudge, 310+plusWidth,100);
		jTextAnswer.setLineWrap(true);
		jTextAnswer.setWrapStyleWord(true);
		jTextAnswer.setEditable(false);
		jTextAnswer.setFont(new Font("Ariel", Font.PLAIN, 18));
		//jTextAnswer.setText("answer test text");
		
		programLoop.attachTextAreas(jTextAnswer, jTextQuestion);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,3));
		buttonPanel.setBounds(10,245+yNudge,310+plusWidth,80);
		
	
		yesButton.addActionListener(new YesButtonListener(programLoop, yesButton, noButton));
		noButton.addActionListener(new NoButtonListener(programLoop, yesButton, noButton));
		nextButton.addActionListener(new NextButtonListener(programLoop, yesButton, noButton, nextButton));

		
		
		
		buttonPanel.add(noButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(yesButton);
		
		
		JLabel fileLbl = new JLabel("file:");
		fileLbl.setBounds(10,295, 100,100);
		

		filePath.setBounds(35,295, 400,100);
		filePath.setFont(new Font("Ariel",Font.PLAIN, 10));
		
		
		frame.setMenuBar(menuBar);
		container.add(questionLabel);
		container.add(scoreTag);
		container.add(scoreLabel);
		container.add(jTextQuestion);
		container.add(answerLabel);
		container.add(jTextAnswer);
		container.add(buttonPanel);
		container.add(fileLbl);
		container.add(filePath);
		
		
	}
	
}
