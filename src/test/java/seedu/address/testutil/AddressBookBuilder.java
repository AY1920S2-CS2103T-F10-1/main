package seedu.address.testutil;

import seedu.address.model.ResumeBook;
import seedu.address.model.item.PersonalDetail;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ResumeBook addressBook;

    public AddressBookBuilder() {
        addressBook = new ResumeBook();
    }

    public AddressBookBuilder(ResumeBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(PersonalDetail person) {
        addressBook.addItem(person);
        return this;
    }

    public ResumeBook build() {
        return addressBook;
    }
}
