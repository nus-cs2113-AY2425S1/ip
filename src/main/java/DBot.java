import java.util.Hashtable;
import java.util.List;

public class DBot {
    private static boolean isOn;
    private static Ui ui;
    private static TaskList taskList;
    private static Storage storage;
    private static Parser parser;

    public static void main(String[] args) {
        isOn = true;

        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        taskList = new TaskList(storage.loadData());
        parser = new Parser();

        while (isOn) {
            String line = ui.readLine();
            parser.parseCommand(line);

            switch (parser.getCommand()) {
                case "bye":
                    ui.printShortMessage("Bye. Hope to see you again soon!");
                    isOn = false;
                    storage.saveData(taskList.getTaskList());
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    mark();
                    break;
                case "unmark":
                    unmark();
                    break;
                case "todo":
                    todo();
                    break;
                case "event":
                    event();
                    break;
                case "deadline":
                    deadline();
                    break;
                case "delete":
                    delete();
                    break;
                case "find":
                    find();
                    break;
                default:
                    ui.printError("Unknown command: " + line);
            }
        }
    }

    private static void list() {
        ui.printShortMessage(ui.listTasks(taskList.getTaskList()));
    }

    private static void mark() {
        try {
            int option = Integer.parseInt(parser.getPrompt());
            Task task = taskList.getTask(option - 1);
            task.mark();

            ui.printLongMessage(new Object[]{
                    "Nice! I've marked this task as done:",
                    task.toString()
            });
        } catch (Exception e) {
            ui.printError("Invalid input, input must be a positive integer and must exist");
        }
    }

    private static void unmark() {
        try {
            int option = Integer.parseInt(parser.getPrompt());
            Task task = taskList.getTask(option - 1);
            task.unmark();
            ui.printLongMessage(new Object[]{
                    "OK, I've marked this task as not done yet:",
                    task.toString()
            });
        } catch (Exception e) {
            ui.printError("Invalid input, input must be a positive integer and must exist");
        }
    }

    private static void todo() {
        if (parser.getPrompt().isEmpty()) {
            ui.printError("Cannot have empty prompt");
            return;
        }
        Todo task = new Todo(parser.getPrompt());
        taskList.addTask(task);
        ui.printLongMessage(new Object[]{
                "Got it. I've added this task:",
                task,
                "Now you have " + taskList.getSize() + " tasks in the list."
        });
    }

    private static void deadline() {
        if (parser.getPrompt().isEmpty()) {
            ui.printError("Cannot have empty prompt");
            return;
        }
        Hashtable<String, String> arguments = parser.getArguments();
        Deadline task;
        try {
            task = new Deadline(parser.getPrompt(), arguments.get("by"));
        } catch (Exception e) {
            ui.printError("Invalid input, deadline missing argument '/by'");
            return;
        }

        taskList.addTask(task);
        ui.printLongMessage(new Object[]{
                "Got it. I've added this task:",
                task,
                "Now you have " + taskList.getSize() + " tasks in the list."
        });
    }

    private static void event() {
        if (parser.getPrompt().isEmpty()) {
            ui.printError("Cannot have empty prompt");
            return;
        }
        Hashtable<String, String> arguments = parser.getArguments();
        Event task;
        try {
            task = new Event(parser.getPrompt(), arguments.get("from"), arguments.get("to"));
        } catch (Exception e) {
            ui.printError("Invalid input, event missing argument(s) '/from' or '/to'");
            return;
        }
        taskList.addTask(task);
        ui.printLongMessage(new Object[]{
                "Got it. I've added this task:",
                task,
                "Now you have " + taskList.getSize() + " tasks in the list."
        });
    }

    private static void delete() {
        try {
            int option = Integer.parseInt(parser.getPrompt());
            Task removed = taskList.removeTask(option - 1);
            ui.printLongMessage(new Object[]{
                    "Noted. I've removed this task:",
                    removed,
                    "Now you have " + taskList.getSize() + " tasks in the list."
            });
        } catch (Exception e) {
            ui.printError("Invalid input, input must be a positive integer and must exist");
        }
    }

    private static void find() {
        List<Task> result = taskList.find(parser.getPrompt());
        ui.printLongMessage(new Object[]{
                "Here are the matching tasks in your list:",
                ui.listTasks(result)
        });
    }
}
