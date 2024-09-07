package Taylor.command;

import Taylor.task.Task;
import Taylor.task.TaskList;
import Taylor.task.Todo;
import Taylor.task.Deadline;
import Taylor.task.Event;

import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Taylor {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Taylor(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TaylorException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        String input = ui.readCommand();
        while (true) {
            try {
                input = operate(input);
                if (input.equalsIgnoreCase("bye")) {
                    break;
                }
            } catch (TaylorException e) {
                ui.println(e.getMessage());
                input = ui.readCommand();
            }
        }

        ui.showGoodbye();
    }

    private String operate(String input) throws TaylorException {
        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0].toLowerCase();
        String args = (inputParts.length > 1) ? inputParts[1] : "";

        switch (command) {
            case "list", "ls" -> ui.showTasks(tasks);
            case "mark" -> {
                try {
                    int index = Integer.parseInt(args) - 1;
                    tasks.markTask(index);
                    ui.showTaskAdded(tasks.get(index), tasks);
                    storage.save(tasks);
                } catch (IOException e) {
                    ui.println("Unable to write to file");
                } catch (NumberFormatException e) {
                    ui.println("Unable to read from string");
                }

            }
            case "unmark" -> {
                int index = Integer.parseInt(args) - 1;
                tasks.unmarkTask(index);
                ui.showTaskAdded(tasks.get(index), tasks);
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    ui.println("Unable to write to file");
                }
            }
            case "todo" -> {
                Task task = new Todo(args.trim());
                tasks.add(task);
                ui.showTaskAdded(task, tasks);
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    ui.println("Unable to write to file");
                }
            }
            case "event" -> {
                try {
                    Event event = getEvent(args);
                    tasks.add(event);
                    ui.showTaskAdded(event, tasks);
                    storage.save(tasks);
                } catch (StringIndexOutOfBoundsException e) {
                    ui.println("OOPS!!! The format of the event command is incorrect.");
                } catch (IOException e) {
                    ui.println("Unable to write to the file.");
                }
            }
            case "deadline" -> {
                try {
                    Task task = getDeadline(args);
                    tasks.add(task);
                    ui.showTaskAdded(task, tasks);
                    storage.save(tasks);
                } catch (IOException e) {
                    ui.println("Unable to write to file");
                } catch (DateTimeParseException e) {
                    ui.println("Unable to parse the date, please the format yyyy-mm-dd");
                } catch (TaylorException e) {
                    ui.println(e.getMessage());
                }
            }
            case "delete" -> {
                int index = Integer.parseInt(args) - 1;
                String task = tasks.get(index).toString();
                tasks.remove(index);
                ui.showTaskDeleted(task, tasks);
                try {
                    storage.save(tasks);
                } catch (IOException e) {
                    ui.println("Unable to write to file");
                }
            }
            case "find" -> {
                try {
                    ui.showLine();
                    String result = tasks.find(args);
                    if (result==null) {
                        System.out.println("There is no matching task found.");
                    } else {
                        System.out.println("Here are the matching tasks in your list:");
                        System.out.println(result);
                    }
                    ui.showLine();
                } catch (StringIndexOutOfBoundsException e) {
                    ui.showLine();
                    System.out.println("OOPS!!! The keyword cannot be empty.");
                    ui.showLine();
                }
            }
            default -> throw new TaylorException(("Unknown command: " + command));
        }
        return ui.readCommand();
    }

    private static Event getEvent(String args) throws TaylorException {
        int fromIndex = args.indexOf("/from");
        int toIndex = args.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
            throw new TaylorException("Invalid event format. Make sure to use /from and /to correctly.");
        }

        String description = args.substring(0, fromIndex).trim();
        String from = args.substring(fromIndex + 6, toIndex).trim();
        String to = args.substring(toIndex + 4).trim();

        return new Event(description, from, to);
    }

    private static Deadline getDeadline(String args) throws TaylorException {
        int byIndex = args.indexOf("/by");

        if (byIndex == -1) {
            throw new TaylorException("Invalid deadline");
        }

        String description = args.substring(0, byIndex).trim();
        String by = args.substring(byIndex + 4).trim();

        return new Deadline(description, by);
    }

    public static void main(String[] args) {
        new Taylor("data/tasks.txt").run();
    }
}