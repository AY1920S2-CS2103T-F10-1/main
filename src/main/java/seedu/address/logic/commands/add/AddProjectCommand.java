package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEBSITE;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Project;

/**
 * Adds a Project Item to the address book.
 */
public class AddProjectCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an project to the address book.\n"
            + COMMAND_WORD + " "
            + PREFIX_ITEM + "TYPE "
            + PREFIX_NAME + "PROJECT NAME "
            + PREFIX_TIME + "TIME "
            + PREFIX_WEBSITE + "WEBSITE "
            + PREFIX_DESCRIPTION + "DESC "
            + "[" + PREFIX_TAG + "TAG]....\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "proj "
            + PREFIX_NAME + "Duke "
            + PREFIX_TIME + "06-2020 "
            + PREFIX_WEBSITE + "abc.github.io "
            + PREFIX_DESCRIPTION + "For a little module named CS2103T. "
            + PREFIX_TAG + "java "
            + PREFIX_TAG + "tech";

    private final Project toAdd;

    /**
     * Creates an AddCommand to add the specified {@code project}.
     */
    public AddProjectCommand(Project project) {
        requireNonNull(project);
        toAdd = project;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasProject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addProject(toAdd);
        model.setProjectToDisplay();
        model.commitResumeBook();

        return new CommandResult(toAdd.toString(),
                String.format(MESSAGE_SUCCESS, toAdd.getType().getFullType()),
                model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProjectCommand // instanceof handles nulls
                && toAdd.equals(((AddProjectCommand) other).toAdd));
    }
}
