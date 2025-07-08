package ch.noseryoung.blj.engine;

import ch.noseryoung.blj.actions.Action;
import ch.noseryoung.blj.conditions.Condition;
import ch.noseryoung.blj.input.InputManager;
import java.util.*;

public class Place {
    private String id;
    private String name;
    private String description;

    private Map<String, Object> states;
    private Map<Direction, Exit> exits;
    private Map<Condition, Action> conditionalActions;
    // todo-possible: make it possible that a condition can do multiple actions and a action needs multiple actions

    private InputManager inputManager;

    public Place() {}

    public void init(String id, String name, String description, Map<String, Object> states, Map<Direction, Exit> exits, Map<Condition, Action> conditionalActions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.states = states;
        this.exits =  exits;
        inputManager = new InputManager();
    }

    public void update() {
        checkConditions();
        describe();
        String input = inputManager.getInput(); // todo: noch überarbeiten
    }

    private void describe() { // todo: make description more flexible
        System.out.println("\n=== " + name + " ===");
        System.out.println(description);
        if (!exits.isEmpty()) {
            System.out.println("Exits: " + exits.keySet()); // todo: make if it is not open, the exit is gray
        } else {
            System.out.println("Exits: No exits");
        }
    }

    private void checkConditions() {
        try {
            for (Map.Entry<Condition, Action> entry : conditionalActions.entrySet()) {
                Condition condition = entry.getKey();
                Action action = entry.getValue();
                if (condition.test()) {
                    action.execute();
                }
            }
        } catch (Exception _) {}
    }


    // |--- states ---|

    public <T> void adsState(String key, T value, Class<T> type) {
        if (!states.containsKey(key)) {
            if (!type.isInstance(value)) {
                throw new IllegalArgumentException("Value is not of type " + type.getName());
            }
            states.put(key, value);
        }
    }

    public <T> T getState(String key, Class<T> type) {
        Object value = states.get(key);
        if (type.isInstance(value)) {
            return type.cast(value);
        }
        throw new ClassCastException("State value for key '" + key + "' is not of type " + type.getName());
    }

    public Class<?> getStateType(String key) {
        Object value = states.get(key);
        if (value == null) {
            throw new IllegalStateException("No value found for key '" + key + "'");
        }
        return value.getClass();
    }

    public Map<String, Object> getStates() {
        return states;
    }

    public <T> void changeState(String key, T newValue, Class<T> type) {
        Object currentValue = states.get(key);
        if (currentValue == null) {
            throw new IllegalStateException("No states found for key '" + key + "'");
        }
        if (!type.isInstance(currentValue)) {
            throw new ClassCastException("Existing states value for key '" + key + "' is not of type " + type.getName());
        }
        states.put(key, newValue);
    }

    public <T> boolean doesStateExist(String key, Class<T> type) {
        Object value = states.get(key);
        return type.isInstance(value);
    }


    // |--- exits ---|

    public Exit getExit(Direction direction) {
        return exits.get(direction);
    }

    public Map<Direction, Exit> getExits() {
        return Collections.unmodifiableMap(exits);
    }


    // |--- conditional actions ---|

    public Map<Condition, Action> getConditionalActions() {
        return Collections.unmodifiableMap(conditionalActions);
    }


    // |--- other getters & setters ---|

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
