import java.awt.*;

/**
 * A robot turtle that can draw to the screen.
 *
 * @author AP CSA (Dec 2022)
 */
public class Turtle {
   // Static fields
   private static int population = 0;
   public static final double UP = 90;
   public static final double DOWN = 270;
   public static final double LEFT = 180;
   public static final double RIGHT = 0;

   // Instance Fields
   private double xCtr = 0.5; // default to middle of screen
   private double yCtr = 0.5; // default to middle of screen
   /**
    * Direction in degrees in standard position:
    * 0 is right, 90 is up, etc.
    * Value in [0, 360).
    */
   private double dir = UP;
   /** Color of turtle's pen. */
   private Color color = Color.GREEN;
   /** Should turtle draw line while moving? */
   private boolean isPenDown = true;
   /** Window to draw to. */
   private final Draw win;

   // Constructors

   /**
    * Creates a new Turtle at the center, pointing up, drawing in green.
    */
   public Turtle(Draw win) {
      this.win = win;
      population++;
      System.out.println("Turtle born! New pop: " + population);
   }

   // TODO: Add a more full-featured constructor

   // Methods
   public void setPenDown(boolean isPenDown) {
      this.isPenDown = isPenDown;
   }

   public void setColor(Color color) { this.color = color; }

   /**
    * Turns the turtle given number of degrees
    * counterclockwise.
    */
   public void turn(double turnDir) {
      dir += turnDir;
      dir %= 360;
      if (dir < 0) { dir += 360; }
      // System.out.println("New dir: " + dir);
   }

   /**
    * Move turtle forward given distance, drawing
    * line if pen is down.
    */
   public void crawl(double dist) {
      double rads = Math.toRadians(dir);
      double dx = dist * Math.cos(rads);
      double dy = dist * Math.sin(rads);
      double oldXCtr = xCtr;
      double oldYCtr = yCtr;
      xCtr += dx;
      yCtr += dy;
      if (isPenDown) {
         win.setPenColor(color);
         win.line(oldXCtr, oldYCtr, xCtr, yCtr);
      }
   }

   /** Moves the turtle to given coordinates without drawing. */
   public void teleport(double x, double y) {
      xCtr = x;
      yCtr = y;
   }

   public static void main(String[] args) {
      Draw window = new Draw();
      window.setTitle("I like Turtles!");
      Turtle bob = new Turtle(window);
      for (int i = 0; i < 720; i++) {
         bob.crawl(0.001);
         bob.turn(0.5);
      }
      Turtle alice = new Turtle(window);
      for (int i = 0; i < 720; i++) {
         alice.crawl(0.001);
         alice.turn(Math.random() * 20 - 10);
      }
   }
}
