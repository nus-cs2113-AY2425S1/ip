import exception.InvalidCreateDeadlineException;
import exception.InvalidCreateEventException;
import exception.InvalidCreateTaskException;
import exception.InvalidCreateToDoException;

import java.util.ArrayList;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void createNewTask(String userInput) throws InvalidCreateTaskException,
            InvalidCreateToDoException, InvalidCreateDeadlineException, InvalidCreateEventException {
        String[] userInputSplit = userInput.split(" ");
        if (userInputSplit[0].equals("todo") && userInput.length() > 4) {
            ToDo.createNewToDo(userInput.substring(5));
        } else if (userInputSplit[0].equals("event") && userInput.length() > 5) {
            Event.createNewEvent(userInput.substring(6));
        } else if (userInputSplit[0].equals("deadline") && userInput.length() > 8) {
            Deadline.createNewDeadline(userInput.substring(9));
        } else {
            throw new InvalidCreateTaskException();
        }
    }

    public static void deleteTask(int taskIndex) {
        TaskList.tasks.get(taskIndex).printDelete();
        TaskList.tasks.remove(taskIndex);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void printMark() {
        UI.printContent("Nice! You have done this task:\n\t" + this.toString());
    }

    public void printUnmark() {
        UI.printContent("I have unmarked this task:\n\t" + this.toString());
    }

    public void printDelete() {
        UI.printContent("You have deleted this task:\n\t" + this.toString());
    }

    public void markAsDone() {
        this.isDone = true;
        this.printMark();
    }

    public void markAsUndone() {
        this.isDone = false;
        this.printUnmark();
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public String toSaveString() {
        return this.getStatusIcon() + "//" + this.description;
    }
}
