package ch.noseryoung.blj.conditions;

import ch.noseryoung.blj.engine.Place;

import java.util.Map;

public class InputCondition extends Condition {

    @Override
    public Map<String, Class<?>> getExpectedParams() {
        return Map.of(
                "turns", Integer.class
        );
    }

    @Override
    public boolean test(Place  currentPlace) {
        return false;
    }
}
