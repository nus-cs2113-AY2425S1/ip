import java.util.Scanner;

public class Yukee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String logo = """
                 Y   Y  U   U  K   K  EEEEE  EEEEE
                  Y Y   U   U  K  K   E      E
                   Y    U   U  KKK    EEEE   EEEE
                   Y    U   U  K  K   E      E
                   Y     UUU   K   K  EEEEE  EEEEE
                """;
        System.out.println("Hello! I'm Yukee, your friendly assistant!\n" + logo);
        System.out.println("What can I do for you today?");

        while (true) {
            try {
                System.out.print("Enter command: ");
                String input = scanner.nextLine().trim();
                String[] inputSplit = input.split(" ", 2);
                String command = inputSplit[0].toLowerCase();

                switch (command) {
                    case "bye":
                        System.out.println("Bye! Hope to see you again soon!");
                        return;

                    case "list":
                        if (taskCount == 0) {
                            System.out.println("Your task list is empty! Try adding some tasks!");
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < taskCount; i++) {
                                System.out.println((i + 1) + ". " + tasks[i]);
                            }
                        }
                        break;

                    case "mark":
                        try {
                            int index = Integer.parseInt(inputSplit[1]) - 1;
                            if (index >= 0 && index < taskCount) {
                                tasks[index].markAsDone();
                                System.out.println("Nice! I've marked this task as done:");
                                System.out.println("  " + tasks[index]);
                            } else {
                                System.out.println("Invalid task number. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid command. Usage: mark <task_number>");
                        }
                        break;

                    case "unmark":
                        try {
                            int index = Integer.parseInt(inputSplit[1]) - 1;
                            if (index >= 0 && index < taskCount) {
                                tasks[index].markAsNotDone();
                                System.out.println("OK, I've marked this task as not done yet:");
                                System.out.println("  " + tasks[index]);
                            } else {
                                System.out.println("Invalid task number. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid command. Usage: unmark <task_number>");
                        }
                        break;

                    case "todo":
                        if (inputSplit.length < 2 || inputSplit[1].trim().isEmpty()) {
                            throw new EmptyDescriptionException("todo");
                        }
                        tasks[taskCount] = new Todo(inputSplit[1]);
                        taskCount++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[taskCount - 1]);
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                        break;

                    case "deadline":
                        if (inputSplit.length < 2 || inputSplit[1].trim().isEmpty()) {
                            throw new EmptyDescriptionException("deadline");
                        }
                        String[] deadlineParts = inputSplit[1].split(" /by ");
                        tasks[taskCount] = new Deadline(deadlineParts[0], deadlineParts[1]);
                        taskCount++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[taskCount - 1]);
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                        break;

                    case "event":
                        if (inputSplit.length < 2 || inputSplit[1].trim().isEmpty()) {
                            throw new EmptyDescriptionException("event");
                        }
                        String[] eventParts = inputSplit[1].split(" /from | /to ");
                        tasks[taskCount] = new Event(eventParts[0], eventParts[1], eventParts[2]);
                        taskCount++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[taskCount - 1]);
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                        break;

                    case "help":
                        System.out.println("Here are the commands you can use:");
                        System.out.println("1. 'list' - List all tasks.");
                        System.out.println("2. 'mark <task_number>' - Mark a task as done.");
                        System.out.println("3. 'unmark <task_number>' - Mark a task as not done.");
                        System.out.println("4. 'todo <task_description>' - Add a todo task.");
                        System.out.println("5. 'deadline <task_description> /by <time>' - Add a deadline task.");
                        System.out.println("6. 'event <task_description> /from <start_time> /to <end_time>' - Add an event task.");
                        System.out.println("7. 'hello' or 'hi' - Greet Yukee.");
                        System.out.println("8. 'bye' - Exit the program.");
                        break;

                    case "hello":
                    case "hi":
                        System.out.println("Hello there! I'm Yukee, your personal assistant. I can help you manage tasks. Just let me know what you'd like to do! (✿◠‿◠)");
                        break;

                    default:
                        throw new InvalidCommandException();
                }
            } catch (YukeeException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
