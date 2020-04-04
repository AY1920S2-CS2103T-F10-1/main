package seedu.address.logic.commands.generate;

import java.awt.*;
import java.io.IOException;

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

public class PdfBuilder {

    private final PDDocument resume = new PDDocument();
    private static PDPageContentStream contentStream;
    private static final Color MAIN_COLOR = new Color(0, 0, 0);
    private static final Color ACCENT_COLOR = new Color(153, 0, 51);
    private static final int BODY_SIZE = 11;
    private static final int HEADING_SIZE = 14;
    private static final int TITLE_SIZE = 20;
    private static final PDFont FONT_BOLD = PDType1Font.HELVETICA_BOLD;
    private static final PDFont FONT_REGULAR = PDType1Font.HELVETICA;
    private static final int marginX = 64;
    private static final int marginY = 100;
    private static final float spacing = 20;
    private static final PDRectangle page = PDRectangle.A4;

    private static float curX = 0;
    private static float curY = 0;
    private static PDFont curFont;
    private static int curSize;

    public PdfBuilder() {

    }

    //=========== Page set up ================================================================================

    public void setUp() throws IOException {
        final PDPage singlePage = new PDPage();
        resume.addPage(singlePage);
        contentStream = new PDPageContentStream(resume, singlePage);
        contentStream.setLeading(spacing);
        float pageHeight = page.getHeight();
        curY = pageHeight - marginY;
        curX = marginX;
        contentStream.beginText();
        contentStream.newLineAtOffset(curX, curY);
    }

    /**
     * Moves cursor to the next line.
     * @throws IOException
     */
    public void nextLine() throws IOException {
        contentStream.newLine();
        curY += spacing;
    }

    /**
     * Resets x alignment to left align.
     * @throws IOException
     */
    public void resetX() throws IOException {
        float xOffSet = -curX + marginX;
        contentStream.newLineAtOffset(xOffSet, 0);
        curX += xOffSet;
    }

    /**
     * Changes the alignment of the text to centre align.
     * @param content content the text to be aligned
     * @throws IOException
     */
    public void centerAlign(String content) throws IOException {
        float stringWidth = curFont.getStringWidth(content) * curSize / 1000f;
        float pageWidth = page.getWidth();
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
        float limit = page.getWidth() - 2 * marginX;
        String[] words = content.split(" ");
        int i = 0;
        boolean isFirstLine = true;
        while (i < words.length) {
            String line = "";
            float width = 0;
            while (i < words.length) {
                String word = words[i];
                float add = curFont.getStringWidth(word) * curSize / 1000f;
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
            System.out.println(line);
            System.out.println("width " + width + " fit in limit " + limit);
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
        setColor(MAIN_COLOR);
        contentStream.setFont(FONT_BOLD, BODY_SIZE);
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
        resetX();
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
        resetX();
        nextLine();
        nextLine();
    }

    /**
     * Adds educational details of the user to the output Resume file.
     * @param user user of the application.
     * @throws IOException
     */
    public void addEducation(Person user) throws IOException {
        String university = user.getUniversity();
        String from = user.getFrom().format();
        String to = user.getTo().format();
        String title = university + " | " + to + " - " + from;
        addItemTitle(title);

        setFont(FONT_REGULAR, BODY_SIZE);
        setColor(MAIN_COLOR);
        String major = "- " + user.getMajor();
        contentStream.showText(major);
        nextLine();
        String cap = "- Cumulative Average Point: " + user.getCap();
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
        String role = internship.getRole();
        String from = internship.getFrom().format();
        String to = internship.getTo().format();
        String title = name + " | " + role + " | " + from + " - " + to;
        addItemTitle(title);

        String description = internship.getDescription();
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

        String description = project.getDescription();
        addDescription(description);
    }

    /**
     * Adds a new {@code Skill} item to the output Resume file.
     * @param skill {@code Skill} item to be added.
     * @throws IOException
     */
    public void addSkill(Skill skill) throws IOException {
        String name = skill.getName().toString();
        String level = skill.getLevel().toString();
        String description = name + " | " + level;
        addItemTitle(description);
    }

    public PDDocument build() throws IOException {
        contentStream.endText();
        contentStream.close();
        return this.resume;
    }

}
