package seedu.address.logic.commands.generate;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import seedu.address.model.item.Internship;
import seedu.address.model.item.Person;
import seedu.address.model.item.Project;
import seedu.address.model.item.Skill;
import seedu.address.model.item.field.Level;

/**
 * Builder class to format resume file.
 */
public class PdfBuilder {

    private static final Color MAIN_COLOR = new Color(0, 0, 0);
    private static final Color ACCENT_COLOR = new Color(153, 0, 51);
    private static final int BODY_SIZE = 11;
    private static final int HEADING_SIZE = 14;
    private static final int TITLE_SIZE = 20;
    private static final PDFont FONT_BOLD = PDType1Font.HELVETICA_BOLD;
    private static final PDFont FONT_REGULAR = PDType1Font.HELVETICA;
    private static final PDFont FONT_OBLIQUE = PDType1Font.HELVETICA_OBLIQUE;

    private final PDDocument resume = new PDDocument();
    private final int marginX = 64;
    private final int marginY = 100;
    private final float spacing = 20;
    private final PDRectangle page = PDRectangle.A4;
    private final float pageHeight = page.getHeight();
    private final float pageWidth = page.getWidth();

    private PDPageContentStream contentStream;
    private float curX;
    private float curY;
    private PDFont curFont;
    private int curSize;

    //=========== Page set up ================================================================================

    /**
     * Adds a new page.
     * @throws IOException
     */
    public void addPage() throws IOException {
        PDPage blank = new PDPage();
        resume.addPage(blank);
        contentStream = new PDPageContentStream(resume, blank);
        setFont(FONT_REGULAR, BODY_SIZE);
        setColor(MAIN_COLOR);
        contentStream.setLeading(spacing);
        contentStream.beginText();
        curX = page.getLowerLeftX();
        curY = page.getLowerLeftY();;
        contentStream.newLineAtOffset(resetX(), resetY());
    }

    /**
     * Ends the current page.
     * @throws IOException
     */
    public void endPage() throws IOException {
        contentStream.endText();
        contentStream.close();
    }

    public boolean isEndOfPage() {
        return curY + spacing <= marginY;
    }

    /**
     * Moves cursor to the next line.
     * @throws IOException
     */
    public void nextLine() throws IOException {
        contentStream.newLine();
        curY -= spacing;
    }

    /**
     * Reset x alignment to left align.
     * @return the value of x-coordinate offset.
     * @throws IOException
     */
    public float resetX() {
        float xOffSet = -curX + marginX;
        curX += xOffSet;
        return xOffSet;
    }

    /**
     * Reset y alignment to left align.
     * @return the value of y-coordinate offset.
     * @throws IOException
     */
    public float resetY() {
        float yOffSet = -curY + pageHeight - marginY - spacing;
        curY += yOffSet;
        return yOffSet;
    }

    /**
     * Changes the alignment of the text to centre align.
     * @param content content the text to be aligned
     * @throws IOException
     */
    public void centerAlign(String content) throws IOException {
        float stringWidth = curFont.getStringWidth(content) * curSize / 1000f;
        float xOffSet = -curX + (pageWidth - stringWidth) / 2f;
        contentStream.newLineAtOffset(xOffSet, 0);
        curX += xOffSet;
    }

    public void setColor(Color color) throws IOException {
        contentStream.setNonStrokingColor(color);
    }

    public void setFont(PDFont font, int size) throws IOException {
        contentStream.setFont(font, size);
        curFont = font;
        curSize = size;
    }

    //=========== Add and format section content =============================================================

    /**
     * Formats and shows content that spans over multiple lines.
     * @param content the content to be shown.
     * @throws IOException
     */
    public void fitMultiLine(String content) throws IOException {
        if (isEndOfPage()) {
            endPage();
            addPage();
        }
        float limit = page.getWidth() - 2 * marginX;
        String[] words = content.split(" ");
        int i = 0;
        boolean isFirstLine = true;
        while (i < words.length) {
            String line = "";
            float width = 0;
            while (i < words.length) {
                String word = words[i];
                float add = curFont.getStringWidth(word + " ") * curSize / 1000f;
                if (width + add > limit) {
                    break;
                }
                width += add;
                line += word + " ";
                i++;
            }
            if (!isFirstLine) {
                line = "  " + line;
            }
            contentStream.showText(line);
            isFirstLine = false;
            nextLine();
        }
    }

    /**
     * Adds a new section title to the output Resume file.
     * @param section name of the new section.
     * @throws IOException
     */
    public void addSectionTitle(String section) throws IOException {
        if (isEndOfPage()) {
            endPage();
            addPage();
        }
        setFont(FONT_BOLD, HEADING_SIZE);
        setColor(ACCENT_COLOR);
        contentStream.showText(section);
        nextLine();
    }

    /**
     * Adds title to a new item in the output Resume file
     * @param title title of the item to be added
     * @throws IOException
     */
    public void addItemTitle(String title) throws IOException {
        if (isEndOfPage()) {
            endPage();
            addPage();
        }
        setColor(MAIN_COLOR);
        setFont(FONT_BOLD, BODY_SIZE);
        contentStream.showText(title);
        nextLine();
    }

