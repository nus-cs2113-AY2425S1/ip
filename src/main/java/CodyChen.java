import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CodyChen {

    public static String dottedLines = "__________________________________\n";
    public static String unMarkedText = "Ok, I've marked this task as not done yet:\n";
    public static String markedText = "Nice! I've marked this task as done:\n";
    public static String add = "Got it. I've added this task: \n";
    public static String delete = "Got it. I've removed this task: \n";
    public static String bye = "Thank you for using Cody Chen. Have a nice day.\n";

    /* ================ printMethod() ================
     Common method for printing items after adding, deleting, marking or unmarking.
      ================ Parameters ================
    - type: Format to print based on type of object (Todo, Event or Deadline)
    - func: Format to print based on function (Adding, deleting, marking or unmarking)
    - count: item ID to extract
     */
    private static void printMethod(ArrayList<Task> tasks, String func, int startIndex) {
        char type = tasks.get(startIndex).getType();

        if(Objects.equals(func, "add")){
            System.out.print(dottedLines + add);
        }
        else if(Objects.equals(func, "delete")){
            System.out.print(dottedLines + delete);
        }
        else if(Objects.equals(func, "mark")){
            System.out.print(dottedLines + markedText);
        }
        else if(Objects.equals(func, "unmark")){
            System.out.print(dottedLines + unMarkedText);
        }

        System.out.print("[" + tasks.get(startIndex).getType() + "]" +
                tasks.get(startIndex).getStatusIcon() +
                tasks.get(startIndex).getDescription());

        switch(type) {
        case 'D':
            System.out.print(tasks.get(startIndex).getFrom() + ")");
            break;
        case 'E':
            System.out.print(tasks.get(startIndex).getFrom());
            System.out.print(tasks.get(startIndex).getTo());
            break;
        }

        if(Objects.equals(func, "add")) {
            System.out.print("\nYou currently have " +
                    (startIndex + 1) + " tasks in the list\n" + dottedLines);
        }
        else if(Objects.equals(func, "delete")) {
            System.out.print("\nYou are left with " +
                    (tasks.size() - 1) + " tasks in the list\n" + dottedLines);
        }
        else {
            System.out.println("\n" + dottedLines);
        }
    }

    /* ================ typeofTask() ================
     Adding or deleting different tasks into ArrayList
      ================ Parameters ================
    - line: Unfiltered input from the user
    - type: Type of task
     */
    public static void typeofTask(String line, ArrayList<Task> tasks, char func){
        String deadline;
        String taskName;
        String events;
        int startIndex;

        switch(func){
        case 'T': //add (todo)
            taskName = line.substring("todo ".length());
            tasks.add(new Todo(taskName));
            break;

        case 'D': //add (deadline)
            taskName = line.substring("deadline ".length(), line.indexOf("/by "));
            deadline = line.substring(line.indexOf("/by ") + "/by ".length());
            tasks.add(new Deadline(taskName, deadline));
            break;

        case 'E': //add (event)
            taskName = line.substring("event ".length(), line.indexOf("/from "));
            deadline = line.substring(line.indexOf("/from ") + "/from ".length(), line.indexOf("/to "));
            events = line.substring(line.indexOf("/to ") + "/to ".length());
            tasks.add(new Event(taskName, deadline, events));
            break;

        case 'X': //delete
            startIndex = Integer.parseInt(line.substring("delete ".length())) - 1;
            printMethod(tasks,"delete", startIndex);
            tasks.remove(startIndex);
            break;

        case 'M': //mark
            startIndex = Integer.parseInt(line.substring("mark ".length())) - 1;
            tasks.get(startIndex).markDone();
            printMethod(tasks,"mark", startIndex);
            break;

        case 'U': //unmark
            startIndex = Integer.parseInt(line.substring("unmark ".length())) - 1;
            tasks.get(startIndex).markDel();
            printMethod(tasks, "unmark", startIndex);
            break;

        }
        if(func != 'X' && func != 'M' && func != 'U'){
            printMethod(tasks, "add", tasks.size() - 1);
        }

    }

    public static void listItemsFunc(ArrayList<Task> tasks){
        if(tasks.size() > 1){
            System.out.println(dottedLines + "Let's review! Your List as follows: ");
        } else if(tasks.isEmpty()){
            System.out.println(dottedLines + "Empty. Time to add some tasks!");
        }
        int loop = 1;
        for(Task task : tasks){
            System.out.print(loop + "."); // Prints object Array
            System.out.print("[" + task.getType() + "]" + task.getStatusIcon() + task.getDescription());
            switch(task.getType()){
            case 'T': System.out.println();
                break;
            case 'D': System.out.println(task.getFrom() + ")");
                break;
            case 'E': System.out.println(task.getFrom() + task.getTo());
                break;
            }
            loop += 1;
        }
        System.out.print(dottedLines);
    }

    public static void main(String[] args) {
        System.out.println("CodyChen Welcomes You");
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        String line = "";

        while(!line.equals("bye")){
            line = in.nextLine();

            if(line.contains("todo")){
                try{
                    typeofTask(line, tasks, 'T');
                } catch(Exception e){
                    System.out.println("Please specify todo <task>");
                }
            }

            else if(line.contains("deadline")){
                try{
                    typeofTask(line, tasks, 'D');
                } catch(Exception e){
                    System.out.println("Please specify deadline <task> /by <End Date>");
                }
            }
            else if(line.contains("event")){
                try{
                    typeofTask(line, tasks, 'E');
                } catch(Exception e){
                    System.out.println("Please specify event <task> /from <Start Date> /to <End Date>");
                }
            }
            else if(line.contains("unmark")){
                try{
                    typeofTask(line, tasks, 'U');
                } catch(Exception e){
                    System.out.println("Please specify unmark <Task Number>");
                }
            }

            else if(line.contains("mark")){
                try{
                    typeofTask(line, tasks, 'M');
                } catch(Exception e){
                    System.out.println("\"Please specify mark <Task Number>\"");
                }
            }

            else if(line.contains("delete")){
                try{
                    typeofTask(line, tasks, 'X');
                } catch(Exception e){
                    System.out.println("The selected item is not found");
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