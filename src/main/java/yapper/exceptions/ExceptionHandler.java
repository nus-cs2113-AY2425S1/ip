package yapper.exceptions;

import yapper.io.StringStorage;

/**
 * The {@code ExceptionHandler} class provides methods for validating user input and
 * handling exceptions related to task management in the Yapper chatbot application.
 *
 * <p>
 * It throws {@code YapperException} with appropriate messages if any checks below fail:
 * <li> empty or unknown instructions <li/>
 * missing arguments and keywords <li/>
 * invalid task types and statuses <li/>
 * <p/>
 *
 */
public class ExceptionHandler {

    // User Input Validation Errors

    /**
     * Checks if the string is empty when reading from user.
     *
     * @param userInputString the input string to validate
     * @param isAboutArgs flag indicating if the check is for arguments
     * @throws YapperException if the input string is empty
     */
    public static void checkIfUserInputEmpty(String userInputString, boolean isAboutArgs) throws YapperException {
        // Boolean is to differentiate between exceptions
        if (userInputString.isEmpty() && !isAboutArgs) {
            throw new YapperException(StringStorage.EMPTY_INPUT_MESSAGE);
        } else if (userInputString.isEmpty()) {
            throw new YapperException(StringStorage.MISSING_ARGUMENTS_MESSAGE);
        }
    }
    /**
     * Validates that the string starts with a recognized instruction prefix when reading from user.
     *
     * @param userInputString the input string to validate
     * @throws YapperException if the input string does not start with a valid prefix
     */
    public static void checkIfStartWithInstructionPrefix(String userInputString) throws YapperException {
        if (!userInputString.startsWith(StringStorage.HELP_INSTRUCTION_PREFIX)
                && !userInputString.startsWith(StringStorage.PREFIX_FIND_INSTRUCTION)
                && !userInputString.startsWith(StringStorage.PREFIX_LIST_INSTRUCTION)
                && !userInputString.startsWith(StringStorage.PREFIX_TODO_INSTRUCTION)
                && !userInputString.startsWith(StringStorage.PREFIX_DEADLINE_INSTRUCTION)
                && !userInputString.startsWith(StringStorage.PREFIX_EVENT_INSTRUCTION)
                && !userInputString.startsWith(StringStorage.PREFIX_DELETE_INSTRUCTION)
                && !userInputString.startsWith(StringStorage.PREFIX_MARK_INSTRUCTION)
                && !userInputString.startsWith(StringStorage.PREFIX_UNMARK_INSTRUCTION)) {
            throw new YapperException(StringStorage.MISSING_PREFIX_MESSAGE);
        }
    }

    /**
     * Checks if the string argument for the Find instruction is missing.
     *
     * @param query the keyword to be matched in task descriptions
     * @throws YapperException if the query is empty
     */
    public static void checkIfFindArgsMissing(String query) throws YapperException {
        if (query.isEmpty()) {
            throw new YapperException(
                    StringStorage.MISSING_QUERY_STRING_MESSAGE);
        }
    }
    /**
     * Checks if the argument for the Todo task is missing.
     *
     * @param desc the description of the Todo task
     * @throws YapperException if the description is empty
     */
    public static void checkIfTodoArgsMissing(String desc) throws YapperException {
        if (desc.isEmpty()) {
            throw new YapperException(
                    StringStorage.PREFIX_TODO_INSTRUCTION + " "
                    + StringStorage.MISSING_DESCRIPTION_MESSAGE);
        }
    }
    /**
     * Checks if the arguments for the Deadline task are missing.
     *
     * @param desc the description of the Deadline task
     * @param endDate the end date for the Deadline task
     * @throws YapperException if any of the parameters are empty
     */
    public static void checkIfDeadlineArgsMissing(String desc, String endDate) throws YapperException {
        if (desc.isEmpty()) {
            throw new YapperException(
                    StringStorage.PREFIX_DEADLINE_INSTRUCTION + " "
                    + StringStorage.MISSING_DESCRIPTION_MESSAGE);
        } else if (endDate.isEmpty()) {
            throw new YapperException(
                    StringStorage.PREFIX_DEADLINE_INSTRUCTION + " "
                    + StringStorage.MISSING_END_DATE_MESSAGE);
        }
    }
    /**
     * Checks if the arguments for the Event task are missing.
     *
     * @param desc the description of the Event task
     * @param startDate the start date for the Event task
     * @param endDate the end date for the Event task
     * @throws YapperException if any of the parameters are empty
     */
    public static void checkIfEventArgsMissing(String desc, String startDate, String endDate) throws YapperException {
        if (desc.isEmpty()) {
            throw new YapperException(
                    StringStorage.PREFIX_EVENT_INSTRUCTION + " "
                            + StringStorage.MISSING_DESCRIPTION_MESSAGE);
        } else if (startDate.isEmpty()) {
            throw new YapperException(
                    StringStorage.PREFIX_EVENT_INSTRUCTION + " "
                            + StringStorage.MISSING_START_DATE_MESSAGE);
        } else if (endDate.isEmpty()) {
            throw new YapperException(
                    StringStorage.PREFIX_EVENT_INSTRUCTION + " "
                            + StringStorage.MISSING_END_DATE_MESSAGE);
        }
    }


