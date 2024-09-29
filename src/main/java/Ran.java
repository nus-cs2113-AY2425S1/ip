package main.java;

import main.java.Ui;
import main.java.Parser;
import main.java.TaskList;
import main.java.Storage;

import ran.command.Command;
import ran.command.CommandType;
import ran.exception.InvalidCommandException;
import ran.exception.OutOfListBoundsException;
import ran.exception.EmptyListException;
import ran.exception.MissingArgumentException;

import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Ran {
    private Storage storage; 
    private Ui ui;
    private TaskList tasks;

    public Ran(String directory) {
        tasks = new TaskList();
        ui = new Ui();

        try {
            storage = new Storage(directory);
        } catch (IOException e) {
            ui.printMessage(new String[] {"There appears to a barrier between your data file and I.", 
                    "Maybe I can use my master's gap manipulation powers to overcome this..."});
            return;
        } 
        try {
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            ui.printMessage(new String[] {"That is strange, I swear I thought your data file exists...", 
                    "Could this be the start of another incident?", 
                    "I ought to report this pecularity to master Yukari."});
            return;
        }
    }
    
    public String[] getArgumentErrorMessage(CommandType commandType) {
        switch (commandType) {
        case TODO:
            return new String[] {"Here's how to add a Todo task:", 
                    "todo [Task Description]"};
        case EVENT:
            return new String[] {"Here's how to add an Event:", 
                    "event [Event Description] /from [Start Time] /to [End Time]"};
        case DEADLINE:
            return new String[] {"Here's how to add a Deadline:", 
                    "deadline [Deadline Description] /by [Deadline]"};
        case MARK:
            return new String[] {"Here's how to mark a task:", 
                    "mark [index_number]"};
        case UNMARK:
            return new String[] {"Here's how to unmark a task:", 
                    "unmark [index_number]"};
        case DELETE:
            return new String[] {"Here's how to delete a task:", 
                    "delete [index_number]"};
        case UNDEFINED:
            // Fall-through
        Default:
            break;
        }
        return new String[] {"What? A new kind of error? I must study this."};
    }

    public void run() {
        ui.greet();
        boolean isTerminated = false;
        // Take in user input from the terminal
        String input;
        Scanner in = new Scanner(System.in);

        while (!isTerminated) {
            input = in.nextLine();
            try {
                Command c = Parser.parse(input);
                isTerminated = c.execute(tasks, ui, storage);
            } catch (IOException e) {
                ui.printMessage(new String[] {"Huh? Your data file is suddenly inaccessible.", 
                        "Is someone tampering with it under our noses?", 
                        "I ought to report this pecularity to master Yukari."});
            } catch (OutOfListBoundsException e) {
                ui.printMessage(new String[] {"Woah, that index is out of the bounds of your list.", 
                        "Accessing it would tear a gap in this computer simulation,", 
                        "leading to a premature termination, causing an incident.", 
                        "That, I cannot allow."});
            } catch (EmptyListException e) {
                ui.printMessage(new String[] {"Ah, it seems your list is empty.", 
                        "Why not give it some substance?"});
            } catch (MissingArgumentException e) {
                String[] argumentErrorMessage = getArgumentErrorMessage(e.getType());
                ui.printMessage(argumentErrorMessage);
            } catch (InvalidCommandException e) {
                ui.printMessage(new String[] {"Your command doesn't comply to the spellbook rules.", 
                        "Here's some you can consider:", 
                        "todo, deadline, event, mark, unmark, list, bye"});
            }
        }

        ui.bidFarewell();
    }

    public static void main(String[] args) {
        new Ran("./data").run();
    }
}
