package ch.noseryoung.blj.actions;

import ch.noseryoung.blj.engine.Place;

import java.util.Map;

public class MoveAction extends Action {

    @Override
    public Map<String, Class<?>> getExpectedParams() {
        return Map.of(
                "healthAmount", String.class
        );
    }

    @Override
    public void execute(Place currentPlace) {
        // todo: make this
    }
}
