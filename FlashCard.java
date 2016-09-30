
public class FlashCard implements Comparable<FlashCard>{
	private String question;
	private String answer;
	private int correctAnswers;
	
	public FlashCard(){
		
	}
	
	public void setQuestion(String question){
		this.question = question;
	}
	
	public void setAnswer(String answer){
		this.answer = answer;
	}
	
	public void answer(boolean answer){
		if(answer){
			correctAnswers++;
		}else{
			correctAnswers--;
		}
	}
	
	public void setCount(int count){
		this.correctAnswers = count;
	}
	
	public int getCount(){
		return correctAnswers;
	}
	
	public String getQuestion(){
		return question;
	}
	
	public String getAnswer(){
		return answer;
	}

	@Override
	public int compareTo(FlashCard arg0) {
		return this.getCount() - arg0.getCount();
	}
	
}
