import java.util.Scanner;

public class lovespiritual {
    public static void main(String[] args) {
        String line = "__________________________________________________"; // horizontal line
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100]; // array of tasks
        boolean[] isMarked = new boolean[100]; // check if task is marked
        String[] taskTypes = new String[100];
        int taskCount = 0; // count the number of tasks added in the array

        // introduction
        System.out.println(line);
        System.out.println("Hello! I'm lovespiritual");
        System.out.println("What can I do for you?");
        System.out.println(line);

        // loop that keeps recurring when the program is running
        while (true) {
            String input = in.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    String checkbox = isMarked[i] ? "[X]" : "[ ]";
                    String taskType = taskTypes[i];
                    System.out.println((i + 1) + "." + taskType + checkbox + " " + tasks[i]);
                }
                System.out.println(line);
            } else if (input.startsWith("mark ")) {
                String numberString = input.substring(5).trim();
                int number = Integer.parseInt(numberString) - 1;
                if (number >= 0 && number < taskCount) {
                    isMarked[number] = true;
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" [X] " + tasks[number]);
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("Invalid number. Please enter a valid number.");
                    System.out.println(line);
                }
            } else if (input.startsWith("unmark ")) {
                String numberString = input.substring(7).trim();
                int number = Integer.parseInt(numberString) - 1;
                if (number >= 0 && number < taskCount) {
                    isMarked[number] = false;
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" [ ] " + tasks[number]);
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("Invalid number. Please enter a valid number.");
                    System.out.println(line);
                }
            } else if (input.startsWith("todo ")){
                String taskDescription = input.substring(5).trim();
                tasks[taskCount] = taskDescription;
                taskTypes[taskCount] = "[T]";
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println(" [T][ ] " + taskDescription);
                System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
                System.out.println(line);
                taskCount++;
            } else if (input.startsWith("deadline ")){
                String fullTaskDetails = input.substring(9).trim();
                String[] taskDetails = fullTaskDetails.split("/by");
                String taskDescription = taskDetails[0].trim();
                String by = taskDetails.length > 1 ? taskDetails[1].trim() : "";
                taskTypes[taskCount] = "[D]";
                tasks[taskCount] = taskDescription + " (by: " + by + ")";
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println(" [D][ ] " + tasks[taskCount]);
                System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
                System.out.println(line);
                taskCount++;
            } else if (input.startsWith("event ")) {
                String fullTaskDetails = input.substring(6).trim();
                String[] taskDetails = fullTaskDetails.split("/from", 2);
                String taskDescription = taskDetails[0].trim();
                String[] time = taskDetails.length > 1 ? taskDetails[1].split("/to", 2) : new String[]{"", ""};
                String from = time[0].trim();
                String to = time.length > 1 ? time[1].trim() : "";
                tasks[taskCount] = taskDescription + " (from: " + from + " to: " + to + ")";
                taskTypes[taskCount] = "[E]";
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println(" [E][ ] " + tasks[taskCount]);
                System.out.println("Now you have " + (taskCount + 1) + " tasks in the list.");
                System.out.println(line);
                taskCount++;
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
    }
}
