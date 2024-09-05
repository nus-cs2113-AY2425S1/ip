import java.util.*;

// Main class to implement a task manager called "Taylor"
public class Taylor {
    public static void main(String[] args) {
        // Initialize a scanner object to take user input
        Scanner sc = new Scanner(System.in);

        // Define a separator line for display purposes
        final String line = "____________________________________________________________";

        // Welcome message
        System.out.println(line);
        System.out.println("Hello! I'm Taylor");
        System.out.println("What can I do for you?");
        System.out.println(line);

        // Take the first user input
        String input = sc.nextLine();

        // Initialize a list to store tasks
        List<Task> tasks = new ArrayList<>();

        // Keep accepting input until the user types "bye"
        while(!input.equals("bye")) {

            // Handle "list" command to show all tasks
            if(input.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + "." + tasks.get(i)); // Print tasks with numbering
                }
                System.out.println(line);
                input = sc.nextLine();
                continue; // Go to next input
            }

            // Handle "mark" command to mark a task as completed
            if(input.startsWith("mark")){
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1])-1; // Parse task index
                if(index<0 || index>=tasks.size()) { // Check if index is valid
                    System.out.println(line);
                    System.out.println("Invalid index");
                    System.out.println(line);
                } else {
                    tasks.get(index).setCompleted(true); // Mark task as completed
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index)); // Display the task marked as done
                    System.out.println(line);
                }
                input = sc.nextLine();
                continue;
            }

            // Handle "unmark" command to mark a task as not completed
            if(input.startsWith("unmark")){
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1])-1; // Parse task index
                if(index<0 || index>=tasks.size()) { // Check if index is valid
                    System.out.println(line);
                    System.out.println("Invalid index");
                } else {
                    tasks.get(index).setCompleted(false); // Mark task as not completed
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index)); // Display the task marked as not done
                    System.out.println(line);
                }
                input = sc.nextLine();
                continue;
            }

            // Handle "todo" command to add a new Todo task
            if(input.startsWith("todo")){
                Todo todo = new Todo(input.substring(5)); // Create a Todo task with the description
                tasks.add(todo); // Add the task to the list
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo); // Display the added task
                System.out.println("Now you have "+ tasks.size() +" tasks in the lists");
                System.out.println(line);
                input = sc.nextLine();
                continue;
            }

            // Handle "event" command to add a new Event task
            if(input.startsWith("event")){
                int from = input.indexOf("/from"); // Get the position of "/from"
                int to = input.indexOf("/to"); // Get the position of "/to"
                String description = input.substring(6,from); // Extract task description
                String _from = input.substring(from+6,to); // Extract event start time
                String _to = input.substring(to+4); // Extract event end time
                Event event = new Event(description,_from,_to); // Create an Event task
                tasks.add(event); // Add the task to the list
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event); // Display the added event
                System.out.println("Now you have "+ tasks.size() +" tasks in the lists");
                System.out.println(line);
                input = sc.nextLine();
                continue;
            }

            // Handle "deadline" command to add a new Deadline task
            if(input.startsWith("deadline")){
                int by = input.indexOf("/by"); // Get the position of "/by"
                String description = input.substring(9,by); // Extract task description
                String _by = input.substring(by+4); // Extract deadline time
                Task task = new Deadline(description,_by); // Create a Deadline task
                tasks.add(task); // Add the task to the list
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task); // Display the added deadline task
                System.out.println("Now you have "+ tasks.size() +" tasks in the lists");
                System.out.println(line);
                input = sc.nextLine();
                continue;
            }

            // If input doesn't match any commands, just treat it as a simple task
            System.out.println(line);
            System.out.println("added: " + input);
            System.out.println(line);
            input = sc.nextLine();
        }

        // Exit message when user types "bye"
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}