package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A builder class to support testing of ResumeEditCommand and TagPullCommand.
 */
public class ItemIndicesBuilder {
    private List<Integer> itemIndices;

    public ItemIndicesBuilder() {
        itemIndices = new ArrayList<>();
    }

    /**
     * Initializes the ItemIndicesBuilder with the data of {@code itemIndices}.
     * @param itemIndices
     */
    public ItemIndicesBuilder(List<Integer> itemIndices) {
        this.itemIndices = itemIndices;
    }

    /**
     * Returns the ItemIndicesBuilder which has included index i.
     * @param i an Integer representing the index of an item.
     * @return ItemIndicesBuilder with the specified index.
     */
    public ItemIndicesBuilder add(Integer i) {
        itemIndices.add(i);
        return this;
    }

    /**
     * Returns an empty Optional which represents the item prefix not supplied in the argument of the command.
     * @return An empty Optional.
     */
    public static Optional<List<Integer>> empty() {
        return Optional.empty();
    }

    public Optional<List<Integer>> build() {
        return Optional.of(itemIndices);
    }

    @Override
    public String toString() {
        return itemIndices
                .stream()
                .distinct()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
