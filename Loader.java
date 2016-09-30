import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader {
	private String path;
	
	
	public Loader(String path){
		this.path = path;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public ArrayList<FlashCard> getFlashCards(){
		ArrayList<FlashCard> flashCards = new ArrayList<FlashCard>();
		ArrayList<String> loadedList = loadFile();
		
		for(int i = 0; i < loadedList.size(); i+=3){
			flashCards.add(makeFlashCard(loadedList.get(i), loadedList.get(i+1), Integer.parseInt(loadedList.get(i+2).trim())));
		}
		
		System.out.println(flashCards.size()+" flashcards created.");
		
		return flashCards;
	}
	
	private ArrayList<String> loadFile(){
		ArrayList<String> loadedList = new ArrayList<String>();
		System.out.println("path is: "+path);
		
		if(path != null){
			File loadFile = new File(path);
			Scanner reader;


			try {
				reader = new Scanner(loadFile);

				while (reader.hasNextLine()) {
					loadedList.add(reader.nextLine());
				}

				reader.close();

			} catch (Exception e) {
				//create the loadfile
				System.out.println("failed to load file, list size: "+loadedList.size());
			}
		}

        return loadedList;
	}
	
	private FlashCard makeFlashCard(String question, String answer, int count){
		FlashCard flashCard = new FlashCard();
		flashCard.setAnswer(answer);
		flashCard.setQuestion(question);
		flashCard.setCount(count);
		return flashCard;
	}
}
