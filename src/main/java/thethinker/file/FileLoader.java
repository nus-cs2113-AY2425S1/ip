package thethinker.file;

import thethinker.parser.UserInputParser;
import thethinker.ui.UiControl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Handles file loading operations when at the start of program.
 */
public class FileLoader {

    public static final String FILE_DIR = "data";
    public static NewFile inputFile = new NewFile("TaskContents.txt");

    /**
     * Loads default filepath and check if it exists.
     * If file does not exist , create a new file based on user input.
     *
     * @throws FileNotFoundException If file creation failed.
     */
    public static void loadDefaultFileElseInputNewFile() throws FileNotFoundException {

        UiControl.printLoadingText();
        createFolderIfNotExist();

        if(!inputFile.isFileExist()) {

            try {
                inputFileNameAndCreateFile();
            } catch (IOException e) {
                System.out.println("File not found. No data loaded. Please create the file under data directory");
                throw new FileNotFoundException();
            }

        }else{
            inputFile.loadFile();
        }
    }

    /**
     * Gets user input for file name and create the file.
     *
     * @throws FileNotFoundException If file name they input does not exist under /data directory.
     */
    private static void inputFileNameAndCreateFile() throws IOException {
        UiControl.printSeparation();
        System.out.println("Input file name you want to save "
                + "data to under the data directory [filename] without .txt");

        UiControl.printSeparation();

        String filename = UserInputParser.getUserInput();
        NewFile newFile = new NewFile(filename + ".txt");
        inputFile = newFile;

        createNewFile(newFile.file);
    }

    private static void createNewFile(File filename) {

        try {

            if (filename.createNewFile()) {
                System.out.println("File created successfully: " + filename.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException exception) {
            System.err.println("Failed to create file: " + exception.getMessage());
        }
    }

    private static void createFolderIfNotExist() {

        File dataDirectory = new File(FILE_DIR);

        if (!dataDirectory.isDirectory()) {
            System.out.println(FILE_DIR + " does not exist. Creating it now.......");
            createFolder(dataDirectory);
        }
    }

    private static void createFolder(File directoryName) {

        boolean isCreated = directoryName.mkdirs();

        if (isCreated) {
            System.out.println(directoryName + " directory created successfully.");
        } else {
            System.out.println(directoryName + " directory already exists or failed to create.");
        }
    }

    /**
     * Gets user input on whether they want to save task to a file.
     */
    public static boolean shouldSaveTask() {
        UiControl.printSeparation();
        System.out.println("Do you want to save your tasks to a file? yes / no");
        UiControl.printSeparation();
        String userInput = UserInputParser.getUserInput();
        return userInput.equalsIgnoreCase("yes");
    }
}
