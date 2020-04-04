package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.Item;
import seedu.address.model.util.ItemUtil;

/**
 * Panel containing the list of persons.
 */
public class ItemListPanel extends UiPart<Region> {
    private static final String FXML = "ItemListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ItemListPanel.class);

    @FXML
    private ListView<Item> itemListView;

    @FXML
    private Label internshipLabel;

    @FXML
    private Label resumeLabel;

    @FXML
    private Label skillLabel;

    @FXML
    private Label projectLabel;

    private Label current;

    public ItemListPanel(ObservableList<Item> itemList) {
        super(FXML);
        itemListView.setItems(itemList);
        itemListView.setCellFactory(listView -> new ItemListViewCell());
        current = internshipLabel;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class ItemListViewCell extends ListCell<Item> {
        @Override
        protected void updateItem(Item item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ItemCard(item, item.getIndex() + 1).getRoot());
            }
        }
    }

    /**
     * Sets the UI according to the list shown.
     * @param displayType String representation of type displayed.
     */
    public void changeStyle(String displayType) {
        current.getStyleClass().remove("typeSelected");
        current.getStyleClass().add("typeNotSelected");
        switch(displayType) {
        case ItemUtil.INTERNSHIP_ALIAS:
            internshipLabel.getStyleClass().remove("typeNotSelected");
            internshipLabel.getStyleClass().add("typeSelected");
            current = internshipLabel;
            break;
        case ItemUtil.RESUME_ALIAS:
            resumeLabel.getStyleClass().remove("typeNotSelected");
            resumeLabel.getStyleClass().add("typeSelected");
            current = resumeLabel;
            break;
        case ItemUtil.SKILL_ALIAS:
            skillLabel.getStyleClass().remove("typeNotSelected");
            skillLabel.getStyleClass().add("typeSelected");
            current = skillLabel;
            break;
        case ItemUtil.PROJECT_ALIAS:
            projectLabel.getStyleClass().remove("typeNotSelected");
            projectLabel.getStyleClass().add("typeSelected");
            current = projectLabel;
            break;
        default:
            break;
        }

    }
}
