import java.util.List;
import java.util.Scanner;

public class Ui {
    private final String BREAK_LINE = "____________________";
    private final String GREETING_LINE = BREAK_LINE + "\nHello! I'm DBot\nWhat can I do for you?\n" + BREAK_LINE;
    Scanner in;

    public Ui() {
        in = new Scanner(System.in);
        System.out.println(GREETING_LINE);
    }

    public void printShortMessage(String message) {
        System.out.println(BREAK_LINE + " Result " + BREAK_LINE);
        System.out.println(message);
        System.out.println(BREAK_LINE + BREAK_LINE + "\n");
    }

    public void printLongMessage(Object[] list) {
        System.out.println(BREAK_LINE + " Result " + BREAK_LINE);
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println(BREAK_LINE + BREAK_LINE + "\n");
    }

    public void printError(String message) {
        System.out.println(BREAK_LINE + " Error " + BREAK_LINE);
        System.out.println("An error occur due to: " + message);
        System.out.println(BREAK_LINE + BREAK_LINE + "\n");
    }

    public String listTasks(List<Task> tasks){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1);
            sb.append(". ");
            sb.append(tasks.get(i).toString());
            sb.append('\n');
        }
        return sb.toString().trim();
    }

    public String readLine() {
        System.out.print("Command: ");
        return in.nextLine().strip();
    }
}
