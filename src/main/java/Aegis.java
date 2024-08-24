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

        while (true) {
            String input = scanner.nextLine();
            System.out.println(" " + input);
            System.out.println("--------------------------------------------------");

            if (input.equals("bye")) {
                break;
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------------");

        scanner.close();
    }
}
