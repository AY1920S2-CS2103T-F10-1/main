package seedu.address.model.item.field;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Item's type in the address book.
 */
public class Type {
    private String alias;

    private Type(String alias) {
        this.alias = alias;
    }

    public static Type generate(String alias) {
        return new Type(requireNonNull(alias));
    }

    public String getAlias() {
        return alias;
    }

    public String getFullType() {
        switch (alias) {
        case ("res"):
            return "Resume";
        case ("ski"):
            return "Skill";
        case ("int"):
            return "Internship";
        case ("proj"):
            return "Project";
        default:
            // TODO: better error handling
            return "Not a valid type";
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) || this.getAlias().equals(((Type) obj).getAlias());
    }
}
