package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' resume book file path.
     */
    Path getResumeBookFilePath();

    /**
     * Sets the user prefs' resume book file path.
     */
    void setResumeBookFilePath(Path addressBookFilePath);

    /**
     * Replaces resume book data with the data in {@code resumeBook}.
     */
    void setResumeBook(ReadOnlyResumeBook resumeBook);

    /** Returns the AddressBook */
    ReadOnlyResumeBook getResumeBook();

    //=========== Internships ================================================================================

    /**
     * Returns true if an internship with the same identity as {@code internship} exists in the resume book.
     */
    boolean hasInternship(Internship internship);

    /**
     * Adds the given internship.
     * {@code internship} must not already exist in the resume book.
     */
    void addInternship(Internship internship);

    /**
     * Replaces the given internship {@code target} with {@code editedInternship}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedInternship} must not be the same as another existing internship in the resume book.
     */
    void setInternship(Internship target, Internship editedInternship);

    /**
     * Deletes the given internship.
     * The internship must exist in the resume book.
     */
    void deleteInternship(Internship internship);

    /**
     * Return an Internship item at the specified index from the internship list.
     * @param index
     * @return Internship item at {@code index}
     */
    Internship getInternship(Index index);

    /**
     * Return the size of the internship list.
     */
    int getInternshipSize();

    /**
     * Updates the List as the internship list.
     */
    void setInternshipToDisplay();

    //=========== Projects ================================================================================

    /**
     * Returns true if a project with the same identity as {@code project} exists in the resume book.
     */
    public boolean hasProject(Project project);

    /**
     * Adds a project to the resume book.
     * The project must not already exist in the resume book.
     */
    public void addProject(Project project);

    /**
     * Replaces the given project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedProject} must not be the same as another existing project in the resume book.
     */
    public void setProject(Project target, Project editedProject);

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void deleteProject(Project key);

    /**
     * Return a Project item at the specified index from the project list.
     * @param index
     * @return Project item at {@code index}
     */
    Project getProject(Index index);

    /**
     * Return the size of the project list.
     */
    int getProjectSize();

    /**
     * Updates the List as the project list.
     */
    void setProjectToDisplay();

    //=========== Skills ================================================================================

    /**
     * Returns true if a skill with the same identity as {@code skill} exists in the resume book.
     */
    public boolean hasSkill(Skill skill);

    /**
     * Adds a skill to the resume book.
     * The skill must not already exist in the resume book.
     */
    public void addSkill(Skill skill);

    /**
     * Replaces the given skill {@code target} in the list with {@code editedSkill}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedSkill} must not be the same as another existing skill in the resume book.
     */
    public void setSkill(Skill target, Skill editedSkill);

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void deleteSkill(Skill key);

    /**
     * Return a Skill item at the specified index from the skill list.
     * @param index
     * @return Skill item at {@code index}
     */
    Skill getSkill(Index index);

    /**
     * Return the size of the skill list.
     */
    int getSkillSize();

    /**
     * Updates the List as the skill list.
     */
    void setSkillToDisplay();

    //=========== Resumes ================================================================================

    /**
     * Returns true if a resume with the same identity as {@code resume} exists in the resume book.
     */
    boolean hasResume(Resume resume);

    /**
     * Adds the given resume.
     * {@code resume} must not already exist in the resume book.
     */
    void addResume(Resume resume);

    /**
     * Replaces the given resume {@code target} with {@code editedResume}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedResume} must not be the same as another existing resume in the resume book.
     */
    void setResume(Resume target, Resume editedResume);

    /**
     * Deletes the given resume.
     * The resume must exist in the resume book.
     */
    void deleteResume(Resume resume);

    /**
     * Return a Resume item at the specified index from the resume list.
     * @param index
     * @return Resume item at {@code index}
     */
    Resume getResume(Index index);

    /**
     * Return the size of the resume list.
     */
    int getResumeSize();

    /**
     * Updates the List as the resume list.
     */
    void setResumeToDisplay();

    //=========== Item Lists ================================================================================

    /** Returns an unmodifiable view of the filtered item list */
    ObservableList<Item> getFilteredItemList();

    /**
     * Updates the filter of the filtered item list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredItemList(Predicate<Item> predicate);

    //// STUBS
    boolean hasItem(Item item);

    void addItem(Item item);

    void deleteItem(Item item);

    void setItem(Item target, Item edit);

    void setItemsToDisplay(String type);
}
