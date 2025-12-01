import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final GameEngine engine;
    private final GameRenderer renderer;
    private final javax.swing.Timer timer;

    public GamePanel() {
        this.engine = new GameEngine();
        this.renderer = new GameRenderer();

        Maze maze = engine.getMaze();
        setPreferredSize(new java.awt.Dimension(
                maze.getWidth() * Constants.CELL_SIZE,
                maze.getHeight() * Constants.CELL_SIZE + 40));
        setBackground(java.awt.Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        timer = new javax.swing.Timer(Constants.GAME_SPEED, this);
        timer.start();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        renderer.render(g, engine);
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        engine.update();
        repaint();
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        int keyCode = e.getKeyCode();
        Direction dir = Direction.fromKeyCode(keyCode);

        if (dir != null) {
            engine.getPacMan().setNextDirection(dir);
        } else if (keyCode == 82) {
            if (engine.getState().isGameOver() || engine.getState().isWon()) {
                engine.reset();
            }
        }
    }

    @Override public void keyTyped(java.awt.event.KeyEvent e) {}
    @Override public void keyReleased(java.awt.event.KeyEvent e) {}
}
