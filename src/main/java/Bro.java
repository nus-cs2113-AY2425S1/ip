import java.util.ArrayList;

/**
 * Entry point for the Bro Task Manager application.
 * Initializes the UI, loads tasks from storage, and processes user commands.
 */
public class Bro {
    public static void main(String[] args) {
        TaskUI ui = new TaskUI();
        ui.showWelcome();

        ArrayList<Task> tasks = Storage.loadTasks();
        Parser parser = new Parser(tasks);
        String command = ui.readCommand();

        while (!(command.equals("Bye"))) {
            parser.execute(command);
            command = ui.readCommand();
        }

        System.out.println("Program exited.");

    }
}
