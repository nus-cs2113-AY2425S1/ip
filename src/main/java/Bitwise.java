import java.util.Scanner;

public class Bitwise {
    public static void main(String[] args) {
        // ASCII art from https://ascii-generator.site/t/
        String logo = " ____   _  _              _            \n"
                + "|  _ \\ (_)| |            (_)           \n"
                + "| |_) | _ | |_ __      __ _  ___   ___ \n"
                + "|  _ < | || __|\\ \\ / /| |/ __| / _ \\ \n"
                + "| |_) || || |_  \\ V  V / | |\\__ \\|  __/\n"
                + "|____/ |_| \\__|  \\_/\\_/  |_||___/ \\___|\n"
                + "                                       \n";
        String sectionBreak = "==================================================\n";
        String lineBreak = "--------------------------------------------------\n";
        System.out.println(sectionBreak + "Hello from\n" + logo);
        System.out.print("How may I help you today?\n" + lineBreak);
        echoUserInput();
        System.out.println("Bye, see you soon!\n" + sectionBreak);
    }
    public static void echoUserInput() {
        String userInput;
        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                return;
            }
            System.out.println(userInput);
        }
    }
}
