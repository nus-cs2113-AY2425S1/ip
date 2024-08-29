import java.util.Scanner;
import java.util.Arrays;

public class Freedom {
    public static void main(String[] args) {
        String[] storage = new String[100];
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
            handleInput(line, storage, lastIndex);
            if (!line.equals("list")) {
                lastIndex++;
            }
        }

        System.out.println(logo + closing + logo);
    }

    public static void handleInput(String input, String[] storage, int lastIndex) {
        if (input.equals("list")) {
            printList(Arrays.copyOf(storage, lastIndex));
            return;
        }
        storage[lastIndex] = input;
        String logo = "\t_________________________________________\n";
        System.out.println(logo + "\tadded: " + input + "\n" + logo);
    }

    public static void printList(String[] storage) {
        String logo = "\t_________________________________________\n";
        int counter = 0;
        System.out.print(logo);
        for (String item: storage) {
            System.out.println("\t" + (counter + 1) + ". " + item);
            counter++;
        }
        System.out.print(logo);
    }
}
