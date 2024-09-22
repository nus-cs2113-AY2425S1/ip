package yapper.exceptions;

import yapper.io.StringStorage;

public class ExceptionHandler {

    // User Input Validation Errors
    public static void checkIfUserInputEmpty(String userInputString, boolean isAboutArgs) throws YapperException {
        // Boolean is to differentiate between exceptions
        if (userInputString.isEmpty() && !isAboutArgs) {
            throw new YapperException("did you say something?"); // TODO
        } else if ( userInputString.isEmpty() ) {
            throw new YapperException("Absence of Instruction Arguments"); // TODO
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
            throw new YapperException("task type is not any of these: "
                    + StringStorage.TODO_SYMBOL + ", "
                    + StringStorage.DEADLINE_SYMBOL + ", "
                    + StringStorage.EVENT_SYMBOL);
        }
    }
    public static void checkIfTaskStatusValid(String taskStatus) throws YapperException {
        if (!taskStatus.equals(StringStorage.IS_DONE_SYMBOL) &&
            !taskStatus.equals(StringStorage.NOT_DONE_SYMBOL)) {
            throw new YapperException("task completion status is not any of these: "
                    + StringStorage.IS_DONE_SYMBOL + ", "
                    + StringStorage.NOT_DONE_SYMBOL);
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
            throw new YapperException("this task is already marked as done");
        } else if (!oldStatus && !newStatus) {
            throw new YapperException("this task is already marked as not done");
        }
    }

//    public static void template() { // TODO rename
//        if () { // TODO condition
//            throw new YapperException("tba"); // TODO message
//        }
//    }
}
