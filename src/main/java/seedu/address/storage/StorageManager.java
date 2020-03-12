package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyResumeBook;
import seedu.address.model.ReadOnlyItem;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    // TODO: Change the first parameter to be an ArrayList of Storage
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyResumeBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(Paths.get("data" , "personaldetail.json"));
    }

    @Override
    public Optional<ReadOnlyResumeBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyResumeBook addressBook) throws IOException {
        saveAddressBook(addressBook, Paths.get("data" , "personaldetail.json"));
    }

    @Override
    public void saveAddressBook(ReadOnlyResumeBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }
    // --------------------------------------------------------------------------------------------------------
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
    //------------------------------------------------------------------------------------------------------------
}
