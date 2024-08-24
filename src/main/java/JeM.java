import java.util.Scanner;

public class JeM {
    public static void main(String[] args) {
        String logo = "      _         __  __ \n"
                + "     | |       |  \\/  |\n"
                + "     | |  ___  | |\\/| |\n"
                + " _   | | / _ \\ | |  | |\n"
                + "| |__| ||  __/ | |  | |\n"
                + " \\____/  \\___| |_|  |_|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");

        System.out.println(" Hello! I'm JeM, Your e-Assistant");
        System.out.println(" What can I do for you? Type bye to end chat");

        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        while(true){
            String line = scanner.nextLine();

            if (line.equals("Bye") || line.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            System.out.println(line);
        }

        System.out.println("____________________________________________________________");
        scanner.close();
    }
}

