package seedu.address.model.item.field;

/**
 * Represents a Item's type in the address book.
 */
public class Type {
    private String alias;

    private Type(String alias) {
        this.alias = alias;
    }

    public static Type generate(String alias) {
        return new Type(alias);
    }

    public String getAlias() {
        return alias;
    }

    public String getFullType() {
        switch (alias) {
        case ("res"):
            return "Resume";
        case ("acad"):
            return "Academic";
        case ("pd"):
            return "Personal Detail";
        case ("edu"):
            return "Education";
        case ("ski"):
            return "Skill";
        case ("int"):
            return "Internship";
        case ("proj"):
            return "Project";
        case ("bio"):
            return "User";
        default:
            return "Not a valid type";
        }
    }
}
