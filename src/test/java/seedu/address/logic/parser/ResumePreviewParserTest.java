package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ResumePreviewCommand;

public class ResumePreviewParserTest {
    private ResumePreviewCommandParser parser = new ResumePreviewCommandParser();

    @Test
    public void parse_validIndex_returnsResumePreviewCommand() {
        assertParseSuccess(parser, "1", new ResumePreviewCommand(INDEX_FIRST_ITEM));
        assertParseSuccess(parser, "4", new ResumePreviewCommand(INDEX_FOURTH_ITEM));
    }

    @Test
    public void parse_noIndex_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ResumePreviewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "a", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-1", ParserUtil.MESSAGE_INVALID_INDEX);
    }

}
