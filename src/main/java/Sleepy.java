import java.util.Scanner;
public class Sleepy {
    public static void main(String[] args) {
        String logo = "  ____   _      ____   ____  ____   __    __\n"
                + " / ___| | |    | ___| | ___| |    \\ \\ \\  / /\n"
                + " \\___ \\ | |    | ===| | ===| | D  |  \\ V  /\n"
                + "  ___) || |___ | |__  | |__  |   _/   |  |\n"
                + " |____/ |____| |____| |____| |__|     |__|\n";

        System.out.println("____________________________________________________________\n" + logo);
        String greeting = "Hello...I'm Sleepy\n"
                + "What did u wake me up for?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);

        String line;
        Scanner in = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                TaskManager.listTasks();
            } else {
                TaskManager.addTask(line);
            }
            line = in.nextLine();
        }
        String goodbye = "____________________________________________________________\n"
                + "Bye. Going back to sleep...ZZZ\n"
                + "____________________________________________________________\n";
        System.out.println(goodbye);
    }

    private static void printResponse(String line) {
        System.out.println("____________________________________________________________\n"
                + line + "\n"
                + "____________________________________________________________\n");
    }
}
