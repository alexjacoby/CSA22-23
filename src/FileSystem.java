import java.util.Scanner;

public class FileSystem {
   public static void main(String[] args) throws Exception {
      Folder root = new Folder(null, "root");
      java.io.File file = new java.io.File("fsinput.txt");
      Scanner in = new Scanner(file);
      Folder loc = root;
      while (in.hasNextLine()) {
         String line = in.nextLine();
         if (line.equals("$ cd /")) {
            loc = root;
         } else if (line.equals("$ ls")) {
            handleLs(loc, in);
         }
      }
      System.out.println("done");
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
