import java.util.Scanner;

public class Freedom {
    protected static Task[] storage = new Task[100];
    static int lastIndex = 0;

    static final String LOGO = "\t________________________________________\n";

    public static void main(String[] args) {
        final String START_MESSAGE = """
                \tHello! I'm Freedom
                \tWhat can I do for you?
                """;
        final String CLOSING_MESSAGE = "\tBye. Hope to see you again soon!\n";

        System.out.println(LOGO + START_MESSAGE + LOGO);
        Scanner in = new Scanner(System.in);

        // Super loop for getting inputs
        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.equals("bye")) {
                break;
            }
            handleInput(line);
        }

        System.out.println(LOGO + CLOSING_MESSAGE + LOGO);
    }

    public static void handleInput(String input) {
        final int COMMAND_INDEX = 0;

        String[] words = input.split(" ");
        String description;
        int listNumber;
        String[] components;

        switch (words[COMMAND_INDEX]) {
            case "list":
                printList();
                return;
            case "mark":
                markTask(words, true);
                return;
            case "unmark":
                markTask(words, false);
                return;
            case "todo":
                description = input.replaceFirst("todo", "");
                storage[lastIndex] = new ToDo(description);
                break;
            case "deadline":
                description = input.replaceFirst("deadline", "");
                components = description.split("/");
                storage[lastIndex] = new Deadline(components[0], components[1]);
                break;
            case "event":
                description = input.replaceFirst("event", "");
                components = description.split("/");
                storage[lastIndex] = new Event(components[0], components[1], components[2]);
                break;
            default:
                storage[lastIndex] = new Task(input);
                break;
        }

        System.out.println(LOGO + "\tGot it. I've added this task: ");
        System.out.println("\t  " + storage[lastIndex].printLine());
        System.out.println("\tNow you have " + (lastIndex + 1) + " tasks in the list.\n" + LOGO);
        lastIndex++;
    }

    public static void printList() {
        int counter = 1;
        System.out.print(LOGO + "\tHere are the tasks in your list:\n");
        for (int i = 0; i < lastIndex; i++) {
            System.out.println("\t" + counter + "." + storage[i].printLine());
            counter++;
        }
        System.out.println(LOGO);
    }

    public static void markTask(String[] words, boolean isDone) {
        final int TASK_INDEX = 1;
        int listNumber = Integer.parseInt(words[TASK_INDEX]);
        String message;

        if (isDone) {
            storage[listNumber - 1].markDone();
            message = "\tNice! I've marked this task as done:";
        } else {
            storage[listNumber - 1].markUndone();
            message = "\tOk, I've marked this task as not done yet:";
        }

        System.out.println(LOGO + message);
        System.out.println("\t  " + storage[listNumber - 1].printLine());
        System.out.println(LOGO);
    }
}
