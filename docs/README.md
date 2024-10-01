# Echo User Guide

## Product Screenshot:
![img.png](img.png)

## Product intro:
This project is part of CS2113 iP component. The project
is about a chatbot called Echo. He is a Task management
chatbot that is able to keep track of different tasks,
mark them as done or undone and even delete the tasks.
When user enters and leaves the CLI, Echo will also load
and save the contents where appropriate.

## Adding deadlines

Allow users to key in deadline task description along with the due date
(due date is accepted only in 3 formats: 
yyyy-MM-dd, d/M/yyyy, and MMM dd yyyy)
and outputs by adding it into the list

Example: `deadline return book /by 2/12/2019` or `deadline return book /by Dec 02 2019`
or `deadline return book /by 2019-12-02`

Description of expected output:
```
______________________________
deadline return book /by 2019-12-02
______________________________
Got it. I've added this task:
[D] [ ] return book (by: Dec 12 2019)
Now you have 7 tasks in the list.
______________________________
```

## Adding todo

Allow users to key in a todo task description and outputs by adding it into the list

Example: `todo Joe`

Description of expected output:
```
______________________________
todo Joe
______________________________
Got it. I've added this task:
[T] [ ] Joe
Now you have 8 tasks in the list.
______________________________
```

## Adding event

Allow users to key in an event task description with the start time and end time
and outputs by adding it into the list

Example: `event lecture /from 4pm /to 6pm`

Description of expected output:
```
______________________________
event lecture /from 4pm /to 6pm
______________________________
Got it. I've added this task:
[E] [ ] lecture (from: 4pm to: 6pm)
Now you have 9 tasks in the list.
______________________________
```

## Listing task

Allow users to see the information on the tasks that is stored in the task list and
outputs the task list

Example: `list`

```
______________________________
list
______________________________
Here are the tasks in your list:
1. [T] [X] Joe
2. [E] [ ] lecture (from: 4pm to: 6pm)
3. [D] [ ] return book (by: Dec 02 2019)

______________________________
```

## Deleting task

Allow users to input index of task to be removed from the list and update the
new total number of task in the list and outputs the task that is deleted.

Example: `delete 4`

```
______________________________
Here are the tasks in your list:
1. [T] [X] Joe
2. [E] [ ] lecture (from: 4pm to: 6pm)
3. [D] [ ] return book (by: Dec 02 2019)
4. [T] [X] finding books

______________________________
delete 4
______________________________
Noted. I've removed this task:
[T] [X] finding books
Now you have 3 tasks in the list.
______________________________
list
______________________________
Here are the tasks in your list:
1. [T] [X] Joe
2. [E] [ ] lecture (from: 4pm to: 6pm)
3. [D] [ ] return book (by: Dec 02 2019)

______________________________
```

## Marking task

Allow users to key in index of the task to be set a task as done 
by marking the task list with an 'X' and the saved file with a '1' and
outputs the task that is marked 

Example: `mark 2`

// A description of the expected outcome goes here

```
______________________________
Here are the tasks in your list:
1. [T] [X] Joe
2. [E] [ ] lecture (from: 4pm to: 6pm)
3. [D] [ ] return book (by: Dec 02 2019)

______________________________
mark 2
______________________________
Nice! I've marked this task as done:
[E] [X] lecture (from: 4pm to: 6pm)
______________________________
list
______________________________
Here are the tasks in your list:
1. [T] [X] Joe
2. [E] [X] lecture (from: 4pm to: 6pm)
3. [D] [ ] return book (by: Dec 02 2019)

______________________________
```

## Unmarking task

Allow users to key in index of the task to be reset as undone
by unmarking the task list with an ' ' and the saved file with a '0' and 
outputs the task that is unmarked

Example: `unmark 2`

```
______________________________
Here are the tasks in your list:
1. [T] [X] Joe
2. [E] [X] lecture (from: 4pm to: 6pm)
3. [D] [ ] return book (by: Dec 02 2019)

______________________________
unmark 2
______________________________
OK, I've marked this task as not done yet:
[E] [ ] lecture (from: 4pm to: 6pm)
______________________________
list
______________________________
Here are the tasks in your list:
1. [T] [X] Joe
2. [E] [ ] lecture (from: 4pm to: 6pm)
3. [D] [ ] return book (by: Dec 02 2019)

______________________________
```

## Finding task

Allow users to look for a task based on a given keyword and outputs a list of task 
which contains the given keyword in their description

Example: `find book`

```
______________________________
Here are the tasks in your list:
1. [T] [X] Joe
2. [E] [ ] lecture (from: 4pm to: 6pm)
3. [D] [ ] return book (by: Dec 02 2019)

______________________________
find book
______________________________
Here are the matching tasks in your list:
1. [D] [ ] return book (by: Dec 02 2019)

______________________________
```

## Loading task

Allow users to automatically restore tasks which was saved prior to exiting
the chatbot in a `.txt` file, outputs the stored list of task after
re-entering the chatbot

### No input needed

```
______________________________
list
______________________________
Here are the tasks in your list:
1. [T] [X] Joe
2. [E] [ ] lecture (from: 4pm to: 6pm)
3. [D] [ ] return book (by: Dec 02 2019)
______________________________
bye
______________________________
Bye. Hope to see you again soon!
______________________________

Hello! I'm Echo
What can I do for you?

______________________________
list
______________________________
Here are the tasks in your list:
1. [T] [X] Joe
2. [E] [ ] lecture (from: 4pm to: 6pm)
3. [D] [ ] return book (by: Dec 02 2019)

______________________________
```

## Saving task

Allow users to automatically save tasks right before exiting the chatbot and
outputs the stored list of task in a `.txt` file

### No input needed

From CLI:
```
______________________________
Here are the tasks in your list:
1. [T] [X] Joe
2. [E] [ ] lecture (from: 4pm to: 6pm)
3. [D] [ ] return book (by: Dec 02 2019)

______________________________

```

From `.txt` file:
```
T | 1 | Joe
E | 0 | lecture (from: 4pm to: 6pm)
D | 0 | return book (by: Dec 02 2019)
```