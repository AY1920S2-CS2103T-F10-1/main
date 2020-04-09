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

import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.list.ListInternshipCommand;
import seedu.address.logic.commands.list.ListNoteCommand;
import seedu.address.logic.commands.list.ListProjectCommand;
import seedu.address.logic.commands.list.ListResumeCommand;
import seedu.address.logic.commands.list.ListSkillCommand;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();
    @Test
    public void parse_listAllInternships_success() {
        assertParseSuccess(parser, ITEM_TYPE_INTERNSHIP, new ListInternshipCommand());
    }

    @Test
    public void parse_listAllNotes_success() {
        assertParseSuccess(parser, ITEM_TYPE_NOTE, new ListNoteCommand());
    }

    @Test
    public void parse_listAllProjects_success() {
        assertParseSuccess(parser, ITEM_TYPE_PROJECT, new ListProjectCommand());
    }

    @Test
    public void parse_listAllResumes_success() {
        assertParseSuccess(parser, ITEM_TYPE_RESUME, new ListResumeCommand());
    }

    @Test
    public void parse_listAllSkills_success() {
        assertParseSuccess(parser, ITEM_TYPE_SKILL, new ListSkillCommand());
    }

    @Test
    public void parser_missingType_failure() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parser_withPreamble_failure() {
        assertParseFailure(parser, "abc " + ITEM_TYPE_RESUME,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parser_invalidType_failure() {
        assertParseFailure(parser, ITEM_TYPE_INVALID, "Not a valid item type!");
    }
}
