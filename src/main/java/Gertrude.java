import java.util.Scanner;

public class Gertrude {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCounter = 0;
        System.out.println("Hello, I'm Gertrude.");
        System.out.println("What can I do for you?");

        while (true) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] lineArr = line.split(" ");
            System.out.println("____________________________________________________________");
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (lineArr[0].equals("list")) {
                for (int i = 1; i <= taskCounter; i++) {
                    System.out.print(i + ".");
                    tasks[i-1].printTask();
                }
                System.out.println("Now you have " + taskCounter + " tasks in the list.");
            } else if (lineArr[0].equals("mark") || lineArr[0].equals("unmark")) {
                int index = Integer.parseInt(lineArr[1]);
                if (index < 1 || index > taskCounter) {
                    System.out.println("That is not a valid index.");
                } else if (lineArr[0].equals("mark")) {
                    tasks[index - 1].markDone();
                } else {
                    tasks[index-1].markNotDone();
                }
            } else if (lineArr[0].equals("todo")) {
                Todo newTodo = new Todo(line);
                tasks[taskCounter] = newTodo;
                taskCounter++;
                System.out.println("added: " + line);
            } else if (lineArr[0].equals("deadline")) {
                String description = "";
                String deadline = "";
                boolean isDeadline = false;
                for(int i = 1; i < lineArr.length; i++) {
                    if (lineArr[i] == "/by") {
                        isDeadline = true;
                    } else if (!isDeadline) {
                        description += lineArr[i];
                    } else {
                        deadline += lineArr[i];
                    }
                }
                Todo newTodo = new Todo(description, deadline);
                tasks[taskCounter] = newTodo;
                taskCounter++;
                System.out.println("added: " + line);
            } else if (lineArr[0].equals("event")) {
                String description = "";
                String start = "";
                String end = "";
                String section = "description";
                for(int i = 1; i < lineArr.length; i++) {
                    if (lineArr[i] == "/from") {
                        section = "from";
                    } else if (lineArr[i] == "/to") {
                        section = "to";
                    } else if (section == "description") {
                        description += lineArr[i];
                    } else if (section == "from") {
                        start += lineArr[i];
                    } else if (section == "to") {
                        end += lineArr[i];
                    }
                }
                Todo newTodo = new Todo(description, start, end);
                tasks[taskCounter] = newTodo;
                taskCounter++;
                System.out.println("added: " + line);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
