import javax.swing.*;

public class LevelSwitcher {
    private LevelOne one;
    private LevelTwo two;
    private LevelThree three;
    private LevelFour four;

    public void start() {
        one = new LevelOne(this);
        one.setVisible(true);
    }

    public void startTwo() {
        JOptionPane.showMessageDialog(null, "Congratulations! You've unlocked level 2!");
        one.setVisible(false);
        two = new LevelTwo(this);
        two.setVisible(true);
    }

    public void startThree() {
        JOptionPane.showMessageDialog(null, "Congratulations! You've unlocked level 3!");
        two.setVisible(false);
        three = new LevelThree(this);
        three.setVisible(true);
    }


    public void startFour() {
        JOptionPane.showMessageDialog(null, "Congratulations! You've unlocked level 4!");
        three.setVisible(false);
        four = new LevelFour(this);
        four.setVisible(true);
    }
}