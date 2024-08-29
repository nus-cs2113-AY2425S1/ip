import java.util.Scanner;

public class Rizzler {
    Emoji emoji = new Emoji();

    public void displayMenu() {
        System.out.println("What can I do to make your day pop? " + emoji.getPartyPopperEmoji() + emoji.getRocketEmoji());
        String menu = """
                  --------------------------------------------------------
                  1. Just type in a task, and Rizzler's got it handled!
                  2. Need the full rundown? Type 'rizz list' and I’ll drop the goods!
                  3. Crushed a task? Type 'rizz done [task number]' and let's celebrate!
                  4. Changed your mind? Type 'rizz undo [task number]' and I'll reset that for you!
                  5. Wanna bounce? Just hit me with 'rizz bye' and I'll catch you on the flip side!
                  --------------------------------------------------------
                  """;

        System.out.println(menu);
    }

    public void listenCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        TaskManager taskManager = new TaskManager();

        while (!command.equals("rizz bye")) {
            if (command.equals("rizz list")) {
                taskManager.displayTaskList();
            }
            else if (command.startsWith("rizz done")) {
                int taskNumber = Integer.parseInt(command.split(" ")[2]);
                taskManager.completeTask(taskNumber);
            }
            else if (command.startsWith("rizz undo")) {
                int taskNumber = Integer.parseInt(command.split(" ")[2]);
                taskManager.undoTask(taskNumber);
            }
            else {
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