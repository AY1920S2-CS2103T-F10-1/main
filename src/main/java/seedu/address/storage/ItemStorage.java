package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyItem;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

// TODO: Fix documentation
public interface ItemStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getItemFilePath();

    /**
     */
    Optional<ReadOnlyItem> readItem() throws DataConversionException, IOException;

    /**
     *
     */
    Optional<ReadOnlyItem> readItem(Path filePath) throws DataConversionException, IOException;

    /**
     */
    void saveItem(ReadOnlyItem item) throws IOException;

    /**
     */
    void saveItem (ReadOnlyItem item, Path filePath) throws IOException;

}
