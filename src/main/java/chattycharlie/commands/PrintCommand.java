package chattycharlie.commands;
import chattycharlie.CharlieExceptions;
import chattycharlie.TaskList;
import chattycharlie.task.Deadline;
import chattycharlie.task.Event;
import chattycharlie.task.Task;
import chattycharlie.userinteractions.Storage;
import chattycharlie.userinteractions.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PrintCommand implements Command{
    private LocalDate time;

    public PrintCommand(String line) {
        String timeText = line.substring(6).trim();
        this.time = LocalDate.parse(timeText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CharlieExceptions {
        int count = 1;
        ui.displayTimeList();
        for(int i = 0; i < taskList.getSize(); i++ ) {
            Task task = taskList.getTask(i);
            CommandType command = task.getType();

            switch (command) {
            case DEADLINE:
                Deadline deadlineTask = (Deadline) task;
                if(time.equals(deadlineTask.getBy())) {
                    ui.displayTaskInList(deadlineTask, count);
                    count++;
                }
                break;
            case EVENT:
                Event eventTask = (Event) task;
                if(time.equals(eventTask.getStartDate())) {
                    ui.displayTaskInList(eventTask, count);
                    count++;
                }
                break;
            default:
                break;
            }
        }
        if(count == 1) {
            ui.displayError("No task found for date: " + time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
    }
}
