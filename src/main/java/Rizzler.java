import java.util.Scanner;

public class Rizzler {
    Emoji emoji = new Emoji();

    public void displayMenu() {
        System.out.println("What can I do to make your day pop? " + emoji.getPartyPopperEmoji() + emoji.getRocketEmoji());
        final String MENU = """
                  --------------------------------------------------------
                  1. To add a TODO task type 'todo [task]'
                     to add a DEADLINE task type 'deadline [task] BY [deadline]'
                     to add a EVENT task type 'event [task] FROM [start time] TO [end time]' , and Rizzler's got it handled!\s
                  2. Need the full rundown? Type 'list' and I’ll drop the goods!
                  3. Crushed a task? Type 'mark [task number]' and let's celebrate!
                  4. Changed your mind? Type 'undo [task number]' and I'll reset that for you!
                  5. Wanna bounce? Just hit me with 'bye' and I'll catch you on the flip side!
                  --------------------------------------------------------
                 \s""";

        System.out.println(MENU);
    }

    public void listenCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            try {
                handleCommand(command);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
            command = scanner.nextLine();
        }
        System.out.println("Peace out, Rizzler’s got places to be! " + emoji.getCoolFaceEmoji() + emoji.getFistBumpEmoji());
    }

    private void handleCommand(String command) throws InvalidInputException {
        TaskManager taskManager = new TaskManager();

        if (command.equals("list")) {
            taskManager.displayTaskList();

        } else if (command.startsWith("mark")) {
            try {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                taskManager.completeTask(taskNumber);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Task number must be an integer --> 'mark [task number]'" + emoji.getExclamationMarkEmoji());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERROR: Please follow the format specified in the MENU --> 'mark [task number]'" + emoji.getExclamationMarkEmoji());
            }

        } else if (command.startsWith("undo")) {
            try {
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                taskManager.undoTask(taskNumber);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Task number must be an integer 'undo [task number]'" + emoji.getExclamationMarkEmoji());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERROR: Please follow 'undo [task number]' format" + emoji.getExclamationMarkEmoji());
            }

        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            taskManager.addTask(command);

        } else {
            throw new InvalidInputException("That's an invalid command! " + emoji.getCrossMarkEmoji() + emoji.getCrossMarkEmoji());  // Throw an exception for invalid commands
        }
    }


    public static void main(String[] args) {
        final String LOGO =
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

        System.out.println(LOGO);
        System.out.println("Yo! I'm Rizzler.");
        rizzler.displayMenu();
        rizzler.listenCommand();
    }
}