package ch.noseryoung.blj.actions;

import ch.noseryoung.blj.GameScene;
import ch.noseryoung.blj.engine.Place;

import java.util.Map;

public class TeleportAction extends Action {

    public TeleportAction(Place currentPlace) {
        super(currentPlace);
    }

    @Override
    public Map<String, Class<?>> getExpectedParams() {
        return Map.of(
                "targetPlaceId", String.class
        );
    }

    @Override
    public void execute() {
        GameScene.getInstance().changePlaceById(currentPlace.getState("TeleportAction_placeId", String.class));
    }
}
