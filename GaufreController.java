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
    private GaufreRandomAI randomAI;
    private boolean playAgainstAI;

    public GaufreController(GaufreModel model, GaufreView view, GameOverView gameOverView, boolean playAgainstAI) {
        this.model = model;
        this.view = view;
        this.gameOverView = gameOverView;
        this.playAgainstAI = playAgainstAI;
        this.randomAI = new GaufreRandomAI(); // Instance de l'IA aléatoire

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
                model.undoMove();
                view.repaint(); // Actualiser la vue
            }
            if (e.getKeyCode() == KeyEvent.VK_H) {
                System.out.println("Historique:");
                model.printHistorique();
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                System.exit(0);
            }
            if (e.getKeyCode() == KeyEvent.VK_M) {
                view.toggleHoverMode();
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
            if (model.isGameOver()) {
                return;
            }

            int x = e.getX() / (view.getWidth() / model.getGaufre()[0].length);
            int y = e.getY() / (view.getHeight() / model.getGaufre().length);

            if (model.isValidMove(y, x)) {
                model.playMove(y, x);

                if (model.isGameOver() || model.isFull()) {
                    handleGameOver();
                } else {
                    view.repaint();

                    if (playAgainstAI && model.getCurrentPlayer() == 2) {
                        // L'IA joue automatiquement après le joueur humain
                        int[] aiMove = randomAI.getRandomMove(model);
                        if (aiMove != null) {
                            model.playMove(aiMove[0], aiMove[1]);
                            if (model.isGameOver() || model.isFull()) {
                                handleGameOver();
                            }
                            view.repaint();
                        }
                    }
                }
            }
        }

        private void handleGameOver() {
            String winner = (model.getCurrentPlayer() == 1) ? "Player 2" : "Player 1";
            gameOverView.setGameOverMessage("Game Over! " + winner + " wins!");
            JFrame frame = new JFrame("Game Over");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(gameOverView);
            frame.pack();
            frame.setAlwaysOnTop(true);
            frame.setVisible(true);
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
        boolean playAgainstAI = false;
        GaufreController controller = new GaufreController(model, view, gameOverView, playAgainstAI);

        // print the instructions
        System.out.println("Instructions:");
        System.out.println("1. Click on a cell to eat it.");
        System.out.println("2. Press 'M' to toggle hover mode.");
        System.out.println("3. Press 'R' to undo the last move.");
        System.out.println("4. Press 'H' to print the game history.");
        System.out.println("5. Press 'Q' to quit the game.");

        JFrame frame = new JFrame("Gaufre Empoisonnée");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(view);
        frame.pack();
        frame.setVisible(true);
    }
}
