package model.factory;

import model.AShape;
import model.ShapeO;

import java.awt.*;

public class ShapeOFactory implements ShapeFactory{
    Color color = Color.decode("#8e44ad");
    @Override
    public AShape createShape() {
        return new ShapeO(color);
    }

}
