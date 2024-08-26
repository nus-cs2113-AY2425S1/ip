import java.util.Objects;
import java.util.Scanner;


public class XiaoMe {
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
            if (!Objects.equals(line, "bye")) {
                // user did not end programme
                System.out.println("____________________________________________________________\n"
                        + line
                        + "\n____________________________________________________________\n");
            } else {
                // user ended programme
                System.out.println("""
                        ____________________________________________________________
                        Bye. Hope to see you again soon!
                        ____________________________________________________________
                        
                        """);
                break;
            }
        }

//        System.out.println("____________________________________________________________ \n"
//                        + "Hello! I'm XiaoMe\n"
//                        + "What can I do for you?\n"
//                        + "____________________________________________________________\n"
//                        + "Bye. Hope to see you again soon!\n"
//                        + "____________________________________________________________");

    }
}
