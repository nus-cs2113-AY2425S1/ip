import java.util.Scanner;
import java.util.Arrays;

public class Atom {
    public static void divider() {
        System.out.println("__________________________________________________");
    }

    public static void printList(String[] list) {
        int index = 1;

        System.out.println("Here is your list:\n");
        for (String item : list) {
            System.out.println(index + ". " + item);
            index++;
        }
    }

    public static void main(String[] args) {

        divider();

        String logo = "    ____   __________  ________  __       __\n"
                + "   / __ \\ |___    ___||  ____  ||  \\     /  |\n"
                + "  / /__\\ \\    |  |    | |    | || |\\\\   //| |\n"
                + " / /    \\ \\   |  |    | |____| || | \\\\_// | |\n"
                + "/_/      \\_\\  |__|    |________||_|  \\_/  |_|\n";

        System.out.println(logo);

        divider();

        System.out.println("Hey there! I'm your friendly chatbot, ATOM!");
        System.out.println("How can i assist you today?");
        System.out.println("\nTIPS:");
        System.out.println("* \"bye\" -> exit program");
        System.out.println("* \"list\" -> view list of tasks");

        divider();

        String line;
        Scanner in = new Scanner(System.in);
        String[] tasksList = new String[100];
        int taskCount = 0;

        System.out.print("Enter command: ");
        line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {
            divider();

            if (line.equalsIgnoreCase("list")) {
                if (taskCount != 0) {
                    printList(Arrays.copyOf(tasksList, taskCount));
                }
                else {
                    System.out.println("Oh oh! List is empty.");
                }
            }
            else {
                tasksList[taskCount] = line;
                taskCount++;
                System.out.println("Added \"" + line + "\" to list");
            }

            divider();
            System.out.print("Enter command: ");
            line = in.nextLine();
        }

        divider();

        System.out.println("Bye Bye. See ya soon!");

        divider();

    }
}
