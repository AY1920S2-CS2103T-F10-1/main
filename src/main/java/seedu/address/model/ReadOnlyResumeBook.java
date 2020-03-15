package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.item.UniqueItemList;

/**
 * Unmodifiable view of a resume book
 */
public interface ReadOnlyResumeBook {

    //=========== Get List Operations ======================================================================

    /**
     * Returns an unmodifiable view of the current items list.
     * This list will not contain any duplicate items.
     */
    ObservableList<Item> getItemToDisplayList();

    /**
     * Returns an unmodifiable view of the current internship list.
     * This list will not contain any duplicate internship.
     */
    UniqueItemList getInternshipList();

    /**
     * Returns an unmodifiable view of the current project list.
     * This list will not contain any duplicate project.
     */
    UniqueItemList getProjectList();

    /**
     * Returns an unmodifiable view of the current skill list.
     * This list will not contain any duplicate skill.
     */
    UniqueItemList getSkillList();

    /**
     * Returns an unmodifiable view of the current resume list.
     * This list will not contain any duplicate resume.
     */
    UniqueItemList getResumeList();

    //=========== Query List Operations ================================================================================

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
     * Return a Resume item at the specified index from the resume list.
     * @param index
     * @return Resume item at {@code index}
     */
    Resume getResume(Index index);

    /**
     * Return the size of the resume list.
     */
    int getResumeSize();

    ///STUB
    UniqueItemList getPersonalDetailList();
}
