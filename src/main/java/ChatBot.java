import esme.Esme;
import esme.Task;

import java.util.Scanner;

public class ChatBot {
    /**
     * The main entry point for the ChatBot program.
     * It first greets the user, and then enters an infinite loop where it waits for
     * user input.
     * If the input is empty, it will prompt the user.
     * If the input is "bye", it will say farewell to the user and terminate the
     * program.
     * If the input is "mark" or "unmark", it will mark or unmark a task with the
     * given index.
     * If the input is "list", it will print out the task list.
     * Otherwise, it will add the input as a new task to the task list.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Esme esme = new Esme();
        esme.greet();
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            String[] words = line.split(" ");
            if (words.length == 0) {
                esme.promptEmptyInput();
            }
            switch (words[0]) {
            case "bye":
                esme.farewell();
                in.close();
                System.exit(0);
                break;
            case "mark":
            case "unmark":
                esme.handleTaskStatus(words);
                break;
            case "list":
                esme.printTaskList();
                break;
            default:
                esme.addTaskToList(new Task(line));
                break;
            }
        }
    }
}
