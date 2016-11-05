package seedu.taskscheduler.logic.commands;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;

import seedu.taskscheduler.commons.core.Messages;

//@@author A0138696L
/**
 * Export the data of Task Scheduler to user specified path.
 */
public class ExportCommand extends Command {
    
public static final String COMMAND_WORD = "export";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exporting data of Task Scheduler to user specified location. "
            + "Parameters: <filename>\n"
            + "Example: " + COMMAND_WORD
            + " TaskSchedulerData\n";

    public static final String MESSAGE_SUCCESS = "Successfully Exported data to: %s";
    public static final String MESSAGE_UNSUCCESS = "Unsuccessfully in exporting data to: %s";
    
    private String PathLink;
    
    public ExportCommand(String arguments) {
        this.PathLink = arguments;
    }

    @Override
    public CommandResult execute() {
        try {
            exportData();
        } catch (IOException e) {
            return new CommandResult(String.format(MESSAGE_UNSUCCESS, PathLink));
        }
        
        return new CommandResult(String.format(MESSAGE_SUCCESS, PathLink));
    }

    @Override
    public CommandResult revert() {
        // This command not available for revert
        assert false : Messages.MESSAGE_PROGRAM_ERROR;
        return null;
    }
    
    private void exportData() throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(CommandHistory.readPreviousStorageFilePath());
            os = new FileOutputStream(PathLink);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
