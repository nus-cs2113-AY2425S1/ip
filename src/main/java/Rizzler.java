import java.util.Scanner;

public class Rizzler {
    Emoji emoji = new Emoji();

    public void displayMenu() {
        System.out.println("What can I do to make your day pop? " + emoji.getPartyPopperEmoji() + emoji.getRocketEmoji());
        String menu = """
                  --------------------------------------------------------
                  1. To add a TODO task just type the task
                     to add a DEADLINE task type '[task] BY [deadline]'
                     to add a EVENT task type '[task] FROM [start time] TO [end time]' , and Rizzler's got it handled!    
                  2. Need the full rundown? Type 'list' and I’ll drop the goods!
                  3. Crushed a task? Type 'mark [task number]' and let's celebrate!
                  4. Changed your mind? Type 'undo [task number]' and I'll reset that for you!
                  5. Wanna bounce? Just hit me with 'bye' and I'll catch you on the flip side!
                  --------------------------------------------------------
                  """;

        System.out.println(menu);
    }

    public void listenCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        TaskManager taskManager = new TaskManager();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                taskManager.displayTaskList();

            } else if (command.startsWith("mark")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                taskManager.completeTask(taskNumber);

            } else if (command.startsWith("undo")) {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                taskManager.undoTask(taskNumber);

            } else {
                taskManager.addTask(command);
            }

            command = scanner.nextLine();
        }
        System.out.println("Peace out, Rizzler’s got places to be! " + emoji.getCoolFaceEmoji() + emoji.getFistBumpEmoji());
    }

    public static void main(String[] args) {
        String logo =
                """
                         _____                          _\s
                        |     \\                        | |\s
                        | |_)  |      ______   ______  | |   ____    _  __\s
                        |     /  (_) |__   /  |__   /  | | /      \\ | |/  \\\s
                        | |\\ \\   | |   /  /     /  /   | | |  ____/ |  _/\\_\\\s
                        | | \\ \\  | |  /  / __  /  / __ | | | |____  | | \s
                        |_|  \\_\\ |_| /______/ /______/ |_|  \\_____| |_|\s
                        """;

        Rizzler rizzler = new Rizzler();

        System.out.println(logo);
        System.out.println("Yo! I'm Rizzler.");
        rizzler.displayMenu();
        rizzler.listenCommand();
    }
}