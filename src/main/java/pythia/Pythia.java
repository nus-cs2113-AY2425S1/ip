package pythia;

import pythia.utility.Parser;
import pythia.utility.IO;
import pythia.task.Task;
import pythia.task.ToDo;
import pythia.task.Deadline;
import pythia.task.Event;
import pythia.exceptions.PythiaException;
import pythia.utility.Storage;
import pythia.utility.TaskList;

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
    private static TaskList taskList;
    private static Storage storage;

    public Pythia(String filePath) {
        storage = new Storage(filePath);
        taskList = storage.load();
    }

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
        storage.save(taskList);
    }

    public static void addTask(Task task) {
        taskList.add(task);
        IO.printAddedTask("added: " + task.getName());
        storage.save(taskList);
    }

    public static void addToDo(String todoName) {
        addTask(new ToDo(todoName));
    }

    public static void addDeadline(String deadlineName, String dueDate) {
        addTask(new Deadline(deadlineName, dueDate));
    }

    public static void addEvent(String eventName, String startDate, String endDate) {
        addTask(new Event(eventName, startDate, endDate));
    }

    public static void markTask(Integer taskNumber) {
        try {
            taskList.markAsDone(taskNumber - 1);
            String msg = "Nice! I've marked this task as done:\n\t" + taskList.get(taskNumber - 1).toString();
            IO.printResponse(msg);
            storage.save(taskList);
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

    private static void run() {
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

    public static void main(String[] args) {
        new Pythia("data/pythia.txt").run();
    }
}
