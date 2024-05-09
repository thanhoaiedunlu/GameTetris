package model;

import model.factory.ShapeFactoryProducer;
import model.observer.Observer;
import model.observer.Subject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board implements Subject {
    private final int width, height;
    private final ShapeFactoryProducer shapeFactory;
    private final Color[][] shapesFreeze;
    private long beginTime;
    private StateGame stateGame;
    private int delayTime, score;
    private boolean collision = false;
    private AShape currentShape;
    private final List<Observer> observers;

    public Board(int width, int height, int delayTime) {
        super();
        this.width = width;
        this.height = height;
        this.delayTime = delayTime;
        this.shapeFactory = ShapeFactoryProducer.getInstance();
        this.stateGame = StateGame.PLAY;
        this.shapesFreeze = new Color[this.height][this.width];
        this.currentShape = this.getNewShape();
        this.observers = new ArrayList<>();
    }


    private void createNewShape() {
        if (this.collision) {
            int[][] element = this.currentShape.getElement();
            for (int row = 0; row < element.length; row++) {
                for (int col = 0; col < element[0].length; col++) {
                    if (element[row][col] != 0) {
                        this.shapesFreeze[this.currentShape.getY() + row][col + this.currentShape.getX()] = currentShape.getColor();
                    }
                }
            }
            this.checkLine();
            this.currentShape = this.getNewShape();
            this.checkOverGame();
            this.collision = !this.collision;
        }
    }

    private AShape getNewShape() {
        return this.shapeFactory.createShape();
    }

    public void state() {
        if (this.stateGame == StateGame.PLAY) {
            this.createNewShape();
            if (System.currentTimeMillis() - this.beginTime > this.delayTime) {
                if (!this.currentShape.checkCollideBelow(this.height)) {
                    this.collision = this.currentShape.checkVerticalForMovement(this.shapesFreeze);
                    if (!collision) this.currentShape.moveDown();
                } else {
                    this.collision = true;
                }
                this.beginTime = System.currentTimeMillis();
            }
        }
        this.notifyObservers();
    }

    private void checkLine() {
        int bottomLine = this.shapesFreeze.length - 1;
        for (int topLine = bottomLine; topLine > 0; topLine--) {
            int count = 0;
            for (int col = 0; col < this.shapesFreeze[0].length; col++) {
                if (this.shapesFreeze[topLine][col] != null) count++;
                this.shapesFreeze[bottomLine][col] = this.shapesFreeze[topLine][col];
            }
            if (count < this.shapesFreeze[0].length) {
                bottomLine--;
            } else {
                this.score += 10;
                this.notifyObservers();
            }
        }
    }

    private void checkOverGame() {
        int[][] shape = this.currentShape.getElement();
        for (int raw = 0; raw < shape.length; raw++) {
            for (int col = 0; col < shape[0].length; col++) {
                if (shape[raw][col] != 0) {
                    if (this.shapesFreeze[raw + this.currentShape.getY()][col + this.currentShape.getX()] != null) {
                        this.stateGame = StateGame.OVER;
                    }
                }
            }
        }
    }

    public void refresh() {
        this.score = 0;
        for (int raw = 0; raw < this.shapesFreeze.length; raw++) {
            for (int col = 0; col < this.shapesFreeze[raw].length; col++) {
                this.shapesFreeze[raw][col] = null;
            }
        }
        this.stateGame = StateGame.PLAY;
        this.currentShape = this.getNewShape();
    }

    public Color[][] getShapesFreeze() {
        return this.shapesFreeze;
    }

    public AShape getCurrentShape() {
        return currentShape;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public StateGame getStateGame() {
        return stateGame;
    }

    public void setStateGame(StateGame stateGame) {
        this.stateGame = stateGame;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void addObserver(Observer... observer) {
        this.observers.addAll(Arrays.asList(observer));
    }

    @Override
    public void removeObserver(Observer... observer) {
        this.observers.removeAll(Arrays.asList(observer));
    }
    @Override
    public void notifyObservers() {
        this.observers.forEach(observer -> observer.update(this));
    }
}
