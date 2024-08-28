import java.util.Scanner;

public class Rizzler {
    Emoji emoji = new Emoji();

    public void displayMenu() {
        System.out.println("What can I do to make your day pop? " + emoji.getPartyPopperEmoji() + emoji.getRocketEmoji());
        String menu = """
                  --------------------------------------------------------
                  1. Just type in a task, and Rizzler's got it handled!
                  2. Need the full rundown? Type 'list' and I’ll drop the goods!
                  3. Wanna bounce? Just hit me with 'bye' and I'll catch you on the flip side!
                  --------------------------------------------------------
                  """;

        System.out.println(menu);
    }

    public void echo() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        Task task = new Task();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                task.displayTaskList();
            }
            else {
                task.addTask(command);
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

        Emoji emoji = new Emoji();
        Rizzler rizzler = new Rizzler();

        System.out.println(logo);
        System.out.println("Yo! I'm Rizzler.");
        rizzler.displayMenu();
        rizzler.echo();
    }
}