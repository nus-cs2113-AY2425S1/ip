import java.util.Scanner;

public class SuBOT {
    private static boolean stopFlag = false;
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    /**
     * Process user's input command
     * @param command Input command
     */
    public static void process(String command) {
        String[] argv = command.split(" ", 2);
        int argc = argv.length;

        String action = argv[0];
        String commandArgs;
        int taskIndex;

        switch (action) {
        case "bye":
            stopFlag = true;
            System.out.println("Bye bye...");
            break;
        case "list":
            for (int i = 0; i < taskCount; ++i) {
                System.out.printf("%d.", i+1);
                tasks[i].printTask();
            }
            break;
        case "mark":
        case "unmark":
            try {
                commandArgs = argv[1];
                taskIndex = Integer.parseInt(commandArgs) - 1;
                tasks[taskIndex].setDone(action.equals("mark"));
                System.out.printf("I have %sed the following task for you:\n", action);
                tasks[taskIndex].printTask();
            } catch (Exception e) {
                System.out.println("Invalid argument(s): " + e);
                System.out.println("Usage: mark/unmark <taskNumber>");
            }
            break;
        case "deadline":
            try {
                commandArgs = argv[1];
                int slashIndex = commandArgs.indexOf("/by");
                String dlName = commandArgs.substring(0, slashIndex-1);
                //Not take the space before slash
                String dlTime = commandArgs.substring(slashIndex+4);
                tasks[taskCount] = new Deadline(dlName, dlTime);
                System.out.println("Deadline task added!\n");
                tasks[taskCount++].printTask();
            } catch (Exception e) {
                System.out.println("Invalid argument(s): " + e);
                System.out.println("Usage: deadline <description> /by <date>");
            }
            break;
        case "todo":
            try {
                commandArgs = argv[1];
                tasks[taskCount] = new ToDo(commandArgs);
                System.out.println("Todo task added!\n");
                tasks[taskCount++].printTask();
            } catch (Exception e) {
                System.out.println("Invalid argument(s): " + e);
                System.out.println("Usage: todo <description>");
            }
            break;
        case "event":
            try {
                commandArgs = argv[1];
                int fromSlashIndex = commandArgs.indexOf("/from");
                int toSlashIndex = commandArgs.indexOf("/to");
                String eventName = commandArgs.substring(0, fromSlashIndex- 1);
                String eventFrom = commandArgs.substring(fromSlashIndex+6, toSlashIndex-1);
                String eventTo = commandArgs.substring(toSlashIndex+4);
                tasks[taskCount] = new Event(eventName, eventFrom, eventTo);
                System.out.println("Deadline task added!\n");
                tasks[taskCount++].printTask();
            } catch (Exception e) {
                System.out.println("Invalid argument(s): " + e);
                System.out.println("Usage: event <description> /from <from> /to <to>");
            }
            break;
        default:
            System.out.println("Invalid action: " + action);
            break;
        }
    }

    public static void main(String[] args) {
        String logo = """
                       ____  ____  ______
           _______  __/ __ )/ __ \\/_  __/
          / ___/ / / / __  / / / / / /
         (__  ) /_/ / /_/ / /_/ / / /
        /____/\\__,_/_____/\\____/ /_/
        """;

        System.out.println(logo);
        System.out.println("How can I help you today?");
        System.out.println("-------------------------------");
        Scanner sc = new Scanner(System.in);
        String command;
        
        while (!stopFlag) {
            command = sc.nextLine();
            System.out.println("-------------------------------");
            process(command);
            System.out.println("-------------------------------");
        }
        System.out.println("System shutting down...");
        System.out.println("Goodbye world!");
    }
}
