import java.util.Scanner;

public class Mel {

    public static final String DRAW_HORIZONTAL_LINE = "\t________________________________________";
    public static final int MARK_WORD_LEN = 4;
    public static final int UNMARK_WORD_LEN = 6;
    public static final int INPUT_SPACE_BUFFER = 2;
    private static final int DELETE_WORD_LEN = 6;

    public static void main(String[] args) {
        printIntroMessage();
        
        // Set up scanner for user input
        Scanner in = new Scanner(System.in);
        List userList = new List();

        getUserInput(in, userList);
    }

    private static void getUserInput(Scanner in, List userList) {
        String line;
        while (true) {
            line = getLine(in);

            if (isBye(line)) {
                printByeMessage();
                printHorizontalLine();
                break;

            } else if (isList(line)) {
                userList.printList();
                printHorizontalLine();

            } else if (isMark(line)) {
                userList.markItem(line);
                printHorizontalLine();

            } else if (isUnmark(line)) {
                userList.unmarkItem(line);
                printHorizontalLine();

            } else if (isDelete(line)) {
                userList.deleteItem(line);
                printHorizontalLine();

            } else {
                userList.addItem(line);
                printHorizontalLine();
            }
        }
    }

    private static void printByeMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    private static boolean isList(String line) {
        return line.equals("list");
    }

    private static boolean isBye(String line) {
        return line.equals("bye");
    }

    private static boolean isMark(String line) {
        return line.length() >= (MARK_WORD_LEN + INPUT_SPACE_BUFFER) && line.substring(0, 4).equals("mark");
    }

    private static boolean isUnmark(String line) {
        return line.length() >= (UNMARK_WORD_LEN + INPUT_SPACE_BUFFER) && line.substring(0, 6).equals("unmark");
    }

    private static boolean isDelete(String line) {
        return line.length() >= (DELETE_WORD_LEN + INPUT_SPACE_BUFFER) && line.substring(0, 6).equals("delete");
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
        System.out.println("\tHello! I'm");
        System.out.println("\t.___  ___.  _______  __      \n" +
                "\t|   \\/   | |   ____||  |     \n" +
                "\t|  \\  /  | |  |__   |  |     \n" +
                "\t|  |\\/|  | |   __|  |  |     \n" +
                "\t|  |  |  | |  |____ |  `----.\n" +
                "\t|__|  |__| |_______||_______|\n" +
                "\t                             ");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }
}
