import javax.swing.*;
import java.awt.*;

public class GameOverView extends JPanel {
    private JLabel gameOverLabel;

    public GameOverView() {
        setPreferredSize(new Dimension(400, 300));
        setBackground(Color.WHITE);

        gameOverLabel = new JLabel();
        gameOverLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gameOverLabel);
    }

    public void setGameOverMessage(String message) {
        gameOverLabel.setText(message);
    }
}
