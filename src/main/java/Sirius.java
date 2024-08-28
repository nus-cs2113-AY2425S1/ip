import java.util.Scanner;
import java.lang.Integer;
public class Sirius {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------" );
        System.out.print("Hello! I'm Sirius!\n" + "What can I do for you?\n" + "-----------------------\n" );

        class Task{
            private String taskName;
            private boolean isMarked;
            public Task() {
                this.taskName = "";
                this.isMarked = false;
            }
            public Task(String taskName, boolean isMarked) {
                this.taskName = taskName;
                this.isMarked = isMarked;
            }
            public String getTaskName() {
                return taskName;
            }
            public boolean isMarked() {
                return isMarked;
            }
            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }
            public void setMarked(boolean isMarked) {
                this.isMarked = isMarked;
            }
            public void printTask() {
                if (isMarked) {
                    System.out.println("[X] " + taskName);
                }
                else {
                    System.out.println("[ ] " + taskName);
                }
            }
        }
        Task[] list = new Task[100];
        int taskCounter = 0;
        while (true) {
            String userInput = scanner.nextLine();
            if (!userInput.equals("bye")) {
                if (userInput.equals("list")){
                    System.out.println("-----------------------");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCounter; i++) {
                        System.out.print(i + 1 + ". ");
                        list[i].printTask();
                    }
                    System.out.println("-----------------------\n");
                }
                else if (userInput.startsWith("mark ")) {
                    System.out.println("-----------------------");
                    int taskNumber = Integer.parseInt(userInput.replace("mark ", ""));
                    list[taskNumber-1].setMarked(true);
                    System.out.println("Nice! I've marked this task as done:");
                    list[taskNumber-1].printTask();
                    System.out.println("-----------------------\n");
                }
                else if (userInput.startsWith("unmark ")) {
                    System.out.println("-----------------------");
                    int taskNumber = Integer.parseInt(userInput.replace("unmark ", ""));
                    list[taskNumber-1].setMarked(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    list[taskNumber-1].printTask();
                    System.out.println("-----------------------\n");
                }
                else {
                    System.out.println("-----------------------");
                    list[taskCounter++] = new Task(userInput, false);
                    System.out.println("added: " + userInput);
                    System.out.println("-----------------------\n");
                }
            } else {
                System.out.println("Bye! Hope to see you soon.");
                break;
            }
        }
        scanner.close();
    }
}
