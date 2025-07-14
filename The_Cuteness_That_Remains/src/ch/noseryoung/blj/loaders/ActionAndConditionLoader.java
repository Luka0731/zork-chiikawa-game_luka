package ch.noseryoung.blj.loaders;

import ch.noseryoung.blj.actions.Action;
import ch.noseryoung.blj.conditions.Condition;
import ch.noseryoung.blj.engine.ParametrizedBehavior;
import ch.noseryoung.blj.engine.Place;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// todo: seperate action and condition loader
public class ActionAndConditionLoader {

    private Condition condition;
    private Action action;

    public void loadActionAndCondition(JSONObject conditionJson, JSONObject actionJson, Place place) {
        // parse condition
        String conditionType = conditionJson.getString("type");
        JSONObject condParamsJson = conditionJson.optJSONObject("params");
        Map<String, Object> condParams = toParamMap(condParamsJson);

        // parse action
        String actionType = actionJson.getString("type");
        JSONObject actParamsJson = actionJson.optJSONObject("params");
        Map<String, Object> actParams = toParamMap(actParamsJson);

        // register params with type safety
        registerTypedParams(conditionType, condParams, place);
        registerTypedParams(actionType, actParams, place);

        // instantiate behavior objects
        this.condition = createCondition(conditionType);
        this.action = createAction(actionType);
    }

    public Condition getCondition() {
        return condition;
    }

    public Action getAction() {
        return action;
    }

    private Map<String, Object> toParamMap(JSONObject json) {
        Map<String, Object> map = new HashMap<>();
        if (json == null) return map;

        Iterator<String> keys = json.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            map.put(key, json.get(key));
        }
        return map;
    }

    private void registerTypedParams(String className, Map<String, Object> paramMap, Place place) {
        Map<String, Class<?>> expectedTypes = getExpectedParamTypes(className);

        for (Map.Entry<String, Class<?>> entry : expectedTypes.entrySet()) {
            String key = entry.getKey();
            Class<?> type = entry.getValue();

            if (!paramMap.containsKey(className + "_" + key)) {
                throw new IllegalArgumentException("Missing required parameter '" + key + "' for class '" + className + "'");
            }

            Object rawValue = paramMap.get(key);

            // type validation
            if (!isCompatible(rawValue, type)) {
                throw new IllegalArgumentException("Parameter '" + key + "' for '" + className + "' must be of type " +
                        type.getSimpleName() + ", but was " + rawValue.getClass().getSimpleName());
            }

            // casted, typed state insert
            insertTypedState(place, key, rawValue, type, className);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> void insertTypedState(Place place, String key, Object rawValue, Class<T> type, String className) {
        T typedValue = (T) rawValue;
        place.addState(className + "_" + key, typedValue, type);
    }

    private boolean isCompatible(Object value, Class<?> type) {
        if (type == Integer.class && value instanceof Number) return true;
        if (type == Double.class && value instanceof Number) return true;
        if (type == Boolean.class && value instanceof Boolean) return true;
        if (type == String.class && value instanceof String) return true;
        return type.isInstance(value);
    }

    private Map<String, Class<?>> getExpectedParamTypes(Object behaviorInstance) {
        if (behaviorInstance instanceof ParametrizedBehavior paramBehavior) {
            return paramBehavior.getExpectedParams();
        }
        return Map.of();
    }

    private Condition createCondition(String className) {
        try {
            Class<?> clazz = Class.forName("ch.noseryoung.blj.conditions." + className);
            return (Condition) clazz.getConstructor(Place.class).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create condition: " + className, e);
        }
    }

    private Action createAction(String className) {
        try {
            Class<?> clazz = Class.forName("ch.noseryoung.blj.actions." + className);
            return (Action) clazz.getConstructor(Place.class).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create action: " + className, e);
        }
    }
    // todo: make less code
}
