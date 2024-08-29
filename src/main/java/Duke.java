import java.util.Scanner;

    public class Duke {

        //taskList variable for level-2 onwards
        private static Task[] taskList = new Task[100];
        //task count
        private static int taskCount = 0;



        //echo function for level-1
        public static void echo(String message) {
            System.out.println(message);
        }


        //add function for level-2
        public static void add(String task) {
            Task newTask = new Task(task);
            taskList[taskCount] = newTask;
            taskCount++;
            System.out.println("added: " + task);
        }


        //list function for level-2
        public static void list() {
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i+1) + ". " +"[" + taskList[i].getStatusIcon() + "] "
                        + taskList[i].getDescription());
            }
        }


        //mark function for level-3
        public void mark(int index) {
            if(index > taskCount || index < 0) {
                System.out.println("Index out of bounds");
                return;
            }
            taskList[index-1].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + taskList[index-1].getStatusIcon() + "] "
                    + taskList[index-1].getDescription());
        }


        //unmark function for level-3
        public void unmark(int index) {
            if(index > taskCount || index < 0) {
                System.out.println("Index out of bounds");
                return;
            }
            taskList[index-1].markAsNotDone();
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println("[" + taskList[index-1].getStatusIcon() + "] "
                    + taskList[index-1].getDescription());
        }



        //main function to execute the chatbot
        public void execute() {
            System.out.println("Hello I'm Lambo");
            System.out.println("What can I do for you?");
            Scanner inputReader = new Scanner(System.in);//scanner for receiving input


            //Super loop used for receiving inputs continuously and response back to user
            SuperLoop:
            while (true) {
                String input = inputReader.nextLine();
                String[] inputComponent = input.split(" ");

                //switch case based on the first word of input line
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
                            continue;//handle exception when the input is empty
                        }
                        add(input);//default case is add task
                        break;
                }
            }
        }
    }
