import java.util.*;

public class Taylor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Taylor");
        System.out.println("What can I do for you?");
        System.out.println(line);

        String input = sc.nextLine();
        List<Task> tasks = new ArrayList<>();
        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + "." + tasks.get(i));
                }
                System.out.println(line);
                input = sc.nextLine();
                continue;
            }

            if(input.startsWith("mark")){
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1])-1;
                if(index<0 || index>=tasks.size()) {
                    System.out.println(line);
                    System.out.println("Invalid index");
                    System.out.println(line);
                } else {
                    tasks.get(index).setCompleted(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index));
                    System.out.println(line);
                }
                input = sc.nextLine();
                continue;
            }

            if(input.startsWith("unmark")){
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1])-1;
                if(index<0 || index>=tasks.size()) {
                    System.out.println(line);
                    System.out.println("Invalid index");
                } else {
                    tasks.get(index).setCompleted(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index));
                    System.out.println(line);
                }
                input = sc.nextLine();
                continue;
            }

            if(input.startsWith("todo")){
                Todo todo = new Todo(input.substring(5));
                tasks.add(todo);
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have "+ tasks.size() +" tasks in the lists");
                System.out.println(line);
                input = sc.nextLine();
                continue;
            }

            if(input.startsWith("event")){
                int from = input.indexOf("/from");
                int to = input.indexOf("/to");
                String description = input.substring(6,from);
                String _from = input.substring(from+6,to);
                String _to = input.substring(to+4);
                Event event = new Event(description,_from,_to);
                tasks.add(event);
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have "+ tasks.size() +" tasks in the lists");
                System.out.println(line);
                input = sc.nextLine();
                continue;
            }

            if(input.startsWith("deadline")){
                int by = input.indexOf("/by");
                String description = input.substring(9,by);
                String _by = input.substring(by+4);
                Task task = new Deadline(description,_by);
                tasks.add(task);
                System.out.println(line);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task);
                System.out.println("Now you have "+ tasks.size() +" tasks in the lists");
                System.out.println(line);
                input = sc.nextLine();
                continue;
            }

            System.out.println(line);
            System.out.println("added: " + input);
            System.out.println(line);
            input = sc.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
