import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final GameEngine engine;
    private final GameRenderer renderer;

    public GamePanel() {
        this.engine = new GameEngine();
        this.renderer = new GameRenderer();

        Maze maze = engine.getMaze();
        setPreferredSize(new Dimension(
                maze.getWidth() * Constants.CELL_SIZE,
                maze.getHeight() * Constants.CELL_SIZE + 40));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        Timer timer = new Timer(Constants.GAME_SPEED, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g, engine);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        engine.update();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
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

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
