package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.generate.GenerateResumeCommand;
import seedu.address.model.item.field.Name;

public class GenerateResumeCommandParserTest {

    private GenerateResumeCommandParser parser = new GenerateResumeCommandParser();

    @Test
    public void parse_validIndex_returnsGenerateResumeCommand() {
        assertParseSuccess(parser, "1", new GenerateResumeCommand(INDEX_FIRST_ITEM));
        assertParseSuccess(parser, "3", new GenerateResumeCommand(INDEX_THIRD_ITEM));
    }

    @Test
    public void parse_validIndexAndName_returnsGenerateResumeCommand() {
        assertParseSuccess(parser, "1 n/Resume",
                new GenerateResumeCommand(INDEX_FIRST_ITEM, new Name("Resume")));
        assertParseSuccess(parser, "3 n/My Resume",
                new GenerateResumeCommand(INDEX_THIRD_ITEM, new Name("My Resume")));
    }

    @Test
    public void parse_noIndex_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GenerateResumeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "d", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "0", ParserUtil.MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "-1", ParserUtil.MESSAGE_INVALID_INDEX);
    }
}
