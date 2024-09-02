import java.util.Scanner;

import static java.lang.System.exit;

public class Initializer {
    public static void initialise(Scanner scanner, LLMChat llmChat)
    {
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
            DataStorage.storeData(scanner);
            break;
//        case "3":
//            llmChat.chatWithAudience(scanner);
//            initialise(scanner, llmChat);
//            break;
        default:
            System.out.println("Invalid input");
            initialise(scanner, llmChat);
            break;

        }
    }
}
