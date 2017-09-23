package ch.fhnw.depa.ColorPlalette.Control.ColorRectangle;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.SkinBase;
import java.awt.Color;

public class ColorRectangleSkin extends SkinBase<ColorRectangleControl>
{
    /**
     * The canvas control to paint the colour on
     */
    private Canvas canvas;

    /**
     *
     * @param control The control for which this Skin should attach to.
     * @param width
     * @param height
     */
    public ColorRectangleSkin(ColorRectangleControl control, int width, int height)
    {
        super(control);

        initializeParts(width, height);
        layoutParts();
        addValueChangedListeners();

        paintColor(getSkinnable().colorProperty().get());
    }

    /**
     * Initializes all parts of this skin
     * @param width  Width of the canvas
     * @param height Height of the canvas
     */
    private void initializeParts(int width, int height)
    {
        canvas = new Canvas();
        canvas.setWidth(width);
        canvas.setHeight(height);
    }

    /**
     * Layouts all parts of this skin
     */
    private void layoutParts()
    {
        getChildren().add(canvas);
    }

    /**
     * Add listeners to controls properties
     */
    private void addValueChangedListeners()
    {
        getSkinnable().colorProperty().addListener((observable, oldValue, newValue) -> paintColor(newValue));
    }

    /**
     * Paint a given color
     * @param color The color to paint
     */
    private void paintColor(Color color)
    {
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.setFill(new javafx.scene.paint.Color(
            ((float) color.getRed()) / 255.0f,
            ((float) color.getGreen())  / 255.0f,
            ((float) color.getBlue())  / 255.0f,
            1.0
        ));

        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
