package ch.noseryoung.blj.conditions;

import ch.noseryoung.blj.engine.Place;

import java.util.Map;

public class RoomTurnsCondition extends Condition {
    private int currentTurn;

    public RoomTurnsCondition(Place currentPlace) {
        super(currentPlace);
        currentTurn = 0;
    }

    @Override
    public Map<String, Class<?>> getExpectedParams() {
        return Map.of(
                "turns", Integer.class
        );
    }

    @Override
    public boolean test() {
        currentTurn++;
        return currentTurn >= currentPlace.getState("RoomTurnsCondition_turns", Integer.class);
    }
}
