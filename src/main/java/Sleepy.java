import java.util.Scanner;
public class Sleepy {
    private static final String LOGO = """
                ____________________________________________________________
                  ____   _      ____   ____  ____   __    __
                 / ___| | |    | ___| | ___| |    \\ \\ \\  / /
                 \\___ \\ | |    | ===| | ===| | D  |  \\ V  /
                  ___) || |___ | |__  | |__  |   _/   |  |
                 |____/ |____| |____| |____| |__|     |__|
                """;
    private static final String LINE_SEPARATOR = "____________________________________________________________\n";
    public static void main(String[] args) {
        System.out.println(LOGO);
        String greeting = getGreeting();
        System.out.println(greeting);

        String line;
        Storage storage = new Storage();
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager(storage.loadTasks());
        line = in.nextLine();
        while (!line.equals("bye")) {
            try {
                if (line.equals("list")) {
                    taskManager.listTasks();
                } else if (line.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(line.substring(5));
                    taskManager.markTask(taskNumber);
                } else if (line.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(line.substring(7));
                    taskManager.unmarkTask(taskNumber);
                } else {
                    taskManager.addTask(line);
                }

                storage.saveTasks(taskManager.getTasks());
            } catch (SleepyException e) {
                System.out.println(LINE_SEPARATOR + e.getMessage() + LINE_SEPARATOR);
            } catch (NumberFormatException e) {
                System.out.println(LINE_SEPARATOR + "Invalid task number format. Please enter a valid number.\n" + LINE_SEPARATOR);
            }
            line = in.nextLine();
        }
        String goodbye = getGoodbye();
        System.out.println(goodbye);
    }

    private static String getGoodbye() {
        return LINE_SEPARATOR + "Bye. Going back to sleep...ZZZ\n" + LINE_SEPARATOR;
    }

    private static String getGreeting() {
        return """
                Hello...I'm Sleepy
                What did u wake me up for?
                """
                + LINE_SEPARATOR;
    }
}
