# depa Übung 1 - Color Palette

This project serves as a solution for depa Assignment 1. This basic color
picker has all features listed in the assignment paper.

## Structure

The project is built on the shoulders of JavaFX which uses MVC for its
controls in the back. The custom controls of the app are built the same way:

A control class offers properties one can program against, its skin handles
the looks, layout and interaction.

For this color palette, there's four different custom controls:

- `ch.fhnw.depa.ColorPalette.Control.ColorList` - Selection of color with radio buttons
- `ch.fhnw.depa.ColorPalette.Control.ColorRectangle` - A canvas that shows the currently selected color
- `ch.fhnw.depa.ColorPalette.Control.ColorSlider` - Slider input containing text field, used for one of R, G and B seperately
- `ch.fhnw.depa.ColorPalette.Control.NumberField` - A TextField specififcally for numbers

Those controls are wired together and layouted in the class `ColorPalettePane`.
The `ColorPalettePane` also adds the top menu bar and the `Darken` and `Brighten`
buttons for which no custom control is needed.

## UML disclaimer

The UML has gotten a bit bigger than expected, because of the dependencies to JavaFX.
Fully qualified class names/paths for parameter and return types have mostly been
omitted because of mentioned size issues.
