import java.util.Scanner;

public class Freedom {
    public static void main(String[] args) {
        Task[] storage = new Task[100];
        int lastIndex = 0;
        String logo = "\t_________________________________________\n";
        String message = """
                \tHello! I'm Freedom
                \tWhat can I do for you?
                """;
        String closing = "\tBye. Hope to see you again soon!\n";

        System.out.println(logo + message + logo);

        while (true) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();

            if (line.equals("bye")) {
                break;
            }
            boolean isCommand = handleInput(line, storage, lastIndex);
            if (!isCommand) {
                lastIndex++;
            }
        }

        System.out.println(logo + closing + logo);
    }

    public static boolean handleInput(String input, Task[] storage, int lastIndex) {
        if (input.equals("list")) {
            printList(storage, lastIndex);
            return true;
        }
        String[] words = input.split(" ");
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
        storage[lastIndex] = new Task(input);
        String logo = "\t_________________________________________\n";
        System.out.println(logo + "\tadded: " + input + "\n" + logo);
        return false;
    }

    public static void printList(Task[] storage, int lastIndex) {
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
