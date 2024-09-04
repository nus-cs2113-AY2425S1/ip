package main.java;

import java.util.Scanner;

public class MrStripes {
    public static void main(String[] args) {
        String Greeting = " hello";
        String Goodbye = "goodbye!";

        System.out.println(Greeting);

        Scanner scanner = new Scanner(System.in);
        Commands commands = new Commands();
        while (commands.isAcceptingCommands()) {
            commands.add(scanner.nextLine());
            commands.echo();
        }

        System.out.println(Goodbye);
    }
}
