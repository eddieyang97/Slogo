package model;
/**
 * Implements the Dictionary interface for SLogo
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import model.commands.Function;
import model.commands.Variable;

public class SLogoDictionary implements Dictionary {

	private Map<Pattern, String> commandList;
	private List<Variable> variableList;
	private List<Function> functionList;

	public SLogoDictionary() {
		commandList = new HashMap<>();
		variableList = new ArrayList<>();
		functionList = new ArrayList<>();
	}
	/**
	 * Returns the function if it exists; else returns a new Function with the input name and adds it to the dictionary
	 */
	@Override
	public Function getFunction(String value) {
		for (Function f : functionList)
			if (f.getName().equals(value))
				return new Function(f);
		Function newFunct = new Function(value);
		functionList.add(newFunct);
		return newFunct;
	}
	/**
	 * Returns the command name if it exists; otherwise returns null
	 */
	@Override
	public String getCommand(String value) {
		for (Pattern p : commandList.keySet()) {
			if (p.matcher(value).matches())
				return commandList.get(p);
		}
		return null;
	}
	/**
	 * Returns the variable if it exists; else returns a new variable with the input name and adds it to the dictionary
	 */
	@Override
	public Variable getVariable(String value) {
		for (Variable v : variableList)
			if (v.getName().equals(value))
				return v;
		Variable newVar = new Variable(value);
		variableList.add(newVar);
		return newVar;
	}

	@Override
	public void setLanguage(Map<Pattern, String> map) {
		commandList = map;
	}

	@Override
	public void clearAll() {
		variableList = new ArrayList<>();
		functionList = new ArrayList<>();
	}

}
