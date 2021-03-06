package seedu.taskscheduler.logic.commands;

import seedu.taskscheduler.commons.core.Messages;
import seedu.taskscheduler.commons.exceptions.IllegalValueException;
import seedu.taskscheduler.model.task.Task;
import seedu.taskscheduler.model.task.UniqueTaskList.TaskNotFoundException;

//@@author A0138696L

/**
 * Unmarks a task in task scheduler as uncompleted.
 */
public class UnmarkCommand extends Command {
    
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the task identified by the index number used in the last tasks listing as uncompleted.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    
    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Un-Completed Task: %1$s";
    public static final String MESSAGE_UNMARK_TASK_FAIL = "This task is not completed.";

    private final int targetIndex;
    private Task taskToUnmark;
    
    public UnmarkCommand() {
        this(EMPTY_INDEX);
    }
    
    public UnmarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }
    
    @Override
    public CommandResult execute() {
        
        try {
            taskToUnmark = (Task) getTaskFromIndexOrLastModified(targetIndex);
            model.unmarkTask(taskToUnmark);
            CommandHistory.addExecutedCommand(this);
            CommandHistory.setModifiedTask(taskToUnmark);
        } catch (IllegalValueException npe) {
            return new CommandResult(MESSAGE_UNMARK_TASK_FAIL);
        } catch (TaskNotFoundException tnfe) {
            return new CommandResult(tnfe.getMessage()); 
        }
        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, taskToUnmark)); 
    }

    @Override
    public CommandResult revert() {
        try {
            model.markTask(taskToUnmark);
            CommandHistory.addRevertedCommand(this);
            CommandHistory.setModifiedTask(taskToUnmark);
        } catch (IllegalValueException e) {
            return new CommandResult(MarkCommand.MESSAGE_MARK_TASK_FAIL);
        } catch (TaskNotFoundException pnfe) {
            assert false : Messages.MESSAGE_TASK_CANNOT_BE_MISSING;
        } 
        return new CommandResult(String.format(MESSAGE_REVERT_COMMAND, COMMAND_WORD, "\n" + taskToUnmark));
    }
}
