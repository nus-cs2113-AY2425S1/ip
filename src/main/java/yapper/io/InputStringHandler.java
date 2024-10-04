package yapper.io;

import yapper.exceptions.ExceptionHandler;
import yapper.exceptions.YapperException;
import yapper.instructions.Instruction;

/**
 * Input String Parser for Yapper.
 *
 * <p>
 * This class handles the parsing of user input strings into
 * structured instructions for task management. It validates
 * input, checks for required parameters, and splits
 * arguments as needed.
 * </p>
 *
 */
public class InputStringHandler {

    /**
     * Parses a user input string and returns an Instruction object.
     *
     * @param userInputString the input string provided by the user
     * @return an Instruction object representing the parsed command
     * @throws YapperException if the input is invalid or any required parameters are missing
     */
    public static Instruction parseUserInput(String userInputString) throws YapperException {
        try {
            ExceptionHandler.checkIfUserInputEmpty(userInputString, false);
            ExceptionHandler.checkIfStartWithInstructionPrefix(userInputString);

            if (userInputString.startsWith(StringStorage.PREFIX_LIST_INSTRUCTION)) {
                ExceptionHandler.checkIfTooManyArguments(userInputString, StringStorage.PREFIX_LIST_INSTRUCTION);
                return new Instruction(Instruction.InstructionType.LIST);
            } else if (userInputString.startsWith(StringStorage.PREFIX_HELP_INSTRUCTION)) {
                ExceptionHandler.checkIfTooManyArguments(userInputString, StringStorage.PREFIX_HELP_INSTRUCTION);
                return new Instruction(Instruction.InstructionType.HELP);
            }

            String[] instructionParts = userInputString.split(" ", 2);
            String instructionType = instructionParts[0];
            String instructionArgs = instructionParts.length > 1
                    ? instructionParts[1].trim() : "";
            ExceptionHandler.checkIfUserInputEmpty(instructionArgs, true);

            switch (instructionType) {
            case StringStorage.PREFIX_FIND_INSTRUCTION:
                ExceptionHandler.checkIfFindArgsMissing(
                        instructionArgs.trim());
                return new Instruction(Instruction.InstructionType.FIND,
                        instructionArgs.trim());
            case StringStorage.PREFIX_TODO_INSTRUCTION:
                ExceptionHandler.checkIfTodoArgsMissing(
                    instructionArgs.trim());
                return new Instruction(Instruction.InstructionType.TODO,
                        instructionArgs.trim());
            case StringStorage.PREFIX_DEADLINE_INSTRUCTION:
                ExceptionHandler.checkIfDeadlineKeywordsPresent(
                    instructionArgs.indexOf(StringStorage.DELIMITER_DEADLINE_END_DATE));
                String[] deadlineArgs = splitStringByDeadlineKeyword(instructionArgs);
                ExceptionHandler.checkIfDeadlineArgsMissing(
                        deadlineArgs[0], deadlineArgs[1]);
                return new Instruction(Instruction.InstructionType.DEADLINE,
                        deadlineArgs[0], deadlineArgs[1]);
            case StringStorage.PREFIX_EVENT_INSTRUCTION:
                ExceptionHandler.checkIfEventKeywordsPresent(
                    instructionArgs.indexOf(StringStorage.DELIMITER_EVENT_START_DATE),
                    instructionArgs.indexOf(StringStorage.DELIMITER_EVENT_END_DATE));
                String[] eventArgs = splitStringByEventKeywords(instructionArgs);
                ExceptionHandler.checkIfEventArgsMissing(
                        eventArgs[0], eventArgs[1], eventArgs[2]);
                return new Instruction(Instruction.InstructionType.EVENT,
                        eventArgs[0], eventArgs[1], eventArgs[2]);
            case StringStorage.PREFIX_DELETE_INSTRUCTION:
                int taskOrdinalToDelete = Integer.parseInt(instructionArgs.trim());
                return new Instruction(Instruction.InstructionType.DELETE,
                        taskOrdinalToDelete);
            case StringStorage.PREFIX_MARK_INSTRUCTION:
            case StringStorage.PREFIX_UNMARK_INSTRUCTION:
                int taskOrdinal = Integer.parseInt(instructionArgs.trim());
                Instruction.InstructionType type = instructionType.equals(
                        StringStorage.PREFIX_MARK_INSTRUCTION)
                        ? Instruction.InstructionType.MARK
                        : Instruction.InstructionType.UNMARK;
                return new Instruction(type, taskOrdinal);
            default:
                throw new YapperException(
                        StringStorage.UNRECOGNISED_INSTRUCTION_MESSAGE);
            }
        } catch (YapperException e) {
            throw new YapperException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new YapperException(
                    "Invalid task ordinal detected. Try again. ");
        }
    }


    /**
     * Splits the instruction arguments by the deadline keyword delimiter.
     *
     * @param instructionArgs the instruction arguments to split
     * @return an array containing the deadline description and date
     */
    public static String[] splitStringByDeadlineKeyword(String instructionArgs) {
        String[] deadlineArgs = instructionArgs.split(
                StringStorage.DELIMITER_DEADLINE_END_DATE, -2);
        String deadlineDesc = deadlineArgs[0].trim();
        String deadlineDate = deadlineArgs[1].trim();
        return new String[] {deadlineDesc, deadlineDate};
    }
    /**
     * Splits the instruction arguments by the event keywords.
     *
     * @param instructionArgs the instruction arguments to split
     * @return an array containing the event description, start date, and end date
     */
    public static String[] splitStringByEventKeywords(String instructionArgs) {
        String[] eventArgs = instructionArgs.split(
                StringStorage.DELIMITER_EVENT_START_DATE, -2);
        String eventDesc = eventArgs[0].trim();
        String[] dates = eventArgs[1].split(
                StringStorage.DELIMITER_EVENT_END_DATE, -2);
        String startDate = dates[0].trim();
        String endDate = dates[1].trim();
        return new String[] {eventDesc, startDate, endDate};
    }

}
