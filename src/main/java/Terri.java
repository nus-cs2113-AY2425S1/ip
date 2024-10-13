import java.util.Scanner;
import java.util.Arrays;

/** TODO Convert Task Class to an abstract one if possible (no reason for it to exist)
 *  TODO Refactor dummy methods in Task Class to be abstract if possible too
 *  TODO Comment all code per JavaDoc specifications
 *  TODO Convert TaskList to a extensible array to do away with artificial limitations
 */


public class Terri {
    final static int MAXTASKS = 100;
    final static String UNRECOGNISED_KEYWORD = "Hey! I don't understand that command.";
    final static String OFFER_REFRESHER = "Did you want a refresher on what tasks I can do for ya? Type Y to accept.";
    final static String REFRESHER_ACCEPTED = "No worries! Here's all the things I can do again.";
    final static String REFRESHER_DECLINED = "Gotcha - let me know what I can do for ya then!";

    private static void printWelcomeMessage() {
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

        System.out.println(logo);
        System.out.println("Heya! I'm Terri.");
        printInstructions();
    }


    public static void main(String[] args) {

        printWelcomeMessage();

        // Initialise a scanner to read input
        Scanner scanner = new Scanner(System.in);
        // Initialise Task array and counter
        Task[] taskList = new Task[MAXTASKS];

        // Continually check for and process user input
        while (true) {
            String userInput = scanner.nextLine().trim();

            // Short-circuit check for exit input.
            if (userInput.equalsIgnoreCase("bye")) {
                // Exit Chatbot and close scanner.
                exitTerri(scanner);
                break;
            }

            // Process input and catch exceptions appropriately
            try {
                handleInput(userInput, taskList);
            }
            catch (TerriException ex) {
                // Print custom error message and offer general refresher
                System.out.println(ex.getMessage());
                System.out.println(OFFER_REFRESHER);

                if (scanner.nextLine().equalsIgnoreCase("Y")) {
                    System.out.println(REFRESHER_ACCEPTED);
                    printInstructions();
                } else {
                    System.out.println(REFRESHER_DECLINED);
                }
            }
            printDivider(); 
        }
    }


    private static void handleInput(String userInput, Task[] taskList) throws TerriException {

        // Isolate individual keywords in user input
        String[] keyWord = userInput.split(" ");

        // Define behaviour depending on initial keyword
        switch (keyWord[0].toLowerCase()) {
            // List all tasks with completion indicators
            case "list":
                TaskList.listTasks();
                break;
            // Mark tasks complete
            case "mark":
                TaskList.handleSetDone(keyWord,true);
                break;
            // Mark task not complete
            case "unmark":
                TaskList.handleSetDone(keyWord,false);
                break;
            case "deadline":
                TaskList.handleDeadline(keyWord);
                break;
            case "event":
                TaskList.handleEvent(keyWord);
                break;
            case "todo":
                TaskList.addToDo(keyWord);
                break;
            // Handle unrecognised keywords by offering instruction page
            default:
                throw new TerriException(UNRECOGNISED_KEYWORD);
        }
    }


    public static void exitTerri(Scanner scanner) {
    System.out.println("Bye then! See ya soon!\n");
    printDivider();
    // Close scanner after loop ends and program terminates
    scanner.close();
    }

    public static void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    // Print program instructions
    private static void printInstructions() {
        System.out.println("I can log and manage a ton of different tasks - just " +
                "use different keywords followed by a description to tell me what to do:");

        printDivider();

        System.out.println("KEYWORDS:");
        System.out.println("'todo' + (description) : logs a task with no specific due date");
        System.out.println("'deadline' + (description) + '/by (due date)' : logs a task due " +
                "at a specific point in time");
        System.out.println("'event' +  (description) + '/from (start time)' + '/to (end time)' : " +
                "logs an event occurring during a specified period");
        System.out.println("If I don't see one of the above keywords, " +
                "I'll ask ya if you want a refresher!");

        printDivider();

        System.out.println("UTILITY:");
        System.out.println("'list' : Generates a list of all currently logged tasks/events, " +
                "ordered oldest-newest. The number before each task is it's 'task number'");

        System.out.println("'mark' + '(task number)' : Records that a task has been completed");
        System.out.println("'unmark' + '(task number)' : Records that a task has not been completed");

        printDivider();

        System.out.println("So - what can I help you with today?");

        printDivider();

    }

    // Return the contents of a subarray as a concatenated string
    public static String extractSubArray(String[] array, int start, int end) {
        return String.join(" ", Arrays.copyOfRange(array, start, end)).trim();
    }


}
