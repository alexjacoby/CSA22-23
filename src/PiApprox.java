import java.awt.*;
import java.util.Scanner;

public class PiApprox {
   public static void main(String[] args) {
      // Providing a default value in square brackets is always
      // appreciated by more discerning users :)
      System.out.println("How many points? [10,000,000,000]");
      Scanner in = new Scanner(System.in);
      // But default values do require more careful handling to
      // convert from possibly-empty Strings to ints (or longs)!
      String response = in.nextLine();
      long totalPoints = 10_000_000_000L; // default
      if (!response.isBlank()) {
         totalPoints = Long.parseLong(response);
      }
      long innerPoints = 0;
      StdDraw.circle(0, 0, 1);
      StdDraw.enableDoubleBuffering();
      for (long pointNum = 0; pointNum < totalPoints; pointNum++) {
         double x = Math.random();
         double y = Math.random();
         Color color = Color.BLACK;
         if (x*x + y*y <= 1) {
            innerPoints++;
            color = Color.MAGENTA;
         }
         StdDraw.setPenColor(color);
         StdDraw.point(x, y);
      } // end for
      StdDraw.show();
      double qtrCirclePercent = 1.0 * innerPoints / totalPoints;
      double piApprox = 4 * qtrCirclePercent;
      System.out.println("Pi is approximately " + piApprox);
   } // end main
} // end class
