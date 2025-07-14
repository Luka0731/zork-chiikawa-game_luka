package ch.noseryoung.blj.loaders;

import ch.noseryoung.blj.actions.Action;
import ch.noseryoung.blj.conditions.Condition;
import ch.noseryoung.blj.engine.*;
import ch.noseryoung.blj.util.JsonLoader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PlaceLoader extends JsonLoader {

    public List<Place> load(String resourceFolder) {
        List<Place> places = new ArrayList<>();
        Set<String> existingIds = new HashSet<>();
        List<String> jsonFiles = listJsonFiles(resourceFolder);

        for (String path : jsonFiles) {
            String content = readResourceFile(path);
            JSONObject json = new JSONObject(content);
            Place place = new Place();

            // check if id is unique
            String id = json.getString("id");
            if (existingIds.contains(id)) {
                throw new RuntimeException("Duplicate place ID found: '" + id + "' in file: " + path);
            }
            existingIds.add(id);

            String name = json.getString("name");
            String description = json.getString("description");

            // load states
            Map<String, Object> states = new HashMap<>();
            if (json.has("states")) {
                JSONObject stateObj = json.getJSONObject("states");
                for (String key : stateObj.keySet()) {
                    states.put(key, stateObj.get(key));
                }
            }

            // load exits
            Map<Direction, Exit> exits = new HashMap<>();
            if (json.has("exits")) {
                JSONObject exitObj = json.getJSONObject("exits");
                for (String dirStr : exitObj.keySet()) {
                    JSONObject exitJson = exitObj.getJSONObject(dirStr);
                    Direction direction = Direction.fromString(dirStr);
                    if (direction != null) {
                        String targetPlaceId = exitJson.getString("targetPlaceId");
                        boolean isAccessible = exitJson.optBoolean("isAccessible", true);
                        String blockedMsg = exitJson.optString("blockedMessage", "");
                        exits.put(direction, new Exit(targetPlaceId, isAccessible, blockedMsg));
                    }
                }
            }

            // load conditionalActions
            Map<Condition, Action> conditionActions = new HashMap<>();
            if (json.has("conditionalActions")) {
                JSONArray condArray = json.getJSONArray("conditionalActions");
                for (int i = 0; i < condArray.length(); i++) {
                    JSONObject behaviorJson = condArray.getJSONObject(i);

                    JSONObject condJson = behaviorJson.getJSONObject("condition");
                    JSONObject actJson = behaviorJson.getJSONObject("action");

                    ActionAndConditionLoader actAndConLoader = new ActionAndConditionLoader();
                    actAndConLoader.loadActionAndCondition(condJson, actJson, place);
                    conditionActions.put(actAndConLoader.getCondition(), actAndConLoader.getAction());
                }
            }

            // load inputOptions
            Map<String, Boolean> inputOptions = new HashMap<>();
            if (json.has("inputOptions")) {
                JSONArray inputArray = json.getJSONArray("inputOptions");
                for (int i = 0; i < inputArray.length(); i++) {
                    JSONObject inputJson = inputArray.getJSONObject(i);
                    String key = inputJson.getString("key");
                    boolean activated = inputJson.optBoolean("activated", true);
                    inputOptions.put(key, activated);
                }
            }

            place.init(id, name, description, states, exits, conditionActions, inputOptions);
            places.add(place);
        }
        return places;
    }
}
