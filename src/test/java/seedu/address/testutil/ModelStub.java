package seedu.address.testutil;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyResumeBook;
import seedu.address.model.ReadOnlyUserPrefs;
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
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {

    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return null;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return null;
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {

    }

    @Override
    public Path getResumeBookFilePath() {
        return null;
    }

    @Override
    public void setResumeBookFilePath(Path addressBookFilePath) {

    }

    @Override
    public void setResumeBook(ReadOnlyResumeBook resumeBook) {

    }

    @Override
    public ReadOnlyResumeBook getResumeBook() {
        return null;
    }

    @Override
    public ReadOnlyResumeBook getStatelessResumeBook() {
        return null;
    }

    @Override
    public void setUser(Person person) {

    }

    @Override
    public Person getUser() {
        return null;
    }

    @Override
    public ObservablePerson getObservableUser() {
        return null;
    }

    @Override
    public boolean hasNote(Note note) {
        return false;
    }

    @Override
    public void addNote(Note note) {

    }

    @Override
    public void setNote(Note target, Note editedNote) {

    }

    @Override
    public void deleteNote(Note note) {

    }

    @Override
    public Note getNote(Index index) {
        return null;
    }

    @Override
    public void sortNotes(Comparator<Note> sortComparator) {

    }

    @Override
    public int getNoteListSize() {
        return 0;
    }

    @Override
    public boolean hasInternship(Internship internship) {
        return false;
    }

    @Override
    public void addInternship(Internship internship) {

    }

    @Override
    public void setInternship(Internship target, Internship editedInternship) {

    }

    @Override
    public void deleteInternship(Internship internship) {

    }

    @Override
    public Internship getInternshipByIndex(Index index) {
        return null;
    }

    @Override
    public Internship getInternshipById(int id) {
        return null;
    }

    @Override
    public List<Internship> getInternshipsByTag(Tag tag) {
        return null;
    }

    @Override
    public void sortInternships(Comparator<Internship> sortComparator) {

    }

    @Override
    public int getInternshipSize() {
        return 0;
    }

    @Override
    public void setInternshipToDisplay() {

    }

    @Override
    public boolean hasProject(Project project) {
        return false;
    }

    @Override
    public void addProject(Project project) {

    }

    @Override
    public void setProject(Project target, Project editedProject) {

    }

    @Override
    public void deleteProject(Project key) {

    }

    @Override
    public Project getProjectByIndex(Index index) {
        return null;
    }

    @Override
    public Project getProjectById(int id) {
        return null;
    }

    @Override
    public List<Project> getProjectsByTag(Tag tag) {
        return null;
    }

    @Override
    public void sortProjects(Comparator<Project> sortComparator) {

    }

    @Override
    public int getProjectSize() {
        return 0;
    }

    @Override
    public void setProjectToDisplay() {

    }

    @Override
    public boolean hasSkill(Skill skill) {
        return false;
    }

    @Override
    public void addSkill(Skill skill) {

    }

    @Override
    public void setSkill(Skill target, Skill editedSkill) {

    }

    @Override
    public void deleteSkill(Skill key) {

    }

    @Override
    public Skill getSkillByIndex(Index index) {
        return null;
    }

    @Override
    public Skill getSkillById(int id) {
        return null;
    }

    @Override
    public List<Skill> getSkillsByTag(Tag tag) {
        return null;
    }

    @Override
    public void sortSkills(Comparator<Skill> sortComparator) {

    }

    @Override
    public int getSkillSize() {
        return 0;
    }

    @Override
    public void setSkillToDisplay() {

    }

    @Override
    public boolean hasResume(Resume resume) {
        return false;
    }

    @Override
    public void addResume(Resume resume) {

    }

    @Override
    public void setResume(Resume target, Resume editedResume) {

    }

    @Override
    public void editResume(Resume target, List<Integer> internshipsId, List<Integer> projectsId,
                           List<Integer> skillsId) {

    }

    @Override
    public boolean hasResumeId(int resumeIndex) {
        return false;
    }

    @Override
    public void deleteResume(Resume resume) {

    }

    @Override
    public Resume getResumeByIndex(Index index) {
        return null;
    }

    @Override
    public void sortResumes(Comparator<Resume> sortComparator) {

    }

    @Override
    public int getResumeSize() {
        return 0;
    }

    @Override
    public void setResumeToDisplay() {

    }

    @Override
    public ObservableList<Item> getFilteredItemList() {
        return null;
    }

    @Override
    public void setItemsToDisplay(String typeString) {

    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {

    }

    @Override
    public ObservableList<Note> getFilteredNoteList() {
        return null;
    }

    @Override
    public void updateFilteredNoteList(Predicate<Item> predicate) {}

    @Override
    public String getDisplayType() {
        return "";
    }

    @Override
    public boolean canUndoResumeBook() {
        return false;
    }

    @Override
    public boolean canRedoResumeBook() {
        return false;
    }

    @Override
    public void undoResumeBook() {

    }

    @Override
    public void redoResumeBook() {

    }

    @Override
    public void commitResumeBook() {

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
    public boolean hasSkillId(int i) {
        return false;
    }
}
