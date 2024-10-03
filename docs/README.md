# Bean User Guide
![Product screenshot](/images/product_screenshot.png)



## Product Intro
Stay organized and productive with Bean, the easy-to-use task management app.

Bean helps you:

- Create and manage tasks: Easily add, edit, and delete tasks to keep track of your to-do list.
- Set deadlines: Assign due dates to your tasks to stay on top of your schedule.
- Categorize tasks: Organize your tasks into different categories for better management.
- Mark tasks as complete: Keep track of your progress and celebrate your achievements.

With Bean, you can streamline your workflow, increase your productivity, and achieve your goals more effectively.



## Adding todos
**Command: ** `todo [description]`
### Example:
```
todo run 10km
```
This will create a new task with description "run 10km".
### Expected output:
```
____________________________________________________________
  Added task: 'run 10km'!
____________________________________________________________
```



## Adding deadlines
**Command: ** `deadline [description] /by [by]`
### Example:
```
deadline submit cs2113 final version /by Fri, Oct 11th 2359
```
This will create a new task with the description "submit cs2113 final version" and a deadline of "Fri, Oct 11th 2359".
### Expected output:
```
____________________________________________________________
  Added task: 'submit cs2113 final version'!
____________________________________________________________
```



## Adding events
**Command: ** `event [description] /from [from] /to [to]`
### Example:
```
event team meeting /from 10:00 AM /to 11:00 AM
```
This will create a new event with the description "team meeting" scheduled from "10:00 AM" to "11:00 AM".
### Expected output:
```
____________________________________________________________
  Added task: 'team meeting'!
____________________________________________________________
```



## Listing tasks
**Command: ** `list`
### Example:
```
list
```
### Expected output:
The list of all tasks, including their status, description, by, from and to. (if applicable).



## Finding tasks by keyword
**Command: ** `find  [keyword]`
### Example:
```
find homework
```
### Expected output:
A list of tasks whose descriptions contain the keyword "homework".
> [!IMPORTANT]
> The keyword input is case-sensitive. `find homework` will return tasks whose description contains 'homework', but not 'Homework'.



## Deleting tasks
**Command: ** `delete [task number]`
### Example:
```
delete 3
```
### Expected output:
```
____________________________________________________________
  Deleted task: [E][ ] dinner (from: 7pm to 9pm).
____________________________________________________________
```
> [!IMPORTANT]
> Ensure that task number is valid to avoid errors.

> [!TIP]
> Use command `list` to check for valid task number range.



## Marking tasks as done
*Command: ** `mark [task number]`
### Example:
```
mark 3
```
### Expected output:
```
____________________________________________________________
  Task 3 has been marked as DONE:
    [D][X] hw (by: 2359)
____________________________________________________________
```



## Unmarking tasks as done
*Command: ** `unmark [task number]`
### Example:
```
unmark 3
```
### Expected output:
```
____________________________________________________________
  Task 3 has been marked as UNDONE:
    [D][ ] hw (by: 2359)
____________________________________________________________
```



## Exit application
*Command: ** `bye`
### Example:
```
bye
```
### Expected output:
```
____________________________________________________________
   .o8                                       
  "888                                       
   888oooo.   .ooooo.   .oooo.   ooo. .oo.   
   d88' `88b d88' `88b `P  )88b  `888P"Y88b  
   888   888 888ooo888  .oP"888   888   888  
   888   888 888    .o d8(  888   888   888  
   `Y8bod8P' `Y8bod8P' `Y888""8o o888o o888o 

  Bye bye, glad I could help! See you soon? :'(
____________________________________________________________
```