package yapper.exceptions;

import yapper.exceptions.YapperException;
import yapper.io.StringStorage;

public class ErrorHandler {

    // Ordinal Errors
    public static void checkIfListEmpty(int currTaskTotal) throws YapperException {
        if (currTaskTotal == 0) {
            throw new YapperException(StringStorage.LIST_EMPTY_MESSAGE);
        }
    }
    public static void checkIfTaskOrdinalWithinRange(int currTaskTotal, int taskOrdinal) throws YapperException {
        if (taskOrdinal < 0 || taskOrdinal >= currTaskTotal) {
            throw new YapperException(StringStorage.LIST_OOB_MESSAGE);
        }
    }
    // User Input Validation Errors
    public static void checkIfUserInputEmpty(String userInputString) throws YapperException {
        if (userInputString.isEmpty()) {
            throw new YapperException("no command given");
        }
    }
    public static void checkIfUserInputArgsEmpty(String userInputString) throws YapperException {
        if (userInputString.isEmpty()) {
            throw new YapperException(StringStorage.INCOMPLETE_INSTRUCTION_MESSAGE);
        }
    }
    //    public static void checkIfUserInputRecognised();

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

//    public static void template() {
//        if () {
//            ;
//        }
//    }
}
