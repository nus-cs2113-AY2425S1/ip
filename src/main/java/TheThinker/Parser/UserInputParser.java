package TheThinker.Parser;

import TheThinker.Exceptions.FormattingException;

import java.util.Scanner;

public class UserInputParser {

    public static String userInput;
    public static final Scanner scanner = new Scanner(System.in);


    public static final String GET_FORMAT = "Please follow format : get [dd/mm/yyyy] or [dd/MMMM/yyyy] or [dd/MM/yyyy , HH am/pm]";
    public static final String FIND_FORMAT = "Please follow format : find [keyword]";

    public static String getUserInput(){
        userInput = scanner.nextLine();
        userInput = userInput.trim();
        return userInput;
    }

    public static String parseUserAction(){
        String[] wordsInUserInput = userInput.split(" ");
        return wordsInUserInput[0];
    }

    /**
     * Parses the task number after unmark or mark command
     *
     * @return Task number
     * @throws NumberFormatException If task number string provided is not a number.
     * @throws FormattingException If no task or multiple task number is provided.
     */
    public static int parseNumberAfterTask() throws NumberFormatException , FormattingException{
        String[] parsedInputs = userInput.split(" ");

        if(parsedInputs.length == 1){
            throw new FormattingException("Task number is not indicated. Please follow format : mark/unmark [number]");
        }

        if(parsedInputs.length > 2){
            throw new FormattingException("Multiple tasks to mark is indicated in a single command. Please mark them one by one.");
        }

        String numberToMark = parsedInputs[1].trim();
        return Integer.parseInt(numberToMark);
    }

    public static String parseKeywordAfterFind() throws FormattingException{
        String[] parsedInputs = userInput.split(" ");
        if(parsedInputs.length != 2){
            throw new FormattingException("keyword is missing. " + FIND_FORMAT);
        }

        return parsedInputs[1].trim();
    }
}
