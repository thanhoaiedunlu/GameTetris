package model.factory;

import model.AShape;
import model.ShapeI;

import java.awt.*;

public class ShapeIFactory implements ShapeFactory{
    Color color = Color.decode("#EA2027");
    @Override
    public AShape createShape() {
        return new ShapeI(color);
    }
}
