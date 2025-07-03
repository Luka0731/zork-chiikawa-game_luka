package ch.noseryoung.blj.engine;

public abstract class Place {

    private static Place instance;

    private String name;
    int x, y;

    private Place(String name, int x, int y) {

    }

    public static Game instantiateGame() {
        return null;
    }

    public abstract void update();
}
