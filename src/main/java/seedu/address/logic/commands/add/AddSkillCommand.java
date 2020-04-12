package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.AddCommandResult;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Skill;

/**
 * Adds a Skill Item to the address book.
 */
public class AddSkillCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a skill to the address book.\n"
            + COMMAND_WORD + " "
            + PREFIX_ITEM + "TYPE "
            + PREFIX_NAME + "SKILL NAME "
            + PREFIX_LEVEL + "LEVEL "
            + "[" + PREFIX_TAG + "TAG]....\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "ski "
            + PREFIX_NAME + "Git and Github "
            + PREFIX_LEVEL + "INTERMEDIATE "
            + PREFIX_TAG + "VCS "
            + PREFIX_TAG + "tech";

    private final Skill toAdd;

    /**
     * Creates an AddCommand to add the specified {@code skill}.
     */
    public AddSkillCommand(Skill skill) {
        requireNonNull(skill);
        toAdd = skill;
    }

    /**
     * Adds {@code toAdd} skill to model.
     *
     * @param model {@code Model} which skill will be added.
     * @return      {@code CommandResult} that describes changes made when command execute runs successfully.
     * @throws      CommandException if adding the model results in duplicate skills.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasSkill(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        model.addSkill(toAdd);
        model.setSkillToDisplay();
        model.commitResumeBook();

        return new AddCommandResult(toAdd.toString(),
                String.format(MESSAGE_SUCCESS, toAdd.getType().getFullType()),
                model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddSkillCommand // instanceof handles nulls
                && toAdd.equals(((AddSkillCommand) other).toAdd));
    }
}
