package commands;

import java.time.LocalDate;

import tasks.Deadlines;
import tasks.Event;
import tasks.TaskList;
import ui.Skeleton;
import ui.Ui;

public class SearchCommand extends Command {
    private String flag;
    private LocalDate searchDate;
    public SearchCommand(String flag, LocalDate date) {
        this.flag = flag;
        this.searchDate = date;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        if (flag.equals("/b")) {
            System.out.print(Skeleton.LINE_BREAK);
            for (int i = 0; i < taskList.size(); i++) {
                if ((taskList.get(i) instanceof Deadlines
                        && ((Deadlines) taskList.get(i)).getDeadline().isBefore(searchDate))
                        || (taskList.get(i) instanceof Event
                        && ((Event) taskList.get(i)).getFrom().isBefore(searchDate))) {
                    taskList.get(i).print();
                }
            }
            System.out.print(Skeleton.LINE_BREAK);
        } else if (flag.equals("/a")) {
            System.out.print(Skeleton.LINE_BREAK);
            for (int i = 0; i < taskList.size(); i++) {
                if ((taskList.get(i) instanceof Deadlines
                        && ((Deadlines) taskList.get(i)).getDeadline().isAfter(searchDate))
                        || (taskList.get(i) instanceof Event
                        && ((Event) taskList.get(i)).getFrom().isAfter(searchDate))) {
                    taskList.get(i).print();
                }
            }
            System.out.print(Skeleton.LINE_BREAK);
        }
    }
}
