package seedu.taskscheduler.logic.parser;

import static seedu.taskscheduler.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;

import seedu.taskscheduler.logic.commands.Command;
import seedu.taskscheduler.logic.commands.ImportCommand;
import seedu.taskscheduler.logic.commands.IncorrectCommand;

//@@author A0138696L

/**
* Parses import command user input.
*/
public class ImportCommandParser extends CommandParser {

    /**
     * Parses arguments in the context of the set import command.
     * 
     * @param args full command args string
     * @return the importing file path
     */
    
    @Override
    public Command prepareCommand(String args) {
        Matcher matcher = SETPATH_DATA_ARGS_FORMAT.matcher(args);

        if (matcher.matches()) {
            String path = matcher.group("name").trim().replaceAll("/$","") +".xml";
            return new ImportCommand(path); 
        }
        else {   
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ImportCommand.MESSAGE_USAGE));
        }
    }
}
