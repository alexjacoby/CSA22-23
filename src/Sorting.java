import java.util.Arrays;
import java.util.function.Consumer;

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

   /** Sorts given array using MergeSort algorithm. */
   public static void mergeSort(int[] a) {
      // Base case: a.length < = 1
      if (a.length <= 1) { return; }
      int[] left = subarray(a, 0, a.length / 2);
      int[] right = subarray(a, a.length / 2, a.length);
      // Recursively sort both halves
      mergeSort(left);
      mergeSort(right);
      merge(a, left, right);
   }

   /**
    * Returns elements of array a including startIdx and
    * excluding stopIdx.
    */
   public static int[] subarray(int[] a, int startIdx, int stopIdx) {
      int[] res = new int[stopIdx - startIdx];
      for (int i = 0; i < res.length; i++) {
         res[i] = a[startIdx + i];
      }
      return res;
      // Note: Use System.arrayCopy() if performance matters!
   }

   /**
    * Merges two sorted arrays, a and b, into array m.
    * Precondition: m.length >= a.length + b.length
    */
   public static void merge(int[] m, int[] a, int[] b) {
      int idxA = 0, idxB = 0, idxM = 0;
      while (idxA < a.length && idxB < b.length) {
         if (a[idxA] <= b[idxB]) {
            m[idxM] = a[idxA];
            idxA++;
         } else {
            m[idxM] = b[idxB];
            idxB++;
         }
         idxM++;
      }
      // Now one array is exhausted!
      while (idxA < a.length) {
         m[idxM] = a[idxA];
         idxA++;
         idxM++;
      }
      while (idxB < b.length) {
         m[idxM] = b[idxB];
         idxB++;
         idxM++;
      }
   }

   /**
    * Returns index of val in sorted array a, or -1 if not found.
    * Uses binary search algorithm.
    * Note: if val appears multiple times in the array, which one of
    * its valid indices is returned is not specified.
    */
   public static int indexOf(int[] a, int val) {
      return indexOf(a, val, 0, a.length - 1);
   }

   /**
    * Helper method for indexOf(int[], int) that searches sorted
    * subarray of a from [min, max] (inclusive) for val and
    * returns its index or -1 if not found.
    */
   private static int indexOf(int[] a, int val, int min, int max) {
      // Base cases
      if (min > max) { return -1; }
      int mid = min + (max - min)/2;
      if (a[mid] == val) { return mid; }
      // Recursive case
      if (val < a[mid]) {
         // Search bottom half of subarray
         return indexOf(a, val, min, mid - 1);
      } else {
         // Search top half of subarray
         return indexOf(a, val, mid + 1, max);
      }
   }

   /**
    * Returns index of val in sorted array a, or -1 if not found.
    * (Same as indexOf(), but uses non-recursive binary search algorithm.)
    * Note: if val appears multiple times in the array, which one of
    * its valid indices is returned is not specified.
    */
   public static int binarySearch(int[] a, int val) {
      int min = 0;
      int max = a.length - 1;
      while (min <= max) {
         int mid = min + (max - min)/2;
         if (a[mid] == val) {
            return mid;
         } else if (val < a[mid]) {
            // search bottom half
            max = mid - 1;
         } else {
            // search top half
            min = mid + 1;
         }
      }
      return -1;
   }

   /**
    * Runs given sorting algorithm on random int arrays of given size and
    * prints the average time.  Warns if results are not sorted.
    *
    * Note: This uses advanced concepts (interfaces & method references) that
    * are NOT tested on the AP exam (but they're pretty handy!).
    *
    * @param sorter A Consumer that takes an int[] to sort.
    * @param size Size of random array of ints to sort.
    * @param runs Number of times to run the sort.
    */
   private static void timeIt(Consumer<int[]> sorter, int size, int runs) {
      long elapsed = 0;
      for (int i = 0; i < runs; i++) {
         int[] vals = randInts(size, 0, 10_000);
         // System.out.println("Unsorted: " + Arrays.toString(vals));
         long startTimeMs = System.currentTimeMillis();
         sorter.accept(vals); // Calls the sorting method!
         elapsed += System.currentTimeMillis() - startTimeMs;
         System.out.print(".");
         if (!isSorted(vals)) {
            System.out.println("Error! Array not sorted!");
            // System.out.println(Arrays.toString(vals));
         }
      }
      System.out.println("Avg: " + (elapsed / runs) + "ms");
   }
   public static void main(String[] args) {
      // indexOf / binarySearch tests
      int[] vals = {1, 1, 4, 6, 8, 10};
      System.out.println("Is 3 in the array? " + binarySearch(vals, 3));
      System.out.println("Is 4 in the array? " + binarySearch(vals, 4));
      vals = new int[] {};
      System.out.println("search empty array: " + binarySearch(vals, 4));
      vals = randInts(1_000_000, -100000, 100000);
      int target = vals[0];
      mergeSort(vals);
      System.out.println("1st element's new index: " + binarySearch(vals, target));

      // Sorting tests
      final int size = (int) 1E5;
      final int runs = 10;
      System.out.println("Timing Sorting Algorithms: size=" + size + ", runs=" + runs);
      System.out.println("** SelectionSort **");
      timeIt(Sorting::insertionSort, size, runs);
      System.out.println("** MergeSort **");
      timeIt(Sorting::mergeSort, size, runs);
   }
}
