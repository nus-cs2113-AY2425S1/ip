package pythia;

import pythia.utility.Parser;
import pythia.utility.Ui;
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

    private static boolean isByeSaid;
    private static TaskList taskList;
    private static Storage storage;
    private static Ui ui;

    public Pythia(String filePath) {
        isByeSaid = false;
        storage = new Storage(filePath);
        taskList = storage.load();
        ui = new Ui();
    }

    public static void greet() {
        String helloMsg =   "Welcome, seeker. I am " + botName + ".\n" +
                            "What brings you here?";
        ui.printResponse(helloMsg);
    }

    public static void sayBye() {
        String byeMsg = "Your path is set. Until we meet again.";
        ui.printResponse(byeMsg);
        isByeSaid = true;
    }

    public static void listTasks() {
        StringBuilder comment = new StringBuilder();
        int remainingTasks = taskList.getNumberOfRemainingTasks();

        comment.append("Now you have ").append(remainingTasks);
        if (remainingTasks == 1) {
            comment.append(" task in the list.");
        } else {
            comment.append(" tasks in the list.");
        }

        ui.printTaskList(taskList, "", comment.toString());
    }

    public static void addTask(String taskName) {
        taskList.add(new Task(taskName));
        ui.printAddedTask("added: " + taskName);
        storage.save(taskList);
    }

    public static void addTask(Task task) {
        taskList.add(task);
        ui.printAddedTask("added: " + task.getName());
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
            ui.printResponse(msg);
            storage.save(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.printResponse("There is no such task :(");
        }
    }

    public static void deleteTask(Integer taskNumber) {
        try {
            String msg = "Nice! I've deleted this task:\n\t" + taskList.get(taskNumber - 1).toString();
            taskList.remove(taskNumber - 1);
            ui.printResponse(msg);
        } catch (IndexOutOfBoundsException e){
            ui.printResponse("There is no such task :(");
        }
    }

    public static void findTasks(String taskKeyword) {
        TaskList filteredTaskList = new TaskList();
        for (Task task : taskList) {
            String taskName = task.getName();
            if (taskName.contains(taskKeyword)) {
                filteredTaskList.add(task);
            }
        }

        if (filteredTaskList.getNumberOfTasks() == 0) {
            ui.printResponse("There is no such task :(");
        } else {
            String commentBefore = "Here are the matching tasks in your list:";
            String commentAfter = "";
            ui.printTaskList(filteredTaskList, commentBefore, commentAfter);
        }
    }

    private static void run() {
        ui.init();
        greet();
        Parser parser = new Parser();

        while (!isByeSaid) {
            try {
                String request = ui.getRequest();
                parser.parse(request);
                parser.execute();
            } catch (PythiaException e) {
                ui.printResponse(e.getUserMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Pythia("data/pythia.txt").run();
    }
}
