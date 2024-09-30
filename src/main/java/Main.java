import cristiano.ui.Ronaldo;
import cristiano.storage.Storage;

import java.util.Scanner;

/**
 * The main class of the Ronaldo chatbot.
 * This chatbot develops a personality of Cristiano Ronaldo,
 * a world renowned footballer and the most followed celebrity in the world.
 */
public class Main {
    /**
     * The start of the main input of the Ronaldo chatbot.
     * Firstly, the program tries to restore any previous tasks saved.
     * Then, it greets the user, before enquiring the user on their purpose.
     * The chatbot runs on an infinite while loop until the user enters an input.
     * By default, the input will be taken as a task, and will be added to a list of tasks.
     * However, if the input is empty, an exception error message is sent.
     * The chatbot will only exit the program if the command "bye" is used.
     * From now on, tasks will be considered as goals.
     *
     * @param args Command line arguments;
     */
    public static void main(String[] args) {
        String path = "./data/cr7.txt";
        Storage storage = new Storage(path);
        Ronaldo ronaldoInstance = new Ronaldo(storage);
        ronaldoInstance.greet();
        Scanner in = new Scanner(System.in);


        while (true) {
            String words = in.nextLine();
            if (words.trim().isEmpty()) {
                ronaldoInstance.reject("Empty");
                continue;
            } else if (words.contains("messi")) {
                ronaldoInstance.boast();
            } else if (words.contains("siu")) {
                ronaldoInstance.exclaim();
            }

            String[] input = words.split(" ", 2);
            String command = input[0].trim();
            switch (command) {
            case "help":
                ronaldoInstance.help();
                break;
            case "bye":
                ronaldoInstance.exit();
                in.close();
                return;
            case "mark":
            case "unmark":
                ronaldoInstance.handleGoal(input);
                break;
            case "list":
                ronaldoInstance.showListOfGoals();
                break;
            case "event":
                ronaldoInstance.addEvent(words);
                break;
            case "todo":
                ronaldoInstance.addTodo(words);
                break;
            case "deadline":
                ronaldoInstance.addDeadline(words);
                break;
            case "delete":
                ronaldoInstance.delete(words);
                break;
            case "find":
                ronaldoInstance.find(words);
                break;
            default:
                ronaldoInstance.reject("Format");
                break;
            }
        }
    }

}
