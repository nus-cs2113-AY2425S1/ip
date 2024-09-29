package parser;

import task.Task;

import java.util.Objects;

public class Parser {


    public Task parseCommand(String userInput) {
        // checks what kind of command was received by XiaoMe

        if (Objects.equals(line, "bye")) {
            return XiaoMe.Type.BYE;
        } else if (Objects.equals(line, "list")) {
            return XiaoMe.Type.LIST;
        }

        String[] words = line.trim().split(" ");
        String first = words[0];

        if (Objects.equals(first, "todo")) {
            return XiaoMe.Type.TODO;
        } else if (Objects.equals(first, "deadline")) {
            return XiaoMe.Type.DEADLINE;
        } else if (Objects.equals(first, "event")) {
            return XiaoMe.Type.EVENT;
        } else if (Objects.equals(first, "mark") || Objects.equals(first, "unmark")) {
            return XiaoMe.Type.MARK;
        }

        throw new XiaoMeException();
    }

}
