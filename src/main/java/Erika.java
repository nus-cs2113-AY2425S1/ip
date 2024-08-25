import java.util.Scanner;

public class Erika {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        String logo =
                " _______   ________  ___  ___  __    ________     \n" +
                "|\\  ___ \\ |\\   __  \\|\\  \\|\\  \\|\\  \\ |\\   __  \\    \n" +
                "\\ \\   __/|\\ \\  \\|\\  \\ \\  \\ \\  \\/  /|\\ \\  \\|\\  \\   \n" +
                " \\ \\  \\_|/_\\ \\   _  _\\ \\  \\ \\   ___  \\ \\   __  \\  \n" +
                "  \\ \\  \\_|| \\ \\  \\\\  \\\\ \\  \\ \\  \\\\ \\  \\ \\  \\ \\  \\ \n" +
                "   \\ \\_______\\ \\__\\\\ _\\\\ \\__\\ \\__\\\\ \\__\\ \\__\\ \\__\\\n" +
                "    \\|_______|\\|__|\\|__|\\|__|\\|__| \\|__|\\|__|\\|__|";
        System.out.println("Hello from\n" + logo + "\n");
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Erika\n What can I do for you?");
        System.out.println("____________________________________________________________");
        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________________________");
            line = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Auf Wiedersehen! Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
