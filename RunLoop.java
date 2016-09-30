import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class RunLoop {
	String path;
	Random random;
	Loader fileLoader;
	int draw;
	int count;
	int cumulativeScore;
	ArrayList<FlashCard> flashCards;
	boolean buttonPressed;
	boolean pathSet;
	Saver saver;
	JTextArea answerText;
	JTextArea questionText;
	JLabel scoreLabel;
	JLabel filePath;
	JButton yesBtn;
	JButton noBtn;
	JButton nextButton;
	boolean showAnswerBtn;
	boolean fileSet;
	
	public RunLoop(String path, JLabel scoreLabel, JLabel filePath, JButton yesBtn, JButton noBtn, JButton nextButton){
		this.path = path; //pointless for now but if I want to make this program save settings use this.
		fileLoader = new Loader(path);
		System.out.println("scoreLabel set");
		this.scoreLabel = scoreLabel;
		this.filePath = filePath;
		this.yesBtn = yesBtn;
		this.noBtn = noBtn;
		this.nextButton = nextButton;
		random = new Random();
		count = 0;
		saver = new Saver(path);

		filePath.setText(path);
	}
	
	
	public void initialLoad(String path){
		if(path.length() > 0){
			this.path = path;
			fileLoader.setPath(path);
			loadFlashCards();
			setCumulativeScore();
			yesBtn.setEnabled(true);
			noBtn.setEnabled(true);
			nextButton.setEnabled(true);
			filePath.setText(path);
			saver.setPath(path);
		}else{
			notifyForFile();
		}
	}
	
	private void loadFlashCards(){
		flashCards = fileLoader.getFlashCards();
	}
	
	public void nextButton(){
		if(!fileSet){
			notifyForFile();
			return;
		}

		if(!showAnswerBtn){
			runNext();
			yesBtn.setEnabled(true);
			noBtn.setEnabled(true);
			showAnswerBtn = true;
			nextButton.setText("Show Answer");
		}else{ //button is now a show answer button
			//make programLoop show the answer, switch back to a "next" button
			setBtnToNext();
			//nextButton.setEnabled(false);
		}
	}
	
	private void setBtnToNext(){
		System.out.println("show answer");
		printQandA();
		showAnswerBtn = false;
		nextButton.setText("Next");
	}
	
	
	private void setCumulativeScore(){
		cumulativeScore = 0;
		for(FlashCard card: flashCards){
			cumulativeScore += card.getCount();
		}
		System.out.println("score is: "+cumulativeScore);
	}
	
	public void attachTextAreas(JTextArea aText, JTextArea qText){
		answerText = aText;
		questionText = qText;
	}
	
	public void runNext(){
		System.out.println("run called");
		if(flashCards != null && flashCards.size() > 0){
			setScoreLabel();
			buttonPressed = false;
			Collections.sort(flashCards);
			clearTextboxes();

			draw = random.nextInt(random.nextInt(flashCards.size())+1); //random in random for weighted random
			questionOrAnswer(random.nextInt(2));
		}else{
			notifyForFile();
		}

	}
	
	private void notifyForFile(){
		questionText.setText("create or set a flashcards file following the format:");
		answerText.setText(" Question line \n AnswerLine \n 0");
	}
	
	private void setScoreLabel(){
		if(cumulativeScore > 0){
			scoreLabel.setForeground(Color.green);
		}else if(cumulativeScore < 0){
			scoreLabel.setForeground(Color.red);
		}else{
			scoreLabel.setForeground(Color.black);
		}
		scoreLabel.setText(cumulativeScore+"");
	}
	
	private void clearTextboxes(){
		answerText.setText("");
		questionText.setText("");
	}
	
	private void saveFile(){
		if(count > 4){
			saver.saveFile(flashCards); //add a tally to this to not save so often
			count = 0;
		}
		count += 1;
	}
	
	public void setButtonPressed(boolean yesOrNo){
		System.out.println("button pressed");
		
		if(!fileSet){
			notifyForFile();
			return;
		}
		
		if(!buttonPressed){
			buttonPressed = true;
			
			if(yesOrNo){
				flashCards.get(draw).answer(true);
				cumulativeScore++;
				
				printQandA();
				saveFile();
				setBtnToNext();
			}else{
				flashCards.get(draw).answer(false);
				cumulativeScore--;
				printQandA();
				saveFile();
				setBtnToNext();
			}
		}
	}
	
	
	
	private void questionOrAnswer(int questOrAns){
		if(questOrAns == 0){
			System.out.println(flashCards.get(draw).getQuestion());
			
			//place into question text box
			questionText.setText(flashCards.get(draw).getQuestion());
			questionText.setBackground(Color.LIGHT_GRAY);
			
			answerText.setBackground(Color.white);
			//System.out.println("What is the answer?");
		}else{
			System.out.println(flashCards.get(draw).getAnswer());
			//place into answer text box
			answerText.setText(flashCards.get(draw).getAnswer());
			answerText.setBackground(Color.lightGray);
			
			questionText.setBackground(Color.white);
			//System.out.println("What is the question?");
		}
	}
	
	
	public void printQandA(){
		System.out.println(flashCards.get(draw).getQuestion());
		System.out.println(flashCards.get(draw).getAnswer());
		
		
		questionText.setText(flashCards.get(draw).getQuestion());
		answerText.setText(flashCards.get(draw).getAnswer());
	}
}
