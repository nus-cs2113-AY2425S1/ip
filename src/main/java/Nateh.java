import java.util.Scanner;

public class Nateh {
    public static void printList(String[] list, int length) {
        String lineBreak = "____________________________________________________________\n";
        System.out.print(lineBreak);
        for (int i = 0; i < length; i++) {
            System.out.print(i+1 + ". ");
            System.out.println(list[i]);
        }
        System.out.print(lineBreak);
    }

    public static void main(String[] args) {
        String lineBreak = "____________________________________________________________\n";
        String skeleton = """
                
                                         {    }
                                          K,   }
                                         /  `Y`
                                    _   /   /
                                   {_'-K.__/
                                     `/-.__L._
                                     /  ' /`\\_}
                                    /  ' /
                            ____   /  ' /
                     ,-'~~~~    ~~/  ' /_
                   ,'             ``~~~%%',
                  (                     %  Y
                 {                      %% I
                {      -                 %  `.
                |       ',                %  )
                |        |   ,..__      __. Y
                |    .,_./  Y ' / ^Y   J   )|
                \\           |' /   |   |   ||
                 \\          L_/    . _ (_,.'(
                  \\,   ,      ^^""' / |      )
                    \\_  \\          /,L]     /
                      '-_`-,       ` `   ./`
                         `-(_            )
                             ^^\\..___,.--`""";
        Scanner in = new Scanner(System.in);
        String input = "";
        String[] list = new String[100];
        int length = 0;
        System.out.print(lineBreak);
        System.out.println("Hello! I'm Nateh\nWhat can I do for you?");
        System.out.println(skeleton);
        System.out.print(lineBreak);
        while (!input.equals("bye")) {
            input = in.nextLine();
            switch (input) {
                case "list":
                    printList(list, length);
                    break;
                case "bye":
                    break;
                default:
                    list[length] = input;
                    length++;
                    System.out.print(lineBreak);
                    System.out.println("added: " + input);
                    System.out.print(lineBreak);
            }
        }
        System.out.print(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(lineBreak);
    }
}
