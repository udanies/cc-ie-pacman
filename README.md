Toy Robot Simulator
===================

The application is a simulation of a toy robot moving on a square tabletop,
of dimensions 5 units x 5 units.

There are no other obstructions on the table surface.
The robot is free to roam around the surface, but any movement 
that would result in the robot falling from the table will be prevented, 
however further valid movement commands will still be allowed.


Getting Started
---------------

### Prerequisites
    Java JDK 10.0.2
    Apache Maven 3.5.4

### Building
    In a command prompt, navigate to the toy-robot folder.
    Run the following command to build the executable .jar file.
        mvn clean install

### Running the Simulation
    In a command prompt, run the jar file to check if the build was successful.
        java -jar target/toy-robot-1.0.0.jar
    The welcome message will be displayed, enter some commands or 'quit' to exit.
    See below for valid commands and examples.


Commands to the Robot
---------------------
 - PLACE X,Y,F - places the robot at the given X,Y coordinates facing NORTH,
                 EAST, SOUTH, or WEST. 
                 E.g.: PLACE 2,3,EAST
 - MOVE        - moves one position in the current direction
 - LEFT        - turns 90 degrees to the left from the current direction
 - RIGHT       - turns 90 degrees to the right from the current direction
 - REPORT      - displays the current position and direction
 - QUIT        - to exit the simulation

**Notes**:
 * Coordinates 0,0 is the SOUTH WEST corner of the table.
 * All commands before correctly placing a robot are ignored.
 * All malformed commands are ignored and does not affect the current position of the robot.
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


