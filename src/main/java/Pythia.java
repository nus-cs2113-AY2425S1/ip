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

    private static boolean byeSaid = false;
    private static ArrayList<Task> taskList = new ArrayList<Task>();

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
        byeSaid = true;
    }

    public static void listTasks() {
        IO.printTaskList(taskList);
    }

    public static void chooseAction(String request) {
        String key = request.split(" ")[0];
        String value = "";

        if (request.split(" ").length > 1) {
            value = request.substring(key.length() + 1);
        }

        switch (key) {
            case "bye" -> sayBye();
            case "list" -> listTasks();
            case "add" -> addTask(value);
            case "mark" -> markTask(Integer.parseInt(value));
            default -> IO.printResponse("Hmm. I am not sure what you mean.");
        }
    }

    public static void addTask(String taskName) {
        taskList.add(new Task(taskName));
        IO.printAddedTask("added: " + taskName);
    }

    public static void markTask(Integer taskNumber) {
        if (taskNumber <= taskList.size()) {
            taskList.get(taskNumber - 1).markAsDone();
            String msg = "Nice! I've marked this task as done:\n\t" + taskList.get(taskNumber - 1).toString();
            IO.printResponse(msg);
        } else {
            IO.printResponse("There is no such task :(");
        }
    }
    
    public static void main(String[] args) {
        IO.init();
        greet();

        while (!byeSaid) {
            String request = IO.getRequest();
            chooseAction(request);
        }
    }
}
