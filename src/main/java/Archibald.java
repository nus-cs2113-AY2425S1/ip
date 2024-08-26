import java.util.Scanner;

public class Archibald {

    private static String[] tasks = new String[100];
    private static int taskCount = 0;

    public static void printArchibaldResponse(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void addTask(String task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            printArchibaldResponse("added: " + task);
        } else {
            printArchibaldResponse("Error: Task list is full!");
        }
    }

    public static void printList() {
        printArchibaldResponse("Tasks:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        String name = "Archibald";
        printArchibaldResponse("Hello, I am known as " + name + ",\nhow may I be of assistance!");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            
            if (input.equals("bye")) {
                printArchibaldResponse("I bid thee farewell! May our paths cross again!");
                break;
            } else if (input.equals("list")) {
                printList();
            } else {
                addTask(input);
            }
        }

        scanner.close();
    }
}