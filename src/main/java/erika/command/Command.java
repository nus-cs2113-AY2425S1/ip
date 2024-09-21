package erika.command;

import erika.exception.OutOfBoundsException;
import erika.filesystem.FileSystem;
import erika.tasklist.TaskList;

import java.io.IOException;

abstract public class Command {
    abstract public void execute(TaskList tasks, FileSystem fileSystem) throws IOException, OutOfBoundsException;
    abstract public boolean isExit();
}
