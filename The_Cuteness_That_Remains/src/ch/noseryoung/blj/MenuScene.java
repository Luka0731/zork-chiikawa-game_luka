package ch.noseryoung.blj;

// todo: save file interface
// todo: menu and begrüssnachricht
public class MenuScene extends Scene {
    private static MenuScene instance;


    // |--- singleton ---|

    private MenuScene() {}

    public static MenuScene getInstance() {
        if (MenuScene.instance == null) {
            MenuScene.instance = new MenuScene();
        }
        return MenuScene.instance;
    }

    // |--- game loop ---|

    public void update() {
        isRunning = true;
        while (isRunning) {

        }
    }
}
