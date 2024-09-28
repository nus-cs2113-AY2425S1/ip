import java.io.IOException;

public class KaiWen {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public KaiWen(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line
                String[] command = Parser.parseCommand(fullCommand);

                switch (command[0]) {
                    case "bye":
                        ui.showGoodbye();
                        isExit = true;
                        break;
                    case "list":
                        tasks.printTasks();
                        break;
                    default:
                        CommandHandler.handleCommand(command, tasks);
                        break;
                }

                storage.save(tasks.getTasks());
            } catch (KaiException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError("Error saving tasks: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new KaiWen("data/tasks.txt").run();
    }
}
