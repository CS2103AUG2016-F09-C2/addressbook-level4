package seedu.taskscheduler.logic.parser;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.taskscheduler.commons.exceptions.IllegalValueException;
import seedu.taskscheduler.commons.util.StringUtil;
import seedu.taskscheduler.logic.commands.Command;
import seedu.taskscheduler.model.task.DeadlineTask;
import seedu.taskscheduler.model.task.EventTask;
import seedu.taskscheduler.model.task.FloatingTask;
import seedu.taskscheduler.model.task.Location;
import seedu.taskscheduler.model.task.Name;
import seedu.taskscheduler.model.task.Task;
import seedu.taskscheduler.model.task.TaskDateTime;

//@@author A0148145E

/**
 * Represents a command parser with hidden internal logic and the ability to be executed.
 */
public abstract class CommandParser {

    protected static final String START_DATE_DELIMITER = "s/";
    protected static final String END_DATE_DELIMITER = "e/";
    
    protected static final Pattern INDEX_COMMAND_FORMAT = Pattern.compile("(?<index>\\d+)(?<arguments>.*)");

    protected static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    protected static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace
   
    protected static final Pattern EVENT_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<name>.+)"
                    + "(?>\\s+\\bfrom\\b)"
                    + "(?<startDate>.*)"
                    + "(?>\\s+\\bto\\b)"
                    + "(?<endDate>.*)"
                    + "(?>\\s+\\bat\\b)"
                    + "(?<address>.*)"
                    );  
    
    protected static final Pattern DEADLINE_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<name>.+?)"
                    + "(?>(\\s+\\b(by|on)\\b))"
                    + "(?<endDate>.*)"
                    );
    
    protected static final Pattern FLOATING_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<name>.+)");

    protected static final Pattern PATH_DATA_ARGS_FORMAT =
            Pattern.compile("(?<name>[\\p{Alnum}|/|.|_|:|\\\\|\\s+]+)"); 
    
    /**
     * Returns the specified index in the {@code command} IF a positive unsigned integer is given as the index.
     *   Returns an {@code Optional.empty()} otherwise.
     */
    protected Optional<Integer> parseIndex(String command) {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(command.trim());
        if (!matcher.matches()) {
            return Optional.empty();
        }

        String index = matcher.group("targetIndex");
        if (!StringUtil.isUnsignedInteger(index)) {
            return Optional.empty();
        }
        return Optional.of(Integer.parseInt(index));

    }

    public abstract Command prepareCommand(String args);
    
    /**
     * @param args that represents a task string
     * @return generated Task
     * @throws IllegalValueException if Task parameters are incorrect
     * @throws InputMismatchException if incorrect format
     */
    protected Task generateTaskFromArgs(String args) throws IllegalValueException, InputMismatchException{
        Matcher taskMatcher;
        
        taskMatcher = EVENT_DATA_ARGS_FORMAT.matcher(args);
        if (taskMatcher.matches()) {
            return new EventTask(new Name(taskMatcher.group("name")), new TaskDateTime(taskMatcher.group("startDate")),
                    new TaskDateTime(taskMatcher.group("endDate")), new Location(taskMatcher.group("address")));
        }
        
        taskMatcher = DEADLINE_DATA_ARGS_FORMAT.matcher(args);
        if (taskMatcher.matches()) {
            return new DeadlineTask(new Name(taskMatcher.group("name")),
                    new TaskDateTime(taskMatcher.group("endDate")));
        }
        
        taskMatcher = FLOATING_DATA_ARGS_FORMAT.matcher(args);
        if (taskMatcher.matches()) {
            return new FloatingTask(new Name(taskMatcher.group("name")));
        }
        return null;
    }
}
