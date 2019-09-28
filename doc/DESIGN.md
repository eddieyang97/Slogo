DESIGN.md
===
## High Level Design
This implementation of SLogo is an IDE that properly interprets and displays the results of input code written in SLogo. The program consists of a View, which handles the user interface and the graphical display, and the Model, which translates input code into actions on the Turtle or the surroundings. The View calls the Model through the CodeInterpreter when the user inputs code or selects a file to run; the Model calls the View through the Visualizer to update the display as commands are run.

The project is open to adding more commands and more languages; the basic Read/Execute/Return structure of SLogo and the basic user interface is closed to modification.

## Adding New Features
1. Adding a new command
    * A new command can be added by implementing a new subclass of Command to take the desired number of arguments. TurtleCommand, TurtleAsker, TurtleAction, and VisualizerCommand are all abstract subclasses of command and the new command can choose to extend one of these  subclasses instead. The resources files should also be updated to provide the name of the Command and the regex corresponding to that command.
2. Adding a new Turtle image or color
    * A new image or color can be added by adding the String corresponding to the file name / color name to the list of options in the corresponding palette.
3. Adding a new display to the GUI
    * A new display can be added by creating a new subclass of Element that implements SizingElement and initializing the Element in the GUI. Note that the GUI may need to be tweaked to allow the new display to be shown in a spot that does not impede view of other displays.
4. Adding a new language
    * A new language can be added by adding the corresponding String to the list of available languages in the MenuBar and adding a resource file that contains the translations for all available commands.

## Design Choices
* It was decided that the Turtle should be part of the backend instead of the frontend. This ultimately had the drawback that the Turtle incorporated significant amounts of graphics and coordinated a lot with the front end's Visualizer. However, it had the benefit that the Commands could instruct Turtles to move or rotate without needing to call something in the front end.
* We decided that the View should be responsible for loading files to the TextBox and saving files from the text in the TextBox. Because the actual process of saving and loading plain text is fairly simple, doing this had the benefit of simplifying the API. While file reading and saving was thus delegated to a part of the project that usually does not handle such tasks, this solution allowed text in code files to be previewed (or said text could be modified) before execution.
* It was decided that the Commands individually store their own children, and that Commands were responsible for calling the act() method on their own children, and that all actions (including constants/varables) should be considered Commands to allow inclusion in the tree. This design allows for certain children not to be executed (for example, the For command's first child is a list of a Variable followed by 3 values; these can be retrieved the list itself does not need to be executed). While constants and variables being commands may seem counterintuitive (after all, constants and variables don't really perform actions), this design also allows the Command to get its input values directly, simplifying tree traversal.

## Assumptions
### Backend
* Commands are evaluated for each active turtle. Thus, the instruction "fd set :x + :x 10" will move each active turtle forward a different amount.
* For a tell or ask command, all turtles up to the highest turtle ID specified will be created. Only turtles with the given IDs will be active.
* As in Java, loops can accept a single instruction to loop over instead of a set of instructions enclosed by brackets.
* Grouping works for any command that takes at least one parameter. This includes user-defined functions with parameters.
    * For grouping, the Difference command returns the difference of the first input and all subsequent inputs. The Quotient command returns the quotient of the first input and all subsequent inputs.

### Frontend
* The user can understand English, even if the code that they write is in a different language.
* The console/history only displays successfully run code.
* Palette size is fixed.
* Palettes will automatically hide themselves and the Visualizer/textbox/console will expand if neither the FunctionDisplay, VariableDisplay, nor TurtleDisplay is showing. The reverse will occur once one of those displays reshows itself.