import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DianaAssistant {
    public void interact() {
        System.out.println("Hello, I am Diana! A personal CLI assistant :)");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        String input;
        while (true) {
            input = scanner.nextLine();
            if ("bye".equals(input)) {
                break;
            }

            switch(getCommand(input)) {
                case "list":
                    printList(list);
                    break;
                case "mark":
                    toMark(list, input, true);
                    break;
                case "unmark":
                    toMark(list, input, false);
                    break;
                case "todo":
                    addTodo(input, list);
                    break;
                case "event":
                    addEvent(input, list);
                    break;
                case "deadline":
                    addDeadline(input, list);
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }

        System.out.println("Bye! Hope to see you again soon.");
        scanner.close();
    }

    private String getCommand(String input) {
        return input.split(" ")[0];
    }

    private void printList (List<Task> list) {

        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
        return;
    }

    private void toMark (List<Task> list, String input, boolean toMark) {
        try {
            int TaskNum = Integer.valueOf(input.substring(input.indexOf(" ") + 1)) - 1;
            if (TaskNum >= 0 && TaskNum < list.size()) {
                Task task = list.get(TaskNum);
                if (toMark) {
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done\n" + task.toString());
                }
                else {
                    task.markAsNotDone();
                    System.out.println("Okay, I've marked this task as not done yet\n" + task.toString());
                }
            }
            else {
                System.out.println("Please enter a number between 1 and " + (list.size() - 1));
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter a number between 1 and " + (list.size() - 1));
        }
    }

    private void printAddedTask (String description, List<Task> list) {
        System.out.println("Got it! I've added this task");
        System.out.println(description);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

    private void addTodo (String input, List<Task> list) {
        String description = input.substring("todo".length()).trim();
        System.out.println("added: " + input);
        Todo todo = new Todo(description);
        list.add(todo);
        printAddedTask(description, list);
    }

    private void addDeadline (String input, List<Task> list) {
        if (!input.contains("/by")) {
            System.out.println("Please enter a valid deadline with the correct format:");
            System.out.println("Eg: Read book /by 5pm");
            return;
        }
        String[] parts = input.split("/by", 2);
        String description = parts[0].substring("deadline".length()).trim();
        String by = parts[1].trim(); // trim removes white space
        Task deadline = new Deadline(description, by);
        list.add(deadline);
        printAddedTask(description, list);
    }

    private void addEvent (String input, List<Task> list) {
        if (!(input.contains("/from") && input.contains("/to"))) {
            System.out.println("Please enter a valid event with the correct format:");
            System.out.println("Eg: Read book /from 2pm /to 4pm");
            return;
        }

        String[] parts = input.split("/from", 2);
        String description = parts[0].substring("event".length()).trim();
        String[] fromto = parts[1].split("/to", 2);
        String from = fromto[0].trim();
        String to = fromto[1].trim();
        Task event = new Event(description, from, to);
        list.add(event);
        printAddedTask(description, list);

    }

}