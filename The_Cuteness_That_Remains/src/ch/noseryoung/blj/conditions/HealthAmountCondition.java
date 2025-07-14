package ch.noseryoung.blj.conditions;

import ch.noseryoung.blj.engine.ParametrizedBehavior;
import ch.noseryoung.blj.engine.Place;

import java.util.Map;

public class HealthAmountCondition extends Condition {

    @Override
    public Map<String, Class<?>> getExpectedParams() {
        return Map.of(
                "min", Integer.class,
                "max", Integer.class
        );
    }

    @Override
    public boolean test(Place currentPlace) {
        return false; // todo: make this
    }
}
