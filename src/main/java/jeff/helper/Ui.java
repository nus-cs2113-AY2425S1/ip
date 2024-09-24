package jeff.helper;

import java.util.Scanner;

public class Ui {
    //Constants
    public final String DIVIDER = "____________________________________________________________";
    public final String INTRO_TEXT = """
                ____________________________________________________________
                Hello! I'm JEFF!!!
                
                Here are the type of tasks I can track!!!
                'todo [description]'
                'deadline [description] /by [some date]'
                'event [description] /from [some date] /to [some date]'
                
                List of commands I support!!!!
                Type 'list' to display everything you've said!
                Type 'mark'/'unmark' to change the status of inputted tasks!
                Type 'delete' to delete a task in the list!
                Type 'bye' to exit!
                
                Follow the above formats closely!
                If you give me nonsense, I will call you out!
                Buuuuttt I will give you some indication of your error!
                ____________________________________________________________
                """;

    public final String EXIT_TEXT = """
                Bye. Hope to see you again soon!
                """;

    public void showWelcome(){
        System.out.print(INTRO_TEXT);
    }

    public void showExit(){
        System.out.print(EXIT_TEXT);
    }

    public void showDivider(){
        System.out.print(DIVIDER);
    }

    public void showNewLine(){
        System.out.print(System.lineSeparator());
    }

    public void showLoadingError(String errorMessage){
        System.out.print(this.DIVIDER + System.lineSeparator() + "An error occurred while loading the data file"
                + System.lineSeparator() + errorMessage);
    }

    public String readCommand(){
        Scanner in = new Scanner(System.in);
        System.out.print("You say:" + System.lineSeparator());
        String line = in.nextLine();
        //Divider that comes after user input
        System.out.print(this.DIVIDER + System.lineSeparator());
        return line;
    }
}