    // User Input Parsing Exception

    /**
     * Checks if the Deadline keywords are present in the parsed input when reading from user.
     *
     * @param byOrdinal the ordinal position of the keyword, if -1 then not in input
     * @throws YapperException if the keyword is not found
     */
    public static void checkIfDeadlineKeywordsPresent(int byOrdinal) throws YapperException {
        if (byOrdinal == -1) {
            throw new YapperException(
                    StringStorage.DELIMITER_DEADLINE_END_DATE
                    + " keyword not found for "
                    + StringStorage.PREFIX_DEADLINE_INSTRUCTION);
        }
    }
    /**
     * Checks if the Event keywords are present in the parsed input when reading from user.
     *
     * @param fromOrdinal the ordinal position of the start date keyword, if -1 then not in input
     * @param toOrdinal the ordinal position of the end date keyword, if -1 then not in input
     * @throws YapperException if any keyword is not found
     */
    public static void checkIfEventKeywordsPresent(int fromOrdinal, int toOrdinal) throws YapperException {
        if (fromOrdinal == -1) {
            throw new YapperException(
                    StringStorage.DELIMITER_EVENT_START_DATE
                    + " keyword not found for "
                            + StringStorage.PREFIX_EVENT_INSTRUCTION);
        } else if (toOrdinal == -1) {
            throw new YapperException(
                    StringStorage.DELIMITER_EVENT_END_DATE
                    + " keyword not found for "
                            + StringStorage.PREFIX_EVENT_INSTRUCTION);
        }
    }


    // File Input Parsing Exceptions

    /**
     * Validates if the specified task type is valid when reading from file.
     *
     * @param taskType the type of the task
     * @throws YapperException if the task type is invalid
     */
    public static void checkIfTaskTypeValid(String taskType) throws YapperException {
        if (!taskType.equals(StringStorage.SYMBOL_TODO)
                && !taskType.equals(StringStorage.SYMBOL_DEADLINE)
                && !taskType.equals(StringStorage.SYMBOL_EVENT)) {
            throw new YapperException(StringStorage.INVALID_TASK_TYPE_MESSAGE);
        }
    }
    /**
     * Validates if the specified task status is valid when reading from file.
     *
     * @param taskStatus the status of the task
     * @throws YapperException if the task status is invalid
     */
    public static void checkIfTaskStatusValid(String taskStatus) throws YapperException {
        if (!taskStatus.equals(StringStorage.SYMBOL_IS_DONE)
                && !taskStatus.equals(StringStorage.SYMBOL_NOT_DONE)) {
            throw new YapperException(StringStorage.INVALID_TASK_STATUS_MESSAGE);
        }
    }


    // Exceptions for: DELETE, MARK, UNMARK

    /**
     * Checks if there are tasks in the list before performing DELETE, MARK, or UNMARK operations.
     *
     * @param currTaskTotal the total number of tasks currently available
     * @throws YapperException if the task list is empty
     */
    public static void checkIfTaskOrdinalIsOutOfRange(int currTaskTotal) throws YapperException {
        if (currTaskTotal == 0) {
            throw new YapperException(StringStorage.LIST_EMPTY_MESSAGE);
        }
    }
    /**
     * Validates if the specified task ordinal is within the valid range of tasks.
     *
     * @param currTaskTotal the total number of tasks currently available
     * @param taskOrdinal the ordinal of the task to validate
     * @throws YapperException if the task ordinal is out of range
     */
    public static void checkIfTaskOrdinalIsOutOfRange(int currTaskTotal, int taskOrdinal) throws YapperException {
        if (taskOrdinal < 0 || taskOrdinal >= currTaskTotal) {
            throw new YapperException(StringStorage.LIST_OOB_MESSAGE);
        }
    }
    /**
     * Checks if the done status needs to be changed based on the old and new status.
     *
     * @param oldStatus the current status of the task
     * @param newStatus the new status to be applied
     * @throws YapperException if trying to change to the same status
     */
    public static void checkIfDoneStatusNeedsChanging(boolean oldStatus, boolean newStatus) throws YapperException {
        if (oldStatus && newStatus) {
            throw new YapperException(StringStorage.TASK_ALREADY_DONE_MESSAGE);
        } else if (!oldStatus && !newStatus) {
            throw new YapperException(StringStorage.TASK_STILL_NOT_DONE_MESSAGE);
        }
    }

}
