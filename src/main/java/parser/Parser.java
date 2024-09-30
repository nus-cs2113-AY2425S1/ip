package parser;

import commands.*;
import exceptions.XiaoMeException;

import java.util.Objects;

public class Parser {


    public static Command parseCommand(String userInput) {
        // checks what kind of command was received by XiaoMe

        if (Objects.equals(userInput, "bye")) {
            return new ByeCommand();
        } else if (Objects.equals(userInput, "list")) {
            return new ListCommand();
        }

        String[] words = userInput.trim().split(" ");
        String first = words[0];

        if (Objects.equals(first, "todo")) {
            return new ToDoCommand(userInput);
        } else if (Objects.equals(first, "deadline")) {
            return new DeadlineCommand(userInput);
        } else if (Objects.equals(first, "event")) {
            return new EventCommand(userInput);
        } else if (Objects.equals(first, "mark") || Objects.equals(first, "unmark")) {
            return new MarkCommand(userInput);
        }

        return new InvalidCommand();
    }

}
