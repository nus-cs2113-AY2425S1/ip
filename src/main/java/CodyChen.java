import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class CodyChen {
    public static void main(String[] args) {
        /* **************** String constants ******************* */
        String dottedLines = "__________________________________\n";
        String intro = "Hello! I'm CodyChen\nWhat can I do for you?\n";
        String unMarkedText = "Ok, I've marked this task as not done yet:\n[";
        String markedText = "Nice! I've marked this task as done:\n[";
        String responseMark = "Thank You for Responding \n __________________________________\nAdded:    ";
        String add = "Got it. I've added this task: \n";
        String edit = "Got it. I've edited this task: \n";

        /* **************** Object array ******************* */
        List<Task> tasks  = new ArrayList<>();
        int count = 1; // Anything to do with COUNT MUST MINUS ONE
        int startIndex = 0;
        int startIndex1 = 0; // for Deadlines and Events
        int startIndex2 = 0; // for Events
        int toChange = 0;
        String taskName;
        String deadline;
        String events;

        /* **************** Scanner class ******************* */
        Scanner in = new Scanner(System.in);
        String line = "";

        /* **************** Hello Message ******************* */
        System.out.println(dottedLines + intro + dottedLines);
        int flag = 0;

        while(!line.equals("bye")){
            line = in.nextLine();

            if(line.contains("todo")){
                startIndex = line.indexOf("todo") + 5;
                taskName = line.substring(startIndex);

                    Task task = new Task(taskName, count); // Adds an object array (1 of 2)
                    tasks.add(task);
                    tasks.get(count - 1).setType('T');
                    System.out.print(dottedLines + add + "[" +
                            tasks.get(count - 1).getType() + "][" +
                                    tasks.get(count - 1).getStatusIcon() + "] " +
                            tasks.get(count - 1).getDescription() + "\nNow you have " +
                            count + " tasks in the list\n" + dottedLines);
                    count += 1;


            }

            else if(line.contains("deadline")){
                startIndex = line.indexOf("deadline") + 9;
                startIndex1 = line.indexOf("/by");
                taskName = line.substring(startIndex, startIndex1 - 1);
                deadline = line.substring(startIndex1 + 4);

                    Task task = new Task(taskName, count); // Adds an object array (1 of 2)
                    tasks.add(task);
                    tasks.get(count - 1).setType('D');
                    tasks.get(count - 1).setFrom(deadline);

                    System.out.print(dottedLines + add + "[" +
                            tasks.get(count - 1).getType() + "][" +
                            tasks.get(count - 1).getStatusIcon() + "] " +
                            tasks.get(count - 1).getDescription() + " (by: " +
                            tasks.get(count - 1).getFrom() +
                            ")\nNow you have " +
                            count + " tasks in the list\n" + dottedLines);

                    count += 1;

            }

            else if(line.contains("event")){
                startIndex = line.indexOf("event") + 6;
                startIndex1 = line.indexOf("/from");
                startIndex2 = line.indexOf("/to");
                taskName = line.substring(startIndex, startIndex1 - 1);
                deadline = line.substring(startIndex1 + 6, startIndex2);
                events = line.substring(startIndex2 + 4);

                    Task task = new Task(taskName, count); // Adds an object array (1 of 2)
                    tasks.add(task);
                    tasks.get(count - 1).setType('E');
                    tasks.get(count - 1).setFrom(deadline);
                    tasks.get(count - 1).setTo(events);

                    System.out.print(dottedLines + add + "[" +
                            tasks.get(count - 1).getType() + "][" +
                            tasks.get(count - 1).getStatusIcon() + "] " +
                            tasks.get(count - 1).getDescription() + " (by: " +
                            tasks.get(count - 1).getFrom() + " to: " +
                            tasks.get(count - 1).getTo() +
                            ")\nNow you have " +
                            count + " tasks in the list\n" + dottedLines);

                    count += 1;

            }


            /* **************** Unmark ******************* */
            // Note: The reason unmark came first, is because "mark" is a substring of "unmark"
            else if(line.contains("unmark")){
                startIndex = line.indexOf('k') + 2;
                toChange = Integer.parseInt(line.substring(startIndex)) - 1; // Finds index of object
                tasks.get(toChange).markDel(); // Accesses index and its method
                System.out.println(unMarkedText + tasks.get(toChange).getStatusIcon() +
                        "] " + tasks.get(toChange).getDescription() +"\n");
            }
            /* **************** Mark ******************* */
            // Note: The reason unmark came first, is because "mark" is a substring of "unmark"
            else if (line.contains("mark")){
                startIndex = line.indexOf('k') + 2;
                toChange = Integer.parseInt(line.substring(startIndex)) - 1; // Finds index of object
                tasks.get(toChange).markDone(); // Accesses index and its method
                System.out.println(markedText + tasks.get(toChange).getStatusIcon() +
                        "] " + tasks.get(toChange).getDescription() + "\n");
            }
            else {
                /* **************** Add items ******************* */
                if(!line.equals("list")){
                    if(flag == 0){ // Will only run ONCE
                        System.out.print(responseMark + line + "\n" + dottedLines);
                        flag = 1; // Sets the flag
                        Task task = new Task(line, count); // Adds an object array (1 of 2)
                        tasks.add(task); // Adds an object array (2 of 2)
                        tasks.get(count - 1).setType('T');
                        count += 1;
                    }
                    else { // Runs second time onward
                        System.out.println("__________________________________\nAdded:     " +
                                line + "\n" + dottedLines);
                        Task task = new Task(line, count); // Adds an object array (1 of 2)
                        tasks.add(task); // Adds an object array (2 of 2)
                        tasks.get(count - 1).setType('T');
                        count += 1;
                    }

                }
                /* **************** List items ******************* */
                else {
                    System.out.print(dottedLines +
                            "Yor List as follows: \n");
                    for(Task task : tasks){
                        System.out.print(task.getId() + "."); // Prints object Array
                        System.out.print("[" + task.getType() + "]");

                        System.out.print("[" + task.getStatusIcon() + "] ");
                        System.out.print(task.getDescription());

                        switch(task.getType()){
                        case 'D': System.out.println("(by: " + task.getFrom() + ")");
                        break;
                        case 'E': System.out.println("(from: " + task.getFrom() + " to: "
                        + task.getTo() + ")");
                        break;
                        default: System.out.println();
                        }
                    }
                    System.out.print(dottedLines);
                }

            }
        }

        System.out.print("Bye. Hope to see you again soon!\n" + dottedLines);


    }
}