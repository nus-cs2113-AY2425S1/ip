package utils;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import Tasks.Todo;
import Tasks.Task;
import Tasks.Deadline;
import Tasks.Event;

/**
 * The Storage class provides methods to read and write tasks to a log file.
 * The log file is stored in the data directory and is named Cubone.txt.
 * The log file is read and written in the format specified in the user guide.
 */
public class Storage {
    public static final String LOG_FILE_NAME = "./data/Cubone.txt";

    /**
     * Retrieves the log file. If the log file does not exist, it creates the file
     * and its parent directories if necessary.
     *
     * @return The log file.
     */
    public static File getLogFile() {
        File file = new File(LOG_FILE_NAME);
        // check if the file exists
        if (!file.exists()) {
            try {
                // check if the dictionary exists
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * Updates the log file with the given list of tasks.
     *
     * @param inputed_tasks An ArrayList of Task objects to be written to the log file.
     *                      Each task will be written on a new line.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void updateLogFile(ArrayList<Task> inputed_tasks) {
        try {
            // run through the list of tasks and write them to the file
            File file = getLogFile();
            FileWriter fileWritter = new FileWriter(file);
            for (Task task : inputed_tasks) {
                fileWritter.write(task.toString() + "\n");
            }
            fileWritter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the log file and parses the tasks stored in it.
     * The log file is expected to contain tasks in a specific format.
     * Each line in the log file represents a task and is split into task details.
     * The task details are parsed based on the type of task (Todo, Deadline, Event, or generic Task).
     * 
     * @return An ArrayList of Task objects parsed from the log file.
     *         If an error occurs while reading the file, an empty ArrayList is returned.
     */
    public static ArrayList<Task> readLogFile() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File file = getLogFile();
            java.util.Scanner scanner = new java.util.Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] taskDetails = line.split(" \\| ",2);
                String taskType = taskDetails[0].substring(1, 2);
                switch (taskType) {
                    case " ":
                        // the task is a task (without a prefix character)
                        Task newTask = new Task(taskDetails[1]);
                        if (taskDetails[0].contains("X")){
                            // the task is done
                            newTask.markAsDone();
                        }
                        tasks.add(newTask);
                        break;
                    case "T":
                        // the task is a todo
                        Todo newTodo = new Todo(taskDetails[1]);
                        if (taskDetails[0].contains("X")){
                            // the task is done
                            newTodo.markAsDone();
                        }
                        tasks.add(newTodo);
                        break;
                    case "D":
                        // the task is a deadline
                        String[] deadlineDetails = taskDetails[1].split(" \\(by: ", 2);
                        String deadlineDescription = deadlineDetails[0];
                        String deadlineBy = deadlineDetails[1].substring(0, deadlineDetails[1].length() - 1);
                        Deadline newDeadline = new Deadline(deadlineDescription, deadlineBy);
                        if (taskDetails[0].contains("X")){
                            // the task is done
                            newDeadline.markAsDone();
                        }
                        tasks.add(newDeadline);
                        break;
                    case "E":
                        // the task is an event
                        String[] eventDetails = taskDetails[1].split(" \\(from: ", 2);
                        String eventDescription = eventDetails[0];
                        String[] eventTimeDetails = eventDetails[1].split(" to: ", 2);
                        String eventFrom = eventTimeDetails[0];
                        String eventTo = eventTimeDetails[1].substring(0, eventTimeDetails[1].length() - 1);
                        Event newEvent = new Event(eventDescription, eventFrom, eventTo);
                        if (taskDetails[0].contains("X")){
                            // the task is done
                            newEvent.markAsDone();
                        }
                        tasks.add(newEvent);
                        break;
                    default:
                        break;
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading the log file, invalid data detected.");
            return new ArrayList<Task>();
        }
        return tasks;
    }
}
