package seedu.taskscheduler.logic.commands;

import seedu.taskscheduler.commons.core.EventsCenter;
import seedu.taskscheduler.commons.events.storage.FilePathChangedEvent;

//@@author A0138696L

/**
 * Changing working file path of taskScheduler.
 */
public class SetpathCommand extends Command {

    public static final String COMMAND_WORD = "setpath";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Set custom save path for Task Scheduler. "
            + "Parameters: <filename>\n"
            + "Example: " + COMMAND_WORD
            + " TaskSchedulerData\n";

    public static final String MESSAGE_SUCCESS = "File path changed: %s";
    
    private String savedPathLink;
    
    public SetpathCommand(String arguments) {
        this.savedPathLink = arguments;
    }

    @Override
    public CommandResult execute() {
        CommandHistory.setPreviousStorageFilePath(savedPathLink);
        EventsCenter.getInstance().post(new FilePathChangedEvent(savedPathLink));
        CommandHistory.addExecutedCommand(this);
        return new CommandResult(String.format(MESSAGE_SUCCESS, savedPathLink));
    }

    @Override
    public CommandResult revert() {
        // Discard similar pathlink on the top of the stack
        if (savedPathLink == CommandHistory.readPreviousStorageFilePath()) {
            CommandHistory.getPreviousStorageFilePath();
        }
        savedPathLink = CommandHistory.getPreviousStorageFilePath();
        EventsCenter.getInstance().post(new FilePathChangedEvent(savedPathLink));
        CommandHistory.addRevertedCommand(this);
        return new CommandResult(String.format(MESSAGE_SUCCESS, savedPathLink));
    } 
}