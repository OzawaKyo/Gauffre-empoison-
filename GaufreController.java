import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GaufreController {
    private GaufreModel model;
    private GaufreView view;
    private GameOverView gameOverView;

    public GaufreController(GaufreModel model, GaufreView view, GameOverView gameOverView) {
        this.model = model;
        this.view = view;
        this.gameOverView = gameOverView;

        view.addMouseListener(new ClickListener());
    }

    class ClickListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Récupérer les coordonnées du clic
            int x = e.getX() / (view.getWidth() / model.getGaufre()[0].length);
            int y = e.getY() / (view.getHeight() / model.getGaufre().length);

            // Vérifier si le coup est valide et jouer le coup
            if (model.isValidMove(y, x) && !model.isGameOver()) {
                model.playMove(y, x);
                if (model.isGameOver() || model.isFull()) {
                    String winner = (model.getCurrentPlayer() == 1) ? "Player 2" : "Player 1";
                    gameOverView.setGameOverMessage("Game Over! " + winner + " wins!");
                    JFrame frame = new JFrame("Game Over");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(gameOverView);
                    frame.pack();
                    frame.setAlwaysOnTop(true); // Définir le niveau de la fenêtre de game over au-dessus de la fenêtre principale
                    frame.setVisible(true);
                }
                view.repaint(); // Actualiser la vue
            }
        }

        // peut etre utiliser pour visualiser les coups
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    public static void main(String[] args) {
        GaufreModel model = new GaufreModel(6, 8);
        GaufreView view = new GaufreView(model);
        GameOverView gameOverView = new GameOverView();
        GaufreController controller = new GaufreController(model, view, gameOverView);

        JFrame frame = new JFrame("Gaufre Empoisonnée");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(view);
        frame.pack();
        frame.setVisible(true);
    }
}
