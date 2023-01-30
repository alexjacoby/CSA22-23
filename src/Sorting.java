import java.util.Arrays;

/**
 * Sorting-related methods for AP CS A (2022-23).
 */
public class Sorting {
   /** Returns true if vals is sorted in increasing order. */
   public static boolean isSorted(int[] vals) {
      for (int i = 0; i < vals.length - 1; i++) {
         if (vals[i] > vals[i+1]) {
            return false;
         }
      }
      return true;
   }

   /** Randomizes order of vals. */
   public static void shuffle(int[] vals) {
      for (int i = 0; i < vals.length; i++) {
         int rand = (int) (Math.random() * vals.length);
         // TODO: Pick rand >= i
         Utils.swap(vals, i, rand);
      }
   }

   /** Sorts vals... eventually. */
   public static void bogoSort(int[] vals) {
      while (!isSorted(vals)) { shuffle(vals); }
   }

   /** Sorts vals using Insertion Sort algorithm. */
   public static void insertionSort(int[] vals) {
      for (int i = 1; i < vals.length; i++) {
         int val = vals[i];
         int j = i - 1;
         // Shift items right while val is smaller than them
         while (j >= 0 &&  val < vals[j]) {
            vals[j+1] = vals[j];
            j--;
         }
         vals[j+1] = val;
      }
   }

   /**
    * Generates numVals random ints between min and max
    * inclusive.
    */
   public static int[] randInts(int numVals, int min, int max) {
      int[] vals = new int[numVals];
      int range = max - min + 1;
      for (int i = 0; i < vals.length; i++) {
         vals[i] = (int) (min + Math.random() * range);
      }
      return vals;
   }

   public static void main(String[] args) {
      int[] vals = randInts(100_000, 0, 10_000);
      // System.out.println("Unsorted: " + Arrays.toString(vals));
      long startTimeMs = System.currentTimeMillis();
      insertionSort(vals);
      long elapsed = System.currentTimeMillis() - startTimeMs;
      // System.out.println("Sorted: " + Arrays.toString(vals));
      System.out.println("Elapsed: " + elapsed + "ms");
      if (!isSorted(vals)) {
         System.out.println("Error! Array not sorted!");
         System.out.println(Arrays.toString(vals));
      }
   }
}
