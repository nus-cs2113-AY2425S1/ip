package doug;

import doug.Commands.ListCommand;
import doug.tasks.Deadline;
import doug.tasks.Event;
import doug.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that deals with saving and loading tasks from the local .txt file
 */
public class Storage {

    /**
     * Re-writes the save file by adding all existing tasks to it
     *
     * @param tasks The object containing the ArrayList of tasks
     * @throws IOException If the save file cannot be opened or written to properly
     */
    public static void saveTasks(TaskList tasks) throws IOException {
        File dir = new File("./data");
        // If directory does not yet exist, create it
        if (!dir.isDirectory()) {
            dir.mkdir();
        }

        FileWriter writer = new FileWriter("./data/tasks.txt");
        for (int i = 0; i < tasks.getCount(); i++) {
            writer.write(tasks.getTask(i).saveString() + System.lineSeparator());
        }
        writer.close();
    }

    /**
     * Reads the save file line by line and adds the saved tasks to the TaskList tasks object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @throws FileNotFoundException If save file cannot be found
     */
    public static void loadTasks(TaskList tasks) throws FileNotFoundException {
        File tasksFile = new File("./data/tasks.txt"); 	// create a File for the given file path
        Scanner reader = new Scanner(tasksFile); 	// create a Scanner using the File as the source
        boolean isEmpty = true;
        while (reader.hasNext()) {
            String line = reader.nextLine();
            isEmpty = false;
            if(line.startsWith("T")) {
                loadToDo(tasks, line);
            } else if (line.startsWith("D")) {
                loadDeadline(tasks, line);
            } else if (line.startsWith("E")) {
                loadEvent(tasks, line);
            }
        }
        UI.sayExistingUserWelcome();
        if (isEmpty) {
            return;
        }
        ListCommand.listTasks(tasks);
    }

    /**
     * Parses the line containing todo task and adds it to the TaskList tasks object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param line Line from save file storing data on a todo task
     */
    public static void loadToDo(TaskList tasks, String line) {
        boolean isMarked = false;
        if (line.contains("| X |")) {
            isMarked = true;
            line = line.replaceFirst("T \\| X \\|", "").trim();
        } else {
            line = line.replaceFirst("T \\|   \\|", "").trim();
        }

        Todo todoTask = new Todo(line);
        tasks.addTask(todoTask);

        if (isMarked) {
            todoTask.markAsDone();
        }
    }

    /**
     * Parses the line containing deadline task and adds it to the TaskList tasks object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param line Line from save file storing data on a deadline task
     */
    public static void loadDeadline(TaskList tasks, String line) {
        boolean isMarked = false;
        if (line.contains("| X |")) {
            isMarked = true;
            line = line.replaceFirst("D \\| X \\|", "").trim();
        } else {
            line = line.replaceFirst("D \\|   \\|", "").trim();
        }

        int indexOfFirstBoundary = line.indexOf("| ");
        String taskName = line.substring(0, indexOfFirstBoundary).trim();

        line = line.replaceFirst(taskName, "");
        String taskDeadline = line.replaceFirst("\\| ", "").trim();

        Deadline deadlineTask = new Deadline(taskName, taskDeadline);
        tasks.addTask(deadlineTask);

        if (isMarked) {
            deadlineTask.markAsDone();
        }
    }

    /**
     * Parses the line containing event task and adds it to the TaskList tasks object
     *
     * @param tasks The object containing the ArrayList of tasks
     * @param line Line from save file storing data on a event task
     */
    public static void loadEvent(TaskList tasks, String line) {
        boolean isMarked = false;
        if (line.contains("| X |")) {
            isMarked = true;
            line = line.replaceFirst("E \\| X \\|", "").trim();
        } else {
            line = line.replaceFirst("E \\|   \\|", "").trim();
        }

        int indexOfFirstBoundary = line.indexOf("| ");
        String taskName = line.substring(0, indexOfFirstBoundary).trim();

        line = line.replaceFirst(taskName, "");
        line = line.replaceFirst("\\| ", "").trim();
        int indexOfSecondBoundary = line.indexOf("| ");
        String taskFrom = line.substring(0, indexOfSecondBoundary);

        line = line.replaceFirst(taskFrom, "");
        String taskTo = line.replaceFirst("\\| ", "").trim();

        Event eventTask = new Event(taskName, taskFrom, taskTo);
        tasks.addTask(eventTask);

        if (isMarked) {
            eventTask.markAsDone();
        }
    }

}

