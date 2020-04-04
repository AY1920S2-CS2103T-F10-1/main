package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;

/**
 * Adds an Internship Item to the address book.
 */
public class AddInternshipCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an internship to the address book.\n"
            + COMMAND_WORD + " "
            + PREFIX_ITEM + "TYPE "
            + PREFIX_NAME + "COMPANY NAME "
            + PREFIX_ROLE + "ROLE "
            + PREFIX_FROM + "FROM "
            + PREFIX_TO + "TO "
            + PREFIX_DESCRIPTION + "DESC "
            + "[" + PREFIX_TAG + "TAG]....\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "int "
            + PREFIX_NAME + "Google "
            + PREFIX_ROLE + "Frontend Web Engineer "
            + PREFIX_FROM + "06-2020 "
            + PREFIX_TO + "12-2020 "
            + PREFIX_DESCRIPTION + "I did work, made money. "
            + PREFIX_TAG + "frontend "
            + PREFIX_TAG + "tech";

    private final Internship toAdd;

    /**
     * Creates an AddCommand to add the specified {@code internship}.
     */
    public AddInternshipCommand(Internship internship) {
        requireNonNull(internship);
        toAdd = internship;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInternship(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addInternship(toAdd);
        model.setInternshipToDisplay();
        model.commitResumeBook();

        return new CommandResult(toAdd.toString(),
                String.format(MESSAGE_SUCCESS, toAdd.getType().getFullType()),
                model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInternshipCommand // instanceof handles nulls
                && toAdd.equals(((AddInternshipCommand) other).toAdd));
    }
}
