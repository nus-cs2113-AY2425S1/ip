package luke;

import luke.commands.Command;
import luke.exceptions.InvalidCommandException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Luke {

    public static final String SAVE_PATH = "data/tasks.txt";
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Luke() {
        ui = new Ui();

        // Get data from save file and save into ArrayList<String>
        storage = new Storage(Paths.get(SAVE_PATH));
        // Load data into taskList
        taskList = new TaskList(storage.saveStrings);
    }

    private void saveAllTasks() throws IOException {
        storage.saveAllTasks(taskList.getTasks());
    }

    private void exitBot() {
        ui.printReply("Bye. Hope to see you again soon!");
        try {
            saveAllTasks();
        } catch (IOException e) {
            ui.printReply(String.format("Error occurred while saving data: %s", e.getMessage()));
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        new Luke().run();
    }

    public void run() {
        ui.printGreeting();

        Scanner in = new Scanner(System.in);
        String line;
        boolean isExit = false;

        while (!isExit) {
            line = in.nextLine();
            String[] inputs = line.split(" ");
            try {
                Command c = Parser.parseCommand(inputs);
                c.execute(taskList, ui, inputs);
                isExit = c.isExit;
            } catch (InvalidCommandException e) {
                ui.printReply("Sorry, I don't understand you :(");
            }
        }
        exitBot();
    }
}
