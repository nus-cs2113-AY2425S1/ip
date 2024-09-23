package TheThinker.Ui;

import TheThinker.Command.TheThinker;

/**
 * Interface controls all the Ui methods
 */
public interface UiControl {

    static void printGreeting() {
        UiControl.printSeparation();
        String asciiArt =
                "  _______ _       _______ _     _       _              \n" +
                        " |__   __| |     |__   __| |   (_)     | |             \n" +
                        "    | |  | |__   ___| |  | |__  _ _ __ | | _____ _ __   \n" +
                        "    | |  | '_ \\ / _ \\ |  | '_ \\| | '_ \\| |/ / _ \\ '__|  \n" +
                        "    | |  | | | |  __/ |  | | | | | | | |   <  __/ |     \n" +
                        "    |_|  |_| |_|\\___|_|  |_| |_|_|_| |_|_|\\_\\___|_|     \n";

        System.out.println(asciiArt);
        System.out.println("Hello! I'm " + TheThinker.NAME);
        System.out.println("What can I do for you?");
        System.out.println();
        UiControl.printSeparation();
    }

    static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void printSeparation() {
        System.out.println("_".repeat(60));
    }

    static void printHelp(){
        System.out.println("Formats for the commands are : ");
        System.out.println("mark : mark [number]");
        System.out.println("unmark : unmark [number]");
        System.out.println("delete : delete [number]");
        System.out.println("todo : todo [task]");
        System.out.println("event : event [task] /from [start time] /by [end time]");
        System.out.println("deadline : deadline [task] /by [time]");
        System.out.println("get : get [dd/mm/yyyy]");
        System.out.println("find : find [keyword]");
    }

    static void printCommands(){
        System.out.println("Command entered is not valid. Available commands are");
        String[] commands = {"mark" , "unmark" , "todo" , "delete", "event" , "deadline" , "list" , "bye" , "get" , "find", "help (get format)"};
        for(String command : commands){
            System.out.println("- " + command);
        }
    }

    static void printLoadingText(){
        System.out.println("Loading file now........");
    }
}
