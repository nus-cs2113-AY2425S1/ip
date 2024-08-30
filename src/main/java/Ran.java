import java.util.Scanner;

public class Ran {
    private static boolean isTerminated = false;
    private static int listCount = 0;

    public static void greet() {
        System.out.println("____________________________________________________________");
        String logo = "     ___           ___           ___\n"
                + "    /\\  \\         /\\  \\         /\\__\\\n"
                + "   /::\\  \\       /::\\  \\       /::|  |\n"
                + "  /:/\\:\\  \\     /:/\\:\\  \\     /:|:|  |\n"
                + " /::\\~\\:\\  \\   /::\\~\\:\\  \\   /:/|:|  |__\n"
                + "/:/\\:\\ \\:\\__\\ /:/\\:\\ \\:\\__\\ /:/ |:| /\\__\\\n"
                + "\\/_|::\\/:/  / \\/__\\:\\/:/  / \\/__|:|/:/  /\n"
                + "   |:|::/  /       \\::/  /      |:/:/  /\n"
                + "   |:|\\/__/        /:/  /       |::/  /\n"
                + "   |:|  |         /:/  /        /:/  /\n"
                + "    \\|__|         \\/__/         \\/__/\n";
        System.out.println(logo + "Hello! I'm Ran.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void bidFarewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String input, Task[] list) {
        list[listCount] = new Task(input);
        listCount++;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + input);
        System.out.println("____________________________________________________________");
    }

    public static void showList(Task[] list) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < listCount; i++) {
            System.out.println((i + 1) + ".[" + list[i].getStatusIcon() + "] " + list[i].getDescription());
        }
        System.out.println("____________________________________________________________");
    }

    public static void markTask(Task[] list, String taskNum) {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        list[taskNumber].setAsDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [X] " + list[taskNumber].getDescription());
        System.out.println("____________________________________________________________");
    }

    public static void unmarkTask(Task[] list, String taskNum) {
        int taskNumber = Integer.parseInt(taskNum) - 1;
        list[taskNumber].setAsUndone();
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  [ ] " + list[taskNumber].getDescription());
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        greet();

        String input;
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];

        while(!isTerminated) {
            input = in.nextLine();
            String[] instruction = input.split(" ");
            if (input.equals("bye")) {
                isTerminated = true;
            } else if (input.equals("list")) {
                showList(list);
            } else if (instruction.length > 1 && instruction[0].equals("mark")) {
                markTask(list, instruction[1]);
            } else if (instruction.length > 1 && instruction[0].equals("unmark")) {
                unmarkTask(list, instruction[1]);
            } else {
                addTask(input, list);
            }
        }

        bidFarewell();
    }
}
