package pythia.utility;

import java.util.Scanner;

public class Ui {
    private static Scanner scanner;;

    public void init() {
        scanner = new Scanner(System.in);
    }

    public void printResponse(String text) {
        String lineSeparator = "____________________________________________________________";
        text = lineSeparator +  "\n" + text + "\n" + lineSeparator + "\n";
        String formattedText = text.replaceAll("(?m)^", "\t");
        System.out.print(formattedText);
    }

    public String getRequest() {
        return scanner.nextLine();
    }

    public void printAddedTask(String text) {
        printResponse(text.toLowerCase());
    }

    public void printTaskList(TaskList taskList, String commentBefore, String commentAfter) {
        StringBuilder response = new StringBuilder();
        if (commentBefore != null && !commentBefore.isEmpty()) {
            response.append(commentBefore).append("\n");
        }
        response.append(taskList.toString());
        if (commentAfter != null && !commentAfter.isEmpty()) {
            response.append("\n").append(commentAfter);
        }
        printResponse(response.toString());
    }
}
