import java.util.Scanner;

public class Eva {

    public static final String HORIZONTAL_LINE = "---------------------------------------------------------------";

    public static int extractDigit(String input) {
        String numberString = input.replaceAll("[^0-9]", "");
        return Integer.parseInt(numberString);
    }

    public static void printTaskList(Task[] tasks, int count) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + ". " + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTaskAsDone(Task[] tasks, String line) {
        int taskNumber = extractDigit(line) - 1;
        tasks[taskNumber].markAsDone();

        System.out.println("Great! This task is marked as done: ");
        System.out.println("[" + tasks[taskNumber].getStatusIcon() + "] " + tasks[taskNumber].description);
        System.out.println("Well done! ;)");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void unmarkTaskAsDone(Task[] tasks, String line) {
        int taskNumber = extractDigit(line) - 1;
        tasks[taskNumber].markAsNotDone();

        System.out.println("Ok, This task is marked as not done yet: ");
        System.out.println("[" + tasks[taskNumber].getStatusIcon() + "] " + tasks[taskNumber].description);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Eva!");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);

        String line;
        Scanner in = new Scanner(System.in);
        int count = 0;
        Task[] sentenceArray = new Task[100];

        while(true)
        {
            line = in.nextLine();
            sentenceArray[count] = new Task(line);

            if(line.equalsIgnoreCase("list")) {
                printTaskList(sentenceArray, count);
            } else if (line.startsWith("mark")) {
                markTaskAsDone(sentenceArray, line);
            } else if (line.startsWith("unmark")) {
                unmarkTaskAsDone(sentenceArray, line);
            } else if (line.equalsIgnoreCase("Bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(HORIZONTAL_LINE);
                break;
            } else {
                System.out.println("added: " + line);
                System.out.println(HORIZONTAL_LINE);
                count++;
            }
        }
    }
}
