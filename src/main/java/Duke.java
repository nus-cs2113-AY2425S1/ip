import java.util.Scanner;

    public class Duke {

        private static String[] taskList = new String[100];
        private static int taskCount = 0;

        public Duke() {
        }

        public static void echo(String message) {
            System.out.println(message);
        }

        public static void add(String task) {
            taskList[taskCount] = task;
            taskCount++;
            System.out.println("added: " + task);
        }

        public static void list() {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i+1) + ". " + taskList[i]);
            }
        }


        public void execute() {
            System.out.println("Hello I'm Lambo");
            System.out.println("What can I do for you?");
            Scanner input_reader = new Scanner(System.in);

            SuperLoop:
            while (true) {
                String input = input_reader.nextLine();
                switch (input) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break SuperLoop;
                    case "list":
                        list();
                        break;
                    default:
                        if(input.isEmpty()) {
                            continue SuperLoop;
                        }
                        add(input);
                        break;
                }
            }
        }
    }
