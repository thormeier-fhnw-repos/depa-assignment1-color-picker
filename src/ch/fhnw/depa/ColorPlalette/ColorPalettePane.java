package ch.fhnw.depa.ColorPlalette;

import ch.fhnw.depa.ColorPlalette.Control.ColorList.ColorListControl;
import ch.fhnw.depa.ColorPlalette.Control.ColorRectangle.ColorRectangleControl;
import ch.fhnw.depa.ColorPlalette.Control.ColorSlider.ColorSliderControl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.awt.Color;

public class ColorPalettePane extends VBox
{
    /**
     * Top menu bar
     */
    private MenuBar menuBar;

    /**
     * Menu "File"
     */
    private Menu fileMenu;

    /**
     * Menu "Attributes", contains all colors to choose from
     */
    private Menu attributesMenu;

    /**
     * Triggers exiting the app
     */
    private MenuItem exitItem;

    /**
     * Input control for red
     */
    private ColorSliderControl redSlider;

    /**
     * Input control for blue
     */
    private ColorSliderControl blueSlider;

    /**
     * Input control for green
     */
    private ColorSliderControl greenSlider;

    /**
     * Displays the color as a rectangle
     */
    private ColorRectangleControl colorRectangle;

    /**
     * A list of colors to select from
     */
    private ColorListControl colorListRadio;

    /**
     * Darkens the color
     */
    private Button darkenButton;

    /**
     * Lightens the color
     */
    private Button brightenButton;

    /**
     * The main colour palette pane
     */
    public ColorPalettePane()
    {
        initializeParts();
        layoutParts();
        addValueChangedListener();
        addEventListeners();
    }

    /**
     * Initializes all necessary controls
     */
    private void initializeParts()
    {
        int min = 0;
        int max = 255;

        Color initialColor = new Color(100, 100, 100);

        redSlider = new ColorSliderControl("Red", initialColor.getRed(), min, max);
        greenSlider = new ColorSliderControl("Green", initialColor.getGreen(), min, max);
        blueSlider = new ColorSliderControl("Blue", initialColor.getBlue(), min, max);

        colorRectangle = new ColorRectangleControl(200, 270, initialColor);

        colorListRadio = new ColorListControl();
        colorListRadio.addColor(Color.RED, "Red")
            .addColor(Color.BLUE, "Blue")
            .addColor(Color.GREEN, "Green")
            .addColor(Color.CYAN, "Cyan")
            .addColor(Color.MAGENTA, "Magenta")
            .addColor(Color.ORANGE, "Orange");

        menuBar = new MenuBar();

        exitItem = new MenuItem("Exit");
        exitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));

        fileMenu = new Menu("File");

        attributesMenu = new Menu("Attributes");
        colorListRadio.getAvailableColors().forEach(stringColorEntry -> {
            MenuItem colorItem = new MenuItem(stringColorEntry.getKey());
            colorItem.setOnAction(event -> colorListRadio.chosenColorProperty().setValue(stringColorEntry.getValue()));

            attributesMenu.getItems().add(colorItem);
        });

        darkenButton = new Button("Darken");
        brightenButton = new Button("Brighten");
    }

    /**
     * Layouts all parts within the border pane
     */
    private void layoutParts()
    {
        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().addAll(fileMenu, attributesMenu);

        HBox bottom = new HBox();
        bottom.getChildren().addAll(darkenButton, brightenButton);
        bottom.setPadding(new Insets(5));
        bottom.setSpacing(10);
        bottom.setAlignment(Pos.CENTER);

        BorderPane main = new BorderPane();
        main.setLeft(colorRectangle);
        main.setRight(colorListRadio);

        getChildren().addAll(menuBar, redSlider, greenSlider, blueSlider, main, bottom);
    }

    /**
     * Attaches event listeners that don't listen to changed values
     */
    private void addEventListeners()
    {
        exitItem.setOnAction(event -> System.exit(0));

        brightenButton.setOnMouseClicked(event -> {
            Color brightenedColor = colorRectangle.colorProperty().getValue().brighter();
            setColorOnSliders(brightenedColor);
        });

        darkenButton.setOnMouseClicked(event -> {
            Color darkendColor = colorRectangle.colorProperty().getValue().darker();
            setColorOnSliders(darkendColor);
        });
    }

    /**
     * Add listeners and hook each part up with another
     */
    private void addValueChangedListener()
    {
        redSlider.colorProperty().addListener((observable, oldValue, newValue) -> {
            Color color = colorRectangle.colorProperty().get();

            colorRectangle.colorProperty().set(new Color(newValue.intValue(), color.getGreen(), color.getBlue()));
        });

        greenSlider.colorProperty().addListener((observable, oldValue, newValue) -> {
            Color color = colorRectangle.colorProperty().get();

            colorRectangle.colorProperty().set(new Color(color.getRed(), newValue.intValue(), color.getBlue()));
        });

        blueSlider.colorProperty().addListener((observable, oldValue, newValue) -> {
            Color color = colorRectangle.colorProperty().get();

            colorRectangle.colorProperty().set(new Color(color.getRed(), color.getGreen(), newValue.intValue()));
        });

        colorRectangle.colorProperty().addListener((observable, oldValue, newValue) -> {
            boolean canBeDarkened = !newValue.equals(newValue.darker());
            boolean canBeBrightend = !newValue.equals(newValue.brighter());

            darkenButton.setDisable(!canBeDarkened);
            brightenButton.setDisable(!canBeBrightend);
        });

        colorListRadio.chosenColorProperty().addListener((observable, oldValue, newValue) -> {
            setColorOnSliders(newValue);
        });
    }

    /**
     * Convenience method
     * @param color The Color to set all sliders to
     */
    private void setColorOnSliders(Color color)
    {
        redSlider.colorProperty().setValue(color.getRed());
        greenSlider.colorProperty().setValue(color.getGreen());
        blueSlider.colorProperty().setValue(color.getBlue());
    }
}
