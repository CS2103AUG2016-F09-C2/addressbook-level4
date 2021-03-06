package seedu.taskscheduler.testutil;

import seedu.taskscheduler.commons.util.CollectionUtil;
import seedu.taskscheduler.model.tag.UniqueTagList;
import seedu.taskscheduler.model.task.*;

//@@author A0148145E

/**
 * A mutable person object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private Location address;
    private TaskDateTime startDateTime;
    private TaskDateTime endDateTime;
    private UniqueTagList tags;
    private boolean completeStatus;
    private TaskType type;

    public TestTask() {
        tags = new UniqueTagList();
    }
    public TestTask(Name name, TaskDateTime startDateTime, TaskDateTime endDateTime, Location address, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, startDateTime, endDateTime, address, tags);
        this.name = name;
        this.startDateTime = new TaskDateTime(startDateTime);
        this.endDateTime = new TaskDateTime(endDateTime);
        this.address = address;
        this.tags = new UniqueTagList(tags);
        this.completeStatus = false;
    }
    public TestTask(ReadOnlyTask source) {
        this(source.getName(), source.getStartDate(), source.getEndDate(), source.getLocation(), source.getTags());
    }
    
    public void setName(Name name) {
        this.name = name;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public void setStartDate(TaskDateTime date) {
        this.startDateTime = date;
    }
    public void setEndDate(TaskDateTime date) {
        this.endDateTime = date;
    }


    @Override
    public Name getName() {
        return name;
    }

    @Override
    public TaskDateTime getStartDate() {
        return startDateTime;
    }

    @Override
    public TaskDateTime getEndDate() {
        return endDateTime;
    }

    @Override
    public Location getLocation() {
        return address;
    }
    
    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        return "add " + getTaskString();
    }
    
    public String getTaskString() {
        if (this.getType() == TaskType.FLOATING) {
            return getFloatingString();
        } else if (this.getType() == TaskType.DEADLINE) {
            return getDeadlineString();
        } else {
            return getEventString();
        }
    }
    
    private String getFloatingString() {
        return " " + this.getName().fullName;
        
    }
    
    private String getDeadlineString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" " + this.getName().fullName + " ");
        sb.append("by " + this.getEndDate());       
        return sb.toString();
    }

    private String getEventString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" " + this.getName().fullName + " ");
        sb.append("from " + this.getStartDate() + " ");
        sb.append("to " + this.getEndDate() + " ");
        sb.append("at" + " " + this.getLocation().value);
        return sb.toString();
    }
    

    public void addDuration(long duration) {
        if (startDateTime.getDate() != null)
            this.startDateTime.setDate(startDateTime.getDate().getTime() + duration + 1);
        if (endDateTime.getDate() != null)
            this.endDateTime.setDate(endDateTime.getDate().getTime() + duration + 1);
    }
    
    @Override
    public TestTask copy() {
        return new TestTask(this);
    }
    
    @Override
    public boolean hasCompleted() {
        return completeStatus;
    }
    
    @Override
    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }
    @Override
    public boolean isOverdue() {
        // TODO Auto-generated method stub
        return false;
    }
}
