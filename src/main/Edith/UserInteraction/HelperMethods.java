package UserInteraction;

import TaskTypes.Task;
import java.util.ArrayList;

public class HelperMethods {

    public static boolean isValidTaskNumber(ArrayList<Task> tasks, int taskNumber) {
        return (taskNumber >= 1 && taskNumber <= tasks.size());
    }
}
