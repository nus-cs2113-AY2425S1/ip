import java.util.Scanner;
public class Sleepy {
    public static void main(String[] args) {
        String logo = """
                  ____   _      ____   ____  ____   __    __
                 / ___| | |    | ___| | ___| |    \\ \\ \\  / /
                 \\___ \\ | |    | ===| | ===| | D  |  \\ V  /
                  ___) || |___ | |__  | |__  |   _/   |  |
                 |____/ |____| |____| |____| |__|     |__|
                """;

        System.out.println("____________________________________________________________\n" + logo);
        String greeting = """
                Hello...I'm Sleepy
                What did u wake me up for?
                ____________________________________________________________
                """;
        System.out.println(greeting);

        String line;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                TaskManager.listTasks();
            } else if (line.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(line.substring(5));
                taskManager.markTask(taskNumber);
            } else if (line.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(line.substring(7));
                taskManager.unmarkTask(taskNumber);
            } else {
                TaskManager.addTask(line);
            }
            line = in.nextLine();
        }
        String goodbye = """
                ____________________________________________________________
                Bye. Going back to sleep...ZZZ
                ____________________________________________________________
                """;
        System.out.println(goodbye);
    }
}
