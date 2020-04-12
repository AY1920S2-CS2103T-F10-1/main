package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.ResumePreviewCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;

/**
 * Previews a resume.
 */
public class ResumePreviewCommand extends Command {

    public static final String COMMAND_WORD = "rpreview";
    public static final String MESSAGE_SUCCESS = "Previewing resume at index %1$s successfully!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Previews the details of the resume identified "
            + "by the index number used in the displayed resume list. "
            + "Parameters: INDEX "
            + "Example: " + COMMAND_WORD + " 1 ";

    private Index index;

    public ResumePreviewCommand(Index index) {
        this.index = index;
    }

    /**
     * Previews resume at {@code targetIndex}.
     *
     * @param model {@code Model} where resume will be previewed.
     * @return      {@code CommandResult} that describes changes made when command execute runs successfully.
     * @throws      CommandException if {@code targetIndex} is out of bounds.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (index.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }

        Resume toPreview = model.getResumeByIndex(index);

        String data = getDataFromResume(toPreview, model);

        return new ResumePreviewCommandResult(data,
                String.format(MESSAGE_SUCCESS, index.getOneBased()), model.getDisplayType());
    }

    private String getDataFromResume(Resume resume, Model model) {
        List<String> internshipList = getStringListOfInternships(resume, model);
        List<String> projectList = getStringListOfProjects(resume, model);
        List<String> skillsList = getStringListOfSkills(resume, model);

        StringBuilder data = new StringBuilder(resume.getName() + "\n");
        data.append("=========================\n")
                .append("PERSONAL DETAILS\n")
                .append("=========================\n\n")
                .append(model.getUser().toPreview())
                .append("\n\n");
        data.append("=========================\n")
                .append("INTERNSHIPS\n")
                .append("=========================\n\n");
        for (String internship: internshipList) {
            data.append(internship).append("\n");
        }
        data.append("=========================\n")
                .append("PROJECTS\n")
                .append("=========================\n\n");
        for (String project: projectList) {
            data.append(project).append("\n");
        }
        data.append("=========================\n")
                .append("SKILLS\n")
                .append("=========================\n\n");
        for (String skill: skillsList) {
            data.append(skill).append("\n");
        }
        return data.toString();
    }

    private List<String> getStringListOfInternships(Resume resume, Model model) {
        return resume.getInternshipIds()
                .stream()
                .map(x -> model.hasInternshipId(x) ? model.getInternshipById(x) : null)
                .filter(Objects::nonNull)
                .map(Internship::toPreview)
                .collect(Collectors.toList());
    }

    private List<String> getStringListOfProjects(Resume resume, Model model) {
        return resume.getProjectIds()
                .stream()
                .map(x -> model.hasProjectId(x) ? model.getProjectById(x) : null)
                .filter(Objects::nonNull)
                .map(Project::toPreview)
                .collect(Collectors.toList());
    }

    private List<String> getStringListOfSkills(Resume resume, Model model) {
        return resume.getSkillIds()
                .stream()
                .map(x -> model.hasSkillId(x) ? model.getSkillById(x) : null)
                .filter(Objects::nonNull)
                .map(Skill::toPreview)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResumePreviewCommand // instanceof handles nulls
                && index.equals(((ResumePreviewCommand) other).index));
    }
}
