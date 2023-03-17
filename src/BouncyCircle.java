import java.awt.Color;

public class BouncyCircle {

   public static void main(String[] args) {
      // Initializing
      double xCtr = 0.5;
      double yCtr = 0.5;
      double radius = 0.03;
      double xVel = -0.005 + Math.random() * 0.01; // -0.005 - 0.005
      double yVel = -0.005 + Math.random() * 0.01; // -0.005 - 0.005
      final double G = -0.00005;
      Color color = StdDraw.randomColor(10);
      StdDraw.setPenColor(color);
      StdDraw.enableDoubleBuffering();
      while (true) {
         // StdDraw.clear();
         StdDraw.filledCircle(xCtr, yCtr, radius);
         StdDraw.show();
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
         // Check top or bottom wall
         if ((yCtr + radius >= 1) || (yCtr - radius <= 0)) {
            yVel = -yVel;
            bounced = true;
         }
         // Apply gravity if we didn't bounce
         if (!bounced) {
            yVel += G;
         }
         StdDraw.pause(1000 / 120); // Approx 120 fps
      }
   }
}
