public class CommandHandler {

    public static void handleCommand(String[] command, TaskList tasks) throws KaiException {
        switch (command[0]) {
            case "todo":
                handleTodoCommand(command, tasks);
                break;
            case "deadline":
                handleDeadlineCommand(command, tasks);
                break;
            case "event":
                handleEventCommand(command, tasks);
                break;
            case "delete":
                handleDeleteCommand(command, tasks);
                break;
            case "mark":
                handleMarkCommand(command, tasks);
                break;
            case "unmark":
                handleUnmarkCommand(command, tasks);

            default:
                throw new KaiException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void handleTodoCommand(String[] command, TaskList tasks) throws KaiException {
        if (command.length < 2 || command[1].trim().isEmpty()) {
            throw new KaiException("The description of a todo cannot be empty.");
        }
        tasks.addTask(new Todo(command[1].trim()));
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.getTask(tasks.getSize() - 1));
        System.out.println(" Now you have " + tasks.getSize() + " tasks in the list.");
    }

    private static void handleDeadlineCommand(String[] command, TaskList tasks) throws KaiException {
        if (command.length < 2 || command[1].trim().isEmpty()) {
            throw new KaiException("The description of a deadline cannot be empty.");
        }
        String[] parts = command[1].split(" /by ");
        if (parts.length < 2) {
            throw new KaiException("The deadline needs a description and a '/by' date.");
        }
        tasks.addTask(new Deadline(parts[0].trim(), parts[1].trim()));
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.getTask(tasks.getSize() - 1));
        System.out.println(" Now you have " + tasks.getSize() + " tasks in the list.");
    }

    private static void handleEventCommand(String[] command, TaskList tasks) throws KaiException {
        if (command.length < 2 || command[1].trim().isEmpty()) {
            throw new KaiException("The description of an event cannot be empty.");
        }
        String[] parts = command[1].split(" /from ");
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new KaiException("The event needs a description, a '/from' time, and a '/to' time.");
        }
        String[] timeParts = parts[1].split(" /to ");
        tasks.addTask(new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim()));
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks.getTask(tasks.getSize() - 1));
        System.out.println(" Now you have " + tasks.getSize() + " tasks in the list.");
    }

    private static void handleDeleteCommand(String[] command, TaskList tasks) throws KaiException {
        try {
            if (command.length < 2) {
                throw new KaiException("You must specify a task number to delete.");
            }
            int taskNumber = Integer.parseInt(command[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.getSize()) {
                throw new KaiException("Invalid task number. Please provide a valid task number.");
            }
            Task removedTask = tasks.removeTask(taskNumber);
            System.out.println(" Noted. I've removed this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.getSize() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new KaiException("Please enter a valid task number.");
        }
    }

    private static void handleMarkCommand(String[] command, TaskList tasks) throws KaiException {
        try {
            if (command.length < 2) {
                throw new KaiException("You must specify a task number to mark.");
            }
            int taskNumber = Integer.parseInt(command[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.getSize()) {
                throw new KaiException("Task number is out of range.");
            }
            tasks.markTaskAsDone(taskNumber);
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.getTask(taskNumber));
        } catch (NumberFormatException e) {
            throw new KaiException("Please enter a valid task number.");
        }
    }

    private static void handleUnmarkCommand(String[] command, TaskList tasks) throws KaiException {
        try {
            if (command.length < 2) {
                throw new KaiException("You must specify a task number to unmark.");
            }
            int taskNumber = Integer.parseInt(command[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.getSize()) {
                throw new KaiException("Task number is out of range.");
            }
            tasks.markTaskAsNotDone(taskNumber);
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.getTask(taskNumber));
        } catch (NumberFormatException e) {
            throw new KaiException("Please enter a valid task number.");
        }
    }
}

