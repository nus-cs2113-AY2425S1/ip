public class CommandHandler {

    public static boolean isExitCommand (String command) {
        return command.equals("bye");
    }

    public static void handleCommand(String command) {
        String[] dissectedCommand = command.split(" ");
        if (command.equals("list")) {
            handleList();
        } else if (dissectedCommand[0].equals("mark")) {
            handleMark(dissectedCommand);
        } else if (dissectedCommand[0].equals("unmark")) {
            handleUnmark(dissectedCommand);
        } else if (dissectedCommand[0].equals("todo")) {
            handleTodo(command);
        } else if (dissectedCommand[0].equals("deadline")) {
            handleDeadline(command);
        } else if (dissectedCommand[0].equals("event")) {
            handleEvent(command);
        } else {
            handleTask(command);
        }
    }

    private static void handleList() {
        System.out.println("Here are the tasks in your list:");
        TaskList.printList();
    }

    private static void handleTask(String command) {
        Task newTask = new Task(command);
        TaskList.addTask(newTask);
        System.out.println("added: " + newTask.getNameWithStatus());
    }

    private static void handleEvent(String command) {
        String description = command.substring(command.indexOf(" ") + 1, command.indexOf(" /from"));
        String start = command.substring(command.indexOf("/from") + 6, command.indexOf(" /to"));
        String end = command.substring(command.indexOf("/to") + 4);
        Event newEvent = new Event(description, start, end);
        TaskList.addTask(newEvent);
        System.out.println("added: " + newEvent.getNameWithStatus());
    }

    private static void handleDeadline(String command) {
        String description = command.substring(command.indexOf(" ") + 1, command.indexOf(" /by"));
        String deadline = command.substring(command.indexOf("/by") + 4);
        Deadline newDeadline = new Deadline(description, deadline);
        TaskList.addTask(newDeadline);
        System.out.println("added: " + newDeadline.getNameWithStatus());
    }

    private static void handleTodo(String command) {
        Todo newTodo = new Todo(command.substring(command.indexOf(" ") + 1));
        TaskList.addTask(newTodo);
        System.out.println("added: " + newTodo.getNameWithStatus());
    }

    private static void handleUnmark(String[] dissectedCommand) {
        int taskId = Integer.parseInt(dissectedCommand[1]) - 1;
        if (TaskList.isValidTaskId(taskId)) {
            TaskList.markTaskAsUndone(taskId);
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println("  " + TaskList.getSingleTaskDetails(taskId));
        } else {
            System.out.println("Invalid task ID.");
        }
    }

    private static void handleMark(String[] dissectedCommand) {
        int taskId = Integer.parseInt(dissectedCommand[1]) - 1;
        if (TaskList.isValidTaskId(taskId)) {
            TaskList.markTaskAsDone(taskId);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + TaskList.getSingleTaskDetails(taskId));
        } else {
            System.out.println("Invalid task ID.");
        }
    }
}
