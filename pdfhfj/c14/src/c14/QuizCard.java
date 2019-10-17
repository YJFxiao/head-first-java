package c14;



public class QuizCard {

    private String uniqueID;
    private String category;
    private String question;
    private String answer;
    private String hint;

	public QuizCard(String text, String text2) {
		// TODO Auto-generated constructor stub
		question=text;
		answer=text2;
	}

	public QuizCard(String uniqueID, String category, String question, String answer, String hint) {
		super();
		this.uniqueID = uniqueID;
		this.category = category;
		this.question = question;
		this.answer = answer;
		this.hint = hint;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

}
