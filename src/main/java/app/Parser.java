package app;

import tasks.Task;

import exceptions.TerriException;

import taskmanager.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

import static app.Messages.COMMAND_ADD_DEADLINE;
import static app.Messages.COMMAND_ADD_EVENT;
import static app.Messages.COMMAND_ADD_TODO;
import static app.Messages.COMMAND_DELETE_TASK;
import static app.Messages.COMMAND_LIST_TASKS;
import static app.Messages.COMMAND_MARK_TASK;
import static app.Messages.COMMAND_UNMARK_TASK;
import static app.Messages.UNRECOGNISED_KEYWORD;

public class Parser {


    /**
     * Processes the user input and handles task-related commands.
     * Recognized commands include listing tasks, marking tasks as complete or incomplete,
     * adding deadlines or events, and deleting tasks. If an unrecognized command is entered,
     * a {@code TerriException} is thrown.
     *
     * @param userInput The input entered by the user.
     * @throws TerriException If the input command is not recognized.
     */
    static void parseInput(String userInput) throws TerriException {

        // Isolate individual keywords in user input
        String[] keyWord = userInput.split(" ");

        // Define behaviour depending on initial keyword
        switch (keyWord[0].toUpperCase()) {
            // List all tasks with completion indicators
            case COMMAND_LIST_TASKS:
                UI.listTasks(TaskList.tasksStorage,TaskList.taskCounter);
                break;
            // Mark tasks complete
            case COMMAND_MARK_TASK:
                TaskList.handleSetDone(keyWord,true);
                break;
            // Mark task not complete
            case COMMAND_UNMARK_TASK:
                TaskList.handleSetDone(keyWord,false);
                break;
            case COMMAND_ADD_DEADLINE:
                TaskList.handleDeadline(keyWord);
                break;
            case COMMAND_ADD_EVENT:
                TaskList.handleEvent(keyWord);
                break;
            case COMMAND_ADD_TODO:
                TaskList.handleToDo(keyWord);
                break;
            case COMMAND_DELETE_TASK:
                TaskList.deleteTask(keyWord);
                break;
            // Handle unrecognised keywords by offering instruction page
            default:
                throw new TerriException(UNRECOGNISED_KEYWORD);
        }
    }

}
