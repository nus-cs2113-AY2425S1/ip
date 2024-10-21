package nell.command;

import nell.list.TaskList;
import nell.common.DateFormats;
import nell.common.Messages;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents an executable search command
 */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";

    private final LocalDate date;

    /**
     * Constructs a new SearchCommand object with a given task list and date string
     *
     * @param tasks The specified task list
     * @param date The specified date string
     * @throws DateTimeParseException If the inputted date string cannot be parsed
     */
    public SearchCommand(TaskList tasks, String date) throws DateTimeParseException {
        super(tasks);
        this.date = LocalDate.parse(date, DateFormats.SEARCH_DATE_FORMAT);
    }

    /**
     * Executes command - prints a list of tasks that occur on the specified date
     */
    @Override
    public void execute() {
        String matchingTasksList = tasks.getTasksOnDate(this.date);
        System.out.println(Messages.TASKS_ON_DATE_MESSAGE);
        System.out.print(matchingTasksList);
    }
}
