package ch.fhnw.depa.ColorPlalette.Control.ColorList;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import java.awt.Color;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

public class ColorListControl extends Control
{
    /**
     * The currently chosen color
     */
    private ObjectProperty<Color> chosenColor = new SimpleObjectProperty<>();

    /**
     * A list of all available colors
     */
    private ObservableList<Entry<String, Color>> availableColors = FXCollections.observableArrayList();

    /**
     * @return The property of the currently chosen color
     */
    public ObjectProperty<Color> chosenColorProperty()
    {
        return chosenColor;
    }

    /**
     * @param color The color
     * @param label Its label in the list
     */
    public ColorListControl addColor(Color color, String label)
    {
        availableColors.add(new SimpleEntry<>(label, color));

        return this;
    }

    /**
     * @return A hashmap of all available colors as key (color) -> value (label)
     */
    public ObservableList<Entry<String, Color>> getAvailableColors()
    {
        return availableColors;
    }

    /**
     * Initializes the default skin for this control.
     * @return The skin
     */
    @Override
    protected Skin<ColorListControl> createDefaultSkin()
    {
        return new ColorListRadioSkin(this);
    }
}
