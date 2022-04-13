package ie.gmit.dip;

import java.io.*;
import java.util.*;

/**
 *  The Parse class is abstract and implements the interface Parseable. 
 * 
 * @author David <strong>Faulkner</strong>, G00299507
 * @version 1.0
 */
public abstract class Parser implements Parseable {
	private Set<String> ignoreWords = new TreeSet<>();
	private Map<String, Integer> wordMap = new HashMap<>();
	private int wordCount;
	private WordCloud wordCloud = new WordCloud();

	/**
	 * Setter for wordCount
	 * 
	 * @param wordCount
	 */
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	
	/**
	 * Getter for ignoreWords file
	 * 
	 * @return ignoreWords Set
	 */
	public Set<String> getIgnoreWords() {
		return ignoreWords;
	}

	/**
	 * Getter for wordMap
	 * 
	 * @return wordMap Map
	 */
	public Map<String, Integer> getWordMap() {
		return wordMap;
	}

	/**
	 * Getter for wordCloud
	 * 
	 * @return WordCloud object
	 */
	public WordCloud getWordCloud() {
		return wordCloud;
	}
	
	/**
	 * Sets canvas size depending on user input
	 * 
	 * @param width
	 * @param height
	 */
	public void setCanvasSize(int w, int h) {
		getWordCloud().setImageWidth(w);
		getWordCloud().setImageHeight(h);
	}

	/**
	 * Parses the ignore words file using BufferedReader
	 * 
	 * @param input text filepath
	 */
	public void parseIgnoreFile(String input) {
		// read in ignorewords.txt
		try (BufferedReader br = new BufferedReader(
								new InputStreamReader(
								new FileInputStream(
								new File(input))));) {

			String line = null;

			while ((line = br.readLine()) != null) {
				String[] words = line.split(" ");
				for (String w : words) {
					getIgnoreWords().add(w.toLowerCase());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Abstract method to parse input text file
	 */
	public abstract void parse(String input) throws IOException;

	/**
	 * Add each word to map
	 * 
	 * @param word to add
	 */
	protected void addToMap(String word) {
		if (word.length() < 3) {
			return;
		}

		int freq = 1;
		if (getWordMap().containsKey(word)) {
			freq += getWordMap().get(word);
		}
		getWordMap().put(word, freq);
	}

	/**
	 * Validates word to ensure each string contains letters only
	 * 
	 * @param word
	 * @return String that has been validated and allowed to include in word cloud
	 */
	protected String checkWord(String word) {
		StringBuilder str = new StringBuilder(word);
		char[] wordArray = word.toCharArray();

		if (!Character.isLetter(wordArray[0])) {
			str.deleteCharAt(0);
		} else if (!Character.isLetter(wordArray[wordArray.length - 1])) {
			str.deleteCharAt(str.length() - 1);
		}

		return str.toString();
	}

	/**
	 * Sorts map in order of frequency value descending
	 * Running time: O(n log n) --> sorting a collection using a list and Map.Entry
	 * Some parts of this code is referenced from: https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
	 * 
	 * 
	 */
	protected void sortMap() {
		// create new list from wordMap entrySet
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(getWordMap().entrySet());

		// ** could this be made into seperate Comparator class??
		// sort list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> object1, Map.Entry<String, Integer> object2) {
				return (object2.getValue()).compareTo(object1.getValue());
			}
		});

		// create new temporary map
		Map<String, Integer> temp = new LinkedHashMap<String, Integer>();

		// add elements to sorted temp map
		for (Map.Entry<String, Integer> l : list) {
			// limit map size, **make size changeable by user
			if (temp.size() < this.wordCount) {
				temp.put(l.getKey(), l.getValue());
			}
		}

		// assign wordMap as sorted map from temporary map variable
		this.wordMap = temp;
	}

	/**
	 * Saves image as user inputted file name
	 * 
	 * @param fileName
	 */
	public void saveImageAs(String fileName) {
		getWordCloud().setSaveImageAs(fileName);
	}

}
