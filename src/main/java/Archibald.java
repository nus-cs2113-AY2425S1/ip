import java.util.Scanner;

public class Archibald {

    public static void printArchibaldResponse(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        String name = "Archibald";
        printArchibaldResponse("Hello, I am known as " + name + ",\nhow may I be of assistance!");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                printArchibaldResponse("I bid thee farewell! May our paths cross again!");
                break;
            }

            printArchibaldResponse(userInput);
        }

        scanner.close();
    }
}