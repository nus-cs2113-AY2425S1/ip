import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Cubone {
    public static void main(String[] args) {
        String logo =   
            "   ______      __                       \n"+
            "  / ____/_  __/ /_  ____  ____  ___     \n"+
            " / /   / / / / __ \\/ __ \\/ __ \\/ _ \\\n"+
            "/ /___/ /_/ / /_/ / /_/ / / / /  __/    \n"+
            "\\____/\\__,_/_.___/\\____/_/ /_/\\___/ \n";
        String chat_prefix = "\n(Cubone) ";
        String chat_bar = "---------------------------------";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Cubone\nWhat can I do for you?\n" + chat_bar);

        // list to store user input
        ArrayList<Task> inputed_tasks = new ArrayList<Task>();

        // loop for user input
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                // exit loop
                System.out.println(chat_bar + chat_prefix + "Bye. Hope to see you again soon!\n" + chat_bar);
                break;
            } else if (input.equals("list")) {
                // print all user inputs
                System.out.println(chat_bar + chat_prefix + "Here are the tasks in your list:");
                for (int i = 0; i < inputed_tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + inputed_tasks.get(i).toString());
                }
                System.out.println(chat_bar);
            } else if(input.contains("mark") && !input.contains("unmark")){
                // mark task as done
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]);
                inputed_tasks.get(index-1).markAsDone();
                System.out.println(chat_bar + chat_prefix + "Nice! I've marked this task as done:\n" + inputed_tasks.get(index-1).toString() + "\n" + chat_bar);
            } else if(input.contains("unmark")){
                // mark task as undone
                String[] split = input.split(" ");
                int index = Integer.parseInt(split[1]);
                inputed_tasks.get(index-1).markAsUndone();
                System.out.println(chat_bar + chat_prefix + "OK, I've marked this task as not done yet:\n" + inputed_tasks.get(index-1).toString() + "\n" + chat_bar);
            } else if (input.contains("todo")){
                // add todo task
                String[] split = input.split(" ");
                if(split.length == 1){
                    System.out.println(chat_bar + chat_prefix + "☹ OOPS!!! The description of a todo cannot be empty.\n" + chat_bar);
                }else{
                    inputed_tasks.add(new Todo(input));
                    System.out.println(chat_bar + chat_prefix + "Got it. I've added this Todo:\n" + inputed_tasks.get(inputed_tasks.size()-1).toString() + "\n" +
                                        "now you have " + inputed_tasks.size() + " tasks in the list\n" + chat_bar); 
                }
            } else if (input.contains("deadline")){
                // add deadline task
                // usage: deadline <description> /by <date>
                Pattern deadlinePattern = Pattern.compile("deadline (.+?) /by (.+)");
                Matcher deadlineMatcher = deadlinePattern.matcher(input);
                if (deadlineMatcher.find()) {
                    inputed_tasks.add(new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2)));
                    System.out.println(chat_bar + chat_prefix + "Got it. I've added this task:\n" + inputed_tasks.get(inputed_tasks.size()-1).toString() + "\n" + 
                                        "now you have " + inputed_tasks.size() + " tasks in the list\n" + chat_bar);
                } else {
                    System.out.println(chat_bar + chat_prefix + "☹ OOPS!!! Someting missing, usage: deadline <description> /by <date>\n" + chat_bar);
                }
            } else if (input.contains("event")){
                // add event task
                // usage: event <description> /from <date> /to <date>
                Pattern eventPattern = Pattern.compile("event (.+?) /from (.+?) /to (.+)");
                Matcher eventMatcher = eventPattern.matcher(input);
                if (eventMatcher.find()) {
                    inputed_tasks.add(new Event(eventMatcher.group(1), eventMatcher.group(2), eventMatcher.group(3)));
                    System.out.println(chat_bar + chat_prefix + "Got it. I've added this task:\n" + inputed_tasks.get(inputed_tasks.size()-1).toString() + "\n" + 
                                        "now you have " + inputed_tasks.size() + " tasks in the list\n" + chat_bar);
                } else {
                    System.out.println(chat_bar + chat_prefix + "☹ OOPS!!! Someting missing, usage: event <description> /from <date> /to <date>\n" + chat_bar);
                }
            }else {
                // add user input into list
                inputed_tasks.add(new Task(input));
                System.out.println(chat_bar + chat_prefix + "added: "+ input + "\n" + chat_bar);
            }
        }
    }
}
