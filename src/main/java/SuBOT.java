import java.util.Scanner;

public class SuBOT {
    public static void main(String[] args) {
        String logo = """
                       ____  ____  ______
           _______  __/ __ )/ __ \\/_  __/
          / ___/ / / / __  / / / / / /
         (__  ) /_/ / /_/ / /_/ / / /
        /____/\\__,_/_____/\\____/ /_/
        """;

        System.out.println(logo);
        System.out.println("How can I help you today?");
        System.out.println("-------------------------------");
        Scanner sc = new Scanner(System.in);
        String command;
        boolean stopFlag = false;
        while (!stopFlag) {
            command = sc.nextLine();
            System.out.println("-------------------------------");
            if (command.equals("bye"))  {
                stopFlag = true;
                System.out.println("Bye bye...");
            }
            else System.out.println(command);
            System.out.println("-------------------------------");
        }
        System.out.println("System shutting down...");
        System.out.println("Goodbye world!");
    }
}