    /**
     * Adds description to a new item in the output Resume file
     * @param description description of the item to be added
     * @throws IOException
     */
    public void addDescription(String description) throws IOException {
        setFont(FONT_REGULAR, BODY_SIZE);

        String[] content = description.split("\\.");
        for (String line: content) {
            String point = "- " + line.trim() + ".";
            fitMultiLine(point);
        }
        nextLine();
    }

    //=========== Add sections ================================================================================

    /**
     * Adds resume title to the resume.
     * @param user user of the application.
     * @throws IOException
     */
    public void addResumeTitle(Person user) throws IOException {
        setFont(FONT_BOLD, TITLE_SIZE);
        setColor(ACCENT_COLOR);
        String name = user.getName().toString();
        centerAlign(name);
        contentStream.showText(name.toUpperCase());
        contentStream.newLineAtOffset(resetX(), 0);
        nextLine();
    }

    /**
     * Adds user's bio to the resume
     * @param user user of the application.
     * @throws IOException
     */
    public void addBio(Person user) throws IOException {
        setFont(FONT_OBLIQUE, HEADING_SIZE);
        setColor(ACCENT_COLOR);
        String bio = user.getDescription().toString();
        centerAlign(bio);
        contentStream.showText(bio);
        contentStream.newLineAtOffset(resetX(), 0);
        nextLine();
    }

    /**
     * Adds user's contact to the resume.
     * @param user user of the application.
     * @throws IOException
     */
    public void addContact(Person user) throws IOException {
        setFont(FONT_REGULAR, BODY_SIZE);
        setColor(MAIN_COLOR);
        String phone = user.getPhone().toString();
        String email = user.getEmail().toString();
        String git = user.getGithub().toString();
        String contact = phone + "  |  " + email + "  |  " + git;
        centerAlign(contact);
        contentStream.showText(contact);
        contentStream.newLineAtOffset(resetX(), 0);
        nextLine();
        nextLine();
    }

    /**
     * Adds educational details of the user to the output Resume file.
     * @param user user of the application.
     * @throws IOException
     */
    public void addEducation(Person user) throws IOException {
        String university = user.getUniversity().toString();
        String from = user.getFrom().format();
        String to = user.getTo().format();
        String title = university + " | " + from + " - " + to;
        addItemTitle(title);

        setFont(FONT_REGULAR, BODY_SIZE);
        setColor(MAIN_COLOR);
        String major = "- " + user.getMajor();
        contentStream.showText(major);
        nextLine();
        String cap = "- Cumulative Average Point: " + user.getCap().toString();
        contentStream.showText(cap);
        nextLine();
        nextLine();
    }

    /**
     * Adds a new {@code Internship} item to the output Resume file.
     * @param internship {@code Internship} item to be added.
     * @throws IOException
     */
    public void addInternship(Internship internship) throws IOException {
        String name = internship.getName().toString();
        String role = internship.getRole().toString();
        String from = internship.getFrom().format();
        String to = internship.getTo().format();
        String title = name + " | " + role + " | " + from + " - " + to;
        addItemTitle(title);

        String description = internship.getDescription().toString();
        addDescription(description);
    }

    /**
     * Adds a new {@code Project} item to the output Resume file.
     * @param project {@code Project} item to be added.
     * @throws IOException
     */
    public void addProject(Project project) throws IOException {
        String name = project.getName().toString();
        String time = project.getTime().format();
        String website = project.getWebsite().toString();
        String title = name + " | " + time + " | " + website;
        addItemTitle(title);

        String description = project.getDescription().toString();
        addDescription(description);
    }

    /**
     * Adds {@code Skill} items to the output Resume file.
     * @param skills {@code Skill} item to be added.
     * @throws IOException
     */
    public void addSkills(List<Skill> skills) throws IOException {
        List<Skill> basic = new ArrayList<>();
        List<Skill> intermediate = new ArrayList<>();
        List<Skill> advanced = new ArrayList<>();

        for (Skill skill: skills) {
            Level level = skill.getLevel();
            switch (level) {
            case BASIC:
                basic.add(skill);
                break;
            case INTERMEDIATE:
                intermediate.add(skill);
                break;
            case ADVANCED:
                advanced.add(skill);
                break;
            default:
                //Should not reach here
            }
        }

        if (!advanced.isEmpty()) {
            addLeveledSkills("Advanced: ", advanced);
        }
        if (!intermediate.isEmpty()) {
            addLeveledSkills("Intermediate: ", intermediate);
        }
        if (!basic.isEmpty()) {
            addLeveledSkills("Basic: ", basic);
        }
    }

    /**
     * Formats and shows skills of a specific level.
     * @param level the level title to be shown.
     * @param skills list of skills with the specified level.
     * @throws IOException
     */
    public void addLeveledSkills(String level, List<Skill> skills) throws IOException {
        if (isEndOfPage()) {
            endPage();
            addPage();
        }
        setColor(MAIN_COLOR);
        setFont(FONT_BOLD, BODY_SIZE);
        contentStream.showText(level);
        String line = skills.get(0).getName().toString();
        for (int i = 1; i < skills.size(); i++) {
            line += ", " + skills.get(i).getName().toString();
        }
        setFont(FONT_REGULAR, BODY_SIZE);
        fitMultiLine(line);
    }

    /**
     * Builds the completed resume file.
     * @return the formatted document to be saved.
     * @throws IOException
     */
    public PDDocument build() throws IOException {
        endPage();
        return this.resume;
    }
}
