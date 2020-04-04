package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a preview page
 */
public class PreviewWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(PreviewWindow.class);
    private static final String FXML = "PreviewWindow.fxml";

    @FXML
    private TextArea previewText;

    /**
     * Creates a new PreviewWindow.
     *
     * @param root Stage to use as the root of the PreviewWindow.
     */
    public PreviewWindow(Stage root) {
        super(FXML, root);
        previewText.setText("PREVIEW_MESSAGE");
    }

    /**
     * Creates a new PreviewWindow.
     */
    public PreviewWindow() {
        this(new Stage());
    }

    public void setPreviewText(String text) {
        previewText.setText(text);
    }

    /**
     * Shows the preview window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing resume preview page.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the preview window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the preview window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the preview window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
