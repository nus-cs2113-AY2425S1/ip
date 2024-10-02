package UserInteraction;

import TaskTypes.*;
import static UserInteraction.PrintShape.printHorizontalLine;

public class CreateTask {

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
        int indexOfTo = enteredString.indexOf("to");

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

    public static Task addNewTask(String enteredString) {
        Task newTask = null;
        try {
            if (enteredString.startsWith("deadline")) {
                newTask = createDeadlineTask(enteredString);
            } else if (enteredString.startsWith("event")) {
                newTask = createEventTask(enteredString);
            } else if (enteredString.startsWith("todo")) {
                newTask = createTodoTask(enteredString);
            } else {
                throw new TaskTypeException("No task type stated. Please try again.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid format for input. Please try again.");
            printHorizontalLine();
        } catch (TaskTypeException e) {
            System.out.println("No task type stated. Please try again.");
            printHorizontalLine();
        } catch (Exception e) {
            System.out.println("Error. Please try again.");
            printHorizontalLine();
        }
            return newTask;
    }
}
