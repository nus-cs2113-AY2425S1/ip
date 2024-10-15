# Yukino User Guide



Yukino is a customised chat bot that assits you in your daily time and event management. You can add different types of tasks to Yukino and Yukino will take note for you! But also remember to check your tasks here from time to time.

## Adding todos

Example: `todo [content]`

expected output:   added:[content]
                   Now you have [number of tasks] tasks

## Adding deadlines

Example: `deadline [content] /by [end time]`

expected output:   added:[content]
                   Now you have [number of tasks] tasks

## Adding events

Example: `event [content] /from [start time] /by [end time]`

expected output:   added:[content]
                   Now you have [number of tasks] tasks

## Delete item

Example: `delete [content]`

expected output:   You have successfully deleted this task

if not found:      Sorry, you are marking an event that has not been added

Note: this command will only delete one task from the list

## Automatic saving and retrieving

This method is automatically called so you do not need to enter any command.
This method is to save the data into a data file called: YukinoData.txt.
This file should locate at the same directory with the jar file.
WARNING: Changing the data file manually without aligning with the data format will cause unexpected consequences!