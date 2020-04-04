package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

/**
 * Adds a Resume Item to the address book.
 */
public class AddResumeCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a resume to the address book.\n"
            + COMMAND_WORD + " "
            + PREFIX_ITEM + "TYPE "
            + PREFIX_NAME + "NAME "
            + "[" + PREFIX_TAG + "TAG]....\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "res "
            + PREFIX_NAME + "Resume 1 "
            + PREFIX_TAG + "frontend "
            + PREFIX_TAG + "tech";

    private final Resume toAdd;

    /**
     * Creates an AddCommand to add the specified {@code resume}
     */
    public AddResumeCommand(Resume resume) {
        requireNonNull(resume);
        toAdd = resume;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasResume(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addResume(toAdd);
        model.setResumeToDisplay();
        model.commitResumeBook();

        return new CommandResult(toAdd.toString(),
                String.format(MESSAGE_SUCCESS, toAdd.getType().getFullType()),
                model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddResumeCommand // instanceof handles nulls
                && toAdd.equals(((AddResumeCommand) other).toAdd));
    }
}
