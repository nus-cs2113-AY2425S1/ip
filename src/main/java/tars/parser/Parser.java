package tars.parser;

import tars.command.*;
import tars.tarsexception.tarsException;

public class Parser {

    // 解析用户输入并返回命令类型
    public Command parse(String userInput) throws tarsException {
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0];
        String arguments = splitInput.length > 1 ? splitInput[1] : "";

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return parseMarkCommand(arguments);
            case "unmark":
                return parseUnmarkCommand(arguments);
            case "delete":
                return parseDeleteCommand(arguments);
            case "todo":
                return new AddTodoCommand(arguments);
            case "deadline":
                return parseDeadlineCommand(arguments);
            case "event":
                return parseEventCommand(arguments);
            default:
                throw new tarsException("Unknown command: " + command);
        }
    }

    // 解析标记任务为完成的命令
    private Command parseMarkCommand(String arguments) throws tarsException {
        if (arguments.isEmpty()) {
            throw new tarsException("The mark command requires a task number.");
        }
        int taskIndex = Integer.parseInt(arguments);
        return new MarkCommand(taskIndex);
    }

    // 解析取消标记任务为未完成的命令
    private Command parseUnmarkCommand(String arguments) throws tarsException {
        if (arguments.isEmpty()) {
            throw new tarsException("The unmark command requires a task number.");
        }
        int taskIndex = Integer.parseInt(arguments);
        return new UnmarkCommand(taskIndex);
    }

    // 解析删除任务的命令
    private Command parseDeleteCommand(String arguments) throws tarsException {
        if (arguments.isEmpty()) {
            throw new tarsException("The delete command requires a task number.");
        }
        int taskIndex = Integer.parseInt(arguments);
        return new DeleteCommand(taskIndex);
    }

    // 解析 deadline 任务的命令
    private Command parseDeadlineCommand(String arguments) throws tarsException {
        if (!arguments.contains("/by")) {
            throw new tarsException("The deadline command requires a /by date.");
        }
        String[] taskParts = arguments.split("/by");
        String description = taskParts[0].trim();
        String by = taskParts[1].trim();
        return new AddDeadlineCommand(description, by);
    }

    // 解析 event 任务的命令
    private Command parseEventCommand(String arguments) throws tarsException {
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            throw new tarsException("The event command requires both /from and /to times.");
        }
        String[] taskParts = arguments.split("/from|/to");
        String description = taskParts[0].trim();
        String from = taskParts[1].trim();
        String to = taskParts[2].trim();
        return new AddEventCommand(description, from, to);
    }
}
