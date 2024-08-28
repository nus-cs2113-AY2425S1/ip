import java.util.Scanner;

public class SuBOT {
    private static boolean stopFlag = false;
    private static Task[] tasks = new Task[100];
    private static int taskCount;

    /**
     * Process user's input command
     * @param command Input command
     */
    public static void process(String command) {
        String[] argv = command.split(" ");
        int argc = argv.length;

        String action = argv[0];
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
                    taskIndex = Integer.parseInt(argv[1]) - 1;
                    tasks[taskIndex].setDone(action.equals("mark"));
                    System.out.printf("I have %sed the following task for you:\n", action);
                    tasks[taskIndex].printTask();
                }
                catch (Exception e) {
                    System.out.println("Invalid argument(s): " + e);
                    System.out.println("Usage: mark/unmark <taskNumber>");
                }
                break;
            default:
                tasks[taskCount++] = new Task(command);
                System.out.println(command);
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
