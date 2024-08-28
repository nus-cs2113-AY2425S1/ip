import esme.Esme;
import esme.Task;

import java.util.Scanner;

public class ChatBot {
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
            } else if (words[0].equals("bye")) {
                esme.farewell();
                break;
            } else if (words[0].equals("mark")) {
                if (words.length == 2) {
                    int index = Integer.parseInt(words[1]);
                    if (esme.isIndexValid(index)) {
                        esme.markTaskInList(Integer.parseInt(words[1]));
                    } else {
                        System.out.println("Oh dear, it seems the index has wandered beyond the boundaries of our list!");
                    }
                } else {
                    System.out.println("Error: Wrong format! Please use the format: command index (e.g., 'mark 1')");
                }
            } else if (words[0].equals("unmark")) {
                if (words.length == 2) {
                    int index = Integer.parseInt(words[1]);
                    if (esme.isIndexValid(index)) {
                        esme.unmarkTaskInList(Integer.parseInt(words[1]));
                    } else {
                        System.out.println("Oh dear, it seems the index has wandered beyond the boundaries of our list!");
                    }
                } else {
                    System.out.println("Error: Wrong format! Please use the format: command index (e.g., 'unmark 1')");

                }
            } else if (line.equals("list")) {
                esme.printTaskList();
            } else {
                esme.addTaskToList(new Task(line));
            }
        }
    }
}
