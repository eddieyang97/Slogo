package model;

/**
 * The SLogo code interpreter, which implements the CodeInterpreter interface to parse and execute SLogo code.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;

import model.commands.Command;
import model.commands.CommandFactory;
import model.commands.Constant;
import model.commands.Function;
import model.commands.ListCommand;
import model.commands.regular_operations.Repeat;
import model.commands.visualizer_operations.MakeUserInstruction;
import view.Visualizer;

public class SLogoCodeInterpreter implements CodeInterpreter {
	private static final Pattern commandMatch = Pattern.compile("[a-zA-Z_]+(\\?)?");
	private static final Pattern constantMatch = Pattern.compile("-?[0-9]+\\.?[0-9]*");
	private static final Pattern variableMatch = Pattern.compile(":[a-zA-Z_]+");
	public static final String RESOURCE_PATH = "resources/languages/";
	public static final String DEFAULT_LANGUAGE = "English"; 
	
	public static final String START_COMMENT = "#";
	public static final String START_LIST = "[";
	public static final String END_LIST = "\\]";
	public static final String START_GROUP = "(";
	public static final String END_GROUP = "\\)"; 
	

	private CommandFactory commandGenerator;
	private Dictionary myDictionary;
	private Scanner codeReader;
	private TurtleTracker myTurtles;
	private Visualizer myVis;
	/**
	 * Initializes a new Dictionary with English as the default language
	 * @param vis the Visualizer to be used for these commands
	 */
	public SLogoCodeInterpreter(Visualizer vis) {
		initializeDictionary();
		myVis = vis;
		myTurtles = new TurtleTracker(vis);
		myTurtles.add();
		myTurtles.get(0).setActive(true);
		myTurtles.get(0).makeVisible();
		commandGenerator = new CommandFactory();
		try {
			setProperties(DEFAULT_LANGUAGE);
		} catch (IOException e) {
			throw new SLogoException("Default language resource missing");
		}
	}
	/**
	 * initializes the specific Dictionary; in this case, the SLogoDictionary
	 */
	public void initializeDictionary() {
		myDictionary = new SLogoDictionary();
	}

	/**
	 * Executes the commands represented by the String array input
	 * @param String array of commands to be executed
	 */
	@Override
	public void execute(String[] command) throws SLogoException {
		String filteredString = filterComments(command);
		codeReader = new Scanner(filteredString);
		while (codeReader.hasNext()) {
			Command c = parseCommand(codeReader.next());
			c.run();
		}

	}

	/**
	 * Removes all comments from the String array and returns the result as a String
	 * Comments start from the first instance of the START_COMMENT string in a line.
	 * @param initial the String array of commands
	 * @return the commentless commands
	 */
	private String filterComments(String[] initial) {
		StringBuilder answer = new StringBuilder();
		for (int a = 0; a < initial.length; a++) {
			String potential = initial[a];
			int index = potential.indexOf(START_COMMENT);
			if(index != -1)
				potential = potential.substring(0,index);
			if (!potential.trim().isEmpty())
				answer.append(potential + " ");
		}
		return answer.toString();
	}

	/**
	 * Finds the Command associated with the String and returns it.
	 * @param s the String to be parsed
	 * @return the command corresponding to the input String
	 */
	private Command parseCommand(String s) {
		if (constantMatch.matcher(s).matches()) {
			return new Constant(Double.parseDouble(s));
		}
		if (variableMatch.matcher(s).matches()) {
			return myDictionary.getVariable(s);
		}
		if (s.equals(START_LIST)) {
			return createListCommand();
		}
		if(s.equals(START_GROUP)) {
			Command c = getCommandOrFunction(codeReader.next());
			if(c.howManyArguments() <= 0) {
				throw new SLogoException("Attempting to group command that takes no arguments");
			}
			createGroupCommand(c); 
			return c;
		}
		return findTrueCommand(s);
	}

	/**
	 * Returns a ListCommand corresponding to the contents of a list
	 * @return ListCommand corresponding to the commands contained within the brackets
	 */
	private Command createListCommand(){
		List<Command> commands = new ArrayList<>();
		while(!codeReader.hasNext(END_LIST)) {
			try {
				String s = codeReader.next();
				commands.add(parseCommand(s));
			}
			catch(NoSuchElementException n) {
				throw new SLogoException("Unclosed Bracket.");
			}
		}
		codeReader.next();
		return new ListCommand(commands);
	}
	/**
	 * Adds all arguments to the root command of a group
	 */
	private void createGroupCommand(Command root){
		if(codeReader.hasNext(END_GROUP))
			throw new SLogoException("No arguments specified in grouping.");
		while(!codeReader.hasNext(END_GROUP)) {
			try {
				handleSpecialCases(root);
				String s = codeReader.next();
				root.addChild(parseCommand(s));
			}
			catch(NoSuchElementException n) {
				throw new SLogoException("Unclosed Parenthesis.");
			}
		}
		codeReader.next();
	}
	/**
	 * Makes the command or function corresponding to the string and adds arguments as necessary
	 * @param s the parsed String
	 * @return Command the Command or Function corresponding to the string
	 * @throws SLogoException
	 *             if invalid command or expression is found
	 */
	public Command findTrueCommand (String s)
	{
		Command c = getCommandOrFunction(s);
		handleSpecialCases(c);
		for(int a = 0; a < c.howManyArguments(); a++) {
			try {
				c.addChild(parseCommand(codeReader.next()));
			}
			catch(NoSuchElementException e) {
				throw new SLogoException("Insufficient arguments for function/command " + s);
			}
		}
		return c;
	}
	/**
	 * Creates the base Command or Function corresponding to the input string
	 * @param s the input string
	 * @throws SLogoException if input string does not match a command or function
	 * @return c the corresponding input or function
	 */
	private Command getCommandOrFunction(String s) {
		Command c;
		if (myDictionary.getCommand(s) != null) {
			try {
				c = commandGenerator.create(myDictionary.getCommand(s),myTurtles, myVis);
			} catch (ClassNotFoundException e) {
				throw new SLogoException("Invalid command entry found: " + s);
			}
		}
		else if(commandMatch.matcher(s).matches()) {
			c = myDictionary.getFunction(s);
		} 
		else {
			if(Pattern.compile(END_GROUP).matcher(s).matches())
				throw new SLogoException("End of grouping command reached with insufficient arguments for a function.");
			if(Pattern.compile(END_LIST).matcher(s).matches())
				throw new SLogoException("End of list reached with insufficient arguments for a function.");
			throw new SLogoException("Invalid expression found in parsing: " + s);
		}
		return c;
	}
	/**
	 * adds special child Commands to the command as necessary
	 * @param c the input Command
	 */
	private void handleSpecialCases(Command c) {
		if(c instanceof Repeat) {
			c.addChild(myDictionary.getVariable(Repeat.DEFAULT_VAR_NAME));
			c.addChild(parseCommand(codeReader.next()));
		}
		else if(c instanceof MakeUserInstruction) {
			String following;
			String following2;
			try {
				following = codeReader.next();
				following2 = codeReader.next();
			}
			catch(NoSuchElementException e) {
				throw new SLogoException("Insufficient arguments for function definition.");
			}
			Function f;
			if(commandMatch.matcher(following).matches()) {
				f = myDictionary.getFunction(following);
			}
			else throw new SLogoException("Attempted to define invalid function.");
			Command col = parseCommand(following2);
			c.addChild(f);
			f.replaceArg(col);
		}
	}
	/**
	 * implements setProperties for the SLogo class
	 */
	@Override
	public void setProperties(String syntax) throws IOException {
		PropertiesReader s = new PropertiesReader();
		myDictionary.setLanguage(s.getProperties(RESOURCE_PATH + syntax));
	}
}
