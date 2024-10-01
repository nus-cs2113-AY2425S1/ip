package initializer;
import wildpeace.exceptions.EmptyCommandException;
import wildpeace.task.Echo;

import java.util.Scanner;

import static java.lang.System.exit;
import static wildpeace.task.DataStorage.storeData;

public class Initializer {
    public static void initialise(Scanner scanner) throws EmptyCommandException {
        System.out.println("What can I do for you, enter '1' for echo, '2' for storing your plan, '0' to exit");
        Scanner initialInputScanner = new Scanner(System.in);
        String initialInput = initialInputScanner.nextLine();
        switch (initialInput) {
        case "0":
            exit(0);
        case "1":
            Echo.echo(scanner);
            break;
        case "2":
            storeData(scanner);
            break;
            default:
            System.out.println("Invalid input");
            initialise(scanner);
            break;

        }
    }
}
