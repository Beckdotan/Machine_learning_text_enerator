import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/* A random text generator. The program "trains" itself by reading and analysing
 * character probabilitie in a given text, and then generates random text that is
 * "similar" to the given text. */
public class TextGen {

	// Length of the moving window
	private static int windowLength; 
	
	// A map for managing the (window, list) mappings 
	private static HashMap<String, List> map;

	// Random number generator, used by the getRandomChar method. 
	private static Random randomGenerator;

	public static void main(String[] args) {
		int windowLength = Integer.parseInt(args[0]);
		String initialText = args[1];
		int generatedTextLength = Integer.parseInt(args[2]);
		boolean randomGeneration = args[3].equals("random");
		String fileName = args[4];
		init(windowLength, randomGeneration, fileName);

		// Creates the map (implemented as a global, class-level variable).
		train();
		
		// Uses the map for generating random text, and prints the text.
		String generatedText = generate(initialText, generatedTextLength);
		System.out.println(generatedText);
	}
	
	// Initializes the training and text generation processes
	private static void init(int length, boolean randomMode, String fileName) {
		windowLength = length;
		map = new HashMap<String, List>();
		StdIn.setInput(fileName);
		if (randomMode) {
			// Creates a random number generator with a random seed:
			// Each program run will produce a different random text.
		    randomGenerator = new Random();    
		} else {
			// Creates a random number generator with a fixed seed:
			// Each program run will produce the same random text.
			// Designed to support consistent testing and debugging.
            randomGenerator = new Random(20);
		}
	}

	/** Trains the model, creating the map. */
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
		/// Put here the code that you wrote in Stage I
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

	/** Generates a string representation of the map, for debugging purposes. */
	public static String mapString() {
		/// Put here the code that you wrote in Stage II.
		/// We leave this function in the code, in case you want to use it
		/// for debugging.
		StringBuilder mapString = new StringBuilder();

		for (Map.Entry<String, List> entry : map.entrySet()) {
			mapString.append(entry);
			mapString.append("\n");
		}
		return mapString.toString();
	}

	/**
	 * Generates random text, based on the map crated by the train() method. 
	 * @param initialText - the beginning of the generated text 
	 * @param textLength - the size of generated text
	 * @return the generated text
	 */
	public static String generate(String initialText, int textLength) {
		/// Replace the following statement with your code
		if (windowLength > initialText.length()) {
			System.out.println(initialText);
			System.exit(5);
		}
		String window = initialText.substring(initialText.length()- windowLength);
		int count = 0;
		StringBuilder ans = new StringBuilder();
		ans.append(initialText);

		while (map.containsKey(window) && count<textLength){
			char c = getRandomChar(map.get(window));
			ans.append(c);
			window = window.substring(1) + c;
			count ++;
		}

		//System.out.println(ans);
		return ans.toString();

	}

	// Returns a random character from a given list of CharData objects,
    // using Monte Carlo.
	private static char getRandomChar(List list) {
		/// Replace the following statement with the code that you wrote in Test2.txt.
		double r = randomGenerator.nextDouble();
		CharData[] cds = list.toArray();
		int i = 0;
		while (cds[i].pp < r){
			i++;
		}
		return  cds[i].chr;

	}
}
