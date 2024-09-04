

import java.util.Scanner;

public class MrStripes {
    public static void main(String[] args) {

        System.out.println(Texts.GREETINGS);

        Scanner scanner = new Scanner(System.in);
        Commands commands = new Commands();
        while (commands.isAcceptingCommands()) {
            commands.accept(scanner.nextLine());
        }

        System.out.println(Texts.GOODBYE);
    }
}
