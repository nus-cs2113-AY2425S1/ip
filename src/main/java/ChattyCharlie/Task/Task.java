package ChattyCharlie.Task;

import ChattyCharlie.CommandType;

//TASK CLASS
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected CommandType type;

    //constructor
    public Task(String description, CommandType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    //check if its marked as done
    public String getMarkedStatus() {
        return (isDone ? "X" : " ");
    }

    public boolean getIsDoneStatus() {
        return this.isDone;
    }

    public CommandType getType() {
        return type;
    }

    public String getDescription() {
        return this.description;
    }

    //to toggle the task
    public void markTask() {
        this.isDone = true; //change the variable
    }

    public void unmarkTask() {
        this.isDone = false; //change the variable
    }

    public String toString() {
        return "[" + getMarkedStatus() + "] " + description;
    }
}