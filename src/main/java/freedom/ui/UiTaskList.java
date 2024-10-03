package freedom.ui;

public class UiTaskList extends Ui {

    /**
     * Indicates the task is added and counts the total number of tasks.
     *
     * @param task added task.
     * @param taskIndex index position of the task in TaskList.
     */
    public void printAddedTask(String task, int taskIndex) {
        printHeadDivider();
        System.out.println("\tAlright! Added this task for you:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskIndex + " tasks.");
        printTailDivider();
    }

    /**
     * Indicates the task has been edited.
     *
     * @param task edited task.
     * @param action specific edit to the task.
     */
    public void printEditedTask(String task, String action) {
        printHeadDivider();
        String message;
        switch (action) {
        case "mark":
            message = "\tMarked this task as done! Good Job!";
            break;
        case "unmark":
            message = "\tUnmarked this task! Get it done!";
            break;
        case "delete":
            message = "\tDeleted this task:";
            break;
        default:
            message = "";
        }
        System.out.println(message);
        System.out.println("\t  " + task);
        printTailDivider();
    }

    /**
     * Indicates the requested task index cannot be reached.
     */
    public void printArrayIndexOutOfBoundsException() {
        printHeadDivider();
        System.out.print("""
                \tWe don't have that many tasks??
                \tYou can use list to check
                """);
        printTailDivider();
    }

    /**
     * Indicates the list of tasks follows.
     */
    public void printListHeader() {
        printHeadDivider();
        System.out.println("\tHere are your tasks:");
    }
}
