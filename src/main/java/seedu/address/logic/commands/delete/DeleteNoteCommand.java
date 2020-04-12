package seedu.address.logic.commands.delete;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.DeleteCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Note;

/**
 * Deletes a Note from the note list.
 */
public class DeleteNoteCommand extends DeleteCommand {

    public DeleteNoteCommand(Index targetIndex) {
        super(targetIndex);
    }

    /**
     * Deletes note at {@code targetIndex}.
     *
     * @param model {@code Model} that the note will be deleted from.
     * @return      {@code CommandResult} that describes changes made when command execute runs successfully.
     * @throws      CommandException if {@code targetIndex} is out of bounds.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getNoteListSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Note toDelete = model.getNoteByIndex(targetIndex);

        model.deleteNote(toDelete);
        model.updateFilteredNoteList(PREDICATE_SHOW_ALL_ITEMS);
        model.commitResumeBook();

        return new DeleteCommandResult(toDelete.toString(),
                String.format(MESSAGE_DELETE_ITEM_SUCCESS, "Note"), model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteNoteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteNoteCommand) other).targetIndex)); // state check
    }
}
