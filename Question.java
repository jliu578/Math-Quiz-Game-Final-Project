public class Question {
  private String question;
  protected String[] choices;
  private String correctAnswer;

  public Question(String question, String[] choices, String correctAnswer) {
    this.question = question;
    this.choices = choices;
    this.correctAnswer = correctAnswer;
  } 

  public String showQuestion() {
    return question;
  }

  public String[] showChoices() {
    return choices;
  }

  public String showCorrectAns() {
    return correctAnswer;
  }
}