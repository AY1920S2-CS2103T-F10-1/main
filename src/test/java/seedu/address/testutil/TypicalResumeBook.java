package seedu.address.testutil;

import seedu.address.model.ResumeBook;

/**
 * A utility class containing a list of {@code ResumeBook} objects to be used in tests.
 */
public class TypicalResumeBook {
    public static final ResumeBook TYPICAL = new ResumeBookBuilder()
            .withInternship(TypicalInternship.GOOGLE)
            .withInternship(TypicalInternship.NINJA_VAN)
            .withInternship(TypicalInternship.PAYPAL)
            .withNote(TypicalNote.FINISH_CS_2103)
            .withPerson(TypicalPerson.ALICE)
            .withProject(TypicalProject.ORBITAL)
            .withProject(TypicalProject.DUKE)
            .withSkill(TypicalSkill.REACT)
            .withSkill(TypicalSkill.GIT)
            .withResume(TypicalResume.ME_RESUME)
            .withResume(TypicalResume.SE_RESUME)
            .build();
    public static final ResumeBook TYPICAL_COPY = new ResumeBookBuilder()
            .withInternship(TypicalInternship.GOOGLE)
            .withInternship(TypicalInternship.NINJA_VAN)
            .withInternship(TypicalInternship.PAYPAL)
            .withNote((new NoteBuilder(TypicalNote.FINISH_CS_2103).build()))
            .withPerson(TypicalPerson.ALICE)
            .withProject(TypicalProject.ORBITAL)
            .withProject(TypicalProject.DUKE)
            .withSkill(TypicalSkill.REACT)
            .withSkill(TypicalSkill.GIT)
            .withResume(TypicalResume.ME_RESUME)
            .withResume(TypicalResume.SE_RESUME)
            .build();
    public static final ResumeBook TYPICAL_WITHOUT_GOOGLE = new ResumeBookBuilder()
            .withInternship(TypicalInternship.NINJA_VAN)
            .withInternship(TypicalInternship.PAYPAL)
            .withNote(TypicalNote.FINISH_CS_2103)
            .withPerson(TypicalPerson.ALICE)
            .withProject(TypicalProject.ORBITAL)
            .withSkill(TypicalSkill.REACT)
            .withResume(TypicalResume.ME_RESUME)
            .build();
    public static final ResumeBook TYPICAL_WITHOUT_GOOGLE_COPY = new ResumeBookBuilder()
            .withInternship(TypicalInternship.NINJA_VAN)
            .withInternship(TypicalInternship.PAYPAL)
            .withNote((new NoteBuilder(TypicalNote.FINISH_CS_2103).build()))
            .withPerson(TypicalPerson.ALICE)
            .withProject(TypicalProject.ORBITAL)
            .withSkill(TypicalSkill.REACT)
            .withResume(TypicalResume.ME_RESUME)
            .build();

    // This TypicalResumeBook is used mainly for the ResumeEdit and TagPull Command tests
    // It will be best not to add more things, nor add Tags to the Items that are added
    public static final ResumeBook TYPICAL_WITH_FILLED_RESUME = new ResumeBookBuilder()
            .withInternship(TypicalInternship.GOOGLE)
            .withInternship(TypicalInternship.NINJA_VAN)
            .withPerson(TypicalPerson.ALICE)
            .withProject(TypicalProject.ORBITAL)
            .withProject(TypicalProject.DUKE)
            .withSkill(TypicalSkill.REACT)
            .withSkill(TypicalSkill.GIT)
            .withResume(TypicalResume.ME_RESUME)
            .withResume(TypicalResume.SE_RESUME)
            .withResume(TypicalResume.FILLED_RESUME)
            .build();
}
