package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyResumeBook;
import seedu.address.model.ResumeBook;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.PersonalDetail;
import seedu.address.model.item.Resume;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "resumebook")
class JsonSerializableResumeBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPersonalDetail> persons = new ArrayList<>();
    private final List<JsonAdaptedResume> resumes = new ArrayList<>();
    private final List<JsonAdaptedInternship> internships = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableResumeBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableResumeBook(@JsonProperty("persons") List<JsonAdaptedPersonalDetail> persons,
                                      @JsonProperty("resumes") List<JsonAdaptedResume> resumes,
                                      @JsonProperty("internships") List<JsonAdaptedInternship> internships) {
        this.persons.addAll(persons);
        this.resumes.addAll(resumes);
        this.internships.addAll(internships);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableResumeBook}.
     */
    public JsonSerializableResumeBook(ReadOnlyResumeBook source) {
        persons.addAll(source
                .getPersonalDetailList()
                .asUnmodifiableObservableList()
                .stream()
                .map(x -> (PersonalDetail) x)
                .map(JsonAdaptedPersonalDetail::new)
                .collect(Collectors.toList()));
        resumes.addAll(source
                .getResumeList()
                .asUnmodifiableObservableList()
                .stream()
                .map(x -> (Resume) x)
                .map(JsonAdaptedResume::new)
                .collect(Collectors.toList()));
        internships.addAll(source
                .getInternshipList()
                .asUnmodifiableObservableList()
                .stream()
                .map(x -> (Internship) x)
                .map(JsonAdaptedInternship::new)
                .collect(Collectors.toList()));

    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ResumeBook toModelType() throws IllegalValueException {
        ResumeBook addressBook = new ResumeBook();
        for (JsonAdaptedPersonalDetail jsonAdaptedPerson : persons) {
            Item person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasItem(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addItem(person);
        }

        for (JsonAdaptedResume jsonAdaptedResume : resumes) {
            Resume resume = jsonAdaptedResume.toModelType();
            if (addressBook.hasItem(resume)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addResume(resume);
        }

        for (JsonAdaptedInternship jsonAdaptedInternship : internships) {
            Internship internship = jsonAdaptedInternship.toModelType();
            if (addressBook.hasItem(internship)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addInternship(internship);
        }
        return addressBook;
    }

}
