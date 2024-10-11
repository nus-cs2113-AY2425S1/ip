package userinteraction;

import tasktypes.Deadline;
import tasktypes.Task;
import tasktypes.TaskTypeException;
import tasktypes.ToDo;
import tasktypes.Event;
import java.util.ArrayList;

import static userinteraction.PrintShape.printHorizontalLine;

/**
 * It represents the list of tasks. It contains methods relating to operations on the actual list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Uses the user input to create a Task object which is added
     * to the existing list of objects and subsequently written to file.
     * It also deals with all exceptions thrown.
     *
     * @param enteredString The user input
     * @param storage The storage object which is used to write to the file
     */
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

    /**
     * Modifies the completion status of the task, both in the list of tasks
     * and in the file, by calling the appropriate methods
     * It also deals with the exceptions thrown.
     *
     * @param enteredString The user input
     * @param storage The storage object which is used to write to the file
     */
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

    /**
     * Updates the completion status of the task to not completed.
     *
     * @param task The Task whose isDone status is to modified
     */
    public static void unmarkTask(Task task) {
        task.setIsDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Updates the completion status of the task to completed.
     *
     * @param task The Task whose isDone status is to modified
     */
    public static void markTask(Task task) {
        task.setIsDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Returns the Task which was created bu itself.
     * Determines which type of task is to be created and
     * invokes the respective appropriate function.
     *
     * @param enteredString  The user input
     * @return Task object which was created using the user input
     * @throws TaskTypeException If the first word of the userinput is not a type of task
     * @throws StringIndexOutOfBoundsException If any of the methods it invokes throws to it
     * @throws Exception If any unforeseen error is encountered
     */
    public static Task addNewTask(String enteredString) throws
            TaskTypeException, StringIndexOutOfBoundsException, Exception {
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

    /**
     * Returns the Event object that it creates by using the user input.
     *
     * @param enteredString The user input
     * @return Deadline object which was created
     * @throws StringIndexOutOfBoundsException If the input format does not match the required type
     */
    public static Deadline createDeadlineTask(String enteredString)
            throws StringIndexOutOfBoundsException {
        int lengthOfBy = "by".length();
        int lengthOfDeadline = "deadline".length();
        int indexOfBy = enteredString.indexOf("by");

        String taskDescription = enteredString.substring(lengthOfDeadline, indexOfBy - 1);
        String deadlineTime = enteredString.substring(indexOfBy + lengthOfBy + 1);
        return new Deadline(taskDescription, deadlineTime);
    }

    /**
     * Returns the Event object that it creates by using the user input.
     *
     * @param enteredString The user input
     * @return Event object which was created
     * @throws StringIndexOutOfBoundsException If the input format does not match the required type
     */
    public static Event createEventTask(String enteredString)
            throws StringIndexOutOfBoundsException {
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

    /**
     * Returns the Event object that it creates by using the user input.
     *
     * @param enteredString The user input
     * @return ToDoo object which was created
     */
    public static ToDo createTodoTask(String enteredString) {
        int lengthOfTodo = "todo".length();

        String taskDescription = enteredString.substring(lengthOfTodo);

        return new ToDo(taskDescription);
    }

    /**
     * Deletes a task from the list of tasks and the file by interpreting the user input
     * It also deals with approriate errors if either no task number is specified
     * by the user of if a task number, which does not have a corresponding
     * task in the list of tasks is given.
     *
     * @param enteredString The user input
     * @param storage The storage object which is used to update to the file
     */
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

    /**
     * Displays the current list of tasks of the instance variable
     * that is used to call the function.
     */
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

    /**
     * Displays the list of tasks
     * of the provided list of tasks.
     *
     * @param tasks List of tasks
     */
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

    /**
     * @param enteredString
     * @return
     * @throws NumberFormatException
     * @throws InvalidTaskNumberException
     */
    public Task getTask(String enteredString)
            throws NumberFormatException, InvalidTaskNumberException {
        int taskNumber = getTaskNumber(enteredString);
        Task task = tasks.get(taskNumber - 1);
        return task;
    }

    /**
     * Returns the task number found in the user input
     *
     * @param enteredString The user input
     * @return the task number
     * @throws NumberFormatException If no number if found in the user input
     * @throws InvalidTaskNumberException If task number does not have a corresponding task in the list of tasks.
     */
    public int getTaskNumber(String enteredString)
            throws NumberFormatException, InvalidTaskNumberException {
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

    /**
     * Displays all tasks whose description contains the keyword(s)
     * specified by the user.
     *
     * @param enteredString The user input
     */
    public void findTask(String enteredString) {
        int indexOfFind = enteredString.indexOf("find");
        int lengthOfFind = "find".length();
        String searchItem = enteredString.substring(indexOfFind + lengthOfFind + 1);
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            String taskDescription = task.getDescription();
            if (taskDescription.contains(searchItem)) {
                matchingTasks.add(task);
            }
        }
        listTasks(matchingTasks);
    }
}
