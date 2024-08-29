import java.util.Scanner;

public class Cy {
    public static void printLine() {
        System.out.println("______________________________________");
    }

    public static void printList(String[] items) {
        printLine();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println(i+1 + ". " + items[i]);
            } else {
                break;
            }
        }
        printLine();
    }

    public static void main(String[] args) {
        System.out.println("Hello, I'm Cy");
        System.out.println("What can I do for you?");
        String[] items = new String[100];
        int count = 0;

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")) {

            if (input.equalsIgnoreCase("list")) {
                printList(items);
            } else {
                items[count] = input;
                count++;
                printLine();
                System.out.println("added: " + input);
                printLine();
            }
            input = scan.nextLine();
        }

        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
