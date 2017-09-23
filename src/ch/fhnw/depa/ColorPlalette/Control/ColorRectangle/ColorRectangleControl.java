package ch.fhnw.depa.ColorPlalette.Control.ColorRectangle;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import java.awt.Color;

public class ColorRectangleControl extends Control
{
    /**
     * Colour property to set/read property from
     */
    private ObjectProperty<Color> color = new SimpleObjectProperty<>();

    /**
     * Width of the canvas in pixels
     */
    private int width;

    /**
     * Height of the canvas in pixels
     */
    private int height;

    /**
     * Displays a given color
     */
    public ColorRectangleControl(int width, int height, Color initialColor)
    {
        this.width = width;
        this.height = height;

        colorProperty().setValue(initialColor);
    }

    /**
     * Initializes the default skin for this control.
     * @return The skin
     */
    @Override
    protected Skin<ColorRectangleControl> createDefaultSkin()
    {
        return new ColorRectangleSkin(this, width, height);
    }

    /**
     * @return The color property of this control
     */
    public ObjectProperty<Color> colorProperty()
    {
        return color;
    }
}
