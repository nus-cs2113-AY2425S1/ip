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

    public void printTaskList(TaskList taskList) {
        int remainingTasks = taskList.getNumberOfTasks();
        StringBuilder taskListString = new StringBuilder();
        for (int i = 0; i < remainingTasks; i++) {
            taskListString.append(i + 1).append(". ");
            taskListString.append(taskList.get(i).toString());
            taskListString.append("\n");
        }

        taskListString.append("Now you have ").append(remainingTasks);
        if (remainingTasks == 1) {
            taskListString.append(" task in the list.");
        } else {
            taskListString.append(" tasks in the list.");
        }
        printResponse(taskListString.toString());
    }
}
