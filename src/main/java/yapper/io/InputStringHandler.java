package yapper.io;

import yapper.exceptions.ExceptionHandler;
import yapper.exceptions.YapperException;
import yapper.instructions.Instruction;

// TODO finish and clean up input validation code
// Input Text Parser for Yapper
public class InputStringHandler {

    public static Instruction parseUserInput(String userInputString) throws YapperException {
        // User Input Validation
        try {
            ExceptionHandler.checkIfUserInputEmpty(userInputString);
        } catch (YapperException e) {
            System.out.println(e.getMessage()); //
        }
        userInputString = userInputString.trim();
        int splitAtIndex = userInputString.indexOf(' ');
        String instructionType = (splitAtIndex == -1) ?
                userInputString : userInputString.substring(0, splitAtIndex);

        // Handle 1-Argument Instructions: Instruction
        if (splitAtIndex == -1) {
            switch (instructionType) {
            case StringStorage.LIST_INSTRUCTION_PREFIX:
                return new Instruction(Instruction.InstructionType.LIST);
            }
        }

        // User Input Validation
        String instructionArgs = userInputString.substring(splitAtIndex).trim();
        try {
            ExceptionHandler.checkIfUserInputArgsEmpty(instructionArgs);
        } catch (YapperException e) {
            StringStorage.printWithDividers(e.getMessage()); //
        }

        switch (instructionType) {
        case StringStorage.TODO_INSTRUCTION_PREFIX:
            // no keywords here to validate
            validateTodoParameters(
                    instructionArgs.trim() );
            return new Instruction(Instruction.InstructionType.TODO,
                    instructionArgs.trim() );
        case StringStorage.DEADLINE_INSTRUCTION_PREFIX:
            String[] deadlineArgs = validateDeadlineKeywords(instructionArgs);
            validateDeadlineParameters(
                    deadlineArgs[0], deadlineArgs[1] );
            return new Instruction(Instruction.InstructionType.DEADLINE,
                    deadlineArgs[0], deadlineArgs[1] );
        case StringStorage.EVENT_INSTRUCTION_PREFIX:
            String[] eventArgs = validateEventKeywords(instructionArgs);
            validateEventParameters(
                    eventArgs[0], eventArgs[1], eventArgs[2] );
            return new Instruction(Instruction.InstructionType.EVENT,
                    eventArgs[0], eventArgs[1], eventArgs[2] );
        case StringStorage.DELETE_INSTRUCTION_PREFIX:
            try {
                taskOrdinal = Integer.parseInt(instructionArgs);
                return new Instruction(Instruction.InstructionType.DELETE, taskOrdinal);
            } catch (NumberFormatException e) {
                StringStorage.printWithDividers(e.getMessage()); // "invalid task number format"
            }
        case StringStorage.MARK_INSTRUCTION_PREFIX:
        case StringStorage.UNMARK_INSTRUCTION_PREFIX:
            try {
                taskOrdinal = Integer.parseInt(instructionArgs);
                Instruction.InstructionType type = instructionType.equals("mark")
                                                 ? Instruction.InstructionType.MARK
                                                 : Instruction.InstructionType.UNMARK;
                return new Instruction(type, taskOrdinal);
            } catch (NumberFormatException e) {
                StringStorage.printWithDividers(e.getMessage()); // "invalid task number format"
            }
        default:
            // If none of the above code works, user input cannot be recognized
            throw new YapperException(StringStorage.UNRECOGNISED_INSTRUCTION_MESSAGE);
            // TODO is this how I should throw unrecognised exception error?
        }
        // TODO exception for incorrect arguments
    }


    // TODO what to return?
    public static void validateTodoInstruction(String todoDesc) {
        try {
            ExceptionHandler.checkIfTodoArgsMissing(todoDesc);
        } catch (YapperException e) {
            StringStorage.printWithDividers(e.getMessage());
        }
    }
    public static void validateDeadlineInstruction(String deadlineDesc, String deadlineDate) {
        try {
            ExceptionHandler.checkIfDeadlineArgsMissing(deadlineDesc, deadlineDate);
        } catch (YapperException e) {
            StringStorage.printWithDividers(e.getMessage());
        }
    }
    public static void validateEventInstruction(String eventDesc, String startDate, String endDate) {
        try {
            ExceptionHandler.checkIfEventArgsMissing(eventDesc, startDate, endDate);
        } catch (YapperException e) {
            StringStorage.printWithDividers(e.getMessage());
        }
    }
}
