package ch.noseryoung.blj;

import ch.noseryoung.blj.util.frontendSamples.Output;

public class Game {
    public static boolean isRunning;
    private static Game instance;
    private static Scene currentScene;


    // |--- singleton ---|

    private Game() {
        // Game.currentScene = MenuScene.getInstance();
        Game.currentScene = GameScene.getInstance(); // todo: remove cause this is for testing
    }

    public static Game getInstance() {
        if (Game.instance == null) {
            Game.instance = new Game();
        }
        return Game.instance;
    }


    // |--- game loop ---|

    public void run() {
        isRunning = true;
        while (isRunning) {
            currentScene.update();
        }
    }


    // |--- change scene ---|

    public static void changeScene(int newScene) {
        switch (newScene) {
            case 0:
                currentScene = GameScene.getInstance();
                currentScene.init();
                break;
            case 1:
                currentScene = MenuScene.getInstance();
                currentScene.init();
                break;
            default:
                Output.printlnError("Unknown scene", String.valueOf(newScene));
        }
    }

}
