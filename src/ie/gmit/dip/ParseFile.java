package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  The ParseFile class is a subclass of Parser and contains the parse method specific to parsing a file saved in the project folder.
 * 
 * @author David <strong>Faulkner</strong>, G00299507
 * @version 1.0
 */
public class ParseFile extends Parser {
	
	/**
	 * Parses .txt file inputted by user
	 * Running time: O(n) --> calls the sortMap method which is in O(n log n), however it loops through each word in the inputted document so results in slower process.
	 * 
	 */
	public void parse(String input) throws IOException {
		// read in word text file
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(input))));) {

			String line = null;

			while ((line = br.readLine()) != null) {
				String[] words = line.split(" ");
				for (String w : words) {
					String addWord = checkWord(w.trim().toLowerCase());
					if (!getIgnoreWords().contains(addWord)) {
						addToMap(addWord);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		sortMap(); // call to sort map
		getWordCloud().process(this.getWordMap());
	}

}
