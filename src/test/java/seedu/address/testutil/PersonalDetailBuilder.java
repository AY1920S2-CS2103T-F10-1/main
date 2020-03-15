package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.item.Item;
import seedu.address.model.item.PersonalDetail;
import seedu.address.model.item.field.Address;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonalDetailBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    public PersonalDetailBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonalBuilder with the data of {@code pdToCopy}.
     */
    public PersonalDetailBuilder(Item pdToCopy) {
        name = pdToCopy.getName();
        /*        phone = pdToCopy.getPhone();
        email = pdToCopy.getEmail();
        address = pdToCopy.getAddress();*/
        tags = new HashSet<>(pdToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code PersonalDetail} that we are building.
     */
    public PersonalDetailBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code PersonalDetail} that we are building.
     */
    public PersonalDetailBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code PersonalDetail} that we are building.
     */
    public PersonalDetailBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code PersonalDetail} that we are building.
     */
    public PersonalDetailBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code PersonalDetail} that we are building.
     */
    public PersonalDetailBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public PersonalDetail build() {
        return new PersonalDetail(name, phone, email, address, tags);
    }

}
