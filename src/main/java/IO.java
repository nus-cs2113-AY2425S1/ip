import java.util.ArrayList;
import java.util.Scanner;

public class IO {
    private static Scanner scanner;;

    public static void init() {
        scanner = new Scanner(System.in);
    }

    public static void printResponse(String text) {
        String lineSeparator = "____________________________________________________________";
        text = lineSeparator +  "\n" + text + "\n" + lineSeparator + "\n";
        String formattedText = text.replaceAll("(?m)^", "\t");
        System.out.print(formattedText);
    }

    public static String getRequest() {
        return scanner.nextLine();
    }

    public static void printAddedTask(String text) {
        printResponse(text.toLowerCase());
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        StringBuilder taskListString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            taskListString.append(i + 1).append(". ");
            taskListString.append(taskList.get(i).toString());
            taskListString.append("\n");
        }

        int remainingTasks = Pythia.getNumberOfRemainingTasks();
        taskListString.append("Now you have ").append(remainingTasks);
        if (remainingTasks == 1) {
            taskListString.append(" task in the list.");
        } else {
            taskListString.append(" tasks in the list.");
        }
        printResponse(taskListString.toString());
    }
}
