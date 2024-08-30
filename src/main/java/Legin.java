import java.util.Scanner;

public class Legin {
    private static Task[] tasks = new Task[100];
    private static int currentTaskCount = 0;

    public static void horizontalLine() {
        System.out.println("______________________" +
                "______________________________________");
    }
    public static void greet() {
        horizontalLine();
        System.out.println("Hello! I'm Legin, your best online companion!");
        System.out.println("What can I do for you today my friend :D");
        horizontalLine();
    }
    public static void bye() {
        horizontalLine();
        System.out.println("Bye " +
            Character.toString(0x1F44B) +
            ". Hope to see you again really soon! " +
            Character.toString(0x1F608));
        horizontalLine();
    }

    public static void echo(String input) {
        tasks[currentTaskCount] = new Task(input);
        currentTaskCount++;
        horizontalLine();
        System.out.println("added: " + input);
        horizontalLine();
    }

    public static void list() {
        horizontalLine();
        for (int i = 0; i < currentTaskCount; i++ ) {
            String marker;
            if (tasks[i].getIsDone()) {
                marker = "X";
            } else {
                marker = " ";
            }
            System.out.println(i + 1 + ".[" + marker + "] " + tasks[i].getTask());
        }
        horizontalLine();
    }

    public static void markTask(String input) {
        horizontalLine();
        String[] words = input.split(" ");
        int taskNumber = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("mark")) {
                taskNumber = Integer.parseInt(words[i + 1]) - 1;
                break;
            }
        }
        tasks[taskNumber].setIsDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + tasks[taskNumber].getTask());
        horizontalLine();
    }

    public static void unmarkTask(String input) {
        String[] words = input.split(" ");
        int taskNumber = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("mark")) {
                taskNumber = Integer.parseInt(words[i + 1]) - 1;
                break;
            }
        }
        tasks[taskNumber].setIsDone(false);
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println("  [ ] " + tasks[taskNumber].getTask());
        horizontalLine();
    }

    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        greet();
        while (true) {
            command = in.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                list();
            } else if (command.contains("unmark")) {
                unmarkTask(command);
            } else if (command.contains("mark")) {
                markTask(command);
            } else {
                echo(command);
            }
        }
        bye();
    }
}
