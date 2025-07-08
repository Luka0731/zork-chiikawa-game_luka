package ch.noseryoung.blj.actions;

import ch.noseryoung.blj.Game;
import ch.noseryoung.blj.engine.ParametrizedBehavior;
import ch.noseryoung.blj.engine.Place;

import java.util.Map;

// todo: make action pool for better performance
public abstract class Action implements ParametrizedBehavior {
    protected Place currentPlace;

    public Action(Place currentPlace) {
        this.currentPlace = currentPlace;
    }

    public abstract void execute();

    @Override
    public Map<String, Class<?>> getExpectedParams() {
        return Map.of();
    }
}