package atom.storage;

import atom.tasklist.TaskList;
import atom.exception.AtomException;
import atom.task.Deadline;
import atom.task.Event;
import atom.task.Task;
import atom.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file;

    //filepath = "./data/AtomList.txt"
    public Storage(String filePath) {
        File folder = new File("./data");
        file = new File(filePath);

        //create directory if it does not exist
        if (folder.mkdir()) {
            System.out.println("Folder \"data\" created.");
        } else {
            System.out.println("Folder already exists.");
        }

        //create txt file if it does not exist
        try {
            if (file.createNewFile()) {
                System.out.println("File created.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Oops!! An error occurred.");
        }
    }


    public ArrayList<Task> load() {
        TaskList tasks = new TaskList();
        //load data (if any) to the list
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String fileLine = fileScanner.nextLine().trim();
                String[] fileLineParams = fileLine.split("\\|");

                String taskType = fileLineParams[0].trim();
                boolean doneStatus = (fileLineParams[1].trim().equals("1"));
                String taskName = fileLineParams[2].trim();

                switch (taskType) {
                case "T":
                    loadTodoTaskToList(tasks, taskName, doneStatus);
                    break;
                case "D":
                    String taskDueDate = fileLineParams[3].trim();
                    loadDeadlineTaskToList(tasks, taskName, doneStatus, taskDueDate);
                    break;
                case "E":
                    String taskDuration = fileLineParams[3].trim();
                    loadEventTaskToList(tasks, taskName, doneStatus, taskDuration);
                    break;
                default:
                    throw new AtomException();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found..");
        }
        return tasks.getTasksList();
    }

    public void loadTodoTaskToList(TaskList tasks, String taskName, boolean doneStatus) {
        Todo todo = tasks.addTodoTask(taskName);
        if (doneStatus) {
            todo.markAsDone();
        }
    }

    public void loadDeadlineTaskToList(TaskList tasks, String taskName, boolean doneStatus,
                                       String taskDueDate) {

        Deadline deadline = tasks.addDeadlineTask(taskName, taskDueDate);
        if (doneStatus) {
            deadline.markAsDone();
        }
    }

    public void loadEventTaskToList(TaskList tasks, String taskName, boolean doneStatus,
                                    String taskDuration) {

        String[] eventDurationParams = taskDuration.split("-");
        String eventStartDate = eventDurationParams[0].trim();
        String eventEndDate = eventDurationParams[1].trim();

        Event event = tasks.addEventTask(taskName, eventStartDate, eventEndDate);
        if (doneStatus) {
            event.markAsDone();
        }
    }

    public void store(TaskList tasks) {

        ArrayList<Task> list = tasks.getTasksList();

        //write data into AtomList.txt file
        try {
            FileWriter fw = new FileWriter(file);

            for (Task task : list) {
                String separator = " | ";
                String doneStatus = (task.getStatus().equals("X")) ? "1" : "0";

                fw.write(task.setTaskType() + separator + doneStatus
                        + separator + task.getItem());

                if (task.setTaskType().equals("D")) {
                    fw.write(separator + task.getDueDate());
                } else if (task.setTaskType().equals("E")) {
                    fw.write(separator + task.getStartDate() + "-" + task.getEndDate());
                }

                fw.write(System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            System.out.println("Oh no!! Something went wrong..");
        }
    }

}
