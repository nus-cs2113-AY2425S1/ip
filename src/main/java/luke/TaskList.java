package luke;

import luke.exceptions.IncorrectInputException;
import luke.exceptions.InsufficientArgumentsException;
import luke.exceptions.LukeException;
import luke.tasks.Deadline;
import luke.tasks.Event;
import luke.tasks.Task;
import luke.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    // Constants
    private static final int TASK_TYPE_INDEX = 0;
    private static final int ISDONE_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int BY_INDEX = 3;
    private static final int FROM_INDEX = 3;
    private static final int TO_INDEX = 4;

    public ArrayList<Task> tasks = new ArrayList<>();
    Ui ui;

    public TaskList(ArrayList<String> saveStrings) {
        ui = new Ui();
        if (saveStrings == null) return;
        for (String line : saveStrings) {
            loadSingleTask(line);
        }
    }
    private void loadSingleTask(String taskStr) {
        String[] taskStrArr = taskStr.split("\\|");
        switch (taskStrArr[TASK_TYPE_INDEX]) {
        case "T":
            tasks.add(new ToDo(taskStrArr[DESCRIPTION_INDEX], taskStrArr[ISDONE_INDEX].equals("1")));
            break;
        case "D":
            tasks.add(new Deadline(taskStrArr[DESCRIPTION_INDEX], toLocalDateTime(taskStrArr[BY_INDEX]),
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

    public String numberOfTasksMessage() {
        return String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() > 1 ? "tasks" : "task");
    }

    public int getSize() {
        return tasks.size();
    }

    public void list() {
        ui.printDivider();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. ", i + 1);
            System.out.println(tasks.get(i).toString());
        }
        ui.printDivider();
    }

    public void mark(String[] inputs) {
        int idx;
        String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
        try {
            idx = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Please input an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InsufficientArgumentsException("Input index of task to mark.");
        }
        if (idx < 0 || idx >= getSize()) {
            throw new IncorrectInputException("Invalid index");
        }
        tasks.get(idx).setAsDone();
        ui.printReply(String.format("Marked:\n  %s", tasks.get(idx).toString()));
    }

    public void unmark(String[] inputs) {
        int idx;
        String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
        try {
            idx = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Please input an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InsufficientArgumentsException("Input index of task to mark.");
        }
        if (idx < 0 || idx >= getSize()) {
            throw new IncorrectInputException("Invalid index");
        }
        tasks.get(idx).setAsUndone();
        ui.printReply(String.format("Unmarked:\n  %s", tasks.get(idx).toString()));
    }

    public void addToDo(String[] inputs) {
        String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
        if (args.length == 0) {
            throw new InsufficientArgumentsException("todo command needs at least 1 argument.");
        }
        String description = String.join(" ", args);
        tasks.add(new ToDo(description));
        ui.printReply(String.format("Task added: %s\n  %s",
                tasks.get(getSize() - 1).toString(), numberOfTasksMessage()));
    }

    private LocalDateTime toLocalDateTime(String datetime) {
        DateTimeFormatter fromString = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime localDateTimeObj;
        try {
            localDateTimeObj = LocalDateTime.parse(datetime, fromString);
        } catch (DateTimeParseException e) {
            throw new LukeException("Incorrect format. Correct format: yyyy-MM-dd HHmm");
        }
        return localDateTimeObj;
    }

    public void addDeadline(String[] inputs) {
        String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
        int idx = -1;
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("/by")) {
                idx = i;
            }
        }
        if (idx == -1) {
            throw new InsufficientArgumentsException("Deadline needs to be specified");
        }
        String description = String.join(" ", Arrays.copyOf(args, idx));
        String deadlineStr = String.join(" ", Arrays.copyOfRange(args, idx + 1, args.length));
        // Create LocalDateTime obj based on deadlineStr
        LocalDateTime due = toLocalDateTime(deadlineStr);
        tasks.add(new Deadline(description, due));
        ui.printReply(String.format("Added deadline: %s\n  %s",
                tasks.get(tasks.size() - 1).toString(), numberOfTasksMessage()));
    }

    public void addEvent(String[] inputs) {
        String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
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
            throw new InsufficientArgumentsException("From when???");
        }
        if (toIdx == -1) {
            throw new InsufficientArgumentsException("To when???");
        }
        String description = String.join(" ", Arrays.copyOf(args, fromIdx));
        String fromStr = String.join(" ", Arrays.copyOfRange(args, fromIdx + 1, toIdx));
        String toStr = String.join(" ", Arrays.copyOfRange(args, toIdx + 1, args.length));
        tasks.add(new Event(description, fromStr, toStr));
        ui.printReply(String.format("Added event: %s\n %s",
                tasks.get(getSize() - 1).toString(), numberOfTasksMessage()));

    }

    public void deleteTask(String[] inputs) {
        String[] args = Arrays.copyOfRange(inputs, 1, inputs.length);
        int idx;
        if (args.length == 0) {
            throw new InsufficientArgumentsException("Delete command needs an index");
        }
        try {
            idx = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("Please input an integer");
        }
        if (idx < 0 || idx >= tasks.size()) {
            throw new IncorrectInputException("Invalid index");
        }
        Task taskToDelete = tasks.get(idx);
        tasks.remove(taskToDelete);
        ui.printReply(String.format("Removed task:\n  %s\nNow you have %d %s in the list.",
                taskToDelete.toString(), getSize(), getSize() > 1 ? "tasks" : "task"));
    }



}
