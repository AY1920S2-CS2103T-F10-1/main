package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status box when there is any interaction with an item that is displayed at the body of the application.
 */
public class ItemDisplay extends UiPart<Region> {
    private static final String FXML = "ItemDisplay.fxml";

    @FXML
    private TextArea itemDisplay;

    public ItemDisplay() {
        super(FXML);
    }

    public void setDataFeedbackToUser(String dataToUser) {
        requireNonNull(dataToUser);
        itemDisplay.setText(dataToUser);
    }
}
