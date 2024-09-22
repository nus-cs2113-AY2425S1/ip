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
    private static final String CMD_BYE = "bye";

    private final Scanner input;
    private final TaskManager taskManager;

    public Sleepy(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        System.out.println(LOGO);
        Sleepy ui = new Sleepy(new TaskManager(Storage.loadTasks()));
        ui.run();
    }

    public void run() {
        System.out.println(getGreeting());

        String line = input.nextLine();
        while (!line.equals(CMD_BYE)) {
            try {
                Parser.parse(line, taskManager);
                Storage.saveTasks(taskManager.getTasks());
            } catch (SleepyException e) {
                System.out.println(LINE_SEPARATOR + e.getMessage() + LINE_SEPARATOR);
            } catch (NumberFormatException e) {
                System.out.println(LINE_SEPARATOR + "Invalid task number format. Please enter a valid number.\n" + LINE_SEPARATOR);
            }
            line = input.nextLine();
        }
        System.out.println(getGoodbye());
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
