import java.util.Scanner;

public class Mel {

    public static final String DRAW_HORIZONTAL_LINE = "\t________________________________________";

    public static void main(String[] args) {
        System.out.println("\t________________________________________");
        System.out.println("\tHello! I'm Mel");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t________________________________________");

        String line;
        Scanner in = new Scanner(System.in);
        List userList = new List();

        while (true) {
            System.out.print(System.lineSeparator());
            line = in.nextLine();
            System.out.println(DRAW_HORIZONTAL_LINE);

            if (line.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(DRAW_HORIZONTAL_LINE);
                break;

            } else if (line.equals("list")) {
                userList.printList();
                System.out.println(DRAW_HORIZONTAL_LINE);

            } else if (line.length() >= 6 && line.substring(0, 4).equals("mark")) {
                userList.markItem(line);
                System.out.println(DRAW_HORIZONTAL_LINE);

            } else if (line.length() >= 8 && line.substring(0, 6).equals("unmark")) {
                userList.unmarkItem(line);
                System.out.println(DRAW_HORIZONTAL_LINE);

            } else {
                userList.addItem(line);
                System.out.println(DRAW_HORIZONTAL_LINE);
            }
        }
    }
}
