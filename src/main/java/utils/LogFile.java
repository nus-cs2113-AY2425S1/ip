package utils;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import Tasks.Todo;
import Tasks.Task;
import Tasks.Deadline;
import Tasks.Event;


public class LogFile {
    public static final String LOG_FILE_NAME = "./data/Cubone.txt";

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
        }
        return tasks;
    }
}
