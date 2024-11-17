package freedom.ui;

/**
 * Subclass of <code>Ui</code> for chatbot responses related to <code>Storage</code>.
 */
public class UiStorage extends Ui {

    /**
     * Indicates the storage text file is created.
     */
    public void printDataFileCreated() {
        System.out.println("\tData file created!");
    }

    /**
     * Indicates the chatbot is unable to access the text file for reading or writing purposes.
     */
    public void printUnableToAccess() {
        printHeadDivider();
        System.out.println("\tUnable to open file!");
        printTailDivider();
    }

    /**
     * Indicates the chatbot cannot load the data from the text file.
     */
    public void printUnableToLoad() {
        printHeadDivider();
        System.out.println("\tCannot Load Data! File might be corrupted :(");
        printTailDivider();
    }

    /**
     * Indicates the text file cannot be created.
     */
    public void printUnableToCreateFile() {
        printHeadDivider();
        System.out.println("\tCannot create data file :(");
        printTailDivider();
    }

    /**
     * Indicates the directory for the text file cannot be created.
     */
    public void printUnableToCreateDirectory() {
        printHeadDivider();
        System.out.println("\tCannot create data directory :(");
        printTailDivider();
    }
}
