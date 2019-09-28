# API_REVIEW

## Part 1

### What about your API / design is intended to be flexible?
HY115
* Generic method called update state, front end draws the lines, backend only updates position
* Front end draws all lines, updates position of turtle
* Use different properties files for languages and commands

### How is your API/design encapsulating your implementation decisions?
HY115
* One update state method tells the turtle what to do, the front end calculates the rest
* Different from mine, as the front end is not doing any of the calculations

### What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
HY115
* Only able to choose language files that exist
* Backend will send exceptions for syntax, error box will pop up

### Why do you think your API/design is good (also define what your measure of good is)?
HY115
* Mostly internal API rather than external to prevent dependencies and exceptions
* Only 1 or 2 methods between front and back end
* All about reducing dependencies between the two


## Part 2

### How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
* Model view (implied controller) is already being implemented
* Front end doesn't have many more useful design patters

### What feature/design problem are you most excited to work on?
* Languages and using language files to change GUI languages4
* Hemanth excited about working with back end side of things more

### What feature/design problem are you most worried about working on?
* Worried about stray exceptions that are very common in GUIs
* Hemanth worried about front end turtle/back end turtle dependencies

### Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
* Change language
Button is clicked to change language, and a language file is chosen. The GUI object would relay this new info to each element, requesting them to change their label language. The information is relayed to the back end through the button handler.
* A valid command is input in the command line
The string is relayed to the back end, and the command is displayed in the console. An exception is looked for, but not caught, from the back end. The turtle is updated and any lines are drawn.
* An invalid command is input
An exception is caught from the back end, and the exception method is printed to the console.
* Change background color
The button prompts a color chooser tool, which relays the new color choice to the visualizer.
* Change pen color
The new pen color is relayed to the visualizer, which changes its lines to the correct color.