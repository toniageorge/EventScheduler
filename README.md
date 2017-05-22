# EventScheduler(maven project)
Deloitte Digital is rewarding their employees in recognition for their hard work for range of successful projects by organising a "Deloitte Digital Away Day". Due to high demand across the firm a number of activities were proposed and a selection of them have been approved. Given there are time constraints, you have been asked to help event organisers by writing a program to accommodate various activities for the day. Organisers have provided you with the following useful information: 
1. The employees will be divided into various teams and each team will be performing various activities
2. Activities start from 9am until lunch is served at 12 noon
3. Activities resume from 1pm and must finish in time for a presentation to be delivered by external speaker on “Staff Motivation"
4. The presentation can start no earlier than 4:00 and no later than 5:00
5. All activity lengths are either in minutes (not hours) or sprint (15 minutes)
6. Due to high spirit of the team there needs to be no gap between each activity 

Assumptions:
1.	On the input file, each event is separated by new line
2.	Event description and time are separated by whitespace
3.	Last event is the presentation on ‘Staff Motivation’
4. The path to the input file is given as a run-time argument
   Output:
5.	Application will be console-based and the list of scheduled events will be displayed on console.
6.	In addition a ‘text’ file will be created in the same directory of the input file The name will be name of the inputfile+_result.txt         

Steps to run:
1.  Build the maven project
2.	Copy the jar file to a folder
3.	Create the input file and save it in a folder(give this path while executing the jar)
4.	Open command prompt and navigate to the jar folder
5.	Execute command  java  –jar EventScheduler-0.0.1.jar {file path} 

