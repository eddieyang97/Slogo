package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * Reads a file and updates dictionary to have the correct values
 * 
 * take resource bundle instead of file
 *
 */
public class PropertiesReader {
	/**
	 * parses through file and returns a Map of Strings to their commands
	 * 
	 * @param file
	 *            of properties
	 * @throws IOException
	 */
	public Map<Pattern, String> getProperties(File file) throws IOException {
		HashMap<Pattern, String> dictionary = new HashMap<>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] words = line.split("\\s+");
			if (words[0].charAt(0) != '#') {
				Pattern p = Pattern.compile(words[2], Pattern.CASE_INSENSITIVE);
				dictionary.put(p, words[0]);
			}
		}
		br.close();
		return dictionary;
	}
    /**
     * Returns dictionary with values
     * example of syntax is :
     * "resources/languages/English"
     */
    public Map<Pattern, String> getProperties (String syntax) {
    		HashMap<Pattern, String> dictionary = new HashMap<>();
        ResourceBundle resources = ResourceBundle.getBundle(syntax);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            dictionary.put(p, key);
        }
        return dictionary;
    }
}
