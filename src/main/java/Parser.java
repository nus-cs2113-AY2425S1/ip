import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    private String commandType = null;
    private ArrayList<String> argumentList = null;

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

    private void parseAdd(String rawText) {
        Pattern pattern = Pattern.compile(" .+");
        Matcher matcher = pattern.matcher(rawText);

        String afterFirstSpace = "";

        if (matcher.find()) {
            afterFirstSpace = matcher.group().trim(); // Trim to remove leading space
        }

        argumentList.add(afterFirstSpace);
    }

    private void parseMark(String rawText) {
        Pattern pattern = Pattern.compile(" .+");
        Matcher matcher = pattern.matcher(rawText);

        String afterFirstSpace = "";

        if (matcher.find()) {
            afterFirstSpace = matcher.group().trim(); // Trim to remove leading space
        }

        argumentList.add(afterFirstSpace);
    }

    private void parseToDo(String rawText) {
        Pattern pattern = Pattern.compile(" .+");
        Matcher matcher = pattern.matcher(rawText);

        String afterFirstSpace = "";

        if (matcher.find()) {
            afterFirstSpace = matcher.group().trim(); // Trim to remove leading space
        }

        argumentList.add(afterFirstSpace);
    }

    private void parseDeadline(String rawText) {
        Pattern pattern = Pattern.compile("(.+?)\\s/by\\s(.+)");
        Matcher matcher = pattern.matcher(rawText);

        String whatToDo = "";
        String byWhen = "";

        if (matcher.find()) {
            whatToDo = matcher.group(1);
            byWhen = matcher.group(2);
        }

        argumentList.add(whatToDo);
        argumentList.add(byWhen);
    }

    private void parseEvent(String rawText) {
        Pattern pattern = Pattern.compile("(.+?)\\s/from\\s(.+?)\\s/to\\s(.+)");
        Matcher matcher = pattern.matcher(rawText);

        String whatToDo = "";
        String fromWhen = "";
        String toWhen = "";

        if (matcher.find()) {
            whatToDo = matcher.group(1);
            fromWhen = matcher.group(2);
            toWhen = matcher.group(3);
        }

        argumentList.add(whatToDo);
        argumentList.add(fromWhen);
        argumentList.add(toWhen);
    }

    public void parse(String rawText) {
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

    public void execute() {
        if (commandType == null) {
            throw new IllegalArgumentException("No command has been parsed.");
        }

        switch (commandType) {
            case "bye" -> Pythia.sayBye();
            case "list" -> Pythia.listTasks();
            case "add" -> Pythia.addTask(argumentList.get(0));
            case "mark" -> Pythia.markTask(Integer.parseInt(argumentList.get(0)));
            case "todo" -> Pythia.addToDo(argumentList.get(0));
            case "deadline" -> Pythia.addDeadline(argumentList.get(0), argumentList.get(1));
            case "event" -> Pythia.addEvent(argumentList.get(0), argumentList.get(1), argumentList.get(2));
            default -> IO.printResponse("Hmm. I am not sure what you mean.");
        }
    }
}
