# slogo

A development environment that helps users write SLogo programs.

#### Names:
- Jack Fitzpatrick
- Jeffrey Li
- Andrew Yeung
- Eddie Yang

#### Start Date:
- 2/15/18

#### Finish Date:
- 3/9/18

#### Hours Spent on project:
- 150 hours

#### Resources Used: 
- Lecture content from Professor Duval's 308 lectures, Java documentation

#### Files Used to Start: 
- Run the Main class.

#### Files used to Test: 
- Example code files from the data folder were used to test functionality of the program

#### Data or Resource Files required: 
- The language resources package is required to run the program.
- The image resource folder is also needed.

#### Information about using the program: 
- User can run code with multiple windows and mu

#### Decisions made regarding project requirements:
- Frontend
	- Turtle is now held in the back end, with an ImageView state variable. This is the most interdependent class in this project, as it is directly influenced by the back end, and is shown on the front end. Done to allow back end better turtle control and to allow multiple turtles
- Backend
	- Certain commands run Lists of commands (e.g. Repeat, For). Much as in Java, the brackets can be omitted and the next command will be treated as the command to run.
	- Created turtles start with the pen up, facing upwards, at (0,0). The initial turtle starts active; subsequent created turtles start inactive unless made active by the command that creates them.

#### Known bugs:
- When dragging (and sometimes making) multiple turtles, the graphical position of the turtles is not always aligned with their actual positions. Additionally, lines are not animated.
#### Extra Features included:
- Grouping will apply to any user-defined functions that take input parameters.
- Animation of turtles works. Speed can be controlled, and it can be paused and restarted.