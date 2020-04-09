package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.item.Note;
import seedu.address.model.item.field.Name;
import seedu.address.model.item.field.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Note objects.
 */
public class NoteBuilder {
    public static final String DEFAULT_NAME = "Finish homework";
    public static final String DEFAULT_TIME = "03-2020";
    public static final String[] DEFAULT_TAGS = {"java", "2103"};


    private Name name;
    private Set<Tag> tags = new HashSet<>();
    private Time time;
    private Boolean done;

    public NoteBuilder() {
        name = new Name(DEFAULT_NAME);
        time = new Time(DEFAULT_TIME);
        done = false;
        tags.addAll(Arrays.stream(DEFAULT_TAGS).map(Tag::new).collect(Collectors.toList()));
    }

    public NoteBuilder(Note noteToCopy) {
        name = noteToCopy.getName();
        time = noteToCopy.getTime();
        done = noteToCopy.isDone();
        tags = new HashSet<>(noteToCopy.getTags());
    }

    /**
     * Creates a NoteBuilder with a certain name.
     * @param name String name
     * @return NoteBuilder with new name
     */
    public NoteBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Creates a NoteBuilder with a certain time.
     * @param time String time
     * @return NoteBuilder with new time
     */
    public NoteBuilder withTime(String time) {
        this.time = new Time(time);
        return this;
    }

    /**
     * Creates a NoteBuilder done.
     * @return NoteBuilder with done set to true.
     */
    public NoteBuilder done() {
        this.done = true;
        return this;
    }
    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Note} that we are building.
     */
    public NoteBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Returns a note.
     * @return note as as specified by descriptions of NoteBuilder.
     */
    public Note build() {
        Note note = new Note(name, time, tags);
        if (done) {
            note.markAsDone();
        }
        return note;
    }
}
