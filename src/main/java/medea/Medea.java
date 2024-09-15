package medea;
import medea.command.CommandHandler;
import java.util.Scanner;

public class Medea {

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.loadTasks();

        String input = scanner.nextLine();

        while(!input.equals("bye")){
            commandHandler.handleInput(input);
            input = scanner.nextLine();
        }

        commandHandler.saveTasks();
        sayGoodbye();
    }

    private static void greet(){
        System.out.println("Hello! I'm...");
        System.out.println("""
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
        System.out.println("What can I do for you?");
    }

    private static void sayGoodbye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

}