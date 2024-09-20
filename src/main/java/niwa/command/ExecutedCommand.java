package niwa.command;

import niwa.Niwa;

public class ExecutedCommand {
    public static void saveTasks() {
        new SaveCommand().execute(Niwa.getOutputFilePath());
    }
}
