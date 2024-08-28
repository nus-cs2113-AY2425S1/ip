import java.util.Scanner;

public class Conglo {

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            isDone = true;
        }

        public void markAsNotDone() {
            isDone = false;
        }
    }

    protected static String command;

    public static void main(String[] args) {
        greetings();
        Scanner scanner = new Scanner(System.in);
        Task[] listing = new Task[100];
        command = scanner.nextLine();
        int i = 0;
        Task input = new Task(command);
        while (!input.description.equals("bye")) {
            String[] words = input.description.split(" ");
            if (input.description.equals("list")) {
                listOut(listing, i);
            } else if (words[0].equals("mark") || words[0].equals("unmark")) {
                markList(words, listing, i);
            } else {
                echo(input.description);
                listing[i] = input;
                i++;
            }
            command = scanner.nextLine();
            input = new Task(command);
        }
        scanner.close();
        sayGoodbye();
    }

    public static void printLineSeparator() {
        System.out.println("-------------------------------------");
    }

    public static void greetings() {
        System.out.println("Hola! I'm Conglo the friendly bot.");
        System.out.println("What brings you here today?");
        printLineSeparator();
    }

    public static void echo(String input) {
        System.out.println("added: " + input);
        printLineSeparator();
    }

    public static void listOut(Task[] listing, int size) {
        int i = 0;
        while (i < size) {
            System.out.print((i + 1) + ". [" + listing[i].getStatusIcon() + "]");
            System.out.println(listing[i].description);
            i++;
        }
        printLineSeparator();
    }

    public static void markList(String[] words, Task[] listing, int size) {
        int i = Integer.parseInt(words[1]) - 1;
        if (i >= size) {
            System.out.println("Invalid task number");
            return;
        }
        if (words[0].equals("mark")) {
            listing[i].markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            listing[i].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("[" + listing[i].getStatusIcon() + "] " + listing[i].description );
    }

    public static void sayGoodbye() {
        System.out.println("Goodbye. See you next time!");
        printLineSeparator();
    }
}
