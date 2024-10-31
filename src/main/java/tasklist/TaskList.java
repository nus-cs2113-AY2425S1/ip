package tasklist;

import tasklist.tasks.Deadline;
import tasklist.tasks.Event;
import tasklist.tasks.Task;
import tasklist.tasks.Todo;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(String fileData) {
        list = new ArrayList<>();
        if (fileData != null) {
            parseFileData(fileData);
        }
    }

    private void parseFileData(String fileData) {
        String[] lines = fileData.split(System.lineSeparator());
        for (String line : lines) {
            try {
                parseLine(line);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Save file has been tampered with or corrupted");
            }
        }
    }

    private void parseLine(String line) throws IndexOutOfBoundsException {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            Todo todoTask = new Todo(parts[2]);
            if (parts[1].trim().equals("1")) {
                todoTask.markAsDone();
            }
            list.add(todoTask);
            break;
        case "D":
            Deadline deadlineTask = new Deadline(parts[2], parts[3]);
            if (parts[1].trim().equals("1")) {
                deadlineTask.markAsDone();
            }
            list.add(deadlineTask);
            break;
        case "E":
            Event eventTask = new Event(parts[2], parts[3], parts[4]);
            if (parts[1].trim().equals("1")) {
                eventTask.markAsDone();
            }
            list.add(eventTask);
            break;
        }
    }

    /**
     * Adds a task object to the task list
     * @param newTask The task object to be added
     */
    public void addTask(Task newTask) {
        System.out.println("I've added this to your list: ");
        newTask.printTask();
        list.add(newTask);
    }

    /**
     * Prints the list of all tasks in the task list
     * The tasks are numbered using a serial number
     */
    public void printTaskList() {
        if (list.isEmpty()) {
            System.out.println("You currently have no tasks");
            return;
        }
        System.out.println("Here is your list of tasks:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.printf("%d. ", i);
            list.get(i - 1).printTask();
        }
    }

    /**
     * Mark the task at a index specified
     * @param index The index of the task to be marked
     */
    public void markTask(int index) {
        list.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        list.get(index).printTask();
    }

    /**
     * Unmark the task at a index specified
     * @param index The index of task to be unmarked
     */
    public void unmarkTask(int index) {
        list.get(index).markAsNotDone();
        System.out.println("Nice! I've marked this task as not done:");
        list.get(index).printTask();
    }

    /**
     * Delete the task at a index specified
     * @param index The index of task to be deleted
     */
    public void deleteTask(int index) {
        Task temp = list.get(index);
        list.remove(index);
        System.out.println("Nice! I've deleted this task for you:");
        temp.printTask();
    }

    /**
     * Search for tasks containing a string in their description
     * Prints a list of tasks containing the search term
     * The search is case-insensitive
     * @param match The search term
     */
    public void searchTasks(String match) {
        System.out.println("The tasks matching your search term are:");
        int printIndex = 1;
        for (Task task : list) {
            String lowerCaseDescription = task.getInfo().toLowerCase();
            if (lowerCaseDescription.contains(match.toLowerCase())) {
                System.out.print(printIndex + ". ");
                printIndex++;
                task.printTask();
            }
        }
        if (printIndex==1){
            System.out.println("No matches found");
        }
    }

    public ArrayList<Task> getList() {
        return list;
    }
}
