package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.NoteEntry;

public class AddNoteCommand extends AddCommand {
//    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_SUCCESS = "New note added!";

    public static final String MESSAGE_DUPLICATE_ERROR = "This note already exists!";

    private final NoteEntry toAdd;

    public AddNoteCommand(NoteEntry note) {
        requireNonNull(note);
        this.toAdd = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasNoteEntry(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ERROR);
        }
        model.addNoteEntry(toAdd);
        return new CommandResult(toAdd.toString(), MESSAGE_SUCCESS, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddNoteCommand)
                && toAdd.equals(((AddNoteCommand) other).toAdd);
    }
}