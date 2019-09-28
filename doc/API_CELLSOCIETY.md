# Slogo Team02 API Exercise
Examining CellSociety Team 8's API

Write a simplified (i.e., less than one page) description of the _six_ APIs in the project (internal and external for each sub-part: simulation, configuration, and visualization) 


## Simulation

### Internal
Internal API was passing information between the Cell Controllers and the Cells. Cell Controllers called methods on cells to update to their new states, and get new states.
### External
The external API was used to pass information to the Visualisation sub-part. Methods were mostly getters of information for the visualisation part to display.


## Configuration
The Configuration sub-part was implemented in the xml package, which contains the XMLReader and XMLWriter classes.
### Internal
There's not much of an internal API needed because the classes serve completely independent functions and it is unlikely that any extensions will be made of the included processes.
### External
In the XMLReader, all getter methods are part of the API. This is so that the Simulation can retrieve the data parsed in from the file.



## Visualization
The Visualization sub-part was implemented in the front-end package, which controls the application, GUI, and button action events.
### Internal
All of the visualisation parts were dependent on each other and needed to call each other's methods to receive information on what to display. 
### External
There is pretty much no external visualisation API. Visualisation is the sub-part that uses the other APIs