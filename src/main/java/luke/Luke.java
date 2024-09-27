package luke;

import luke.exceptions.IncorrectInput;
import luke.exceptions.InsufficientArguments;
import luke.exceptions.InvalidCommand;
import luke.exceptions.LukeException;
import luke.tasks.Deadline;
import luke.tasks.Event;
import luke.tasks.Task;
import luke.tasks.ToDo;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

    private void list() {
        taskList.list();
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

    private void mark(String[] inputArr) {
        int idx = -1;
        String[] args = Arrays.copyOfRange(inputArr, 1, inputArr.length);
        try {
            idx = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectInput("Please input an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InsufficientArguments("Input index of task to mark.");
        }
        if (idx < 0 || idx >= taskList.getSize()) {
            throw new IncorrectInput("Invalid index");
        }
        taskList.tasks.get(idx).setAsDone();
        ui.printReply(String.format("Marked:\n  %s", taskList.tasks.get(idx).toString()));
    }
    private void unmark(String[] inputArr) {
        int idx = -1;
        String[] args = Arrays.copyOfRange(inputArr, 1, inputArr.length);
        try {
            idx = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectInput("Please input an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InsufficientArguments("Input index of task to mark.");
        }
        if (idx < 0 || idx >= taskList.getSize()) {
            throw new IncorrectInput("Invalid index");
        }
        taskList.tasks.get(idx).setAsUndone();
        ui.printReply(String.format("Unmarked:\n  %s", taskList.tasks.get(idx).toString()));
    }

    private void addDeadline(String[] inputArr) {
        String[] args = Arrays.copyOfRange(inputArr, 1, inputArr.length);
        int idx = -1;
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("/by")) {
                idx = i;
            }
        }
        if (idx == -1) {
            throw new InsufficientArguments("Deadline needs to be specified");
        }
        String description = String.join(" ", Arrays.copyOf(args, idx));
        String deadlineStr = String.join(" ", Arrays.copyOfRange(args, idx + 1, args.length));
        taskList.tasks.add(new Deadline(description, deadlineStr));
        ui.printReply(String.format("Added deadline: %s\n  %s",
                taskList.tasks.get(taskList.tasks.size() - 1).toString(), numberOfTasksMessage()));
    }

    private void addEvent(String[] inputArr) {
        String[] args = Arrays.copyOfRange(inputArr, 1, inputArr.length);
        int fromIdx = -1;
        int toIdx = -1;
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("/from")) {
                fromIdx = i;
            } else if (args[i].startsWith("/to")) {
                toIdx = i;
            }
        }
        if (fromIdx == -1) {
            throw new InsufficientArguments("From when???");
        }
        if (toIdx == -1) {
            throw new InsufficientArguments("To when???");
        }
        String description = String.join(" ", Arrays.copyOf(args, fromIdx));
        String fromStr = String.join(" ", Arrays.copyOfRange(args, fromIdx + 1, toIdx));
        String toStr = String.join(" ", Arrays.copyOfRange(args, toIdx + 1, args.length));
        taskList.tasks.add(new Event(description, fromStr, toStr));
        ui.printReply(String.format("Added event: %s\n %s",
                taskList.tasks.get(taskList.getSize() - 1).toString(), numberOfTasksMessage()));
    }

    private void addToDo(String[] inputArr) {
        String[] args = Arrays.copyOfRange(inputArr, 1, inputArr.length);
        if (args.length == 0) {
            throw new InsufficientArguments("todo command needs at least 1 argument.");
        }
        String description = String.join(" ", args);
        taskList.tasks.add(new ToDo(description));
        ui.printReply(String.format("Task added: %s\n  %s",
                taskList.tasks.get(taskList.getSize() - 1).toString(), numberOfTasksMessage()));
    }

    private void deleteTask(String[] inputArr) {
        String[] args = Arrays.copyOfRange(inputArr, 1, inputArr.length);
        int idx = -1;
        if (args.length == 0) {
            throw new InsufficientArguments("Delete command needs an index");
        }
        try {
            idx = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectInput("Please input an integer");
        }
        if (idx < 0 || idx >= taskList.tasks.size()) {
            throw new IncorrectInput("Invalid index");
        }
        Task taskToDelete = taskList.tasks.get(idx);
        taskList.tasks.remove(taskToDelete);
        ui.printReply(String.format("Removed task:\n  %s\nNow you have %d %s in the list.",
                taskToDelete.toString(), taskList.getSize(), taskList.getSize() > 1 ? "tasks" : "task"));
    }

    private void sendMessage(String userInput) {
        String[] inputArr = userInput.split(" ");
        String command = inputArr[0];

        if (command.equalsIgnoreCase("bye")) {
            exitBot();
        } else if (command.equalsIgnoreCase("list")) {
            list();
        } else if (command.equalsIgnoreCase("mark")) {
            try {
                mark(inputArr);
            } catch (LukeException e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("unmark")) {
            try {
                unmark(inputArr);
            } catch (LukeException e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("todo")){
            try {
                addToDo(inputArr);
            } catch (InsufficientArguments e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("deadline")){
            try {
                addDeadline(inputArr);
            } catch (InsufficientArguments e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("event")) {
            try {
                addEvent(inputArr);
            } catch (InsufficientArguments e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("delete")) {
            try {
                deleteTask(inputArr);
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
