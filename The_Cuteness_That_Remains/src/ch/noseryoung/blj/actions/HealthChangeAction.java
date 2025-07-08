package ch.noseryoung.blj.actions;

import ch.noseryoung.blj.engine.Place;

import java.util.Map;

public class HealthChangeAction extends Action {

    public HealthChangeAction(Place currentPlace) {
        super(currentPlace);
    }

    @Override
    public Map<String, Class<?>> getExpectedParams() {
        return Map.of(
                "healthAmount", String.class
        );
    }

    @Override
    public void execute() {
        // todo: make this
    }
}
