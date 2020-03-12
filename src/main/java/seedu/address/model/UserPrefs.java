package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");

    private Path achievementFilePath = Paths.get("data", "achievement.json");
    private Path educationFilePath = Paths.get("data", "education.json");
    private Path internshipFilePath = Paths.get("data", "internship.json");
    private Path personalDetailFilePath = Paths.get("data", "personaldetail.json");
    private Path projectFilePath = Paths.get("data", "project.json");
    private Path resumeFilePath = Paths.get("data", "resume.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setResumeBookFilePath(newUserPrefs.getResumeBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }




    public ArrayList<Path> getAllFilePaths() {
        ArrayList<Path> paths = new ArrayList<>();
        paths.add(addressBookFilePath);
        paths.add(achievementFilePath);
        paths.add(educationFilePath);
        paths.add(internshipFilePath);
        paths.add(personalDetailFilePath);
        paths.add(projectFilePath);
        paths.add(resumeFilePath);
        return paths;
    }

    public Path getResumeBookFilePath() {

        return addressBookFilePath;
    }

    public void setResumeBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        return sb.toString();
    }

}
