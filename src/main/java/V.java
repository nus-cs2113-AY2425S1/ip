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

    public static void displayList(Task[] listOfTasks, int length) {
        System.out.println(LINE_SEPERATOR);
        for (int i = 0; i < length; i++) {
            System.out.println(String.format("%d.[%s] %s", i + 1, listOfTasks[i].getStatus(), 
                    listOfTasks[i].getDescription()));
        }
        System.out.println(LINE_SEPERATOR);
    }

    public static void main(String[] args) {
        greet();

        boolean isOnline = true;
        Task[] listOfTasks = new Task[100];
        int count = 0;
        String line;
        String[] lineArr;
        Scanner input = new Scanner(System.in);
        
        while (isOnline) {
            line = input.nextLine();
            lineArr = line.trim().split(" ");
            switch (lineArr[0]) {
            case "bye":
                input.close();
                isOnline = false;
                break;
            case "list":
                displayList(listOfTasks, count);
                break;
            case "mark":
                int position = Integer.parseInt(lineArr[1]);
                listOfTasks[position - 1].setDone();
                displayList(listOfTasks, count);
                break;
            default:
                System.out.println("Try again");
                break;
            }
        }
        System.out.println(LINE_SEPERATOR + "\nSee ya\n" + LINE_SEPERATOR);
    }
}
