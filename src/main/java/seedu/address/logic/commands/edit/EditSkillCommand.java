package seedu.address.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.EditCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Skill;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.item.field.Level;
import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * Edits a Skill Item in the resume book.
 * TODO: CONNECT LEVEL TO SKILL
 */
public class EditSkillCommand extends EditCommand {

    public static final String MESSAGE_EDIT_SKILL_SUCCESS = "Edited Skill: %1$s";

    private static final String FIELDS = COMMAND_WORD
            + " INDEX "
            + PREFIX_ITEM + "res "
            + "[" + PREFIX_NAME + "SKILL NAME] "
            + "[" + PREFIX_LEVEL + "LEVEL] "
            + "[" + PREFIX_TAG + "TAG]....\n";
    private static final String EXAMPLE = "Example: "
            + COMMAND_WORD + " 1 "
            + PREFIX_ITEM + " ski "
            + PREFIX_NAME + " Software Engineering";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.\n" + FIELDS + EXAMPLE;

    private EditSkillDescriptor editSkillDescriptor;
    /**
     * @param index index of the skill in the filtered skill list to edit
     * @param editSkillDescriptor details to edit the skill with
     */
    public EditSkillCommand(Index index, EditSkillDescriptor editSkillDescriptor) {
        super(index);
        this.editSkillDescriptor = editSkillDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getSkillSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Skill toEdit = model.getSkillByIndex(index);

        Skill editedSkill = createEditedSkill(toEdit, editSkillDescriptor);

        try {
            model.setSkill(toEdit, editedSkill);
            model.setSkillToDisplay();
            model.commitResumeBook();
        } catch (DuplicateItemException e) {
            throw new CommandException(MESSAGE_DUPLICATE_ITEM);
        }

        return new EditCommandResult(editedSkill.toString(),
                String.format(MESSAGE_EDIT_SKILL_SUCCESS, editedSkill),
                model.getDisplayType());
    }

    /**
     * Creates the edited Skill item from the skill to be edited and the descriptor.
     * @param toEdit Skill item to be edited
     * @param editSkillDescriptor Descriptor parsed from input of user
     * @return Edited Skill item.
     */
    public static Skill createEditedSkill(Skill toEdit, EditSkillDescriptor editSkillDescriptor) {
        Name updatedName = editSkillDescriptor.getName().orElse(toEdit.getName());
        Level level = editSkillDescriptor.getLevel().orElse(toEdit.getLevel());
        Set<Tag> updatedTags = editSkillDescriptor.getTags().orElse(toEdit.getTags());
        int id = toEdit.getId();
        return new Skill(updatedName, level, updatedTags, id);
    }
}
