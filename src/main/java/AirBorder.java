import java.util.Scanner;


    public class AirBorder {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Task[] tasks = new Task[100];  //
            int taskCount = 0;

            System.out.println("____________________________________________________________");
            System.out.println(" Hello! I'm AirBorder");
            System.out.println(" What can I do for you?");
            System.out.println("____________________________________________________________");

            while (true) {
                String userInput = scanner.nextLine().trim().toLowerCase();

                if (userInput.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    if (taskCount == 0) {
                        System.out.println(" No tasks added.");
                    } else {
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println(" " + (i + 1) + ". " + tasks[i]);
                        }
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("mark ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < taskCount) {
                        tasks[taskNumber].markAsDone();  // Call on Task object
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks[taskNumber]);
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println(" Invalid task number.");
                        System.out.println("____________________________________________________________");
                    }
                } else if (userInput.startsWith("unmark ")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < taskCount) {
                        tasks[taskNumber].markAsNotDone();  // Call on Task object
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[taskNumber]);
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println(" Invalid task number.");
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    if (taskCount < 100) {  // Check to prevent exceeding the array limit
                        tasks[taskCount] = new Task(userInput);
                        taskCount++;
                        System.out.println("____________________________________________________________");
                        System.out.println(" added: " + userInput);
                        System.out.println("____________________________________________________________");
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println(" Task list is full. Cannot add more tasks.");
                        System.out.println("____________________________________________________________");
                    }
                }
            }

            scanner.close();
        }
    }
