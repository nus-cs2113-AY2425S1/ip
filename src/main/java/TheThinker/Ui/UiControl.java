package TheThinker.Ui;

import TheThinker.Command.TheThinker;

public class UiControl {

    public static void printGreeting() {
        UiControl.printSeparation();
        System.out.println("Hello! I'm " + TheThinker.NAME);
        System.out.println("What can I do for you?");
        UiControl.printSeparation();
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printSeparation() {
        System.out.println("_".repeat(60));
    }

    public static void printHelp(){
        System.out.println("Formats for the commands are : ");
        System.out.println("mark : mark [number]");
        System.out.println("unmark : unmark [number]");
        System.out.println("delete : delete [number]");
        System.out.println("todo : todo [task]");
        System.out.println("event : event [task] /from [start time] /by [end time]");
        System.out.println("deadline : deadline [task] /by [time]");
        System.out.println("get : get [dd/mm/yyyy]");
    }

    public static void printCommands(){
        System.out.println("Command entered is not valid. Available commands are");
        String[] commands = {"mark" , "unmark" , "todo" , "delete", "event" , "deadline" , "list" , "bye" , "get" , "help (get format)"};
        for(String command : commands){
            System.out.println("- " + command);
        }
    }

    public static void printLoadingText(){
        System.out.println("Loading file now........");
    }
}
