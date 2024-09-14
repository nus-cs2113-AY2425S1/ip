package yapper.exceptions;

import yapper.exceptions.YapperException;
import yapper.io.StringStorage;

public class ErrorHandler {
    public static void checkIfListEmpty(int currTaskTotal) throws YapperException {
        if (currTaskTotal == 0) {
            throw new YapperException(StringStorage.LIST_EMPTY_MESSAGE);
        }
    }
    public static void checkIfListFull(int currTaskTotal, int maxCapacity) throws YapperException {
        if (currTaskTotal == maxCapacity) {
            throw new YapperException(StringStorage.LIST_FULL_MESSAGE);
        }
    }
    public static void checkIfTaskOrdinalWithinRange(int currTaskTotal, int taskOrdinal) throws YapperException {
        if (taskOrdinal < 0 || taskOrdinal >= currTaskTotal) {
            throw new YapperException(StringStorage.LIST_OOB_MESSAGE);
        }
    }
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
}
