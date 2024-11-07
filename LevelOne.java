import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelOne extends JFrame {
  private JLabel questionLabel;
  private JButton[] answerButtons;
  private Question currentQuestion;
  private int currentQuestionIndex = 0;
  private ArrayList<Question> questions;
  private int correctAnswers = 0;
  private JLabel correctAnsLabel;
  private LevelSwitcher levelSwitcher;
  
  ArrayList<String> questionList = new ArrayList<String>();
  ArrayList<String> answerList = new ArrayList<String>();
  ArrayList<String> answerChoice = new ArrayList<String>();

  public LevelOne(LevelSwitcher levelSwitcher) {
    this.levelSwitcher = levelSwitcher;
    setTitle("MathsQ");
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
    correctAnsLabel = new JLabel("Correct Answer: 0", SwingConstants.CENTER);
    add(correctAnsLabel, BorderLayout.SOUTH);
    generateQuestions();
    generateNext();
    // pack();
    setVisible(true);
  }

  private void generateQuestions() {
    questions = new ArrayList<>();
    questions.add(new Question("What is equal to square root of 9?", new String[]{"-4", "2", "3", "4"}, "3"));
    questions.add(new Question("What is 10 * 20?", new String[] {"210", "200", "180", "190"}, "200"));
    questions.add(new Question("What is the range of the function y = 3x - 1?", new String[] {"y > 0", "y < 0", "y > 3", "y belong to infinite"}, "y belong to infinite"));
    questions.add(new Question("What is 23 + 32?", new String[] {"55", "66", "16", "45"}, "55"));
    questions.add(new Question("Already know that f(x) = x^2 + 3, find f'(x)", new String[] {"f'(x) = 2x", "f'(x) = x + 3", "f'(x) = 3", "f'(x) = x"}, "f'(x) = 2x"));
  }

  private void generateNext() {
    if (currentQuestionIndex < questions.size()) {
      currentQuestion = questions.get(currentQuestionIndex);
      questionLabel.setText(currentQuestion.showQuestion());
      
      String[] choices = currentQuestion.showChoices();
      for (int i = 0; i < choices.length; i++) {
        answerButtons[i].setText(currentQuestion.choices[i]);
      }
    }

    else {
      if (correctAnswers < 5) {
        JOptionPane.showMessageDialog(this, "No more questions!");
      }
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
        correctAnsLabel.setText("Correct Answers: " + correctAnswers);
      }

      else {
        JOptionPane.showMessageDialog(null, "You are wrong!");
      }

      currentQuestionIndex++;

      if (correctAnswers >= 5) {
        levelSwitcher.startTwo();
      }

      else if (currentQuestionIndex < questions.size()) {
        generateNext();
      }

      else {
        JOptionPane.showMessageDialog(LevelOne.this, "No more questions!");
      }
    }
  }

}