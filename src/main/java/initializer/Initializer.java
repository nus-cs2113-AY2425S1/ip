package initializer;

import wildpeace.exceptions.EmptyCommandException;
import wildpeace.task.TaskManager;
import wildpeace.echo.Echo;

import java.util.Scanner;

import static java.lang.System.exit;

public class Initializer {
    public static void initialise(Scanner scanner) throws EmptyCommandException {
        System.out.println("What can I do for you? Enter '1' for echo, '2' for storing your plan, '0' to exit.");
        String initialInput = scanner.nextLine();
        switch (initialInput) {
        case "0":
            exit(0);
        case "1":
            Echo.echo(scanner);
            break;
        case "2":
            TaskManager taskManager = new TaskManager(scanner);
            taskManager.run();
            break;
        default:
            System.out.println("Invalid input");
            initialise(scanner);
            break;
        }
    }
}
