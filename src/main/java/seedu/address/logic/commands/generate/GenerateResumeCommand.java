package seedu.address.logic.commands.generate;

import static java.lang.System.err;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.pdfbox.pdmodel.PDDocument;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Person;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.item.field.Name;

/**
 * Generate pdf file from a Resume item
 */
public class GenerateResumeCommand extends Command {

    public static final String COMMAND_WORD = "rgen";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Generate a pdf file from a Resume item identified by the index number used in the resume list. "
            + "If no name is provided for the output .pdf file, default name is the same as resume name.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME]\n"
            + "Example: " + COMMAND_WORD + " 1 [" + PREFIX_NAME + "MyResume";
    public static final String MESSAGE_GENERATE_SUCCESS = "Generated %s from %s";
    private static final String rootPath = "";

    protected String fileName;
    protected final Index targetIndex;

    private final PdfBuilder builder = new PdfBuilder();



    public GenerateResumeCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        fileName = null;
    }

    public GenerateResumeCommand(Index targetIndex, Name resumeName) {
        this.targetIndex = targetIndex;
        this.fileName = resumeName.toString();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        // Get resume item
        requireNonNull(model);
        if (targetIndex.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }
        Person user = model.getUser();
        Resume resumeToGenerate = model.getResumeByIndex(targetIndex);

        // Set file name
        if (this.fileName == null) {
            fileName = resumeToGenerate.getName().toString();
        }

        // Get internships to add
        List<Integer> internshipsToAdd = resumeToGenerate.getInternshipIds();

        // Get projects to add
        List<Integer> projectsToAdd = resumeToGenerate.getProjectIds();

        // Get skills to add
        List<Integer> skillsToAdd = resumeToGenerate.getSkillIds();

        try {
            builder.addPage();
            builder.addResumeTitle(user);
            builder.addContact(user);

            builder.addSectionTitle("EDUCATION");
            builder.addEducation(user);

            builder.addSectionTitle("INTERNSHIPS");
            for (Integer id: internshipsToAdd) {
                Internship toAdd = model.getInternshipById(id);
                builder.addInternship(toAdd);
            }

            builder.addSectionTitle("PROJECTS");
            for (Integer id: projectsToAdd) {
                Project toAdd = model.getProjectById(id);
                builder.addProject(toAdd);
            }

            builder.addSectionTitle("SKILLS");
            List<Skill> skills = resumeToGenerate.getSkillIds().stream()
                    .map(x -> model.hasSkillId(x) ? model.getSkillById(x) : null)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            builder.addSkills(skills);

            PDDocument resume = builder.build();
            resume.save(rootPath + fileName + ".pdf");

        } catch (IOException e) {
            err.println("Exception while trying to create simple document - " + e);
        }

        return new CommandResult(resumeToGenerate.toString(),
                String.format(MESSAGE_GENERATE_SUCCESS, fileName, resumeToGenerate.getName().toString()),
                model.getDisplayType(), false, true, false, false);
    }
}
