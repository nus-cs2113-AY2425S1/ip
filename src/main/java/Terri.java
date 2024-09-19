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

        System.out.println(logo);

        System.out.println("Heya! I'm Terri.");

        printInstructions();

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

            // Define behaviour depending on initial keyword
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
                case "deadline":

                    String newBy = null;
                    String newDeadline = null;

                    // TODO: Abstract the below description/deadline parsing logic out

                    // Create temp description string to iteratively added to
                    StringBuilder tempDeadlineInfo = new StringBuilder();

                    // Parse description and deadline from user input
                    for (int i = 1; i <= keyWord.length; i++) {
                        // Stop parsing description and begin parsing deadline
                        if (keyWord[i].equals("/by")) {

                            StringBuilder tempBy = new StringBuilder();

                            for (int y = i + 1; y < keyWord.length; y++) {
                                tempBy.append(keyWord[y]).append(" ");
                            }

                            // Allocate collated info to strings to output
                            newBy = tempBy.toString().trim();
                            newDeadline = tempDeadlineInfo.toString().trim();

                            break;
                        }
                        // Add current word to description
                        tempDeadlineInfo.append(keyWord[i]).append(" ");
                    }

                    TaskList.addDeadline(newDeadline, newBy);
                    break;

                case "event":
                    String newDescription;
                    String newStart;
                    String newEnd;

                    // Initialise variables arbitrarily
                    int startIdx = 0;
                    int endIdx = 0;

                    // Create temp strings to be iteratively added to
                    StringBuilder tempStart = new StringBuilder();
                    StringBuilder tempEnd = new StringBuilder();
                    StringBuilder tempEventInfo = new StringBuilder();

                    // Finds indexes corresponding to start time/end time
                    for (int i = 0; i < keyWord.length; i++) {

                        if (keyWord[i].equals("/from")) {
                            startIdx = i;
                        }

                        if (keyWord[i].equals("/to")) {
                            endIdx = i;
                            break;
                        }
                    }

                    // Construct relevant string fields
                    for (int i = 1; i < startIdx; i++) {
                        tempEventInfo.append(keyWord[i]).append(" ") ;
                    }

                    for (int i = startIdx+1; i < endIdx; i++) {
                        tempStart.append(keyWord[i]).append(" ") ;
                    }

                    for (int i = endIdx+1; i < keyWord.length; i++) {
                        tempEnd.append(keyWord[i]).append(" ") ;
                    }

                    newDescription = tempEventInfo.toString().trim();
                    newStart = tempStart.toString().trim();
                    newEnd = tempEnd.toString().trim();

                    TaskList.addEvent(newDescription, newStart, newEnd);
                    break;

                case "todo":
                    TaskList.addToDo(userInput);
                    break;

                // Create new ToDo
                default:
                    TaskList.addToDo(userInput);
            }
        }
    }
    public static void printDivider() {
        String divider = "____________________________________________________________";
        System.out.println(divider);
    }

    private static void printInstructions() {
        System.out.println("I can log a ton of different tasks for you " +
                "use different keywords followed by a description to tell me what to do:");

        printDivider();

        System.out.println("KEYWORDS:");
        System.out.println("'todo' + (description) : logs a task with no specific due date");
        System.out.println("'deadline' + (description) + '/by (due date)' : logs a task due " +
                "at a specific point in time");
        System.out.println("'event' +  (description) + '/from (start time)' + '/to (end time)' : " +
                "logs an event occurring during a specified period");
        System.out.println("If I don't see one of the above keywords, " +
                "I'll just assume you're inputting a ToDo!");

        printDivider();

        System.out.println("UTILITY:");
        System.out.println("'list' : Generates a list of all currently logged tasks/events, " +
                "ordered oldest-newest. The number before each task is it's 'task number'");

        System.out.println("'mark' + '(task number)' : Records that a task has been completed");
        System.out.println("'unmark' + '(task number)' : Records that a task has not been completed");

        printDivider();

        System.out.println("So - what can I help you with today?");
    }
}
