import java.util.ArrayList;
import java.util.Scanner;

public class Aegis {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = """
                                                                            \s
                        **                                                  \s
                     *****                                  *               \s
                    *  ***                                 ***              \s
                       ***                                  *               \s
                      *  **                                          ****   \s
                      *  **          ***        ****      ***       * **** *\s
                     *    **        * ***      *  ***  *   ***     **  **** \s
                     *    **       *   ***    *    ****     **    ****      \s
                    *      **     **    ***  **     **      **      ***     \s
                    *********     ********   **     **      **        ***   \s
                   *        **    *******    **     **      **          *** \s
                   *        **    **         **     **      **     ****  ** \s
                  *****      **   ****    *  **     **      **    * **** *  \s
                 *   ****    ** *  *******    ********      *** *    ****   \s
                *     **      **    *****       *** ***      ***            \s
                *                                    ***                    \s
                 **                            ****   ***                   \s
                                             *******  **                    \s
                                            *     ****                      \s
                                                                            \s""";

        System.out.println("--------------------------------------------------");
        System.out.println("Hello, this is\n" + logo);

        System.out.println("--------------------------------------------------");
        System.out.println("Hello! This is Aegis, an Anti-Shadow Suppression Weapon and a chatbot.");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------------");

        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(" I could do much more than checking your list, but, if you wish:");
                for (int i = 0; i < taskList.size(); ++i) {
                    System.out.printf(" %d.%s%n", i + 1, taskList.get(i));
                }
            } else if (input.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    taskList.get(taskIndex).markAsDone();
                    System.out.printf(" I've marked this task as done:%n   %s%n", taskList.get(taskIndex));
                } else {
                    System.out.println(" Invalid task number.");
                }
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    taskList.get(taskIndex).unmarkAsDone();
                    System.out.printf(" I've marked this task as not done yet:%n   %s%n", taskList.get(taskIndex));
                } else {
                    System.out.println(" Invalid task number.");
                }
            } else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println(" added: " + input);
            }

            System.out.println("--------------------------------------------------");
        }

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------------");

        scanner.close();
    }
}