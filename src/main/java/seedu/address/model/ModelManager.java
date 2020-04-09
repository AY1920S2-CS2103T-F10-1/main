package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Note;
import seedu.address.model.item.ObservablePerson;
import seedu.address.model.item.Person;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedResumeBook versionedResumeBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Item> filteredItems;
    private final FilteredList<Note> filteredNotes;

    /**
     * Initializes a ModelManager with the given resumeBook and userPrefs.
     */
    public ModelManager(ReadOnlyResumeBook resumeBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(resumeBook, userPrefs);

        logger.fine("Initializing with resume book: " + resumeBook + " and user prefs " + userPrefs);

        this.versionedResumeBook = new VersionedResumeBook(resumeBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredItems = new FilteredList<>(this.versionedResumeBook.getItemToDisplayList());
        filteredNotes = new FilteredList<>(this.versionedResumeBook.getNoteToDisplayList());
    }

    public ModelManager() {
        this(new ResumeBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getResumeBookFilePath() {
        return userPrefs.getResumeBookFilePath();
    }

    @Override
    public void setResumeBookFilePath(Path resumeBookFilePath) {
        requireNonNull(resumeBookFilePath);
        userPrefs.setResumeBookFilePath(resumeBookFilePath);
    }

    //=========== ResumeBook ================================================================================

    @Override
    public void setResumeBook(ReadOnlyResumeBook resumeBook) {
        this.versionedResumeBook.resetData(resumeBook);
    }

    @Override
    public ReadOnlyResumeBook getResumeBook() {
        return versionedResumeBook;
    }

    @Override
    public ReadOnlyResumeBook getStatelessResumeBook() {
        return versionedResumeBook.getStatelessResumeBook();
    }

    //=========== User ================================================================================


    @Override
    public void setUser(Person person) {
        // Wrap the Person object so it is consistent with the setUser method in ResumeBook
        versionedResumeBook.setUser(person);
    }

    @Override
    public Person getUser() {
        return versionedResumeBook.getUser();
    }

    @Override
    public ObservablePerson getObservableUser() {
        return versionedResumeBook.getObservableUser();
    }

    //======================================= Internships ==================================================
    @Override
    public boolean hasNote(Note note) {
        requireNonNull(note);
        return versionedResumeBook.hasNote(note);
    }

    @Override
    public void addNote(Note note) {
        versionedResumeBook.addNote(note);
        updateFilteredNoteList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setNote(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);
        versionedResumeBook.setNote(target, editedNote);
    }

    @Override
    public void deleteNote(Note note) {
        versionedResumeBook.deleteNote(note);
    }

    @Override
    public Note getNote(Index index) {
        return versionedResumeBook.getNoteByIndex(index);
    }

    @Override
    public void sortNotes(Comparator<Note> sortComparator) {
        versionedResumeBook.sortNotes(sortComparator);
    }

    @Override
    public int getNoteListSize() {
        return versionedResumeBook.getNoteListSize();
    }

    //=========== Internships ================================================================================

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return versionedResumeBook.hasInternship(internship);
    }

    @Override
    public void addInternship(Internship internship) {
        versionedResumeBook.addInternship(internship);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {
        requireAllNonNull(target, editedInternship);
        versionedResumeBook.setInternship(target, editedInternship);
    }

    @Override
    public void deleteInternship(Internship key) {
        versionedResumeBook.deleteInternship(key);
    }

    @Override
    public Internship getInternshipByIndex(Index index) {
        return versionedResumeBook.getInternshipByIndex(index);
    }

    @Override
    public boolean hasInternshipId(int id) {
        return versionedResumeBook.hasInternshipId(id);
    }

    @Override
    public Internship getInternshipById(int id) {
        return versionedResumeBook.getInternshipById(id);
    }

    @Override
    public List<Internship> getInternshipsByTag(Tag tag) {
        return versionedResumeBook.getInternshipsByTag(tag);
    }

    @Override
    public void sortInternships(Comparator<Internship> sortComparator) {
        versionedResumeBook.sortInternships(sortComparator);
    }

    @Override
    public int getInternshipSize() {
        return versionedResumeBook.getInternshipSize();
    }

    @Override
    public void setInternshipToDisplay() {
        versionedResumeBook.setInternshipToDisplay();
    }

    //======================================= Projects ==========================================

    @Override
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return versionedResumeBook.hasProject(project);
    }

    @Override
    public void addProject(Project project) {
        versionedResumeBook.addProject(project);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setProject(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);
        versionedResumeBook.setProject(target, editedProject);
    }

    @Override
    public void deleteProject(Project key) {
        versionedResumeBook.deleteProject(key);
    }

    @Override
    public Project getProjectByIndex(Index index) {
        return versionedResumeBook.getProjectByIndex(index);
    }

    @Override
    public boolean hasProjectId(int id) {
        return versionedResumeBook.hasProjectId(id);
    }

    @Override
    public Project getProjectById(int id) {
        return versionedResumeBook.getProjectById(id);
    }

    @Override
    public List<Project> getProjectsByTag(Tag tag) {
        return versionedResumeBook.getProjectsByTag(tag);
    }

    @Override
    public void sortProjects(Comparator<Project> sortComparator) {
        versionedResumeBook.sortProjects(sortComparator);
    }

    @Override
    public int getProjectSize() {
        return versionedResumeBook.getProjectSize();
    }

    @Override
    public void setProjectToDisplay() {
        versionedResumeBook.setProjectToDisplay();
    }

    //======================================= Skill ===================================================

    @Override
    public boolean hasSkill(Skill skill) {
        requireNonNull(skill);
        return versionedResumeBook.hasSkill(skill);
    }

    @Override
    public void addSkill(Skill skill) {
        versionedResumeBook.addSkill(skill);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void setSkill(Skill target, Skill editedSkill) {
        requireAllNonNull(target, editedSkill);
        versionedResumeBook.setSkill(target, editedSkill);
    }

    @Override
    public void deleteSkill(Skill key) {
        versionedResumeBook.deleteSkill(key);
    }

    @Override
    public Skill getSkillByIndex(Index index) {
        return versionedResumeBook.getSkillByIndex(index);
    }

    @Override
    public boolean hasSkillId(int id) {
        return versionedResumeBook.hasSkillId(id);
    }

    @Override
    public Skill getSkillById(int id) {
        return versionedResumeBook.getSkillById(id);
    }

    @Override
    public List<Skill> getSkillsByTag(Tag tag) {
        return versionedResumeBook.getSkillsByTag(tag);
    }

    @Override
    public void sortSkills(Comparator<Skill> sortComparator) {
        versionedResumeBook.sortSkills(sortComparator);
    }

    @Override
    public int getSkillSize() {
        return versionedResumeBook.getSkillSize();
    }

    @Override
    public void setSkillToDisplay() {
        versionedResumeBook.setSkillToDisplay();
    }

    //========================================== Resume =======================================================

    @Override
    public boolean hasResume(Resume resume) {
        requireNonNull(resume);
        return versionedResumeBook.hasResume(resume);
    }

    @Override
    public void addResume(Resume resume) {
        versionedResumeBook.addResume(resume);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void editResume(Resume target, List<Integer> internshipIds, List<Integer> projectIds,
                           List<Integer> skillIds) {
        versionedResumeBook.editResume(target, internshipIds, projectIds, skillIds);
    }

    @Override
    public void setResume(Resume target, Resume editedResume) {
        requireAllNonNull(target, editedResume);
        versionedResumeBook.setResume(target, editedResume);
    }

    @Override
    public void deleteResume(Resume target) {
        versionedResumeBook.deleteResume(target);
    }

    @Override
    public Resume getResumeByIndex(Index index) {
        return versionedResumeBook.getResumeByIndex(index);
    }

    @Override
    public void sortResumes(Comparator<Resume> sortComparator) {
        versionedResumeBook.sortResumes(sortComparator);
    }

    @Override
    public boolean hasResumeId(int id) {
        return versionedResumeBook.hasResumeId(id);
    }

    @Override
    public int getResumeSize() {
        return versionedResumeBook.getResumeSize();
    }

    @Override
    public void setResumeToDisplay() {
        versionedResumeBook.setResumeToDisplay();
    }

    //=========== Filtered Item List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Item} backed by the internal list of
     * {@code versionedResumeBook}
     */
    @Override
    public ObservableList<Item> getFilteredItemList() {
        return filteredItems;
    }

    @Override
    public void setItemsToDisplay(String typeString) {
        versionedResumeBook.setItemsToDisplay(typeString);
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

    @Override
    public void updateFilteredNoteList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredNotes.setPredicate(predicate);
    }

    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return filteredNotes;
    }


    public String getDisplayType() {
        return versionedResumeBook.getDisplayType();
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoResumeBook() {
        return versionedResumeBook.canUndo();
    }

    @Override
    public boolean canRedoResumeBook() {
        return versionedResumeBook.canRedo();
    }

    @Override
    public void undoResumeBook() {
        versionedResumeBook.undo();
    }

    @Override
    public void redoResumeBook() {
        versionedResumeBook.redo();
    }

    @Override
    public void commitResumeBook() {
        versionedResumeBook.commit();
    }

    //========================================================================================================

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedResumeBook.equals(other.versionedResumeBook)
                && userPrefs.equals(other.userPrefs)
                && filteredItems.equals(other.filteredItems);
    }


}
