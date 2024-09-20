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

public class Storage {

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


}

