package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.DoneCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Note;

/**
 * Marks a note as done.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_DONE_SUCCESS = "Marked this note as done!";
    public static final String MESSAGE_DONE_FAILURE = "This note is alread marked as done!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " INDEX \n"
            + "Example: done 1 ";

    protected final Index targetIndex;

    public DoneCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex.getZeroBased() >= model.getNoteListSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Note toSetDone = model.getNote(targetIndex);

        if (toSetDone.isDone()) {
            throw new CommandException(MESSAGE_DONE_FAILURE);
        }

        Note done = toSetDone.toCopy();
        done.markAsDone();
        model.setNote(toSetDone, done);
        model.commitResumeBook();

        return new DoneCommandResult(done.toString(), MESSAGE_DONE_SUCCESS, model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && targetIndex.equals(((DoneCommand) other).targetIndex)); // state check
    }
}
