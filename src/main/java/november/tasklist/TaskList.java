package november.tasklist;

import november.exceptions.NovemberExceptions;
import november.tasks.Deadline;
import november.tasks.Event;
import november.tasks.Task;
import november.tasks.Todo;

import java.io.IOException;
import java.util.List;

import static november.ui.Ui.beginSegment;
import static november.ui.Ui.endSegment;
import static november.ui.Ui.printTaskCount;
import static november.storage.Storage.updateSaveFile;

import static november.messages.Messages.ADD_TASK_MESSAGE;
import static november.messages.Messages.DELETE_TASK_MESSAGE;
import static november.messages.Messages.INDEX_OUT_OF_BOUNDS_MESSAGE;
import static november.messages.Messages.MARK_TASK_MESSAGE;
import static november.messages.Messages.NONNUMERICAL_INDEX_MESSAGE;
import static november.messages.Messages.UNMARK_TASK_MESSAGE;

public class TaskList {
    /**
     * Adds a new TODO task to the list and updates the save file.
     *
     * @param description The description of the TODO task.
     * @param taskList    The list to add the task to.
     */
    public static void addNewTodo(String description, List<Task> taskList) {
        try {
            Todo todoTask = NovemberExceptions.validTodo(description); // Create a new TODO task
            taskList.add(todoTask); // Add the task to the list
            beginSegment();
            System.out.print(ADD_TASK_MESSAGE);
            todoTask.printTask(); // Print the added task
            printTaskCount(taskList); // Print the current task count
            endSegment();
            updateSaveFile(taskList); // Update the save file
        } catch (IllegalArgumentException e) {
            beginSegment();
            System.out.println(e.getMessage()); // Print error message if task is invalid
            endSegment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a new DEADLINE task to the list and updates the save file.
     *
     * @param description The description of the DEADLINE task.
     * @param taskList    The list to add the task to.
     */
    public static void addNewDeadline(String description, List<Task> taskList) {
        try {
            Deadline deadlineTask = NovemberExceptions.validDeadline(description); // Create a new DEADLINE task
            taskList.add(deadlineTask); // Add the task to the list
            beginSegment();
            System.out.print(ADD_TASK_MESSAGE);
            deadlineTask.printTask(); // Print the added task
            printTaskCount(taskList); // Print the current task count
            endSegment();
            updateSaveFile(taskList); // Update the save file
        } catch (IllegalArgumentException e) {
            beginSegment();
            System.out.println(e.getMessage()); // Print error message if task is invalid
            endSegment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a new EVENT task to the list and updates the save file.
     *
     * @param description The description of the EVENT task.
     * @param taskList    The list to add the task to.
     */
    public static void addNewEvent(String description, List<Task> taskList) {
        try {
            Event eventTask = NovemberExceptions.validEvent(description); // Create a new EVENT task
            taskList.add(eventTask); // Add the task to the list
            beginSegment();
            System.out.print(ADD_TASK_MESSAGE);
            eventTask.printTask(); // Print the added task
            printTaskCount(taskList); // Print the current task count
            endSegment();
            updateSaveFile(taskList); // Update the save file
        } catch (IllegalArgumentException e) {
            beginSegment();
            System.out.println(e.getMessage()); // Print error message if task is invalid
            endSegment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Marks a task as complete.
     *
     * @param description The index of the task to mark.
     * @param taskList    The list of tasks.
     * @param printMessage Flag indicating whether to print a message.
     */
    public static void markTask(String description, List<Task> taskList, Boolean printMessage) {
        try {
            int markIndex = Integer.parseInt(description) - 1; // Parse the index and adjust for 0-based indexing
            taskList.get(markIndex).setComplete(); // Set the task as complete
            if (printMessage) {
                beginSegment();
                System.out.print(MARK_TASK_MESSAGE);
                taskList.get(markIndex).printTask(); // Print the marked task
                endSegment();
            }
        } catch (NumberFormatException e) {
            beginSegment();
            System.out.println(NONNUMERICAL_INDEX_MESSAGE); // Print error message for invalid index
            endSegment();
        } catch (IndexOutOfBoundsException e) {
            beginSegment();
            System.out.println("Sorry, but that mark index is not within the list."); // Print error message for out-of-bounds index
            endSegment();
        }
    }

    /**
     * Unmarks a task as incomplete.
     *
     * @param description The index of the task to unmark.
     * @param taskList    The list of tasks.
     */
    public static void unmarkTask(String description, List<Task> taskList) {
        try {
            int unmarkIndex = Integer.parseInt(description) - 1; // Parse the index and adjust for 0-based indexing
            taskList.get(unmarkIndex).setIncomplete(); // Set the task as incomplete
            beginSegment();
            System.out.print(UNMARK_TASK_MESSAGE);
            taskList.get(unmarkIndex).printTask(); // Print the unmarked task
            endSegment();
            updateSaveFile(taskList); // Update the save file
        } catch (NumberFormatException e) {
            beginSegment();
            System.out.println(NONNUMERICAL_INDEX_MESSAGE); // Print error message for invalid index
            endSegment();
        } catch (IndexOutOfBoundsException e) {
            beginSegment();
            System.out.println(INDEX_OUT_OF_BOUNDS_MESSAGE); // Print error message for out-of-bounds index
            endSegment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param sentence The input sentence containing the task index to delete.
     * @param taskList The list of tasks.
     */
    public static void deleteTask(String[] sentence, List<Task> taskList) {
        try {
            int deleteIndex = Integer.parseInt(sentence[1]) - 1; // Parse the index and adjust for 0-based indexing
            beginSegment();
            System.out.print(DELETE_TASK_MESSAGE);
            taskList.get(deleteIndex).printTask(); // Print the deleted task
            taskList.remove(deleteIndex); // Remove the task from the list
            printTaskCount(taskList); // Print the updated task count
            endSegment();
            updateSaveFile(taskList); // Update the save file
        } catch (NumberFormatException e) {
            beginSegment();
            System.out.println(NONNUMERICAL_INDEX_MESSAGE); // Print error message for non-numeric index
            endSegment();
        } catch (IndexOutOfBoundsException e) {
            beginSegment();
            System.out.println(INDEX_OUT_OF_BOUNDS_MESSAGE); // Print error message for out-of-bounds index
            endSegment();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Finds tasks in the list matching a keyword.
     *
     * @param keyword The input keyword to be searched for.
     * @param taskList The list of tasks.
     */
    public static void findTask(String keyword, List<Task> taskList) {
        boolean taskFound = false;
        beginSegment();
        System.out.println("Searching for tasks containing the specified keyword: " + keyword);
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                task.printTask();
                taskFound = true;
            }
        }
        if (!taskFound) {
            System.out.println("No matching tasks found");
        }
        endSegment();
    }
}
