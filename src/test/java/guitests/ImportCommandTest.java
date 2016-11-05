package guitests;

import static seedu.taskscheduler.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.Test;

import seedu.taskscheduler.logic.commands.ImportCommand;

//@@author A0138696L

public class ImportCommandTest extends TaskSchedulerGuiTest {

    @Test
    public void importFilePath() {
        
        // Checking for invalid importing filepath before executing import command
        String importingPath = "datatest/testing";
        commandBox.runCommand("import " + importingPath);
        assertResultMessage(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE_INVALID));
    }
}
