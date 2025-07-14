package ch.noseryoung.blj.actions;

import ch.noseryoung.blj.Game;
import ch.noseryoung.blj.engine.ParametrizedBehavior;
import ch.noseryoung.blj.engine.Place;

import java.util.Map;

// todo: make action pool for better performance
public abstract class Action implements ParametrizedBehavior {

    @Override
    public Map<String, Class<?>> getExpectedParams() {
        return Map.of();
    }

    public abstract void execute(Place currentPlace);
}