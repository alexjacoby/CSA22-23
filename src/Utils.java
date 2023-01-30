import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Collection of miscellaneous utility methods.
 *
 * @author AP CS A (Fall-Spring 2022-23)
 */
public class Utils {
   /** Swaps elements at idx1 and idx2. */
   public static void swap(Object[] vals, int idx1, int idx2) {
      Object tmp = vals[idx1];
      vals[idx1] = vals[idx2];
      vals[idx2] = tmp;
   }

   /** Swaps elements at idx1 and idx2. */
   public static void swap(ArrayList vals, int idx1, int idx2) {
      Object tmp = vals.get(idx1);
      vals.set(idx1, vals.get(idx2));
      vals.set(idx2, tmp);
   }

   /** Swaps elements at idx1 and idx2. */
   public static void swap(int[] vals, int idx1, int idx2) {
      int tmp = vals[idx1];
      vals[idx1] = vals[idx2];
      vals[idx2] = tmp;
   }

   /**
    * Prompts user for an int in the given range from
    * min to max, inclusive.
    * <p>
    * <pre>min <= max</pre>
    */
   public static int promptInt(String prompt, int min, int max) {
      // Check precondition that min <= max!
      if (min > max) {
         throw new IllegalArgumentException("min cannot be greater than max!: " +
               "min=" + min + ", max=" + max);
      }
      Scanner in = new Scanner(System.in);
      while (true) {
         System.out.println(prompt + " [" + min + "-" + max + "]");
         // Try this block of code - if there's a problem with the
         // input, go to the catch block to handle it!
         try {
            int val = in.nextInt();
            if (val >= min && val <= max) {
               return val;
            }
            System.out.println("Value outside range!");
         } catch (InputMismatchException e) {
            System.out.println("Please enter an integer!");
            // e.printStackTrace();
            in.next(); // Consume and ignore the offending input!
         }
      }
   }

   public static void main(String[] args) {
      String[] names = {"Alpha", "Beta", "Gamma"};
      System.out.println("Original: " + Arrays.toString(names));
      swap(names, 1, 2);
      System.out.println("Swapped: " + Arrays.toString(names));
      ArrayList<String> names2 = new ArrayList<>();
      names2.add("Alpha");
      names2.add("Beta");
      names2.add("Gamma");
      System.out.println("Original: " + names2);
      swap(names2, 1, 2);
      System.out.println("Swapped: " + names2);
   }
}
