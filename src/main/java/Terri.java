import java.util.Scanner;


public class Terri {
    final static int MAXTASKS = 100;

    public static void main(String[] args) {
        String logo = "\n" +
                "$$$$$$$$\\                               $$\\ \n" +
                "\\__$$  __|                              \\__|\n" +
                "   $$ |    $$$$$$\\   $$$$$$\\   $$$$$$\\  $$\\ \n" +
                "   $$ |   $$  __$$\\ $$  __$$\\ $$  __$$\\ $$ |\n" +
                "   $$ |   $$$$$$$$ |$$ |  \\__|$$ |  \\__|$$ |\n" +
                "   $$ |   $$   ____|$$ |      $$ |      $$ |\n" +
                "   $$ |   \\$$$$$$$\\ $$ |      $$ |      $$ |\n" +
                "   \\__|    \\_______|\\__|      \\__|      \\__|\n" +
                "                                            \n";

        System.out.println("Hello from\n" + logo);
        String divider = "____________________________________________________________";
        System.out.println("Heya! I'm Terri.");
        System.out.println("What can I help you with today?");

        // Initialise a scanner to read input
        Scanner scanner = new Scanner(System.in);

        // Initialise Task array and counter
        Task[] taskList = new Task[MAXTASKS];
        int taskCounter = 0;

        // Continually check for user input
        while (true) {
            String userInput = scanner.nextLine();
            System.out.println(divider);

            switch (userInput) {
                // Short-circuit check for exit input
                case "bye":
                    System.out.println("Bye then! See ya soon!\n"+divider);
                    return;
                case "list":
                    for (int i = 1; i <= taskCounter; i++) {
                        System.out.println(i+". "
                                + taskList[i-1].retrieveTaskName());
                    }
                    System.out.println(divider);
                    break;
                // Create new task
                default:
                    taskList[taskCounter++] = new Task(userInput);
                    System.out.println("Just added: " + userInput + "!");
                    System.out.println(divider);
                    break;
            }
        }

    }


}
