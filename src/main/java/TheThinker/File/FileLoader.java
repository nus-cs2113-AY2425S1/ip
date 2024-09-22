package TheThinker.File;

import TheThinker.Parser.UserInputParser;
import TheThinker.Ui.UiControl;
import java.io.FileNotFoundException;

public class FileLoader {

    public static NewFile loadDefaultFileElseInputNewFile() throws FileNotFoundException {
        UiControl.printLoadingText();
        NewFile data = new NewFile("TaskContents.txt");

        if(!data.isFileExist()) {
            try {
                data = inputFileNameAndLoadFile();
            } catch (FileNotFoundException e) {
                System.out.println("File not found. No data loaded. Please create the file under Data directory");
            }
        }else{
            data.loadFile();
        }
        return data;
    }

    private static NewFile inputFileNameAndLoadFile() throws FileNotFoundException{
        UiControl.printSeparation();
        System.out.println("Input file name you want to extract data from under the Data directory [filename.txt]");
        UiControl.printSeparation();
        String filename = UserInputParser.getUserInput();
        NewFile data = new NewFile(filename);
        data.loadFile();
        return data;
    }
}
