public class RecursionFun {
   /**
    * Returns the nth fibbonacci number.
    * @param n positive int
    */
   public static int fib(int n) {
      // Base case
      if (n <= 2) {
         return 1;
      }
      // Recursive step
      return fib(n-1) + fib(n-2);
   }

   /**
    * Draws a Koch Snowflake side of given length, recurring
    * depth more times.
    */
   private static void kochSide(Turtle t, double len, int depth) {
      // Base case: depth == 0
      if (depth == 0) {
         t.crawl(len);
      } else {
         // Recursive step
         kochSide(t, len/3, depth-1);
         t.turn(-60);
         kochSide(t, len/3, depth-1);
         t.turn(120);
         kochSide(t, len/3, depth-1);
         t.turn(-60);
         kochSide(t, len/3, depth-1);
      }
   }

   /**
    * Draws Koch Snowflake of given size and recursive depth centered in a new window.
    */
   public static void kochSnowflake(double len, int depth) {
      Draw win = new Draw();
      win.enableDoubleBuffering();
      win.setTitle("Koch Snowflake");
      Turtle t = new Turtle(win);
      t.teleport(0.15, 0.3);
      t.turn(-90); // Face right
      kochSnowflake(t, len, depth);
      win.show();
   }

   /**
    * Draws Koch Snowflake of given size and recursive depth with given turtle.
    * Snowflake drawn from turtle's current position and direction.
    */
   public static void kochSnowflake(Turtle t,
                                    double len, int depth)
   {
      kochSide(t, len, depth);
      t.turn(120);
      kochSide(t, len, depth);
      t.turn(120);
      kochSide(t, len, depth);
      t.turn(120);
   }

   public static void kochSuperSnowflake(Turtle t, double len, int depth) {
      final int NUM_FLAKES = 13;
      final double ANGLE = 360.0 / NUM_FLAKES;
      for (int i = 0; i < NUM_FLAKES; i++) {
         t.setColor(Draw.randomColor(150));
         kochSnowflake(t, len, depth);
         t.turn(ANGLE);
      }
   }

   public static void main(String[] args) {
      Draw win = new Draw();
      win.enableDoubleBuffering();
      win.setTitle("Koch Snowflake");
      Turtle t = new Turtle(win);
      // Good for single large fractal:
      // t.teleport(0.15, 0.3);
      // t.turn(-90); // Face right
      // kochSnowflake(t, 0.7, 6);
      kochSuperSnowflake(t, 0.4, 5);
      win.show();
      while (true) {
         if (win.isMousePressed()) {
            t.teleport(win.mouseX(), win.mouseY());
            t.setColor(Draw.randomColor(200));
            kochSnowflake(t, 0.7, 6);
            win.show();
            win.pause(500); // Pause half second for mouse to release
         }
      }
      /*
      System.out.println("Fibonnacci numbers:");
      for (int i = 1; i <= 10; i++) {
         System.out.println(i + ": " + fib(i));
      }
     */
   }
}
