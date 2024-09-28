package pythia;

import pythia.utility.Parser;
import pythia.utility.IO;
import pythia.task.Task;
import pythia.task.ToDo;
import pythia.task.Deadline;
import pythia.task.Event;
import pythia.exceptions.PythiaException;
import pythia.utility.TaskList;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Pythia {
    private static String botName = "Pythia";
    private static String dataPath = "data/pythia.txt";
    private static String logo =
            "____        _   _     _       \n" +
            "|  _ \\ _   _| |_| |__ (_) __ _ \n" +
            "| |_) | | | | __| '_ \\| |/ _` |\n" +
            "|  __/| |_| | |_| | | | | (_| |\n" +
            "|_|    \\__, |\\__|_| |_|_|\\__,_|\n" +
            "       |___/                   ";

    private static boolean isByeSaid = false;
    private static TaskList taskList = new TaskList();

    public static void greet() {
        String helloMsg =   "Welcome, seeker. I am " + botName + ".\n" +
                            "What brings you here?";
        IO.printResponse(helloMsg);
    }

    public static void sayBye() {
        String byeMsg = "Your path is set. Until we meet again.";
        IO.printResponse(byeMsg);
        isByeSaid = true;
    }

    public static void listTasks() {
        IO.printTaskList(taskList);
    }

    public static void addTask(String taskName) {
        taskList.add(new Task(taskName));
        IO.printAddedTask("added: " + taskName);
        saveTasksToTxt();
    }

    public static void addToDo(String todoName) {
        taskList.add(new ToDo(todoName));
        IO.printAddedTask("added: " + todoName);
        saveTasksToTxt();
    }

    public static void addDeadline(String deadlineName, String dueDate) {
        taskList.add(new Deadline(deadlineName, dueDate));
        IO.printAddedTask("added: " + deadlineName);
        saveTasksToTxt();
    }

    public static void addEvent(String eventName, String startDate, String endDate) {
        taskList.add(new Event(eventName, startDate, endDate));
        IO.printAddedTask("added: " + eventName);
        saveTasksToTxt();
    }

    public static void markTask(Integer taskNumber) {
        try {
            taskList.markAsDone(taskNumber - 1);
            String msg = "Nice! I've marked this task as done:\n\t" + taskList.get(taskNumber - 1).toString();
            IO.printResponse(msg);
            saveTasksToTxt();
        } catch (IndexOutOfBoundsException e) {
            IO.printResponse("There is no such task :(");
        }
    }

    public static void deleteTask(Integer taskNumber) {
        try {
            String msg = "Nice! I've deleted this task:\n\t" + taskList.get(taskNumber - 1).toString();
            taskList.remove(taskNumber - 1);
            IO.printResponse(msg);
        } catch (IndexOutOfBoundsException e){
            IO.printResponse("There is no such task :(");
        }
    }

    public static void saveTasksToTxt() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataPath, false))) {
            for (Task task : taskList) {
                writer.write(task.toTxt());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        IO.init();
        greet();
        Parser parser = new Parser();

        while (!isByeSaid) {
            try {
                String request = IO.getRequest();
                parser.parse(request);
                parser.execute();
            } catch (PythiaException e) {
                IO.printResponse(e.getUserMessage());
            }
        }
    }
}
