WordCloud Project - AOOP, HDip Software Development, GMIT
David Faulkner - G00299507

Program instructions:
1) If executing the program from the /bin directory, user can enter the command "java -cp ./wordcloud.jar ie.gmit.dip.Runner".

2) The user will be asked: "Please choose to import text from saved file or URL. Enter "1" for saved file or "2" for URL:".

3) The user will then be asked: "Please enter file or URL path (e.g. "../filepath.txt" or "https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html":".
- If using a saved file that is located in the main project folder, then enter something like this: "../bohrap.txt".
- If using a url path, then enter something like this: "https://en.wikipedia.org/wiki/Javadoc".

4) The user will then be asked to "Enter the number of words to be displayed on word cloud:".
- Enter the desired number of words to be displayed on the drawing here.

5) The user will then be asked to "Enter width of canvas (e.g. 1000):"
- Enter the desired width of the canvas, recommended between 400 and 1500.

6) The user will then be asked to "Enter height of canvas (e.g. 800):"
- Enter the desired height of the canvas, recommended between 400 and 1500.

7) The user will then be asked to "Enter location of ignore words file:"
- Enter the relative file path of the file in which words are to be ignored. If executing the .jar file from the /bin directory then enter "../ignorewords.txt" as the ignorewords.txt file is located in the main project folder.

8) The user will then be prompted to "Enter name of image to be saved (e.g. "image.png"):"


Program features:
- This program creates a wordcloud and outputs the image to png format.
- It is used on a command line interface.
- The canvas to be drawn is customizable in many ways by the user:
	- The user can create a canvas of their desired width and height.
	- The user can create the wordcloud based on a saved text file or from a webpage using a url.
	- The user can select how many words to be displayed on the image.
	- The user can select the file in which to exclude certain common words.
	- The user can save the image to their desired name and path.
- The Runner class calls the Menu class to start the menu.
- The Menu class starts a console UI for the user to allow input with the Scanner.
- The interface Parseable contains an abstract method parse() which will be specialised in sub-classes ParseFile and ParseURL depending on whether the user wants to create a wordcloud from a .txt file or webpage.
- The Parser class is an abstract implementation class of parseable and implements all the main tasks for the containing and sorting the word map.
- Depending on whether the user selects to use a txt file or a url, the ParseFile class or ParseURL sub-classes will be used. 
- The WordCloud class is then called when the word map is ready to be used, and this creates the word cloud and writes it to file.
- The create(String word) and checkCollision(List<Rectangle2D> used, Rectangle2D bounds) methods, contain code that try to avoid collision on the word maps. Random x, y coordinates are generated for each word - but these are checked to ensure that they don't collide with words that have already been drawn on the canvas.
- Jsoup is used to import and parse the text from a URL webpage. This is imported from a downloaded .jar file saved in the /lib folder.


Potential issues:
- Jsoup is imported to parse the URL text into the program. This worked well when used in eclipse, however, there may be issues when running the program .jar file from the /bin folder as at the last minute I had issues with my program file locating this imported .jar file. Parsing a .txt file should not be affected by this. See issue below:

Exception in thread "main" java.lang.NoClassDefFoundError: org/jsoup/Jsoup
        at ie.gmit.dip.ParseURL.parse(ParseURL.java:22)
        at ie.gmit.dip.Menu.menu(Menu.java:69)
        at ie.gmit.dip.Runner.main(Runner.java:9)
Caused by: java.lang.ClassNotFoundException: org.jsoup.Jsoup
        at java.base/jdk.internal.loader.BuiltinClassLoader.loadClass(BuiltinClassLoader.java:606)
        at java.base/jdk.internal.loader.ClassLoaders$AppClassLoader.loadClass(ClassLoaders.java:168)
        at java.base/java.lang.ClassLoader.loadClass(ClassLoader.java:522)
        ... 3 more
