package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyResumeBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonResumeBookStorage implements ResumeBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonResumeBookStorage.class);

    private Path filePath;

    public JsonResumeBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getResumeBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyResumeBook> readResumeBook() throws DataConversionException {
        return readResumeBook(filePath);
    }

    /**
     * Similar to {@link #readResumeBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyResumeBook> readResumeBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableResumeBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableResumeBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyResumeBook addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyResumeBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyResumeBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableResumeBook(addressBook), filePath);
    }

}