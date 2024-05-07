package model.factory;

import model.AShape;
import model.ShapeZ;

import java.awt.*;

public class ShapeZFactory implements ShapeFactory {
    Color color = Color.decode("#d35400");
    @Override
    public AShape createShape() {
        return new ShapeZ(color);
    }
}
