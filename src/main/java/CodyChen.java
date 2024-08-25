import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class CodyChen {
    public static void main(String[] args) {
        /* **************** String constants ******************* */
        String dottedLines = "\n__________________________________\n";
        String intro = "Hello! I'm CodyChen\n What can I do for you?";
        String unMarkedText = "Ok, I've marked this task as not done yet:\n[";
        String markedText = "Nice! I've marked this task as done:\n[";
        String responseMark = "Thank You for Responding \n __________________________________\nAdded:    ";

        /* **************** Object array ******************* */
        List<Task> tasks  = new ArrayList<>();
        int count = 1;
        int startIndex = 0;
        int toChange = 0;

        /* **************** Scanner class ******************* */
        Scanner in = new Scanner(System.in);
        String line = "";

        /* **************** Hello Message ******************* */
        System.out.println(dottedLines + intro + dottedLines);
        int flag = 0;

        while(!line.equals("bye")){
            line = in.nextLine();

            /* **************** Unmark ******************* */
            // Note: The reason unmark came first, is because "mark" is a substring of "unmark"
            if(line.contains("unmark")){
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
                        System.out.print(responseMark + line + dottedLines);
                        flag = 1; // Sets the flag
                        Task task = new Task(line, count); // Adds an object array (1 of 2)
                        tasks.add(task); // Adds an object array (2 of 2)
                    }
                    else { // Runs second time onward
                        System.out.println("__________________________________\nAdded:     " +
                                line + dottedLines);
                        count += 1;
                        Task task = new Task(line, count); // Adds an object array (1 of 2)
                        tasks.add(task); // Adds an object array (2 of 2)
                    }

                }
                /* **************** List items ******************* */
                else {
                    System.out.print(dottedLines +
                            "Yor List as follows: \n");
                    for(Task task : tasks){
                        System.out.print(task.getId() + "."); // Prints object Array
                        System.out.print("[" + task.getStatusIcon() + "] ");
                        System.out.println(task.getDescription());
                    }
                    System.out.print(dottedLines);
                }

            }
        }

        System.out.print("Bye. Hope to see you again soon!\n" + dottedLines);


    }
}
