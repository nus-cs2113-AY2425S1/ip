package luke;

import luke.exceptions.IncorrectInput;
import luke.exceptions.InsufficientArguments;
import luke.exceptions.InvalidCommand;
import luke.exceptions.LukeException;

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

    private void sendMessage(String userInput) {
        String[] inputArr = userInput.split(" ");
        String command = inputArr[0];

        if (command.equalsIgnoreCase("bye")) {
            exitBot();
        } else if (command.equalsIgnoreCase("list")) {
            taskList.list();
        } else if (command.equalsIgnoreCase("mark")) {
            try {
                taskList.mark(inputArr);
            } catch (LukeException e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("unmark")) {
            try {
                taskList.unmark(inputArr);
            } catch (LukeException e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("todo")){
            try {
                taskList.addToDo(inputArr);
            } catch (InsufficientArguments e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("deadline")){
            try {
                taskList.addDeadline(inputArr);
            } catch (InsufficientArguments e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("event")) {
            try {
                taskList.addEvent(inputArr);
            } catch (InsufficientArguments e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("delete")) {
            try {
                taskList.deleteTask(inputArr);
            } catch (InsufficientArguments | IncorrectInput e) {
                ui.printReply(e.getMessage());
            }
        } else {
            throw new InvalidCommand("Invalid command");
        }
    }



    public static void main(String[] args) {
        new Luke().run();
    }

    public void run() {
        ui.printGreeting();

        Scanner in = new Scanner(System.in);
        String line;

        while (true) {
            line = in.nextLine();
            try {
                sendMessage(line);
            } catch (InvalidCommand e) {
                ui.printReply("Sorry, I don't understand you :(");
            }
        }
    }
}
