package luke;

import luke.commands.Command;
import luke.exceptions.InvalidCommand;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Luke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Luke() {
        ui = new Ui();

        // Get data from save file and save into ArrayList<String>
        storage = new Storage(Paths.get("data/tasks.txt"));
        // Load data into taskList
        taskList = new TaskList(storage.saveStrings);
    }

    private String numberOfTasksMessage() {
        return taskList.numberOfTasksMessage();
    }

    private void saveAllTasks() throws IOException {
        storage.saveAllTasks(taskList.tasks);
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
            } catch (InvalidCommand e) {
                ui.printReply("Sorry, I don't understand you :(");
            }
        }
        exitBot();
    }
}
