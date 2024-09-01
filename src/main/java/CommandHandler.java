public class CommandHandler {

    public static boolean isExitCommand (String command) {
        return command.equals("bye");
    }

    public static void handleCommand(String command) {
        String[] dissectedCommand = command.split(" ");
        if (command.equals("list")) {
            System.out.println("Here are the tasks in your list:");
            TaskList.printList();
        } else if (dissectedCommand[0].equals("mark")) {
            int taskId = Integer.parseInt(dissectedCommand[1]) - 1;
            if (TaskList.isValidTaskId(taskId)) {
                TaskList.markTaskAsDone(taskId);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + TaskList.getSingleTaskDetails(taskId));
            } else {
                System.out.println("Invalid task ID.");
            }
        } else if (dissectedCommand[0].equals("unmark")) {
            int taskId = Integer.parseInt(dissectedCommand[1]) - 1;
            if (TaskList.isValidTaskId(taskId)) {
                TaskList.markTaskAsUndone(taskId);
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println("  " + TaskList.getSingleTaskDetails(taskId));
            } else {
                System.out.println("Invalid task ID.");
            }
        } else if (dissectedCommand[0].equals("todo")) {
            Todo newTodo = new Todo(command.substring(command.indexOf(" ") + 1));
            TaskList.addTask(newTodo);
            System.out.println("added: " + newTodo.getNameWithStatus());
        } else if (dissectedCommand[0].equals("deadline")) {
            String description = command.substring(command.indexOf(" ") + 1,command.indexOf(" /by"));
            String deadline = command.substring(command.indexOf("/by") + 4);
            Deadline newDeadline = new Deadline(description, deadline);
            TaskList.addTask(newDeadline);
            System.out.println("added: " + newDeadline.getNameWithStatus());
        } else {
            Task newTask = new Task(command);
            TaskList.addTask(newTask);
            System.out.println("added: " + newTask.getNameWithStatus());
        }
    }
}
