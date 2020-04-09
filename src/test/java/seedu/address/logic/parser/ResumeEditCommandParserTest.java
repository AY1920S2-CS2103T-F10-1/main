package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.util.ItemUtil.PROJECT_ALIAS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ResumeEditCommand;
import seedu.address.testutil.ItemIndicesBuilder;

public class ResumeEditCommandParserTest {
    private ResumeEditCommandParser parser = new ResumeEditCommandParser();

    @Test
    public void parse_allItemsSpecified_success() {
        ItemIndicesBuilder internshipIndicesBuilder = new ItemIndicesBuilder().add(1).add(3).add(4);
        ItemIndicesBuilder projectIndicesBuilder = new ItemIndicesBuilder().add(1).add(2);
        ItemIndicesBuilder skillIndicesBuilder = new ItemIndicesBuilder().add(1);

        // Standard
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " " + internshipIndicesBuilder.toString() + " " + PREFIX_SKILL
                        + " " + skillIndicesBuilder.toString() + " " + PREFIX_PROJECT + " "
                        + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));

        // Shuffle the order of prefixes
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " " + projectIndicesBuilder.toString() + " " + PREFIX_INTERNSHIP
                        + " " + internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " "
                        + skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));

        // multiple project prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " 100 200 " + PREFIX_INTERNSHIP + " "
                        + internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " "
                        + skillIndicesBuilder.toString() + " " + PREFIX_PROJECT + " "
                        + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));

        // multiple internship prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " 100 200 " + PREFIX_INTERNSHIP + " "
                        + internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " "
                        + skillIndicesBuilder.toString() + " " + PREFIX_PROJECT + " "
                        + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));

        // multiple skill prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_SKILL + " 100 200 " + PREFIX_INTERNSHIP + " "
                        + internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " "
                        + skillIndicesBuilder.toString() + " " + PREFIX_PROJECT + " "
                        + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));
    }

    @Test
    public void parse_intAndProjSpecified_success() {
        ItemIndicesBuilder internshipIndicesBuilder = new ItemIndicesBuilder().add(1).add(3).add(4);
        ItemIndicesBuilder projectIndicesBuilder = new ItemIndicesBuilder().add(1).add(2);

        // Standard
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " " + internshipIndicesBuilder.toString() + " "
                        + PREFIX_PROJECT + " " + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));

        // Shuffle the order of prefixes
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " " + projectIndicesBuilder.toString() + " " + PREFIX_INTERNSHIP
                        + " " + internshipIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));

        // multiple internship prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " 100 200 " + PREFIX_INTERNSHIP + " "
                        + internshipIndicesBuilder.toString() + " " + PREFIX_PROJECT + " "
                        + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));

        // multiple project prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " 100 200 " + PREFIX_INTERNSHIP + " "
                        + internshipIndicesBuilder.toString() + " " + PREFIX_PROJECT + " "
                        + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));
    }

    @Test
    public void parse_intAndSkiSpecified_success() {
        ItemIndicesBuilder internshipIndicesBuilder = new ItemIndicesBuilder().add(1).add(3).add(4);
        ItemIndicesBuilder skillIndicesBuilder = new ItemIndicesBuilder().add(1).add(2);

        // Standard
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " " + internshipIndicesBuilder.toString() + " "
                        + PREFIX_SKILL + " " + skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));

        // Shuffle the order of prefixes
        assertParseSuccess(parser,
                "1 " + PREFIX_SKILL + " " + skillIndicesBuilder.toString() + " " + PREFIX_INTERNSHIP
                        + " " + internshipIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));

        // multiple internship prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " 100 200 " + PREFIX_INTERNSHIP + " "
                        + internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " "
                        + skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));

        // multiple skill prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_SKILL + " 100 200 " + PREFIX_INTERNSHIP + " "
                        + internshipIndicesBuilder.toString() + " " + PREFIX_SKILL + " "
                        + skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));
    }

    @Test
    public void parse_onlyProjSpecified_success() {
        ItemIndicesBuilder projectIndicesBuilder = new ItemIndicesBuilder().add(1).add(2);

        // Standard
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " " + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));

        // multiple project prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " 100 200 " + PREFIX_PROJECT + " " + projectIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));
    }

    @Test
    public void parse_onlySkiSpecified_success() {
        ItemIndicesBuilder skillIndicesBuilder = new ItemIndicesBuilder().add(1).add(2);

        // Standard
        assertParseSuccess(parser,
                "1 " + PREFIX_SKILL + " " + skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));

        // multiple skill prefixes -- only last one accepted
        assertParseSuccess(parser,
                "1 " + PREFIX_SKILL + " 100 200 " + PREFIX_SKILL + " " + skillIndicesBuilder.toString(),
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));
    }

    @Test
    public void parse_nothingSpecified_success() {
        assertParseSuccess(parser,
                "1",
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty(), ItemIndicesBuilder.empty(),
                        ItemIndicesBuilder.empty()));

        assertParseSuccess(parser,
                "4",
                new ResumeEditCommand(INDEX_FOURTH_ITEM, ItemIndicesBuilder.empty(), ItemIndicesBuilder.empty(),
                        ItemIndicesBuilder.empty()));
    }

    @Test
    public void parse_itemsSpecifiedButNoIndex_success() {
        ItemIndicesBuilder internshipIndicesBuilder = new ItemIndicesBuilder();
        ItemIndicesBuilder projectIndicesBuilder = new ItemIndicesBuilder();
        ItemIndicesBuilder skillIndicesBuilder = new ItemIndicesBuilder();

        // clear all
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " " + PREFIX_PROJECT + " " + PREFIX_SKILL,
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));

        // clear only two
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " " + PREFIX_SKILL,
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));

        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT + " " + PREFIX_SKILL,
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));

        // clear only one
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP,
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), ItemIndicesBuilder.empty(),
                        ItemIndicesBuilder.empty()));

        assertParseSuccess(parser,
                "1 " + PREFIX_PROJECT,
                new ResumeEditCommand(INDEX_FIRST_ITEM, ItemIndicesBuilder.empty(), projectIndicesBuilder.build(),
                        ItemIndicesBuilder.empty()));
    }

    @Test
    public void parse_mixAndMatchItemSpecifiedButNoIndex_success() {
        ItemIndicesBuilder internshipIndicesBuilder = new ItemIndicesBuilder().add(1).add(4).add(3);
        ItemIndicesBuilder projectIndicesBuilder = new ItemIndicesBuilder().add(2).add(4).add(1);
        ItemIndicesBuilder skillIndicesBuilder = new ItemIndicesBuilder();

        // fill up the internship and project, but empty the skill
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " " + internshipIndicesBuilder.toString() + " "
                        + PREFIX_PROJECT + " " + projectIndicesBuilder.toString() + " " + PREFIX_SKILL,
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), projectIndicesBuilder.build(),
                        skillIndicesBuilder.build()));

        // fill up the internship, but empty the skill
        assertParseSuccess(parser,
                "1 " + PREFIX_INTERNSHIP + " " + internshipIndicesBuilder.toString() + " " + PREFIX_SKILL,
                new ResumeEditCommand(INDEX_FIRST_ITEM, internshipIndicesBuilder.build(), ItemIndicesBuilder.empty(),
                        skillIndicesBuilder.build()));
    }

    @Test
    public void parse_invalidResumeIndex_throwsParseException() {
        // standard
        assertParseFailure(parser, "a", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-1", ParserUtil.MESSAGE_INVALID_INDEX);

        // with some item prefixes
        assertParseFailure(parser, "a " + PREFIX_INTERNSHIP + " 1 2 3 " + PREFIX_PROJECT + " 3 2 1 "
                        + PREFIX_SKILL + " 2 1", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-1 " + PREFIX_PROJECT + " 4 1 2", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "1 1 " + PREFIX_PROJECT + " 4 1 2", ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidReditItemIndices_throwsParseException() {
        // standard
        assertParseFailure(parser, "1 " + PREFIX_INTERNSHIP + " a",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "1 " + PREFIX_INTERNSHIP + " -1",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "2 " + PREFIX_PROJECT + " a",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "2 " + PREFIX_PROJECT + " -1",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "3 " + PREFIX_SKILL + " 0",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "3 " + PREFIX_SKILL + " 100@",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);

        // mix valid and invalid
        assertParseFailure(parser, "2 " + PREFIX_PROJECT + " -1 " + PREFIX_SKILL + " 1 2 3",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "2 " + PREFIX_INTERNSHIP + " 3 1 2 " + PREFIX_SKILL + " @",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "2 " + PREFIX_INTERNSHIP + " 3 2 " + PREFIX_PROJECT + " a",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);

        // all invalid
        assertParseFailure(parser, "2 " + PREFIX_INTERNSHIP + " -1 " + PREFIX_PROJECT + " a",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
        assertParseFailure(parser, "3 " + PREFIX_INTERNSHIP + " -1 " + PREFIX_PROJECT + " a "
                + PREFIX_SKILL + " 0",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
    }

    @Test
    public void parse_variousTypo_throwsParseException() {
        // Forgot to specify index and go to item prefixes directly -- Parser expects an index
        assertParseFailure(parser, PREFIX_INTERNSHIP + "1 2", ParserUtil.MESSAGE_INVALID_INDEX);

        // Space between slash and prefix, right after index -- Parser thinks that the mistyped prefix is part of index
        assertParseFailure(parser, "1 " + PROJECT_ALIAS + " /", ParserUtil.MESSAGE_INVALID_INDEX);

        // Space between slash and prefix, right after a correct prefix -- Parser thinks it's part of the indices of
        // the correctly-written prefix
        assertParseFailure(parser, "1 " + PREFIX_INTERNSHIP + " 1 2 " + PROJECT_ALIAS + " / 2 3",
                ParserUtil.MESSAGE_INVALID_REDIT_ITEM_INDEX);
    }
}
