package seedu.address.model.item.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeTest {
    private Type resume = Type.generate("res");
    private Type skill = Type.generate("ski");
    private Type internship = Type.generate("int");
    private Type project = Type.generate("proj");
    private Type invalid = Type.generate("hello");

    @Test
    public void generate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Type.generate(null));
    }

    @Test
    public void getAlias() {

        assertEquals(resume.getAlias(), "res");
        assertEquals(skill.getAlias(), "ski");
        assertNotEquals(resume.getAlias(), skill.getAlias());
    }

    @Test
    public void getFullType() {
        assertEquals(internship.getFullType(), "Internship");
        assertEquals(project.getFullType(), "Project");
        assertNotEquals(internship.getFullType(), project.getFullType());
        assertEquals(invalid.getFullType(), "Not a valid type");
    }

    @Test
    public void equal() {
        assertEquals(resume, Type.generate("res"));
        assertNotEquals(resume, internship);
        assertNotEquals(resume, invalid);
    }
}
