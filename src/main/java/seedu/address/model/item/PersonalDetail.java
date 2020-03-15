package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import seedu.address.model.item.field.Address;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Type;
import seedu.address.model.tag.Tag;

/**
 * Represents a PersonalDetail in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class PersonalDetail extends Item {

    // Identity fields
    private final Type type;

    // Data fields
    private final Phone phone;
    private final Email email;
    private final Address address;

    /**
     * Every field must be present and not null.
     */
    public PersonalDetail(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, tags);
        requireAllNonNull(name, phone, email, address, tags);
        this.type = Type.generate("pd");
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns true if both personal details of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two personal details.
     */
    public boolean isSame(PersonalDetail otherPerson) {
        return isSame(otherPerson)
                && (otherPerson.getPhone().equals(getPhone()) || otherPerson.getEmail().equals(getEmail()));
    }

    @Override
    public String getSummary() {
        return "";
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress());
        return builder.toString();
    }

}
