package yapper.io;

import yapper.exceptions.ExceptionHandler;
import yapper.exceptions.YapperException;
import yapper.instructions.Instruction;

// TODO finish and clean up input validation code
// Input Text Parser for Yapper
public class InputStringHandler {

    public static Instruction parseUserInput(String userInputString) throws YapperException {
        // Check if User Input Empty
        try {
            userInputString = userInputString.trim();
            ExceptionHandler.checkIfUserInputEmpty(userInputString);
            // Handle 1-Argument Instructions: Instruction
            if ( userInputString.startsWith(StringStorage.LIST_INSTRUCTION_PREFIX)) ) {
                if (userInputString.trim() != StringStorage.LIST_INSTRUCTION_PREFIX) {
                    throw new YapperException("list does not need other parameters");
                }
                return new Instruction(Instruction.InstructionType.LIST);
            }
            int splitAtIndex = userInputString.indexOf(' ');
            String instructionType = (splitAtIndex == -1) ?
                    userInputString : userInputString.substring(0, splitAtIndex);
            // how do i get rid of the 2 lines above while still checking if args missing?
            String instructionArgs = userInputString.substring(splitAtIndex).trim();
            ExceptionHandler.checkIfUserInputEmpty(instructionArgs);

            switch (instructionType) {
            case StringStorage.TODO_INSTRUCTION_PREFIX:
                // no keywords here to validate
                ExceptionHandler.checkIfTodoArgsMissing(
                    instructionArgs.trim() );
                // no need to split arg
                return new Instruction(Instruction.InstructionType.TODO,
                        instructionArgs.trim() );
            case StringStorage.DEADLINE_INSTRUCTION_PREFIX:
                ExceptionHandler.checkIfDeadlineKeywordsPresent(
                    instructionArgs.indexOf(StringStorage.DEADLINE_END_DATE_DELIMITER));
                String[] deadlineArgs = splitStringByDeadlineKeyword(instructionArgs);
                ExceptionHandler.checkIfDeadlineArgsMissing(
                    deadlineArgs[0].trim(), deadlineArgs[1].trim() );
                return new Instruction(Instruction.InstructionType.DEADLINE,
                        deadlineArgs[0], deadlineArgs[1] );
            case StringStorage.EVENT_INSTRUCTION_PREFIX:
                ExceptionHandler.checkIfEventKeywordsPresent(
                    instructionArgs.indexOf(StringStorage.EVENT_START_DATE_DELIMITER),
                    instructionArgs.indexOf(StringStorage.EVENT_END_DATE_DELIMITER));
                String[] eventArgs = splitStringByEventKeywords(instructionArgs);
                ExceptionHandler.checkIfEventArgsMissing(
                    eventArgs[0].trim(), eventArgs[1].trim(), eventArgs[2].trim() );
                return new Instruction(Instruction.InstructionType.EVENT,
                        eventArgs[0], eventArgs[1], eventArgs[2] );
            case StringStorage.DELETE_INSTRUCTION_PREFIX:
                int taskOrdinalToDelete = Integer.parseInt( instructionArgs.trim() );
                return new Instruction(Instruction.InstructionType.DELETE,
                        taskOrdinalToDelete);
            case StringStorage.MARK_INSTRUCTION_PREFIX:
            case StringStorage.UNMARK_INSTRUCTION_PREFIX:
                int taskOrdinal = Integer.parseInt( instructionArgs.trim() );
                Instruction.InstructionType type = instructionType.equals(StringStorage.MARK_INSTRUCTION_PREFIX)
                                                 ? Instruction.InstructionType.MARK
                                                 : Instruction.InstructionType.UNMARK;
                return new Instruction(type, taskOrdinal);
            default:
                // If none of the above code works, user input cannot be recognized
                throw new YapperException(StringStorage.UNRECOGNISED_INSTRUCTION_MESSAGE);
                return null;
            }
        } catch (YapperException e) {
            System.out.println("failed to recognize instruction because: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.out.println("failed to recognize instruction because: " + e.getMessage());
            return null;
        }
    }

    // Methods to Split By Keywords
    public static String[] splitStringByDeadlineKeyword(String instructionArgs) {
        String[] deadlineArgs = instructionArgs.split(
                StringStorage.DEADLINE_END_DATE_DELIMITER);
        String deadlineDesc = deadlineArgs[0].trim();
        String deadlineDate = deadlineArgs[1].trim();
        return new String[] {deadlineDesc, deadlineDate};
    }
    public static String[] splitStringByEventKeywords(String instructionArgs) {
        String[] eventArgs = instructionArgs.split(StringStorage.EVENT_START_DATE_DELIMITER);
        String eventDesc = eventArgs[0].trim();
        String[] dates = eventArgs[1].split(StringStorage.EVENT_END_DATE_DELIMITER);
        String startDate = dates[0].trim();
        String endDate = dates[1].trim();
        return new String[] {eventDesc, startDate, endDate};
    }

}
