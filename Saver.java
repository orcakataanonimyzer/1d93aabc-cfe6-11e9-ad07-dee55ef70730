import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Saver {
	String path;
	
	public Saver(String path){
		this.path = path;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public void saveFile(ArrayList<FlashCard> flashCards){
        BufferedWriter writer;
        try {

            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));

            for (int i = 0; i < flashCards.size(); i++) {
                writer.write(flashCards.get(i).getQuestion() + "\r\n");
                writer.write(flashCards.get(i).getAnswer()+ "\r\n");
                writer.write(flashCards.get(i).getCount()+"\r\n");
            }
            System.out.println("save finished");
            writer.close();
        } catch (Exception e) {
            System.out.println("save failed");
        }
	}
}
