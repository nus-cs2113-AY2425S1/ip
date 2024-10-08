# Erika Chatbot

```angular2html
	Hello from
 _______   ________  ___  ___  __    ________     
|\  ___ \ |\   __  \|\  \|\  \|\  \ |\   __  \    
\ \   __/|\ \  \|\  \ \  \ \  \/  /|\ \  \|\  \   
 \ \  \_|/_\ \   _  _\ \  \ \   ___  \ \   __  \  
  \ \  \_|| \ \  \\  \\ \  \ \  \\ \  \ \  \ \  \ 
   \ \_______\ \__\\ _\\ \__\ \__\\ \__\ \__\ \__\
    \|_______|\|__|\|__|\|__|\|__| \|__|\|__|\|__|

	____________________________________________________________
	Hello! I'm Erika
 	What can I do for you?
	____________________________________________________________

```


## Add ```Todos```, ```Deadlines``` and ```Events```

Add ```Tasks``` to your list of tasks via CLI

Example: `todo/deadline/event DESCRIPTION [optional parameters]`

### Todo:
```angular2html
todo mytodo
	____________________________________________________________
	Got it. I've added this task:
	  [T][ ] mytodo
	Now you have 1 task in the list.
	____________________________________________________________
```
### Deadline:
```angular2html
deadline mydeadline /by 19/09/2024 23:59
____________________________________________________________
Got it. I've added this task:
[D][ ] mydeadline (by: 19 Sep 2024 11.59PM)
Now you have 3 tasks in the list.
____________________________________________________________
```
### Event:
```angular2html
event myevent /from 19/09/2024 23:59 /to 20/09/2024 23:59
	____________________________________________________________
	Got it. I've added this task:
	  [E][ ] myevent (from: 19 Sep 2024 11.59PM to: 20 Sep 2024 11.59PM)
	Now you have 4 tasks in the list.
	____________________________________________________________

```
## Delete ```Tasks```

```angular2html
delete 1
	____________________________________________________________
	Nice! I've deleted this task:
	[T][ ] mytodo
	Now you have 3 tasks in the list.
	____________________________________________________________
```
```angular2html
delete all
	____________________________________________________________
	All tasks deleted. There are now 0 tasks
	____________________________________________________________
```

## Search for ```Tasks```
```angular2html
find todo
	____________________________________________________________
	Here are the matching items in your list: 
	1. [T][ ] mytodo
	2. [T][ ] myothertodo
	3. [T][ ] todo!
	____________________________________________________________
```
## Marking and Unmarking ```Tasks```

```angular2html
mark 1
	____________________________________________________________
	Nice! I've marked this task as done:
	[T][X] mytodo
	____________________________________________________________
```
```angular2html
unmark 1
	____________________________________________________________
	Nice! I've marked this task as not done yet:
		[T][ ] mytodo
	____________________________________________________________
```

## Acknowledgements
I would like to thank @jinzihan2002 for help with smoke testing. 

