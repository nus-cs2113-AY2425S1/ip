import java.util.Scanner;

public class Poppy {
    public static String input;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Poppy");
        System.out.println("What can I do for you?");
        Task[] list = new Task[100];
        input = sc.nextLine();
        list[0]= new Task(input);
        int counter = 0;
        while (!input.equals("Bye")) {
            String[] str = input.split(" " );
            if (str[0].equals("mark")){
                int index = Integer.parseInt(str[1]) - 1;
                if (list[index]== null){
                    System.out.println("Sorry, list number does not exist");
                }else {
                    list[index].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + list[index].getStatusIcon() + "] " + list[index].toString());
                }
            } else if (str[0].equals("unmark")){
                int index = Integer.parseInt(str[1])- 1;
                if (list[index]== null){
                    System.out.println("Sorry, list number does not exist");
                }else {
                    list[index].markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + list[index].getStatusIcon() + "] " + list[index].toString());
                }
            } else if (input.equals("List")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < list.length; i++) {
                    if (list[i] != null) {
                        System.out.println((i + 1) + ". " + '[' + list[i].getStatusIcon() + ']' + list[i].description);
                    }
                }
            } else {
                list[counter] = new Task(input);
                counter++;
                echo(list[counter - 1]);
            }
            input =sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public static void echo(Task task) {
        System.out.println("added: " + task.description);
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }
        //...
    }
}

