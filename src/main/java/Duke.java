import java.util.Scanner;
import java.io.*;

    public class Duke {

        private static Task[] taskList = new Task[100];
        private static int taskCount = 0;

        public Duke() {
        }

        public static void echo(String message) {
            System.out.println(message);
        }

        public static void add(String task) {
            Task newTask = new Task(task);
            taskList[taskCount] = newTask;
            taskCount++;
            System.out.println("added: " + task);
        }

        public static void list() {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i+1) + ". " +"[" + taskList[i].getStatusIcon() + "] "
                        + taskList[i].getDescription());
            }
        }

        public void mark(int index) {
            if(index > taskCount+1 || index < 0) {
                System.out.println("Index out of bounds");
                return;
            }
            taskList[index-1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + taskList[index-1].getStatusIcon() + "] "
                    + taskList[index-1].getDescription());
        }

        public void unmark(int index) {
            if(index > taskCount+1 || index < 0) {
                System.out.println("Index out of bounds");
                return;
            }
            taskList[index-1].markAsNotDone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println("[" + taskList[index-1].getStatusIcon() + "] "
                    + taskList[index-1].getDescription());
        }

        public void execute() {
            System.out.println("Hello I'm Lambo");
            System.out.println("What can I do for you?");
            Scanner input_reader = new Scanner(System.in);

            SuperLoop:
            while (true) {
                String input = input_reader.nextLine();
                String[] inputComponent = input.split(" ");
                switch (inputComponent[0]) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break SuperLoop;
                    case "list":
                        list();
                        break;
                    case "mark":
                        mark(Integer.parseInt(inputComponent[1]));
                        break;
                    case "unmark":
                        unmark(Integer.parseInt(inputComponent[1]));
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
