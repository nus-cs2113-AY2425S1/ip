import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DianaAssistantN {
    public void interact() {
        System.out.println("Hello, I am Diana!\nWhat can I do for you?");
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
                default:
                    addInput(input, list);
                    break;
            }
        }

        System.out.println("Bye! Hope to see you again soon.");
        scanner.close();
    }

    private String getCommand(String input) {
        if (input.equals("list")) {
            return "list";
        }
        if (input.startsWith("mark ")) {
            return "mark";
        }
        if (input.startsWith("unmark ")) {
            return "unmark";
        }
        return "add";
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
                    task.MarkAsDone();
                    System.out.println("Nice! I've marked this task as done\n" + task.toString());
                }
                else {
                    task.MarkAsNotDone();
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

    private void addInput (String input, List<Task> list) {
        Task task = new Task(input);
        System.out.println("added: " + input);
        list.add(task);
    }

}