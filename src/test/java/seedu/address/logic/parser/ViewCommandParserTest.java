package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_INTERNSHIP;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_INVALID;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_NOTE;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_PROJECT;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_RESUME;
import static seedu.address.logic.commands.CommandTestUtil.ITEM_TYPE_SKILL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.view.ViewCommand;
import seedu.address.logic.commands.view.ViewInternshipCommand;
import seedu.address.logic.commands.view.ViewNoteCommand;
import seedu.address.logic.commands.view.ViewProjectCommand;
import seedu.address.logic.commands.view.ViewResumeCommand;
import seedu.address.logic.commands.view.ViewSkillCommand;
import seedu.address.model.item.Item;
import seedu.address.testutil.TypicalIndexes;

public class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_viewInternship_success() {
        assertParseSuccess(parser, "3 " + ITEM_TYPE_INTERNSHIP,
                new ViewInternshipCommand(TypicalIndexes.INDEX_THIRD_ITEM));
    }

    @Test
    public void parse_viewNote_success() {
        assertParseSuccess(parser, "3 " + ITEM_TYPE_NOTE,
                new ViewNoteCommand(TypicalIndexes.INDEX_THIRD_ITEM));
    }

    @Test
    public void parse_viewProject_success() {
        assertParseSuccess(parser, "3 " + ITEM_TYPE_PROJECT,
                new ViewProjectCommand(TypicalIndexes.INDEX_THIRD_ITEM));
    }

    @Test
    public void parse_viewResume_success() {
        assertParseSuccess(parser, "3 " + ITEM_TYPE_RESUME,
                new ViewResumeCommand(TypicalIndexes.INDEX_THIRD_ITEM));
    }

    @Test
    public void parse_viewSkill_success() {
        assertParseSuccess(parser, "3 " + ITEM_TYPE_SKILL,
                new ViewSkillCommand(TypicalIndexes.INDEX_THIRD_ITEM));
    }

    @Test
    public void parser_missingParameters_failure() {
        // Missing both
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));

        // Missing type
        assertParseFailure(parser, "3 ", Item.MESSAGE_CONSTRAINTS);

        // Missing index
        assertParseFailure(parser, ITEM_TYPE_RESUME, ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parser_invalidIndex_failure() {
        assertParseFailure(parser, "abc " + ITEM_TYPE_RESUME,
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parser_invalidType_failure() {
        assertParseFailure(parser, "3 " + ITEM_TYPE_INVALID,
                "Not a valid item type!");
    }
}
