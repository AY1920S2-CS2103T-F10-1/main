package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;
/*import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;*/
/*import static seedu.address.testutil.TypicalPersonalDetails.ALICE;
import static seedu.address.testutil.TypicalPersonalDetails.HOON;
import static seedu.address.testutil.TypicalPersonalDetails.IDA;
import static seedu.address.testutil.TypicalPersonalDetails.getTypicalAddressBook;*/
/*
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyResumeBook;
import seedu.address.model.ResumeBook;*/

public class JsonAddressBookStorageTest {
//
//    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");
//
//    @TempDir
//    public Path testFolder;
//
//    @Test
//    public void readAddressBook_nullFilePath_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> readAddressBook(null));
//    }
//
//    private java.util.Optional<ReadOnlyResumeBook> readAddressBook(String filePath) throws Exception {
//        return new JsonAddressBookStorage(Paths.get(filePath)).readAddressBook(addToTestDataPathIfNotNull(filePath));
//    }
//
//    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
//        return prefsFileInTestDataFolder != null
//                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
//                : null;
//    }
//
//    @Test
//    public void read_missingFile_emptyResult() throws Exception {
//        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
//    }
//
//    @Test
//    public void read_notJsonFormat_exceptionThrown() {
//        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
//    }
//
//    @Test
//    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
//        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
//    }
//
//    @Test
//    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
//        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.json"));
//    }
//
//    @Test
//    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
//        Path filePath = testFolder.resolve("TempAddressBook.json");
//        ResumeBook original = getTypicalAddressBook();
//        JsonAddressBookStorage jsonAddressBookStorage = new JsonAddressBookStorage(filePath);
//
//        // Save in new file and read back
//        jsonAddressBookStorage.saveAddressBook(original, filePath);
//        ReadOnlyResumeBook readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
//        //assertEquals(original, new ResumeBook(readBack));
//
//        // Modify data, overwrite exiting file, and read back
//        original.addPersonalDetail(HOON);
//        original.removePersonalDetail(ALICE);
//        jsonAddressBookStorage.saveAddressBook(original, filePath);
//        readBack = jsonAddressBookStorage.readAddressBook(filePath).get();
//        //assertEquals(original, new ResumeBook(readBack));
//
//        // Save and read without specifying file path
//        original.addPersonalDetail(IDA);
//        jsonAddressBookStorage.saveAddressBook(original); // file path not specified
//        readBack = jsonAddressBookStorage.readAddressBook().get(); // file path not specified
//        //assertEquals(original, new ResumeBook(readBack));
//
//    }
//
//    @Test
//    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
//    }
//
//    /**
//     * Saves {@code addressBook} at the specified {@code filePath}.
//     */
//    private void saveAddressBook(ReadOnlyResumeBook addressBook, String filePath) {
//        try {
//            new JsonAddressBookStorage(Paths.get(filePath))
//                    .saveAddressBook(addressBook, addToTestDataPathIfNotNull(filePath));
//        } catch (IOException ioe) {
//            throw new AssertionError("There should not be an error writing to the file.", ioe);
//        }
//    }
//
//    @Test
//    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> saveAddressBook(new ResumeBook(), null));
//    }
}
