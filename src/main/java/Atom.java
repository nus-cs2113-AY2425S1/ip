import java.util.Scanner;

public class Atom {
    public static void divider() {
        System.out.println("__________________________________________________");
    }

    public static void main(String[] args) {

        divider();

        String logo = "    ____   __________  ________  __       __\n"
                + "   / __ \\ |___    ___||  ____  ||  \\     /  |\n"
                + "  / /__\\ \\    |  |    | |    | || |\\\\   //| |\n"
                + " / /    \\ \\   |  |    | |____| || | \\\\_// | |\n"
                + "/_/      \\_\\  |__|    |________||_|  \\_/  |_|\n";

        System.out.println(logo);

        divider();

        System.out.println("Hey there! I'm your friendly chatbot, ATOM!");
        System.out.println("How can i assist you today?");
        System.out.println("\nTIP: Type \"bye\" to exit program");

        divider();

        String line;
        Scanner in = new Scanner(System.in);

        System.out.print("Enter command: ");
        line = in.nextLine();

        while (!line.equalsIgnoreCase("bye")) {
            divider();
            System.out.println("You said: " + line);
            divider();
            System.out.print("Enter command: ");
            line = in.nextLine();
        }

        divider();

        System.out.println("Bye Bye. See ya soon!");

        divider();

    }
}
