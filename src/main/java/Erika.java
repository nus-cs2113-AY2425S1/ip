import java.util.Scanner;
import java.util.ArrayList;

public class Erika {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();h

        String logo =
                " _______   ________  ___  ___  __    ________     \n" +
                "|\\  ___ \\ |\\   __  \\|\\  \\|\\  \\|\\  \\ |\\   __  \\    \n" +
                "\\ \\   __/|\\ \\  \\|\\  \\ \\  \\ \\  \\/  /|\\ \\  \\|\\  \\   \n" +
                " \\ \\  \\_|/_\\ \\   _  _\\ \\  \\ \\   ___  \\ \\   __  \\  \n" +
                "  \\ \\  \\_|| \\ \\  \\\\  \\\\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \n" +
                "   \\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\\\ \\__\\ \\__\\ \\__\\\n" +
                "    \\|_______|\\|__|\\|__|\\|__|\\|__| \\|__|\\|__|\\|__|";
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Erika\n What can I do for you?");
        System.out.println("____________________________________________________________");
        line = in.nextLine();

        while (!line.equals("bye")) {
            line = line.trim();
            if (line.equals("list")) {
                System.out.println("____________________________________________________________");
                if (Task.getTaskSize() == 0) {
                    System.out.println("It seems that there are no tasks! Please consider adding some!");
                }
                for (int i = 0; i < Task.getTaskSize(); i++) {
                    System.out.println(i+1 + ".[" + (tasks.get(i).getMark()?"x":" ") + "] "
                            + tasks.get(i).getDescription());
                }
                System.out.println("____________________________________________________________");
            } else {
                int markIndex = -1;
                String digitString = line.replaceAll("[^0-9]", "");
                if (!digitString.isEmpty()) {
                    markIndex = Integer.parseInt(digitString);
                }
                if(markIndex > Task.getTaskSize()) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Error! Task " + markIndex + " is out of bounds!");
                    System.out.println("____________________________________________________________");
                } else {
                     if(line.contains("unmark")) {
                        tasks.get(markIndex-1).setMark(false);
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as not done yet:");
                        System.out.println("[ ] " + tasks.get(markIndex-1).getDescription());
                        System.out.println("____________________________________________________________");
                    } else if(line.contains("mark")) {
                        tasks.get(markIndex-1).setMark(true);
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[X] " + tasks.get(markIndex-1).getDescription());
                        System.out.println("____________________________________________________________");
                    } else {
                        Task newTask = new Task(line);
                        tasks.add(newTask);
                        System.out.println("____________________________________________________________");
                        System.out.println("added: " + newTask.getDescription());
                        System.out.println("____________________________________________________________");
                        Task.incrementTaskSize();
                    }
                }
            }
            line = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
