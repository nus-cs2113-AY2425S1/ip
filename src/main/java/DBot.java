import java.util.Hashtable;

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
                default:
                    ui.printError("Unknown command: " + line);
            }
        }
    }

    private static void list() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.getSize(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(taskList.getTask(i).toString());
            sb.append('\n');
        }
        ui.printShortMessage(sb.toString());
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
        Todo task = new Todo(parser.getPrompt());
        taskList.addTask(task);
        ui.printLongMessage(new Object[]{
                "Got it. I've added this task:",
                task,
                "Now you have " + taskList.getSize() + " tasks in the list."
        });
    }

    private static void deadline() {
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
}
