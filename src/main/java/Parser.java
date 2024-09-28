import java.util.Scanner;

public class Parser {
    private static final int MARK_WORD_LEN = 4;
    private static final int UNMARK_WORD_LEN = 6;
    private static final int INPUT_SPACE_BUFFER = 2;
    private static final int DELETE_WORD_LEN = 6;

    public static void getUserInput(Scanner in, String listFilePath, List userList) {
        String line;
        while (true) {
            line = getLine(in);

            if (isBye(line)) {
                Ui.printByeMessage();
                Ui.printHorizontalLine();
                break;

            } else if (isList(line)) {
                userList.printList();
                Ui.printHorizontalLine();

            } else if (isMark(line)) {
                userList.markItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(listFilePath, userList);

            } else if (isUnmark(line)) {
                userList.unmarkItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(listFilePath, userList);

            } else if (isDelete(line)) {
                userList.deleteItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(listFilePath, userList);

            } else {
                userList.addItem(line);
                Ui.printHorizontalLine();
                Storage.saveListToFile(listFilePath, userList);

            }
        }
    }

    private static String getLine(Scanner in) {
        String line;
        System.out.print(System.lineSeparator());
        line = in.nextLine();
        Ui.printHorizontalLine();
        return line;
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
}
