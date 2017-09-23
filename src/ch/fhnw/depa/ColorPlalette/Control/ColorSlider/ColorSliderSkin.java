package ch.fhnw.depa.ColorPlalette.Control.ColorSlider;

import ch.fhnw.depa.ColorPlalette.Control.NumberField.NumberField;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ColorSliderSkin extends SkinBase<ColorSliderControl>
{
    /**
     * Value field
     */
    private NumberField base10Field;

    /**
     * Displays the hex value of the chosen colour
     */
    private Text hexField;

    /**
     * Actual slider
     */
    private Slider slider;

    /**
     * Label to the far left stating the colors name
     */
    private Text label;

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    public ColorSliderSkin(ColorSliderControl control, String label, int initialValue, int min, int max)
    {
        super(control);

        initializeParts(label, min, max);
        layoutParts();
        addValueChangedListeners();

        // Set initial value to whole control
        slider.valueProperty().setValue(initialValue);
    }

    /**
     * Initializes all parts of this control
     */
    private void initializeParts(String label, int min, int max)
    {
        this.label = new Text(label);
        base10Field = new NumberField(min, max);
        hexField = new Text();
        slider = new Slider(min, max, 0);
    }

    /**
     * Layouts all parts of this control, i.e. puts them in a FlowPane
     */
    private void layoutParts()
    {
        HBox box = new HBox();

        box.setPadding(new Insets(5, 10, 5, 10));
        box.setSpacing(10);

        box.setAlignment(Pos.CENTER_LEFT);
        box.getChildren().addAll(label, slider, base10Field, hexField);

        getChildren().add(box);
    }

    /**
     * Hooks all parts up with each other
     */
    private void addValueChangedListeners()
    {
        IntegerProperty skinnableColorProperty = getSkinnable().colorProperty();

        // Slider changes should be propagated back to colour value
        slider.valueProperty().addListener((observable, oldValue, newValue) -> skinnableColorProperty.set(newValue.intValue()));

        // Changes to Base 10 field should be propagated to colour value
        base10Field.textProperty().addListener((observable, oldValue, newValue) -> skinnableColorProperty.set(Integer.parseInt(newValue)));

        // Propagate change of colour value back to all fields
        skinnableColorProperty.addListener((observable, oldValue, newValue) -> {
            slider.valueProperty().setValue(newValue);
            base10Field.textProperty().setValue(newValue.toString());
            hexField.textProperty().setValue(Integer.toHexString(newValue.intValue()));
        });
    }
}
