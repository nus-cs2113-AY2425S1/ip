import java.util.ArrayList;

/**
 * Parses and executes user commands.
 */
public class Parser {

    private final ArrayList<Task> tasks;

    /**
     * Initializes the parser with a list of tasks.
     *
     * @param tasks: the list of tasks to be managed.
     */
    public Parser(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    /**
     * Executes the user command.
     *
     * @param command: the user string input command.
     */
    public void execute(String command) {
        if (command.equals("Bye")) {
            System.out.println("Bye! Hope to see you again.");
            Storage.saveTasks(tasks);
            return;
        }

        switch (command.split(" ")[0]) {
            case "list":
                listTasks();
                break;
            case "todo":
                addTodoTask(command);
                break;
            case "deadline":
                addDeadlineTask(command);
                break;
            case "event":
                addEventTask(command);
                break;
            case "delete":
                deleteTask(command);
                break;
            case "mark":
                markTask(command, true);
                break;
            case "unmark":
                markTask(command, false);
                break;
            case "find":
                findTasks(command);
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                break;
        }
    }

    /**
     * List all the tasks.
     */
    private void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("No tasks yet! Please add tasks.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }


    /**
     * Add a "todo" for a task.
     *
     * @param command: user input to be executed.
     */
    private void addTodoTask(String command) {
        if (command.trim().length() <= 4) {
            System.out.println("Description of a todo cannot be empty. Please provide a task description.");
        } else {
            String description = command.substring(5);
            Todo todo = new Todo(description);
            tasks.add(todo);
            System.out.println("Got it. I've added this task\n " + todo);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            Storage.saveTasks(tasks);
        }

    }

    /**
     * Add a task with a deadline.
     *
     * @param command: user input to be executed.
     */
    private void addDeadlineTask(String command) {
        if (command.trim().length() <= 8) {
            System.out.println("Description of a deadline cannot be empty. Please provide a task description and due date.");
        } else {
            try {
                String[] infos = command.substring(9).split(" /by ");
                Deadline deadline = new Deadline(infos[0], infos[1]);
                tasks.add(deadline);
                System.out.println("Got it. I've added this task\n " + deadline);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                Storage.saveTasks(tasks);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * Add an event with a from and to.
     *
     * @param command: user input to be executed.
     */
    private void addEventTask(String command) {
        if (command.trim().length() <= 5) {
            System.out.println("Description of an event cannot be empty. Please provide a task description, from, and end date.");
        } else {
            try {
                String[] infos = command.substring(6).split(" /from | /to ");
                Event event = new Event(infos[0], infos[1], infos[2]);
                tasks.add(event);
                System.out.println("Got it. I've added this task\n " + event);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                Storage.saveTasks(tasks);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Delete a task from the current storage.
     *
     * @param command: user input to be executed.
     */
    private void deleteTask(String command) {
        try {
            if (command.trim().length() <= 6) {
                System.out.println("Please provide the task index to delete.");
                return;
            }
            int taskInt = Integer.parseInt(command.split(" ")[1]) - 1;
            if (taskInt < 0 || taskInt >= tasks.size()) {
                throw new Exception("Invalid task number.");
            }
            Task removedTask = tasks.remove(taskInt);
            System.out.println("I've removed this task for you: ");
            System.out.println(removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            Storage.saveTasks(tasks);

        } catch (Exception e){
            System.out.println(("Error: " + e.getMessage()));
        }
    }

    /**
     * Mark or unmark a task.
     *
     * @param command: user input to be executed.
     * @param isDone: to do mark or do unmark execution for a task.
     */
    private void markTask(String command, boolean isDone) {
        try {
            if (command.equals("mark") || command.equals("unmark")) {
                System.out.println("Please provide the task index to mark or unmark.");
                return;
            }
            int taskInt = Integer.parseInt(command.split(" ")[1]) - 1;
            if (taskInt < 0 || taskInt >= tasks.size()) {
                throw new Exception("Invalid task number.");
            }
            Task task = tasks.get(taskInt);
            if (isDone) task.markAsDone();
            else task.markAsUndone();
            Storage.saveTasks(tasks);
            System.out.println("Task marked as " + (isDone ? "done" : "not done") + ":\n" + task);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * To find a task that contains keyword.
     *
     * @param command: user input to be executed.
     */
    private void findTasks(String command) {
        if (command.equals("find")) {
            System.out.println("Please provide the keyword.");
            return;
        }
        String keyword = command.substring(5);
        System.out.println("Here are the matching tasks in your list:");

        int count = 1;
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                System.out.println(count + ". " + task);
                count++;
            }

        }
    }

}
