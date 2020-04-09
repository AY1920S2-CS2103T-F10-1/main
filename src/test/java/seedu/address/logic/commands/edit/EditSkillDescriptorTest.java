package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.Skill;
import seedu.address.testutil.SkillBuilder;

public class EditSkillDescriptorTest {
    Skill sampleSkill = new SkillBuilder().build();
    private EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();

    @Test
    public void equals() {
        editSkillDescriptor.setName(sampleSkill.getName());
        editSkillDescriptor.setTags(sampleSkill.getTags());
        editSkillDescriptor.setLevel(sampleSkill.getLevel());

        EditSkillDescriptor editSkillDescriptorToTest =
                new EditSkillDescriptor(editSkillDescriptor);

        // same name -> returns true
        assertEquals(editSkillDescriptor.getName().get(), sampleSkill.getName());

        // same object -> returns true
        assertEquals(editSkillDescriptor.getTags().get(), sampleSkill.getTags());

        // same name -> returns true
        assertEquals(editSkillDescriptor.getLevel().get(), sampleSkill.getLevel());

        // same name -> returns true
        assertEquals(editSkillDescriptorToTest.getName().get(), editSkillDescriptor.getName().get());

        // same object -> returns true
        assertEquals(editSkillDescriptorToTest.getTags().get(), editSkillDescriptor.getTags().get());

        // same object -> returns true
        assertEquals(editSkillDescriptorToTest.getLevel().get(), editSkillDescriptor.getLevel().get());

        // same descriptor object -> returns true
        assertEquals(editSkillDescriptor, editSkillDescriptorToTest);

        // checks two descriptors if same -> returns true
        assertTrue(editSkillDescriptor.equals(editSkillDescriptorToTest));
    }

    @Test
    public void field_isAnyNonNull_FalseIfAllNull() {
        EditSkillDescriptor editSkillDescriptor = new EditSkillDescriptor();
        assertEquals(editSkillDescriptor.isAnyFieldEdited(), false);
    }
}
