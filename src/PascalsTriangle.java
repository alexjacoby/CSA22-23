import java.util.Scanner;

/**
 * Pascal's Triangle (an intro to 2D arrays).
 */
public class PascalsTriangle {
   private int[][] vals;

   public PascalsTriangle(int rows) {
      vals = new int[rows][]; // Each row has a different length, so don't initialize them yet
      for (int r = 0; r < vals.length; r++) {
         vals[r] = new int[r+1];
         // Set first and last columns to 1
         vals[r][0] = 1;
         vals[r][r] = 1;
         // Set center elements by adding the 2 elements above them
         for (int c = 1; c < vals[r].length - 1; c++) {
            vals[r][c] = vals[r-1][c-1] + vals[r-1][c];
         }
      }
   }

   public String toString() {
      String res = "";
      for (int[] row : vals) {
         for (int n : row) {
            res += n + ", ";
         }
         res += "\n";
      }
      return res;
   }

   public static void main(String[] args) {
      System.out.println("How many rows?");
      Scanner scan = new Scanner(System.in);
      int rows = scan.nextInt();
      PascalsTriangle pt = new PascalsTriangle(rows);
      System.out.println("Generated triangle: \n" + pt);
   }
}
