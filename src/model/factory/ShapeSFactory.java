package model.factory;

import model.AShape;
import model.ShapeS;

import java.awt.*;

public class ShapeSFactory implements ShapeFactory {
    Color color = Color.decode("#e056fd");
    @Override
    public AShape createShape() {
        return new ShapeS(color);
    }
}
