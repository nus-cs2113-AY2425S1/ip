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

        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            System.out.println();
            System.out.println("---------------BEGIN--OF--PROMPT------------------");

            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(" I could do much more than checking your list, but, if you wish:");
                for (int i = 0; i < taskList.size(); ++i) {
                    System.out.printf("   %d.%s%n", i + 1, taskList.get(i));
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
            } else if (input.startsWith("deadline ")) {
                if (!input.contains(" /by ")) {
                    System.out.println(" Invalid syntax: you need to include [/by] parameter");
                    continue;
                }
                String[] parts = input.split(" /by ", 2);
                String description = parts[0].replaceFirst("deadline ", "").trim();
                String byTime = parts[1].trim();

                Deadline newDeadline = new Deadline(description, byTime);
                taskList.add(newDeadline);
                System.out.printf(" New deadline added: %n   %s%n", newDeadline);
                System.out.printf(" %d tasks needed to be done. Let me assist you!%n", taskList.size());
            } else if (input.startsWith("event ")) {
                if (!input.matches(".*/from.*/to.*")) {
                    System.out.println(" Invalid syntax: you need to include [/from, /to] parameters");
                    continue;
                }
                String[] parts = input.split(" /from | /to ");
                String description = parts[0].replaceFirst("event ", "").trim();
                String fromTime = parts[1].trim();
                String toTime = parts[2].trim();

                Event newEvent = new Event(description, fromTime, toTime);
                taskList.add(newEvent);
                System.out.printf(" New event added: %n   %s%n", newEvent);
                System.out.printf(" %d tasks needed to be done. Let me assist you!%n", taskList.size());
            } else if (input.startsWith("todo ")) {
                String description = input.replaceFirst("todo ", "").trim();

                Todo newTodo = new Todo(description);
                taskList.add(newTodo);
                System.out.printf(" New todo added: %n   %s%n", newTodo);
                System.out.printf(" %d tasks needed to be done. Let me assist you!%n", taskList.size());
            } else {
                System.out.println(" Please specify the task type! [todo, deadline, event]");
            }
        }

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println();
        System.out.println("-------------END--OF--CONVERSATION----------------");

        scanner.close();
    }
}

