package TheThinker.File;

import TheThinker.Parser.UserInputParser;
import TheThinker.Ui.UiControl;
import java.io.FileNotFoundException;

/**
 * Handles file loading operations when starting the program
 */
public interface FileLoader {

    /**
     * Loads default file name and path and check if it exists.
     * If file exists , load the contents of the file.
     * If file does not exist , ask user for another file name.
     *
     * @return NewFile object.
     * @throws FileNotFoundException If the filepath is invalid.
     */
    static NewFile loadDefaultFileElseInputNewFile() throws FileNotFoundException {
        UiControl.printLoadingText();
        NewFile data = new NewFile("TaskContents.txt");

        if(!data.isFileExist()) {
            try {
                data = inputFileNameAndLoadFile();
            } catch (FileNotFoundException e) {
                System.out.println("File not found. No data loaded. Please create the file under Data directory");
                throw new FileNotFoundException();
            }
        }else{
            data.loadFile();
        }
        return data;
    }

    /**
     * Gets user input for file name they want to load and load the file.
     *
     * @return NewFile object.
     * @throws FileNotFoundException If file name they input does not exist under /Data directory.
     */
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
