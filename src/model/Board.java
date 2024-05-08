package model;

import model.factory.ShapeFactoryProducer;

import java.awt.*;
import java.util.Arrays;

public class Board {
    private int width;
    private int height;
    private ShapeFactoryProducer shapeFactoryProducer;
    private Color[][] shapesFreeze;
    private long beginTime;
    private StateGame stateGame;
    private int delayTime;
    private Player player;
    private boolean collision = false;
    private AShape currentShape;
    public Board(int width, int height, int delayTime) {
        super();
        this.width = width;
        this.height = height;
        this.delayTime = delayTime;
        this.shapeFactoryProducer = shapeFactoryProducer.getInstance();
        this.stateGame = StateGame.PLAY;
        this.shapesFreeze = new Color[this.height][this.width];
        this.currentShape = this.getNewShape();
        this.player = this.getPlayer();
    }

    private AShape getNewShape() {
        return this.shapeFactoryProducer.createShape();
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
                this.player.setScore(this.player.getScore() + 10);
//                this.notifyObservers();
            }
        }
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
//        this.notifyObservers();
    }
    public void refresh() {
        this.player.setScore(0);
        for (int raw = 0; raw < this.shapesFreeze.length; raw++) {
            for (int col = 0; col < this.shapesFreeze[raw].length; col++) {
                this.shapesFreeze[raw][col] = null;
            }
        }
        this.stateGame = StateGame.PLAY;
        this.currentShape = this.getNewShape();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ShapeFactoryProducer getShapeFactoryProducer() {
        return shapeFactoryProducer;
    }

    public void setShapeFactoryProducer(ShapeFactoryProducer shapeFactoryProducer) {
        this.shapeFactoryProducer = shapeFactoryProducer;
    }

    public Color[][] getShapesFreeze() {
        return shapesFreeze;
    }

    public void setShapesFreeze(Color[][] shapesFreeze) {
        this.shapesFreeze = shapesFreeze;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public StateGame getStateGame() {
        return stateGame;
    }

    public void setStateGame(StateGame stateGame) {
        this.stateGame = stateGame;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public AShape getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(AShape currentShape) {
        this.currentShape = currentShape;
    }

    @Override
    public String toString() {
        return "Board{" +
                "width=" + width +
                ", height=" + height +
                ", shapeFactoryProducer=" + shapeFactoryProducer +
                ", shapesFreeze=" + Arrays.toString(shapesFreeze) +
                ", beginTime=" + beginTime +
                ", stateGame=" + stateGame +
                ", delayTime=" + delayTime +
                ", player=" + player +
                ", collision=" + collision +
                ", currentShape=" + currentShape +
                '}';
    }
}
