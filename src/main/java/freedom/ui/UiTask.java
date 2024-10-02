package freedom.ui;

public class UiTask extends Ui {
    public void printAlreadyDone() {
        printHeadDivider();
        System.out.println("\tAlready done :) Time for other things");
        printTailDivider();
    }

    public void printAlreadyUndone() {
        printHeadDivider();
        System.out.println("\tYou have not done it yet? >:(");
        printTailDivider();
    }

    public void printEmptyDescriptionError() {
        printHeadDivider();
        System.out.println("\tDescription cannot be empty!!");
        printTailDivider();
    }

    public void printNoDeadline() {
        printHeadDivider();
        System.out.print("""
                \tYou need to set a date/time!
                \tRemember:
                \t  Use /by before the date/time
                \t  Include a date/time
                """);
        printTailDivider();
    }

    public void printNoDuration() {
        printHeadDivider();
        System.out.print("""
                \tYou need to set a duration!
                \tRemember:
                \t  Use /from and /to before the date/time
                \t  Include a date/time span
                """);
        printTailDivider();
    }
}
