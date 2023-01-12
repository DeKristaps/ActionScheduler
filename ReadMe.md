<h1>Action scheduler</h1>

This is an Action scheduler that runs in a configurable interval, and can read a CVS File containing a time in HH: MI format, and a mask determining on which days the action should be made. For instance 1 in binary equals 1 so the task would run on Mondays while 5 equals 101 so the task would run on Mondays and Wednesdays

The program can be configured with:

* Zone offset for checking times in different countries.
* A run interval in ms (after which the program opens the file again and checks the days and times listed).
Any file location.
* The program can be configured in the application.properties file.

After hitting run the program executes immediately and then only after the specified interval

To run the program you must first set the file path in the application.properties file.

At the moment the program will only print - "It's time to do the action" when time and days match the CSV,
but it can be modified to make any method call. 

To do this, changes need to be made in the highlighted area. 

<img src="ReadMePic/Screen.PNG">