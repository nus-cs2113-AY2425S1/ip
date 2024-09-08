import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Collections;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    private void parseBye(String rawText) {}

    private void parseList(String rawText) {}

    private void parseAdd(String rawText) throws PythiaException {
        Pattern pattern = Pattern.compile("add\\s(.+)");
        Matcher matcher = pattern.matcher(rawText);

        String what = "";

        if (matcher.find()) {
            what = matcher.group(1);
        } else {
            throw new PythiaException(parsingErrorMessage, "I am not sure what to add.");
        }

        argumentList.clear();
        Collections.addAll(argumentList, what);
    }

    private void parseMark(String rawText) throws PythiaException {
        Pattern pattern = Pattern.compile("mark\\s(.+)");
        Matcher matcher = pattern.matcher(rawText);

        String what = "";

        if (matcher.find()) {
            what = matcher.group(1);
        } else {
            throw new PythiaException(parsingErrorMessage, "Please specify what should I mark as done.");
        }

        argumentList.clear();
        Collections.addAll(argumentList, what);
    }

    private void parseToDo(String rawText) throws PythiaException {
        Pattern pattern = Pattern.compile("todo\\s(.+)");
        Matcher matcher = pattern.matcher(rawText);

        String what = "";

        if (matcher.find()) {
            what = matcher.group(1);
        } else {
            throw new PythiaException(parsingErrorMessage, "Todo should have a description.");
        }

        argumentList.clear();
        Collections.addAll(argumentList, what);
    }

    private void parseDeadline(String rawText) throws PythiaException {
        Pattern pattern = Pattern.compile("deadline\\s(.+)\\s/by\\s(.+)");
        Matcher matcher = pattern.matcher(rawText);

        String what = "";
        String byWhen = "";

        if (matcher.find()) {
            what = matcher.group(1);
            byWhen = matcher.group(2);
        } else {
            throw new PythiaException(parsingErrorMessage, "Deadline should have description and a date.");
        }

        argumentList.clear();
        Collections.addAll(argumentList, what, byWhen);
    }

    private void parseEvent(String rawText) throws PythiaException {
        Pattern pattern = Pattern.compile("event\\s(.+)\\s/from\\s(.+)\\s/to\\s(.+)");
        Matcher matcher = pattern.matcher(rawText);

        String what = "";
        String fromWhen = "";
        String toWhen = "";

        if (matcher.find()) {
            what = matcher.group(1);
            fromWhen = matcher.group(2);
            toWhen = matcher.group(3);
        } else {
            throw new PythiaException(parsingErrorMessage, "Event should have description and from and to dates.");
        }

        argumentList.clear();
        Collections.addAll(argumentList, what, fromWhen, toWhen);
    }

    public void parse(String rawText) throws PythiaException {
        commandType = parseCommandType(rawText);
        argumentList = new ArrayList<>();

        switch (commandType) {
            case "bye" -> parseBye(rawText);
            case "list" -> parseList(rawText);
            case "add" -> parseAdd(rawText);
            case "mark" -> parseMark(rawText);
            case "todo" -> parseToDo(rawText);
            case "deadline" -> parseDeadline(rawText);
            case "event" -> parseEvent(rawText);
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
            default -> throw new PythiaException(parsingErrorMessage, "Hmm. I am not sure what you mean.");
        }
    }
}