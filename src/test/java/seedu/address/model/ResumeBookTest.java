package seedu.address.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Note;
import seedu.address.model.item.ObservablePerson;
import seedu.address.model.item.Person;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.item.UniqueItemList;
import seedu.address.model.item.field.DisplayPicture;
import seedu.address.model.item.field.Email;
import seedu.address.model.item.field.Github;
import seedu.address.model.item.field.Level;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Phone;
import seedu.address.model.item.field.Time;
import seedu.address.model.item.field.Website;
import seedu.address.model.tag.Tag;

public class ResumeBookTest {
    /*
    private final ResumeBook resumeBook = new ResumeBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), resumeBook.getPersonalDetailList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> resumeBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyResumeBook_replacesData() {
        ResumeBook newData = getTypicalAddressBook();
        resumeBook.resetData(newData);
        assertEquals(newData, resumeBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Item editedAlice = new PersonalDetailBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Item> newItems = Arrays.asList(ALICE, editedAlice);
        ResumeBookStub newData = new ResumeBookStub(newItems);

        assertThrows(DuplicateItemException.class, () -> resumeBook.resetData(newData));
    }

    @Test
    public void hasPersonalDetail_nullPersonalDetail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> resumeBook.hasPersonalDetail(null));
    }

    @Test
    public void hasPersonalDetail_personalDetailNotInAddressBook_returnsFalse() {
        assertFalse(resumeBook.hasPersonalDetail(ALICE));
    }

    @Test
    public void hasPersonalDetail_personalDetailInAddressBook_returnsTrue() {
        resumeBook.addPersonalDetail(ALICE);
        assertTrue(resumeBook.hasPersonalDetail(ALICE));
    }

    @Test
    public void hasPersonalDetail_personalDetailWithSameIdentityFieldsInAddressBook_returnsTrue() {
        resumeBook.addPersonalDetail(ALICE);
        Item editedAlice = new PersonalDetailBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(resumeBook.hasPersonalDetail(editedAlice));
    }

    @Test
    public void getPersonalDetailList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> resumeBook.getPersonalDetailList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class ResumeBookStub implements ReadOnlyResumeBook {
        private Person person = new Person(new DisplayPicture("/images/Duke.png"), new Name("Default name"),
                "Default description",
                new Phone("000"), new Email("000@gmail.com"),
                new Github("000"), "Default university", "Default major",
                new Time("12-9999"), new Time("12-9999"), 5.0);
        private ObservablePerson observableUser = new ObservablePerson(person);
        private final ObservableList<Item> itemsToDisplay = FXCollections.observableArrayList();
        private final UniqueItemList<Internship> internships = new UniqueItemList<>();
        private final UniqueItemList<Project> projects = new UniqueItemList<>();
        private final UniqueItemList<Skill> skills = new UniqueItemList<>();
        private final UniqueItemList<Resume> resumes = new UniqueItemList<>();
        private final UniqueItemList<Note> notes = new UniqueItemList<>();

        ResumeBookStub(Collection<Item> itemsToDisplay) {
            this.itemsToDisplay.setAll(itemsToDisplay);
        }
        // TODO: fix the stub methods
        @Override
        public boolean hasSkillId(int i) {
            return false;
        }

        @Override
        public Person getUser() {
            return observableUser.getInternalPerson();
        }

        @Override
        public ObservableList<Item> getItemToDisplayList() {
            return itemsToDisplay;
        }

        @Override
        public ObservableList<Note> getNoteToDisplayList() {
            return null;
        }

        @Override
        public UniqueItemList<Internship> getInternshipList() {
            return internships;
        }

        @Override
        public UniqueItemList<Project> getProjectList() {
            return projects;
        }

        @Override
        public UniqueItemList<Skill> getSkillList() {
            return skills;
        }

        @Override
        public UniqueItemList<Resume> getResumeList() {
            return resumes;
        }

        @Override
        public UniqueItemList<Note> getNoteList() {
            return notes;
        }

        @Override
        public ObservablePerson getObservableUser() {
            return null;
        }

        public void setNote(Note target, Note editedNote) {

        }

        public void addNote(Note note) {

        }

        public boolean hasNote(Note note) {
            return false;
        }

        @Override
        public boolean hasResumeId(int resumeIndex) {
            return false;
        }

        @Override
        public boolean hasInternshipId(int i) {
            return false;
        }

        @Override
        public boolean hasProjectId(int i) {
            return false;
        }

        @Override
        public Internship getInternshipByIndex(Index index) {
            return new Internship(new Name("Company 1"), "Software Engineer", new Time("02-2019"),
                    new Time("05-2020"), "I did nothing", new HashSet<>());
        }

        @Override
        public List<Internship> getInternshipsByTag(Tag tag) {
            return null;
        }

        @Override
        public Internship getInternshipById(int id) {
            return new Internship(new Name("Company 1"), "Software Engineer", new Time("02-2019"),
                    new Time("05-2020"), "I did nothing", new HashSet<>());
        }

        @Override
        public Project getProjectByIndex(Index index) {
            return new Project(new Name("Project 1"), new Time("01-2020"), new Website("www.website.com"),
                    "I did nothing", new HashSet<>());
        }

        @Override
        public List<Project> getProjectsByTag(Tag tag) {
            return null;
        }

        @Override
        public Project getProjectById(int id) {
            return new Project(new Name("Project 1"), new Time("01-2020"), new Website("www.website.com"),
                    "I did nothing", new HashSet<>());
        }

        @Override
        public Skill getSkillByIndex(Index index) {
            return new Skill(new Name("Useless skill 1"), Level.ADVANCED, new HashSet<>(), -1);
        }

        @Override
        public List<Skill> getSkillsByTag(Tag tag) {
            return null;
        }

        @Override
        public Skill getSkillById(int id) {
            return new Skill(new Name("Useless skill 1"), Level.ADVANCED, new HashSet<>(), -1);
        }

        @Override
        public Resume getResumeByIndex(Index index) {
            return new Resume(new Name("Resume 1"), new HashSet<>());
        }

        @Override
        public int getInternshipSize() {
            return 1;
        }

        @Override
        public int getProjectSize() {
            return 1;
        }

        @Override
        public int getSkillSize() {
            return 1;
        }

        @Override
        public int getResumeSize() {
            return 1;
        }

        @Override
        public Note getNoteByIndex(Index index) {
            return null;
        }

        @Override
        public int getNoteListSize() {
            return 0;
        }

    }

}
