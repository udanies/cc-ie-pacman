Pacman Simulator
===================

The application is a simulation of a Pacman moving on a grid,
of dimensions 5 units x 5 units.

There are no other obstructions on the grid.
Pacman is free to roam around the surface, but any movement 
that would result in Pacman falling from the grid will be prevented, 
however further valid movement commands will still be allowed.


Getting Started
---------------

### Prerequisites
    Java JDK 10.0.2
    Apache Maven 3.5.4

### Building
    In a command prompt, navigate to the cc-ie-pacman folder.
    Run the following command to build the executable .jar file.
        mvn clean install

### Running the Simulation
    In a command prompt, run the jar file to check if the build was successful.
        java -jar target/ie-pacman-1.0.0.jar
    The welcome message will be displayed, enter some commands or 'quit' to exit.
    See below for valid commands and examples.


Commands to Pacman
---------------------
 - PLACE X,Y,F - places Pacman at the given X,Y coordinates facing NORTH,
                 EAST, SOUTH, or WEST. 
                 E.g.: PLACE 2,3,EAST
 - MOVE        - moves one position in the current direction
 - LEFT        - turns 90 degrees to the left from the current direction
 - RIGHT       - turns 90 degrees to the right from the current direction
 - REPORT      - displays the current position and direction
 - QUIT        - to exit the simulation

**Notes**:
 * Coordinates 0,0 is the SOUTH WEST corner of the grid.
 * All commands before correctly placing Pacman are ignored.
 * All malformed commands are ignored and does not affect the current position of Pacman.
 * Commands are not case sensitive.


Example Input and Output
------------------------

### Example a

    PLACE 0,0,NORTH
    MOVE
    REPORT

Expected output:

    0,1,NORTH

### Example b

    PLACE 0,0,NORTH
    LEFT
    REPORT

Expected output:

    0,0,WEST

### Example c

    PLACE 1,2,EAST
    MOVE
    MOVE
    LEFT
    MOVE
    REPORT

Expected output:

    3,3,NORTH


