/** Tests some of the List class methods.
 *  Should generate the output shown in the HW10 document. */
public class Test1 {
	
	public static void main(String args[]) {
		//testAddFirst();
		//testIndexOf();
		//testGet();
		//testRemove();
	//	testToArray();
		///testIterator();
       testUpdate();
	}
	
	public static void testAddFirst() {
		System.out.println("Tests the addFirst method:");
		List q = new List();
		System.out.println("The list before adding any element: " + q);
		q.addFirst('a');
		System.out.println("The list after adding 'a' to the beginning: " + q);
		q.addFirst('b');
		System.out.println("The list after adding 'b' to the beginning: " + q);
		q.addFirst('c');
		System.out.println("The list after adding 'c' to the beginning: " + q);
		System.out.println();
	}
	
	public static void testIndexOf() {
		System.out.println("Tests the indexOf method:");
		List q = buildListabc();
		System.out.println("Base list: " + q);
		System.out.println("The index of 'a' is: " + q.indexOf('a'));
		System.out.println("The index of 'b' is: " + q.indexOf('b'));
		System.out.println("The index of 'c' is: " + q.indexOf('c'));
		System.out.println("The index of 'd' is: " + q.indexOf('d'));
		System.out.println();
	}

	public static void testGet() {
		System.out.println("Tests the get method:");
		List q = buildListabc();
		System.out.println("Base list: " + q);
		// Write the rest of the test code here.
		// Start by getting objects that have a valid index.
		System.out.println("Get the char in index 1: " + q.get(1));
		// Then try to get objects that have a negative index, or an index that it
		//System.out.println("Get the char in index -1: " + q.get(-1));
		// greater than the list size. This will test the execption code that you have to
		System.out.println("Get the char in index 8: " + q.get(8));
		// write in the get() method implementation.
		// Once you verify that the get() method generates the exception as specified,
		// remove the calls with the invalid indexes from this method, so that all the 
		// testing methods in this class will run one after the other without interruption,
		// generating the output shown in the HW10 document. 
		System.out.println();
	}

	public static void testRemove() {
		System.out.println("Tests the remove method:");
		List q = buildListabc();
		System.out.println("Base list: " + q);
		System.out.println("remove b: " + q.remove('b') + q);
		System.out.println("remove b: " + q.remove('b') + q);
		// Write the rest of the test code here.
	}

	public static void testToArray() {
		System.out.println("Tests the toArray method:");
		List q = buildListabc();
	    System.out.println("Base list: " + q);
	    CharData[] arr = q.toArray();
		for (int i = 0; i < q.getSize(); i++) {
			System.out.println(arr[i]);
		}

		// Constructs an array that consists of all the elements of the list,
		// and prints the elements of this array.
		// Write the rest of the test code here.
		System.out.println();	
	}
	
	public static void testIterator() {
		System.out.println("Tests the iterator method:");
		// Builds a list of some elements
		List q = new List();
		q.addFirst('a');
		q.addFirst('b');
		q.addFirst('c'); 
		q.addFirst('d');
		q.addFirst('e');
		q.addFirst('f'); 
		System.out.println("Base list: " + q);
		
		// Uses an iterator to print all the elements from 
		// position 2 to the end of the list.
		System.out.println("The elements of the list from index 2 to the end: ");
		// Write the rest of the test code here.
		System.out.println();
	}

	public static void testUpdate() {
		System.out.println("Tests the update(chr) method, which works as follows:");
		System.out.println("If the character chr is not in the list, adds it to the list's beginning.");
		System.out.println("If the character chr is in the list, increments its counter.");
		List q = new List();
		// Write the rest of the test code here.
		System.out.println("the list before: ()");
		q.update('a');
		System.out.println("the list after addint 'a' : " + q);
		q.update('b');
		System.out.println("the list after addint 'b' : " + q);
		q.update('c');
		System.out.println("the list after addint 'c' : " + q);
		System.out.println("the list before: ()");
		q.update('a');
		System.out.println("the list after addint 'a' : " + q);
		q.update('b');
		System.out.println("the list after addint 'b' : " + q);
		q.update('c');


		System.out.println();
	}

	// Builds and returns a small list. Called by some of the test methods.
	private static List buildListabc() {
		List q = new List();
		q.addFirst('a');
		q.addFirst('b');
		q.addFirst('c');
		return q;
	}
}
