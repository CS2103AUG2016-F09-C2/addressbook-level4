<!-- @@author A0148145E -->

# Manual Testing

* [Setting Up](#setting-up)
* [UI Quick Guide](#ui-quick-guide)
* [Get Started](#get-started)
 * [Adding tasks](#adding-tasks)
 * [Find tasks](#find-tasks)
 * [Delete tasks](#delete-tasks)
 * [Clear tasks](#clear-tasks)
 * [Edit tasks](#edit-tasks)
 * [Replace tasks](#replace-tasks)
 * [Mark tasks](#mark-tasks)
 * [Unmark tasks](#unmark-tasks)
 * [Recur tasks](#recur-tasks)
 * [Select tasks](#select-tasks)
 * [Undo commands](#undo-commands)
 * [Tag tasks](#tag-tasks)
 * [Import data](#import-data)
 * [Export data](#export-data)
 * [Shift data location](#shift-data-location)
 * [Help window](#help-window)
 * [Command Box](#command-box)
 * [Tag List](#tag-list)
 * [Pending List](#pending-list)
 * [Task List](#task-list)
 * [Exit the program](#exit-the-program)
* [Command Summary](#command-summary)

## Setting Up

0. Ensure you have Java version `1.8.0_60` or later installed in your Computer.<br>
   > Having any Java 8 version is not enough.<br>
   This app will not work with earlier versions of Java 8.
   
1. Download the latest `MustDoList.jar` from the [Releases](https://github.com/CS2103AUG2016-F09-C2/main/releases) 
   & SampleData.xml from [Here](https://raw.githubusercontent.com/CS2103AUG2016-F09-C2/main/test-script/src/test/data/ManualTesting/SampleData.xml)

2. Save both files to the same folder you want to use as the home folder for your MustDoList.

3. Double-click the MustDoList.jar to start the app. The GUI should appear in a few seconds. 
   > <img src="images/Ui.png" width="600">

4. Type the command in the command box and press <kbd>Enter</kbd> to execute it. <br>
   e.g. typing **`help`** and pressing <kbd>Enter</kbd> will open the help window. 
   If you need help understanding our UI, checkout our [UI Quick Guide](#ui-quick-guide)
   
5. To import the SampleData.xml, type "import FILE_LOCATION"
   e.g. `import SampleData.xml` if in same location as .jar file
   e.g. `import C:\V0.5\data\SampleData.xml` else specify the file location
   
6. The application will then restart, loaded with the SampleData.xml  <br>
   and using SampleData.xml file as default storage.

7. If error occur when importing, you can still run the SampleData.xml by renaming it to 
   taskscheduler.xml and putting it in a subfolder "data".  

[[Return to Top]](#manual-testing)

## UI Quick Guide

<img src="images/ui-taglist.png" width="400"><br>
<img src="images/ui-pendinglist.png" width="400"><br>
<img src="images/ui-statistics.png" width="400"><br>
<img src="images/ui-tasklist.png" width="400"><br>
<img src="images/ui-commandbox.png" width="400"><br>
<img src="images/ui-resultdisplay.png" width="400"><br>
<img src="images/ui-status.png" width="400"><br>

[[Return to Top]](#manual-testing)

---

## Get Started

### Steps to perform manul testing : 

#### The instructions will be in this format

<p>
<b>Command</b> : The command to type and press enter <br>
<b>Show</b> : <br>
Visual changes <br>
<b>Result</b> : <br>
Result in result display <br>
</p>

#### The instructions will be accompanied by an undo command 

To demostrate that all our commands (except for help, exit and import command) 
can be undo 

- Goes back to original 
means all data should return to the state before the previous command is executed

[[Return to Top]](#manual-testing)

---

### Adding tasks

<p>
<b>Command</b> : add SoC Event from 9 am to 12 pm at COM1-Level 2 <br>
<b>Show</b> : <br>
Highlights the task added in task list <br>
Pending task count increase by 1<br>
<b>Result</b> : <br>
New task added: SoC Event [TODAY'S DATE, DAY] 09:00 AM [TODAY'S DATE, DAY] 12:00 PM COM1-Level 2 <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
<b>Result</b> : <br>
Revert add command:<br>
SoC Event [TODAY'S DATE, DAY] 09:00 AM [TODAY'S DATE, DAY] 12:00 PM COM1-Level 2 <br>
</p>

<p>
<b>Command</b> : add submit CS2103 manual scripted testing by 7 Nov 23:59 <br>
<b>Show</b> : <br>
Highlights the task added in task list <br>
Pending task count increase by 1<br>
<b>Result</b> : <br>
New task added: submit CS2103 manual scripted testing  07-Nov-2016, Mon 11:59 PM<br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
<b>Result</b> : <br>
Revert add command: <br>
submit CS2103 manual scripted testing  07-Nov-2016, Mon 11:59 PM<br>
</p>

<p>
<b>Command</b> : add browse for new phone <br>
<b>Show</b> : <br>
Highlights the task added in task list <br>
Pending task count increase by 1<br>
<b>Result</b> : <br>
New task added: browse for new phone<br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
<b>Result</b> : <br>
Revert add command: <br>
browse for new phone<br>
</p>

[[Return to Top]](#manual-testing)

---

### Find tasks

<p>
<b>Command</b> : find event <br>
<b>Show</b> : <br>
Pending list and Task list will show the tasks matching the description <br>
Statistics update - should show 9 Completed, 16 Pending. Overdue is dependant on the date of testing <br>
<b>Result</b> : <br>
25 tasks listed! <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Result</b> : Goes back to original <br>
<b>Show</b> : <br>
Listed all tasks <br>
</p>

<p>
<b>Command</b> : find MA1506 <br>
<b>Show</b> : <br>
Pending list and Task list will show the tasks matching the description <br>
Statistics update - should show 2 Completed, 3 Pending. Overdue is dependant on the date of testing <br>
<b>Result</b> : <br>
5 tasks listed! <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original<br>
<b>Result</b> : <br> 
Listed all tasks <br>
</p>

<p>
<b>Command</b> : find floating <br>
<b>Show</b> : <br>
Pending list and Task list will show the tasks matching the description <br>
Statistics update - should show 0 Completed, 15 Pending, 0 Overdue <br>
<b>Result</b> : <br>
15 tasks listed!<br>
</p>

[[Return to Top]](#manual-testing)

#### List tasks

<p>
<b>Command</b> : list <br>
<b>Show</b> : <br>
Pending list and Task list will show all the tasks <br>
Statistics update - should show 9 Completed, 41 Pending. Overdue is dependant on the date of testing <br>
<b>Result</b> : <br>
Listed all tasks <br> 
</p>

[[Return to Top]](#manual-testing)

---

### Delete tasks

<p>
<b>Command</b> : delete 1 <br>
<b>Show</b> : <br> 
No. 1 Task removed from Task list <br>
Completed task count decrease by 1 <br>
<b>Result</b> : <br>
Deleted Task: EE2021 Lecture 19-Oct-2016, Wed 12:00 PM 19-Oct-2016, Wed 02:00 PM E3<br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
Highlights the No. 1 Task <br>
<b>Result</b> : <br>
Revert delete command: <br>
EE2021 Lecture 19-Oct-2016, Wed 12:00 PM 19-Oct-2016, Wed 02:00 PM E3 <br>
</p>

<p>
<b>Command</b> : delete 6 <br>
<b>Show</b> : <br>
Pending task count decrease by 1 <br>
No. 6 Task removed from Task and Pending list <br>
CS2103 tag removed from Tag list <br>
<b>Result</b> : <br> 
Deleted Task: V0.5rc dogfooding 21-Oct-2016, Fri 04-Nov-2016, Fri anywhere <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
CS2103 tag added back to tag list <br>
Deleted task added back to Task and Pending list <br>
Highlights the No. 6 Task <br>
<b>Result</b> : <br> 
Revert delete command:<br> 
V0.5rc dogfooding 21-Oct-2016, Fri 04-Nov-2016, Fri anywhere` <br>
</p>

[[Return to Top]](#manual-testing)

---

### Clear tasks

<p>
<b>Command</b> : clear <br>
<b>Show</b> : <br>
Completed, Pending, Overdue task count goes to 0 <br>
All tasks removed from Task and Pending list <br>
All tags removed from Tag list <br>
<b>Result</b> : <br> 
Task scheduler has been cleared! <br> 
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
<b>Result</b> : <br> 
Revert clear command: <br> 
EE2021 Lecture 19-Oct-2016, Wed 12:00 PM 19-Oct-2016, Wed 02:00 PM E3 <br>
... <br>
... <br>
... <br>
All the tasks that was deleted <br>
</p>

[[Return to Top]](#manual-testing)

---

### Edit tasks

<p>
<b>Command</b> : edit 6 V0.5 dogfooding by 7 Nov at everywhere <br>
<b>Show</b> : <br>
Highlights the editted task in task list <br>
Task's name changes to V0.5 dogfooding <br>
Task's due date changes to 07-Nov-2016, Mon <br>
Task's address changes to everywhere <br>
<b>Result</b> : <br>
Task editted: V0.5rc dogfooding 21-Oct-2016, Fri 04-Nov-2016, Fri anywhere <br>
Display the task details before edit for comparison <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
Highlights the No.6 task <br>
<b>Result</b> : <br> 
Revert edit command: <br>
V0.5 dogfooding 21-Oct-2016, Fri 07-Nov-2016, Mon everywhere <br>
</p>

<p>
<b>Command</b> : edit at everywhere <br>
<b>Show</b> : <br>
Highlights the No.6 task <br>
No.6 task's location changes to everywhere <br>
<b>Result</b> : <br> 
Task editted: V0.5rc dogfooding 21-Oct-2016, Fri 04-Nov-2016, Fri anywhere <br> 
Display the task details before edit for comparison <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
Highlights the No.6 task <br>
<b>Result</b> : <br>
Revert edit command: <br> 
V0.5 dogfooding 21-Oct-2016, Fri 07-Nov-2016, Mon everywhere <br>
</p>

[[Return to Top]](#manual-testing)

---

### Replace tasks 

<p>
<b>Command</b> : replace 1 EE2021 Exam on 29-Nov 9 am <br>
<b>Show</b> : <br>
Highlights the new task <br>
New task only contains name and due date <br>
Adds the new task to pending list <br>
Pending task count increase by 1 <br>
Completed task count decrease by 1 <br>
<b>Result</b> : <br> 
Task replaced: EE2021 Lecture 19-Oct-2016, Wed 12:00 PM 19-Oct-2016, Wed 02:00 PM E3 <br> 
Display the task details before replace for comparison <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
Highlights the No. 1 Task <br>
<b>Result</b> : <br>
Revert replace command: <br> 
EE2021 Exam  29-Nov-2016, Tue 09:00 AM <br>
</p>

[[Return to Top]](#manual-testing)

---

### Mark tasks

<p>
<b>Command</b> : mark 6 <br>
<b>Show</b> : <br>
Highlights the No. 6 task <br>
Star changes from Red to Green (Overdue to Completed) <br>
Removes the task from pending list <br>
Pending task count decrease by 1 <br>
Overdue task count decrease by 1 <br>
Completed task count increase by 1 <br>
<b>Result</b> : <br>
Completed Task: V0.5rc dogfooding 21-Oct-2016, Fri 04-Nov-2016, Fri anywhere <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
Highlights the No. 6 Task <br>
<b>Result</b> : <br> 
Revert mark command: <br> 
V0.5rc dogfooding 21-Oct-2016, Fri 04-Nov-2016, Fri anywhere <br>
</p>

<p>
<b>Command</b> : mark 1 <br>
<b>Show</b> : <br>
Nothing happens <br>
<b>Result</b> : <br>
This task is already completed. <br>
</p>

[[Return to Top]](#manual-testing)

---

### Unmark tasks

<p>
<b>Command</b> : unmark 3 <br>
<b>Show</b> : <br>
Highlights the No. 3 task <br>
Star changes from Green to Red (Completed to Overdue) <br>
Adds the task to pending list <br>
Pending task count increase by 1 <br>
Overdue task count increase by 1 <br>
Completed task count decrease by 1 <br>
<b>Result</b> : <br> 
Un-Completed Task: CS2103 Project 21-Oct-2016, Fri 09:00 AM 21-Oct-2016, Fri 10:00 AM COM1-B103 <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
Highlights the No. 3 Task <br>
<b>Result</b> : <br>
Revert unmark command: <br> 
CS2103 Project 21-Oct-2016, Fri 09:00 AM 21-Oct-2016, Fri 10:00 AM COM1-B103 <br>
</p>

<p>
<b>Command</b> : unmark 1 <br>
<b>Show</b> : <br>
Nothing happens <br>
<b>Result</b> : <br> 
This task is not completed. <br>
</p>

[[Return to Top]](#manual-testing)

---
### Recur tasks

<p>
<b>Command</b> : add weekly community service from 1st december at 2pm to 1st december at 4pm at community hub <br>
<b>Show</b> : <br>
Highlights the task added in task list <br>
Pending task count increase by 1<br>
<b>Result</b> : <br>
New task added: weekly community service 01-Dec-2016, Thu 02:00 PM 01-Dec-2016, Thu 04:00 PM community hub <br>
</p>

<p>
<b>Command</b> : recur every 1 week until 3 weeks from 1st december <br>
<b>Show</b> : <br>
Highlights the last recur task added in task list <br>
Pending task count increase by 3<br>
<b>Result</b> : <br>
Recur task added: <br>
weekly community service 08-Dec-2016, Thu 02:00 PM 08-Dec-2016, Thu 04:00 PM community hub <br>
weekly community service 15-Dec-2016, Thu 02:00 PM 15-Dec-2016, Thu 04:00 PM community hub <br>
weekly community service 22-Dec-2016, Thu 02:00 PM 22-Dec-2016, Thu 04:00 PM community hub <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
<b>Result</b> : <br>
Revert recur command: <br>
weekly community service 08-Dec-2016, Thu 02:00 PM 08-Dec-2016, Thu 04:00 PM community hub <br>
weekly community service 15-Dec-2016, Thu 02:00 PM 15-Dec-2016, Thu 04:00 PM community hub <br>
weekly community service 22-Dec-2016, Thu 02:00 PM 22-Dec-2016, Thu 04:00 PM community hub <br>
</p>

<p>
<b>Command</b> : recur 34 every 2 week until 3 weeks from 1st december <br>
<b>Show</b> : <br>
Highlights the last recur task added in task list <br>
Pending task count increase by 1<br>
<b>Result</b> : <br>
Recur task added: <br>
weekly community service 15-Dec-2016, Thu 02:00 PM 15-Dec-2016, Thu 04:00 PM community hub <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
<b>Result</b> : <br>
Revert recur command: <br>
weekly community service 15-Dec-2016, Thu 02:00 PM 15-Dec-2016, Thu 04:00 PM community hub <br>
</p>

[[Return to Top]](#manual-testing)

---

### Select tasks
  
<p>
<b>Command</b> : select 1 <br>
<b>Show</b> : <br>
Highlights the No.1 task in task list <br>
"add EE2021 Lecture from 19-Oct-2016, Wed 12:00 PM to 19-Oct-2016, Wed 02:00 PM at E3" appears in command box <br>
<b>Result</b> : <br>
Selected Task: 1 <br>
</p>

<p>
<b>Command</b> : select 50 <br>
<b>Show</b> : <br>
Highlights the No.50 task in task list <br>
"add Buy christmas presents online" appears in command box <br>
<b>Result</b> : <br>
Selected Task: 50 <br>
</p>
[[Return to Top]](#manual-testing)

---

### Undo commands

Success Tested along with other commands

No previous command scenario
<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Nothing happens <br>
<b>Result</b> : <br>
There is no previous command to undo! <br>
</p>

[[Return to Top]](#manual-testing)

---

### Tag tasks

<p>
<b>Command</b> : tag 50 Christmas Online <br>
<b>Show</b> : <br>
Highlights the No.50 task in task list <br>
Task 50 now have additional "Online" tag <br>
"Online" tag added to tag list <br>
<b>Result</b> : <br>
Tags: Buy christmas presents online <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Goes back to original <br>
Highlights the No.50 task in task list <br>
<b>Result</b> : <br>
Revert tag command: <br>
Buy christmas presents online <br>
</p>
[[Return to Top]](#manual-testing)

---

### Import data

Success Tested during setting up

File not found scenario
<p>
<b>Command</b> : import sample.xml <br>
<b>Show</b> : <br>
Nothing happens <br>
<b>Result</b> : <br>
File not found: sample.xml  <br>
import: valid file to Task Scheduler. Parameters: &lt;filename&gt; <br>
Example: import TaskSchedulerData <br>
</p>

[[Return to Top]](#manual-testing)

---

### Export data

<p>
<b>Command</b> : export new file.xml <br>
<b>Show</b> : <br>
new file.xml created in the same directory as jar file <br>
new file.xml contains all data <br>
<b>Result</b> : <br>
Successfully Exported data to: new file.xml <br>
</p>

<p>
<b>Command</b> : export C:\Dropbox\share file.xml <br>
<b>Show</b> : <br>
share file.xml created in the C:\Dropbox folder <br>
share file.xml contains all data <br>
<b>Result</b> : <br>
Successfully Exported data to: C:\Dropbox\share file.xml <br>
</p>
[[Return to Top]](#manual-testing)

---

### Shift data location

<p>
<b>Command</b> : setpath C:\Dropbox\new share file.xml <br>
<b>Show</b> : <br>
Rename and shift the data file to C:\Dropbox folder <br>
System now use the new share file.xml as default data file <br>
<br>
<b>Result</b> : <br>
File path changed: C:\Dropbox\new share file.xml <br>
</p>

<p>
<b>Command</b> : undo <br>
<b>Show</b> : <br>
Rename and shift the data file back to old location <br>
Goes back to original <br>
<br>
<b>Result</b> : <br>
File path changed: C:\Dropbox\new share file.xml <br>
</p>
[[Return to Top]](#manual-testing)

---

### Help window

<p>
<b>Command</b> : <kbd>Ctrl</kbd> + <kbd>F1</kbd> <br>
<b>Show</b> : <br>
Help window opens <br>
Shows online user guide (requires internet connection) <br>
<b>Result</b> : <br>
Opened help window. <br>
</p>

<p>
<b>Command</b> : help <br>
<b>Show</b> : <br>
Help window opens <br>
Shows online user guide (requires internet connection) <br>
<b>Result</b> : <br>
Opened help window. <br>
</p>
[[Return to Top]](#manual-testing)

---

### Command Box

<p>
<b>Command</b> : <kbd>Ctrl</kbd> + <kbd>4</kbd> <br>
<b>Show</b> : <br>
Focus command box <br>
</p>

<p>
<b>Command</b> : <kbd>Up</kbd> <br>
<b>Show</b> : <br>
Command Box shows previous entered commands <br>
</p>

<p>
<b>Command</b> : <kbd>Down</kbd> <br>
<b>Show</b> : <br>
Command Box shows next entered commands <br>
</p>

[[Return to Top]](#manual-testing)

---

### Tag List

<p>
<b>Command</b> : <kbd>Ctrl</kbd> + <kbd>1</kbd> <br>
<b>Show</b> : <br>
Focus tag list <br>
</p>

<p>
<b>Command</b> : <kbd>Down</kbd> <br>
<b>Show</b> : <br>
Highlights the next tag <br>
Pending and Task list shows only task with the highlighted tag <br>
</p>

<p>
<b>Command</b> : <kbd>Up</kbd> <br>
<b>Show</b> : <br>
Highlights the previous tag <br>
Pending and Task list shows only task with the highlighted tag <br>
</p>


[[Return to Top]](#manual-testing)

---

### Pending List

<p>
<b>Command</b> : <kbd>Ctrl</kbd> + <kbd>2</kbd> <br>
<b>Show</b> : <br>
Focus pending list <br>
</p>

<p>
<b>Command</b> : <kbd>Up</kbd> / <kbd>Down</kbd> <br>
<b>Show</b> : <br>
Scrolling through the pending list <br>
</p>

---

### Task List

<p>
<b>Command</b> : <kbd>Ctrl</kbd> + <kbd>3</kbd> <br>
<b>Show</b> : <br>
Focus task list <br>
</p>

<p>
<b>Command</b> : <kbd>Up</kbd> / <kbd>Down</kbd> <br>
<b>Show</b> : <br>
Scrolling through the task list <br>
</p>

[[Return to Top]](#manual-testing)

---

### Exit the program 

<p>
<b>Command</b> : exit <br>
<b>Show</b> : <br>
Application close <br>
</p>
[[Return to Top]](#manual-testing)

---

## Command Summary

* Help: `help`

* Add: **`add`**`FLOATING TASK NAME`<br>
**`add`**`TASK NAME by END_TIME END_DATE`<br> 
**`add`**`EVENT NAME from START_TIME_DATE to END_TIME_DATE at LOCATION`<br>
e.g. **`add`**`Do CS2103 Pretut`<br>
e.g. **`add`**`Do CS2103 Pretut by 8am 01-Oct-2016`<br>
e.g. **`add`**`CS2103 Tutorial from 8am today to 9am tomorrow at NUS COM1-B103`

* List: `list`

* Find: **`find`**`KEYWORD`<br>
e.g. **`find`**`CS2103`

* Delete: **`delete`**`[INDEX]`<br>
e.g. **`delete`**`1`

* Clear: `clear`

* Edit: **`edit`**`[INDEX] [EVENT_NAME][from START_TIME_DATE to END_TIME_DATE][at LOCATION]`<br>
e.g. **`edit`**`1 Must Do CS2103 Pretut`<br>
e.g. **`edit`**`2 at NUS COM1-B103`<br>
e.g. **`edit`**`1 from 8am 11-Oct-2016 to 9am 11-Oct-2016`

* Replace: **`replace`**`[INDEX] EVENT_NAME from START_TIME_DATE to END_TIME_DATE at LOCATION`<br>
e.g. **`replace`**`2 new task name from 8am 10-Oct-2016 to 9am 10-Oct-2016 at NUS`<br>

* Undo: `undo`

* Mark: **`mark`**`[INDEX]`<br>
e.g. **`mark`**`1`

* Unmark: **`unmark`**`[INDEX]`<br>
e.g. **`unmark`**`1`

* Recur: **`recur`**`[INDEX] every INTERVAL until END_DATE`<br>
e.g. **`recur`**`every 2 days until 19-Oct-2016`

* Setpath: **`setpath`**`FILENAME`<br>
e.g. **`setpath`**`taskData.xml`

* Select: **`select`**`INDEX`<br>
e.g. **`select`**`1`

* Tag: **`tag`**`[INDEX] TAG_NAME...`<br>
e.g. **`tag`**`1 project priority`

* Export: **`export`**`FILENAME`<br>
e.g. **`export`**`taskData.xml`

* Import: **`import`**`FILENAME`<br>
e.g. **`import`**`taskData.xml`

* Exit: `exit`

* <kbd>Up</kbd> <kbd>Down</kbd>: system display and select previously keyed commands

* ColorCode: system indicate overdue(red) and completed(green) task by color code

* Save: system save automatically

[[Return to Top]](#manual-testing)


