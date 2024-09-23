package TheThinker.Command;

import TheThinker.File.FileLoader;
import TheThinker.File.NewFile;
import TheThinker.Ui.CommandLine;
import TheThinker.Ui.UiControl;
import java.io.FileNotFoundException;

/**
 * Class which contains the main function.
 * Loads file , polls for user input till bye command is received
 * If default file and user's newly input file is not valid , program ends.
 */
public class TheThinker{

    public static final String NAME = "TheThinker";

    public static void main(String[] args) {

        try {
            NewFile data = FileLoader.loadDefaultFileElseInputNewFile();
            UiControl.printGreeting();
            CommandLine.pollForUserInputTillBye(data);

        } catch (FileNotFoundException e) {
            System.out.println("Please create file before proceeding");
        }
    }

}
