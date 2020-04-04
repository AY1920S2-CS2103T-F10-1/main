package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    // TODO: OOP to make more CommandResult classes

    private final String feedbackToUser;
    private final String dataToUser;
    private final String displayType;

    /** Preview information about a resume. */
    private final boolean isShowPreview;

    /** Generate .pdf file from a resume. */
    private final boolean isGenerate;

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** The application should exit. */
    private final boolean isExit;

    /**
     * Constructs a {@code CommandResult} with the specified {@code dataToUser} and {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String dataToUser, String feedbackToUser, String displayType) {
        this(dataToUser, feedbackToUser, displayType,
                false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     * @param dataToUser data to show user.
     * @param feedbackToUser feedback to user.
     * @param isShowPreview boolean value of whether the CommandResult is the result of a {@code rpreview} command.
     * @param isGenerate boolean value of whether the CommandResult is the result of a {@code rgen} command.
     * @param isShowHelp boolean value of whether the CommandResult is the result of a {@code help} command.
     * @param isExit boolean value of whether the CommandResult is the result of an {@code exit} command.
     */
    public CommandResult(String dataToUser, String feedbackToUser, String displayType, boolean isShowPreview,
                         boolean isGenerate, boolean isShowHelp, boolean isExit) {
        this.dataToUser = requireNonNull(dataToUser);
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.displayType = requireNonNull(displayType);
        this.isShowPreview = isShowPreview;
        this.isGenerate = isGenerate;
        this.isShowHelp = isShowHelp;
        this.isExit = isExit;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public String getDataToUser() {
        return dataToUser;
    }

    public String getDisplayType() {
        return this.displayType;
    }

    public boolean isShowPreview() {
        return isShowPreview;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean hasItemChanged() {
        return !dataToUser.equals("");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isShowHelp == otherCommandResult.isShowHelp
                && isExit == otherCommandResult.isExit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataToUser, feedbackToUser, isShowHelp, isExit);
    }

}
