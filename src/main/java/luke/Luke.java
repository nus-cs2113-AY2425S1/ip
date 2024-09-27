package luke;

import luke.exceptions.IncorrectInput;
import luke.exceptions.InsufficientArguments;
import luke.exceptions.InvalidCommand;
import luke.exceptions.LukeException;
import luke.tasks.Deadline;
import luke.tasks.Event;
import luke.tasks.Task;
import luke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Luke {

    private Ui ui;

    private static final int TASK_TYPE_INDEX = 0;
    private static final int ISDONE_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int BY_INDEX = 3;
    private static final int FROM_INDEX = 3;
    private static final int TO_INDEX = 4;
    private static ArrayList<Task> tasks = new ArrayList<>();

    public Luke() {
        ui = new Ui();
    }

    private static String numberOfTasksMessage() {
        return String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() > 1 ? "tasks" : "task");
    }

    private void list() {
        ui.printDivider();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. ", i + 1);
            System.out.println(tasks.get(i).toString());
        }
        ui.printDivider();
    }

    private static void saveAllTasks() throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        String line;
        for (Task t : tasks) {
            line = t.toString2();
            fw.write(line);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private void executeCommand(CommandType commandType, String[] inputArr) {
        int idx = -1;
        int fromIdx = -1;
        int toIdx = -1;
        String description;
        String deadlineStr;
        String fromStr;
        String toStr;
        String[] args = Arrays.copyOfRange(inputArr, 1, inputArr.length);
        switch (commandType) {
        case BYE:
            ui.printReply("Bye. Hope to see you again soon!");
            try {
                saveAllTasks();
            } catch (IOException e) {
                ui.printReply(String.format("Error occurred while saving data: %s", e.getMessage()));
            }
            System.exit(0);
        case LIST:
            list();
            break;
        case MARK:
            try {
                idx = Integer.parseInt(args[0]) - 1;
            } catch (NumberFormatException e) {
                throw new IncorrectInput("Please input an integer");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InsufficientArguments("Input index of task to mark.");
            }
            if (idx < 0 || idx >= tasks.size()) {
                throw new IncorrectInput("Invalid index");
            }
            tasks.get(idx).setAsDone();
            ui.printReply(String.format("Marked:\n  %s", tasks.get(idx).toString()));
            break;
        case UNMARK:
            try {
                idx = Integer.parseInt(args[0]) - 1;
            } catch (NumberFormatException e) {
                throw new IncorrectInput("Please input an integer");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InsufficientArguments("Input index of task to mark.");
            }
            if (idx < 0 || idx >= tasks.size()) {
                throw new IncorrectInput("Invalid index");
            }
            tasks.get(idx).setAsUndone();
            ui.printReply(String.format("Unmarked:\n  %s", tasks.get(idx).toString()));
            break;
        case TODO:
            if (args.length == 0) {
                throw new InsufficientArguments("todo command needs at least 1 argument.");
            }
            description = String.join(" ", args);
            tasks.add(new ToDo(description));
            ui.printReply(String.format("Task added: %s\n  %s",
                    tasks.get(tasks.size() - 1).toString(), numberOfTasksMessage()));
            break;
        case DEADLINE:
            for (int i = 0; i < args.length; i++) {
                if (args[i].startsWith("/by")) {
                    idx = i;
                }
            }
            if (idx == -1) {
                throw new InsufficientArguments("Deadline needs to be specified");
            }
            description = String.join(" ", Arrays.copyOf(args, idx));
            deadlineStr = String.join(" ", Arrays.copyOfRange(args, idx + 1, args.length));
            tasks.add(new Deadline(description, deadlineStr));
            ui.printReply(String.format("Added deadline: %s\n  %s",
                    tasks.get(tasks.size() - 1).toString(), numberOfTasksMessage()));
            break;

        case EVENT:
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
            description = String.join(" ", Arrays.copyOf(args, fromIdx));
            fromStr = String.join(" ", Arrays.copyOfRange(args, fromIdx + 1, toIdx));
            toStr = String.join(" ", Arrays.copyOfRange(args, toIdx + 1, args.length));
            tasks.add(new Event(description, fromStr, toStr));
            ui.printReply(String.format("Added event: %s\n %s",
                    tasks.get(tasks.size() - 1).toString(), numberOfTasksMessage()));
            break;
        case DELETE:
            if (args.length == 0) {
                throw new InsufficientArguments("Delete command needs an index");
            }
            try {
                idx = Integer.parseInt(args[0]) - 1;
            } catch (NumberFormatException e) {
                throw new IncorrectInput("Please input an integer");
            }
            if (idx < 0 || idx >= tasks.size()) {
                throw new IncorrectInput("Invalid index");
            }
            Task taskToDelete = tasks.get(idx);
            tasks.remove(taskToDelete);
            ui.printReply(String.format("Removed task:\n  %s\nNow you have %d %s in the list.",
                    taskToDelete.toString(), tasks.size(), tasks.size() > 1 ? "tasks" : "task"));
            break;
        default:
            throw new InvalidCommand("Invalid command");
        }
    }

    private void sendMessage(String userInput) {
        String[] inputArr = userInput.split(" ");
        String command = inputArr[0];

        if (command.equalsIgnoreCase("bye")) {
            executeCommand(CommandType.BYE, inputArr);
        } else if (command.equalsIgnoreCase("list")) {
            executeCommand(CommandType.LIST, inputArr);
        } else if (command.equalsIgnoreCase("mark")) {
            try {
                executeCommand(CommandType.MARK, inputArr);
            } catch (LukeException e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("unmark")) {
            try {
                executeCommand(CommandType.UNMARK, inputArr);
            } catch (LukeException e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("todo")){
            try {
                executeCommand(CommandType.TODO, inputArr);
            } catch (InsufficientArguments e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("deadline")){
            try {
                executeCommand(CommandType.DEADLINE, inputArr);
            } catch (InsufficientArguments e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("event")) {
            try {
                executeCommand(CommandType.EVENT, inputArr);
            } catch (InsufficientArguments e) {
                ui.printReply(e.getMessage());
            }
        } else if (command.equalsIgnoreCase("delete")) {
            try {
                executeCommand(CommandType.DELETE, inputArr);
            } catch (InsufficientArguments | IncorrectInput e) {
                ui.printReply(e.getMessage());
            }
        } else {
            throw new InvalidCommand("Invalid command");
        }
    }

    private static void loadSingleTask(String taskStr) {
        String[] taskStrArr = taskStr.split("\\|");
        switch (taskStrArr[TASK_TYPE_INDEX]) {
        case "T":
            tasks.add(new ToDo(taskStrArr[DESCRIPTION_INDEX], taskStrArr[ISDONE_INDEX].equals("1")));
            break;
        case "D":
            tasks.add(new Deadline(taskStrArr[DESCRIPTION_INDEX], taskStrArr[BY_INDEX],
                    taskStrArr[ISDONE_INDEX].equals("1")));
            break;
        case "E":
            tasks.add(new Event(taskStrArr[DESCRIPTION_INDEX], taskStrArr[FROM_INDEX], taskStrArr[TO_INDEX],
                    taskStrArr[ISDONE_INDEX].equals("1")));
            break;
        default:
            break;
        }
    }

    private static void loadSavedTasks() throws IOException {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        File save = new File("data/tasks.txt");
        if (!save.exists()) {
            save.createNewFile();
        }
        Scanner s = new Scanner(save);
        String line;
        while (s.hasNext()) {
            line = s.nextLine();
            loadSingleTask(line);
        }
    }
    public static void main(String[] args) {
        new Luke().run();
    }

    public void run() {
        ui.printGreeting();
        try {
            loadSavedTasks();
        } catch (FileNotFoundException e) {
            // Do nothing
        } catch (IOException e) {
            ui.printReply(String.format("Error: %s", e.getMessage()));
        }
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
