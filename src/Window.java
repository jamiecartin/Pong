import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {

    Graphics2D g2;

    public Window() {
        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g2 = (Graphics2D)this.getGraphics();

    }

    public void update(double dt) {
        System.out.println(1 / dt + " fps");
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    }

    public void run() {
        double lastFrameTime = 0.0;
        while (true) {
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

            try {
                Thread.sleep(30);
            } catch (Exception e) {

            }
        }
    }
}
