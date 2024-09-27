package joe;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;
import java.time.format.DateTimeParseException;


/**
 * Parses text for commands to initiate further methods on its TaskList object
 * Includes two types of parsing:
 * A) Parsing of user input from the command-line
 * B) Parsing stored Task descriptions of a txt file back into Task and TaskList objects
 */
public class ChatParser {

    private TaskList toDoItemArrayList;

    public ChatParser(TaskList toDoItemArrayList) {
        this.toDoItemArrayList = toDoItemArrayList;
    }

    /**
     * Starts the parser for parsing user command line input and orchestrates high-level
     * method calls that are initiated by user input of one keyword only
     */
    public void start() {
        Scanner in = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = in.nextLine().strip();
            switch (input) {
            case "bye":
                break;
            case "list":
                UI.printList(this.toDoItemArrayList);
                break;
            case "today":
                LocalDateTime today = LocalDate.now().atTime(23, 59);
                UI.printList(this.toDoItemArrayList.getDueTaskList(today));
                break;
            case "tomorrow":
                LocalDateTime tomorrow = LocalDate.now().plusDays(1).atTime(23, 59);
                UI.printList(this.toDoItemArrayList.getDueTaskList(tomorrow));
                break;
            default:
                chatAboutTasks(input);
            }
        }
    }


    /**
     * Parses multi-keyword user input and orchestrates the corresponding method calls accordingly.
     * @param input the user input from the command line
     */
    public void chatAboutTasks(String input) {
        String[] tokens = input.split(" ");
        String commandToken = tokens[0];
        switch (commandToken) {
        case "unmark":
            try {
                int toDoNumber = Integer.parseInt(tokens[1].strip());
                this.toDoItemArrayList.unmarkTask(toDoNumber);
            } catch (NumberFormatException e) {
                UI.printReply("Invalid input. Second token is not a number.", "Retry: ");
            } catch (IndexOutOfBoundsException e) {
                UI.printReply("No todo with this number exists.", "Retry: ");
            }
            break;
        case "mark":
            try {
                int toDoNumber = Integer.parseInt(tokens[1].strip());
                this.toDoItemArrayList.markTask(toDoNumber);
            } catch (NumberFormatException e) {
                UI.printReply("Invalid input. Second token is not a number.", "Retry: ");
            } catch (IndexOutOfBoundsException e) {
                UI.printReply("No todo with this number exists.", "Retry: ");
            }
            break;
        case "delete":
            try {
                int toDoNumber = Integer.parseInt(tokens[1].strip());
                this.toDoItemArrayList.deleteFromList(toDoNumber);
            } catch (NumberFormatException e) {
                UI.printReply("Second token is not a number.", "Retry: ");
            } catch (IndexOutOfBoundsException e) {
                UI.printReply("No todo with this number exists.", "Retry: ");
            }
            break;
        case "find":
            if (tokens.length > 1 && tokens.length < 3) {
                Optional<TaskList> filteredTasks = this.toDoItemArrayList.findTasksByKeyword(tokens[1]);
                filteredTasks.ifPresentOrElse(
                    taskList -> UI.printList(taskList),
                    () -> UI.printReply("No task exists with this keyword", "")
                );
            } else {
                UI.printReply("Sorry you can (and need) to search for one keyword at a time", "Retry: ");
            }
            break;
        default:
            try {
                this.createNewTask(commandToken, input);
            } catch (EmptyTaskException e) {
                UI.printReply("A task of type " + commandToken + " cannot be empty.", "Retry: ");

            }
        }
    }

    /**
     * Creates new Task objects based on the detected commandToken.
     * @param commandToken a token from the parsed user input that indicates what type of Task is to be created
     *                     i.e. Todo, Event, Deadline
     * @param input the full user input string
     * @throws EmptyTaskException raised if the user input contains no task description
     */
    public void createNewTask(String commandToken, String input) throws EmptyTaskException {
        String itemDescription;
        Optional<Task> newItem = Optional.empty();
        switch (commandToken) {
        case "todo":
            itemDescription = Todo.extractDescription(input);
            if (itemDescription.length() > 0) {
                newItem = Optional.of(new Todo(itemDescription));
            } else {
                throw new EmptyTaskException();
            }
            break;
        case "deadline":
            try {
                itemDescription = Deadline.extractDescription(input);
                if(itemDescription.length() > 0) {
                    LocalDateTime deadlineDate = Deadline.extractDeadlineDate(input).orElse(LocalDateTime.now());
                    newItem = Optional.of(new Deadline(itemDescription, deadlineDate));
                } else {
                    throw new EmptyTaskException();
                }
            } catch (StringIndexOutOfBoundsException s) {
                UI.printReply("Please use /by to specify the deadline's date", "Retry: ");
            } catch (DateTimeParseException d) {
                UI.printReply("Use a valid date time specifier.", "Retry: ");
            }
            break;
        case "event":
            try {
                itemDescription = Event.extractDescription(input);
                if (itemDescription.length() > 0) {
                    LocalDateTime endDate = Event.extractEndDate(input).orElse(LocalDateTime.now());
                    LocalDateTime startDate = Event.extractStartDate(input).orElse(LocalDateTime.now());
                    newItem = Optional.of(new Event(itemDescription, startDate, endDate));
                } else {
                    throw new EmptyTaskException();
                }
            } catch (IndexOutOfBoundsException i) {
                UI.printReply("use /from and /to to specify the event's timeline", "Retry:");
            } catch (DateTimeParseException d) {
                UI.printReply("use valid date time specifier", "Retry: ");
            }
            break;
        default:
            newItem = Optional.empty();
        }

        newItem.ifPresentOrElse(
                task -> this.toDoItemArrayList.addToList(task),
                () -> UI.printMultiLine(
                        new String[]{"Invalid command: Follow this guide ",
                            "https://kennethsty.github.io/ip/"})

        );
    }
}
