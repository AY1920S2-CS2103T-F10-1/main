package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.Item;

/**
 * Panel containing the list of persons.
 */
public class ItemDisplayList extends UiPart<Region> {
    private static final String FXML = "ItemDisplayList.fxml";
    private final Logger logger = LogsCenter.getLogger(ItemDisplayList.class);

    @FXML
    private ListView<String> itemDisplayList;

    public ItemDisplayList(ObservableList<String> itemList) {
        super(FXML);
        itemDisplayList.setItems(itemList);
        itemDisplayList.setCellFactory(lst ->
            new ListCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setPrefHeight(45.0);
                        setText(null);
                    } else {
                        setPrefHeight(50);
                        setText(item);
                    }
                }
        });
    }

    public void updateDisplayItem(String[] strings) {
        itemDisplayList.setItems(FXCollections.observableArrayList(strings));
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
}
