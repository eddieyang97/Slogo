# Slogo Team02 API Slogo

## SLogo Architecture Design

### 1.  When does parsing need to take place and what does it need to start properly?

Parsing needs to take place when the language files are read, when the example code files are read, and when the user types a command. The code parsing needs to have a specific language set and a nonempty String of commands to run properly. The file parsing needs a valid file.

### 2.  What is the result of parsing and who receives it?

Parsing the language files makes a map of what inputs result in what commands. Parsing the user typed input commands use the map to call the appropriate commands.

### 3.  When are errors detected and how are they reported?

Errors are detected when a file is uploaded, and when a the user hits enter after typing a command. An incorrect file or incorrect command results in an error message on the GUI.

### 4.  What do commands know, when do they know it, and how do they get it?

Commands are already installed as methods in the turtle class. There will be a map that maps user inputs to the relevant command. 

### 5.  How is the GUI updated after a command has completed execution?

The back end gives information regarding state of simulation to the GUI.


## APIs

### View
The View part will handle the GUI, text input, and visualization of the turtle. It will also handle loading files, menus, and other user interactions.

#### External
The external API of View will update the turtle visualization based on the model's calculations. <br/>
The model calls the external API (e.g. a drawLine(...) method) to update what gets displayed after each command, if the command changed the state of the turtle.

#### Internal



### Model
The Model will be responsible for handling any file reading and is responsible for updating the Turtle based on the commands.
#### External
The external API of Model will allow files to be read, resources to be loaded, and command text to be parsed. These methods will be called by the View to change the state of the turtle.
#### Internal
The internal API of Model will read and execute the given commands, updating the turtle as necessary.