import java.util.HashMap;
import java.util.Map;
/* This class provides the training stage of a random text generator.
 * The program reads text ("corpus") from a given file, and analyses
 * and records the character probabilitie in the given text. */
public class TextTrain {

	// Length of the moving window
	private static int windowLength;

	// A map for managing the (window, list) mappings 
	private static HashMap<String, List> map;

	public static void main(String[] args) {
		int windowLength = Integer.parseInt(args[0]);
		String fileName = args[1];
		init(windowLength, fileName);

		// Creates the map (implemented as a global, class-level variable).
		train();

		// Prints a textual representation of the map (for debugging purposes only).
		System.out.println(mapString());
	}

	// Initializes the training process.

	public static void init(int length, String fileName) {
		windowLength = length;
		map = new HashMap<String, List>();
		StdIn.setInput(fileName);
	}

	/**
	 * Trains the model, creating the map.
	 */
	public static void train() {
		String window = "";
		char c;
		// Constructs the first window
		String sb = StdIn.readAll();
		for (int i = 0; i < sb.length() - windowLength; i++) {
			String current = sb.substring(i, i + windowLength);
			c = sb.charAt(i + windowLength);
			if (map.containsKey(current)) {
				map.get(current).update(c);
			} else {
				List temp = new List();
				temp.addFirst(c);
				map.put(current, temp);
			}
		}

		// Computes and sets the p and pp fields of all the
		// CharData objects in each list in the map.
		for (List list : map.values()) {
			calculateProbabilities(list);
		}
	}

	// Computes and sets the probabilities (p and pp fields) of all the
	// characters in the given list. */
	private static void calculateProbabilities(List list) {
		// Put here the code that you wrote in the test2.java class.
		double totalCount = 0;
		for (int i = 0; i < list.getSize(); i++) {
			totalCount = totalCount + list.get(i).count;
		}
		for (int i = 0; i < list.getSize(); i++) {
			if (i == 0) {
				list.get(i).p = list.get(i).count / totalCount;
				list.get(i).pp = list.get(i).p;
			} else {
				list.get(i).p = list.get(i).count / totalCount;
				list.get(i).pp = list.get(i).p + list.get(i - 1).pp;
			}
		}
	}

	/**
	 * Generates a string representation of the map, for debugging purposes.
	 */
	public static String mapString() {
		StringBuilder mapString = new StringBuilder();

		for (Map.Entry<String, List> entry : map.entrySet()) {
			mapString.append(entry);
			mapString.append("\n");
		}
		return mapString.toString();
	}
}

