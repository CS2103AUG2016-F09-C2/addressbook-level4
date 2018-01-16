package seedu.taskscheduler.model.task;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.taskscheduler.commons.core.EventsCenter;
import seedu.taskscheduler.commons.events.ui.JumpToListRequestEvent;
import seedu.taskscheduler.commons.exceptions.DuplicateDataException;
import seedu.taskscheduler.commons.exceptions.IllegalValueException;
import seedu.taskscheduler.commons.util.CollectionUtil;
import seedu.taskscheduler.model.tag.UniqueTagList;
import seedu.taskscheduler.model.tag.UniqueTagList.DuplicateTagException;
import seedu.taskscheduler.model.task.ReadOnlyTask.TaskType;
/**
 * A list of tasks that enforces uniqueness between its elements and does not allow nulls.
 *
 * Supports a minimal set of list operations.
 *
 * @see Task#equals(Object)
 * @see CollectionUtil#elementsAreUnique(Collection)
 */
public class UniqueTaskList implements Iterable<Task> {

    public static final int FIRST_INDEX = 0;
    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTaskException extends DuplicateDataException {
        protected DuplicateTaskException() {
            super("Operation would result in duplicate tasks");
        }
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching task in the list.
     */
    public static class TaskNotFoundException extends Exception {
        public TaskNotFoundException() {
            
        }
        public TaskNotFoundException(String message) {
            super(message);
        }
    }

    private final ObservableList<Task> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent task as the given argument.
     */
    public boolean contains(ReadOnlyTask toCheck) {
        assert toCheck != null;
        return internalList.contains(toCheck);
    }

    //@@author A0148145E
    /**
     * Adds a task to the list.
     *
     * @throws DuplicateTaskException if the task to add is a duplicate of an existing task in the list.
     */
    public void add(Task toAdd) throws DuplicateTaskException {
        assert toAdd != null;
        if (contains(toAdd)) {
            throw new DuplicateTaskException();
        }
        int index = indexToInsertInSortedOrder(toAdd);
        internalList.add(index, toAdd);
        EventsCenter.getInstance().post(new JumpToListRequestEvent(index));
    }
    
    //@@author A0148145E
    /**
     * Determines the sorted position of the toAdd in the list
     * @param toAdd
     * @return
     */
    private int indexToInsertInSortedOrder(Task toAdd) {
        for (int i = FIRST_INDEX; i < internalList.size(); i++) {
            if (compareTaskOrder(toAdd, internalList.get(i))) {
                return i;
            }
        }
        // else add to the back
        return internalList.size();
    }
    
    /**
     * Compares the task in terms of end time and name's lexicographic order
     * @return true if {@code task} < {@code other} else false
     */
    private boolean compareTaskOrder(Task task, Task other) {
        return task.isBefore(other) || (task.isSameTime(other) 
                && task.getName().isLexicoSmaller(other.getName()));
    }

    //@@author A0148145E
    /**
     * Replaces a task in the list.
     *
     * @throws DuplicateTaskException if the task to replace is a duplicate of an existing task in the list.
     */
    public void replace(Task oldTask, Task newTask) throws DuplicateTaskException, TaskNotFoundException{
        assert oldTask != null;
        if (contains(newTask)) {
            throw new DuplicateTaskException();
        }
        int index = internalList.indexOf(oldTask);
        if (index < FIRST_INDEX) {
            throw new TaskNotFoundException();
        }
        internalList.remove(index);
        index = indexToInsertInSortedOrder(newTask);
        internalList.add(index, newTask);
        EventsCenter.getInstance().post(new JumpToListRequestEvent(index));
    }

    //@@author A0148145E
    /**
     * Marks a task to the list as completed.
     *
     * @throws TaskNotFoundException
     * @throws IllegalValueException if the task is already complete.
     */
    public void mark(Task toMark) throws TaskNotFoundException, IllegalValueException{
        assert toMark != null;
        int index = internalList.indexOf(toMark);
        if (index < FIRST_INDEX) {
            throw new TaskNotFoundException();
        }
        toMark.markComplete();
        internalList.set(index, toMark);
        EventsCenter.getInstance().post(new JumpToListRequestEvent(index));
    }
    

    //@@author A0148145E
    /**
     * Unmarks a task to the list as completed.
     *
     * @throws TaskNotFoundException
     * @throws DuplicateTagException if the task is already complete.
     */
    public void unmark(Task toMark) throws TaskNotFoundException, IllegalValueException {
        assert toMark != null;
        int index = internalList.indexOf(toMark);
        if (index < FIRST_INDEX) {
            throw new TaskNotFoundException();
        }
        toMark.unmarkComplete();
        internalList.set(index, toMark);
        EventsCenter.getInstance().post(new JumpToListRequestEvent(index));
    }
    
  //@@author A0148145E
    /**
     * Replaces the tag list of a task in the list.
     *
     * @throws TaskNotFoundException
     */
    public void tagTask(Task task, UniqueTagList tagList) throws TaskNotFoundException {
        assert task != null;
        int index = internalList.indexOf(task);
        if (index < FIRST_INDEX) {
            throw new TaskNotFoundException();
        }
        task.setTags(tagList);
        internalList.set(index, task);
        EventsCenter.getInstance().post(new JumpToListRequestEvent(index));
    }
    
    
    //@@author A0140007B
    /**
     * Inserts a task into another task's position in the list.
     *
     * @throws TaskNotFoundException
     */
    public void insert(int index, Task newTask) throws TaskNotFoundException {
        assert newTask != null;
        assert index > FIRST_INDEX;
        internalList.add(index-1, newTask);
    }
    //@@author
    
    /**
     * Removes the equivalent task from the list.
     *
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public boolean remove(ReadOnlyTask toRemove) throws TaskNotFoundException {
        assert toRemove != null;
        final boolean taskFoundAndDeleted = internalList.remove(toRemove);
        if (!taskFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
        return taskFoundAndDeleted;
    }

    public ObservableList<Task> getInternalList() {
        return internalList;
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTaskList // instanceof handles nulls
                && this.internalList.equals(
                ((UniqueTaskList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
