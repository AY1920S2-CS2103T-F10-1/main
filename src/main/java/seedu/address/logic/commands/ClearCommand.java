package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.results.ClearCommandResult;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ResumeBook;
import seedu.address.model.VersionedResumeBook;

/**
 * Clears the resume book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Resume book has been cleared!";

    /**
     * Clears the Resume Book in {@code model}
     *
     * @param model {@code Model} in which data will be cleared.
     * @return      {@code CommandResult} that describes changes made when command execute runs successfully.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String type = model.getDisplayType();
        model.setResumeBook(new VersionedResumeBook(new ResumeBook()));
        model.setItemsToDisplay(type);
        model.commitResumeBook();
        return new ClearCommandResult(" ", MESSAGE_SUCCESS, model.getDisplayType());
    }
}
