package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ResumeEditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code ResumeEditCommand} object
 */
public class ResumeEditCommandParser implements Parser<ResumeEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ResumeEditCommand
     * and returns an ResumeEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ResumeEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INTERNSHIP, PREFIX_SKILL, PREFIX_PROJECT);
        Index index;

        index = ParserUtil.parseIndex(argMultimap.getPreamble());

        // Optional.empty() denotes non-existence, "" denotes that no argument specified, else some arguments specified
        Optional<List<Integer>> internshipIndices = ParserUtil.parseReditItemIndices(
                argMultimap.getValue(PREFIX_INTERNSHIP).orElse(null));
        Optional<List<Integer>> projectsIndices = ParserUtil.parseReditItemIndices(
                argMultimap.getValue(PREFIX_PROJECT).orElse(null));
        Optional<List<Integer>> skillsIndices = ParserUtil.parseReditItemIndices(
                argMultimap.getValue(PREFIX_SKILL).orElse(null));

        return new ResumeEditCommand(index, internshipIndices, projectsIndices, skillsIndices);
    }
}
