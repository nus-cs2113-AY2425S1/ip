package commands;

import java.util.Arrays;
import java.util.HashMap;

public class Command {
    public String[] splitCommand(String line) {
        return line.split(" ", 2);
    }

    public int getIndex(String line, int size) {
        String[] parts = splitCommand(line);
        if (parts.length <2){
            System.out.println("Please provide an index");
            return -1;
        }
        try {
            int index = Integer.parseInt(parts[1]);
            index-=1; // Convert from one based to zero based indexing
            if (index<0 || index>=size){
                System.out.println("Please use a index within bounds");
                return -1;
            }
            return index;
        }
        catch (NumberFormatException e) {
            System.out.println("Please provide a valid number as index");
        }
        return -1;
    }

    public HashMap<String,String> getFlags(String line, String[] requiredOptions){
        String[] parts = splitCommand(line);
        if (parts.length < 2){
            System.out.println("Please provide required options: " + Arrays.toString(requiredOptions));
            return null;
        }

        HashMap<String,String> options = parseFlags(parts[1]);
        if (containsAllRequiredFlags(options, requiredOptions)) {
            System.out.println("Please provide required options: " + Arrays.toString(requiredOptions));
            return null;
        }
        return options;
    }

    public HashMap<String, String> parseFlags(String line) {
        if (!line.contains("/")) {
            return new HashMap<>();
        }
        String[] parameters = line.split("/");
        HashMap<String, String> options = new HashMap<>();
        for (String parameter : parameters) {
            if (parameter.isEmpty()) {
                continue;
            }
            String[] parts = splitCommand(parameter);
            if (parts.length < 2) {
                continue;
            }
            options.put(parts[0], parts[1]);
        }
        return options;
    }
    public boolean containsAllRequiredFlags(HashMap<String, String> options, String[] requiredOptions) {
        for (String option : requiredOptions) {
            if (!options.containsKey(option)) {
                return true;
            }
        }
        return false;
    }
}
