import java.util.Scanner;

public class Ui {

    static Scanner in = new Scanner(System.in);

    public Ui() {

    }

    public void showWelcome() {
        System.out.println("Hello! I'm Anke.");
        System.out.println("What can I do for you?\n");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public String readCommand() {
        String line;
        line = in.nextLine().trim();
        return line;
    }

    public void showLoadingError() {
        System.out.println("No input file found.\n");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public static void showLine() {
        System.out.println("Got it. I've added this task:");
        System.out.println(TaskList.getTask(Storage.getCount()));
        Storage.incrementCount();
        System.out.println("Now you have " + Storage.getCount() + " tasks in the list.\n");
    }

    public static void printList() {
        for (int i = 0; i < Storage.getCount(); i++) {
            System.out.println((i + 1) + ". " + TaskList.getTask(i));
        }
        System.out.println("");
    }

    public static void handleWrongFormat() {
        System.out.println("Please follow the format (parameter inside {} must be non-empty!) : \n");
        System.out.println("list : visualizing tasks");
        System.out.println("mark {int n} : set task number {n} as done");
        System.out.println("unmark {int n} : set task number {n} as not done");
        System.out.println("todo {String s} : create todo with description {s}");
        System.out.println("deadline {String s1} /by {String s2} : create deadline with description {s1} and due date {s2}");
        System.out.println("event {String s1} /from {String s2} /to {String s3} : create event with description {s1} from {s2} to {s3}");
        System.out.println("delete {int n} : remove task number {n} from the list\n");
    }
}
