package niwa.ui;

import niwa.Niwa;
import niwa.command.CommandResult;
import niwa.data.task.Task;
import niwa.messages.NiwaMesssages;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class NiwaUI {
    private static String PREFIX = "\t";
    private static Scanner scanner;

    public NiwaUI() {
        scanner = new Scanner(System.in);
    }

    public String getUserCommand() {
        if(scanner.hasNextLine()) {
            String command = scanner.nextLine();

            // silently consume all ignored lines
            while (command.trim().isEmpty()) {
                command = scanner.nextLine();
            }

            return command;
        }
        return "";
    }

    public void printMessage(String message) {
        System.out.println(PREFIX + message);
    }

    public void printMessages(List<String> messages) {
        messages.forEach(this::printMessage);
    }

    public void printMessages(String... messages) {
        for (String m: messages) {
            printMessage(m);
        }
    }

    public void printTaskInfo(List<Task> tasks) {
        if (tasks == null) {
            return;
        }

        AtomicInteger index = new AtomicInteger(1);;
        tasks.forEach((t) -> printMessage(index.getAndIncrement() + "." + t.getFullInfo()));
    }

    public void showCommandResult(CommandResult result) {
        if (result == null) {
            return;
        }
        printMessage(NiwaMesssages.SEPARATOR);
        printMessages(result.feedbackToUser);
        printTaskInfo(result.getRelevantTasks());
        printMessage(NiwaMesssages.SEPARATOR);
    }

}
