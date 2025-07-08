package ch.noseryoung.blj.loaders;

import ch.noseryoung.blj.actions.Action;
import ch.noseryoung.blj.conditions.Condition;
import ch.noseryoung.blj.engine.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class PlaceLoader {

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
            JSONArray condArray = json.getJSONArray("conditionalActions");
            for (int i = 0; i < condArray.length(); i++) {
                JSONObject behaviorJson = condArray.getJSONObject(i);

                JSONObject condJson = behaviorJson.getJSONObject("condition");
                JSONObject actJson = behaviorJson.getJSONObject("action");

                ActionAndConditionLoader actAndConLoader = new ActionAndConditionLoader();
                actAndConLoader.loadActionAndCondition(condJson, actJson, place);
                conditionActions.put(actAndConLoader.getCondition(), actAndConLoader.getAction());
            }

            place.init(id, name, description, states, exits,  conditionActions);
            places.add(place);
        }
        return places;
    }

    private String readResourceFile(String filePath) {
        InputStream stream = PlaceLoader.class.getClassLoader().getResourceAsStream(filePath);
        if (stream == null) throw new RuntimeException("File not found: " + filePath);
        Scanner scanner = new Scanner(stream, StandardCharsets.UTF_8).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }

    private List<String> listJsonFiles(String resourceFolder) {
        List<String> fileNames = new ArrayList<>();
        URL folderUrl = PlaceLoader.class.getClassLoader().getResource(resourceFolder);
        if (folderUrl == null) throw new RuntimeException("Folder not found: " + resourceFolder); // todo: better exceptions

        File folder = new File(folderUrl.getFile());
        File[] files = folder.listFiles();
        if (files == null) return fileNames;

        for (File file : files) {
            if (file.getName().endsWith(".json")) {
                fileNames.add(resourceFolder + "/" + file.getName());
            }
        }
        return fileNames;
    }


}