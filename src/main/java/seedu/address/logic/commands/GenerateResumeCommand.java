package seedu.address.logic.commands;

import static java.lang.System.err;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.item.Internship;
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

    private static final Color MAIN_COLOR = new Color(0, 0, 0);
    private static final Color ACCENT_COLOR = new Color(153, 0, 51);
    private static final int BODY_SIZE = 11;
    private static final int HEADING_SIZE = 14;
    private static final PDFont FONT_BOLD = PDType1Font.HELVETICA_BOLD;
    private static final PDFont FONT_REGULAR = PDType1Font.HELVETICA;
    private static final int marginX = 64;
    private static final int marginY = 100;
    private static final float spacing = 24;
    private static final PDRectangle page = PDRectangle.A4;
    private static final String rootPath = "export/";

    private static float curX = 0;
    private static float curY = 0;

    protected final Index targetIndex;
    protected String title;

    public GenerateResumeCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        title = null;
    }

    public GenerateResumeCommand(Index targetIndex, Name resumeName) {
        this.targetIndex = targetIndex;
        this.title = resumeName.toString();
    }

    public void setUp() {
        float pageHeight = page.getHeight();
        curY = pageHeight - marginY;
    }

    /**
     * Changes the alignment of the text to centre align.
     * @param content the text to be aligned
     * @throws IOException
     */
    public void centerAlign(String content) throws IOException {
        float stringWidth = FONT_BOLD.getStringWidth(content) * HEADING_SIZE / 1000f;
        float pageWidth = page.getWidth();
        curX = (pageWidth - stringWidth) / 2f;
    }

    /**
     * Adds title heading to the output Resume file.
     * @param contentStream `Content Stream` to write to file.
     * @param title title of the Resume document.
     * @throws IOException
     */
    public void addTitle(PDPageContentStream contentStream, String title) throws IOException {
        contentStream.setFont(FONT_BOLD, HEADING_SIZE);
        contentStream.setNonStrokingColor(ACCENT_COLOR);
        setUp();
        centerAlign(title);
        contentStream.newLineAtOffset(curX, curY);
        contentStream.showText(title.toUpperCase());
        contentStream.newLineAtOffset(-curX + marginX, 0);
    }

    /**
     * Adds a new section to the output Resume file.
     * @param contentStream `Content Stream` to write to file.
     * @param section name of the new section.
     * @throws IOException
     */
    public void addSection(PDPageContentStream contentStream, String section) throws IOException {
        contentStream.setFont(FONT_BOLD, BODY_SIZE);
        contentStream.setNonStrokingColor(ACCENT_COLOR);
        contentStream.setLeading(spacing);
        contentStream.newLine();
        contentStream.newLine();
        contentStream.showText(section);
    }

    /**
     * Adds a new `Internship` item to the output Resume file.
     * @param contentStream `Content Stream` to write to file.
     * @param internship `Internship` item to be added.
     * @throws IOException
     */
    public void addInternship(PDPageContentStream contentStream, Internship internship) throws IOException {
        String name = internship.getName().toString();
        String role = internship.getRole();
        String from = internship.getFrom().format();
        String to = internship.getTo().format();
        String title = name + " | " + role + " | " + from + " - " + to;
        addItemTitle(contentStream, title);

        String description = internship.getDescription();
        addDescription(contentStream, description);
    }

    /**
     * Adds a new `Project` item to the output Resume file.
     * @param contentStream `Content Stream` to write to file.
     * @param project `Project` item to be added.
     * @throws IOException
     */
    public void addProject(PDPageContentStream contentStream, Project project) throws IOException {
        String name = project.getName().toString();
        String time = project.getTime().format();
        String website = project.getWebsite().toString();
        String title = name + " | " + time + " | " + website;
        addItemTitle(contentStream, title);

        String description = project.getDescription();
        addDescription(contentStream, description);
    }

    /**
     * Adds a new `Skill` item to the output Resume file.
     * @param contentStream `Content Stream` to write to file.
     * @param skill `Skill` item to be added.
     * @throws IOException
     */
    public void addSkill(PDPageContentStream contentStream, Skill skill) throws IOException {
        String name = skill.getName().toString();
        String level = skill.getLevel().toString();
        String description = name + " | " + level;
        addDescription(contentStream, description);
    }

    /**
     * Adds title to a new item in the output Resume file
     * @param contentStream `Content Stream` to write to file.
     * @param title title of the item to be added
     * @throws IOException
     */
    public void addItemTitle(PDPageContentStream contentStream, String title) throws IOException {
        contentStream.setNonStrokingColor(MAIN_COLOR);
        contentStream.setFont(FONT_BOLD, BODY_SIZE);
        contentStream.setLeading(spacing);
        contentStream.newLine();
        contentStream.showText(title);
    }

    /**
     * Adds description to a new item in the output Resume file
     * @param contentStream `Content Stream` to write to file.
     * @param description description of the item to be added
     * @throws IOException
     */
    public void addDescription(PDPageContentStream contentStream, String description) throws IOException {
        contentStream.setNonStrokingColor(MAIN_COLOR);
        contentStream.setFont(FONT_REGULAR, BODY_SIZE);
        contentStream.setLeading(spacing);

        String[] content = description.split("\\.");
        for (String line: content) {
            contentStream.newLine();
            contentStream.showText("- " + line.trim() + ".");
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        // Get resume item
        requireNonNull(model);
        if (targetIndex.getZeroBased() >= model.getResumeSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX);
        }
        Resume resumeToGenerate = model.getResumeByIndex(targetIndex);

        // Get title
        if (this.title == null) {
            title = resumeToGenerate.getName().toString();
        }

        // Get internships to add
        List<Integer> internshipsToAdd = resumeToGenerate.getInternshipIds();

        // Get projects to add
        List<Integer> projectsToAdd = resumeToGenerate.getProjectIds();

        // Get skills to add
        List<Integer> skillsToAdd = resumeToGenerate.getSkillIds();

        final PDPage singlePage = new PDPage();
        try {
            final PDDocument resume = new PDDocument();
            resume.addPage(singlePage);
            final PDPageContentStream contentStream = new PDPageContentStream(resume, singlePage);
            contentStream.beginText();
            addTitle(contentStream, title);
            addSection(contentStream, "INTERNSHIPS");
            for (Integer id: internshipsToAdd) {
                Internship toAdd = model.getInternshipById(id);
                addInternship(contentStream, toAdd);
            }

            addSection(contentStream, "PROJECTS");
            for (Integer id: projectsToAdd) {
                Project toAdd = model.getProjectById(id);
                addProject(contentStream, toAdd);
            }

            addSection(contentStream, "SKILLS");
            for (Integer id: skillsToAdd) {
                Skill toAdd = model.getSkillById(id);
                addSkill(contentStream, toAdd);
            }
            contentStream.endText();
            contentStream.close();
            resume.save(rootPath + title + ".pdf");
        } catch (IOException e) {
            err.println("Exception while trying to create simple document - " + e);
        }

        return new CommandResult(resumeToGenerate.toString(),
                String.format(MESSAGE_GENERATE_SUCCESS, title, resumeToGenerate.getName().toString()),
                false, false, true, false, false);
    }
}