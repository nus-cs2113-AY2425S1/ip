import java.util.Scanner;

public class V {

    private static final String LINE_SEPERATOR = "____________________________________________________________";

    // Print 2 line seperators between a block of text for cleaner CLI
    public static void printBlock(String text) {
        System.out.println(LINE_SEPERATOR);
        System.out.println(text);
        System.out.println(LINE_SEPERATOR);
    }

    public static void greet() {
        String logo = " _       _ \n"
                + "\\ \\     / / \n"
                + " \\ \\   / / \n"
                + "  \\ \\_/ / \n"
                + "   \\___/ \n";
        System.out.print(logo);
        printBlock("Hi I'm V\nWhat can I do for you?");
    }

    public static void displayList(Task[] list, int length) {
        System.out.println(LINE_SEPERATOR);
        for (int i = 0; i < length; i++) {
            System.out.println(String.format("%d.[%s] %s", i + 1, list[i].getStatus(), list[i].getDescription()));
        }
        System.out.println(LINE_SEPERATOR);
    }

    public static void main(String[] args) {
        greet();

        boolean isOnline = true;
        Task[] list = new Task[100];
        int count = 0;
        String line;
        String[] lineArr;
        Scanner input = new Scanner(System.in);
        
        while (isOnline) {
            line = input.nextLine();
            lineArr = line.split(" ");
            if (lineArr[0].equals("bye")) {
                input.close();
                isOnline = false;
            } else if (lineArr[0].equals("list")) {
                displayList(list, count);
            } else if (lineArr[0].equals("mark")) {
                int position = Integer.parseInt(lineArr[1]);
                list[position - 1].markAsDone();
                displayList(list, count);
            } else {
                list[count] = new Task(line);
                count++;
                printBlock("added: " + line);
            }
        }
        System.out.println(LINE_SEPERATOR + "\nSee ya\n" + LINE_SEPERATOR);
    }
}
