import java.util.Scanner;

public class Tars {
    public static void main(String[] args) {
        String logo = " _____                        \n"
                + "|_   _|__  _ __ __ _ _ __ ___  \n"
                + "  | |/ _ \\| '__/ _` | '_ ` _ \\ \n"
                + "  | | (_) | | | (_| | | | | | |\n"
                + "  |_|\\___/|_|  \\__,_|_| |_| |_|\n";

        // Opening speech.
        System.out.println("    " + "------------------------------------------------------------");
        System.out.println("    " + "Hello! I'm Tars.");
        System.out.println("    " + "Ready to conquer the world? Or maybe just help with something smaller? What can I do for you?");
        System.out.println("    " + "------------------------------------------------------------");

        // Define an array to store tasks with a maximum capacity of 100 tasks, using the Task class to store data.
        Task[] tasks = new Task[100];
        int taskCount = 0;

        // Waiting for user input.
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true)
        {
            // Read user input.
            input = scanner.nextLine();

            // Exit the program when the user inputs "bye".
            if (input.equals("bye"))
            {
                System.out.println("    " + "------------------------------------------------------------");
                System.out.println("    " + "Oh no, you're leaving already? Fine, be that way. But don't forget, I'll be right here waiting... forever!");
                System.out.println("    " + "------------------------------------------------------------");
                break;
            }

            // List all tasks when the user inputs "list".
            else if (input.equals("list"))
            {
                System.out.println("    " + "------------------------------------------------------------");
                System.out.println("    " + "Here are your tasks: ");
                for (int i = 0; i < taskCount; i++)
                {
                    System.out.println("    " + (i + 1) + ". " + tasks[i]);
                }
                printTaskCount(taskCount);
                System.out.println("    " + "------------------------------------------------------------");
            }
            else if (input.startsWith("todo"))
            {
                String taskDescription = input.substring(5);
                tasks[taskCount] = new Todo(taskDescription);
                taskCount++;
                System.out.println("Got it. I've added this task: ");
                System.out.println(tasks[taskCount - 1]);
                printTaskCount(taskCount);
            }
            else if (input.startsWith("deadline"))
            {
                String[] parts = input.substring(9).split("/by");
                tasks[taskCount] = new Deadline(parts[0].trim(), parts[1].trim());
                taskCount++;
                System.out.println("Got it. I've added this task: ");
                System.out.println(tasks[taskCount - 1]);
                printTaskCount(taskCount);
            }
            else if (input.startsWith("event"))
            {
                String[] parts = input.substring(6).split("/from|/to");
                tasks[taskCount] = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                taskCount++;
                System.out.println("Got it. I've added this task: ");
                System.out.println(tasks[taskCount - 1]);
                printTaskCount(taskCount);
            }
            // Mark the specified task as done when the user inputs "mark <task number>".
            else if (input.startsWith("mark"))
            {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;  // Extract the task number and convert it to an array index.
                tasks[taskNumber].markAsDone();  // Mark the task as done.
                System.out.println("    " + "------------------------------------------------------------");
                System.out.println("    " + "Nice! I've marked this task as done: ");
                System.out.println("    " + tasks[taskNumber]);  // Print the details of the completed task.
                System.out.println("    " + "------------------------------------------------------------");
            }

            // Unmark the specified task when the user inputs "unmark <task number>".
            else if (input.startsWith("unmark"))
            {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;  // Extract the task number and convert it to an array index.
                tasks[taskNumber].markAsNotDone();  // Unmark the task.
                System.out.println("    " + "------------------------------------------------------------");
                System.out.println("    " + "OK, I've marked this task as not done yet: ");
                System.out.println("    " + tasks[taskNumber]);  // Print the details of the uncompleted task.
                System.out.println("    " + "------------------------------------------------------------");
            }
            else
            {
                // Create a new Task object and store it in the array.
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("    " + "------------------------------------------------------------");
                System.out.println("    " + "    " + "added: " + input);
                System.out.println("    " + "------------------------------------------------------------");
            }
        }
    }

    public static void printTaskCount(int taskCount) {
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

}