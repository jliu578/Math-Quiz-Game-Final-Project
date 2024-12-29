import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class LevelThree extends JFrame {
    private JLabel questionLabel;
    private JButton[] answerButtons;
    private JLabel correctAnswersLabel;
    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private int correctAnswers;
    private LevelSwitcher levelSwitcher;

    public LevelThree(LevelSwitcher levelSwitcher) {
        this.levelSwitcher = levelSwitcher;
        setTitle("Maths Multiple Choice Quiz - Level 3");
        setPreferredSize(new Dimension(400, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel("Question will appear here", SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel(new GridLayout(2, 2));
        answerButtons = new JButton[4];

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton();
            answerButtons[i].addActionListener(new AnswerButtonListener());
            answersPanel.add(answerButtons[i]);
        }

        add(answersPanel, BorderLayout.CENTER);
        correctAnswersLabel = new JLabel("Correct Answers: 0", SwingConstants.CENTER);
        add(correctAnswersLabel, BorderLayout.SOUTH);
        currentQuestionIndex = 0;
        correctAnswers = 0;
        generateQuestions();
        showNextQuestion();
        pack();
        setVisible(true);
    }

    private void generateQuestions() {
        questions = new ArrayList<>();
        questions.add(new Question("Already know h(x) = x^3 + 3x^2 + 6x - 12, find h''(0). ", new String[]{"-12", "-6", "6", "4"}, "6"));
        questions.add(new Question("If cot θ = 3/4, find sec θ. ", new String[] {"3/4", "5/3", "3/5", "4/5"}, "5/3"));
        questions.add(new Question("If sin θ = 1/2, find θ. ", new String[] {"-π/6", "-5π/6", "π/3", "π/6"}, "π/6"));
    }

    private void showNextQuestion() {
        System.out.println("Current Index: " + currentQuestionIndex);
        System.out.println("Questions Size: " + questions.size());
        if (currentQuestionIndex < questions.size()) {
            Question q = questions.get(currentQuestionIndex);
            questionLabel.setText(q.showQuestion());
            String[] choices = q.showChoices();
            for (int i = 0; i < 4; i++) {
                answerButtons[i].setText(choices[i]);
            }
        } 
    }

    private class AnswerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            Question currentQuestion = questions.get(currentQuestionIndex);
            String correctAnswer = currentQuestion.showCorrectAns();
    
            if (source.getText().equals(correctAnswer)) {
                correctAnswers++;
                JOptionPane.showMessageDialog(null, "Correct!");
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect!");
            }
    
            correctAnswersLabel.setText("Correct Answers: " + correctAnswers);
            currentQuestionIndex++;
    
            if (correctAnswers >= 3) {
                JOptionPane.showMessageDialog(null, "You've advanced to the next level!");
                levelSwitcher.startThree();
                return; // Exit the current level
            }

            if (currentQuestionIndex >= questions.size()) {
                JOptionPane.showMessageDialog(null, "Quiz completed!");
                currentQuestionIndex = 0; // Reset if needed
            } else {
                showNextQuestion();
            }
        }
    }
}