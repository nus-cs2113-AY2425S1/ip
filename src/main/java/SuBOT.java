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
        String[] commands = new String[100];
        int commandCount = 0;
        boolean stopFlag = false;
        while (!stopFlag) {
            command = sc.nextLine();
            System.out.println("-------------------------------");
            switch (command) {
                case "bye":
                    stopFlag = true;
                    System.out.println("Bye bye...");
                    break;
                case "list":
                    for (int i = 0; i < commandCount; ++i) {
                        System.out.printf("%d. %s\n", i+1, commands[i]);
                    }
                    break;
                default:
                    commands[commandCount++] = command;
                    System.out.println(command);
            }
            System.out.println("-------------------------------");
        }
        System.out.println("System shutting down...");
        System.out.println("Goodbye world!");
    }
}
