package model.strategy;

public class EasyLevel implements ILevelStategy {
    @Override
    public int setBoardWidth() {
        return 6;
    }
    @Override
    public int setBoardHeight() {
        return 16;
    }
    @Override
    public int setDelayTime() {
        return 1000;
    }

}
