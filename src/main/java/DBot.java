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

            try {
                switch (parser.getCommand()) {
                    case "bye":
                        isOn = false;
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
                        throw new DBotException("Unknown command: " + line, "Unknown command");
                }
            } catch (Exception e) {
                if (e instanceof DBotException)
                    ui.printDBotError((DBotException) e);
                else ui.printGenericError(e);
            }
        }
        ui.printShortMessage("Bye. Hope to see you again soon!");
        storage.saveData(taskList.getTaskList());
    }

    private static void list() {
        ui.printShortMessage(ui.listTasks(taskList.getTaskList()));
    }

    private static void mark() throws DBotException {
        try {
            int option = Integer.parseInt(parser.getPrompt());
            Task task = taskList.getTask(option - 1);
            task.mark();

            ui.printLongMessage(new Object[]{
                    "Nice! I've marked this task as done:",
                    task.toString()
            });
        } catch (Exception e) {
            throw new DBotException("Invalid input, input must be a positive integer and must exist", "Invalid input");
        }
    }

    private static void unmark() throws DBotException {
        try {
            int option = Integer.parseInt(parser.getPrompt());
            Task task = taskList.getTask(option - 1);
            task.unmark();
            ui.printLongMessage(new Object[]{
                    "OK, I've marked this task as not done yet:",
                    task.toString()
            });
        } catch (Exception e) {
            throw new DBotException("Invalid input, input must be a positive integer and must exist", "Invalid input");
        }
    }

    private static void todo() throws DBotException {
        if (parser.getPrompt().isEmpty())
            throw new DBotException("Cannot have empty prompt", "Invalid input");

        Todo task = new Todo(parser.getPrompt());
        taskList.addTask(task);
        ui.printLongMessage(new Object[]{
                "Got it. I've added this task:",
                task,
                "Now you have " + taskList.getSize() + " tasks in the list."
        });
    }

    private static void deadline() throws DBotException {
        if (parser.getPrompt().isEmpty())
            throw new DBotException("Cannot have empty prompt", "Invalid input");

        Hashtable<String, String> arguments = parser.getArguments();
        Deadline task;
        try {
            task = new Deadline(parser.getPrompt(), arguments.get("by"));
        } catch (Exception e) {
            throw new DBotException("Deadline missing argument '/by'", "Missing argument");
        }

        taskList.addTask(task);
        ui.printLongMessage(new Object[]{
                "Got it. I've added this task:",
                task,
                "Now you have " + taskList.getSize() + " tasks in the list."
        });
    }

    private static void event() throws DBotException {
        if (parser.getPrompt().isEmpty())
            throw new DBotException("Cannot have empty prompt", "Invalid input");

        Hashtable<String, String> arguments = parser.getArguments();
        Event task;
        try {
            task = new Event(parser.getPrompt(), arguments.get("from"), arguments.get("to"));
        } catch (Exception e) {
            throw new DBotException("Event missing argument(s) '/from' or '/to'", "Missing argument");
        }
        taskList.addTask(task);
        ui.printLongMessage(new Object[]{
                "Got it. I've added this task:",
                task,
                "Now you have " + taskList.getSize() + " tasks in the list."
        });
    }

    private static void delete() throws DBotException {
        try {
            int option = Integer.parseInt(parser.getPrompt());
            Task removed = taskList.removeTask(option - 1);
            ui.printLongMessage(new Object[]{
                    "Noted. I've removed this task:",
                    removed,
                    "Now you have " + taskList.getSize() + " tasks in the list."
            });
        } catch (Exception e) {
            throw new DBotException("Invalid input, input must be a positive integer and must exist", "Invalid input");
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
