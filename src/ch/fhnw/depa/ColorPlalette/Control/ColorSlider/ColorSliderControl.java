package ch.fhnw.depa.ColorPlalette.Control.ColorSlider;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class ColorSliderControl extends Control
{
    /**
     * The initial value of this control
     */
    private int initialValue;

    /**
     * Minimum value of the control
     */
    private int minValue;

    /**
     * Maximum value of the control
     */
    private int maxValue;

    /**
     * Label to display next to the control
     */
    private String label;

    /**
     * Central property to
     */
    private IntegerProperty color = new SimpleIntegerProperty();

    /**
     * Slider + input field for colour choosing
     * @param initialValue Initial colour value
     */
    public ColorSliderControl(String label, int initialValue, int minValue, int maxValue)
    {
        this.initialValue = initialValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.label = label;
    }

    /**
     * Initializes the default skin for this control.
     * @return The skin
     */
    @Override
    protected Skin<ColorSliderControl> createDefaultSkin()
    {
        return new ColorSliderSkin(this, label, initialValue, minValue, maxValue);
    }

    /**
     * Immutable access to colour property
     * @return the colour property
     */
    public IntegerProperty colorProperty()
    {
        return color;
    }
}
