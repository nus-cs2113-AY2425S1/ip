import cristiano.Ronaldo;

import java.util.Scanner;

public class Main {
    /**
     * The start of the main input of the Ronaldo chatbot.
     * This chatbot develops a personality of Cristiano Ronaldo,
     * a world renowned footballer and the most followed celebrity in the world.
     * Firstly, it greets the user, before enquiring the user on their purpose.
     * The chatbot runs on an infinite while loop until the user enters an input.
     * By default, the input will be taken as a task, and will be added to a list of tasks.
     * However, if the input is empty or includes spaces, an exception error message is sent.
     * If the input is "bye", the chatbot bids its farewell and exits the program.
     *
     * @param args Command line arguments;
     */
    public static void main(String[] args) {
        Ronaldo ronaldoInstance = new Ronaldo();
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
            switch (input[0]) {
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
                    ronaldoInstance.addEvent(input[1]);
                    break;
                case "todo":
                    ronaldoInstance.addTodo(input[1]);
                    break;
                case "deadline":
                    ronaldoInstance.addDeadline(input[1]);
                    break;
                case "siu":
                    ronaldoInstance.exclaim();
                    break;
                default:
                    ronaldoInstance.reject("Format");
                    break;
            }
        }
    }

}

