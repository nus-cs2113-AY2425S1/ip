package userinteraction;


/**
 * Makes sense (understands and interprets) the user input
 */
public class Parser {
    /**
     * Interprets which command the user wants to execute and calls
     * the appropriate function respectively
     *
     * @param tasks The current list of tasks
     * @param enteredString The user input
     * @param storage The storage object which is used to write to the file
     * @return If the user wants to exit the program
     */
    public static boolean understandUser(TaskList tasks, String enteredString, Storage storage) {
        if (enteredString.equals("bye")) {
            return true;
        } else if (enteredString.equals("list")) {
            tasks.listTasks();
        } else if (enteredString.contains("mark")) {
            tasks.changeTaskStatus(enteredString, storage);
        } else if (enteredString.contains("delete")) {
            tasks.deleteTask(enteredString, storage);
        } else if (enteredString.contains("find")) {
            tasks.findTask(enteredString);
        } else {
            tasks.addTaskToList(enteredString, storage);
        }
        return false;
    }
}
