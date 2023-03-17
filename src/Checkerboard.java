import java.awt.*;

public class Checkerboard {
   public static void main(String[] args) {
      final int SIZE = 8;
      StdDraw.setScale(0, SIZE);
      for (int r = 0; r < SIZE; r++) {
         for (int c = 0; c < SIZE; c++) {
            double x = c + 0.5;
            double y = r + 0.5;
            // Conditional operator: (boolean)? trueVal : falseVal
            Color color = ((r+c) % 2 == 1)? Color.RED : Color.BLACK;
//            Color color = Color.BLACK;
//            if ((r+c) % 2 == 1) {
//               color = Color.RED;
//            }
            StdDraw.setPenColor(color);
            StdDraw.filledSquare(x, y, 0.5);
         }
      }
   }
}
