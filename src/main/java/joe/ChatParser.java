package joe;

import java.util.Optional;
import java.util.Scanner;

public class ChatParser {

    private TaskList toDoItemArrayList;

    public ChatParser(TaskList toDoItemArrayList) {
        this.toDoItemArrayList = toDoItemArrayList;
    }


    public void start() {
        Scanner in = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = in.nextLine();
            switch (input) {
            case "bye":
                break;
            case "list":
                UI.printList(this.toDoItemArrayList);
                break;
            default:
                chatAboutTasks(input);
            }
        }
    }


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
                UI.printReply("Invalid input: Second token is not a number.", "Retry: ");
            } catch (IndexOutOfBoundsException e) {
                UI.printReply("No todo with this number exists.", "Retry: ");
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
                    String deadlineDate = Deadline.extractDeadlineDate(input).orElse("NA");
                    newItem = Optional.of(new Deadline(itemDescription, deadlineDate));
                } else {
                    throw new EmptyTaskException();
                }
            } catch (StringIndexOutOfBoundsException s) {
                UI.printReply("Please use /by to specify the deadline's date", "Retry:");
            }
            break;
        case "event":
            try {
                itemDescription = Event.extractDescription(input);
                if (itemDescription.length() > 0) {
                    String endDate = Event.extractEndDate(input).orElse("NA");
                    String startDate = Event.extractStartDate(input).orElse("NA");
                    newItem = Optional.of(new Event(itemDescription, startDate, endDate));
                } else {
                    throw new EmptyTaskException();
                }

            } catch (IndexOutOfBoundsException i) {
                UI.printReply("Please use /from and /to to specify the event's timeline", "Retry:");
            }
            break;
        default:
            newItem = Optional.empty();
        }

        newItem.ifPresentOrElse(
                task -> this.toDoItemArrayList.addToList(task),
                () -> UI.printMultiLine(new String[]{"Remember available commands for creating tasks are: todo, event or deadline for creating tasks",
                        "General commands: list, bye, unmark and mark. "})
        );
    }
}
