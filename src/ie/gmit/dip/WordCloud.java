package ie.gmit.dip;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 *  The WordCloud class is abstract and implements the interface Parseable.
 * 
 * @author David <strong>Faulkner</strong>, G00299507
 * @version 1.0
 */
public class WordCloud {
	private BufferedImage image;
	private Graphics2D g;
	private int imageWidth;
	private int imageHeight;
	private int fontSize;
	private String saveImageAs;   
	private List<Rectangle2D> used = new ArrayList<>();
	
	/**
	 * Setter for imageWidth
	 * Running time: O(1) --> constant
	 * 
	 * @param imageWidth
	 */
	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	/**
	 * Setter for imageHeight
	 * Running time: O(1) --> constant
	 * 
	 * @param imageHeight
	 */
	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	/**
	 * Setter for saveImageAs
	 * Running time: O(1) --> constant
	 * 
	 * @param saveImageAs
	 */
	public void setSaveImageAs(String saveImageAs) { 
		this.saveImageAs = saveImageAs;
	}

	/**
	 * Processes word map, calls to create the canvas, get font size and write image.
	 * Running time: O(n) --> this has three methods of O(1) each and a for each loop which will always be worst case of O(n) for the amount of words to be drawn on canvas.
	 * 
	 * @param map
	 * @throws IOException
	 */
	public void process(Map<String, Integer> map) throws IOException {
		createCanvas();
		Set<String> words = map.keySet();
		
		// set initial font size
		setFontSize();

		// loop through each word in map and call create method to draw on canvas
		for (String word : words) {
			create(word);
			fontSize--;	// reduce font size by 1 after each iteration
			if(fontSize < 8) fontSize = 5; // limit font size going into negative values
		}
		
		// after drawing is created, call write image to file method
		writeImage();
	}

	/**
	 * Sets font size for each word
	 * Running time: O(1) --> constant and checked for each word seperately.
	 * 
	 */
	private void setFontSize() {
		int canvasArea = imageWidth*imageHeight;
		if(canvasArea < 50000) {
			this.fontSize = 10;
		} else if(canvasArea < 100000) {
			this.fontSize = 20;
		} else if(canvasArea < 250000) {
			this.fontSize = 40;
		} else {
			this.fontSize = 60;
		}
	}

	/**
	 * Creates canvas image
	 * Running time: O(1) --> constant, called once in program.
	 * 
	 */
	private void createCanvas() {
		image = new BufferedImage(this.imageWidth, this.imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
	}
	
	// 
	// 
	/**
	 * Creates graphic for each word that will be drawn onto canvas
	 * Running time: O(1) this method is run for each word seperately so therefore runs in constant time. Although there is a while loop, this is maxed out to 25 loops.
	 * 
	 * @param word
	 * 
	 * Some parts of this code is referenced from here - for avoidance of overlapping words on canvas --> https://stackoverflow.com/questions/34690321/java-graphics-random-text/34691463
	 */
	private void create(String word) {
		// graphics to place on canvas
		g = (Graphics2D) image.getGraphics();
		g.setColor(getRandomColor()); //return eg. Color.red
		g.setFont(getRandomFont()); //return eg. Font.SANS_SERIF, FONT.ITALIC, 62
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(word, g);
		
		//get string width
		int textWidth = g.getFontMetrics().stringWidth(word);
		int textHeight = g.getFontMetrics().getHeight();
		
		// initialise x and y coordinates for string text position
		int imgX;
		int imgY;
		
		int checkCount = 0; // initialise checkCount
		
		//get random (x, y) coordinates for string location in 'do while' loop (while condition to check for overlapping of words on canvas)
		do {
			imgX = (int) (Math.random()* (this.imageWidth - textWidth));
			imgY = (int) (Math.random()* (this.imageHeight - textHeight));
			
			bounds.setFrame(imgX, imgY, textWidth, textHeight);
			
			// check count to ensure loop ends after 50 checks
			checkCount++;
			if(checkCount == 25) break;
		} while(checkCollision(used, bounds));
		
		used.add(bounds);
		g.drawString(word, imgX, imgY); // location to draw
		
	}

	/**
	 * Checks for overlapping of graphics on canvas
	 * Running time: O(n) --> size of used list is looped at O(n) in worst case.
	 * 
	 * @param used list of space on canvas
	 * @param bounds of current graphic to be checked
	 * @return
	 */
	private boolean checkCollision(List<Rectangle2D> used, Rectangle2D bounds) {
		boolean collides = false;
		
		for(Rectangle2D check : used) {
			if(bounds.intersects(check)) {
				collides = true;
				break;
			}
		}
		return collides;
	}

	/**
	 * Gets random color for each string graphic
	 * Running time: O(1) --> constant and checked for each word seperately.
	 * 
	 * @return random Color
	 */
	private Color getRandomColor() {
		Random random = new Random();
		float r = random.nextFloat(); // get red value
		float g = random.nextFloat(); // get green value
		float b = random.nextFloat(); // get blue value
		return new Color(r, g, b);
	}

	/**
	 * Gets random font style for each string graphic
	 * Running time: O(1) --> constant and checked for each word seperately.
	 * 
	 * @return random Font
	 */
	private Font getRandomFont() {		
		// get random font style 
		int rand = (int) (Math.random() * 10);
		int fontStyle;
				
		if (rand < 3.33) {
			fontStyle = Font.PLAIN;
		} else if (rand < 6.66) {
			fontStyle = Font.ITALIC;
		} else {
			fontStyle = Font.BOLD;
		}
		
		return new Font(Font.SERIF, fontStyle, fontSize);
	}

	/**
	 * Writes image to file
	 * Running time: O(1) --> constant, called once for program.
	 * 
	 * @throws IOException
	 */
	private void writeImage() throws IOException {
		g.dispose();
		ImageIO.write(image, "png", new File(saveImageAs));
		System.out.println(saveImageAs + " written to project file!");
	}

}
