import java.awt.Color;

public class GraphicsFun {
   public static void main(String[] args) {
      final int NUM_CIRCLES = 200;
      int count = 0;
      while (count < NUM_CIRCLES) {
         double x = Math.random();
         double y = Math.random();
         double radius = Math.random() * .1 + 0.02; // 0.02 - 0.12
         int r = (int) (Math.random() * 256);
         int g = (int) (Math.random() * 256);
         int b = (int) (Math.random() * 256);
         int alpha = 175;
         Color color = new Color(r, g, b, alpha);
         StdDraw.setPenColor(color);
         StdDraw.filledCircle(x, y, radius);
         count++;
      }
   }
}
