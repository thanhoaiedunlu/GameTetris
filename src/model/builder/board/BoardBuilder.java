package model.builder.board;

import model.Board;
import model.strategy.EasyLevel;
import model.strategy.HardLevel;
import model.strategy.ILevelStategy;

public class BoardBuilder implements IBoardBuilder {
    private int width;
    private int height;
    private int delayTime;
    private ILevelStategy iLevelStategy = new HardLevel();
    @Override
    public BoardBuilder width(int width) {
        this.width = width;
        return this;
    }

    @Override
    public BoardBuilder height(int height) {
        this.height = height;
        return this;
    }

    @Override
    public BoardBuilder delayTime(int delayTime) {
        this.delayTime = delayTime;
        return this;
    }

    @Override
    public Board build() {
        return new Board(this.width, this.height, this.delayTime);
    }
    public Board build2() {
        return new Board(this.iLevelStategy.setBoardWidth(), this.iLevelStategy.setBoardHeight(), this.iLevelStategy.setDelayTime());
    }
}
