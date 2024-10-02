package freedom.ui;

public class UiStorage extends Ui {
    public void printDataLoaded() {
        System.out.println("\tData loaded!");
    }

    public void printDataFileCreated() {
        System.out.println("\tData file created!");
    }

    public void printUnableToAccess() {
        printHeadDivider();
        System.out.println("\tUnable to open file!");
        printTailDivider();
    }

    public void printUnableToLoad() {
        printHeadDivider();
        System.out.println("\tCannot Load Data! File might be corrupted :(");
        printTailDivider();
    }

    public void printUnableToCreateFile() {
        printHeadDivider();
        System.out.println("\tCannot create data file :(");
        printTailDivider();
    }

    public void printUnableToCreateDirectory() {
        printHeadDivider();
        System.out.println("\tCannot create data directory :(");
        printTailDivider();
    }
}
