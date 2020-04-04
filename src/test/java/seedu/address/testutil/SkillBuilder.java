package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.item.Skill;
import seedu.address.model.item.field.Level;
import seedu.address.model.item.field.Name;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Skill objects.
 */
public class SkillBuilder {
    public static final String DEFAULT_NAME = "React";
    public static final String[] DEFAULT_TAGS = {"web", "frontend"};

    private Name name;
    private Level level;
    private Set<Tag> tags = new HashSet<>();

    public SkillBuilder() {
        name = new Name(DEFAULT_NAME);
        level = Level.BASIC;
        tags.addAll(Arrays.stream(DEFAULT_TAGS).map(Tag::new).collect(Collectors.toList()));
    }

    public SkillBuilder(Skill skill) {
        name = skill.getName();
        level = skill.getLevel();
        tags.addAll(skill.getTags());
    }

    /**
     * Creates a SkillBuilder with a certain name.
     * @param name String name
     * @return SkillBuilder with new name
     */
    public SkillBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Creates a SkillBuilder with a certain skill.
     * @param level Level of skill
     * @return SkillBuilder with new level
     */
    public SkillBuilder withLevel(Level level) {
        this.level = level;
        return this;
    }

    public Skill build() {
        return new Skill(name, level, tags);
    }
}
