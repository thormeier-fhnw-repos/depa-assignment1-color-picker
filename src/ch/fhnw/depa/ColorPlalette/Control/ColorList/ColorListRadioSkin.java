package ch.fhnw.depa.ColorPlalette.Control.ColorList;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SkinBase;
import javafx.scene.control.ToggleGroup;

import java.awt.Color;
import java.util.Map.Entry;

public class ColorListRadioSkin extends SkinBase<ColorListControl>
{
    /**
     * Groups the radio buttons
     */
    private ToggleGroup group = new ToggleGroup();

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    public ColorListRadioSkin(ColorListControl control)
    {
        super(control);

        ListView listView = new ListView();
        listView.setItems(control.getAvailableColors());

        listView.setCellFactory(param -> new ListCell<Entry<String, Color>>() {
            public void updateItem(Entry<String, Color> obj, boolean empty) {
                super.updateItem(obj, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    RadioButton radioButton = new RadioButton(obj.getKey());
                    radioButton.setToggleGroup(group);
                    // Add Listeners if any

                    radioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue) {
                            getSkinnable().chosenColorProperty().set(obj.getValue());
                        }
                    });

                    setGraphic(radioButton);
                }
            }
        });

        getChildren().add(listView);
    }
}
