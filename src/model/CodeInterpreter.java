package model;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class for executing code instructions
 */
public interface CodeInterpreter {
	/**
	 * executes a command
	 * @param command
	 */
	public void execute(String[] command);
	
	/**
	 * makes a properties reader to update the dictionary of properties
	 * @param String corresponding to ResourceBundle to load
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 */
	public void setProperties(String syntax) throws IOException;
}
