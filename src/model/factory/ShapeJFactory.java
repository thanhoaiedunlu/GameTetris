package model.factory;

import model.AShape;
import model.ShapeJ;

import java.awt.*;

public class ShapeJFactory implements ShapeFactory{
    Color color = Color.decode("#27ae60");
    @Override
    public AShape createShape() {
        return new ShapeJ(color);
    }
}
