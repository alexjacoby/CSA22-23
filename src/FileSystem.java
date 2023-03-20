import java.io.IOException;
import java.util.Scanner;

public class FileSystem {
   private Folder root;

   /**
    * Generate FileSystem based on text file, format specified
    * in Advent of Code 2022, Day 7.
    */
   public FileSystem(String pathname) throws IOException {
      root = new Folder(null, "root");
      java.io.File file = new java.io.File(pathname);
      Scanner in = new Scanner(file);
      Folder loc = root;
      while (in.hasNextLine()) {
         String line = in.nextLine();
         if (line.equals("$ cd /")) {
            loc = root;
         } else if (line.equals("$ ls")) {
            handleLs(loc, in);
         } else if (line.equals("$ cd ..")) {
            if (loc.parent() == null) {
               throw new IllegalStateException("Attempt to visit root's parent!");
            }
            loc = loc.parent();
         } else if (line.startsWith("$ cd ")) {
            String name = line.split(" ")[2];
            FSEntry entry = loc.getEntry(name);
            if (entry instanceof Folder) {
               loc = (Folder) entry;
            } else {
               throw new IllegalStateException("Attempt to cd to a file: " + name);
            }
         } else {
            // NEW
            throw new IllegalStateException("Unexpected line: " + line);
         }
      }
      System.out.println("done");
   }
   public static void main(String[] args) throws Exception {
      FileSystem fs = new FileSystem("fsinput.txt");
      /*
      Folder users = new Folder(root, "users");
      Folder programs = new Folder(root, "programs");
      Folder nils = new Folder(users, "nils");
      Folder intellij = new Folder(programs, "intellij");
      File nilsGame = new File(nils, "nilsGame", 10_000);
      System.out.println("root: " + root);
      System.out.println(nilsGame);
      */

   }

   private static void handleLs(Folder loc, Scanner in) {
      System.out.println("in handleLs...");
      // Regular expression
      while (in.hasNext("(dir)|(\\d+)")) {
         System.out.println("  " + in.nextLine());
         String line = in.nextLine();
         if (line.startsWith("dir")) {
            String name = line.substring("dir ".length());
            Folder f = new Folder(loc, name);
         } else {
            int spaceIdx = line.indexOf(" ");
            int size = Integer.parseInt(line.substring(0, spaceIdx));
            String name = line.substring(spaceIdx + 1);
            File f = new File(loc, name, size);
         }
      }
      System.out.println("...done handleLs");
   }
}
