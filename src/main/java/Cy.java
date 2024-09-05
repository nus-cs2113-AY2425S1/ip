import java.util.Scanner;

public class Cy {

    public static final int MAX_LIST = 100;

    public static void printLine() {
        System.out.println("______________________________________");
    }

    public static void markOutput(Task task) {
        printLine();

        if (task.isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }

        System.out.println( task.getStatusIcon()+" "+ task.description);
        printLine();
    }

    public static boolean isMarkError(String[] splitInputs, int count) {
        try {
            int index = Integer.parseInt(splitInputs[1]) - 1;

            if (index >= count || index < 0) {
                System.out.println("Please give a valid task number from 1 to " + count);
                return true;
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return true;
        }

        return false;
    }

    public static void markItem(String[] splitInputs, Task[] items, int count) {
        if (isMarkError(splitInputs, count)) {
            return;
        }

        int index = Integer.parseInt(splitInputs[1]) - 1;
        items[index].isDone = true;
        markOutput(items[index]);
    }

    public static void unmarkItem(String[] splitInputs, Task[] items, int count) {
        if (isMarkError(splitInputs, count)) {
            return;
        }

        int index = Integer.parseInt(splitInputs[1]) - 1;
        items[index].isDone = false;
        markOutput(items[index]);
    }

    public static void printList(Task[] items, int count) {
        printLine();
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < count; i++) {
            if (items[i] != null) {
                System.out.println((i + 1) + "." + items[i].getStatusIcon() + " "+ items[i].description);
            }
        }
        printLine();
    }

    public static void addItem(Task[] items, int count, String input) {
        items[count] = new Task(input);
        printLine();
        System.out.println("added: " + input);
        printLine();
    }

    public static void addTodo(Task[] items, int count, String input) {
        items[count] = new Todo(input);
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(items[count].getStatusIcon() + " " + input);
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
        printLine();
    }

    public static void addDeadline(Task[] items, int count, String input) {
        String[] splitInputs = input.split("/by",2);
        String deadline = splitInputs[0] +"(by:" + splitInputs[1] + ")";

        items[count] = new Deadline(deadline);

        System.out.println("Got it. I've added this deadline:");
        System.out.println(items[count].getStatusIcon() + " " + deadline);
        System.out.println("Now you have " + (count + 1) + " tasks in the list");
        printLine();
    }

    public static void main(String[] args) {
        System.out.println("Hello, I'm Cy");
        System.out.println("What can I do for you?");

        Task[] items = new Task[MAX_LIST];
        int count = 0;

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")) {

            String[] splitInputs = input.split(" ");

            if (input.equalsIgnoreCase("list")) {
                printList(items, count);
            } else if (splitInputs[0].equalsIgnoreCase("mark")) {
                markItem(splitInputs, items, count);
            } else if (splitInputs[0].equalsIgnoreCase("unmark")) {
                unmarkItem(splitInputs, items, count);
            } else if (splitInputs[0].equalsIgnoreCase("todo")) {
                addTodo(items, count, input);
                count++;
            } else if (splitInputs[0].equalsIgnoreCase("deadline")){
                addDeadline(items, count, input);
                count++;
            }else {
                addItem(items, count, input);
                count++;
            }
            input = scan.nextLine();
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}