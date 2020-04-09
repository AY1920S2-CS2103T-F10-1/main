package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.EditCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Note;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;

/**
 * Edits a specified Note.
 */
public class EditNoteCommand extends EditCommand {

    private static final String FIELDS = "Example: "
            + COMMAND_WORD + " "
            + PREFIX_ITEM + " note "
            + "[" + PREFIX_NAME + "NOTE NAME] "
            + "[" + PREFIX_TIME + "TIME] ";
    private static final String EXAMPLE = "Example: "
            + COMMAND_WORD + " 1 "
            + PREFIX_ITEM + " note "
            + PREFIX_NAME + " Complete Resume 3 "
            + PREFIX_TIME + " 04-2020 ";

    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.\n"
            + FIELDS
            + EXAMPLE;

    public static final String MESSAGE_EDIT_NOTE_SUCCESS = "Edited This Note!";

    private EditNoteDescriptor editNoteDescriptor;

    public EditNoteCommand(Index index, EditNoteDescriptor editNoteDescriptor) {
        super(index);
        this.editNoteDescriptor = editNoteDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getNoteListSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Note toEdit = model.getNote(index);

        Note editedNote = createEditedNoteEntry(toEdit, editNoteDescriptor);
        try {
            model.setNote(toEdit, editedNote);
            model.updateFilteredNoteList(PREDICATE_SHOW_ALL_ITEMS);
            model.commitResumeBook();
        } catch (DuplicateItemException e) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        return new EditCommandResult(editedNote.toString(), MESSAGE_EDIT_NOTE_SUCCESS, model.getDisplayType());
    }

    /**
     * Create a new Note after it has been edited.
     * @param toEdit the note that will be edited.
     * @param editNoteDescriptor describes how the note will be edited.
     * @return the edited note
     */
    public Note createEditedNoteEntry(Note toEdit, EditNoteDescriptor editNoteDescriptor) {
        Name updatedName = editNoteDescriptor.getName().orElse(toEdit.getName());
        Time updatedTime = editNoteDescriptor.getTime().orElse(toEdit.getTime());
        Set<Tag> updateTags = editNoteDescriptor.getTags().orElse(toEdit.getTags());
        int id = toEdit.getId();
        return new Note(updatedName, updatedTime, toEdit.isDone(), updateTags, id);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditNoteCommand)) {
            return false;
        }

        // state check
        EditNoteCommand e = (EditNoteCommand) other;
        return index.equals(e.index)
                && editNoteDescriptor.equals(e.editNoteDescriptor);
    }
}
