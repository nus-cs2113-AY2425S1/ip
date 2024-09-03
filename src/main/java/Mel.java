import java.util.Scanner;

public class Mel {

    public static final String DRAW_HORIZONTAL_LINE = "\t________________________________________";
    public static final int MARK_WORD_LEN = 4;
    public static final int UNMARK_WORD_LEN = 6;
    public static final int INPUT_SPACE_BUFFER = 2;

    public static void main(String[] args) {
        printIntroMessage();
        
        // Set up scanner for user input
        String line;
        Scanner in = new Scanner(System.in);
        List userList = new List();

        getUserInput(in, userList);
    }

    private static void getUserInput(Scanner in, List userList) {
        String line;
        while (true) {
            line = getLine(in);

            if (line.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                printHorizontalLine();
                break;

            } else if (line.equals("list")) {
                userList.printList();
                printHorizontalLine();

            } else if (line.length() >= (MARK_WORD_LEN + INPUT_SPACE_BUFFER) && line.substring(0, 4).equals("mark")) {
                userList.markItem(line);
                printHorizontalLine();

            } else if (line.length() >= (UNMARK_WORD_LEN + INPUT_SPACE_BUFFER) && line.substring(0, 6).equals("unmark")) {
                userList.unmarkItem(line);
                printHorizontalLine();

            } else {
                userList.addItem(line);
                printHorizontalLine();
            }
        }
    }

    private static void printHorizontalLine() {
        System.out.println(DRAW_HORIZONTAL_LINE);
    }

    private static String getLine(Scanner in) {
        String line;
        System.out.print(System.lineSeparator());
        line = in.nextLine();
        printHorizontalLine();
        return line;
    }

    private static void printIntroMessage() {
        printHorizontalLine();
        System.out.println("\tHello! I'm Mel");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }
}
