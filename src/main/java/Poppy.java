import java.util.Scanner;

public class Poppy {
    public static String input;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Poppy");
        System.out.println("What can I do for you?");
        Task[] list = new Task[100];
        input = sc.nextLine();
        list[0]= new Task(input);
        int counter = 0;
        while (!input.equals("Bye")) {
            String[] str = input.split(" ", 2 );
            String command = str[0];
            switch (command) {
            case "mark":
                markAsDone(list, str);
                break;
            case "unmark":
                markAsNotDone(list, str);
                break;
            case "List":
                showList(list);
                break;
            case "todo":
                ToDo task= new ToDo(str[1]);
                list[counter] = task;
                counter++;
                break;
            case "deadline":
                String[] deadlinestring = str[1].split( "/by", 2);
                Deadline deadline = new Deadline(deadlinestring[0], deadlinestring[1]);
                list[counter] = deadline;
                counter++;
                break;
            case "event":
                String[] eventstring = str[1].split( "/from", 2);
                Events event = new Events(eventstring[0], eventstring[1]);
                list[counter] = event;
                counter++;
                break;
            default:
                list[counter] = new Task(input);
                counter++;
                echo(list[counter - 1]);
            }
            input =sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public static void echo(Task task) {
        System.out.println("added: " + task.description);
    }

    public static void markAsDone(Task[] list, String[] str) {
        int index = Integer.parseInt(str[1]) - 1;
        if (list[index]== null){
            System.out.println("Sorry, list number does not exist");
        }else {
            list[index].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list[index].taskType() + " [" + list[index].getStatusIcon() + "] " + list[index].description);
        }
    }

    public static void showList(Task[] list){
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.println((i + 1) + ". " +list[i].taskType() +" [" + list[i].getStatusIcon() + "] " + list[i].description);
            }
        }
    }

    public static void markAsNotDone(Task[] list, String[] str) {
        int index = Integer.parseInt(str[1])- 1;
        if (list[index]== null){
            System.out.println("Sorry, list number does not exist");
        }else {
            list[index].markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(list[index].taskType()+ " [" + list[index].getStatusIcon() + "] " + list[index].description);
        }
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }

        public String taskType(){
            return "[]";
        }
        //...
    }
    public static class ToDo extends Task{
        public ToDo (String description){
            super(description);
            System.out.println("[T]" + " [" + super.getStatusIcon()+ "] " + description);
        }

        @Override
        public String taskType() {
            return "[T] ";
        }
    }

    public static class Deadline extends Task{
        protected String by;

        public Deadline (String description, String by){
            super(description);
            this.by = by;
            System.out.println("[D]" + " [" + super.getStatusIcon()+ "] " + description + "by:" +by +" ");
        }

        @Override
        public String taskType() {
            return "[D] ";
        }
    }

    public static class Events extends Task{
        protected String from;
        public Events (String description, String from){
            super(description);
            this.from =from;
            System.out.println("[E]" + " [" + super.getStatusIcon()+ "] " + description + "from:" +from +" ");
        }
        @Override
        public String taskType(){
            return "[E]";
        }
    }
}

