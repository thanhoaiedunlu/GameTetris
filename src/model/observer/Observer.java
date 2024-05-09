package model.observer;

import model.Board;

public interface Observer {
    void update(Board board);
    void setAttributes(Board board);
}
