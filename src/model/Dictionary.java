package model;

import java.util.Map;
import java.util.regex.Pattern;

import model.commands.Function;
import model.commands.Variable;

/**
 * translates user inputs to corresponding commands, variables and functions
 */
public interface Dictionary {
	
	/**
	 * uses a map to get a Function
	 * @param value
	 * @return
	 */
	Function getFunction(String value);
	
	/**
	 * uses a map to get a command
	 * @param value
	 * @return
	 */
	String getCommand(String value);
	
	/**
	 * uses a map to get a variable
	 * @param value
	 * @return
	 */
	public Variable getVariable(String value);
	
	/**
	 * updates one value to the map of commands
	 * @param key
	 * @param value
	 */
	public void setLanguage(Map<Pattern, String> map);
	
	/**
	 * clears dictionary of user-defined variables and functions
	 */
	public void clearAll();
}
