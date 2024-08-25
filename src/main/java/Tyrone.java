import java.util.Scanner;

public class Tyrone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Tyrone.\nWhat can I do for you?");
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                TaskList.printList();
            } else {
                TaskList.addTask(input);
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
