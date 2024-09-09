import java.util.Scanner;

public class Freedom {
    protected static Task[] storage = new Task[100];
    static int lastIndex = 0;

    public static void main(String[] args) {
        String logo = "\t_________________________________________\n";
        String message = """
                \tHello! I'm Freedom
                \tWhat can I do for you?
                """;
        String closing = "\tBye. Hope to see you again soon!\n";

        System.out.println(logo + message + logo);

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();

            if (line.equals("bye")) {
                break;
            }
            boolean isCommand = handleInput(line);
            if (!isCommand) {
                lastIndex++;
            }
        }

        System.out.println(logo + closing + logo);
    }

    public static boolean handleInput(String input) {
        String[] words = input.split(" ");
        String description;

        if (input.equals("list")) {
            printList();
            return true;
        }
        if (words[0].equals("mark")) {
            int listNumber = Integer.parseInt(words[1]);
            storage[listNumber - 1].markDone();
            markTaskStatement(storage[listNumber - 1], true);
            return true;
        }
        if (words[0].equals("unmark")) {
            int listNumber = Integer.parseInt(words[1]);
            storage[listNumber - 1].markUndone();
            markTaskStatement(storage[listNumber - 1], false);
            return true;
        }
        if (words[0].equals("todo")) {
            description = input.replaceFirst("todo", "");
            storage[lastIndex] = new ToDo(description);
        }
        else if (words[0].equals("deadline")) {
            description = input.replaceFirst("deadline", "");
            String[] members = description.split("/");
            storage[lastIndex] = new Deadline(members[0], members[1]);
        }
        else if (words[0].equals("event")) {
            description = input.replaceFirst("event", "");
            String[] members = description.split("/");
            storage[lastIndex] = new Event(members[0], members[1], members[2]);
        } else {
            storage[lastIndex] = new Task(input);
        }
        String logo = "\t_________________________________________\n";
        System.out.println(logo + "\tGot it. I've added this task: ");
        System.out.println("\t  " + storage[lastIndex].printLine());
        System.out.println("\tNow you have " + (lastIndex + 1) + " tasks in the list.\n" + logo);
        return false;
    }

    public static void printList() {
        String logo = "\t_________________________________________\n";
        int counter = 0;
        System.out.print(logo + "\tHere are the tasks in your list:\n");
        for (int i = 0; i < lastIndex; i++) {
            System.out.println("\t" + (counter + 1) + "." + storage[i].printLine());
            counter++;
        }
        System.out.println(logo);
    }

    public static void markTaskStatement(Task task, boolean isDone) {
        String logo = "\t_________________________________________\n";
        String message = isDone ?
                "\tNice! I've marked this task as done:" :
                "\tOk, I've marked this task as not done yet:";
        System.out.println(logo + message);
        System.out.println("\t  " + task.printLine());
        System.out.println(logo);
    }
}
