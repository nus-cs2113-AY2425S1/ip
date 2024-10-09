package commands;

import java.time.LocalDate;

import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import ui.Skeleton;
import ui.Ui;

/**
 * Represents the command to search for tasks within the TaskList
 */
public class SearchCommand extends Command {
    private String flag;
    private LocalDate searchDate;
    private String searchTerm;

    /**
     * For searches based on the date of the of task
     * @param flag represents the type of search used
     * @param date represents the date used to search for tasks
     */
    public SearchCommand(String flag, LocalDate date) {
        this.flag = flag;
        this.searchDate = date;
    }

    /**
     * For searches based on the description of the task
     * @param flag represents the type of search used
     * @param searchTerm represents the term used to search for tasks
     */
    public SearchCommand(String flag, String searchTerm) {
        this.flag = flag;
        this.searchTerm = searchTerm;
    }

    /**
     * Executes the search task searching by either date or terms.
     * /b is used to search for tasks before a date.
     * /a is used to search for tasks after a date.
     * /d is used to search for tasks only of that date.
     * /c is used to search for any tasks that contain the search term.
     * @param taskList the TaskList object being executed on
     * @param ui the Ui object used for user interactions
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        boolean isBeforeFlag = flag.equals("/b");
        boolean isAfterFlag = flag.equals("/a");
        boolean isDateFlag = flag.equals("/d");
        boolean isContainsFlag = flag.equals("/c");
        System.out.print(Skeleton.LINE_BREAK);
        for (int i = 0; i < taskList.size(); i++) {
            if (isBeforeFlag && ((taskList.get(i) instanceof Deadline
                    && ((Deadline) taskList.get(i)).getDeadline().isBefore(searchDate))
                    || (taskList.get(i) instanceof Event
                    && ((Event) taskList.get(i)).getFrom().isBefore(searchDate)))) {
                System.out.println(i + 1 + ". ");
                taskList.get(i).print();
            } else if (isAfterFlag && ((taskList.get(i) instanceof Deadline
                    && ((Deadline) taskList.get(i)).getDeadline().isAfter(searchDate))
                    || (taskList.get(i) instanceof Event
                    && ((Event) taskList.get(i)).getFrom().isAfter(searchDate)))) {
                System.out.println(i + 1 + ". ");
                taskList.get(i).print();
            } else if (isDateFlag && ((taskList.get(i) instanceof Deadline
                    && ((Deadline) taskList.get(i)).getDeadline().equals(searchDate))
                    || (taskList.get(i) instanceof Event
                    && ((Event) taskList.get(i)).getFrom().equals(searchDate)))) {
                System.out.println(i + 1 + ". ");
                taskList.get(i).print();
            } else if (isContainsFlag && taskList.get(i).getTask().contains(searchTerm)) {
                System.out.print(i + 1 + ". ");
                taskList.get(i).print();
            }
            System.out.print(Skeleton.LINE_BREAK);
        }
    }
}
