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
            Terri.printDivider();

            // Isolate individual keywords in user input

            String[] keyWord = userInput.split(" ");

            switch (keyWord[0]) {
                // Short-circuit check for exit input
                case "bye":
                    System.out.println("Bye then! See ya soon!\n");
                    Terri.printDivider();
                    return;
                // List all tasks with completion indicators
                case "list":
                    TaskList.listTasks();
                    break;
                // Mark tasks complete
                case "mark":
                    TaskList.markDone(Integer.parseInt(keyWord[1]) - 1);
                    break;
                // Mark tasks not complete
                case "unmark":
                    TaskList.markNotDone(Integer.parseInt(keyWord[1]) - 1);
                    break;
                // Create new task
                default:
                    TaskList.addTask(userInput);
            }
        }
    }
    public static void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }
}
