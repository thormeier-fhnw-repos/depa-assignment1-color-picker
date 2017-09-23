package ch.fhnw.depa.ColorPlalette.Control.NumberField;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import java.util.function.UnaryOperator;

public class NumberField extends TextField
{
    /**
     * TextField specifically for numbers
     * @param min Minimum value
     * @param max Maximum value
     */
    public NumberField(int min, int max)
    {
        UnaryOperator<TextFormatter.Change> integerFilter = (TextFormatter.Change change) -> {
            String newText = change.getControlNewText();
            if (newText.matches("^([0-9]{1,3})$")) {
                int intValue = Integer.parseInt(newText);

                if (intValue >= min && intValue <= max) {
                    return change;
                }
            }
            return null;
        };

        setTextFormatter(new TextFormatter<String>(integerFilter));
    }
}
