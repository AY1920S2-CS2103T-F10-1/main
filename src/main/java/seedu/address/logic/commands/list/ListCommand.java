package seedu.address.logic.commands.list;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;

import seedu.address.logic.commands.Command;

/**
 * Lists all items in the address book to the user.
 */
public abstract class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all %1$s items";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all items of specified type in the resume book.\n"
            + COMMAND_WORD + " "
            + PREFIX_ITEM + "TYPE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ITEM + "res ";

}
