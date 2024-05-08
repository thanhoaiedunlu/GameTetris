package model.strategy;

public class HardLevel implements ILevelStategy{
    @Override
    public int setBoardWidth() {
        return 10;
    }
    @Override
    public int setBoardHeight() {
        return 20;
    }
    @Override
    public int setDelayTime() {
        return 600;
    }
}
