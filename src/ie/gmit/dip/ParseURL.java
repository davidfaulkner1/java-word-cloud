package ie.gmit.dip;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *  The ParseURL class is a subclass of Parser and contains the parse method specific to parsing a URL path of webpage text.
 * 
 * @author David <strong>Faulkner</strong>, G00299507
 * @version 1.0
 */
public class ParseURL extends Parser {
	
	/**
	 * Parses URL path inputted by user
	 * Running time: O(n) --> calls the sortMap method which is in O(n log n), however it loops through each word in the inputted document so results in slower process.
	 * 
	 * Uses imported Jsoup to get text from URL path.
	 */
	public void parse(String input) throws IOException {
		Document doc = Jsoup.connect(input).get();

		String bodyText = doc.body().text();
		String[] textArray = bodyText.split(" ");
		try {
			for (String w : textArray) {
				if (!w.isEmpty() || !w.isBlank() || w.length() > 0) {
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
