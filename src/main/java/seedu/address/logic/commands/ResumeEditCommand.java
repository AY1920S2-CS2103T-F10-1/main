package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Resume;

/**
 * Edits the content of a Resume.
 */
public class ResumeEditCommand extends Command {
    public static final String COMMAND_WORD = "redit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits what an existing resume contains in the ResuMe "
            + "application with the specified index.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX "
            + "TYPE/ [ITEM_INDEX] "
            + "[MORE_TYPE/ [ITEM_ID...]]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_INTERNSHIP + " 1 3 4 "
            + PREFIX_SKILL + " 1 2 "
            + PREFIX_PROJECT + " 1 ";

    protected final Index index;
    protected final Optional<List<Integer>> internshipIndices;
    protected final Optional<List<Integer>> projectIndices;
    protected final Optional<List<Integer>> skillIndices;

    public ResumeEditCommand(Index index, Optional<List<Integer>> internshipIndices,
                             Optional<List<Integer>> projectIndices, Optional<List<Integer>> skillIndices) {
        this.index = index;
        this.internshipIndices = internshipIndices;
        this.projectIndices = projectIndices;
        this.skillIndices = skillIndices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        checkIndicesValidity(model);

        Resume toEdit = model.getResumeByIndex(index);

        List<Integer> internshipsId = toEdit.getInternshipIds();
        List<Integer> projectsId = toEdit.getProjectIds();
        List<Integer> skillsId = toEdit.getSkillIds();

        // If any of the indices are present (user keys in the prefix), then use what the user uses
        // Else, use the one currently being used by the resume

        if (internshipIndices.isPresent()) {
            internshipsId = internshipIndices
                    .get()
                    .stream()
                    .distinct()
                    .map(x -> model.getInternshipByIndex(Index.fromOneBased(x)).getId())
                    .collect(Collectors.toList());
        }

        if (projectIndices.isPresent()) {
            projectsId = projectIndices
                    .get()
                    .stream()
                    .distinct()
                    .map(x -> model.getProjectByIndex(Index.fromOneBased(x)).getId())
                    .collect(Collectors.toList());
        }

        if (skillIndices.isPresent()) {
            skillsId = skillIndices
                    .get()
                    .stream()
                    .distinct()
                    .map(x -> model.getSkillByIndex(Index.fromOneBased(x)).getId())
                    .collect(Collectors.toList());
        }

        Resume editedResume = new Resume(toEdit.getName(), toEdit.getId(), toEdit.getTags());
        model.editResume(editedResume, internshipsId, projectsId, skillsId);
        model.setResume(toEdit, editedResume);
        model.setResumeToDisplay();
        model.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        model.commitResumeBook();
        return new CommandResult(toEdit.toString(), "Resume is updated", model.getDisplayType());
    }

    /**
     * Checks for whether the specified indices are valid.
     */
    private void checkIndicesValidity(Model model) throws CommandException {
        // Internships
        if (internshipIndices.isPresent()) {
            List<Integer> unboxedIndices = internshipIndices.get();
            StringBuilder invalidIndices = new StringBuilder();
            boolean isInvalidIndexPresent = false;
            for (Integer i: unboxedIndices) {
                if (Index.fromOneBased(i).getZeroBased() >= model.getInternshipSize()) {
                    isInvalidIndexPresent = true;
                    invalidIndices.append(i.toString()).append(" ");
                }
            }

            if (isInvalidIndexPresent) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "internship",
                        invalidIndices.toString().trim()));
            }
        }

        // Projects
        if (projectIndices.isPresent()) {
            List<Integer> unboxedIndices = projectIndices.get();
            StringBuilder invalidIndices = new StringBuilder();
            boolean isInvalidIndexPresent = false;
            for (Integer i: unboxedIndices) {
                if (Index.fromOneBased(i).getZeroBased() >= model.getProjectSize()) {
                    isInvalidIndexPresent = true;
                    invalidIndices.append(i.toString()).append(" ");
                }
            }

            if (isInvalidIndexPresent) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "project",
                        invalidIndices.toString().trim()));
            }
        }

        // Skills
        if (skillIndices.isPresent()) {
            List<Integer> unboxedIndices = skillIndices.get();
            StringBuilder invalidIndices = new StringBuilder();
            boolean isInvalidIndexPresent = false;
            for (Integer i: unboxedIndices) {
                if (Index.fromOneBased(i).getZeroBased() >= model.getSkillSize()) {
                    isInvalidIndexPresent = true;
                    invalidIndices.append(i.toString()).append(" ");
                }
            }

            if (isInvalidIndexPresent) {
                throw new CommandException(String.format(Messages.MESSAGE_INVALID_REDIT_ITEM_INDEX, "skill",
                        invalidIndices.toString().trim()));
            }
        }
    }
}
