import java.util.Scanner;

public class Nateh {
    public static String lineBreak = "____________________________________________________________\n";
    public static void printList(Task[] list, int length) {
        System.out.print(lineBreak);
        for (int i = 0; i < length; i++) {
            System.out.print(i+1 + ". ");
            list[i].print();
        }
        System.out.print(lineBreak);
    }

    public static void main(String[] args) {
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
        Task[] list = new Task[100];
        list[0] = new Task();
        int index = 0;
        System.out.print(lineBreak);
        System.out.println("Hello! I'm Nateh\nWhat can I do for you?");
        System.out.println(skeleton);
        System.out.print(lineBreak);
        while (!input.equals("bye")) {
            input = in.nextLine();
            String[] splitInput = input.split(" ");
            switch (splitInput[0]) {
                case "list":
                    printList(list, Task.getLength());
                    break;
                case "mark":
                    index = Integer.parseInt(splitInput[1]) - 1;
                    list[index].setDone(true);
                    System.out.print(lineBreak);
                    System.out.println("Wow! Great job! :)");
                    list[index].print();
                    System.out.print(lineBreak);
                    break;
                case "unmark":
                    index = Integer.parseInt(splitInput[1]) - 1;
                    list[index].setDone(false);
                    System.out.print(lineBreak);
                    System.out.println("Aw you didn't get to finish. :(");
                    list[index].print();
                    System.out.print(lineBreak);
                    break;
                case "bye":
                    System.out.print(lineBreak);
                    System.out.println("Bye. See you next time!");
                    System.out.print(lineBreak);
                    break;
                case "todo":
                    list[Task.getLength()] = new Todo(input.substring(input.indexOf(" ") + 1));
                    System.out.print(lineBreak);
                    System.out.println("added: " + list[Task.getLength() - 1].getTask());
                    System.out.print(lineBreak);
                    break;
                case "deadline":
                    list[Task.getLength()] = new Deadlines(input.substring(input.indexOf(" ") + 1, input.indexOf("/by") - 1), input.substring(input.indexOf("/by") + 4));
                    System.out.print(lineBreak);
                    System.out.print("added: ");
                    list[Task.getLength() - 1].print();
                    System.out.print(lineBreak);
                    break;
                case "event":
                    list[Task.getLength()] = new Event();
                default:
                    list[Task.getLength()] = new Task(input);
                    System.out.print(lineBreak);
                    System.out.println("added: " + input);
                    System.out.print(lineBreak);
                    break;
            }
        }
    }
}
