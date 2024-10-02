package TheThinker.File;

import TheThinker.Parser.UserInputParser;
import TheThinker.Ui.UiControl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Handles file loading operations when starting the program
 */
public interface FileLoader {

    String FILE_DIR = "Data";

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
        createFolderIfNotExist();

        NewFile data = new NewFile("TaskContents.txt");

        if(!data.isFileExist()) {
            try {
                data = inputFileNameAndCreateFile();
            } catch (IOException e) {
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
    private static NewFile inputFileNameAndCreateFile() throws IOException {
        UiControl.printSeparation();
        System.out.println("Input file name you want to extract data from under the Data directory [filename.txt]");
        UiControl.printSeparation();
        String filename = UserInputParser.getUserInput();
        NewFile newFile = new NewFile(filename + ".txt");
        createNewFile(newFile.file);
        return newFile;
    }

    private static void createNewFile(File filename){
        try {
            if (filename.createNewFile()) {
                System.out.println("File created successfully: " + filename.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("Failed to create file: " + e.getMessage());
        }
    }

    private static void createFolderIfNotExist(){

        File dataDirectory = new File(FILE_DIR);

        if (!dataDirectory.isDirectory()) {
            createFolder(dataDirectory);
        }
    }

    private static void createFolder(File directoryName){
        boolean isCreated = directoryName.mkdirs();
        if(isCreated){
            System.out.println(directoryName + " directory created successfully.");
        }else{
            System.out.println(directoryName + " directory already exists or failed to create.");
        }
    }
}
