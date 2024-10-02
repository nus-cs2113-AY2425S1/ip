package UserInteraction;

import TaskTypes.*;

public class CreateTask {

    public static Task addNewTask(String enteredString) throws TaskTypeException, StringIndexOutOfBoundsException, Exception {
        Task newTask;
        if (enteredString.startsWith("deadline")) {
            newTask = createDeadlineTask(enteredString);
        } else if (enteredString.startsWith("event")) {
            newTask = createEventTask(enteredString);
        } else if (enteredString.startsWith("todo")) {
            newTask = createTodoTask(enteredString);
        } else {
            throw new TaskTypeException("No task type stated. Please try again.");
        }

        return newTask;
    }

    public static Deadline createDeadlineTask(String enteredString) throws StringIndexOutOfBoundsException {
        int lengthOfBy = "by".length();
        int lengthOfDeadline = "deadline".length();
        int indexOfBy = enteredString.indexOf("by");

        String taskDescription = enteredString.substring(lengthOfDeadline, indexOfBy - 1);
        String deadlineTime = enteredString.substring(indexOfBy + lengthOfBy + 1);
        return new Deadline(taskDescription, deadlineTime);
    }

    public static Event createEventTask(String enteredString) throws StringIndexOutOfBoundsException {
        int lengthOfEvent = "event".length();
        int lengthOfFrom = "from".length();
        int lengthOfTo = "to".length();
        int indexOfFrom = enteredString.indexOf("from");
        int indexOfTo = enteredString.indexOf("to ");

        String taskDescription = enteredString.substring(lengthOfEvent, indexOfFrom - 1);
        String eventFromTime = enteredString.substring(indexOfFrom + lengthOfFrom + 1, indexOfTo);
        String eventToTime = enteredString.substring(indexOfTo + lengthOfTo + 1);

        return new Event(taskDescription, eventFromTime, eventToTime);
    }

    public static ToDo createTodoTask(String enteredString) throws StringIndexOutOfBoundsException {
        int lengthOfTodo = "todo".length();

        String taskDescription=enteredString.substring(lengthOfTodo);

        return new ToDo(taskDescription);
    }
}
