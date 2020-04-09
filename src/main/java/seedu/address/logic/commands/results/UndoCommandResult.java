package seedu.address.logic.commands.results;

/**
 * Represents the result of a Undo command execution.
 */
public class UndoCommandResult extends CommandResult {

    // This is to trigger a view box refresh
    public static final String DEFAULT_DATA_TO_USER = " ";

    /**
     * Constructs a {@code UndoCommandResult} with the specified {@code feedbackToUser} and
     * {@code displayType}.
     * @param feedbackToUser feedback to user.
     * @param displayType the alias of the item type.
     */
    public UndoCommandResult(String feedbackToUser, String displayType) {
        super(DEFAULT_DATA_TO_USER, feedbackToUser, displayType);
        super.isShowPreview = false;
        super.isGenerate = false;
        super.isShowHelp = false;
        super.isExit = false;
    }
}
