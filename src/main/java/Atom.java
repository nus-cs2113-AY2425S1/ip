import java.util.Scanner;
import java.util.Arrays;

public class Atom {
    public static void divider() {
        System.out.println("__________________________________________________");
    }

    public static void printList(Task[] list) {
        int index = 1;

        System.out.println("Here is your list:\n");
        for (Task item : list) {
            System.out.println(index + "." + "[" + item.getStatus() + "] "  + item.getItem());
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
        System.out.println("\nUSER GUIDE:");
        System.out.println("* \"bye\" -> exit program");
        System.out.println("* \"list\" -> view list of tasks");
        System.out.println("* \"mark <task id>\" -> mark task as DONE");
        System.out.println("* \"unmark <task id>\" -> mark task as UNDONE");

        divider();

        String line;
        Scanner in = new Scanner(System.in);
        Task[] tasksList = new Task[100];

        System.out.print("Enter command: ");
        line = in.nextLine().trim();
        String[] words = line.split(" ");
        String keyword = words[0].toLowerCase();

        while (!line.equalsIgnoreCase("bye")) {
            divider();

            if (line.equalsIgnoreCase("list")) {
                if (Task.getTaskCount() != 0) {
                    printList(Arrays.copyOf(tasksList, Task.getTaskCount()));
                }
                else {
                    System.out.println("Oh oh! List is empty.");
                }
            }
            else if (keyword.equals("mark") || (keyword.equals("unmark"))) {
                try {
                    int taskId = Integer.parseInt(words[1]) - 1;
                    if (taskId < Task.getTaskCount() && taskId >= 0) {
                        Task currTask = tasksList[taskId];
                        if (keyword.equals("mark")) {
                            currTask.markAsDone();
                            System.out.println("Wonderful! Task successfully marked as DONE!");
                        } else {
                            currTask.markAsUndone();
                            System.out.println("Got it. Task successfully marked as UNDONE!");
                        }
                        System.out.println("[" + currTask.getStatus() + "] " + currTask.getItem());
                    } else {
                        System.out.println("Whoops! Task not found.");
                    }
                } catch (Exception exception) {
                    System.out.println("Oops! Cannot identify task id.");
                    System.out.println("Please ensure task id is valid.");
                }
            }
            else {
                Task task = new Task(line);
                tasksList[Task.getTaskCount() - 1] = task;
                System.out.println("Added \"" + line + "\" to list");
            }

            divider();

            System.out.print("Enter command: ");
            line = in.nextLine().trim();
            words = line.split(" ");
            keyword = words[0].toLowerCase();
        }

        divider();

        System.out.println("Bye Bye. See ya soon!");

        divider();

    }
}
