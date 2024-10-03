package pythia.utility;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
import pythia.exceptions.PythiaException;
import pythia.Pythia;

public class Parser {
    private String commandType = null;
    private ArrayList<String> argumentList = null;
    private String parsingErrorMessage = "Parsing of add command unsuccessful.";

    public Parser() {
    }

    private String parseCommandType(String rawText) {
        int spaceIndex = rawText.indexOf(' ');

        String firstPart;
        if (spaceIndex != -1) {
            firstPart = rawText.substring(0, spaceIndex);
        } else {
            firstPart = rawText;
        }
        return firstPart;
    }

    private void parseBye(String rawText) {
    }

    private void parseList(String rawText) {
    }

    private void parseAdd(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "add\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 1;

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "I am not sure what to add.");
        }
    }

    private void parseMark(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "mark\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 1 && argumentList.get(0).matches("\\d+");

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Please specify what should I mark as done.");
        }
    }

    private void parseToDo(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "todo\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 1;

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Todo should have a description.");
        }
    }

    private void parseDeadline(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "deadline\\s(.+)\\s/by\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 2;

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Deadline should have description and a date.");
        }
    }

    private void parseEvent(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "event\\s(.+)\\s/from\\s(.+)\\s/to\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 3;

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Event should have description and from and to dates.");
        }
    }

    public void parseDelete(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "delete\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 1 && argumentList.get(0).matches("\\d+");

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Please specify what should I delete.");
        }
    }

    public void parseFind(String rawText) throws PythiaException {
        argumentList = tokenize(rawText, "find\\s(.+)");
        boolean isCorrectFormat = argumentList.size() == 1;

        if (!isCorrectFormat) {
            throw new PythiaException(parsingErrorMessage, "Please specify what should I find.");
        }
    }

    private ArrayList<String> tokenize(String rawText, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(rawText);

        ArrayList<String> tokens = new ArrayList<>();

        if (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                tokens.add(matcher.group(i));  // Get specific captured groups
            }
        }

        return tokens;
    }

    public void parse(String rawText) throws PythiaException {
        commandType = parseCommandType(rawText);

        switch (commandType) {
            case "bye" -> parseBye(rawText);
            case "list" -> parseList(rawText);
            case "add" -> parseAdd(rawText);
            case "mark" -> parseMark(rawText);
            case "todo" -> parseToDo(rawText);
            case "deadline" -> parseDeadline(rawText);
            case "event" -> parseEvent(rawText);
            case "delete" -> parseDelete(rawText);
            case "find" -> parseFind(rawText);
        }
    }

    public void execute() throws PythiaException {
        if (commandType == null) {
            throw new PythiaException(parsingErrorMessage, "Sorry, I am busy.");
        }

        switch (commandType) {
            case "bye" -> Pythia.sayBye();
            case "list" -> Pythia.listTasks();
            case "add" -> Pythia.addTask(argumentList.get(0));
            case "mark" -> Pythia.markTask(Integer.parseInt(argumentList.get(0)));
            case "todo" -> Pythia.addToDo(argumentList.get(0));
            case "deadline" -> Pythia.addDeadline(argumentList.get(0), argumentList.get(1));
            case "event" -> Pythia.addEvent(argumentList.get(0), argumentList.get(1), argumentList.get(2));
            case "delete" -> Pythia.deleteTask(Integer.parseInt(argumentList.get(0)));
            case "find" -> Pythia.findTasks(argumentList.get(0));
            default -> throw new PythiaException(parsingErrorMessage, "Hmm. I am not sure what you mean.");
        }
    }
}