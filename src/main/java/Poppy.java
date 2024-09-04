import java.util.Scanner;

public class Poppy {

    public static String input;
    private static final int INITIAL_INDEX = 0;
    private static final int TASK_LIST_SIZE = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Poppy");
        System.out.println("What can I do for you?");
        Task[] taskList = new Task[TASK_LIST_SIZE];
        input = sc.nextLine();
        taskList[INITIAL_INDEX]= new Task(input);
        int counter = 0;
        while (!input.equals("Bye")) {
            String[] commandArgs = input.split(" ", 2 );
            String command = commandArgs[0];
            switch (command) {
            case "mark":
                markAsDone(taskList, commandArgs);
                break;
            case "unmark":
                markAsNotDone(taskList, commandArgs);
                break;
            case "List":
                showList(taskList);
                break;
            case "todo":
                ToDo task= new ToDo(commandArgs[1]);
                taskList[counter] = task;
                counter++;
                System.out.println(task.toString());
                System.out.println("You now have " + counter + " tasks");
                break;
            case "deadline":
                String[] deadlinestring = commandArgs[1].split( "/by", 2);
                Deadline deadline = new Deadline(deadlinestring[0], deadlinestring[1]);
                taskList[counter] = deadline;
                counter++;
                System.out.println(deadline.toString());
                System.out.println("You now have " + counter + " tasks");
                break;
            case "event":
                String[] eventstring = commandArgs[1].split( "/from", 2);
                Events event = new Events(eventstring[0], eventstring[1]);
                taskList[counter] = event;
                counter++;
                System.out.println(event.toString());
                System.out.println("You now have " + counter + " tasks");
                break;
            default:
                taskList[counter] = new Task(input);
                counter++;
                echo(taskList[counter - 1]);
            }
            input =sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
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
                System.out.println((i+1) + ". " + list[i].toString());
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

        @Override
        public String toString() {
            return "[" +  this.taskType() + "]" + "[" + this.getStatusIcon() + "]" + this.description;
        }

        //...
    }
    public static class ToDo extends Task{
        public ToDo (String description){
            super(description);
        }

        @Override
        public String taskType() {
            return "[T] ";
        }

        @Override
        public String toString(){
            return this.taskType()  + "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    public static class Deadline extends Task{
        protected String by;

        public Deadline (String description, String by){
            super(description);
            this.by = by;
        }

        @Override
        public String taskType() {
            return "[D] ";
        }

        @Override
        public String toString(){
            return this.taskType()+ "[" + super.getStatusIcon()+ "] " + description + "by:" +by +" ";
        }
    }

    public static class Events extends Task{
        protected String from;
        public Events (String description, String from){
            super(description);
            this.from =from;
        }
        @Override
        public String taskType(){
            return "[E] ";
        }

        @Override
        public String toString(){
            return (this.taskType()+ "["+ super.getStatusIcon()+ "] " + description + "from:" +from +" ");
        }
    }
}

