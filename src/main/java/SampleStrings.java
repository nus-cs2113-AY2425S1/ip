public interface SampleStrings {
    int HORIZONTAL_LINE_USER_COMMAND_LENGTH = 70;
    int HORIZONTAL_LINE_FENIX_MODIFICATION_LENGTH = 62;
    String HORIZONTAL_LINE_USER_COMMAND = "~".repeat(HORIZONTAL_LINE_USER_COMMAND_LENGTH);
    String HORIZONTAL_LINE_FENIX_MODIFICATION = "\t\t" + "*".repeat(HORIZONTAL_LINE_FENIX_MODIFICATION_LENGTH);
    String GREETING = "Greetings. I am Fenix, your digital assistant.";
    String SERVICE_PROMPT = "How may I be of service to you today?";
    String FAREWELL = "It has been a pleasure assisting you. Farewell.";
    String[] ADD = {"The task has been duly noted and added to your list.", "added: "};
    String[] DELETE = {"The task has been removed from your list as per your request.", "deleted: "};
    String[] MARK = {"Task successfully completed. A job well executed.", "marked: "};
    String[] UNMARK = {"Understood. This task has been marked as not done yet.", "unmarked: "};
}
