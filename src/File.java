public class File extends FSEntry {
   private int size;

   public File(Folder parent, String name, int size) {
      super(parent, name);
      this.size = size;
   }

   @Override
   public int size() { return size; }
}
