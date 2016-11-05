package seedu.taskscheduler.logic.parser;

import static seedu.taskscheduler.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.taskscheduler.logic.commands.Command;
import seedu.taskscheduler.logic.commands.ExportCommand;
import seedu.taskscheduler.logic.commands.IncorrectCommand;

//@@author A0138696L

/**
* Parses export command user input.
*/
public class ExportCommandParser extends CommandParser {

    /**
     * Parses arguments in the context of the export command.
     * 
     * @param args full command args string
     * @return the exported path
     */

    @Override
    public Command prepareCommand(String args) {
        Matcher matcher = SETPATH_DATA_ARGS_FORMAT.matcher(args);

        if (matcher.matches()) {
            String path = matcher.group("name").trim().replaceAll("/$","") +".xml";
            return new ExportCommand(path); 
        }
        else {   
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }
    }
    
}
