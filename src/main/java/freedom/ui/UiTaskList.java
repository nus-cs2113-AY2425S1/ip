package freedom.ui;

public class UiTaskList extends Ui {
    public void printAddedTask(String task, int taskIndex) {
        printHeadDivider();
        System.out.println("\tAlright! Added this task for you:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskIndex + " tasks.");
        printTailDivider();
    }

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

    public void printArrayIndexOutOfBoundsException() {
        printHeadDivider();
        System.out.print("""
                \tWe don't have that many tasks??
                \tYou can use list to check
                """);
        printTailDivider();
    }

    public void printListHeader() {
        printHeadDivider();
        System.out.println("\tHere are your tasks:");
    }
}
