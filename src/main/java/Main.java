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
                System.out.println("Woops, your input is empty, just like Spurs' trophy cabinet.\n");
                continue;
            } else if (words.contains("messi")) {
                ronaldoInstance.boast();
            } else if (words.contains("siu")) {
                ronaldoInstance.exclaim();
            }
            String[] input = words.split(" ");

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
                default:
                    ronaldoInstance.addGoal(words);
                    break;
            }
        }
    }

}



