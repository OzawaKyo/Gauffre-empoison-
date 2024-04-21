import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GaufreController {
    private GaufreModel model;
    private GaufreView view;
    private GameOverView gameOverView;
    private GaufreHistorique historique;

    public GaufreController(GaufreModel model, GaufreView view, GameOverView gameOverView,
            GaufreHistorique historique) {
        this.model = model;
        this.view = view;
        this.gameOverView = gameOverView;
        this.historique = historique;

        view.setFocusable(true); // Permettre à la vue de recevoir le focus
        view.requestFocusInWindow(); // Demander le focus pour la vue

        view.addMouseListener(new ClickListener());
        view.addMouseMotionListener(new HoverListener());
        view.addKeyListener(new UndoKeyListener());
    }

    class UndoKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_R) {
                System.out.println("Undoing the last move");
                model.undoMove(historique.annulerCoup());
                view.repaint(); // Actualiser la vue
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    class HoverListener implements MouseMotionListener {
        private int lastX = -1;
        private int lastY = -1;

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            // Récupérer les coordonnées de la souris
            int x = e.getX() / (view.getWidth() / model.getGaufre()[0].length);
            int y = e.getY() / (view.getHeight() / model.getGaufre().length);

            // Unhover the last cell
            if (lastX != -1 && lastY != -1) {
                model.unhoverMove(lastY, lastX);
            }

            // Mettre à jour la vue pour afficher la case survolée
            if (model.isValidMove(y, x)) {
                model.hoverMove(y, x);
                view.repaint(); // Actualiser la vue

                // Update the last cell
                lastX = x;
                lastY = y;
            } else {
                // Reset the last cell if the current cell is not valid
                lastX = -1;
                lastY = -1;
            }
        }
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
                historique.ajouterCoup(y, x);
                if (model.isGameOver() || model.isFull()) {
                    String winner = (model.getCurrentPlayer() == 1) ? "Player 2" : "Player 1";
                    gameOverView.setGameOverMessage("Game Over! " + winner + " wins!");
                    JFrame frame = new JFrame("Game Over");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.getContentPane().add(gameOverView);
                    frame.pack();
                    frame.setAlwaysOnTop(true); // Définir le niveau de la fenêtre de game over au-dessus de la fenêtre
                                                // principale
                    frame.setVisible(true);
                }
                view.repaint(); // Actualiser la vue
            }
        }

        // peut etre utiliser pour visualiser les coups
        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public static void main(String[] args) {
        GaufreModel model = new GaufreModel(6, 8);
        GaufreView view = new GaufreView(model);
        GameOverView gameOverView = new GameOverView();
        GaufreHistorique historique = new GaufreHistorique();
        GaufreController controller = new GaufreController(model, view, gameOverView, historique);

        JFrame frame = new JFrame("Gaufre Empoisonnée");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(view);
        frame.pack();
        frame.setVisible(true);
    }
}
