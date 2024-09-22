package yapper.exceptions;

import yapper.io.StringStorage;

public class ExceptionHandler {

    // User Input Validation Errors
    public static void checkIfUserInputEmpty(String userInputString) throws YapperException {
        if (userInputString.isEmpty()) {
            throw new YapperException("no command given"); // TODO
        }
    }
    public static void checkIfTodoArgsMissing(String desc) throws YapperException {
        if (desc.isEmpty()) {
            throw new YapperException(StringStorage.MISSING_DESCRIPTION_MESSAGE);
        }
    }
    public static void checkIfDeadlineArgsMissing(String desc, String endDate) throws YapperException {
        if (desc.isEmpty()) {
            throw new YapperException(StringStorage.MISSING_DESCRIPTION_MESSAGE);
        } else if (endDate.isEmpty()) {
            throw new YapperException(StringStorage.MISSING_END_DATE_MESSAGE);
        }
    }
    public static void checkIfEventArgsMissing(String desc, String startDate, String endDate) throws YapperException {
        if (desc.isEmpty()) {
            throw new YapperException(StringStorage.MISSING_DESCRIPTION_MESSAGE);
        } else if (startDate.isEmpty()) {
            throw new YapperException(StringStorage.MISSING_START_DATE_MESSAGE);
        } else if (endDate.isEmpty()) {
            throw new YapperException(StringStorage.MISSING_END_DATE_MESSAGE);
        }
    }
    // User Input Parsing Exception
    public static void checkIfDeadlineKeywordsPresent(int byOrdinal) throws YapperException {
        if (byOrdinal == -1) {
            throw new YapperException("/by" + " keyword not found");
        }
    }
    public static void checkIfEventKeywordsPresent(int fromOrdinal, int toOrdinal) throws YapperException {
        if (fromOrdinal == -1) {
            throw new YapperException("/from" + " keyword not found");
        } else if (toOrdinal == -1) {
            throw new YapperException("/to" + " keyword not found");
        }
    }
    // File Input Parsing Exceptions
    public static void checkIfTaskTypeValid(String taskType) throws YapperException {
        if ( !taskType.equals(StringStorage.TODO_SYMBOL) &&
             !taskType.equals(StringStorage.DEADLINE_SYMBOL) &&
             !taskType.equals(StringStorage.EVENT_SYMBOL) ) {
            throw new YapperException("I don't recognize the task type");
        }
    }
    public static void checkIfTaskStatusValid(String taskStatus) throws YapperException {
        if (!taskStatus.equals(StringStorage.NOT_DONE_SYMBOL) &&
            !taskStatus.equals(StringStorage.NOT_DONE_SYMBOL)) {
            throw new YapperException("I don't recognize the task completion status");
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

    // File Errors
    public static void checkIfB() throws YapperException { // TODO rename
        if (false) { // TODO condition
            throw new YapperException(StringStorage.SAVING_ERROR_MESSAGE); // TODO message
        }
    }
    public static void checkIfA() throws YapperException { // todo rename
        if (false) { // TODO condition
            throw new YapperException(StringStorage.LOADING_ERROR_MESSAGE); // TODO message
        }
    }

//    public static void template() { // TODO rename
//        if () { // TODO condition
//            throw new YapperException("tba"); // TODO message
//        }
//    }
}
