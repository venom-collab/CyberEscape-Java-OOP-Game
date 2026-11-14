import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JFrame {

    static class Player {
        String name;
        int score;

        Player(String name) {
            this.name = name;
            this.score = 0;
        }

        void addPoint() {
            score++;
        }

        void resetScore() {
            score = 0;
        }
    }

    static class Game {
        Random random = new Random();

        String getComputerChoice() {
            String[] choices = {"Rock", "Paper", "Scissors"};
            return choices[random.nextInt(3)];
        }

        String checkWinner(String player, String computer) {

            if (player.equals(computer)) {
                return "Draw!";
            }

            if ((player.equals("Rock") && computer.equals("Scissors")) ||
                (player.equals("Paper") && computer.equals("Rock")) ||
                (player.equals("Scissors") && computer.equals("Paper"))) {

                return "You Win!";
            }

            return "Computer Wins!";
        }
    }

    Player player = new Player("Player");
    Game game = new Game();

    JLabel roundLabel;
    JLabel scoreLabel;
    JLabel resultLabel;

    int round = 0;

    public Main() {

        setTitle("Rock Paper Scissors - Java OOP Game");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel("ROCK PAPER SCISSORS");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);

        roundLabel = new JLabel("Round: 0 / 5");
        roundLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        roundLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(15));
        panel.add(roundLabel);

        scoreLabel = new JLabel("Your Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(10));
        panel.add(scoreLabel);

        resultLabel = new JLabel("Choose your move!");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(25));
        panel.add(resultLabel);

        JPanel buttons = new JPanel();

        JButton rock = new JButton("Rock");
        JButton paper = new JButton("Paper");
        JButton scissors = new JButton("Scissors");

        rock.setPreferredSize(new Dimension(120, 45));
        paper.setPreferredSize(new Dimension(120, 45));
        scissors.setPreferredSize(new Dimension(120, 45));

        buttons.add(rock);
        buttons.add(paper);
        buttons.add(scissors);

        panel.add(Box.createVerticalStrut(25));
        panel.add(buttons);

        JButton restart = new JButton("Restart Game");
        restart.setPreferredSize(new Dimension(150, 40));
        restart.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(25));
        panel.add(restart);

        add(panel);

        rock.addActionListener(e -> playGame("Rock"));
        paper.addActionListener(e -> playGame("Paper"));
        scissors.addActionListener(e -> playGame("Scissors"));
        restart.addActionListener(e -> restartGame());

        setVisible(true);
    }

    void playGame(String playerChoice) {

        if (round >= 5) {
            return;
        }

        round++;

        String computerChoice = game.getComputerChoice();
        String result = game.checkWinner(playerChoice, computerChoice);

        if (result.equals("You Win!")) {
            player.addPoint();
        }

        resultLabel.setText(
            "You: " + playerChoice +
            " | Computer: " + computerChoice +
            " | " + result
        );

        roundLabel.setText("Round: " + round + " / 5");
        scoreLabel.setText("Your Score: " + player.score);

        if (round == 5) {

            String message;

            if (player.score >= 3) {
                message = "Congratulations! You Won the Game!";
            }
            else if (player.score == 2) {
                message = "Match Draw!";
            }
            else {
                message = "Computer Won the Game!";
            }

            JOptionPane.showMessageDialog(
                this,
                message + "\n\nFinal Score: " + player.score + " / 5",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    void restartGame() {

        round = 0;
        player.resetScore();

        roundLabel.setText("Round: 0 / 5");
        scoreLabel.setText("Your Score: 0");
        resultLabel.setText("Choose your move!");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
