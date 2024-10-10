package tommi;

import tommi.Task.Deadline;
import tommi.Task.Event;
import tommi.Task.ToDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public static void processInputCases(String input) {
        try {
            if (input.equals("list")) {
                TaskList.listTasks();
                return;
            }

            String[] words = input.split(" ");
            int taskIndex;

            switch (words[0]) {
            case "mark":
                taskIndex = Integer.parseInt(words[1]) - 1;
                TaskList.markTask(taskIndex);
                trySaveTaskData();
                break;
            case "unmark":
                taskIndex = Integer.parseInt(words[1]) - 1;
                TaskList.unmarkTask(taskIndex);
                trySaveTaskData();
                break;
            case "search":
                ArrayList<String> foundResults = Storage.search(words[1]);
                Ui.printSearchResults(foundResults);
                break;
            case "delete":
                taskIndex = Integer.parseInt(words[1]) - 1;
                TaskList.deleteTask(taskIndex);
                trySaveTaskData();
                break;
            case "todo":
                if (words.length > 1 && !words[1].isEmpty()) {
                    TaskList.addTask(new ToDo(false, input.substring(5)));
                    trySaveTaskData();
                } else {
                    throw new IllegalArgumentException(
                            "____________________________________________________________\n" +
                            "Please provide what should be in the todo.\n" +
                            "____________________________________________________________"
                    );
                }
                break;
            case "deadline":
                String[] deadlineParts = input.substring(9).split("/by", 2);
                TaskList.addTask(new Deadline(false, deadlineParts[0], deadlineParts[1]));
                trySaveTaskData();
                break;
            case "event":
                String[] eventParts = input.split("/from", 2);
                String[] timeParts = eventParts[1].split("/to", 2);

                TaskList.addTask(new Event(false, eventParts[0].substring(6), timeParts[0], timeParts[1]));
                trySaveTaskData();
                break;
            default:
                throw new IllegalArgumentException(
                        "____________________________________________________________\n" +
                        "Please use a todo, event or deadline. List all tasks with list or exit with bye!\n" +
                        "____________________________________________________________"
                );
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void trySaveTaskData() {
        try {
            Storage.saveTaskData(TaskList.getTaskList());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
