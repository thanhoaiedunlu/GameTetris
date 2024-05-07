package model.factory;

import model.AShape;
import model.ShapeL;

import java.awt.*;

public class ShapeLFactory implements ShapeFactory{
    Color color = Color.decode("#3498db");
    @Override
    public AShape createShape() {
        return new ShapeL(color);
    }
}
