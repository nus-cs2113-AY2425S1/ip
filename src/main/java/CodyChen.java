import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class CodyChen {

    public static String dottedLines = "__________________________________\n";
    public static String unMarkedText = "Ok, I've marked this task as not done yet:\n[";
    public static String markedText = "Nice! I've marked this task as done:\n[";
    public static String add = "Got it. I've added this task: \n";
    public static String bye = "Thank you for using Cody Chen. Have a nice day.\n";

    public static int typeofTask(String line, Task[] tasks, String type, int count){
        int startIndex = line.indexOf(type) + type.length() + 1;
        int startIndex1;
        int startIndex2;
        String deadline;
        String taskName;
        String events;

        switch(type){
        case "todo":
            taskName = line.substring(startIndex);
            tasks[count] = new Todo(taskName);
            break;

        case "deadline":
            startIndex1 = line.indexOf("/by");
            deadline = line.substring(startIndex1 + 4);
            taskName = line.substring(startIndex, startIndex1 - 1);
            tasks[count] = new Deadline(taskName, deadline);
            break;

        case "event":
            startIndex1 = line.indexOf("/from");
            startIndex2 = line.indexOf("/to");
            taskName = line.substring(startIndex, startIndex1 - 1);
            deadline = line.substring(startIndex1 + type.length() + 1, startIndex2);
            events = line.substring(startIndex2 + 4);
            tasks[count] = new Event(taskName, deadline, events);
            break;
        }

        System.out.print(dottedLines + add +
                tasks[count].getType() +
                tasks[count].getStatusIcon() +
                tasks[count].getDescription());

        switch(type){
        case "deadline":
            System.out.print(tasks[count].getFrom() + ")");
            break;
        case "event":
            System.out.print(tasks[count].getFrom());
            System.out.println(tasks[count].getTo());
            break;
        }

        System.out.println("\nLook! Now you have " +
                (count + 1) + " tasks in the list\n" + dottedLines);
        count += 1;
        return count;
    }

    public static void markFunc(String line, Task[] tasks, char type){
        int startIndex = line.indexOf('k') + 2;
        int toChange = Integer.parseInt(line.substring(startIndex)) - 1; // Finds index of object
        if(type == 'm'){
            tasks[toChange].markDone(); // Accesses index and its method
            System.out.println(markedText + tasks[toChange].getStatusIcon() +
                    "] " + tasks[toChange].getDescription() + "\n");
        } else {
            tasks[toChange].markDel(); // Accesses index and its method
            System.out.println(unMarkedText + tasks[toChange].getStatusIcon() +
                    "] " + tasks[toChange].getDescription() +"\n");
        }

    }

    public static void listItemsFunc(Task[] tasks){
        System.out.print(dottedLines +
                "Done! Your List as follows: \n");
        int loop = 1;
        for(Task task : tasks){
            if(task != null){
                System.out.print(loop + "."); // Prints object Array
                System.out.print(task.getType() + task.getStatusIcon() + task.getDescription());
                switch(task.getType()){
                case "[D]": System.out.println(task.getFrom() + ")");
                    break;
                case "[E]": System.out.println(task.getFrom() + task.getTo());
                    break;
                default: System.out.println();
                }
                loop += 1;
            }

        }
        System.out.print(dottedLines);
    }

    public static void main(String[] args) {
        System.out.println("CodyChen Welcomes You");
        Task[] tasks = new Task[100];
        int count = 0;

        Scanner in = new Scanner(System.in);
        String line = "";

        while(!line.equals("bye")){
            line = in.nextLine();

            if(line.contains("todo")){
                try{
                    count = typeofTask(line, tasks, "todo", count);
                } catch(Exception e){
                    System.out.println("Please specify todo <task>");
                }
            }

            else if(line.contains("deadline")){
                try{
                    count = typeofTask(line, tasks, "deadline", count);
                } catch(Exception e){
                    System.out.println("Please specify deadline <task> /by <End Date>");
                }
            }
            else if(line.contains("event")){
                try{
                    count = typeofTask(line, tasks, "event", count);
                } catch(Exception e){
                    System.out.println("Please specify event <task> /from <Start Date> /to <End Date>");
                }
            }
            else if(line.contains("unmark")){
                try{
                    markFunc(line, tasks, 'u');
                } catch(Exception e){
                    System.out.println("Please specify unmark <Task Number>");
                }
            }

            else if(line.contains("mark")){
                try{
                    markFunc(line, tasks, 'm');
                } catch(Exception e){
                    System.out.println("\"Please specify mark <Task Number>\"");
                }
            }

            else if(line.contains("list")){
                try{
                    listItemsFunc(tasks);
                } catch(Exception e){
                    System.out.println("Oops! I did not get that ><");
                }
            }

            else if(line.contains("bye")){
                System.out.println(bye);

            }

            else {
                System.out.println("Please specify todo / deadline / event to start the bot");
            }
        }
    }
}