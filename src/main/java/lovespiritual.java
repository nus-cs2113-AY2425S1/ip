import java.util.Scanner;

public class lovespiritual {
    public static void main(String[] args) {
        String line = "__________________________________________________";
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        boolean[] status = new boolean[100];
        int count = 0;

        System.out.println(line);
        System.out.println("Hello! I'm lovespiritual");
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = in.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    String checkbox = status[i] ? "[X]" : "[ ]";
                    System.out.println((i + 1) + "." + checkbox + " " + list[i]);
                }
                System.out.println(line);
            } else if (input.startsWith("mark ")) {
                String numberString = input.substring(5).trim();
                int number = Integer.parseInt(numberString) - 1;
                if (number >= 0 && number < count) {
                    status[number] = true;
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(" [X] " + list[number]);
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("Invalid number. Please enter a valid number.");
                    System.out.println(line);
                }
            } else if (input.startsWith("unmark ")) {
                String numberString = input.substring(7).trim();
                int number = Integer.parseInt(numberString) - 1;
                if (number >= 0 && number < count) {
                    status[number] = false;
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(" [ ] " + list[number]);
                    System.out.println(line);
                } else {
                    System.out.println(line);
                    System.out.println("Invalid number. Please enter a valid number.");
                    System.out.println(line);
                }
            } else {
                list[count] = input;
                count++;
                System.out.println(line);
                System.out.println("added: " + input);
                System.out.println(line);
            }
        }
    }
}
