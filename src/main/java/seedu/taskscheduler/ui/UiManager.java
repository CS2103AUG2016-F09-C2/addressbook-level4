package seedu.taskscheduler.ui;

import com.google.common.eventbus.Subscribe;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import seedu.taskscheduler.MainApp;
import seedu.taskscheduler.commons.core.ComponentManager;
import seedu.taskscheduler.commons.core.Config;
import seedu.taskscheduler.commons.core.LogsCenter;
import seedu.taskscheduler.commons.events.storage.DataSavingExceptionEvent;
import seedu.taskscheduler.commons.events.ui.CommandBoxTextChangeRequestEvent;
import seedu.taskscheduler.commons.events.ui.JumpToListRequestEvent;
import seedu.taskscheduler.commons.events.ui.ShowHelpEvent;
import seedu.taskscheduler.commons.events.ui.TagPanelSelectionChangedEvent;
import seedu.taskscheduler.commons.events.ui.TaskPanelItemChangedEvent;
import seedu.taskscheduler.commons.events.ui.TaskPanelSelectionChangedEvent;
import seedu.taskscheduler.commons.util.StringUtil;
import seedu.taskscheduler.logic.Logic;
import seedu.taskscheduler.logic.commands.CommandHistory;
import seedu.taskscheduler.model.UserPrefs;

import java.util.EmptyStackException;
import java.util.logging.Logger;

/**
 * The manager of the UI component.
 */
public class UiManager extends ComponentManager implements Ui {
    private static final Logger logger = LogsCenter.getLogger(UiManager.class);
    private static final String ICON_APPLICATION = "/images/icon.png";

    private Logic logic;
    private Config config;
    private UserPrefs prefs;
    private MainWindow mainWindow;

    public UiManager(Logic logic, Config config, UserPrefs prefs) {
        super();
        this.logic = logic;
        this.config = config;
        this.prefs = prefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting UI...");
        primaryStage.setTitle(config.getAppTitle());

        //Set the application icon.
        primaryStage.getIcons().add(getImage(ICON_APPLICATION));

        try {
            mainWindow = MainWindow.load(primaryStage, config, prefs, logic);
            mainWindow.show(); //This should be called before creating other UI parts
            mainWindow.fillInnerParts();
            mainWindow.fillLabels();
            mainWindow.updateLabels(logic.getFilteredTaskList());
        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error during initializing", e);
        }
    }

    @Override
    public void stop() {
        prefs.updateLastUsedGuiSetting(mainWindow.getCurrentGuiSetting());
        mainWindow.hide();
        mainWindow.releaseResources();
    }

    private void showFileOperationAlertAndWait(String description, String details, Throwable cause) {
        final String content = details + ":\n" + cause.toString();
        showAlertDialogAndWait(AlertType.ERROR, "File Op Error", description, content);
    }

    private Image getImage(String imagePath) {
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    private void showAlertDialogAndWait(Alert.AlertType type, String title, String headerText, String contentText) {
        showAlertDialogAndWait(mainWindow.getPrimaryStage(), type, title, headerText, contentText);
    }

    private static void showAlertDialogAndWait(Stage owner, AlertType type, String title, String headerText,
                                               String contentText) {
        final Alert alert = new Alert(type);
        alert.getDialogPane().getStylesheets().add("view/DarkTheme.css");
        alert.initOwner(owner);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    private void showFatalErrorDialogAndShutdown(String title, Throwable e) {
        logger.severe(title + " " + e.getMessage() + StringUtil.getDetails(e));
        showAlertDialogAndWait(Alert.AlertType.ERROR, title, e.getMessage(), e.toString());
        Platform.exit();
        System.exit(1);
    }

    //==================== Event Handling Code =================================================================

    @Subscribe
    private void handleDataSavingExceptionEvent(DataSavingExceptionEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        showFileOperationAlertAndWait("Could not save data", "Could not save data to file", event.exception);
    }

    @Subscribe
    private void handleShowHelpEvent(ShowHelpEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.handleHelp();
    }

    @Subscribe
    private void handleJumpToListRequestEvent(JumpToListRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.getTaskListPanel().scrollTo(event.targetIndex);
    }
    

    //@@author A0140007B
    @Subscribe
    private void handleCommandBoxTextChangeRequestEvent(CommandBoxTextChangeRequestEvent event) {
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        mainWindow.getCommandBox().setCommandText(event.text);
    }
    //@@author

    @Subscribe
    private void handleTagPanelSelectionChangedEvent(TagPanelSelectionChangedEvent event){
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
        logic.execute("find " + event.getNewSelection().tagName, false);
        try {
            CommandHistory.getExecutedCommand();
        } catch (EmptyStackException ese) {
            logger.info(ese.getMessage());
        }   
        
    }
    
    @Subscribe
    private void handleTaskPanelSelectionChangedEvent(TaskPanelSelectionChangedEvent event){
        logger.info(LogsCenter.getEventHandlingLogMessage(event));
    }

    @Subscribe
    private void handleTaskPanelItemChangedEvent(TaskPanelItemChangedEvent event){
        mainWindow.updateLabels(event.getNewList());
    }

}
