import java.util.Objects;
import java.util.Scanner;


public class XiaoMe {
    static String[] dataStorage= new String[100];
    static int NumOfData = 0;
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String line;
        Scanner in = new Scanner(System.in);

        System.out.println("""
                ____________________________________________________________\s
                Hello! I'm XiaoMe
                What can I do for you?
                ____________________________________________________________
                """);
        while (true) {
            line = in.nextLine();
            if (Objects.equals(line, "bye")) {
                // user input is bye: end programme
                System.out.println("""
                        ____________________________________________________________
                        Bye. Hope to see you again soon!
                        ____________________________________________________________
                        
                        """);
                break;
            } else if (Objects.equals(line, "list")) {
                // user input is list: display past inputs
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < NumOfData; i++) {
                    System.out.println((i + 1) + ". " + dataStorage[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________\n"
                        + "added: " + line + "\n"
                        + "____________________________________________________________\n");
                dataStorage[NumOfData] = line; // add text to storage
                NumOfData += 1;
            }

        }
    }
}
