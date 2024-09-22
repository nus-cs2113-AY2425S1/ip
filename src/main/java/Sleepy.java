import java.util.Scanner;
public class Sleepy {
    public static final String MARK = "mark";
    private static final String LOGO = """
                ____________________________________________________________
                  ____   _      ____   ____  ____   __    __
                 / ___| | |    | ___| | ___| |    \\ \\ \\  / /
                 \\___ \\ | |    | ===| | ===| | D  |  \\ V  /
                  ___) || |___ | |__  | |__  |   _/   |  |
                 |____/ |____| |____| |____| |__|     |__|
                """;
    private static final String LINE_SEPARATOR = "____________________________________________________________\n";
    public static final String BYE = "bye";
    public static final String LIST = "list";
    public static final String UNMARK = "unmark";
    public static final String DELETE = "delete";
    public static final String FIND = "find";

    public static void main(String[] args) {
        System.out.println(LOGO);
        String greeting = getGreeting();
        System.out.println(greeting);

        String line;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager(Storage.loadTasks());
        line = in.nextLine();
        //checks for all the keywords
        while (!line.equals(BYE)) {
            try {
                if (line.equals(LIST)) {
                    taskManager.listTasks();
                } else if (line.startsWith(MARK)) {
                    int taskNumber = Integer.parseInt(line.substring(4).trim());
                    taskManager.markTask(taskNumber);
                } else if (line.startsWith(UNMARK)) {
                    int taskNumber = Integer.parseInt(line.substring(6).trim());
                    taskManager.unmarkTask(taskNumber);
                } else if (line.startsWith(DELETE)){
                    int taskNumber = Integer.parseInt(line.substring(6).trim());
                    taskManager.deleteTask(taskNumber);
                } else if (line.startsWith(FIND)) {
                    String keyword = line.substring(4).trim();
                    taskManager.findTask(keyword);
                } else {
                    taskManager.addTask(line);
                }

                Storage.saveTasks(taskManager.getTasks());
            } catch (SleepyException e) {
                System.out.println(LINE_SEPARATOR + e.getMessage() + "\n" + LINE_SEPARATOR);
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
