/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
   private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        // Starts with a dummy node
        first = new Node(null);
        size = 0;
    }
    
    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        Node newNode = new Node(new CharData(chr));
        // Inserts the new node between the dummy and the first node.
        newNode.next = first.next;
        first.next = newNode;
        size++;
    }
    
    /** Textual representation of this list. */
    public String toString() {
        if (size == 0) return "()";
        StringBuilder str = new StringBuilder("(");
        // Creates a pointer to the first element
        Node current = first.next; // skips the dummy  	    
        while (current != null) {
            str.append(current.cd + " ");
            current = current.next; 
        }
        // Replaces the last " " with ")"
        str.deleteCharAt(str.length()-1);
        str.append(")");
        return str.toString();
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        // Replace the following statement with your code
        Node current = first.next;
        for (int i = 0; i < size; i++) {
            if (current.cd.chr == chr) return i;
            current = current.next;
        }
        return -1;
    }

    /** If the chr of the given CharData object exists in one of the
     *  CharData objects in this list, removes this CharData object from 
     *  the list and returns true. Otherwise, returns false. */
    public boolean remove(char chr) {
        if (indexOf(chr) == -1) return false;  
        // Creates two lock-step pointers
        Node prev = first;
        Node current = first.next;
        while (!current.cd.equals(chr)) {
            prev = current;
            current = current.next;
        }
        prev.next = current.next;
        current = null; // for object recycling

        size--;
        return true;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        // Replace the following statement with your code
        Node current = first.next;
        int i = 0;
        if (index >= size){
            throw new IndexOutOfBoundsException();
        }
        while (i != index){
            current = current.next;
            i++;
        }
        return current.cd;
    }
     
    /** If the given chr exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
       Node current = first.next;
        while (current != null){
            if (current.cd.chr == chr){
                current.cd.count++;
                return;
            }
            current = current.next;
        }
        Node letter = new Node (new CharData(chr), first.next);
        first.next = letter;
        this.size++;

    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    // Replace the following statement with your code
        Node current = first.next;
        for (int i = 0; i < size; i++) {
            arr[i] = current.cd;
            current = current.next;
        }

        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first.next; // skips the dummy 
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }
}