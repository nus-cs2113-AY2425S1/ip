import java.util.Scanner;

public class Eva {

    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";
    private final static TaskManager taskManager = new TaskManager();
    private final static Ui ui = new Ui();

    public static void main(String[] args) {

        ui.showWelcomeMessage();

        String line;

        while(true) {
            line = ui.readCommand();

            try {
                switch (getCommand(line)) {
                case "list":
                    taskManager.printTaskList();
                    break;
                case "mark":
                    taskManager.markTask(line);
                    break;
                case "unmark":
                    taskManager.unmarkTask(line);
                    break;
                case "bye":
                    ui.showGoodbyeMessage();
                    return;
                case "todo":
                    taskManager.printTodo(line);
                    break;
                case "deadline":
                    taskManager.printDeadline(line);
                    break;
                case "event":
                    taskManager.printEvent(line);
                    break;
                case "delete":
                    taskManager.deleteTask(line);
                    break;
                }
            } catch (EvaException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    public static String getCommand(String line) throws EvaException {

        if (line.equalsIgnoreCase("list")) {
            return "list";
        } else if (line.startsWith("mark")) {
            return "mark";
        } else if (line.startsWith("unmark")) {
            return "unmark";
        } else if (line.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (line.startsWith("todo")) {
            return "todo";
        } else if (line.startsWith("deadline")) {
            return "deadline";
        } else if (line.startsWith("event")) {
            return "event";
        } else if (line.startsWith("delete")) {
            return "delete";
        } else {
            throw new EvaException("Sorry!! I don't understand what does " + line + " means!");
        }
    }
}
