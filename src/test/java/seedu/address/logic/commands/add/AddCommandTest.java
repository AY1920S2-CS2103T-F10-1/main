package seedu.address.logic.commands.add;

import java.nio.file.Path;
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
import seedu.address.model.item.Person;
import seedu.address.model.item.Project;
import seedu.address.model.item.Resume;
import seedu.address.model.item.Skill;


public class AddCommandTest {
    /**
     * A default model stub that have all of the methods failing.
     */
    protected class ModelStub implements Model {
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
        public void setUser(Person person) {

        }

        @Override
        public Person getUser() {
            return null;
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
        public Internship getInternship(Index index) {
            return null;
        }

        @Override
        public Internship getInternshipById(int id) {
            return null;
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
        public Project getProject(Index index) {
            return null;
        }

        @Override
        public Project getProjectById(int id) {
            return null;
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
        public Skill getSkill(Index index) {
            return null;
        }

        @Override
        public Skill getSkillById(int id) {
            return null;
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
        public Resume getResume(Index index) {
            return null;
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
        public void updateFilteredItemList(Predicate<Item> predicate) {

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
        public boolean hasItem(Item item) {
            return false;
        }

        @Override
        public void addItem(Item item) {

        }

        @Override
        public void deleteItem(Item item) {

        }

        @Override
        public void setItem(Item target, Item edit) {

        }

        @Override
        public void setItemsToDisplay(String type) {

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


}
