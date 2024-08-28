import java.util.Scanner;

public class Rizzler {

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        String coolFaceEmoji = "\uD83D\uDE0E";
        String fistBumpEmoji = "\uD83D\uDC4A";
        String rockstarHandEmoji = "\uD83E\uDD1F\uD83C\uDFFD";
        String fireEmoji = "\uD83D\uDD25";

        while (!command.equals("bye")) {
            if (command.contains("!")) {
                System.out.println(command + " " + rockstarHandEmoji + fireEmoji);
            }
            else {
                System.out.println(command + " " + rockstarHandEmoji);
            }
            command = scanner.nextLine();
        }

        System.out.println("Peace out, Rizzler’s got places to be! " + coolFaceEmoji + fistBumpEmoji);
    }
    public static void main(String[] args) {
        String logo =
                  " _____                          _ \n"
                + "|     \\                        | | \n"
                + "| |_)  |      ______   ______  | |   ____    _  __ \n"
                + "|     /  (_) |__   /  |__   /  | | /      \\ | |/  \\ \n"
                + "| |\\ \\   | |   /  /     /  /   | | |  ____/ |  _/\\_\\ \n"
                + "| | \\ \\  | |  /  / __  /  / __ | | | |____  | |  \n"
                + "|_|  \\_\\ |_| /______/ /______/ |_|  \\_____| |_| \n";


        String partyPopperEmoji = "\uD83C\uDF89";
        String rocketEmoji = "\uD83D\uDE80";

        System.out.println(logo);
        System.out.println("Yo! I'm Rizzler.");
        System.out.println("What can I do to make your day pop? " + partyPopperEmoji + rocketEmoji);
        echo();
    }
}