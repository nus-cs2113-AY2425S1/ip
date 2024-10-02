# Bean User Guide
// Product screenshot goes here

## Product Intro
// Product intro goes here

## Adding todos
To add a todo, use the following command:
**Command:** `todo [description]`
### Example:
```
todo run 10km
```
This will create a new task with description "run 10km".
### Expected output:
```
  Added task: 'run 10km'!
```

## Adding deadlines
To add a deadline, use the following command: 
**Command:** `deadline [description] /by [by]`
### Example:
```
deadline submit cs2113 final version /by Fri, Oct 11th 2359
```
This will create a new task with the description "submit cs2113 final version" and a deadline of "Fri, Oct 11th 2359".
### Expected output:
```
  Added task: 'submit cs2113 final version'!
```
