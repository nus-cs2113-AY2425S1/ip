# Yukino User Guide



Yukino is a customised chat bot that assits you in your daily time and event management. You can add different types of tasks to Yukino and Yukino will take note for you! But also remember to check your tasks here from time to time.

## Adding todos

Example: `add todo [content]`

expected output:   added:[content]
                   Now you have [number of tasks] tasks

## Adding deadlines

Example: `add deadline [content] /by [end time]`

expected output:   added:[content]
                   Now you have [number of tasks] tasks

## Adding events

Example: `add event [content] /from [start time] /by [end time]`

expected output:   added:[content]
                   Now you have [number of tasks] tasks


Notes: in the above three adding methods, time should be in the form of HH-dd-MM-YYYY, where HH is time, dd is day, MM is month, YYYY is year, all in number form

## List items

Example: `list`


Expected output:   -----------------------------------

                   1. [T][] deadline By: 12-09-09-2009
                   2. [D][X] test By: 12-09-09-2000
                   3. [E][] test2 From: 11-11-11-1111 By: 12-12-12-1212
                   -----------------------------------

if not taks:       -----------------------------------

                   No task found
                   -----------------------------------

Note: there are two boxes for each item. The first is for the type of task; the second is for the marking of the item.

## Find item

Example: `find 11`

Expected output:   1. [T][] 112
                   2. [T][] 113

Note: This method will return all tasks containing the key word searched

## Delete item

Example: `delete [number]`

expected output:   You have successfully deleted this task

if not found:      Sorry, you are deleting an event that has not been added

Note: this command receives the sequential number of the task as parameter. Please use list first

## Automatic saving and retrieving

This method is automatically called so you do not need to enter any command.
This method is to save the data into a data file called: YukinoData.txt.
This file should locate at the same directory with the jar file.
WARNING: Changing the data file manually without aligning with the data format will cause unexpected consequences!