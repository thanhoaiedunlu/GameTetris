package model.factory;

import model.*;

import java.awt.*;
import java.util.Random;

public class ShapeFactoryProducer {
    private Random random;
    private static  ShapeFactoryProducer instance;
    public ShapeFactoryProducer() {
        this.random = new Random();
    }
    public ShapeType getRandomShape() {
        ShapeType[] shapeTypes = ShapeType.values();
        return shapeTypes[this.random.nextInt(shapeTypes.length)];
    }
    public AShape generateShape(ShapeType type) {
        AShape shape;
        switch (type) {
            case SHAPE_T:
                shape = new ShapeTFactory().createShape();
                break;
            case SHAPE_Z:
                shape = new ShapeZFactory().createShape();
                break;
            case SHAPE_O:
                shape = new ShapeOFactory().createShape();
                break;
            case SHAPE_L:
                shape = new ShapeLFactory().createShape();
                break;
            case SHAPE_I:
                shape = new ShapeIFactory().createShape();
                break;
            case SHAPE_S:
                shape = new ShapeSFactory().createShape();
                break;
            case SHAPE_J:
                shape = new ShapeJFactory().createShape();
                break;
            default:
                throw new RuntimeException("Generate shape failed with type: " + type);
        }
        return shape;
    }
    public AShape createShape() {
        ShapeType shapeType = getRandomShape();
        return this.generateShape(shapeType);
    }
    public static ShapeFactoryProducer getInstance() {
        if (instance == null) instance = new ShapeFactoryProducer();
        return instance;
    }
    public static void main(String[] args) {
        ShapeFactoryProducer shapeFactoryProducer = new ShapeFactoryProducer();
        AShape shape = shapeFactoryProducer.createShape();
        System.out.println(shape);
    }
}
