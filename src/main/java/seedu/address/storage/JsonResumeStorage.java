package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyItem;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

public class JsonResumeStorage implements ItemStorage {
    @Override
    public Path getItemFilePath() {
        return null;
    }

    @Override
    public Optional<ReadOnlyItem> readItem() throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public Optional<ReadOnlyItem> readItem(Path filePath) throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public void saveItem(ReadOnlyItem item) throws IOException {

    }

    @Override
    public void saveItem(ReadOnlyItem item, Path filePath) throws IOException {

    }
}
