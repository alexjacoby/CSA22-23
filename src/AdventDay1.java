import java.util.Scanner;

public class AdventDay1 {
   public static void main(String[] args) {
      long max = -1;
      long calories = 0; // calories carried by current elf
      Scanner scan = new Scanner(System.in);
      while (scan.hasNextLine()) {
         String line = scan.nextLine();
         if (line.isEmpty()) {
            // next elf!
            if (calories > max) {
               max = calories;
            }
            System.out.println(calories);
            calories = 0;
         } else {
            calories += Integer.parseInt(line);
         }
      }
      // Check the last elf in case file doesn't end with blank line
      if (calories > max) {
         max = calories;
      }
      System.out.println("The max calories is " + max);
   }
}
