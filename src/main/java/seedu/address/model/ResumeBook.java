package seedu.address.model;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.model.item.Internship;
import seedu.address.model.item.Item;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;
import seedu.address.model.item.UniqueItemList;

/**
 * Wraps all data at the resume-book level
 * Duplicates are not allowed (by .isSame comparison)
 */
public class ResumeBook implements ReadOnlyResumeBook {

    // Should be all caps but check style complain
    private final UniqueItemList itemsToDisplay;
    private final UniqueItemList internships;
    private final UniqueItemList projects;
    private final UniqueItemList skills;
    private final UniqueItemList resumes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        itemsToDisplay = new UniqueItemList();
        internships = new UniqueItemList();
        projects = new UniqueItemList();
        skills = new UniqueItemList();
        resumes = new UniqueItemList();
    }

    public ResumeBook() {}

    /**
     * Creates an ResumeBook using the Items in the {@code toBeCopied}
     */
    public ResumeBook(ReadOnlyResumeBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //=========== Replace display list =====================================================================

    /**
     * Replaces the contents of the item list with {@code items}.
     * {@code items} must not contain duplicate items.
     */
    public void setItemsToDisplay(UniqueItemList itemsToDisplay) {
        this.itemsToDisplay.setItems(itemsToDisplay);
    }

    // STUB
    public void setItemsToDisplay(String type) {}

    /**
     * Replaces the contents of the item list to the content of the internship list.
     */
    public void setInternshipToDisplay() {
        setItemsToDisplay(internships);
    }

    /**
     * Replaces the contents of the item list to the content of the project list.
     */
    public void setProjectToDisplay() {
        setItemsToDisplay(projects);
    }

    /**
     * Replaces the contents of the item list to the content of the skill list.
     */
    public void setSkillToDisplay() {
        setItemsToDisplay(skills);
    }

    /**
     * Replaces the contents of the item list to the content of the resume list.
     */
    public void setResumeToDisplay() {
        setItemsToDisplay(resumes);
    }

    //=========== Overwrite list ================================================================================

    /**
     * Replaces the contents of the internship list with {@code internships}.
     * {@code internships} must not contain duplicate items.
     */
    public void setInternships(UniqueItemList internships) {
        this.internships.setItems(internships);
    }

    /**
     * Replaces the contents of the project list with {@code projects}.
     * {@code projects} must not contain duplicate items.
     */
    public void setProjects(UniqueItemList projects) {
        this.projects.setItems(projects);
    }

    /**
     * Replaces the contents of the skill list with {@code skills}.
     * {@code skills} must not contain duplicate items.
     */
    public void setSkills(UniqueItemList skills) {
        this.skills.setItems(skills);
    }

    /**
     * Replaces the contents of the resume list with {@code resumes}.
     * {@code resumes} must not contain duplicate items.
     */
    public void setResumes(UniqueItemList resumes) {
        this.resumes.setItems(resumes);
    }

    /**
     * Resets the existing data of this {@code ResumeBook} with {@code newData}.
     */
    public void resetData(ReadOnlyResumeBook newData) {
        requireNonNull(newData);
        setInternships(newData.getInternshipList());
        setProjects(newData.getProjectList());
        setSkills(newData.getSkillList());
        setResumes(newData.getResumeList());
    }

    //=========== Internships ================================================================================

    /**
     * Returns true if an internship with the same identity as {@code internship} exists in the resume book.
     */
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internships.contains(internship);
    }

    /**
     * Adds an internship to the resume book.
     * The internship must not already exist in the resume book.
     */
    public void addInternship(Internship internship) {
        requireNonNull(internship);
        internships.add(internship);
    }

    /**
     * Replaces the given internship {@code target} in the list with {@code editedInternship}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedInternship} must not be the same as another existing internship in the resume book.
     */
    public void setInternship(Internship target, Internship editedInternship) {
        internships.setItem(target, editedInternship);
    }

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void deleteInternship(Internship key) {
        internships.remove(key);
    }

    @Override
    public Internship getInternship(Index index) {
        return (Internship) internships.asUnmodifiableObservableList().get(index.getZeroBased());
    }

    @Override
    public int getInternshipSize() {
        return internships.getSize();
    }

    //=========== Projects ================================================================================

    /**
     * Returns true if a project with the same identity as {@code project} exists in the resume book.
     */
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return projects.contains(project);
    }

    /**
     * Adds a project to the resume book.
     * The project must not already exist in the resume book.
     */
    public void addProject(Project project) {
        requireNonNull(project);
        projects.add(project);
    }

    /**
     * Replaces the given project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedProject} must not be the same as another existing project in the resume book.
     */
    public void setProject(Project target, Project editedProject) {
        projects.setItem(target, editedProject);
    }

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void deleteProject(Project key) {
        projects.remove(key);
    }

    @Override
    public Project getProject(Index index) {
        return (Project) projects.asUnmodifiableObservableList().get(index.getZeroBased());
    }

    @Override
    public int getProjectSize() {
        return projects.getSize();
    }

    //=========== Skills ================================================================================

    /**
     * Returns true if a skill with the same identity as {@code skill} exists in the resume book.
     */
    public boolean hasSkill(Skill skill) {
        requireNonNull(skill);
        return skills.contains(skill);
    }

    /**
     * Adds a skill to the resume book.
     * The skill must not already exist in the resume book.
     */
    public void addSkill(Skill skill) {
        requireNonNull(skill);
        skills.add(skill);
    }

    /**
     * Replaces the given skill {@code target} in the list with {@code editedSkill}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedSkill} must not be the same as another existing skill in the resume book.
     */
    public void setSkill(Skill target, Skill editedSkill) {
        skills.setItem(target, editedSkill);
    }

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void deleteSkill(Skill key) {
        skills.remove(key);
    }

    @Override
    public Skill getSkill(Index index) {
        return (Skill) skills.asUnmodifiableObservableList().get(index.getZeroBased());
    }

    @Override
    public int getSkillSize() {
        return skills.getSize();
    }

    //=========== Resumes ================================================================================

    /**
     * Returns true if a resume with the same identity as {@code resume} exists in the resume book.
     */
    public boolean hasResume(Resume resume) {
        requireNonNull(resume);
        return resumes.contains(resume);
    }

    /**
     * Adds a resume to the resume book.
     * The resume must not already exist in the resume book.
     */
    public void addResume(Resume resume) {
        requireNonNull(resume);
        resumes.add(resume);
    }

    /**
     * Replaces the given resume {@code target} in the list with {@code editedResume}.
     * {@code target} must exist in the resume book.
     * The identity of {@code editedResume} must not be the same as another existing resume in the resume book.
     */
    public void setResume(Resume target, Resume editedResume) {
        resumes.setItem(target, editedResume);
    }

    /**
     * Removes {@code key} from this {@code ResumeBook}.
     * {@code key} must exist in the resume book.
     */
    public void deleteResume(Resume key) {
        resumes.remove(key);
    }

    @Override
    public Resume getResume(Index index) {
        return (Resume) resumes.asUnmodifiableObservableList().get(index.getZeroBased());
    }

    @Override
    public int getResumeSize() {
        return resumes.getSize();
    }

    //=========== Util methods ================================================================================

    @Override
    public String toString() {
        return itemsToDisplay.asUnmodifiableObservableList().size() + " items";
        // TODO: refine later
    }

    @Override
    public ObservableList<Item> getItemToDisplayList() {
        return itemsToDisplay.asUnmodifiableObservableList();
    }

    @Override
    public UniqueItemList getInternshipList() {
        return internships;
    }

    @Override
    public UniqueItemList getProjectList() {
        return projects;
    }

    @Override
    public UniqueItemList getSkillList() {
        return skills;
    }

    @Override
    public UniqueItemList getResumeList() {
        return resumes;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResumeBook // instanceof handles nulls
                && itemsToDisplay.equals(((ResumeBook) other).itemsToDisplay))
                && internships.equals(((ResumeBook) other).internships)
                && projects.equals(((ResumeBook) other).projects)
                && skills.equals(((ResumeBook) other).skills)
                && resumes.equals(((ResumeBook) other).resumes);
    }

    @Override
    public int hashCode() {
        return itemsToDisplay.hashCode();
    }

    //STUBS
    public UniqueItemList getPersonalDetailList() {
        return new UniqueItemList();
    }
    public boolean hasItem(Item item) {
        return false;
    }
    public void addItem(Item item) {}
    public void deleteItem(Item item) {}
    public void setItem(Item target, Item edit) {}
}
