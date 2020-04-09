package seedu.address.logic.commands.sort;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVERSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import seedu.address.logic.commands.Command;

/**
 * Sorts items of one type in the resume book.
 */
public abstract class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all %1$s items";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts items from the same list "
            + "and change the indices in the displayed item list accordingly. "
            + "Specify if you want to sort in reverse order.\n"
            + "All items can be sorted by name using sort word \"name\", "
            + "Projects can be sorted by time and internships by start time using \"time\". "
            + "Skills can be sorted by level using \"level\". \n"
            + "Parameters: "
            + PREFIX_ITEM + " TYPE "
            + PREFIX_SORT_ORDER + " SORT_WORD "
            + "[ " + PREFIX_REVERSE + " REVERSE_OR_NOT ]\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_ITEM + " int "
            + PREFIX_SORT_ORDER + " time ";

    public static final String MESSAGE_SORT_NOT_EXISTED = "This sort criterion does not exist.";

    public static final String MESSAGE_INAPPLICABLE_SORT = "The sort criterion is inapplicable for this item type.";

}
