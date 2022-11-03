import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

    public class MainMenu extends JFrame implements Runnable {

        public Graphics2D g2;
        public KL keyListener = new KL();
        public Text startGame, exitGame, pong;


        public MainMenu() {
            this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
            this.setTitle(Constants.SCREEN_TITLE);
            this.setResizable(false);
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.addKeyListener(keyListener);
            this.startGame = new Text("Start Game", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH / 2.0 - 140.0, Constants.SCREEN_HEIGHT /2);
            this.exitGame = new Text("Exit", new Font("Times New Roman", Font.PLAIN, 40), Constants.SCREEN_WIDTH / 2.0 - 80, Constants.SCREEN_HEIGHT / 2 + 60);
            this.pong = new Text("Pong", new Font("Times New Roman", Font.PLAIN, 100), Constants.SCREEN_WIDTH / 2 - 155.0, 200);
            g2 = (Graphics2D)getGraphics();
        }


        public void update(double dt) {
            Image dbImage = createImage(getWidth(), getHeight());
            Graphics dbg = dbImage.getGraphics();
            this.draw(dbg);
            g2.drawImage(dbImage, 0, 0, this);
        }

        public void draw(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

            startGame.draw(g2);
            exitGame.draw(g2);
            pong.draw(g2);
        }

        public void run() {
            double lastFrameTime = 0.0;
            while (true) {
                double time = Time.getTime();
                double deltaTime = time - lastFrameTime;
                lastFrameTime = time;

                update(deltaTime);
            }
        }
    }
