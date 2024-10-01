public class TulipTask {
    public static void main(String[] args) throws TulipTaskException.InvalidTaskDescriptionException, TulipTaskException.InvalidEndDateException, TulipTaskException.InvalidStartDateException, TulipTaskException.InvalidDeadlineException, TulipTaskException.InvalidTaskIndexException {
        Ui ui = new Ui();
        ui.run();
    }
}

