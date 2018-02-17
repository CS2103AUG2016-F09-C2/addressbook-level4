package seedu.taskscheduler.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import seedu.taskscheduler.commons.core.LogsCenter;
import seedu.taskscheduler.model.task.ReadOnlyTask;
import seedu.taskscheduler.model.task.ReadOnlyTask.TaskType;

import java.util.logging.Logger;

//@@author A0148145E

/**
 * Panel containing the list of priority tasks.
 */
public class PriorityListPanel extends UiPart {
    private final Logger logger = LogsCenter.getLogger(PriorityListPanel.class);
    private static final String FXML = "PriorityListPanel.fxml";
    private VBox panel;
    private AnchorPane placeHolderPane;

    private ObservableList<ReadOnlyTask> originalList;
    
    @FXML
    private ListView<ReadOnlyTask> priorityListView;

    public PriorityListPanel() {
        super();
    }

    @Override
    public void setNode(Node node) {
        panel = (VBox) node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }

    @Override
    public void setPlaceholder(AnchorPane pane) {
        this.placeHolderPane = pane;
    }

    public static PriorityListPanel load(Stage primaryStage, AnchorPane priorityListPlaceholder,
                                       ObservableList<ReadOnlyTask> taskList) {
        PriorityListPanel priorityListPanel =
                UiPartLoader.loadUiPart(primaryStage, priorityListPlaceholder, new PriorityListPanel());
        priorityListPanel.configure(taskList);
        return priorityListPanel;
    }

    private void configure(ObservableList<ReadOnlyTask> taskList) {
        setConnections(taskList);
        addToPlaceholder();
    }

    private void setConnections(ObservableList<ReadOnlyTask> taskList) {
        originalList = taskList;
        priorityListView.setItems(taskList.filtered(b -> b.getType() == TaskType.FLOATING));
        priorityListView.setCellFactory(listView -> new TaskListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void addToPlaceholder() {
        SplitPane.setResizableWithParent(placeHolderPane, false);
        placeHolderPane.getChildren().add(panel);
    }

    private void setEventHandlerForSelectionChangeEvent() {
        priorityListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                logger.fine("Selection in priority list panel changed to : '" + newValue + "'");
            }
        });
    }

    public void scrollTo(int index) {
        Platform.runLater(() -> {
            priorityListView.scrollTo(index);
            priorityListView.getSelectionModel().clearAndSelect(index);
        });
    }

    public void requestFocus() {
        priorityListView.requestFocus();
    }
    
    class TaskListViewCell extends ListCell<ReadOnlyTask> {

        @Override
        protected void updateItem(ReadOnlyTask task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null || task.hasCompleted()) {
                setGraphic(null);
                setText(null);
                setManaged(false);
            } else {
                setGraphic(TaskCard.load(task, originalList.indexOf(task) + 1).getLayout());
            }
        }
    }

}
