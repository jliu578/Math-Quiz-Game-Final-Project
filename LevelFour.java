import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelFour extends JFrame {
  private JLabel questionLabel;
  private JButton[] answerButtons;
  private Question currentQuestion;
  private int currentQuestionIndex = 0;
  private ArrayList<Question> questions;
  private int correctAnswers = 0;
  private JLabel correctAnsLabel;
  
  ArrayList<String> questionList = new ArrayList<String>();
  ArrayList<String> answerList = new ArrayList<String>();
  ArrayList<String> answerChoice = new ArrayList<String>();

  public LevelFour(LevelSwitcher levelSwitcher) {
    setTitle("Maths Multiple Choice Quiz - Level 4");
    setSize(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    questionLabel = new JLabel("Question will appears here", SwingConstants.CENTER);
    add(questionLabel, BorderLayout.NORTH);
    JPanel answersPanel = new JPanel(new GridLayout(2, 2));
    answerButtons = new JButton[4];
    

    for (int a = 0; a < 4; a++) {
      answerButtons[a] = new JButton();
      answerButtons[a].addActionListener(new AnswerButtonListener());

      answersPanel.add(answerButtons[a]); 
    }

    add(answersPanel, BorderLayout.CENTER);
    currentQuestionIndex = 0;
    correctAnswers = 0;
    correctAnsLabel = new JLabel("Correct Answer: 0", SwingConstants.CENTER);
    add(correctAnsLabel, BorderLayout.SOUTH);
    generateQuestions();
    generateNext();
    // pack();
    setVisible(true);
  }

  private void generateQuestions() {
    questions = new ArrayList<>();
    questions.add(new Question("g(x) = (1 - sec x)/x, find g'(0).", new String[]{"0", "DNE", "1", "-1"}, "0"));
    questions.add(new Question("h(x) = (1 - cos x)/x, find h'(0).", new String[] {"DNE", "0", "2", "-1"}, "0"));
    questions.add(new Question("f(x) = (sin x - x)/x^2, find f'(x).", new String[] {"1", "4", "2", "0"}, "0"));
  }

  private void generateNext() {
    if (currentQuestionIndex < questions.size()) {
      currentQuestion = questions.get(currentQuestionIndex);
      questionLabel.setText(currentQuestion.showQuestion());
      for (int i = 0; i < 4; i++) {
        answerButtons[i].setText(currentQuestion.showCorrectAns());
      }
    }

    else {
      JOptionPane.showMessageDialog(this, "Game Over!");
      System.exit(0);
    }
  }

  private class AnswerButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      JButton b1 = (JButton) e.getSource();

      if (b1.getText().equals(currentQuestion.showCorrectAns())) {
        correctAnswers += 1;
        JOptionPane.showMessageDialog(null, "You are correct!");
      }

      else {
        JOptionPane.showMessageDialog(null, "You are wrong!");
      }

      currentQuestionIndex++;

      if (correctAnswers >= 5) {
        System.out.println("You won!!!!!!");
      }

      else if (currentQuestionIndex < questions.size()) {
        generateNext();
      }

      else {
        JOptionPane.showMessageDialog(null, "No more questions!");
      }
    }
  }
}