package ch.noseryoung.blj.input;

import ch.noseryoung.blj.GameScene;
import ch.noseryoung.blj.util.frontendSamples.Input;

public class InputManager {

    public String getInput() {
        String input = Input.getString("Where do you want to goo: ").toLowerCase().trim();
        if (input.equals("down")) {
            GameScene.getInstance().changePlaceById("dark-bunker");
        }
        if (input.equals("up")) {
            GameScene.getInstance().changePlaceById("hallway");
        }
        return "Just a Test";
    }
}
