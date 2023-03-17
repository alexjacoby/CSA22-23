/**
 * Represents a File or Folder in the file system.
 */
public abstract class FSEntry {
   private Folder parent;
   private String name;

   public FSEntry(Folder parent, String name) {
      this.parent = parent;
      this.name = name;
      if (parent != null) {
         parent.add(this); // Be careful! We aren't fully created yet!
      }
   }

   public Folder parent() {
      return parent;
   }

   public String name() {
      return name;
   }

   public String toString() {
      return getClass() + " size=" + size() +
            " parent=" + ((parent != null)? parent.name() : "null");
   }

   /** Returns size of this entry - must be implemented by subclasses! */
   public abstract int size();

}
