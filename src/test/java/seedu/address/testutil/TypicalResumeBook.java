package seedu.address.testutil;

import seedu.address.model.ResumeBook;

/**
 * A utility class containing a list of {@code ResumeBook} objects to be used in tests.
 */
public class TypicalResumeBook {
    public static final ResumeBook TYPICAL = new ResumeBookBuilder()
            .withInternship(TypicalInternship.GOOGLE)
            .withInternship(TypicalInternship.NINJA_VAN)
            .withPerson(TypicalPerson.ALICE)
            .withProject(TypicalProject.ORBITAL)
            .withProject(TypicalProject.DUKE)
            .withSkill(TypicalSkill.REACT)
            .withSkill(TypicalSkill.GIT)
            .withResume(TypicalResume.ME_RESUME)
            .withResume(TypicalResume.SE_RESUME)
            .build();
    public static final ResumeBook TYPICAL_WITHOUT_GOOGLE = new ResumeBookBuilder()
            .withInternship(TypicalInternship.GOOGLE)
            .withPerson(TypicalPerson.ALICE)
            .withProject(TypicalProject.ORBITAL)
            .withProject(TypicalProject.DUKE)
            .build();

}
