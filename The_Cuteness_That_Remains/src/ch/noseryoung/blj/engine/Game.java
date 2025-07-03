package ch.noseryoung.blj.engine;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static boolean isRunning;
    private static Game instance;
    private List<Place> places;
    private Place currentPlace;

    private Game() {
        places = new ArrayList<>();
    }

    public static Game instantiateGame() {
        if (Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }

    public static void terminateGame() {
        isRunning = false;
    }

    public void run() {
        isRunning = true;
        while (isRunning) {
            currentPlace.update();
        }
    }

    public static void changePlace() {

    }
}
