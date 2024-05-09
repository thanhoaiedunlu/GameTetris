package model.observer;

public interface Subject {
    void addObserver(Observer... observer);
    void removeObserver(Observer... observer);
    public void notifyObservers();
}
