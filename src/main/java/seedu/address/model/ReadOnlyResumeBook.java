package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Person;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.item.UniqueItemList;

/**
 * Unmodifiable view of a resume book
 */
public interface ReadOnlyResumeBook {

    //=========== Get Detail Operations ======================================================================

    /**
     * Returns the user of the resume book.
     * @return {@code Person} item representing the user.
     */
    Person getUser();

    /**
     * Returns an unmodifiable view of the current items list as an {@code ObservableList}
     * This list will not contain any duplicate items.
     */
    ObservableList<Item> getItemToDisplayList();

    /**
     * Returns an unmodifiable view of the current internship list.
     * This list will not contain any duplicate internship.
     */
    UniqueItemList<Internship> getInternshipList();

    /**
     * Returns an unmodifiable view of the current project list.
     * This list will not contain any duplicate project.
     */
    UniqueItemList<Project> getProjectList();

    /**
     * Returns an unmodifiable view of the current skill list.
     * This list will not contain any duplicate skill.
     */
    UniqueItemList<Skill> getSkillList();

    /**
     * Returns an unmodifiable view of the current resume list.
     * This list will not contain any duplicate resume.
     */
    UniqueItemList<Resume> getResumeList();

    //=========== Query List Operations ================================================================================

    /**
     * Return an Internship item at the specified index from the internship list.
     * @param index index of internship
     * @return Internship item at {@code index}
     */
    Internship getInternshipByIndex(Index index);

    boolean hasInternshipId(int id);

    /**
     * Return a Internship item with the specified ID from the internship list.
     * @param id
     * @return Internship item with {@code id}
     */
    Internship getInternshipById(int id);

    /**
     * Return the size of the internship list.
     */
    int getInternshipSize();

    /**
     * Return a Project item at the specified index from the project list.
     * @param index index of project
     * @return Project item at {@code index}
     */
    Project getProjectByIndex(Index index);

    boolean hasProjectId(int id);

    /**
     * Return a Project item with the specified ID from the project list.
     * @param id
     * @return Project item with {@code id}
     */
    Project getProjectById(int id);

    /**
     * Return the size of the project list.
     */
    int getProjectSize();

    /**
     * Return a Skill item at the specified index from the skill list.
     * @param index index of skill
     * @return Skill item at {@code index}
     */
    Skill getSkillByIndex(Index index);

    boolean hasSkillId(int id);

    /**
     * Return a Skill item with the specified ID from the skill list.
     * @param id
     * @return Skill item with {@code id}
     */
    Skill getSkillById(int id);

    /**
     * Return the size of the skill list.
     */
    int getSkillSize();

    /**
     * Return a Resume item at the specified index from the resume list.
     * @param index index of resume
     * @return Resume item at {@code index}
     */
    Resume getResumeByIndex(Index index);

    boolean hasResumeId(int id);

    /**
     * Return the size of the resume list.
     */
    int getResumeSize();
}
