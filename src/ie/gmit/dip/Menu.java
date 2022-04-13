package ie.gmit.dip;

import java.io.IOException;
import java.util.Scanner;

/**
 *  The Menu class runs the menu and user input for the WordCloud Program. 
 * 
 * @author David <strong>Faulkner</strong>, G00299507
 * @version 1.0
 */
public class Menu {
	private Scanner scan = new Scanner(System.in);
	private Parser parseFile = new ParseFile();
	//private Parser parseURL = new ParseURL();

	/**
	 * Displays the program menu to the user and provides options to create the wordcloud.
	 * 
	 * Running time: O(1) -> this method is run once in constant time.
	 * 
	 * @return void
	 * @throws IOException
	 */
	public void menu() throws IOException {
		System.out.println("******************************");
		System.out.println("****  Word Cloud Project  ****");
		System.out.println("****       G00299507      ****");
		System.out.println("****     David Faulkner   ****");
		System.out.println("****     AOOP Jan 2022    ****");
		System.out.println("******************************");
		
		System.out.println("\n1) Please choose to import text from saved file or URL.");
		System.out.println("Enter \"1\" for saved file (or \"2\" for URL --> need to add this option, just saved file working): ");
		//int userIn = scan.nextInt();
		
		System.out.println("\n2) Please enter file or URL path (e.g. \"./filepath.txt\" or \"https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html\"): ");
		String path = scan.next();
		
		System.out.println("\n3) Enter the number of words to be displayed on word cloud: ");
		int wordCount = scan.nextInt();
		
		System.out.println("\n4) Enter width of canvas (e.g. 1000): ");
		int width = scan.nextInt();
		
		System.out.println("\n5) Enter height of canvas (e.g. 800): ");
		int height = scan.nextInt();
		
		System.out.println("\n6) Enter location of ignore words file (e.g. \"./ignorewords.txt\"): ");
		String ignoreFile = scan.next();
		
		System.out.println("\n7) Enter name of image to be saved (e.g. \"image.png\"): ");
		String imageName = scan.next();

		scan.close(); // close scanner
				
		// call parse based on user selection of file path or URL
		/*
		if(userIn == 1) {
			parseFile.setWordCount(wordCount);
			parseFile.setCanvasSize(width, height);
			parseFile.saveImageAs(imageName);
			parseFile.parseIgnoreFile(ignoreFile);
			parseFile.parse(path);
		} else if (userIn == 2) {
			parseURL.setWordCount(wordCount);
			parseURL.setCanvasSize(width, height);
			parseURL.saveImageAs(imageName);
			parseURL.parseIgnoreFile(ignoreFile);
			parseURL.parse(path);
		}*/
		
		parseFile.setWordCount(wordCount);
		parseFile.setCanvasSize(width, height);
		parseFile.saveImageAs(imageName);
		parseFile.parseIgnoreFile(ignoreFile);
		parseFile.parse(path);
		
	}

}
