package yapper.exceptions;

import yapper.io.StringStorage;

public class ExceptionHandler {

    // User Input Validation Errors
    public static void checkIfUserInputEmpty(String userInputString, boolean isAboutArgs) throws YapperException {
        // Boolean is to differentiate between exceptions
        if (userInputString.isEmpty() && !isAboutArgs) {
            throw new YapperException(StringStorage.EMPTY_INPUT_MESSAGE);
        } else if ( userInputString.isEmpty() ) {
            throw new YapperException(StringStorage.MISSING_ARGUMENTS_MESSAGE);
        }
    }
    public static void checkIfStartWithInstructionPrefix(String userInputString) throws YapperException {
        if ( !userInputString.startsWith(StringStorage.LIST_INSTRUCTION_PREFIX) &&
             !userInputString.startsWith(StringStorage.TODO_INSTRUCTION_PREFIX) &&
             !userInputString.startsWith(StringStorage.DEADLINE_INSTRUCTION_PREFIX) &&
             !userInputString.startsWith(StringStorage.EVENT_INSTRUCTION_PREFIX) &&
             !userInputString.startsWith(StringStorage.DELETE_INSTRUCTION_PREFIX) &&
             !userInputString.startsWith(StringStorage.MARK_INSTRUCTION_PREFIX) &&
             !userInputString.startsWith(StringStorage.UNMARK_INSTRUCTION_PREFIX) ) {
            throw new YapperException(StringStorage.MISSING_PREFIX_MESSAGE);
        }
    }
    public static void checkIfTodoArgsMissing(String desc) throws YapperException {
        if (desc.isEmpty()) {
            throw new YapperException(
                    StringStorage.TODO_INSTRUCTION_PREFIX + " " +
                    StringStorage.MISSING_DESCRIPTION_MESSAGE);
        }
    }
    public static void checkIfDeadlineArgsMissing(String desc, String endDate) throws YapperException {
        if (desc.isEmpty()) {
            throw new YapperException(
                    StringStorage.DEADLINE_INSTRUCTION_PREFIX + " " +
                    StringStorage.MISSING_DESCRIPTION_MESSAGE);
        } else if (endDate.isEmpty()) {
            throw new YapperException(
                    StringStorage.DEADLINE_INSTRUCTION_PREFIX + " " +
                    StringStorage.MISSING_END_DATE_MESSAGE);
        }
    }
    public static void checkIfEventArgsMissing(String desc, String startDate, String endDate) throws YapperException {
        if (desc.isEmpty()) {
            throw new YapperException(
                    StringStorage.EVENT_INSTRUCTION_PREFIX + " " +
                    StringStorage.MISSING_DESCRIPTION_MESSAGE);
        } else if (startDate.isEmpty()) {
            throw new YapperException(
                    StringStorage.EVENT_INSTRUCTION_PREFIX + " " +
                    StringStorage.MISSING_START_DATE_MESSAGE);
        } else if (endDate.isEmpty()) {
            throw new YapperException(
                    StringStorage.EVENT_INSTRUCTION_PREFIX + " " +
                    StringStorage.MISSING_END_DATE_MESSAGE);
        }
    }
    // User Input Parsing Exception
    public static void checkIfDeadlineKeywordsPresent(int byOrdinal) throws YapperException {
        if (byOrdinal == -1) {
            throw new YapperException(
                    StringStorage.DEADLINE_END_DATE_DELIMITER
                    + " keyword not found for " +
                    StringStorage.DEADLINE_INSTRUCTION_PREFIX);
        }
    }
    public static void checkIfEventKeywordsPresent(int fromOrdinal, int toOrdinal) throws YapperException {
        if (fromOrdinal == -1) {
            throw new YapperException(
                    StringStorage.EVENT_START_DATE_DELIMITER
                    + " keyword not found for " +
                    StringStorage.EVENT_INSTRUCTION_PREFIX);
        } else if (toOrdinal == -1) {
            throw new YapperException(
                    StringStorage.EVENT_END_DATE_DELIMITER
                    + " keyword not found for " +
                    StringStorage.EVENT_INSTRUCTION_PREFIX);
        }
    }
    // File Input Parsing Exceptions
    public static void checkIfTaskTypeValid(String taskType) throws YapperException {
        if ( !taskType.equals(StringStorage.TODO_SYMBOL) &&
             !taskType.equals(StringStorage.DEADLINE_SYMBOL) &&
             !taskType.equals(StringStorage.EVENT_SYMBOL) ) {
            throw new YapperException(StringStorage.INVALID_TASK_TYPE_MESSAGE);
        }
    }
    public static void checkIfTaskStatusValid(String taskStatus) throws YapperException {
        if (!taskStatus.equals(StringStorage.IS_DONE_SYMBOL) &&
            !taskStatus.equals(StringStorage.NOT_DONE_SYMBOL)) {
            throw new YapperException(StringStorage.INVALID_TASK_STATUS_MESSAGE);
        }
    }
    // Exceptions for: DELETE, MARK, UNMARK
    public static void checkIfTaskOrdinalIsOutOfRange(int currTaskTotal) throws YapperException {
        if (currTaskTotal == 0) {
            throw new YapperException(StringStorage.LIST_EMPTY_MESSAGE);
        }
    }
    public static void checkIfTaskOrdinalIsOutOfRange(int currTaskTotal, int taskOrdinal) throws YapperException {
        if (taskOrdinal < 0 || taskOrdinal >= currTaskTotal) {
            throw new YapperException(StringStorage.LIST_OOB_MESSAGE);
        }
    }
    public static void checkIfDoneStatusNeedsChanging(boolean oldStatus, boolean newStatus) throws YapperException {
        if (oldStatus && newStatus) {
            throw new YapperException(StringStorage.TASK_ALREADY_DONE_MESSAGE);
        } else if (!oldStatus && !newStatus) {
            throw new YapperException(StringStorage.TASK_STILL_NOT_DONE_MESSAGE);
        }
    }

//    public static void template() { // TODO rename
//        if () { // TODO condition
//            throw new YapperException("tba"); // TODO message
//        }
//    }
}
