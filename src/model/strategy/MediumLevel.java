package model.strategy;

public class MediumLevel implements ILevelStategy{
    @Override
    public int setBoardWidth() {
        return 8;
    }
    @Override
    public int setBoardHeight() {
        return 18;
    }
    @Override
    public int setDelayTime() {
        return 800;
    }
}
