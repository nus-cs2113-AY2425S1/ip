import java.util.Scanner;

public class IO {
    private static Scanner scanner;;

    public static void init() {
        scanner = new Scanner(System.in);
    }

    public static void printAnswer(String text) {
        String lineSeparator = "-------------------------------";
        text = lineSeparator +  "\n" + text + "\n" + lineSeparator + "\n";
        String formattedText = text.replaceAll("(?m)^", "\t");
        System.out.print(formattedText);
    }

    public static String getQuestion() {
        return scanner.nextLine();
    }

    public static void greet() {
        String helloMsg =   "Welcome, seeker. I am " + Pythia.botName + ".\n" +
                            "What brings you here?";
        printAnswer(helloMsg);
    }

    public static void echo(String text) {
        printAnswer(text);
    }

    public static void sayBye() {
        String byeMsg = "Your path is set. Until we meet again.";
        printAnswer(byeMsg);
    }
}
