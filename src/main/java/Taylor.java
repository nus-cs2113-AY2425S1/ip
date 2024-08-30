import java.util.*;

public class Taylor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "____________________________________________________________";
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
                    System.out.println(line);
                } else {
                    tasks.get(index).setCompleted(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(index));
                    System.out.println(line);
                }
                input = sc.nextLine();
                continue;
            }

            System.out.println(line);
            System.out.println("added: " + input);
            tasks.add(new Task(input));
            System.out.println(line);
            input = sc.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
