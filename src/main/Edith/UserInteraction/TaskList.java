package UserInteraction;

import TaskTypes.*;

import java.util.ArrayList;

import static UserInteraction.Storage.*;
import UserInteraction.Storage.*;
import static UserInteraction.PrintShape.printHorizontalLine;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTaskToList(String enteredString, Storage storage) {
        try {
            Task newTask = addNewTask(enteredString);
            tasks.add(newTask);
            storage.addTaskToFile(newTask);
            System.out.println("Got it. I've added this task: ");
            System.out.println(newTask);
            printNumberOfTasks();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid format for input. Please try again.");
        } catch (TaskTypeException e) {
            System.out.println(e); // "No task type stated. Please try again."
        } catch (Exception e) {
            System.out.println("Error. Please try again.");
        } finally {
            printHorizontalLine();
        }
    }

    public void changeTaskStatus(String enteredString, Storage storage) {

        try {
            Task currentTask = getTask(enteredString);
            if (enteredString.contains("unmark")) {
                unmarkTask(currentTask);
            } else if (enteredString.contains("mark")) {
                markTask(currentTask);
            }
        } catch (NumberFormatException e) {
            System.out.println("Task Number not found ");
        } catch (InvalidTaskNumberException e) {
            System.out.println(e.getMessage());
        } finally {
            storage.rewriteArrayList(tasks);
            printHorizontalLine();
        }

    }

    public static void unmarkTask(Task task) {
        task.setIsDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }
    public static void markTask(Task task) {
        task.setIsDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public static Task addNewTask(String enteredString) throws TaskTypeException, StringIndexOutOfBoundsException, Exception {
        Task newTask;
        if (enteredString.startsWith("deadline")) {
            newTask = createDeadlineTask(enteredString);
        } else if (enteredString.startsWith("event")) {
            newTask = createEventTask(enteredString);
        } else if (enteredString.startsWith("todo")) {
            newTask = createTodoTask(enteredString);
        } else {
            throw new TaskTypeException("Invalid input format. Please try again");
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

    public void deleteTask(String enteredString, Storage storage) {
        try {
            Task removedTask = tasks.remove(getTaskNumber(enteredString) - 1);
            storage.rewriteArrayList(tasks);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            printNumberOfTasks();
        } catch (NumberFormatException e) {
            System.out.println("Task Number not found ");
        } catch (InvalidTaskNumberException e) {
            System.out.println(e.getMessage());
        } finally {
            printHorizontalLine();
        }
    }

    public boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber >= 1 && taskNumber <= tasks.size());
    }

    public boolean isInvalidTaskNumber(int taskNumber) {
        return (!isValidTaskNumber(taskNumber));
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
            printHorizontalLine();
            return;
        }
        try {
            System.out.println("Here are the tasks in your list: ");
            int serialNumber = 0;
            for (Task task : tasks) {
                serialNumber++;
                System.out.println(serialNumber + "." + task.toString());
            }
            printHorizontalLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
            printHorizontalLine();
            return;
        }
        try {
            System.out.println("Here are the matching tasks in your list:");
            int serialNumber = 0;
            for (Task task : tasks) {
                serialNumber++;
                System.out.println(serialNumber + "." + task.toString());
            }
            printHorizontalLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Task getTask(String enteredString) throws NumberFormatException, InvalidTaskNumberException {
        int taskNumber = getTaskNumber(enteredString);
        Task task = tasks.get(taskNumber - 1);
        return task;
    }

    public int getTaskNumber(String enteredString) throws NumberFormatException, InvalidTaskNumberException {
        int lengthOfString = enteredString.length();
        String taskNumberString = enteredString.substring(lengthOfString - 1);
        int taskNumber = Integer.parseInt(taskNumberString);
        if (isInvalidTaskNumber(taskNumber)) { //can add an exception here
            throw new InvalidTaskNumberException("Invalid task number. Please try again.");
        }
        return taskNumber;
    }

    public void printNumberOfTasks() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void findTask(String searchItem) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {

        }
    }
}
