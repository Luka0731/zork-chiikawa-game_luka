package ch.noseryoung.blj.conditions;

import ch.noseryoung.blj.engine.ParametrizedBehavior;
import ch.noseryoung.blj.engine.Place;

import java.util.Map;

// todo: make condition pool for better performance
public abstract class Condition implements ParametrizedBehavior {

    @Override
    public Map<String, Class<?>> getExpectedParams() {
        return Map.of();
    }

    public abstract boolean test(Place currentPlace);
}