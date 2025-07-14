package ch.noseryoung.blj.loaders;

import ch.noseryoung.blj.engine.Place;
import ch.noseryoung.blj.inputmanagement.InputOption;
import ch.noseryoung.blj.inputmanagement.InputParameter;
import ch.noseryoung.blj.util.JsonLoader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InputOptionLoader extends JsonLoader {

    public List<InputOption> loadInputs(String resourceFolder, Place place) {
        List<InputOption> options = new ArrayList<>();
        List<String> files = listJsonFiles(resourceFolder);

        for (String path : files) {
            String content = readResourceFile(path);
            JSONObject obj = new JSONObject(content);

            InputOption option = new InputOption();
            option.setKey(obj.getString("keyword"));

            // parse params
            List<InputParameter> parameters = new ArrayList<>();
            JSONArray paramArray = obj.optJSONArray("params");
            if (paramArray != null) {
                for (int i = 0; i < paramArray.length(); i++) {
                    JSONObject paramObj = paramArray.getJSONObject(i);
                    String name = paramObj.getString("name");
                    String type = paramObj.getString("type");
                    parameters.add(new InputParameter(name, type));
                }
            }
            option.setParameters(parameters);

            // load action using ActionAndConditionLoader
            JSONObject actionObj = obj.getJSONObject("action");
            ActionAndConditionLoader loader = new ActionAndConditionLoader();
            JSONObject dummyCondition = new JSONObject().put("type", "TrueCondition");
            loader.loadActionAndCondition(dummyCondition, actionObj, place);
            option.setAction(loader.getAction());

            options.add(option);
        }

        return options;
    }
}