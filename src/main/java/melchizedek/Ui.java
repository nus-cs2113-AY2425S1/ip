package melchizedek;

public class Ui {
    public static final String SEPARATOR = "\t____________________________________________________________";


    public static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    public static void sayHelloToUser() {
        printSeparator();
        System.out.println("\tHello! I'm Melchizedek.");
        System.out.println("\tHow can I be a blessing to you?");
        printSeparator();
    }

    public static void sayByeToUser() {
        System.out.println("\tGoodbye. Hope to see you again soon! May peace be upon you.");
        printSeparator();
    }

    public static void listCommands() {
        System.out.println("\tHere is the list of commands:");
        System.out.println("\t  to add a todo: todo DESCRIPTION");
        System.out.println("\t  to add a deadline: deadline DESCRIPTION /by BY");
        System.out.println("\t  to add an event: event DESCRIPTION /from FROM /to TO");
        System.out.println("\t  to delete a task: delete TASK_NUMBER");
        System.out.println("\t  to mark a task as done: mark TASK_NUMBER");
        System.out.println("\t  to unmark a task as done: unmark TASK_NUMBER");
        System.out.println("\t  to display all tasks on the list: list");
        System.out.println("\t  to exit: bye");
    }
}
