package seedu.taskscheduler.logic.commands;

import static seedu.taskscheduler.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.io.File;

import seedu.taskscheduler.commons.core.EventsCenter;
import seedu.taskscheduler.commons.core.Messages;
import seedu.taskscheduler.commons.events.storage.ImportFilePathEvent;
import seedu.taskscheduler.commons.util.FileUtil;

//@@author A0138696L

/**
 * Importing data into taskScheduler.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Import file to Task Scheduler. "
            + "Parameters: <filename>\n"
            + "Example: " + COMMAND_WORD
            + " TaskSchedulerData\n";
    public static final String MESSAGE_USAGE_INVALID = COMMAND_WORD + ": Import valid file to Task Scheduler. "
            + "Parameters: <filename>\n"
            + "Example: " + COMMAND_WORD
            + " TaskSchedulerData\n";

    public static final String MESSAGE_SUCCESS = "File path changed: %s";
    
    private String filePath;
    
    public ImportCommand(String arguments) {
        this.filePath = arguments;
    }

    @Override
    public CommandResult execute() {
        if (FileUtil.isFileExists(new File(filePath))) {
            EventsCenter.getInstance().post(new ImportFilePathEvent(filePath));
            return new CommandResult(String.format(MESSAGE_SUCCESS, filePath));
        }
        return new CommandResult(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE_INVALID)); 
    }

    @Override
    public CommandResult revert() {
        // This command not available for revert
        assert false : Messages.MESSAGE_PROGRAM_ERROR;
        return null;
    } 
}
