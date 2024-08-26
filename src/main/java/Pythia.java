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
    }

    public static void chooseAction(String request) {
        String key = request.split(" ")[0];
        String value = "";

        if (request.split(" ").length > 1) {
            value = request.substring(key.length() + 1);
        }

        if (key.equals("bye")) {
            sayBye();
            byeSaid = true;
        }
        else if (key.equals("list")) {
            IO.printTaskList(taskList);
        }
        else if (key.equals("add")) {
            addTask(value);
            IO.printAddedTask("added: " + value);
        }
    }

    public static void addTask(String name) {
        taskList.add(new Task(name));
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
