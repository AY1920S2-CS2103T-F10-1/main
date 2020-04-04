package seedu.address.ui.personbio;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.MainApp;
import seedu.address.model.item.Person;
import seedu.address.ui.UiPart;

/**
 * The entire portion that displays user's personal details
 */
public class UserOverallPane extends UiPart<Region> {
    private static final String FXML = "UserOverallPane.fxml";
    private static String defaultProfilePicPath = "/images/Duke.png";

    private UserProfilePane userProfile;
    private Image profilePic;
    private UserDetailPane userDetail;
    private Person user;
    private String profilePicPath;

    @FXML
    private HBox userProfilePlaceholder;

    @FXML
    private VBox userDetailPlaceholder;

    /**
    * Constructs the entire person pane with UserProfile and UserDetail.
    */
    public UserOverallPane(Person user) {
        super(FXML);
        this.user = user;

        String dp = this.user.getDisplayPicture().toString();
        String name = this.user.getName().toString();
        String phone = this.user.getPhone().toString();
        String email = this.user.getEmail().toString();
        String github = this.user.getGithub().toString();
        String university = this.user.getUniversity();
        String major = this.user.getMajor();
        String from = this.user.getFrom().toString();
        String to = this.user.getTo().toString();
        String cap = String.valueOf(this.user.getCap());

        File imageFile = new File(dp);
        if (imageFile.exists()) {
            profilePic = new Image(imageFile.toURI().toString());
        } else {
            profilePic = new Image(MainApp.class.getResourceAsStream(defaultProfilePicPath));
        }

        userProfile = new UserProfilePane(profilePic, name,
                "What if Newton discovered gravity from a durian?");
        userProfilePlaceholder.getChildren().add(userProfile.getRoot());

        userDetail = new UserDetailPane(dp, name, phone, email, github, university, major, from, to, cap);
        userDetailPlaceholder.getChildren().add(userDetail.getRoot());
    }

    /**
     * Update UI if there is any changes to the user profile.
     * @param updatedUser
     */
    public void updateUserProfile(Person updatedUser) {
        userDetailPlaceholder.getChildren().clear();
        userProfilePlaceholder.getChildren().clear();
        String dp = updatedUser.getDisplayPicture().toString();
        String name = updatedUser.getName().toString();
        String phone = updatedUser.getPhone().toString();
        String email = updatedUser.getEmail().toString();
        String github = updatedUser.getGithub().toString();
        String university = updatedUser.getUniversity();
        String major = updatedUser.getMajor();
        String from = updatedUser.getFrom().toString();
        String to = updatedUser.getTo().toString();
        String cap = String.valueOf(updatedUser.getCap());

        File imageFile = new File(dp);

        if (!imageFile.exists()) {

        } else {
            profilePicPath = imageFile.toURI().toString();
            profilePic = new Image(profilePicPath);
        }

        userDetailPlaceholder.getChildren().add(new UserDetailPane(dp, name, phone, email, github, university,
                 major, from, to, cap).getRoot());
        UserProfilePane userProfilePane = new UserProfilePane(profilePic, name,
                "What if Newton discovered gravity from a durian?");
        userProfilePlaceholder.getChildren().add(userProfilePane.getRoot());
    }
}
