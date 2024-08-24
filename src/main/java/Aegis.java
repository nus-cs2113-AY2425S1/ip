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

        ArrayList<String> promptHistory = new ArrayList<String>();
        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < promptHistory.size(); ++i) {
                    System.out.printf(" %d: %s%n", i + 1, promptHistory.get(i));
                }
            } else {
                promptHistory.add(input);
                System.out.println(" added: " + input);
            }

            System.out.println("--------------------------------------------------");
        }

        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------------");

        scanner.close();
    }
}
