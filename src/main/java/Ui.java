public class Ui {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printIntroduction() {
        System.out.println("Hello, I'm Gertrude.");
        System.out.println("What can I do for you?");
    }

    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showLoadingError() {
        System.out.println("Save file not found. Creating new file.");
    }

    public static void showCorruptionError() {
        System.out.println("Corrupted file.");
    }
}
