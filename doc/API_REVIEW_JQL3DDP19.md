# SLogo API Review

## Part 1

### What about your API/design is intended to be flexible?

By using a factory that uses reflection, if a new command needs to be added, you only need to add a new command subclass.

It works on any number of turtles.

### How is your API/design encapsulating your implementation decisions?

As long as it takes in the input and returns the output, it doesn't matter what goes on in the implementation.

### What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

If the file is not found, a filenotfoundexception is thrown. 

### Why do you think your API/design is good (also define what your measure of good is)?

It allows each person in the team to know what other people's implementations will do, but no t worry about how it does it. It summarises each members role in the group, and highlights gaps in functionality between parts.

## Part 2

### How do you think Design Patterns are currently represented in the design or could be used to help improve the design?

The way the commands are implemented is a factory design pattern.
The whole project has a model - view - controller pattern.

### What feature/design problem are you most excited to work on?

Making all the different command sub classes.

### What feature/design problem are you most worried about working on?

I'm worried that I'm gonna have to help the code parser dude figure out the code logic

### Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).

1) a new command is to be added: simply make a new command subclass with the appropriate act method, then add it into the resource files

2) To move the turtle forward: my factory would be passed a "moveForward" string and a moveForward Command could be created. the act method would be called on this moveForward object which would change the coordinates of the Turtle object. Then the turtle object updates the visualisation.



