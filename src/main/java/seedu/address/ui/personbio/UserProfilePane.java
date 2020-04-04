package seedu.address.ui.personbio;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * Ui for user profile with user picture, name and description.
 */
public class UserProfilePane extends UiPart<Region> {
    private static final String FXML = "UserProfilePane.fxml";

    @FXML
    private ImageView profilePicture;

    @FXML
    private Label name;

    @FXML
    private Label description;

    public UserProfilePane(Image profilePic, String name, String description) {
        super(FXML);
        this.profilePicture.setImage(profilePic);
        this.name.setText(name);
        this.description.setText(description);
    }

}
