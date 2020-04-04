package seedu.address.ui.personbio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * Table to display all personal details
 */
public class UserDetailPane extends UiPart<Region> {
    private static final String FXML = "UserDetailPane.fxml";
    private static final List<String> LABELS = new ArrayList<String>(List.of("Name:", "Phone:", "Email:", "Github:",
            "University:", "Major:", "From:", "To:", "CAP:"));

    private ObservableList<FieldDataPair> list;

    @FXML
    private TableView<FieldDataPair> tableView;

    @FXML
    private TableColumn<String, String> field;

    @FXML
    private TableColumn<String, String> data;

    /**
     * Constructs a personal detail pane with a complete user profile.
     * @param name
     * @param phone
     * @param email
     * @param github
     * @param university
     * @param major
     * @param time
     * @param to
     * @param cap
     */
    public UserDetailPane(String dp, String name, String phone, String email, String github, String university,
                          String major, String time, String to, String cap) {
        super(FXML);
        field.setCellValueFactory(new PropertyValueFactory<String, String>("field"));
        data.setCellValueFactory(new PropertyValueFactory<String, String>("data"));
        list = FXCollections.observableArrayList();
        List<String> data = new ArrayList<>(List.of(name, phone, email, github, university, major, time, to, cap));
        Iterator<String> iter = data.iterator();
        LABELS.forEach(label -> {
            list.add(new FieldDataPair(label, iter.next()));
        });
        tableView.setItems(list);
    }
}
