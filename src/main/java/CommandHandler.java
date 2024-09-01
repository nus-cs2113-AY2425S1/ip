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
        } else {
            Task newTask = new Task(command);
            TaskList.addTask(newTask);
            System.out.println("added: " + newTask.getNameWithStatus());
        }
    }
}
