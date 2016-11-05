package seedu.taskscheduler.commons.events.storage;

import seedu.taskscheduler.commons.events.BaseEvent;

//@@author A0138696L

/**
* Indicates an execution of a Importing File
*/
public class ImportFilePathEvent extends BaseEvent {
    private final String path;
    
    public ImportFilePathEvent(String path) {
        this.path = path;
    }
    
    @Override
    public String toString() {
        return this.path;
    }
}