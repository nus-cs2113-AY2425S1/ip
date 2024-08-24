import java.awt.Graphics;
import java.util.Scanner;

public class Glendon {
    public static void main(String[] args) {
        String logo = " _______   _                     _\n"
                + "| ______| | |                   | |\n"
                + "| |  ___  | |  ___   _____   ___| |  _____   _____\n"
                + "| | |__ | | | / _ \\ |  _  | |  _  | |  _  | |  _  |\n"
                + "| |___| | | | | __/ | | | | | |_| | | |_| | | | | |\n"
                + "|_______| |_| \\___| |_| |_| |_____| |_____| |_| |_|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Glendon.\n" + "What can I do for you?\n");

        Scanner in = new Scanner(System.in);
        String response;
        response = in.nextLine();
        Task[] list = new Task[100];
        int taskNumber;
        int taskCounter = 0;
        Task currentTask;

        while (response != null) {
            if (response.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (response.equals("list")){
                taskNumber = 1;
                for (int i = 0; i < list.length; i++) {
                    currentTask = list[i];
                    if (currentTask != null) {
                        System.out.println(taskNumber + ". " + currentTask.toString());
                        taskNumber++;
                    }
                }
            } else if (response.contains("unmark") && response.indexOf("unmark") == 0) {
                int taskValue = Integer.valueOf(response.split(" ")[1]) - 1;
                list[taskValue].setCompletion(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list[taskValue].toString());
            } else if (response.contains("mark") && response.indexOf("mark") == 0) {
                int taskValue = Integer.valueOf(response.split(" ")[1]) - 1;
                list[taskValue].setCompletion(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[taskValue].toString());
            } else {
                list[taskCounter++] = new Task(response);
                System.out.println("added: " + response);
            }
            response = in.nextLine();
        }
    }
}

class Task {
    String taskName;
    boolean completion;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completion = false;
    }

    public Task(String taskName, boolean completion) {
        this.taskName = taskName;
        this.completion = completion;
    }

    public boolean isCompletion() {
        return completion;
    }

    public void setCompletion(boolean completion) {
        this.completion = completion;
    }

    @Override
    public String toString() {
        String answer;
        String marked = "";
        if (this.isCompletion()) {
            marked = "X";
        }
        answer = "[" + marked + "] " + taskName;
        return answer;
    }
}
