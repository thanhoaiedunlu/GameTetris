package model.factory;

import model.AShape;
import model.ShapeT;

import java.awt.*;

public class ShapeTFactory implements ShapeFactory{
    Color color = Color.decode("#f1c40f");
    @Override
    public AShape createShape() {
        return new ShapeT(color);
    }
}
