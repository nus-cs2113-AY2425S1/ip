package main.java;
import java.util.Scanner;
import Commands.;

public class MrStripes {
    public static void main(String[] args) {
        String Greeting = " hello";
        String Goodbye = "goodbye!";
        Scanner scanner = new Scanner(System.in);
        Commands commands = new Commands();
        while (true) {
            commands.add(scanner.nextLine());
            commands.echo();
        }

        System.out.println(Greeting);
        System.out.println(Goodbye);
    }
}
