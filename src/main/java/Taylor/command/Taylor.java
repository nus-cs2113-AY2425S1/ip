package Taylor.command;

import Taylor.task.*;
import java.io.*;
import java.util.*;

// Main class to implement a task manager called "Taylor.command.Taylor"
public class Taylor {
    private static final String FILE_PATH = "./data/Taylor.txt";
    private static final String LINE = "____________________________________________________________";
    private static TaskList tasks;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcome();
        tasks = new TaskList();
        try {
            read();
        } catch (IOException e) {
            System.out.println("Unable to load the file");
        } catch (TaylorException e) {
            System.out.println(e.getMessage());
        }

        // Take the first user input
        String input = sc.nextLine();

        // Keep accepting input until the user types "bye"
        while(true) {
            try {
                input = operate(input, tasks, sc);
                if(input.equalsIgnoreCase("bye")){
                    break;
                }
            } catch (TaylorException e) {
                System.out.println(e.getMessage());
                input = sc.nextLine();
            }
        }
    }

    private static String operate(String input, TaskList tasks, Scanner sc) throws TaylorException {

        String[] inputParts = input.split(" ", 2);
        String command = inputParts[0];
        String args = (inputParts.length > 1) ? inputParts[1] : "";
        switch (command){
            case "bye"-> {
                bye();
                System.exit(0);
            }
            case "list"->{
                System.out.println(LINE);
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + "." + tasks.get(i)); // Print tasks with numbering
                }
                System.out.println(LINE);
                input = sc.nextLine();
            }

            case "mark" -> {
                int index = Integer.parseInt(args) - 1;
                try {
                    tasks.markTask(index);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index)); // Display the task marked as done
                    System.out.println(LINE);
                    saveTasks();
                } catch (IndexOutOfBoundsException e) {
                    printInvalidIndex();
                } catch (IOException e){
                    System.out.println("Unable to write to the file");
                }
                input = sc.nextLine();
            }
            case "unmark"-> {
                int index = Integer.parseInt(args) - 1;
                try {
                    tasks.unmarkTask(index);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index)); // Display the task marked as not done
                    System.out.println(LINE);
                    saveTasks();
                } catch (IndexOutOfBoundsException e) {
                    printInvalidIndex();
                } catch (IOException e){
                    System.out.println("Unable to write to the file");
                }
                input = sc.nextLine();
            }

            case "todo" -> {
                try {
                    Task task = new Todo(input.substring(5).trim());
                    System.out.println("saved");
                    input = handleEvent(tasks, task, sc);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(LINE);
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(LINE);
                    input = sc.nextLine();
                } catch (IOException e){
                    System.out.println("Unable to write to the file");
                }
            }

            case "event" -> {
                try {
                    Event event = getEvent(input);
                    input = handleEvent(tasks, event, sc);
                    saveTasks();
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(LINE);
                    System.out.println("Start and end time of the event cannot be empty.");
                    System.out.println(LINE);
                    input = sc.nextLine();
                } catch (IOException e){
                    System.out.println("Unable to write to the file");
                }
            }

            case "deadline" ->{
                int by = input.indexOf("/by"); // Get the position of "/by"
                try {
                    String description = input.substring(9, by).trim(); // Extract task description
                    String _by = input.substring(by + 4).trim(); // Extract deadline time
                    Task task = new Deadline(description, _by); // Create a Taylor.task.Deadline task
                    input = handleEvent(tasks, task, sc);
                    saveTasks();
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(LINE);
                    System.out.println("Time of the deadline cannot be empty.");
                    System.out.println(LINE);
                    input = sc.nextLine();
                } catch (IOException e){
                    System.out.println("Unable to write to the file");
                }
            }

            case "delete" -> {
                try {
                    int index = Integer.parseInt(args) - 1;
                    String stringOfTheTask = tasks.get(index).toString();
                    tasks.remove(index);
                    System.out.println(LINE);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  "+stringOfTheTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(LINE);
                    Taylor.saveTasks();
                } catch (IndexOutOfBoundsException e) {
                    printInvalidIndex();
                } catch (IOException e){
                    System.out.println("Unable to write to file");
                }
                input = sc.nextLine();
            }

            case "save" -> {
                try {
                    Taylor.saveTasks();
                    System.out.println(LINE);
                    System.out.println("Saved");
                    System.out.println(LINE);
                } catch (IOException e) {
                    System.out.println("Unable to write to file");
                }
                input = sc.nextLine();
            }

            default -> throw new TaylorException(LINE + "\nOOPS!!! I'm sorry, but I don't know what that means :-(\n" + LINE);

        }
        return input;
    }

    private static Event getEvent(String input) {
        int from = input.indexOf("/from"); // Get the position of "/from"
        int to = input.indexOf("/to"); // Get the position of "/to"
        String description = input.substring(6, from).trim(); // Extract task description
        String _from = input.substring(from + 6, to).trim(); // Extract event start time
        String _to = input.substring(to + 4).trim(); // Extract event end time
        return new Event(description, _from, _to);
    }

    private static void printInvalidIndex() {
        System.out.println(LINE);
        System.out.println("Invalid index");
        System.out.println(LINE);
    }

    private static String handleEvent(TaskList tasks, Task task, Scanner sc) throws IOException {
        String input;
        tasks.add(task);
        Taylor.saveTasks();// Add the task to the list
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task); // Display the added event
        System.out.println("Now you have "+ tasks.size() +" tasks in the lists");
        System.out.println(LINE);
        input = sc.nextLine();
        return input;
    }

    private static void bye() {
        // Exit message when user types "bye"
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void welcome() {
        System.out.println(LINE);
        System.out.println("Hello! I'm Taylor.command.Taylor");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public static void saveTasks() throws IOException {
        File file = new File(FILE_PATH);
        if(file.getParentFile().mkdirs()) {
            System.out.println(LINE);
            System.out.println("Directory created");
            System.out.println(LINE);
        }
        FileWriter writer = new FileWriter(file);
        writer.write(tasks.write());
        writer.close();
    }

    public static void read() throws IOException, TaylorException {
        File file = new File(FILE_PATH);
        if(file.exists()){
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            while(line != null){
                String[] parts = line.split(" \\| ");
                String taskType = parts[0].trim();
                boolean isCompleted = parts[1].trim().equals("1");
                String description = parts[2].trim();

                switch(taskType){
                    case "T" -> tasks.add(new Todo(description, isCompleted));
                    case "D" -> tasks.add(new Deadline(description, isCompleted,parts[3].trim()));
                    case "E" -> {
                        String[] time = parts[3].split("-");
                        String from = time[0].trim();
                        String to = time[1].trim();
                        tasks.add(new Event(description, isCompleted, from, to));
                    }
                    default -> throw new TaylorException("Unknown tasks in file");
                }
                line = reader.readLine();
            }
            reader.close();
        }
    }

}