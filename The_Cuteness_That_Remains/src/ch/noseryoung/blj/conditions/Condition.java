package ch.noseryoung.blj.conditions;

import ch.noseryoung.blj.engine.ParametrizedBehavior;
import ch.noseryoung.blj.engine.Place;

import java.util.Map;

// todo: make condition pool for better performance
public abstract class Condition implements ParametrizedBehavior {
    protected Place currentPlace;

    public Condition(Place currentPlace) {
        this.currentPlace = currentPlace;
    }

    @Override
    public Map<String, Class<?>> getExpectedParams() {
        return Map.of();
    }

    public abstract boolean test();
}