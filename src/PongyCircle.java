import java.awt.*;

/**
 * One-player pong, inspired by Wesley Hoy.
 *
 * @author A. Jacoby (Nov 2022)
 */
public class PongyCircle {

   public static void main(String[] args) {
      StdDraw.enableDoubleBuffering();
      StdDraw.setTitle("Bouncy Circle");
      StdDraw.text(0.5, 0.5, "Click to begin!");
      StdDraw.show();
      while (true) {
         // Wait for click before starting
         boolean clicked = false;
         while (!clicked) {
            clicked = StdDraw.isMousePressed();
            StdDraw.pause(100);
         }
         int score = 0;
         double xCtr = 0.5;
         double yCtr = 0.5;
         double radius = 0.03;
         double xVel = -0.005 + Math.random() * 0.01; // -0.005 - 0.005
         double yVel = 0.005 + Math.random() * 0.01; // 0.005 - 0.015
         final double G = -0.00005;
         final double PADDLE_Y_CTR = 0.05;
         final double PADDLE_HALF_WIDTH = 0.08;
         final double PADDLE_HALF_HEIGHT = 0.02;
         final double PADDLE_PADDING = 1.04;
         Color circleColor = new Color(30, 180, 180, 150);
         Color paddleColor = new Color(180, 30, 180, 150);
         // partly transparent background should lead to gradual fading effect
         Color backgroundColor = new Color(0, 0, 0, 30);
         boolean alive = true;
         while (alive) {
            // Move
            xCtr += xVel;
            yCtr += yVel;
            // Bounce if necessary
            boolean bounced = false;
            // Check right or left wall
            if ((xCtr + radius >= 1) || (xCtr - radius <= 0)) {
               xVel = -xVel;
               bounced = true;
            }
            // Check top wall
            if ((yCtr + radius >= 1)) {
               yVel = -yVel;
               bounced = true;
            }
            // Check paddle
            double paddleXCtr = Math.max(PADDLE_HALF_WIDTH, StdDraw.mouseX());
            paddleXCtr = Math.min(paddleXCtr, 1 - PADDLE_HALF_WIDTH);
            if (yCtr - radius < PADDLE_Y_CTR + PADDLE_HALF_HEIGHT) {
               if (Math.abs(paddleXCtr - xCtr) < (PADDLE_HALF_WIDTH + radius)) {
                  yVel = -yVel;
                  bounced = true;
                  score++;
               } else {
                  alive = false;
               }
            }
            // Apply gravity if we didn't bounce
            if (!bounced) {
               yVel += G;
            }
            StdDraw.clear(backgroundColor);
            StdDraw.setPenColor(circleColor);
            StdDraw.filledCircle(xCtr, yCtr, radius);
            StdDraw.setPenColor(paddleColor);
            StdDraw.filledRectangle(paddleXCtr, PADDLE_Y_CTR, PADDLE_HALF_WIDTH, PADDLE_HALF_HEIGHT);
            StdDraw.textRight(0.98, 0.95, "Score: " + score);
            StdDraw.show();
            StdDraw.pause(1000 / 120); // Approx 120 fps
         } // end main game loop
         StdDraw.clear(backgroundColor);
         StdDraw.text(0.5, 0.5, "Game Over!");
         StdDraw.text(0.5, 0.1, "Click to restart!");
         StdDraw.show();
      } // end infinite loop
   } // end main
}
