package seedu.address.logic.parser;

import seedu.address.model.util.ItemUtil;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */

    /* AB3's original prefixes */
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");

    /* Items */
    public static final Prefix PREFIX_ITEM = new Prefix("i/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_TAG = new Prefix("#/");

    /* Internship */
    public static final Prefix PREFIX_FROM = new Prefix("f/");
    public static final Prefix PREFIX_TO = new Prefix("t/");
    public static final Prefix PREFIX_ROLE = new Prefix("r/");

    /* Project */
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_WEBSITE = new Prefix("w/");

    /* Common between Internship and Project */
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");

    /* Skill */
    public static final Prefix PREFIX_LEVEL = new Prefix("l/");

    /* Prefixes for different item types */
    public static final Prefix PREFIX_INTERNSHIP = new Prefix(ItemUtil.INTERNSHIP_ALIAS + "/");
    public static final Prefix PREFIX_PROJECT = new Prefix(ItemUtil.PROJECT_ALIAS + "/");
    public static final Prefix PREFIX_RESUME = new Prefix(ItemUtil.RESUME_ALIAS + "/");
    public static final Prefix PREFIX_SKILL = new Prefix(ItemUtil.SKILL_ALIAS + "/");

    /* User */
    public static final Prefix PREFIX_DP = new Prefix("dp/");
    public static final Prefix PREFIX_GITHUB = new Prefix("g/");
    public static final Prefix PREFIX_UNIVERSITY = new Prefix("u/");
    public static final Prefix PREFIX_MAJOR = new Prefix("m/");
    public static final Prefix PREFIX_CAP = new Prefix("c/");
}
