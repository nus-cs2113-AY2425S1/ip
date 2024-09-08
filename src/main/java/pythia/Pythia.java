package pythia;

import pythia.io.Parser;
import pythia.io.IO;
import pythia.task.Task;
import pythia.task.ToDo;
import pythia.task.Deadline;
import pythia.task.Event;
import pythia.exceptions.PythiaException;
import java.util.ArrayList;

public class Pythia {
    private static String botName = "Pythia";
    private static String logo =
            "____        _   _     _       \n" +
            "|  _ \\ _   _| |_| |__ (_) __ _ \n" +
            "| |_) | | | | __| '_ \\| |/ _` |\n" +
            "|  __/| |_| | |_| | | | | (_| |\n" +
            "|_|    \\__, |\\__|_| |_|_|\\__,_|\n" +
            "       |___/                   ";

    private static boolean isByeSaid = false;
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    private static int remainingTasks = 0;

    public static int getNumberOfRemainingTasks() {
        return remainingTasks;
    }

    public static void greet() {
        String helloMsg =   "Welcome, seeker. I am " + botName + ".\n" +
                            "What brings you here?";
        IO.printResponse(helloMsg);
    }

    public static void echo(String text) {
        IO.printResponse(text);
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
        remainingTasks++;
        IO.printAddedTask("added: " + taskName);
    }

    public static void addToDo(String todoName) {
        taskList.add(new ToDo(todoName));
        remainingTasks++;
        IO.printAddedTask("added: " + todoName);
    }

    public static void addDeadline(String deadlineName, String dueDate) {
        taskList.add(new Deadline(deadlineName, dueDate));
        remainingTasks++;
        IO.printAddedTask("added: " + deadlineName);
    }

    public static void addEvent(String eventName, String startDate, String endDate) {
        taskList.add(new Event(eventName, startDate, endDate));
        remainingTasks++;
        IO.printAddedTask("added: " + eventName);
    }

    public static void markTask(Integer taskNumber) {
        if (taskNumber <= taskList.size()) {
            taskList.get(taskNumber - 1).markAsDone();
            String msg = "Nice! I've marked this pythia.task as done:\n\t" + taskList.get(taskNumber - 1).toString();
            IO.printResponse(msg);
        } else {
            IO.printResponse("There is no such pythia.task :(");
        }
        remainingTasks--;
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
