package medea.core;
import java.util.Scanner;
import java.io.PrintStream;

public class Ui {
    private final int LINE_LENGTH = 50;
    private Scanner in;
    private PrintStream out;

    public Ui(){
        in = new Scanner(System.in);
        out = new PrintStream(System.out);
    }

    public String readCommand(){
        return in.nextLine();
    }

    public void showLine() {
        out.println("=".repeat(LINE_LENGTH));
    }

    public void showError(Exception e){
        out.println("Error: " + e.getMessage());
    }

    public void showMsg(String msg){
        out.println(msg);
    }

    public void showWelcome() {
        out.println("Hello! I'm...");
        out.println("""
                 __       __  ________  _______   ________   ______ \s
                /  \\     /  |/        |/       \\ /        | /      \\\s
                $$  \\   /$$ |$$$$$$$$/ $$$$$$$  |$$$$$$$$/ /$$$$$$  |
                $$$  \\ /$$$ |$$ |__    $$ |  $$ |$$ |__    $$ |__$$ |
                $$$$  /$$$$ |$$    |   $$ |  $$ |$$    |   $$    $$ |
                $$ $$ $$/$$ |$$$$$/    $$ |  $$ |$$$$$/    $$$$$$$$ |
                $$ |$$$/ $$ |$$ |_____ $$ |__$$ |$$ |_____ $$ |  $$ |
                $$ | $/  $$ |$$       |$$    $$/ $$       |$$ |  $$ |
                $$/      $$/ $$$$$$$$/ $$$$$$$/  $$$$$$$$/ $$/   $$/\s
                """);
        out.println("What can I do for you?");
    }

    public void showFarewell() {
        out.println("Bye. Hope to see you again soon!");
    }
}