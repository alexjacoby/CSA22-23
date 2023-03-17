import java.util.ArrayList;

public class Folder extends FSEntry {
   private ArrayList<FSEntry> entries;

   public Folder(Folder parent, String name) {
      super(parent, name);
      entries = new ArrayList<>();
   }

   @Override
   public int size() {
      int size = 0;
      for (FSEntry e : entries) {
         size += e.size();
      }
      return size;
   }

   public void add(FSEntry e) {
      entries.add(e);
   }

   public String toString() {
      return super.toString() + " #entries=" + entries.size();
   }
}
