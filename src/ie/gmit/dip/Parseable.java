package ie.gmit.dip;

import java.io.IOException;

/**
 *  The interface Parseable contains the parse method which will be specialised with the two subclasses of the Parse class.
 * 
 * @author David <strong>Faulkner</strong>, G00299507
 * @version 1.0
 */
public interface Parseable {
	
	/**
	 * Parses the input file suggested by the user
	 * 
	 * @param input
	 * @return void
	 * @throws IOException
	 */
	public void parse(String input) throws IOException;

}
