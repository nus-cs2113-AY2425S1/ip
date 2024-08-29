import java.util.Scanner;

public class Nateh {
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
        System.out.print(lineBreak);
        System.out.println("Hello! I'm Nateh\nWhat can I do for you?");
        System.out.println(skeleton);
        System.out.print(lineBreak);
        while (!input.equals("bye")) {
            input = in.nextLine();
            System.out.print(lineBreak);
            System.out.println(input);
            System.out.print(lineBreak);
        }
        System.out.print(lineBreak);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print(lineBreak);
    }
}
