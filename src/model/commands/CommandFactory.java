package model.commands;

import model.TurtleTracker;
import view.Visualizer;

/**
 * 		create method called by CodeInterpreter class to create
 *         command methods. A string with the command name is sent, and a turtle
 *         is sent.
 */

public class CommandFactory {
	
	public Command create(String className, TurtleTracker t, Visualizer v) throws ClassNotFoundException {

		Class<?> clazz;
		Command command;
		try {
			clazz = Class.forName("model.commands.turtle_commands." + className);
			command = (Command) clazz.getConstructor(t.getClass()).newInstance(t);
		} catch (Exception e) {
			try {
				clazz = Class.forName("model.commands.visualizer_operations." + className);
				command = (Command) clazz.getConstructor(Visualizer.class).newInstance(v);
			} catch (Exception e2) {
				try {
					clazz = Class.forName("model.commands.regular_operations." + className);
					command = (Command) clazz.getConstructor().newInstance();
				} catch (Exception e3) {
					System.out.println(className);
					throw new IllegalArgumentException("Command doesn't exist");
				}
			}
		}
		return command;
	}
}
