package seedu.address.logic.commands.results;

/**
 * Represents the result of a Redo command execution.
 */
public class RedoCommandResult extends CommandResult {

    // This is to trigger a view box refresh
    public static final String DEFAULT_DATA_TO_USER = " ";

    /**
     * Constructs a {@code RedoCommandResult} with the specified {@code feedbackToUser} and
     * {@code displayType}.
     * @param feedbackToUser feedback to user.
     * @param displayType the alias of the item type.
     */
    public RedoCommandResult(String feedbackToUser, String displayType) {
        super(DEFAULT_DATA_TO_USER, feedbackToUser, displayType);
        super.isShowPreview = false;
        super.isGenerate = false;
        super.isShowHelp = false;
        super.isExit = false;
    }
}
